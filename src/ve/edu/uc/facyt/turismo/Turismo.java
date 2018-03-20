package ve.edu.uc.facyt.turismo;


import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import ve.edu.uc.facyt.turismo.usuario.Proveedor;
import ve.edu.uc.facyt.turismo.usuario.Cliente;
import ve.edu.uc.facyt.turismo.transporte.Ruta;
import ve.edu.uc.facyt.turismo.hospedaje.Residencia;
import ve.edu.uc.facyt.turismo.hospedaje.Posada;
import ve.edu.uc.facyt.turismo.hospedaje.Hospedaje;
import ve.edu.uc.facyt.turismo.hospedaje.Campamento;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Datos de conexión a db
 * usuario = {
 *     usuario: "cliente_turismo",
 *     contraseña: "contraseña random"
 * }
 *
 * administrador = {
 *     usuario: "admin_turismo",
 *     contraseña: "contraseña muy segura"
 * }
 *
 */


public class Turismo {
    /** Información de la DB **/
    private String dbUser;
    private String dbPassword;
    private String dbName;
    private String dbHost;
    private String dbPort;
    /** Información de app **/
    private Cliente currentUser;

    public Turismo(String dbUser, String dbPassword, String dbName, String dbHost, String dbPort) {
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        this.dbName = dbName;
        this.dbHost = dbHost;
        this.dbPort = dbPort;

    }

    /**
     * Create a connection
     * @return connection
     */
    private Connection connect(){
        String connString = String.format("jdbc:postgresql://%s:%s/%s",dbHost,dbPort,dbName);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connString,dbUser,dbPassword);
        } catch (SQLException sqlE){
            System.out.println(sqlE.toString());
            System.exit(1);
        }
        return conn;
    }


    /**
     * Register user
     * @param user
     * @param password
     * @param conn
     * @return
     */

    public Cliente registerUser(String user,String password,Connection conn){
        String SQL;
        String randomString = UUID.randomUUID().toString().replaceAll("-","");
        String idCliente = user + randomString.charAt(0) + randomString.charAt(1) + randomString.charAt(2);
        Cliente c = null;
        try{
            c = new Cliente(idCliente,user,password);
            c.save(conn);
        }
        catch (SQLException e){
            System.out.println(e.toString());
            c = null;
        }

        return c;
    }


    public Cliente loginUser(String user, String password,Connection conn) throws SQLException{
        Cliente usuario = Cliente.find(conn,user);
        if(!password.equals(usuario.getClave())){
            throw new SQLException("Contraseña incorrecta");
        }
        return usuario;
    }

    private Cliente handleRegister(Connection conn){
        Scanner inn = new Scanner(System.in);
        String usuario;
        String contrasena;
        Cliente c = null;
        boolean valid = false;
        System.out.println("Ingrese \"GO BACK!!!\" en cualquier momento para volver atras");
        do {
            System.out.println("Nombre de usuario");
            usuario = inn.nextLine();
            if(usuario.equals("GO BACK!!!"))
                break;
            System.out.println("Contraseña");
            contrasena = inn.nextLine();
            if(contrasena.equals("GO BACK!!!"))
                break;

            else{
                c = registerUser(usuario,contrasena,conn);
                if(c == null)
                    System.out.println("Ha ocurrido un error, vuelva a intentar");
            }
        }while(c == null);
        return c;
    }

    private Cliente handleLogin(Connection conn){
        Scanner inn = new Scanner(System.in);
        String usuario;
        String contrasena;
        Cliente c = null;
        boolean valid = false;
        boolean volverAtras = false;
        do {
            System.out.println("Nombre de usuario");
            usuario = inn.nextLine();
            if(usuario.equals("GO BACK!!!"))
                break;

            System.out.println("Contraseña");
            contrasena = inn.nextLine();
            if(contrasena.equals("GO BACK!!!"))
                break;

            try {
                c = loginUser(usuario,contrasena,conn);
                valid = true;
            }
            catch (SQLException e){
                System.out.println("Credenciales inválidas");
                c = null;
            }
        }while(!valid);
        return c;
    }

    private void reportMenu(Connection conn){
        Scanner inn = new Scanner(System.in);
        int option;
        System.out.println("¿Qué desea hacer, administrador?");
        System.out.println("1 - Total de visitas por destino");
        System.out.println("2 - Proveedores de transporte de empresa");
        System.out.println("3 - Proveedores de residencia de empresa");
        System.out.println("4 - salir");
        do{
            option = inn.nextInt();
            if(option == 4)
                return;
        }while (option < 0 || option > 4);

    }

    private void createHospedaje(Connection conn){
        System.out.println("Ingrese los siguientes datos separados por un \";\"");
        System.out.println("precio, rif, dirección");
        Scanner inn = new Scanner(System.in);
        String value = inn.nextLine();
        String[] values = value.split(";");
        try {
            Hospedaje h = new Hospedaje(Integer.parseInt(values[0]),values[1],values[2]);
            h.save(conn);
        }
        catch (SQLException e){
            System.out.println(e.toString());
            System.out.println("No se pudo registrar el hospedaje");
        }
    }

    private void createTransporte(Connection conn){
        System.out.println("Ingrese los siguientes datos separados por un \";\"");
        System.out.println("id ruta, origen, destino, nombre organización, tipo, distancia, cupo,precio,fecha");
        Scanner inn = new Scanner(System.in);
        String value = inn.nextLine();
        String[] values = value.split(";");
        SimpleDateFormat f = new SimpleDateFormat("ddmmyyyy");
        Date parsed = null;
        System.out.println(values[0]);
        System.out.println(values[1]);
        System.out.println(values[2]);
        System.out.println(values[3]);
        System.out.println(values[4]);
        System.out.println(values[5]);
        System.out.println(values[6]);
        System.out.println(values[7]);
        System.out.println(values[8]);
        try {
            parsed = f.parse(values[8]);
        }catch (Exception e){
            System.out.println("fecha invalida");
        }
        java.sql.Date fecha = new java.sql.Date(parsed.getTime());
        int precio = Integer.parseInt(values[7]);
        int cupo = Integer.parseInt(values[6]);
        Integer distancia = Integer.parseInt(values[5]);
        try{
            Ruta r = new Ruta(values[0],values[1],values[2],values[3],values[4],distancia,cupo,precio,fecha);
            r.save(conn);
        }catch (SQLException e){
            System.out.println(e.toString());
            System.out.println("No se pudo registrar la ruta");
        }
    }



    /**
     * Interfaz para el menu del administrador
     * @param c
     */

    private void showAdminMainMenu(Cliente c,Connection conn){
        while (true){
            Scanner inn = new Scanner(System.in);
            int option;
            System.out.println("¿Qué desea hacer, administrador?");
            System.out.println("1 - Ingresar Hospedaje");
            System.out.println("2 - Ingresar Transporte");
            System.out.println("3 - Emitir reporte");
            System.out.println("4 - salir");
            do{
                option = inn.nextInt();
                if(option == 4)
                    System.exit(1);
            }while(option<0 || option >4);
            if(option == 1)
                createHospedaje(conn);
            else if(option == 2)
                createTransporte(conn);
            else
                reportMenu(conn);
        }

    }

    /**
     * Cambiar datos del usuario
     * @param c
     * @param conn
     */
    private void handleChangeData(Cliente c, Connection conn){
        String[] opciones = {"ciudad","contraseña","estado","nacionalidad","nombre","estado civil"};
        while (true){
            System.out.println("¿Qué desea cambiar?");
            System.out.println("1 - Ciudad");
            System.out.println("2 - Contraseña");
            System.out.println("3 - Estado");
            System.out.println("4 - Nacionalidad");
            System.out.println("5 - Nombre");
            System.out.println("6 - Estado civil");
            System.out.println("7 - salir");

            Scanner inn = new Scanner(System.in);
            int option;
            String newValue = null;
            do{
                option = inn.nextInt();
                if(option == 7)
                    System.exit(1);
            }while(option < 0 || option > 7);
            System.out.println(String.format("Ingrese su nuevo valor para %s",opciones[option-1]));
            inn = new Scanner(System.in);
            newValue = inn.nextLine();
            switch (option){
                case 1:
                    c.setCiudad(newValue);
                    break;
                case 2:
                    c.setClave(newValue);
                    break;
                case 3:
                    c.setEstado(newValue);
                    break;
                case 4:
                    c.setNacionalidad(newValue);
                    break;
                case 5:
                    c.setNombre_c(newValue);
                    break;
                case 6:
                    c.setEdo_civil(newValue);
                    break;
                default:
                    System.out.println("opcion no válida");
            }
            try{

                c.save(conn);
            }
            catch (SQLException e){
                System.out.println("Ha ocurrido un error al guardar sus cambios, prueba de nuevo");
            }
        }
    }


    /**
     * Maneja el mostrar el menú
     * @param Cliente c
     */
    private void showUserMainMenu(Cliente c,Connection conn){
        Scanner inn = new Scanner(System.in);
        int option;
        System.out.println(String.format("¿Qué desea hacer, %s?",c.getUsuario()));
        System.out.println("1 - Cambiar datos");
        System.out.println("2 - salir");
        do{
            option = inn.nextInt();
            if(option == 2)
                System.exit(1);
        }while (option<0 || option >2);
        handleChangeData(c,conn);

    }


    public static void main(String[] args){
        Turismo app = new Turismo("cliente_turismo","contraseña random","proyecto_turismo","localhost","5432");
        Connection conn = app.connect();
        int option;
        boolean exit = false;
        Scanner inn = new Scanner(System.in);
        Cliente c = null;
        do{
            System.out.println("¿Qué desea hacer?");
            System.out.println("1 - Registro");
            System.out.println("2 - Login");
            System.out.println("3 - exit");
            do{
                option = inn.nextInt();
                if(option < 1 || option >3)
                    System.out.println("Opción inválida");
            }while(option < 1 || option >3);
            if(option != 3)
                c = option==1? app.handleRegister(conn):app.handleLogin(conn);
            else
                exit = true;
        }while(c == null && !exit);
        if(!exit) {
            if (c.isAdmin()) {
                try{
                    conn.close();
                }catch (Exception e){
                    System.out.println(e.toString());
                }

                app.setDbUser("admin_turismo");
                app.setDbPassword("contraseña muy segura");
                conn = app.connect();
                app.showAdminMainMenu(c,conn);
            } else {
                app.showUserMainMenu(c,conn);
            }
        }
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }
}

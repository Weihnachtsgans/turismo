package ve.edu.uc.facyt.turismo;


import ve.edu.uc.facyt.turismo.usuario.Proveedor;
import ve.edu.uc.facyt.turismo.usuario.Cliente;
import ve.edu.uc.facyt.turismo.transporte.Ruta;
import ve.edu.uc.facyt.turismo.hospedaje.Residencia;
import ve.edu.uc.facyt.turismo.hospedaje.Posada;
import ve.edu.uc.facyt.turismo.hospedaje.Hospedaje;
import ve.edu.uc.facyt.turismo.hospedaje.Campamento;


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
        }
        return conn;
    }


    /**
     * Update user info
     * @param c
     * @param conn
     * @return
     */
    public boolean updateUser(Cliente c, Connection conn){
        try {
            c.save(conn);
        }
        catch(SQLException e){
            return false;
        }
        return true;
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
        boolean success = true;
        try{
            SQL = "INSERT INTO cliente VALUES(?,?,?,?,?,?,?,?,?)";
            c = new Cliente(idCliente,user,password);
            c.save(conn);
        }
        catch (SQLException e){
            success = false;
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
        Cliente c;
        boolean valid = false;
        boolean volverAtras = false;
        System.out.println("Ingrese \"GO BACK!!!\" en cualquier momento para volver atras");
        do {
            System.out.println("Nombre de usuario");
            usuario = inn.nextLine();
            System.out.println("Contraseña");
            contrasena = inn.nextLine();
            if(usuario.equals("GO BACK!!!") || contrasena.equals("GO BACK!!!")){
                volverAtras = true;
            }
            else{
                c = registerUser(usuario,contrasena,conn);
                if(c == null)
                    System.out.println("Ha ocurrido un error, vuelva a intentar")
            }
        }while(c == null && !volverAtras);
        return c;
    }

    private Cliente handleLogin(Connection conn){
        Scanner inn = new Scanner(System.in);
        String usuario;
        String contrasena;
        Cliente c = null;
        boolean valid = false;
        boolean volverAtras = false;
        boolean valid = false;
        do {
            System.out.println("Nombre de usuario");
            usuario = inn.nextLine();
            System.out.println("Contraseña");
            contrasena = inn.nextLine();
            if(usuario.equals("GO BACK!!!") || contrasena.equals("GO BACK!!!")){
                volverAtras = true;
            }
            else{
                try {
                    c = loginUser(usuario,contrasena,conn);
                    valid = true;
                }
                catch (SQLException e){
                    System.out.println("Credenciales inválidas");
                    c = null;
                }
            }
        }while(!valid && !volverAtras);
        return c;
    }


    public static void main(String[] args){
        Turismo app = new Turismo("cliente_turismo","contraseña random","turismo","localhost","5432");
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



    }
}

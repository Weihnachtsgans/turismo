package ve.edu.uc.facyt.turismo;


import ve.edu.uc.facyt.turismo.usuario.Proveedor;
import ve.edu.uc.facyt.turismo.usuario.Cliente;
import ve.edu.uc.facyt.turismo.transporte.Ruta;
import ve.edu.uc.facyt.turismo.hospedaje.Residencia;
import ve.edu.uc.facyt.turismo.hospedaje.Posada;
import ve.edu.uc.facyt.turismo.hospedaje.Hospedaje;
import ve.edu.uc.facyt.turismo.hospedaje.Campamento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    private String dbUser;
    private String dbPassword;
    private String dbName;
    private String dbHost;
    private String dbPort;

    public Turismo(String dbUser, String dbPassword, String dbName, String dbHost, String dbPort) {
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        this.dbName = dbName;
        this.dbHost = dbHost;
        this.dbPort = dbPort;
    }

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

    public boolean registerUser(String user,String password,Connection conn){
        String SQL;
        boolean success = true;
        try{
            SQL = "INSERT INTO cliente VALUES";
            Cliente c = new Cliente(user)
        }
        catch (SQLException e){
            success = false;
        }

        return success;
    }
    public static void main(String[] args){
        Turismo app = new Turismo("admin_turismo","contraseña muy segura","turismo","localhost","5432");
        Connection conn = app.connect();
        try {
            Cliente c = new Cliente("abc","user2","ciudad","such pasword","ninguno","Venezolana","Wot","Soltero",false);
            c.save(conn);

        }
        catch (Exception e){
            System.out.println(e.toString());
        }


    }
}

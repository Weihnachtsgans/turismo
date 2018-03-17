package ve.edu.uc.facyt.turismo.usuario;

import java.sql.*;
import ve.edu.uc.facyt.turismo.Model;
import ve.edu.uc.facyt.turismo.usuario.Cliente;


public class Proveedor extends Cliente{
    /*
    private String nombre;
    private String apellido;
    private String usuario;
    private String password;
    private String nacionalidad;
    private Boolean isAdmin;
    private boolean isNew = true;



    public Proveedor(String nombre, String apellido, String usuario, String password, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.password = password;
        this.nacionalidad = nacionalidad;
    }

    public Proveedor() {
        nombre = null;
        apellido = null;
        usuario = null;
        password = null;
        nacionalidad = null;
    }

    private Proveedor(String nombre, String apellido, String usuario, String password, String nacionalidad, boolean isNew) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.password = password;
        this.nacionalidad = nacionalidad;
        this.isNew = isNew;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean save(Connection c) throws SQLException{
        /*
        String SQL;

        //Si es un nuevo elemento, insertar, sino, actualizar. De esta forma, es posible usar el método tanto si es un objeto nuevo
        //como si es uno ya ubicado
        if(isNew){
            SQL = "INSERT INTO clientes(nombre,apellido,usuario,contraseña,es_admin) VALUES (?,?,?,?,?)";
        }
        else{
            SQL = "UPDATE clientes SET nombre=?,apellido=?,contraseña=?,es_admin=? WHERE usuario=?";
        }

        //Preparar la query, usando un prepared statement, para evitar inyecciones sql
        PreparedStatement stmt;

        stmt = c.prepareStatement(SQL);
        stmt.setString(1,nombre);
        stmt.setString(2,apellido);
        if(isNew){
            stmt.setString(3,usuario);
            stmt.setString(4,password);
            stmt.setBoolean(5,isAdmin);
        }
        else{
            stmt.setString(3,password);
            stmt.setBoolean(4,isAdmin);
            stmt.setString(5,usuario);
        }

        return stmt.executeUpdate() > 0;
    }*/
}

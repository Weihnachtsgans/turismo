package ve.edu.uc.facyt.turismo.usuario;


import ve.edu.uc.facyt.turismo.Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Model{
    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String usuario;
    private String password;
    private String nacionalidad;
    private boolean isAdmin;
    private boolean isNew = true;

    /*
    public static Cliente find(String nombre);
    public static Cliente find(String nombre,String Apellido);
    public static Cliente find(String usuario,String password);
    public static Cliente find(String nombre,String Apellido,String usuario,String password,String nacionalidad);
    */

    public Cliente(Integer idCliente,String nombre, String apellido, String usuario, String password, String nacionalidad, boolean isAdmin) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.password = password;
        this.nacionalidad = nacionalidad;
        this.isAdmin = isAdmin;
    }

    public Cliente() {
        idCliente = null;
        nombre = null;
        apellido = null;
        usuario = null;
        password = null;
        nacionalidad = null;
        isAdmin = false;
    }

    private Cliente(Integer idCliente,String nombre, String apellido, String usuario, String password, String nacionalidad, boolean isAdmin, boolean isNew) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.password = password;
        this.nacionalidad = nacionalidad;
        this.isAdmin = isAdmin;
        this.isNew = isNew;
    }

    public static Cliente find(Connection c,String usuario) throws SQLException{
        //Query
        String SQL = "SELECT * FROM clientes WHERE usuario=?";
        List<Object> parameters = new ArrayList<>();
        parameters.add(usuario);
        ResultSet rs = Cliente.executeSelectQuery(SQL,parameters,c);

        Integer idCliente = rs.getInt("id_cliente");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        String usuario = rs.getString("usuario");
        String password = rs.getString("constraseña");
        String nacionalidad = rs.getString("nacionalidad");
        Boolean isAdmin = rs.getBoolean("es_admin");
        return new Cliente(idCliente,nombre,apellido,usuario,password,nacionalidad,isAdmin,false);

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

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    /** CAMBIAR **/
    public boolean save(Connection c) throws SQLException{
        String SQL;

        //Si es un nuevo elemento, insertar, sino, actualizar. De esta forma, es posible usar el método tanto si es un objeto nuevo
        //como si es uno ya ubicado
        if(isNew){
            SQL = "INSERT INTO clientes(nombre,apellido,usuario,contraseña,nacionalidad,es_admin,id_cliente) VALUES (?,?,?,?,?,?)";
        }
        else{
            SQL = "UPDATE clientes SET nombre=?,apellido=?,usuario=?,contraseña=?,nacionalidad=?,es_admin=? WHERE id_cliente=?";
        }

        //Preparar la query, usando un prepared statement, para evitar inyecciones sql
        PreparedStatement stmt;

        stmt = c.prepareStatement(SQL);

        stmt.setString(1,nombre);
        stmt.setString(2,apellido);
        stmt.setString(3,usuario);
        stmt.setString(4,password);
        stmt.setString(5,nacionalidad);
        stmt.setBoolean(6,isAdmin);
        stmt.setInt(7,idCliente);

        return stmt.executeUpdate() > 0;
    }


}

package ve.edu.uc.facyt.turismo.usuario;


import ve.edu.uc.facyt.turismo.Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Model{
    private String idCliente;
    private String usuario;
    private String ciudad;
    private String clave;
    private String estado;
    private String nacionalidad;
    private String nombre_c;
    private String edo_civil;
    private boolean isAdmin;
    private boolean isNew = true;

    public Cliente() {
    }

    public Cliente(String idCliente, String usuario, String ciudad, String clave, String estado, String nacionalidad, String nombre_c, String edo_civil, boolean isAdmin) {
        this.idCliente = idCliente;
        this.usuario = usuario;
        this.ciudad = ciudad;
        this.clave = clave;
        this.estado = estado;
        this.nacionalidad = nacionalidad;
        this.nombre_c = nombre_c;
        this.edo_civil = edo_civil;
        this.isAdmin = isAdmin;
    }

    private Cliente(String idCliente, String usuario, String ciudad, String clave, String estado, String nacionalidad, String nombre_c, String edo_civil, boolean isAdmin, boolean isNew) {
        this.idCliente = idCliente;
        this.usuario = usuario;
        this.ciudad = ciudad;
        this.clave = clave;
        this.estado = estado;
        this.nacionalidad = nacionalidad;
        this.nombre_c = nombre_c;
        this.edo_civil = edo_civil;
        this.isAdmin = isAdmin;
        this.isNew = isNew;
    }

    public Cliente(String idCliente,String usuario, String clave){
        this.idCliente = idCliente;
        this.usuario = usuario;
        this.clave = clave;

        this.ciudad = "cambiar";
        this.estado = "cambiar";
        this.nacionalidad = "cambiar";
        this.nombre_c = "cambiar";
        this.edo_civil = "Soltero";
        this.isAdmin = false;
    }


    public static Cliente find(Connection c,String usuario) throws SQLException{
        //Query
        String SQL = "SELECT * FROM cliente WHERE usuario=?";
        List<Object> parameters = new ArrayList<>();
        parameters.add(usuario);
        ResultSet rs = Cliente.executeSelectQuery(SQL,parameters,c);
        if(!rs.isBeforeFirst()){
            throw new SQLException("Coincidence not found");
        }
        rs.next();
        return new Cliente(
                rs.getString("id_cliente"),
                rs.getString("usuario"),
                rs.getString("ciudad"),
                rs.getString("clave"),
                rs.getString("estado"),
                rs.getString("nacionalidad"),
                rs.getString("nombre_c"),
                rs.getString("edo_civil"),
                rs.getBoolean("es_admin"),
                false
        );

    }

    public boolean save(Connection c) throws SQLException{
        String SQL;

        //Si es un nuevo elemento, insertar, sino, actualizar. De esta forma, es posible usar el método tanto si es un objeto nuevo
        //como si es uno ya ubicado
        if(isNew){
            SQL = "INSERT INTO cliente(usuario,ciudad,clave,estado,nacionalidad,nombre_c,edo_civil,es_admin,id_cliente) VALUES (?,?,?,?,?,?,?,?,?)";
        }
        else{
            SQL = "UPDATE cliente SET usuario=?,ciudad=?,clave=?,estado=?,nacionalidad=?,nombre_c=?,edo_civil=?,es_admin=? WHERE id_cliente=?";
        }

        //Añadir los parámetros
        List<Object> parameters = new ArrayList<>();
        parameters.add(usuario);
        parameters.add(ciudad);
        parameters.add(clave);
        parameters.add(estado);
        parameters.add(nacionalidad);
        parameters.add(nombre_c);
        parameters.add(edo_civil);
        parameters.add(isAdmin);
        parameters.add(idCliente);
        return Cliente.executePostQuery(SQL,parameters,c);
    }


    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNombre_c() {
        return nombre_c;
    }

    public void setNombre_c(String nombre_c) {
        this.nombre_c = nombre_c;
    }

    public String getEdo_civil() {
        return edo_civil;
    }

    public void setEdo_civil(String edo_civil) {
        this.edo_civil = edo_civil;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

}

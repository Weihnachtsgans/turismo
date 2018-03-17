package ve.edu.uc.facyt.turismo.usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ve.edu.uc.facyt.turismo.Model;


public class Proveedor extends Model{

    private String idProveedor;
    private String usuario;
    private String ciudad;
    private String password;
    private String estado;
    private String nacionalidad;
    private String nombre;
    private String paisOrigen;
    private String direccionR;
    private String edoCivil;
    private Boolean isAdmin;
    private boolean isNew = true;


    public Proveedor(String idProveedor, String usuario, String ciudad, String password, String estado, String nacionalidad, String nombre, String paisOrigen, String direccionR, String edoCivil, Boolean isAdmin) {
        this.idProveedor = idProveedor;
        this.usuario = usuario;
        this.ciudad = ciudad;
        this.password = password;
        this.estado = estado;
        this.nacionalidad = nacionalidad;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.direccionR = direccionR;
        this.edoCivil = edoCivil;
        this.isAdmin = isAdmin;
    }

    private Proveedor(String idProveedor, String usuario,String ciudad, String password, String estado, String nacionalidad, String nombre, String paisOrigen, String direccionR, String edoCivil, Boolean isAdmin, boolean isNew) {
        this.idProveedor = idProveedor;
        this.usuario = usuario;
        this.ciudad = ciudad;
        this.password = password;
        this.estado = estado;
        this.nacionalidad = nacionalidad;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.direccionR = direccionR;
        this.edoCivil = edoCivil;
        this.isAdmin = isAdmin;
        this.isNew = isNew;
    }

    public Proveedor() {

    }


    public boolean save(Connection c) throws SQLException {
        String SQL;
        if(isNew){
            SQL = "INSERT INTO proveedor(usuario,ciudad,clave,estado,nacionalidad,nombre_p,pais_origen,direccion_R,edo_civil,id_proveedor) VALUES (?,?,?,?,?,?,?,?,?,?)";
        }
        else{
            SQL = "UPDATE proveedor SET usuario=?, ciudad=?, clave=?, estado=?, nacionalidad=?, nombre_p=?, pais_origen=?, direccion_R=?, edo_civil=? WHERE id_proveedor=?";
        }
        List<Object> parameters = new ArrayList<>();
        parameters.add(usuario);
        parameters.add(ciudad);
        parameters.add(password);
        parameters.add(estado);
        parameters.add(nacionalidad);
        parameters.add(nombre);
        parameters.add(paisOrigen);
        parameters.add(direccionR);
        parameters.add(edoCivil);
        parameters.add(idProveedor);

        return Proveedor.executePostQuery(SQL,parameters,c);
    }

    public static Proveedor find(Connection c,String idProveedor) throws SQLException{
        String SQL = "SELECT * FROM proveedor WHERE id_proveedor=?";
        List<Object> parameters = new ArrayList<>();
        parameters.add(idProveedor);
        ResultSet rs = Proveedor.executeSelectQuery(SQL,parameters,c);
        return new Proveedor(
                rs.getString("id_proveedor"),
                rs.getString("usuario"),
                rs.getString("ciudad"),
                rs.getString("clave"),
                rs.getString("estado"),
                rs.getString("nacionalidad"),
                rs.getString("nombre_p"),
                rs.getString("pais_origen"),
                rs.getString("direccion_r"),
                rs.getString("edo_civil"),
                false,
                false
                );

    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getDireccionR() {
        return direccionR;
    }

    public void setDireccionR(String direccionR) {
        this.direccionR = direccionR;
    }

    public String getEdoCivil() {
        return edoCivil;
    }

    public void setEdoCivil(String edoCivil) {
        this.edoCivil = edoCivil;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}

package ve.edu.uc.facyt.turismo.transporte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ve.edu.uc.facyt.turismo.Model;

public class Ruta extends Model{
    private String idRuta;
    private String origen;
    private String destino;
    private String tipo;
    private Integer distancia;
    private int cupo;
    private float precio;
    private Date fecha;
    private boolean isNew = true;

    public Ruta(String idRuta, String origen, String destino, String tipo,int distancia, int cupo, float precio, Date fecha) {
        this.idRuta = idRuta;
        this.origen = origen;
        this.destino = destino;
        this.tipo = tipo;
        this.distancia = distancia;
        this.cupo = cupo;
        this.precio = precio;
        this.fecha = fecha;
        this.isNew = isNew;
    }
    private Ruta(String idRuta, String origen, String destino, String tipo, int distancia, int cupo, float precio, Date fecha, boolean isNew) {
        this.idRuta = idRuta;
        this.origen = origen;
        this.destino = destino;
        this.tipo = tipo;
        this.distancia = distancia;
        this.cupo = cupo;
        this.precio = precio;
        this.fecha = fecha;
        this.isNew = isNew;
    }


    public static Ruta find(Connection c, String idRuta) throws SQLException{
        String SQL = "SELECT * FROM ruta WHERE idRuta=?";
        List<Object> parameters = new ArrayList<>();
        parameters.add(idRuta);
        ResultSet rs = Ruta.executeSelectQuery(SQL,parameters,c);
        return new Ruta(
                rs.getString("id_r"),
                rs.getString("origen"),
                rs.getString("destino"),
                rs.getString("tipo_l"),
                rs.getInt("distancia"),
                rs.getInt("cupo"),
                rs.getFloat("precio"),
                rs.getDate("fecha"),
                false
                );

    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }


    public String getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(String idRuta) {
        this.idRuta = idRuta;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /** CAMBIAR **/
    public boolean save(Connection c) throws SQLException{

        String SQL;
        PreparedStatement stmt;
        if(isNew){
            SQL = "INSERT INTO ruta(origen,destino,tipo,id_r) VALUES (?,?,?,?)";
        }
        else{
            SQL = "UPDATE ruta SET origen=? destino=? tipo=? WHERE id_r=?";
        }
        stmt = c.prepareStatement(SQL);
        stmt.setString(4,idRuta);
        stmt.setString(1,origen);
        stmt.setString(2,destino);
        stmt.setString(3,tipo);


        return stmt.executeUpdate() > 0;
    }
}

package ve.edu.uc.facyt.turismo.transporte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ve.edu.uc.facyt.turismo.Model;

public class Ruta extends Model{
    private String idRuta;
    private String origen;
    private String destino;
    private String nombreL;
    private String tipo;
    private Integer distancia;
    private int cupo;
    private int precio;
    private Date fecha;
    private boolean isNew = true;

    public Ruta(String idRuta, String origen, String destino, String tipo,int distancia, int cupo, int precio, Date fecha) {
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


    private Ruta(String idRuta, String origen, String destino, String nombreL, String tipo, Integer distancia, int cupo, int precio, Date fecha, boolean isNew) {
        this.idRuta = idRuta;
        this.origen = origen;
        this.destino = destino;
        this.nombreL = nombreL;
        this.tipo = tipo;
        this.distancia = distancia;
        this.cupo = cupo;
        this.precio = precio;
        this.fecha = fecha;
        this.isNew = isNew;
    }

    public Ruta(String idRuta, String origen, String destino, String nombreL, String tipo, Integer distancia, int cupo, int precio, Date fecha) {
        this.idRuta = idRuta;
        this.origen = origen;
        this.destino = destino;
        this.nombreL = nombreL;
        this.tipo = tipo;
        this.distancia = distancia;
        this.cupo = cupo;
        this.precio = precio;
        this.fecha = fecha;
    }


    public static Ruta find(Connection c, String idRuta) throws SQLException{
        String SQL = "SELECT * FROM ruta WHERE idRuta=?";
        List<Object> parameters = new ArrayList<>();
        parameters.add(idRuta);
        ResultSet rs = Ruta.executeSelectQuery(SQL,parameters,c);
        if(!rs.isBeforeFirst()){
            throw new SQLException("Coincidence not found");
        }
        rs.next();
        return new Ruta(
                rs.getString("id_r"),
                rs.getString("origen"),
                rs.getString("destino"),
                rs.getString("nombre_l"),
                rs.getString("tipo_l"),
                rs.getInt("distancia"),
                rs.getInt("cupo"),
                rs.getInt("precio"),
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean save(Connection c) throws SQLException{

        String SQL;

        if(isNew){
            SQL = "INSERT INTO ruta(origen,destino,distancia,nombre_l,tipo_l,cupo,precio,fecha,id_r) VALUES (?,?,?,?,?,?,?,?,?)";
        }
        else{
            SQL = "UPDATE ruta SET origen=?,destino=?,distancia=?,nombre_l=?,tipo_l=?,cupo=?,precio=?,fecha=? WHERE id_r=?";
        }

        List<Object> parameters = new ArrayList<>();
        parameters.add(origen);
        parameters.add(destino);
        parameters.add(distancia);
        parameters.add(nombreL);
        parameters.add(tipo);
        parameters.add(cupo);
        parameters.add(precio);
        parameters.add(fecha);
        parameters.add(idRuta);
        System.out.println(origen);
        System.out.println(destino);
        System.out.println(distancia);
        System.out.println(nombreL);
        System.out.println(tipo);
        System.out.println(cupo);
        System.out.println(precio);
        System.out.println(fecha);
        System.out.println(idRuta);
        return Ruta.executePostQuery(SQL,parameters,c);
    }
}

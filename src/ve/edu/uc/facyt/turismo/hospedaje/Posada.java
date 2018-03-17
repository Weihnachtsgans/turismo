package ve.edu.uc.facyt.turismo.hospedaje;

import java.sql.*;
import ve.edu.uc.facyt.turismo.Model;

public class Posada extends Model{
    private String rif;
    private Integer calidad;
    private float precio;
    private Integer servicioP;
    private Integer habitaciones;
    private boolean isNew = true;

    public Posada() {
        this.rif = null;
        this.calidad = null;
        this.precio = 0;
        this.servicioP = null;
        this.habitaciones = null;
    }

    public Posada(String rif, Integer calidad, float precio, Integer servicioP, Integer habitaciones) {
        this.rif = rif;
        this.calidad = calidad;
        this.precio = precio;
        this.servicioP = servicioP;
        this.habitaciones = habitaciones;
    }

    private Posada(String rif, Integer calidad, float precio, Integer servicioP, Integer habitaciones, boolean isNew) {
        this.rif = rif;
        this.calidad = calidad;
        this.precio = precio;
        this.servicioP = servicioP;
        this.habitaciones = habitaciones;
        this.isNew = isNew;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public Integer getCalidad() {
        return calidad;
    }

    public void setCalidad(Integer calidad) {
        this.calidad = calidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Integer getServicioP() {
        return servicioP;
    }

    public void setServicioP(Integer servicioP) {
        this.servicioP = servicioP;
    }

    public Integer getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Integer habitaciones) {
        this.habitaciones = habitaciones;
    }

    public boolean save(Connection c) throws SQLException{
        String SQL;
        if(isNew){
            SQL = "INSERT INTO posada(calidad,servicioP,precio,habitaciones,rif_posada) VALUES (?,?,?,?,?)";
        }
        else{
            SQL = "UPDATE posada SET calidad=?,servicioP=?,precio=?,habitaciones=? WHERE rif_posada=?";
        }
        PreparedStatement stmt = c.prepareStatement(SQL);

        return stmt.executeUpdate() > 0;
    }
}

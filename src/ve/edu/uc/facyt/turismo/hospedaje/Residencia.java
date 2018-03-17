package ve.edu.uc.facyt.turismo.hospedaje;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import ve.edu.uc.facyt.turismo.Model;

public class Residencia extends Model{
    private String rif;
    private String direccion;
    private int precio;
    private boolean servicioResidencia;
    private boolean isNew = true;

    public Residencia(String rif,String direccion,int precio, boolean servicioResidencia) {
        this.rif = rif;
        this.direccion = direccion;
        this.precio = precio;
        this.servicioResidencia = servicioResidencia;
    }

    private Residencia(String rif,String direccion,int precio, boolean servicioResidencia,boolean isNew) {
        this.rif = rif;
        this.direccion = direccion;
        this.precio = precio;
        this.servicioResidencia = servicioResidencia;
        this.isNew = isNew;
    }


    public static Residencia find(Connection c,String rif, String dir) throws SQLException{
        String SQL;
    }


    public boolean save(Connection c) throws SQLException{
        String SQL;
        if(isNew){
            SQL = "INSERT INTO residencia(servicio_residencia,precio,rif_r,direccion_r) VALUES(?,?,?,?)";
        }
        else{
            SQL = "UPDATE residencia SET servicio_residencia=?, precio=? WHERE rif_r=? AND direccion_r=?";
        }

        PreparedStatement stmt = c.prepareStatement(SQL);
        stmt.setString(3,rif);
        stmt.setString(4,direccion);
        stmt.setInt(2,precio);
        stmt.setBoolean(1,servicioResidencia);

        return stmt.executeUpdate() > 0;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public boolean isServicioResidencia() {
        return servicioResidencia;
    }

    public void setServicioResidencia(boolean servicioResidencia) {
        this.servicioResidencia = servicioResidencia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}

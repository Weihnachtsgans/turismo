package ve.edu.uc.facyt.turismo.hospedaje;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ve.edu.uc.facyt.turismo.Model;

public class Residencia extends Model{
    private String rif;
    private String direccion;
    private boolean servicioResidencia;
    private int precio;
    private boolean isNew = true;

    public Residencia(String rif, String direccion, boolean servicioResidencia, int precio) {
        this.rif = rif;
        this.direccion = direccion;
        this.servicioResidencia = servicioResidencia;
        this.precio = precio;
    }

    public Residencia() {
    }

    private Residencia(String rif, String direccion, boolean servicioResidencia, int precio, boolean isNew) {
        this.rif = rif;
        this.direccion = direccion;
        this.servicioResidencia = servicioResidencia;
        this.precio = precio;
        this.isNew = isNew;
    }


    public static Residencia find(Connection c,String rif, String dir) throws SQLException{
        String SQL = "SELECT * FROM residencia WHERE rif_resid=? AND direccion_r=?";
        List<Object> parameters = new ArrayList<>();
        parameters.add(rif);
        parameters.add(dir);
        ResultSet rs = Residencia.executeSelectQuery(SQL,parameters,c);
        return new Residencia(
                rs.getString("rif_resid"),
                rs.getString("direccion_r"),
                rs.getBoolean("servicio_residencia"),
                rs.getInt("precio"),
                false
        );
    }


    public boolean save(Connection c) throws SQLException{
        String SQL;
        if(isNew){
            SQL = "INSERT INTO residencia(servicio_residencia,precio,rif_r,direccion_r) VALUES(?,?,?,?)";
        }
        else{
            SQL = "UPDATE residencia SET servicio_residencia=?, precio=? WHERE rif_r=? AND direccion_r=?";
        }

        List<Object> parameters = new ArrayList<>();
        parameters.add(servicioResidencia);
        parameters.add(precio);
        parameters.add(rif);
        parameters.add(direccion);

        return Residencia.executePostQuery(SQL,parameters,c);
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

package ve.edu.uc.facyt.turismo.hospedaje;


import ve.edu.uc.facyt.turismo.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Hospedaje extends Model{
    private int precio;
    private String rif;
    private String direccion;
    private boolean isNew = true;

    public Hospedaje(int precio, String rif, String direccion) {
        this.precio = precio;
        this.rif = rif;
        this.direccion = direccion;
    }
    public Hospedaje(){
        precio = 0;
        rif = null;
        direccion = null;
    }

    private Hospedaje(int precio, String rif, String direccion, boolean isNew) {
        this.precio = precio;
        this.rif = rif;
        this.direccion = direccion;
        this.isNew = isNew;
    }

    public static Hospedaje find(Connection conn,String rif) throws SQLException{
        String SQL = "SELECT * FROM Hospedaje WHERE rif=?";
        List<Object> parameters = new ArrayList<>();
        parameters.add(rif);
        ResultSet rs = Hospedaje.executeSelectQuery(SQL,parameters,conn);
        if(!rs.isBeforeFirst()){
            throw new SQLException("Coincidence not found");
        }
        rs.next();
        return new Hospedaje(
                rs.getInt("precio"),
                rs.getString("rif"),
                rs.getString("direccion"),
                false
        );
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean save(Connection c) throws SQLException{
        String SQL;
        if(isNew){
            SQL = "INSERT INTO hospedaje(precio,direccion,rif) VALUES (?,?,?)";
        }
        else{
            SQL = "UPDATE hospedaje SET precio=?,direccion=? WHERE rif=?";
        }
        List<Object> parameters = new ArrayList<>();
        parameters.add(this.precio);
        parameters.add(this.direccion);
        parameters.add(this.rif);
        return Hospedaje.executePostQuery(SQL,parameters,c);
    }
}

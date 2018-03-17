package ve.edu.uc.facyt.turismo.hospedaje;

import ve.edu.uc.facyt.turismo.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Campamento extends Model{
    private String rif;
    private long capacidad;
    private int precio;
    private boolean servicio_campamento;
    private boolean isNew = true;

    public Campamento(String rif, long capacidad,int precio,boolean servicio_campamento) {
        this.rif = rif;
        this.capacidad = capacidad;
        this.precio = precio;
        this.servicio_campamento = servicio_campamento;
    }
    public Campamento(){
        rif = null;
        capacidad = 0;
        precio = 0;
        servicio_campamento = false;
    }

    private Campamento(String rif, long capacidad,int precio, boolean servicio_campamento, boolean isNew) {
        this.rif = rif;
        this.capacidad = capacidad;
        this.precio = precio;
        this.servicio_campamento = servicio_campamento;
        this.isNew = isNew;
    }

    public static Campamento find(Connection c, String rif) throws SQLException{
        String SQL = "SELECT * FROM campamento WHERE rif=?";
        List<Object> parameters = new ArrayList<>();
        parameters.add(rif);
        ResultSet rs = Campamento.executeSelectQuery(SQL,parameters,c);
        return new Campamento(rs.getString("rif_camp"),rs.getLong("capacidad"),rs.getInt("precio"),rs.getBoolean("servicio_campamento"),false);
    }


    /** CAMBIAR **/
    public boolean save(Connection c) throws SQLException{
        String SQL;
        boolean result = false;
        //Si es un nuevo elemento, insertar, sino, actualizar. De esta forma, es posible usar el método tanto si es un objeto nuevo
        //como si es uno ya ubicado
        if(isNew){
            SQL = "INSERT INTO campamento (capacidad,rif_camp) VALUES (?,?)";
        }
        else {
            SQL = "UPDATE campamento SET capacidad=? WHERE rif_camp=?";
        }

        //Preparar la query, usando un prepared statement, para evitar inyecciones sql
        PreparedStatement stmt;
        stmt = c.prepareStatement(SQL);

        stmt.setString(2,rif);
        stmt.setLong(1,capacidad);

        return stmt.executeUpdate() > 0;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public long getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(long capacidad) {
        this.capacidad = capacidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isServicio_campamento() {
        return servicio_campamento;
    }

    public void setServicio_campamento(boolean servicio_campamento) {
        this.servicio_campamento = servicio_campamento;
    }

}

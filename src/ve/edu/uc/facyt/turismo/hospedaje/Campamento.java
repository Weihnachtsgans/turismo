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
    private int capacidad;
    private int precio;
    private boolean servicio_campamento;
    private boolean isNew = true;

    public Campamento(String rif, int capacidad,int precio,boolean servicio_campamento) {
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

    private Campamento(String rif, int capacidad,int precio, boolean servicio_campamento, boolean isNew) {
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



    public boolean save(Connection c) throws SQLException{
        String SQL;
        boolean result = false;
        //Si es un nuevo elemento, insertar, sino, actualizar. De esta forma, es posible usar el método tanto si es un objeto nuevo
        //como si es uno ya ubicado
        if(isNew){
            SQL = "INSERT INTO campamento (capacidad,precio,servicio_campamento,rif_camp) VALUES (?,?,?,?)";
        }
        else {
            SQL = "UPDATE campamento SET capacidad=?, precio=?, servicio_campamento=? WHERE rif_camp=?";
        }

        List<Object> parameters = new ArrayList<>();
        parameters.add(capacidad);
        parameters.add(precio);
        parameters.add(servicio_campamento);
        parameters.add(rif);

        return Campamento.executePostQuery(SQL,parameters,c);
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
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

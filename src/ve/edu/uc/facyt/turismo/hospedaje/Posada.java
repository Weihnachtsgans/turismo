package ve.edu.uc.facyt.turismo.hospedaje;

import java.util.Date;

public class Posada{
    private String rif;
    private String codigo;
    private float precio;
    private String direccion;
    private Date fechaInicio;
    private Date fechaFinal;

    public Posada(String rif, String codigo, float precio, String direccion, Date fechaInicio, Date fechaFinal) {
        this.rif = rif;
        this.codigo = codigo;
        this.precio = precio;
        this.direccion = direccion;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }
    public Posada(){
        rif = null;
        codigo = null;
        precio = 0;
        direccion = null;
        fechaInicio = null;
        fechaFinal = null;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public boolean save(){
        return true;
    }
}

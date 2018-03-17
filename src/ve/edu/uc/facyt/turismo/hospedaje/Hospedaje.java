package ve.edu.uc.facyt.turismo.hospedaje;

public class Hospedaje {
    private float precio;
    private String rif;
    private String direccion;

    public Hospedaje(float precio, String rif, String direccion) {
        this.precio = precio;
        this.rif = rif;
        this.direccion = direccion;
    }
    public Hospedaje(){
        precio = 0;
        rif = null;
        direccion = null;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
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

    public boolean save(){
        return true;
    }
}

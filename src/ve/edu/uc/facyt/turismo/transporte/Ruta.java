package ve.edu.uc.facyt.turismo.transporte;

public class Ruta {
    private String origen;
    private String destino;
    private String tipo;
    private float costo;

    public Ruta(String origen, String destino, String tipo, float costo) {
        this.origen = origen;
        this.destino = destino;
        this.tipo = tipo;
        this.costo = costo;
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
    public boolean save(){
        //
        return true;
    }

}

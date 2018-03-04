package ve.edu.uc.facyt.turismo.hospedaje;

public class Campamento {
    private String rif;
    private long capacidad;

    public Campamento(String rif, long capacidad) {
        this.rif = rif;
        this.capacidad = capacidad;
    }
    public Campamento(){
        rif = null;
        capacidad = 0;
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

    public boolean save(){
        return true;
    }
}

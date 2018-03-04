package ve.edu.uc.facyt.turismo.hospedaje;

public class Residencia {
    private String rif;
    private boolean servicioResidencia;

    public Residencia(String rif, boolean servicioResidencia) {
        this.rif = rif;
        this.servicioResidencia = servicioResidencia;
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

    public boolean save(){
        return true;
    }
}

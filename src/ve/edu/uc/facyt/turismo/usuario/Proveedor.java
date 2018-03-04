package ve.edu.uc.facyt.turismo.usuario;

public class Proveedor {
    private String nombre;
    private String apellido;
    private String usuario;
    private String password;
    private String nacionalidad;

    /*
    public static Cliente find(String nombre);
    public static Cliente find(String nombre,String Apellido);
    public static Cliente find(String usuario,String password);
    public static Cliente find(String nombre,String Apellido,String usuario,String password,String nacionalidad);
    */

    public Proveedor(String nombre, String apellido, String usuario, String password, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.password = password;
        this.nacionalidad = nacionalidad;
    }

    public Proveedor() {
        nombre = null;
        apellido = null;
        usuario = null;
        password = null;
        nacionalidad = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean save(){
        //Save into db
        return true;
    }
}

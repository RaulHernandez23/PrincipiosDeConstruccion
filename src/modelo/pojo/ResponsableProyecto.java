package modelo.pojo;

public class ResponsableProyecto {

    private int idResponsableProyecto;
    private String numPersonal;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String telefono;
    private String password;

    public ResponsableProyecto() {
    }

    public ResponsableProyecto(int idResponsableProyecto, String numPersonal, String nombre, String apellidoPaterno,
            String apellidoMaterno, String correo, String telefono, String password) {

        this.idResponsableProyecto = idResponsableProyecto;
        this.numPersonal = numPersonal;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;

    }

    public int getIdResponsableProyecto() {
        return idResponsableProyecto;
    }

    public void setIdResponsableProyecto(int idResponsableProyecto) {
        this.idResponsableProyecto = idResponsableProyecto;
    }

    public String getNumPersonal() {
        return numPersonal;
    }

    public void setNumPersonal(String numPersonal) {
        this.numPersonal = numPersonal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

        if (nombre != null) {
            this.nombre = nombre;
        }

    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {

        if (apellidoPaterno != null) {
            this.apellidoPaterno = apellidoPaterno;
        }

    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {

        if (apellidoMaterno != null) {
            this.apellidoMaterno = apellidoMaterno;
        }

    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {

        if (correo != null) {
            this.correo = correo;
        }

    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {

        if (telefono != null) {
            this.telefono = telefono;
        }

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        if (password != null) {
            this.password = password;
        }

    }

    @Override
    public String toString() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }

}

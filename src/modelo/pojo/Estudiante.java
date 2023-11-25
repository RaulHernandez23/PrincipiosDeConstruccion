package modelo.pojo;

public class Estudiante {
    private int idEstudiante;
    private String matricula;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int idEstadoEstudiante;
    private String estado;
    private String password;

    public Estudiante() {
    }

    public Estudiante(int idEstudiante, String matricula, String nombre, 
            String apellidoPaterno, String apellidoMaterno, 
            int idEstadoEstudiante, String estado, String password) {

        this.idEstudiante = idEstudiante;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.idEstadoEstudiante = idEstadoEstudiante;
        this.estado = estado;
        this.password = password;

    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {

        if (idEstudiante > 0) {
            this.idEstudiante = idEstudiante;
        }

    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {

        if (matricula != null) {
            this.matricula = matricula;
        }

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

    public int getIdEstadoEstudiante() {
        return idEstadoEstudiante;
    }

    public void setIdEstadoEstudiante(int idEstadoEstudiante) {

        if (idEstadoEstudiante > 0) {
            this.idEstadoEstudiante = idEstadoEstudiante;
        }

    }

    public String getEstado() {
        return estado;
    }

    public void setEstadoEstudiante(String estado) {

        if (estado != null) {
            this.estado = estado;
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

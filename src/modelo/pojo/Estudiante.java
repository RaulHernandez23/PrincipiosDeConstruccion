package modelo.pojo;

public class Estudiante {
    private Integer idEstudiante;
    private String matricula;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer idEstadoEstudiante;
    private String estado;
    private String password;

    public Estudiante() {
    }

    public Estudiante(Integer idEstudiante, String matricula, String nombre, 
            String apellidoPaterno, String apellidoMaterno, 
            Integer idEstadoEstudiante, String estado, String password) {

        this.idEstudiante = idEstudiante;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.idEstadoEstudiante = idEstadoEstudiante;
        this.estado = estado;
        this.password = password;

    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getIdEstadoEstudiante() {
        return idEstadoEstudiante;
    }

    public void setIdEstadoEstudiante(Integer idEstadoEstudiante) {
        this.idEstadoEstudiante = idEstadoEstudiante;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstadoEstudiante(String estado) {
        this.estado = estado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }

}

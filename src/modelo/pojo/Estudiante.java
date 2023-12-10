package modelo.pojo;

public class Estudiante {
    private Integer idEstudiante;
    private String matricula;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer idEstadoEstudiante;
    private Integer idPeriodoEscolar;
    private String nombrePeriodoEscolar;
    private String estado;
    private String password;
    private Integer idProyecto;
    private String nombreProyecto;

    public Estudiante() {
    }

    public Estudiante(Integer idEstudiante, String matricula, String nombre, 
            String apellidoPaterno, String apellidoMaterno, 
            Integer idEstadoEstudiante, Integer idPeriodoEscolar,
            String nombrePeriodoEscolar, String estado, String password,
            Integer idProyecto, String nombreProyecto) {

        this.idEstudiante = idEstudiante;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.idEstadoEstudiante = idEstadoEstudiante;
        this.idPeriodoEscolar = idPeriodoEscolar;
        this.nombrePeriodoEscolar = nombrePeriodoEscolar;
        this.estado = estado;
        this.password = password;
        this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;

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

    public Integer getIdPeriodoEscolar() {
        return idPeriodoEscolar;
    }

    public void setIdPeriodoEscolar(Integer idPeriodoEscolar) {
        this.idPeriodoEscolar = idPeriodoEscolar;

    }

    public String getNombrePeriodoEscolar() {
        return nombrePeriodoEscolar;
    }

    public void setNombrePeriodoEscolar(String nombrePeriodoEscolar) {
        this.nombrePeriodoEscolar = nombrePeriodoEscolar;
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

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto= idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto= nombreProyecto;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }

}

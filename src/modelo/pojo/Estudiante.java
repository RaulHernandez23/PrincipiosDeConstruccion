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

    public Estudiante() {
    }

    public Estudiante(Integer idEstudiante, String matricula, String nombre, String apellidoPaterno,
            String apellidoMaterno, Integer idEstadoEstudiante, Integer idPeriodoEscolar, String nombrePeriodoEscolar,
            String estado, String password, Integer idProyecto) {
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
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {

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

    public Integer getIdEstadoEstudiante() {
        return idEstadoEstudiante;
    }

    public void setIdEstadoEstudiante(Integer idEstadoEstudiante) {

        if (idEstadoEstudiante > 0) {
            this.idEstadoEstudiante = idEstadoEstudiante;
        }

    }

    public Integer getIdPeriodoEscolar() {
        return idPeriodoEscolar;
    }

    public void setIdPeriodoEscolar(Integer idPeriodoEscolar) {

        if (idPeriodoEscolar > 0) {
            this.idPeriodoEscolar = idPeriodoEscolar;
        }

    }

    public String getNombrePeriodoEscolar() {
        return nombrePeriodoEscolar;
    }

    public void setNombrePeriodoEscolar(String nombrePeriodoEscolar) {

        if (nombrePeriodoEscolar != null) {
            this.nombrePeriodoEscolar = nombrePeriodoEscolar;
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

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {

        if (idProyecto > 0) {
            this.idProyecto = idProyecto;
        }

    }

    @Override
    public String toString() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }

}

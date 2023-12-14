package modelo.pojo;

public class Actividad {

    private Integer idActividad;
    private String titulo;
    private String descripcion;
    private Integer esfuerzoMinutos;
    private String fechaInicio;
    private String fechaFin;
    private Integer idEstadoActividad;
    private Integer idProyecto;
    private Integer idEstudiante;
    private Integer idResponsable;
    private Integer idTipo;
    private String estadoActividad;
    private String proyecto;
    private String estudiante;
    private String responsable;
    private String tipo;

    public Actividad() {
    }

    public Actividad(Integer idActividad, String titulo, String descripcion,
            Integer esfuerzoMinutos, String fechaInicio, String fechaFin,
            Integer idEstadoActividad, Integer idProyecto, Integer idEstudiante,
            Integer idResponsable, Integer idTipo, String estadoActividad,
            String proyecto, String estudiante, String responsable,
            String tipo) {

        this.idActividad = idActividad;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.esfuerzoMinutos = esfuerzoMinutos;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idEstadoActividad = idEstadoActividad;
        this.idProyecto = idProyecto;
        this.idEstudiante = idEstudiante;
        this.idResponsable = idResponsable;
        this.idTipo = idTipo;
        this.estadoActividad = estadoActividad;
        this.proyecto = proyecto;
        this.estudiante = estudiante;
        this.responsable = responsable;
        this.tipo = tipo;

    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEsfuerzoMinutos() {
        return esfuerzoMinutos;
    }

    public void setEsfuerzoMinutos(Integer esfuerzoMinutos) {
        this.esfuerzoMinutos = esfuerzoMinutos;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getIdEstadoActividad() {
        return idEstadoActividad;
    }

    public void setIdEstadoActividad(Integer idEstadoActividad) {
        this.idEstadoActividad = idEstadoActividad;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Integer getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Integer idResponsable) {
        this.idResponsable = idResponsable;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getEstadoActividad() {
        return estadoActividad;
    }

    public void setEstadoActividad(String estadoActividad) {
        this.estadoActividad = estadoActividad;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return titulo;
    }

}

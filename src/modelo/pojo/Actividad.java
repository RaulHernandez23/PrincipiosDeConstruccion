package modelo.pojo;

public class Actividad {
    private int idActividad;
    private String titulo;
    private String descripcion;
    private int esfuerzoMinutos;
    private String fechaInicio;
    private String fechaFin;
    private int idEstadoActividad;
    private int idProyecto;
    private int idEstudiante;
    private int idResponsable;
    private int idTipo;
    private String estadoActividad;
    private String proyecto;
    private String estudiante;
    private String responsable;
    private String tipo;

    public Actividad() {
    }

    public Actividad(int idActividad, String titulo, String descripcion,
            int esfuerzoMinutos, String fechaInicio, String fechaFin,
            int idEstadoActividad, int idProyecto, int idEstudiante,
            int idResponsable, int idTipo, String estadoActividad,
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

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {

        if (idActividad > 0) {
            this.idActividad = idActividad;
        }

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {

        if (titulo != null) {
            this.titulo = titulo;
        }

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {

        if (descripcion != null) {
            this.descripcion = descripcion;
        }

    }

    public int getEsfuerzoMinutos() {
        return esfuerzoMinutos;
    }

    public void setEsfuerzoMinutos(int esfuerzoMinutos) {

        if (esfuerzoMinutos > 0) {
            this.esfuerzoMinutos = esfuerzoMinutos;
        }

    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {

        if (fechaInicio != null) {
            this.fechaInicio = fechaInicio;
        }

    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {

        if (fechaFin != null) {
            this.fechaFin = fechaFin;
        }

    }

    public int getIdEstadoActividad() {
        return idEstadoActividad;
    }

    public void setIdEstadoActividad(int idEstadoActividad) {

        if (idEstadoActividad > 0) {
            this.idEstadoActividad = idEstadoActividad;
        }

    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {

        if (idProyecto > 0) {
            this.idProyecto = idProyecto;
        }

    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {

        if (proyecto != null) {
            this.proyecto = proyecto;
        }

    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {

        if (idEstudiante > 0) {
            this.idEstudiante = idEstudiante;
        }

    }

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {

        if (idResponsable > 0) {
            this.idResponsable = idResponsable;
        }

    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {

        if (idTipo > 0) {
            this.idTipo = idTipo;
        }

    }

    public String getEstadoActividad() {
        return estadoActividad;
    }

    public void setEstadoActividad(String estadoActividad) {

        if (estadoActividad != null) {
            this.estadoActividad = estadoActividad;
        }

    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {

        if (estudiante != null) {
            this.estudiante = estudiante;
        }

    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {

        if (responsable != null) {
            this.responsable = responsable;
        }

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {

        if (tipo != null) {
            this.tipo = tipo;
        }

    }

    @Override
    public String toString() {
        return titulo;
    }

}

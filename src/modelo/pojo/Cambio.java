package modelo.pojo;

public class Cambio {
    private int idCambio;
    private String titulo;
    private String Descripcion;
    private int esfuerzoMinutos;
    private String fechaInicio;
    private String fechaFin;
    private int idEstadoCambio;
    private int idSolicitud;
    private int idTipoActividad;
    private Integer idEstudiante;
    private Integer idProyecto;
    private String estadoCambio;
    private String solicitud;
    private String tipoActividad;

    public Cambio() {
    }

    public Cambio(int idCambio, String titulo, String Descripcion, int esfuerzoMinutos, String fechaInicio,
            String fechaFin, int idEstadoCambio, int idSolicitud, int idTipoActividad, Integer idEstudiante,
            Integer idProyecto, String estadoCambio, String solicitud, String tipoActividad) {
        this.idCambio = idCambio;
        this.titulo = titulo;
        this.Descripcion = Descripcion;
        this.esfuerzoMinutos = esfuerzoMinutos;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idEstadoCambio = idEstadoCambio;
        this.idSolicitud = idSolicitud;
        this.idTipoActividad = idTipoActividad;
        this.idEstudiante = idEstudiante;
        this.idProyecto = idProyecto;
        this.estadoCambio = estadoCambio;
        this.solicitud = solicitud;
        this.tipoActividad = tipoActividad;
    }

    public int getIdCambio() {
        return idCambio;
    }

    public void setIdCambio(int idCambio) {

        if (idCambio > 0) {
            this.idCambio = idCambio;
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
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {

        if (Descripcion != null) {
            this.Descripcion = Descripcion;
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

    public int getIdEstadoCambio() {
        return idEstadoCambio;
    }

    public void setIdEstadoCambio(int idEstadoCambio) {

        if (idEstadoCambio > 0) {
            this.idEstadoCambio = idEstadoCambio;
        }

    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {

        if (idSolicitud > 0) {
            this.idSolicitud = idSolicitud;
        }

    }

    public int getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(int idTipoActividad) {

        if (idTipoActividad > 0) {
            this.idTipoActividad = idTipoActividad;
        }

    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {

        if (idEstudiante > 0) {
            this.idEstudiante = idEstudiante;
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

    public String getEstadoCambio() {
        return estadoCambio;
    }

    public void setEstadoCambio(String estadoCambio) {

        if (estadoCambio != null) {
            this.estadoCambio = estadoCambio;
        }

    }

    public String getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(String solicitud) {

        if (solicitud != null) {
            this.solicitud = solicitud;
        }

    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {

        if (tipoActividad != null) {
            this.tipoActividad = tipoActividad;
        }

    }

    @Override
    public String toString() {
        return titulo;
    }
}

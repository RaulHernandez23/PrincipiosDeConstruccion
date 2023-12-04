
package modelo.pojo;

public class SolicitudDeCambio {
    private Integer idSolicitudDeCambio;
    private String titulo;
    private String descripcion;
    private String razon;
    private String impacto;
    private String accionPropuesta;
    private String fechaCreacion;
    private String fechaEvaluacion;
    private Integer idEstudiante;
    private Integer idEstadoSolicitud;
    private Integer idProyecto;
    private Integer idResponsable;
    private Integer idDefecto;

    public SolicitudDeCambio() {

    }

    public SolicitudDeCambio(Integer idSolicitudDeCambio, String titulo, String descripcion, String razon,
            String impacto, String accionPropuesta, String fechaCreacion, String fechaEvaluacion,
            Integer idEstudiante, Integer idEstadoSolicitud, Integer idProyecto, Integer idResponsable,
            Integer idDefecto) {
        this.idSolicitudDeCambio = idSolicitudDeCambio;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.razon = razon;
        this.impacto = impacto;
        this.accionPropuesta = accionPropuesta;
        this.fechaCreacion = fechaCreacion;
        this.fechaEvaluacion = fechaEvaluacion;
        this.idEstudiante = idEstudiante;
        this.idEstadoSolicitud = idEstadoSolicitud;
        this.idProyecto = idProyecto;
        this.idResponsable = idResponsable;
        this.idDefecto = idDefecto;
    }

    public Integer getIdSolicitudDeCambio() {
        return idSolicitudDeCambio;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getRazon() {
        return razon;
    }

    public String getImpacto() {
        return impacto;
    }

    public String getAccionPropuesta() {
        return accionPropuesta;
    }

    public Integer getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public Integer getIdResponsable() {
        return idResponsable;
    }

    public Integer getIdDefecto() {
        return idDefecto;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public String getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setIdSolicitudDeCambio(Integer idSolicitudDeCambio) {
        this.idSolicitudDeCambio = idSolicitudDeCambio;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public void setImpacto(String impacto) {
        this.impacto = impacto;
    }

    public void setAccionPropuesta(String accionPropuesta) {
        this.accionPropuesta = accionPropuesta;
    }

    public void setIdEstadoSolicitud(Integer idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public void setIdResponsable(Integer idResponsable) {
        this.idResponsable = idResponsable;
    }

    public void setIdDefecto(Integer idDefecto) {
        this.idDefecto = idDefecto;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaEvaluacion(String fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    @Override
    public String toString() {
        return titulo + " id: " + idSolicitudDeCambio;
    }

}


package modelo.pojo;

import java.util.Date;

public class SolicitudDeCambio {
    private Integer idSolicitudDeCambio;
    private String nombre;
    private String descripcion;
    private String razon;
    private String impacto;
    private String propuesta;
    private Integer idEstadoSolicitud;
    private Integer idEstudiante;
    private Date fechaCreacion;

    public SolicitudDeCambio() {
        
    }

    public SolicitudDeCambio(Integer idSolicitudDeCambio, String nombre, String descripcion, String razon, String impacto, String propuesta, Integer idEstadoSolicitud, Integer idEstudiante, Date fechaCreacion) {
        this.idSolicitudDeCambio = idSolicitudDeCambio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.razon = razon;
        this.impacto = impacto;
        this.propuesta = propuesta;
        this.idEstadoSolicitud = idEstadoSolicitud;
        this.idEstudiante = idEstudiante;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdSolicitudDeCambio() {
        return idSolicitudDeCambio;
    }

    public String getNombre() {
        return nombre;
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

    public String getPropuesta() {
        return propuesta;
    }

    public Integer getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setIdSolicitudDeCambio(Integer idSolicitudDeCambio) {
        this.idSolicitudDeCambio = idSolicitudDeCambio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public void setPropuesta(String propuesta) {
        this.propuesta = propuesta;
    }

    public void setIdEstadoSolicitud(Integer idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
}

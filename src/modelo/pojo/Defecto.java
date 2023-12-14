/*
 * Nombre del archivo: Defecto.java
 * Nombre del autor: César González López
 * Paquete: modelo.pojo
 * Fecha de creacion: 26/11/2023
 * Fecha de modificacion: 08/12/2023
 * Descripcion: Clase Defecto que contiene los atributos de un defecto
 */

package modelo.pojo;

public class Defecto {
    
    private Integer idDefecto;
    private String titulo;
    private String descripcion;
    private Integer esfuerzoMinutos = 0;
    private String fechaReporte;
    private String fechaFin;
    private Integer idEstadoDefecto;
    private Integer idEstudiante;
    private Integer idProyecto;

    private String estadoDefecto;
    private String nombreEstudiante;

    public Defecto() {
    }

    public Defecto(Integer idDefecto, String titulo, String descripcion, Integer esfuerzoMinutos, String fechaReporte,
            Integer idEstadoDefecto, Integer idEstudiante, String estadoDefecto, String nombreEstudiante, Integer idProyecto) {
        
        this.idDefecto = idDefecto;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.esfuerzoMinutos = esfuerzoMinutos;
        this.fechaReporte = fechaReporte;
        this.idEstadoDefecto = idEstadoDefecto;
        this.idEstudiante = idEstudiante;
        this.estadoDefecto = estadoDefecto;
        this.nombreEstudiante = nombreEstudiante;
        this.idProyecto = idProyecto;

    }

    public Integer getIdDefecto() {
        return idDefecto;
    }

    public void setIdDefecto(Integer idDefecto) {
        this.idDefecto = idDefecto;
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

    public Integer getEsfuerzoMinutos() {
        return esfuerzoMinutos;
    }

    public void setEsfuerzoMinutos(Integer esfuerzoMinutos) {

        if (esfuerzoMinutos > 0) {
            this.esfuerzoMinutos = esfuerzoMinutos;
        }

    }

    public String getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(String fechaReporte) {

        if (fechaReporte != null) {
            this.fechaReporte = fechaReporte;
        }

    }

    public Integer getIdEstadoDefecto() {
        return idEstadoDefecto;
    }

    public void setIdEstadoDefecto(Integer idEstadoActividad) {

        if (idEstadoActividad > 0) {
            this.idEstadoDefecto = idEstadoActividad;
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

    public String getEstadoDefecto() {
        return estadoDefecto;
    }

    public void setEstadoDefecto(String estadoDefecto) {

        if (estadoDefecto != null) {
            this.estadoDefecto = estadoDefecto;
        }

    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {

        if (nombreEstudiante != null) {
            this.nombreEstudiante = nombreEstudiante;
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

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
    
        if (fechaFin != null) {
            this.fechaFin = fechaFin;            
        }

    }

    @Override
    public String toString() {
        return titulo;
    }

}

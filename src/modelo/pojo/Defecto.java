/*
 * Nombre del archivo: Defecto.java
 * Nombre del autor:
 * Paquete: modelo.pojo
 * Fecha de creacion:
 * Fecha de modificacion: 29/11/2023
 * Descripcion: Clase Defecto que contiene los atributos de un defecto
 */

package modelo.pojo;

public class Defecto {
    private int idDefecto;
    private String titulo;
    private String descripcion;
    private Integer esfuerzoMinutos;
    private String fechaReporte;
    private int idEstadoDefecto;
    private int idEstudiante;
    private String estadoDefecto;
    private String nombreEstudiante;
    private Integer idProyecto;

    public Defecto() {
    }

    public Defecto(int idDefecto, String titulo, String descripcion, Integer esfuerzoMinutos, String fechaReporte,
            int idEstadoDefecto, int idEstudiante, String estadoDefecto, String nombreEstudiante, Integer idProyecto) {
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

    public int getIdDefecto() {
        return idDefecto;
    }

    public void setIdDefecto(int idDefecto) {

        if (idDefecto > 0) {
            this.idDefecto = idDefecto;
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

    public Integer getEsfuerzoMinutos() {
        return esfuerzoMinutos;
    }

    public void setEsfuerzoMinutos(Integer esfuerzoMinutos) {

        if (esfuerzoMinutos != null) {
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

    public int getIdEstadoDefecto() {
        return idEstadoDefecto;
    }

    public void setIdEstadoDefecto(int idEstadoActividad) {

        if (idEstadoActividad > 0) {
            this.idEstadoDefecto = idEstadoActividad;
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

    @Override
    public String toString() {
        return titulo;
    }

}

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
    private String esfuerzoMinutos;
    private String fechaReporte;
    private int idEstadoDefecto;
    private int idEstudiante;
    private String estadoDefecto;
    private String nombreEstudiante;

    public Defecto() {
    }

    public Defecto(int idDefecto, String titulo, String descripcion, String esfuerzoMinutos, String fechaReporte,
            int idEstadoDefecto, int idEstudiante, String estadoDefecto, String nombreEstudiante) {
        this.idDefecto = idDefecto;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.esfuerzoMinutos = esfuerzoMinutos;
        this.fechaReporte = fechaReporte;
        this.idEstadoDefecto = idEstadoDefecto;
        this.idEstudiante = idEstudiante;
        this.estadoDefecto = estadoDefecto;
        this.nombreEstudiante = nombreEstudiante;
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

    public String getEsfuerzoMinutos() {
        return esfuerzoMinutos;
    }

    public void setEsfuerzoMinutos(String esfuerzoMinutos) {
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

    public int getIdestadoDefecto() {
        return idEstadoDefecto;
    }

    public void setIdestadoDefecto(int idEstadoActividad) {
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

    @Override
    public String toString() {
        return "Defecto{" + "idDefecto=" + idDefecto + ", titulo=" + titulo + ", descripcion=" + descripcion
                + ", esfuerzoMinutos=" + esfuerzoMinutos + ", fechaReporte=" + fechaReporte + ", idEstadoDefecto="
                + idEstadoDefecto + ", idEstudiante=" + idEstudiante + ", estadoDefecto=" + estadoDefecto
                + ", nombreEstudiante=" + nombreEstudiante + '}';
    }

}

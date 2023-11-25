package modelo.pojo;

import java.util.Date;

public class Actividad {
    private int idActividad;
    private String titulo;
    private String descripcion;
    private int esfuerzoMinutos;
    private Date fechaInicio;
    private Date fechaFin;
    private int idEstadoActividad;
    private int idEstudiante;
    private int idResponsable;
    private String estadoActividad;
    private String Estudiante;
    private String Responsable;

    public Actividad() {
    }

    public Actividad(int idActividad, String titulo, String descripcion, 
            int esfuerzoMinutos, Date fechaInicio, Date fechaFin, 
            int idEstadoActividad, int idEstudiante, int idResponsable) {

        this.idActividad = idActividad;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.esfuerzoMinutos = esfuerzoMinutos;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idEstadoActividad = idEstadoActividad;
        this.idEstudiante = idEstudiante;
        this.idResponsable = idResponsable;

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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {

        if (fechaInicio != null) {
            this.fechaInicio = fechaInicio;
        }

    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {

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

    public String getEstadoActividad() {
        return estadoActividad;
    }

    public void setEstadoActividad(String estadoActividad) {

        if (estadoActividad != null) {
            this.estadoActividad = estadoActividad;
        }

    }

    public String getEstudiante() {
        return Estudiante;
    }

    public void setEstudiante(String Estudiante) {

        if (Estudiante != null) {
            this.Estudiante = Estudiante;
        }

    }

    public String getResponsable() {
        return Responsable;
    }

    public void setResponsable(String Responsable) {

        if (Responsable != null) {
            this.Responsable = Responsable;
        }

    }

    @Override
    public String toString() {
        return "Actividad{" + "idActividad=" + idActividad + ", titulo=" + 
            titulo + ", descripcion=" + descripcion + ", esfuerzoMinutos=" + 
            esfuerzoMinutos + ", fechaInicio=" + fechaInicio + ", fechaFin=" + 
            fechaFin + ", idEstadoActividad=" + idEstadoActividad +
            ", idEstudiante=" + idEstudiante + ", idResponsable=" + 
            idResponsable + ", estadoActividad=" + estadoActividad + 
            ", Estudiante=" + Estudiante + ", Responsable=" + Responsable + '}';
    }

}

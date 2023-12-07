package modelo.pojo;

public class Bitacora {

    private String titulo;
    private String estado;
    private Integer esfuerzo;
    private String tipo;
    private String fechaInicio;
    private String fechaReporte;
    private Integer numeroSolicitud;
    private String fechaSolicitud;
    private Integer idBitacora;
    private Integer idProyecto;

    public Bitacora() {
    }

    public Bitacora(String titulo, String estado, Integer esfuerzo, String tipo, String fechaInicio,
            String fechaReporte, Integer numeroSolicitud, String fechaSolicitud, Integer idBitacora,
            Integer idProyecto) {
        this.titulo = titulo;
        this.estado = estado;
        this.esfuerzo = esfuerzo;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaReporte = fechaReporte;
        this.numeroSolicitud = numeroSolicitud;
        this.fechaSolicitud = fechaSolicitud;
        this.idBitacora = idBitacora;
        this.idProyecto = idProyecto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {

        if (titulo != null) {
            this.titulo = titulo;
        }

    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {

        if (estado != null) {
            this.estado = estado;
        }

    }

    public Integer getEsfuerzo() {
        return esfuerzo;
    }

    public void setEsfuerzo(Integer esfuerzo) {

        if (esfuerzo != null) {
            this.esfuerzo = esfuerzo;
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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {

        if (fechaInicio != null) {
            this.fechaInicio = fechaInicio;
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

    public Integer getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(Integer numeroSolicitud) {

        if (numeroSolicitud != null) {
            this.numeroSolicitud = numeroSolicitud;
        }

    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {

        if (fechaSolicitud != null) {
            this.fechaSolicitud = fechaSolicitud;
        }

    }

    public Integer getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(Integer idBitacora) {

        if (idBitacora > 0) {
            this.idBitacora = idBitacora;
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

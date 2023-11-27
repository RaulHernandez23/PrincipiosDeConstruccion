package modelo.pojo;

public class Defecto {
    private int idDefecto;
    private String titulo;
    private String descripcion;
    private String esfuerzoMinutos;
    private String fechaReporte;
    private int idEstadoActividad;
    private int idEstudiante;
    private String estadoActividad;
    private String nombreEstudiante;

    public Defecto() {
    }

    public Defecto(int idDefecto, String titulo, String descripcion, String esfuerzoMinutos, String fechaReporte,
            int idEstadoActividad, int idEstudiante, String estadoActividad, String nombreEstudiante) {
        this.idDefecto = idDefecto;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.esfuerzoMinutos = esfuerzoMinutos;
        this.fechaReporte = fechaReporte;
        this.idEstadoActividad = idEstadoActividad;
        this.idEstudiante = idEstudiante;
        this.estadoActividad = estadoActividad;
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

    public String getEstadoActividad() {
        return estadoActividad;
    }

    public void setEstadoActividad(String estadoActividad) {
        if (estadoActividad != null) {
            this.estadoActividad = estadoActividad;
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
                + ", esfuerzoMinutos=" + esfuerzoMinutos + ", fechaReporte=" + fechaReporte + ", idEstadoActividad="
                + idEstadoActividad + ", idEstudiante=" + idEstudiante + ", estadoActividad=" + estadoActividad
                + ", nombreEstudiante=" + nombreEstudiante + '}';
    }

}

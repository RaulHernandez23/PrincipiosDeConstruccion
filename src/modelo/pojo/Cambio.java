package modelo.pojo;

public class Cambio {
    private int idCambio;
    private String Nombre;
    private String Descripcion;
    private int idEstadoActividad;
    private int idSolicitud;
    private String estadoActividad;
    private String Solicitud;

    public Cambio() {
    }

    public Cambio(int idCambio, String Nombre, String Descripcion, 
            int idEstadoActividad, int idSolicitud) {

        this.idCambio = idCambio;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.idEstadoActividad = idEstadoActividad;
        this.idSolicitud = idSolicitud;

    }

    public int getIdCambio() {
        return idCambio;
    }

    public void setIdCambio(int idCambio) {

        if (idCambio > 0) {
            this.idCambio = idCambio;
        }

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {

        if (Nombre != null) {
            this.Nombre = Nombre;
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

    public int getIdEstadoActividad() {
        return idEstadoActividad;
    }

    public void setIdEstadoActividad(int idEstadoActividad) {

        if (idEstadoActividad > 0) {
            this.idEstadoActividad = idEstadoActividad;
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

    public String getEstadoActividad() {
        return estadoActividad;
    }

    public void setEstadoActividad(String estadoActividad) {

        if (estadoActividad != null) {
            this.estadoActividad = estadoActividad;
        }

    }

    public String getSolicitud() {
        return Solicitud;
    }

    public void setSolicitud(String Solicitud) {

        if (Solicitud != null) {
            this.Solicitud = Solicitud;
        }

    }

    @Override
    public String toString() {
        return  Nombre ;
    }
}

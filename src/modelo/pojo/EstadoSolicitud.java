
package modelo.pojo;

public class EstadoSolicitud {
    
    private int idEstadoSolicitud;
    private String estado;

    public EstadoSolicitud() {
    }

    public EstadoSolicitud(int idEstadoSolicitud, String estado) {

        this.idEstadoSolicitud = idEstadoSolicitud;
        this.estado = estado;
        
    }

    public int getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setIdEstadoSolicitud(int idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}

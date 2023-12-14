/*
 * Nombre del archivo: EstadoSolicitudDeCambio.java
 * Autor: Miguel Angel Morales Cruz
 * Paquete: modelo.pojo
 * Fecha de creación: 20/11/2023
 * Fecha de modificación: 10/12/2023
 * Descripción: Clase que contiene todos los datos para modelar 
 * los estados de la solicitud de cambio.
 */

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

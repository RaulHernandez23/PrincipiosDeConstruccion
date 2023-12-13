/*
 * Nombre del archivo: ObservadorSolciitudesDeCambio.java
 * Autor: Miguel Angel Morales Cruz
 * Paquete: interfaces
 * Fecha de creación: 19/11/2023
 * Fecha de modificación: 23/12/2023
 * Descripción: Interfaz para la actualizar la tabla de solicitudes de cambio.
 */
package interfaces;

public interface ObservadorSolicitudesDeCambio {
    public void operacionExitosa(String tipoOperacion, String nombre);
}

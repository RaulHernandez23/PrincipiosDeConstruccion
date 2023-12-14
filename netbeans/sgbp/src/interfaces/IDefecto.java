/*
 * Nombre del archivo: IDefecto.java
 * Autor: Cesar González López
 * Paquete: interfaces
 * Fecha de creación: 23/11/2023
 * Fecha de modificación: 09/12/2023
 * Descripción: Interfaz que sirve de observador en el 
 * patrón de diseño observador para la clase FXMLConsultarDefectosController
 */

package interfaces;

import modelo.pojo.Defecto;

public interface IDefecto {
    void recibirDefectoSeleccionado(Defecto defecto);
}

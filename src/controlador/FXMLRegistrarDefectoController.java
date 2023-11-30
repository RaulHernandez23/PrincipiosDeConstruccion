/*
 * Nombre del archivo: FXMLRegistrarDefectoController.java
 * Autor: Raul Hernandez
 * Paquete: src.controlador
 * Fecha de creacion: 29/11/2023
 * Fecha de modificacion: 29/11/2023
 * Descripción: archivo que contiene la logica de la interfaz
 * FXMLRegistrarDefecto
 */

package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class FXMLRegistrarDefectoController {
    @FXML
    private TextField tfTitulo;
    
    @FXML
    private TextArea taDescripcion;
    
    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnVolver;

    @FXML
    private void btnRegistrar() {
        // Lógica para registrar
    }

    @FXML
    private void btnVolver() {
        // Lógica para volver
    }

    @FXML
    private void btnSalir() {
        // Lógica para salir
    }

    @FXML
    private void hoverInSalir() {
        // Lógica para cuando el cursor entra en el botón de salir
    }

    @FXML
    private void hoverOutSalir() {
        // Lógica para cuando el cursor sale del botón de salir
    }
}
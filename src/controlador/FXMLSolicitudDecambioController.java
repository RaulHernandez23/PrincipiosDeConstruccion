/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author MIGUEL ANGEL
 */
public class FXMLSolicitudDecambioController implements Initializable {

    @FXML
    private Label lblNombreSolcitud;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblNumeroSolicitud;
    @FXML
    private Label lblNombreEstudiante;
    @FXML
    private Label lblDescripcionCambio;
    @FXML
    private Label lblRazonDeCambio;
    @FXML
    private Label lblImpactoCambio;
    @FXML
    private Label lblAccionPropuesta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAprobarSolicitud(ActionEvent event) {
    }

    @FXML
    private void btnRechazarSolicitud(ActionEvent event) {
    }

    @FXML
    private void btnVolver(ActionEvent event) {
    }
    
}

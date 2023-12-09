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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author MIGUEL ANGEL
 */
public class FXMLSolicitudDeCambioController implements Initializable {

    @FXML
    private VBox vboxScrollPane;
    @FXML
    private ImageView ivSalir;
    @FXML
    private Label lbNombreSolicitud;
    @FXML
    private Label lbFecha;
    @FXML
    private Label lbNumSolicitud;
    @FXML
    private Label lbSolicitadoPor;
    @FXML
    private Label lbDescripcion;
    @FXML
    private Label lbRazon;
    @FXML
    private Label lbImpacto;
    @FXML
    private Label lbAccionPropuesta;
    @FXML
    private Label lbDefectoAsociado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void hoverOutSalir(MouseEvent event) {
    }

    @FXML
    private void hoverInSalir(MouseEvent event) {
    }

    @FXML
    private void btnSalir(MouseEvent event) {
    }

    @FXML
    private void btnVolver(ActionEvent event) {
    }

    @FXML
    private void btnAprobarSolicitud(ActionEvent event) {
    }

    @FXML
    private void btnRechazarSolicitud(ActionEvent event) {
    }

}

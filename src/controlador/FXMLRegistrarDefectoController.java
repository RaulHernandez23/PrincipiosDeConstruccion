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

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utilidades.Utilidades;

public class FXMLRegistrarDefectoController implements Initializable {

    @FXML
    private VBox vboxEjemplo;

    @FXML
    private TextField tfTitulo;

    @FXML
    private ImageView ivSalir;

    @FXML
    private TextArea taDescripcion;

    @FXML
    void btnRegistrar(ActionEvent event) {

    }

    @FXML
    void btnVolver(ActionEvent event) {
        salir();
    }

    @FXML
    void btnSalir(MouseEvent event) {
        salir();
    }

    @FXML
    private void hoverOutSalir(MouseEvent event) {
        ivSalir.setImage(new Image(Utilidades.getInputStream("/recursos/imagenes/logoSalir.png")));
    }

    @FXML
    private void hoverInSalir(MouseEvent event) {
        ivSalir.setImage(new Image(Utilidades.getInputStream("/recursos/imagenes/logoSalir2.png")));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void salir() {

        Stage escenario = (Stage) vboxEjemplo.getScene().getWindow();

        escenario.close();

    }
}
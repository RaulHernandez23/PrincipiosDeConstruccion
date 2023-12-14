/*
 * Nombre del archivo: FXMLConsultarDetallesDefectosController.java
 * Autor: Cesar Gonzalez Lopez
 * Paquete: controlador
 * Fecha de creación: 25/11/2023
 * Fecha de modificación: 14/12/2023
 * Descripción: Muestra detalladamente la información de un defecto
 */

package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.pojo.Defecto;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLConsultarDetallesDefectosController implements Initializable {

    private Defecto defecto;

    @FXML
    private TextField tfTitulo;

    @FXML
    private TextArea tbDescripcion;

    @FXML
    private TextField tfFechaInicio;

    @FXML
    private TextField tfFechaFin;

    @FXML
    private TextField tfEstudiante;

    @FXML
    private TextField tfEsfuerzo;
    @FXML
    private TextField tfEstado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void inicializarVentana(Defecto defectoSeleccionado) {

        this.defecto = defectoSeleccionado;
        tfTitulo.setText(defecto.getTitulo());
        tfTitulo.setText(defectoSeleccionado.getTitulo());
        tbDescripcion.setText(defectoSeleccionado.getDescripcion());
        tfFechaInicio.setText(defectoSeleccionado.getFechaReporte());
        tfFechaFin.setText(defectoSeleccionado.getFechaFin());
        tfEstudiante.setText(defectoSeleccionado.getNombreEstudiante());
        tfEsfuerzo.setText(String.valueOf(defectoSeleccionado
                .getEsfuerzoMinutos()));
        tfEstado.setText(defectoSeleccionado.getEstadoDefecto());

    }

    @FXML
    private void btnCerrarClic(ActionEvent event) {
        salir();
    }

    private void salir() {

        Stage escenario = (Stage) tfTitulo.getScene().getWindow();

        escenario.close();

    }
}

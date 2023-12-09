/*
* Autor: Cesar Gonzalez Lopez
* Fecha de creación: 24/11/2023
* Fecha de modificación: 25/11/2023
* Descripción: clase para poder finalizar los cambios
*/
package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.dao.CambioDAO;
import modelo.pojo.Cambio;
import utilidades.Alertas;
import utilidades.Utilidades;

public class FXMLFinalizarCambioController implements Initializable{

    @FXML
    private ImageView ivSalir;

    @FXML
    private TableView<Cambio> tvCambios;

    @FXML
    private TableColumn<Cambio, String> colTitulo;

    @FXML
    private TableColumn<Cambio, String> colDescripcion;

    @FXML
    private TableColumn<Cambio, String> colFechaInicio;

    @FXML
    private TableColumn<Cambio, String> colTipoActividad;

    @FXML
    private Button btnFinalizar;

    @FXML
    private Button btnVolver;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostrarDatos();
    }

    private void mostrarDatos() {
        int idProyecto = 123; // Reemplaza con el ID correcto de tu proyecto
        HashMap<String, Object> respuesta = CambioDAO.consultarCambios();

        if (!(Boolean) respuesta.get("error")) {
            ArrayList<Cambio> listaCambios = (ArrayList<Cambio>) respuesta.get("cambios");

            ObservableList<Cambio> observableListaCambios = FXCollections.observableArrayList();
            observableListaCambios.addAll(listaCambios);

            colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            colDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
            colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
            colTipoActividad.setCellValueFactory(new PropertyValueFactory<>("tipoActividad"));

            tvCambios.setItems(observableListaCambios);
        } else {
            String mensajeError = respuesta.get("mensaje").toString();
            Alertas.mostrarAlerta("Error", mensajeError, AlertType.ERROR);

            // Cerrar la ventana actual en caso de error
            Stage escenario = (Stage) tvCambios.getScene().getWindow();
            escenario.close();
        }
    }

    @FXML
    private void btnFinalizarClic(ActionEvent event) {
        // Código para manejar el evento
    }

    @FXML
    private void btnVolverClic(ActionEvent event) {
        // Código para manejar el evento
    }

    @FXML
    private void btnSalir(MouseEvent event) {
        // Código para manejar el evento
        salir(); // Puedes llamar a tu método salir() u otro código que necesites
    }


    @FXML
    private void hoverInSalir() {
        ivSalir.setImage(new Image(Utilidades.getInputStream(
                "/recursos/imagenes/logoSalir2.png")));
    }

    @FXML
    private void hoverOutSalir() {
        ivSalir.setImage(new Image(Utilidades.getInputStream(
                "/recursos/imagenes/logoSalir.png")));
    }

    private void salir() {
        Stage escenario = (Stage) tvCambios.getScene().getWindow();

        escenario.close();
    }

}
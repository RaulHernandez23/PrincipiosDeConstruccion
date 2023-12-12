package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.dao.ActividadDAO;
import modelo.pojo.Actividad;
import utilidades.Utilidades;
import javafx.scene.control.Alert;

public class FXMLFinalizarActividadController implements Initializable {

    private ObservableList<Actividad> actividades;
    @FXML
    private TableView<Actividad> tvActividades;

    @FXML
    private TableColumn colTitulo;

    @FXML
    private TableColumn colFechaInicio;

    @FXML
    private TableColumn colEstado;

    @FXML
    private TableColumn colEsfuerzo;

    @FXML
    private Button btnFinalizar;

    @FXML
    private Button btnVolver;

    @FXML
    private ImageView ivSalir;

    @FXML
    private void btnFinalizarClic(ActionEvent event) {
        // TODO: Implement the logic for the "Finalizar" button
    }

    @FXML
    private void btnVolver() {
        salir();
    }

    @FXML
    private void btnSalir() {
        salir();
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
        Stage escenario = (Stage) tvActividades.getScene().getWindow();

        escenario.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        configurarTabla();

    }

    public void inicializarInformacion(int idProyecto) {

        obtenerActividadesSinFinalizar(idProyecto);

    }

    private void configurarTabla() {

        colTitulo.setCellValueFactory(new PropertyValueFactory(
                "titulo"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory(
                "fechaInicio"));
        colEstado.setCellValueFactory(new PropertyValueFactory(
                "estadoActividad"));
        colEsfuerzo.setCellValueFactory(new PropertyValueFactory(
                "esfuerzoMinutos"));

        tvActividades.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Actividad>() {

            @Override
            public void changed(
                    ObservableValue<? extends Actividad> observable,
                    Actividad oldValue,
                    Actividad newValue) {

                if (newValue != null) {
                    btnFinalizar.setDisable(false);
                }
            }

        });

    }

    private void obtenerActividadesSinFinalizar(int idProyecto) {

        HashMap<String, Object> respuesta = ActividadDAO
                .obtenerActividadesSinFinalizar(idProyecto);

        if (!(boolean) respuesta.get("error")) {

            actividades = FXCollections.observableArrayList();
            ArrayList<Actividad> lista = (ArrayList) respuesta
                    .get("actividades");
            actividades.addAll(lista);
            tvActividades.setItems(actividades);

        } else {
            Utilidades.mostrarAlertaSimple("Error",
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.ERROR);
        }

    }
}

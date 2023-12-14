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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import modelo.dao.EstudianteDAO;
import modelo.pojo.Estudiante;
import utilidades.Alertas;
import utilidades.Utilidades;

public class FXMLDesasignarEstudianteController implements Initializable {

    @FXML
    private VBox vboxDesasignarEstudiante;

    @FXML
    private ImageView ivSalir;

    @FXML
    private ComboBox<Estudiante> cbEstudiantes;

    @FXML
    private Label lbNombre;

    @FXML
    private Label lbApellidos;

    @FXML
    private Label lbMatricula;

    @FXML
    private Label lbSemestre;

    private Integer idProyecto;

    ObservableList<Estudiante> listaEstudiantes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void hoverOutSalir(MouseEvent event) {
        ivSalir.setImage(new Image(Utilidades.getInputStream(
                "/recursos/imagenes/logoSalir.png")));
    }

    @FXML
    private void hoverInSalir(MouseEvent event) {
        ivSalir.setImage(new Image(Utilidades.getInputStream(
                "/recursos/imagenes/logoSalir2.png")));
    }

    @FXML
    private void btnSalir(MouseEvent event) {
        Stage escenario = (Stage) vboxDesasignarEstudiante.getScene()
                .getWindow();
        escenario.close();
    }

    @FXML
    private void btnRegistrar(ActionEvent event) {
        boolean respuesta = Alertas.mostrarAlertaConfirmacion(
                null,
                "¿Está seguro de desasignar al estudiante?");

        if (respuesta) {

            desasignarEstudiante();
            Stage escenario = (Stage) vboxDesasignarEstudiante.getScene()
                    .getWindow();
            escenario.close();

        }
    }

    public void inicializarInformacion(int idProyecto) {

        this.idProyecto = idProyecto;

        cargarEstudiantes();

        ChangeListener<Estudiante> listener = (
                ObservableValue<? extends Estudiante> observable,
                Estudiante oldValue, Estudiante newValue) -> {

            if (newValue != null) {

                lbNombre.setText(newValue.getNombre());
                lbApellidos.setText(newValue.getApellidoPaterno() + " "
                        + newValue.getApellidoMaterno());
                lbMatricula.setText(newValue.getMatricula());
                lbSemestre.setText(newValue.getNombrePeriodoEscolar());

            }

        };

        cbEstudiantes.getSelectionModel().selectedItemProperty().addListener(
                listener);
        cbEstudiantes.getSelectionModel().selectFirst();

    }

    private void cargarEstudiantes() {

        HashMap<String, Object> respuesta = EstudianteDAO
                .recuperarEstudiantesActivosProyecto(
                        idProyecto);
        if (!(Boolean) respuesta.get("error")) {

            listaEstudiantes = FXCollections.observableArrayList();

            listaEstudiantes.addAll((ArrayList<Estudiante>) respuesta.get(
                    "estudiantes"));
            cbEstudiantes.setItems(listaEstudiantes);

        } else {

            Alertas.mostrarAlerta("Error",
                    "No se pudo cargar la lista de estudiantes",
                    AlertType.ERROR);

            Stage escenario = (Stage) vboxDesasignarEstudiante.getScene()
                    .getWindow();

            escenario.close();

        }

    }

    private void desasignarEstudiante() {
        Estudiante estudiante = cbEstudiantes.getSelectionModel()
                .getSelectedItem();
        HashMap<String, Object> respuesta = EstudianteDAO.desasignarEstudiante(
                estudiante.getIdEstudiante());
        if (!(Boolean) respuesta.get("error")) {
            Alertas.mostrarAlerta("Desasignación exitosa",
                    respuesta.get("mensaje").toString(),
                    AlertType.INFORMATION);
        } else {
            Alertas.mostrarAlerta("Error",
                    respuesta.get("mensaje").toString(),
                    AlertType.ERROR);

            Stage escenario = (Stage) vboxDesasignarEstudiante.getScene()
                    .getWindow();

            escenario.close();

        }
    }

}

/*
* Autor: Cesar Gonzalez Lopez
* Fecha de creación: 24/11/2023
* Fecha de modificación: 25/11/2023
* Descripción: clase para reasignar actividades a los estudiantes
*/

package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import modelo.dao.EstudianteDAO;
import modelo.pojo.Actividad;
import modelo.pojo.Estudiante;

public class FXMLReasignarActividadController implements Initializable {
    @FXML
    private TableView<Actividad> tvActividadesPendientes;

    @FXML
    private TableColumn<Actividad, String> colTitulo;

    @FXML
    private TableColumn<Actividad, String> colEstudiante;

    @FXML
    private TableColumn<Actividad, String> colFechaInicio;

    @FXML
    private ComboBox<Estudiante> cbEstudiantes;

    @FXML
    private Button btnReasignarActividad;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnReasignarActividad.setDisable(true);

        tvActividadesPendientes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tvActividadesPendientes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Actividad>() {
            @Override
            public void changed(ObservableValue<? extends Actividad> observable, Actividad oldValue, Actividad newValue) {
                
                btnReasignarActividad.setDisable(newValue == null);

            }
        });

        mostrarDatos();

        // Asegúrate de que la lista de estudiantes no esté vacía antes de seleccionar el primero
        ObservableList<Estudiante> estudiantes = cbEstudiantes.getItems();
        if (!estudiantes.isEmpty()) {
            cbEstudiantes.getSelectionModel().select(0);
        }
    }


    private void mostrarDatos() {

        ObservableList<Actividad> actividades = FXCollections.observableArrayList();

        try {

            ArrayList<Actividad> listaActividades = modelo.dao.ActividadDAO.consultarActividades();
            actividades.addAll(listaActividades);

            tvActividadesPendientes.setItems(actividades);
            colTitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
            colEstudiante
                    .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstudiante()));
            colFechaInicio
                    .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaInicio()));

        } catch (Exception e) {

            utilidades.Alertas.mostrarAlerta("Error de conexion",
                    "No se pudo consultar la lista de cambios",
                    Alert.AlertType.ERROR);

        }

        ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList();

        try {

            ArrayList<Estudiante> listaEstudiantes = EstudianteDAO.consultarListaEstudiante();
            estudiantes.addAll(listaEstudiantes);

            cbEstudiantes.setItems(estudiantes);

        } catch (Exception e) {

            utilidades.Alertas.mostrarAlerta("Error de conexion",
                    "No se pudo consultar la lista de cambios",
                    Alert.AlertType.ERROR);

        }
    }

    @FXML
    private void btnReasignarActividadClic(ActionEvent event) {

        TableView<Actividad> tableView = tvActividadesPendientes;
        Actividad actividadSeleccionada = tableView.getSelectionModel().getSelectedItem();

        Estudiante estudianteSeleccionado = cbEstudiantes.getValue();

        if (actividadSeleccionada != null && estudianteSeleccionado != null) {

            try {

                modelo.dao.ActividadDAO.reasignarActividad(actividadSeleccionada.getIdActividad(),
                        estudianteSeleccionado.getIdEstudiante());

                utilidades.Alertas.mostrarAlerta("Actividad reasignada",
                        "La actividad se reasigno correctamente",
                        Alert.AlertType.INFORMATION);

            } catch (Exception e) {

                utilidades.Alertas.mostrarAlerta("Error de conexion",
                        "No se pudo reasignar la actividad",
                        Alert.AlertType.ERROR);

            }

        } else {

            utilidades.Alertas.mostrarAlerta("Actividad no seleccionada",
                    "Por favor selecciona una actividad",
                    Alert.AlertType.WARNING);

        }
        Stage escenario = (Stage) btnReasignarActividad.getScene().getWindow();
        escenario.close();

    }

    @FXML
    private void btnVolverClic(ActionEvent event) {

        Stage escenario = (Stage) btnReasignarActividad.getScene().getWindow();
        escenario.close();

    }
}

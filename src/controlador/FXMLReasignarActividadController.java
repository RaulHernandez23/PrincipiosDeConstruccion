/*
* Autor: Cesar Gonzalez Lopez
* Fecha de creación: 24/11/2023
* Fecha de modificación: 09/12/2023
* Descripción: clase para reasignar actividades a los estudiantes
*/

package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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

        tvActividadesPendientes.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE);

        tvActividadesPendientes.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Actividad>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends Actividad> observable,
                            Actividad oldValue,
                            Actividad newValue) {

                        btnReasignarActividad.setDisable(newValue == null);

                    }

                });

        mostrarDatos();

        ObservableList<Estudiante> estudiantes = cbEstudiantes.getItems();

        if (!estudiantes.isEmpty()) {
            cbEstudiantes.getSelectionModel().select(0);
        }
    }

    private void mostrarDatos() {

        ObservableList<Actividad> actividades = FXCollections
                .observableArrayList();
        ObservableList<Estudiante> estudiantes = FXCollections
                .observableArrayList();

        try {

            HashMap<String, Object> respuesta = modelo.dao.ActividadDAO
                    .consultarActividades();

            if (!(Boolean) respuesta.get("error")) {

                ArrayList<HashMap<String, Object>> listaActividades = (ArrayList<HashMap<String, Object>>) respuesta
                        .get("actividades");

                for (HashMap<String, Object> actividadMap : listaActividades) {
                    
                    Actividad actividad = new Actividad();

                    actividad.setIdActividad((Integer) actividadMap.get(
                            "idActividad"));
                    actividad.setTitulo((String) actividadMap.get(
                            "titulo"));
                    actividad.setIdEstudiante((Integer) actividadMap.get(
                            "idEstudiante"));
                    actividad.setEstudiante((String) actividadMap.get(
                            "estudiante"));
                    actividad.setFechaInicio((String) actividadMap.get(
                            "fechaInicio"));

                    actividades.add(actividad);

                }

                tvActividadesPendientes.setItems(actividades);
                colTitulo.setCellValueFactory(
                        cellData -> new SimpleStringProperty(
                                cellData.getValue().getTitulo()));
                colEstudiante
                        .setCellValueFactory(
                                cellData -> new SimpleStringProperty(
                                        cellData.getValue().getEstudiante()));
                colFechaInicio.setCellValueFactory(
                        cellData -> new SimpleStringProperty(
                                cellData.getValue().getFechaInicio()));

            } else {

                String mensajeError = respuesta.get("mensaje").toString();
                utilidades.Alertas.mostrarAlerta("Error de conexión",
                        mensajeError, Alert.AlertType.ERROR);

            }

        } catch (Exception e) {

            e.printStackTrace();
            utilidades.Alertas.mostrarAlerta("Error",
                    "Error inesperado", Alert.AlertType.ERROR);

        }

        try {

            HashMap<String, Object> respuestaEstudiantes = EstudianteDAO
                    .consultarListaEstudiante();

            if (!((Boolean) respuestaEstudiantes.get("error"))) {

                ArrayList<HashMap<String, Object>> listaEstudiantes = 
                (ArrayList<HashMap<String, Object>>) respuestaEstudiantes
                        .get("estudiantes");

                for (HashMap<String, Object> estudianteMap : listaEstudiantes) {
                    
                    Estudiante estudiante = new Estudiante();

                    estudiante.setIdEstudiante((int) estudianteMap.get(
                            "idEstudiante"));
                    estudiante.setNombre((String) estudianteMap.get(
                            "nombre"));
                    estudiante.setApellidoPaterno((String) estudianteMap.get(
                            "apellidoPaterno"));
                    estudiante.setApellidoMaterno((String) estudianteMap.get(
                            "apellidoMaterno"));

                    estudiantes.add(estudiante);

                }

                cbEstudiantes.setItems(estudiantes);
            } else {

                String mensajeError = respuestaEstudiantes.get("mensaje")
                        .toString();
                utilidades.Alertas.mostrarAlerta("Error de conexión",
                        mensajeError, Alert.AlertType.ERROR);

            }
        } catch (Exception e) {

            e.printStackTrace(); 
            utilidades.Alertas.mostrarAlerta("Error de conexión",
                    "Error al obtener la lista de estudiantes",
                    Alert.AlertType.ERROR);

        }

    }

    @FXML
    private void btnReasignarActividadClic(ActionEvent event) {

        TableView<Actividad> tableView = tvActividadesPendientes;
        Actividad actividadSeleccionada = tableView.getSelectionModel()
                .getSelectedItem();
        Estudiante estudianteSeleccionado = cbEstudiantes.getValue();

        if (actividadSeleccionada != null && estudianteSeleccionado != null) {

            try {

                modelo.dao.ActividadDAO.reasignarActividad(
                        actividadSeleccionada.getIdActividad(),
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

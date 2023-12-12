package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.pojo.Cambio;
import modelo.pojo.Defecto;
import utilidades.Utilidades;

public class FXMLConsultarDefectosController implements Initializable {

    private Integer idProyecto;

    @FXML
    private TableView<Defecto> tvDefectos;

    @FXML
    TableColumn<Defecto, String> colTituloDefecto;

    @FXML
    TableColumn<Defecto, String> colEstadoDefectos;

    @FXML
    TableColumn<Defecto, String> colFechaReporteDefectos;

    @FXML
    Button btnAceptar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnAceptar.setDisable(true);
        tvDefectos.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Defecto> observable, Defecto oldValue, Defecto newValue) -> {
                    btnAceptar.setDisable(newValue == null);
                });

        mostrarDatos();

    }

    public void inicializarVentana(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    private void mostrarDatos() {
        ObservableList<Defecto> defectos = FXCollections.observableArrayList();

        try {
            HashMap<String, Object> respuestaDefectos = modelo.dao.DefectoDAO
                    .consultarDefectos();

            if (!((Boolean) respuestaDefectos.get("error"))) {
                ArrayList<HashMap<String, Object>> listaDefectos;
                listaDefectos = (ArrayList<HashMap<String, Object>>) respuestaDefectos
                        .get("defectos");

                for (HashMap<String, Object> defectoMap : listaDefectos) {
                    Defecto defecto = new Defecto();
                    defecto.setIdDefecto((int) defectoMap.get("idDefecto"));
                    defecto.setTitulo((String) defectoMap.get("titulo"));
                    defecto.setDescripcion((String) defectoMap.get(
                            "descripcion"));
                    defecto.setEsfuerzoMinutos((int) defectoMap.get(
                            "esfuerzoMinutos"));
                    defecto.setFechaReporte((String) defectoMap.get(
                            "fechaReporte"));
                    defecto.setFechaFin((String) defectoMap.get(
                            "fechaFin"));
                    defecto.setIdEstadoDefecto((int) defectoMap.get(
                            "idEstadoDefecto"));
                    defecto.setIdEstudiante((int) defectoMap.get(
                            "idEstudiante"));
                    defecto.setEstadoDefecto((String) defectoMap.get(
                            "estadoActividad"));
                    defecto.setNombreEstudiante((String) defectoMap.get(
                            "estudiante"));

                    defectos.add(defecto);
                }

                tvDefectos.setItems(defectos);
                colTituloDefecto
                        .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
                colEstadoDefectos.setCellValueFactory(
                        cellData -> new SimpleStringProperty(cellData.getValue().getEstadoDefecto()));
                colFechaReporteDefectos.setCellValueFactory(
                        cellData -> new SimpleStringProperty(cellData.getValue().getFechaReporte()));
            } else {
                String mensajeError = respuestaDefectos.get("mensaje").toString();
                utilidades.Alertas.mostrarAlerta("Error de conexión", mensajeError, Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Añadir un manejo de excepciones más adecuado según tus necesidades
            utilidades.Alertas.mostrarAlerta("Error de conexión", "Error al obtener la lista de defectos",
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnAceptarClic(ActionEvent event) {
        Defecto defectoSeleccionado = tvDefectos.getSelectionModel().getSelectedItem();
        consultarDefecto(defectoSeleccionado);
    }

    private void consultarDefecto(Defecto defectoSeleccionado) {
        Stage escenario = new Stage();
        try {
            FXMLLoader loader = Utilidades.getFXMLLoader("/vista/FXMLConsultarDetallesDefecto.fxml");
            Parent vista = loader.load();
            Scene escena = new Scene(vista);
            FXMLConsultarDetallesDefectosController controlador = loader.getController();
            controlador.inicializarVentana(defectoSeleccionado);

            escenario.setScene(escena);
            escenario.setTitle("Consultar defecto");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnCerrarClic(ActionEvent event) {
        salir();
    }

    private void salir() {
        Stage escenario = (Stage) tvDefectos.getScene().getWindow();

        escenario.close();
    }
}

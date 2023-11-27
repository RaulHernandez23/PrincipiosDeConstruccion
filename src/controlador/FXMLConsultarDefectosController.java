package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.pojo.Defecto;

public class FXMLConsultarDefectosController implements Initializable {

    @FXML
    private TableView<Defecto> tvDefectos;

    @FXML
    TableColumn<Defecto, String> colTituloDefecto;

    @FXML
    TableColumn<Defecto, String> colEstadoDefectos;

    @FXML
    TableColumn<Defecto, String> colFechaReporteDefectos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mostrarDatos();

    }

    private void mostrarDatos() {

        ObservableList<Defecto> defectos = FXCollections.observableArrayList();

        try {

            ArrayList<Defecto> listaDefectos = modelo.dao.DefectoDAO.consultarDefectos();
            defectos.addAll(listaDefectos);

            tvDefectos.setItems(defectos);
            colTituloDefecto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
            colEstadoDefectos.setCellValueFactory(
                    cellData -> new SimpleStringProperty(cellData.getValue().getEstadoActividad()));
            colFechaReporteDefectos
                    .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaReporte()));

        } catch (Exception e) {

            utilidades.Alertas.mostrarAlerta("Error de conexion",
                    "No se pudo consultar la lista de cambios",
                    Alert.AlertType.ERROR);

        }

    }

    @FXML
    private void btnAceptarClic(ActionEvent event) {

    }

    @FXML
    private void btnCerrarClic(ActionEvent event) {

    }

}

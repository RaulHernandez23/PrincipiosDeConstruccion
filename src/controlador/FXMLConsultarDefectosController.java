package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
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
                    cellData -> new SimpleStringProperty(cellData.getValue().getEstadoDefecto()));
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
        Defecto defectoSeleccionado = tvDefectos.getSelectionModel().getSelectedItem();
        if (defectoSeleccionado != null) {
            try {
                FXMLConsultarDetallesDefectosController detallesController = new FXMLConsultarDetallesDefectosController();
                detallesController.recibirDefectoSeleccionado(defectoSeleccionado);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/FXMLConsultarDetallesDefectos.fxml"));
                loader.setController(detallesController);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                

            } catch (IOException e) {
                e.printStackTrace();  // Manejo de errores, aquí puedes poner tu lógica específica
            }
        } else {
            utilidades.Alertas.mostrarAlerta("Defecto no seleccionado",
                    "No se ha seleccionado ningún defecto",
                    Alert.AlertType.WARNING);
        }
    }


    @FXML
    private void btnCerrarClic(ActionEvent event) {

    }
}

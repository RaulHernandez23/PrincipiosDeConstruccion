package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import modelo.dao.CambioDAO;
import modelo.pojo.Cambio;

public class FXMLFinalizarCambioController implements Initializable {

    @FXML
    public ComboBox<Cambio> cbCambios;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostrarDatos();
    }

    private void mostrarDatos() {

        ObservableList<Cambio> cambios = FXCollections.observableArrayList();

        try {

            ArrayList<Cambio> listaCambios = CambioDAO.consultarCambios();
            cambios.addAll(listaCambios);

            cbCambios.setItems(cambios);

        } catch (Exception e) {
            utilidades.Alertas.mostrarAlerta("Error de conexion", 
                "No se pudo consultar la lista de cambios", 
                Alert.AlertType.ERROR);
        }

    }

}

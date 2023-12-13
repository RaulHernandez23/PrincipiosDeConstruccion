/*
 * Nombre del archivo: FXMLRegistrarDefectoController.java
 * Autor: Raul Hernandez
 * Paquete: src.controlador
 * Fecha de creacion: 29/11/2023
 * Fecha de modificacion: 29/11/2023
 * Descripción: archivo que contiene la logica de la interfaz
 * FXMLRegistrarDefecto
 */

package controlador;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.dao.DefectoDAO;
import modelo.pojo.Defecto;
import utilidades.Utilidades;
import javafx.scene.control.Alert;

public class FXMLRegistrarDefectoController implements Initializable {

    private Defecto defecto;
    private int idProyecto;
    private int idEstudiante;

    @FXML
    private VBox vboxEjemplo;

    @FXML
    private TextField tfTitulo;

    @FXML
    private ImageView ivSalir;

    @FXML
    private TextArea taDescripcion;

    @FXML
    private Button btnRegistrar;

    @FXML
    void btnRegistrarClic(ActionEvent event) {
        defecto = new Defecto();
        registrarDefecto();
    }

    @FXML
    void btnVolver(ActionEvent event) {
        salir();
    }

    @FXML
    void btnSalir(MouseEvent event) {
        salir();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnRegistrar.setDisable(true);
        configurarTextFields();
    }

    public void salir() {

        Stage escenario = (Stage) vboxEjemplo.getScene().getWindow();

        escenario.close();

    }

    public void inicializarInformacion(int idProyecto, int idEstudiante) {

        this.idProyecto = idProyecto;
        this.idEstudiante = idEstudiante;

    }

    private void registrarDefecto() {

        defecto.setTitulo(tfTitulo.getText());
        defecto.setDescripcion(taDescripcion.getText());
        defecto.setIdProyecto(idProyecto);
        defecto.setIdEstudiante(idEstudiante);

        HashMap<String, Object> respuesta = DefectoDAO.registrarDefecto(
                defecto);

        if ((Boolean) respuesta.get("error") == false) {

            Utilidades.mostrarAlertaSimple("Información",
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.INFORMATION);

            salir();

        } else {
            Utilidades.mostrarAlertaSimple("Error",
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.ERROR);
        }

    }

    private void configurarTextFields() {

        tfTitulo.textProperty()
                .addListener((observable, oldValue, newValue) -> activarBoton(
                        tfTitulo, taDescripcion, btnRegistrar));
        taDescripcion.textProperty()
                .addListener((observable, oldValue, newValue) -> activarBoton(
                        tfTitulo, taDescripcion, btnRegistrar));

    }

    // Configurar
    /*
     * private void configurarListenerACampos() {
     * 
     * ChangeListener<String> cambiosEnCampos = new ChangeListener<String>() {
     * 
     * @Override
     * public void changed(ObservableValue<? extends String> observable, String
     * oldValue, String newValue) {
     * // Verificar si todos los campos están llenos y habilitar/deshabilitar el
     * botón
     * // en consecuencia
     * btnAgregarProyecto.setDisable(
     * tfNombre.getText().isEmpty() ||
     * tfApellidoPaterno.getText().isEmpty() ||
     * tfApellidoMaterno.getText().isEmpty() ||
     * tfMatricula.getText().isEmpty() ||
     * tfMatricula.getText().isEmpty());
     * }
     * };
     * 
     * tfNombre.textProperty().addListener(cambiosEnCampos);
     * tfApellidoPaterno.textProperty().addListener(cambiosEnCampos);
     * tfApellidoMaterno.textProperty().addListener(cambiosEnCampos);
     * tfMatricula.textProperty().addListener(cambiosEnCampos);
     * }
     */
    private void activarBoton(TextField tfTitulo,
            TextArea taDescripcion,
            Button btnRegistrar) {

        boolean camposLLenos = !tfTitulo.getText().isEmpty() &&
                !taDescripcion.getText().isEmpty();
        btnRegistrar.setDisable(!camposLLenos);

    }
}
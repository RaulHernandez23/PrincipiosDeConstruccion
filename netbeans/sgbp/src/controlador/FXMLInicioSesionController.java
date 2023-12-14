/*
 * Nombre del archivo: FXMLInicioSesionController.java
 * Autor: Albhieri Cristoff Villa Contreras
 * Paquete: controlador
 * Fecha de creación: 03/12/2023
 * Fecha de modificación: 14/12/2023
 * Descripción: Clase controlador para la ventana de inicio de sesión
 */

package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import modelo.dao.EstudianteDAO;
import modelo.dao.ResponsableProyectoDAO;
import modelo.pojo.Estudiante;
import modelo.pojo.ResponsableProyecto;
import modelo.pojo.RespuestaInicioSesion;
import utilidades.Utilidades;

public class FXMLInicioSesionController implements Initializable {

    @FXML
    private VBox vboxIniciarSesion;
    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Pane paneMensaje;
    @FXML
    private Label lblMensaje;
    @FXML
    private Button btnIniciarSesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paneMensaje.setVisible(false);
    }

    @FXML
    private void btnIniciarSesionClic(ActionEvent event) {
        iniciarSesion();
    }

    @FXML
    void presionarTeclaEnter(KeyEvent event) {

        if (event.getCode().toString().equals("ENTER")) {
            iniciarSesion();
        }

    }

    @FXML
    void presionarTeclaAbajo(KeyEvent event) {
        if (event.getCode().toString().equals("DOWN") ||
                event.getCode().toString().equals("TAB")) {
            pfPassword.requestFocus();
        }
    }

    @FXML
    void presionarTeclaArriba(KeyEvent event) {
        if (event.getCode().toString().equals("UP") ||
                event.getCode().toString().equals("TAB")) {
            tfUsuario.requestFocus();
        }
    }

    private void iniciarSesion() {

        if (!verificarCamposLlenos()) {

            lblMensaje.setText(
                    "Por favor digita el usuario y/o la contraseña");

            ObservableList<String> estilo = paneMensaje.getStyleClass();

            if (estilo.contains("mensajeError")) {

                estilo.remove("mensajeError");
                estilo.add("mensajeAdvertencia");

            }

            paneMensaje.setVisible(true);

        } else {
            verificarConexion(tfUsuario.getText(), pfPassword
                    .getText());
        }

    }

    private boolean verificarCamposLlenos() {
        return !tfUsuario.getText().isEmpty() &&
                !pfPassword.getText().isEmpty();
    }

    private void verificarConexion(String usuario, String password) {

        RespuestaInicioSesion usuarioAutenticado = null;

        if (usuario.toLowerCase().startsWith("s")) {
            usuarioAutenticado = EstudianteDAO.iniciarSesionEstudiante(
                    usuario.toLowerCase(), password);
        } else {
            usuarioAutenticado = ResponsableProyectoDAO
                    .iniciarSesionResponsable(usuario, password);
        }

        if (!usuarioAutenticado.isCorrecto()) {

            lblMensaje.setText(usuarioAutenticado.getMensaje());

            ObservableList<String> estilo = paneMensaje.getStyleClass();

            if (estilo.contains("mensajeAdvertencia")) {

                estilo.remove("mensajeAdvertencia");
                estilo.add("mensajeError");

            }

            paneMensaje.setVisible(true);

        } else {

            if (usuario.toLowerCase().startsWith("s")) {
                abrirMenuEstudiante(usuarioAutenticado
                        .getEstudiante());
            } else {
                abrirMenuResponsable(
                        usuarioAutenticado.getResponsableProyecto());
            }

        }

    }

    private void abrirMenuEstudiante(Estudiante estudiante) {

        Stage escenario = (Stage) vboxIniciarSesion.getScene().getWindow();

        try {

            FXMLLoader fxmlLoader = Utilidades.getFXMLLoader(
                    "/vista/FXMLMenuEstudiante.fxml");
            Pane vista = fxmlLoader.load();
            Scene escena = new Scene(vista);
            FXMLMenuEstudianteController controlador = fxmlLoader
                    .getController();

            escena.getStylesheets().add(Utilidades.getURLString(
                    "/vista/estilos/escenaMenu.css"));
            controlador.inicializarVentana(estudiante);
            escenario.setScene(escena);
            escenario.setTitle("Menu Estudiante");
            escenario.setResizable(false);
            escenario.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void abrirMenuResponsable(ResponsableProyecto responsable) {

        Stage escenario = (Stage) vboxIniciarSesion.getScene().getWindow();

        try {

            FXMLLoader fxmlLoader = Utilidades.getFXMLLoader(
                    "/vista/FXMLMenuResponsableA.fxml");
            Pane vista = fxmlLoader.load();
            Scene escena = new Scene(vista);
            FXMLMenuResponsableController controlador = fxmlLoader
                    .getController();

            escena.getStylesheets().add(Utilidades.getURLString(
                    "/vista/estilos/escenaMenu.css"));
            controlador.inicializarVentana(responsable);
            escenario.setScene(escena);
            escenario.setTitle("Menu Responsable <<pagina 1>>");
            escenario.setResizable(false);
            escenario.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

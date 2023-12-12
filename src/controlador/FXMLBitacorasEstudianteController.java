package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import modelo.dao.ActividadDAO;
import modelo.dao.CambioDAO;
import modelo.dao.DefectoDAO;
import modelo.dao.EstudianteDAO;
import modelo.dao.SolicitudDeCambioDAO;
import modelo.pojo.Actividad;
import modelo.pojo.Bitacora;
import modelo.pojo.Cambio;
import modelo.pojo.Defecto;
import modelo.pojo.Estudiante;
import modelo.pojo.SolicitudDeCambio;
import utilidades.Alertas;
import utilidades.Utilidades;

public class FXMLBitacorasEstudianteController implements Initializable {

    @FXML
    private VBox vboxBitacorasEstudiante;
    @FXML
    private ImageView ivSalir;
    @FXML
    private ComboBox<Estudiante> cbEstudiante;
    @FXML
    private ComboBox<String> cbBitacora;
    @FXML
    private TableView<Bitacora> tvBitacoras;

    private TableColumn colTituloCambio;
    private TableColumn colEstadoCambio;
    private TableColumn colEsfuerzoCambio;
    private TableColumn colTipoCambio;
    private TableColumn colFechaInicioCambio;

    private TableColumn colTituloActividad;
    private TableColumn colEstadoActividad;
    private TableColumn colEsfuerzoActividad;
    private TableColumn colTipoActividad;
    private TableColumn colFechaInicioActividad;

    private TableColumn colTituloDefecto;
    private TableColumn colEstadoDefecto;
    private TableColumn colEsfuerzoDefecto;
    private TableColumn colFechaReporteDefecto;

    private TableColumn colTituloSolicitud;
    private TableColumn colNumeroSolicitud;
    private TableColumn colFechaSolicitud;

    private ObservableList<Estudiante> listaEstudiantes;
    private ObservableList<Actividad> listaActividades;
    private ObservableList<Cambio> listaCambios;
    private ObservableList<Defecto> listaDefectos;
    private ObservableList<SolicitudDeCambio> listaSolicitudes;
    private Integer idProyecto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colTituloCambio = new TableColumn("Título");
        colEstadoCambio = new TableColumn("Estado");
        colEsfuerzoCambio = new TableColumn("Esfuerzo");
        colTipoCambio = new TableColumn("Tipo");
        colFechaInicioCambio = new TableColumn("Fecha de inicio");

        colTituloActividad = new TableColumn("Título");
        colEstadoActividad = new TableColumn("Estado");
        colEsfuerzoActividad = new TableColumn("Esfuerzo");
        colTipoActividad = new TableColumn("Tipo");
        colFechaInicioActividad = new TableColumn("Fecha de inicio");

        colTituloDefecto = new TableColumn("Título");
        colEstadoDefecto = new TableColumn("Estado");
        colEsfuerzoDefecto = new TableColumn("Esfuerzo");
        colFechaReporteDefecto = new TableColumn("Fecha de reporte");

        colTituloSolicitud = new TableColumn("Título");
        colNumeroSolicitud = new TableColumn("Número de solicitud");
        colFechaSolicitud = new TableColumn("Fecha de solicitud");

        configurarTabla();

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
        salir();
    }

    @FXML
    private void btnVolver(ActionEvent event) {
        salir();
    }

    public void inicializarVentana(Integer idProyecto) {

        this.idProyecto = idProyecto;

        cargarEstudiantes();
        cargarBitacoras();

    }

    private void salir() {

        Stage escenario = (Stage) vboxBitacorasEstudiante.getScene()
                .getWindow();

        escenario.close();

    }

    private void configurarTabla() {

        this.colTituloActividad.setCellValueFactory(
                new PropertyValueFactory<>("titulo"));
        this.colEstadoActividad.setCellValueFactory(
                new PropertyValueFactory<>("estado"));
        this.colEsfuerzoActividad.setCellValueFactory(
                new PropertyValueFactory<>("esfuerzo"));
        this.colTipoActividad.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));
        this.colFechaInicioActividad.setCellValueFactory(
                new PropertyValueFactory<>("fechaInicio"));

        this.colTituloCambio.setCellValueFactory(
                new PropertyValueFactory<>("titulo"));
        this.colEstadoCambio.setCellValueFactory(
                new PropertyValueFactory<>("estado"));
        this.colEsfuerzoCambio.setCellValueFactory(
                new PropertyValueFactory<>("esfuerzo"));
        this.colTipoCambio.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));
        this.colFechaInicioCambio.setCellValueFactory(
                new PropertyValueFactory<>("fechaInicio"));

        this.colTituloDefecto.setCellValueFactory(
                new PropertyValueFactory<>("titulo"));
        this.colEstadoDefecto.setCellValueFactory(
                new PropertyValueFactory<>("estado"));
        this.colEsfuerzoDefecto.setCellValueFactory(
                new PropertyValueFactory<>("esfuerzo"));
        this.colFechaReporteDefecto.setCellValueFactory(
                new PropertyValueFactory<>("fechaReporte"));

        this.colTituloSolicitud.setCellValueFactory(
                new PropertyValueFactory<>("titulo"));
        this.colNumeroSolicitud.setCellValueFactory(
                new PropertyValueFactory<>("numeroSolicitud"));
        this.colFechaSolicitud.setCellValueFactory(
                new PropertyValueFactory<>("fechaSolicitud"));

    }

    private void cambiarTablaCambios() {

        int numeroColumnas = 5;

        tvBitacoras.getColumns().clear();

        colTituloCambio.prefWidthProperty().bind(tvBitacoras.widthProperty()
                .divide(numeroColumnas));
        colEstadoCambio.prefWidthProperty().bind(tvBitacoras.widthProperty()
                .divide(numeroColumnas));
        colEsfuerzoCambio.prefWidthProperty().bind(tvBitacoras.widthProperty()
                .divide(numeroColumnas));
        colTipoCambio.prefWidthProperty().bind(tvBitacoras.widthProperty()
                .divide(numeroColumnas));
        colFechaInicioCambio.prefWidthProperty().bind(tvBitacoras
                .widthProperty().divide(numeroColumnas));

        tvBitacoras.getColumns().addAll(
                colTituloCambio,
                colEstadoCambio,
                colEsfuerzoCambio,
                colTipoCambio,
                colFechaInicioCambio);

        HashMap<String, Object> respuesta = CambioDAO
                .consultarCambiosEstudiante(
                        cbEstudiante.getSelectionModel().getSelectedItem()
                                .getIdEstudiante(),
                        idProyecto);

        if (!(Boolean) respuesta.get("error")) {

            listaCambios = FXCollections.observableArrayList();

            listaCambios.addAll((ArrayList<Cambio>) respuesta.get(
                    "cambios"));

            ObservableList<Bitacora> listaBitacoras = FXCollections
                    .observableArrayList();

            for (Cambio cambio : listaCambios) {

                Bitacora bitacora = new Bitacora();

                bitacora.setTitulo(cambio.getTitulo());
                bitacora.setEstado(cambio.getEstadoCambio());
                bitacora.setEsfuerzo(cambio.getEsfuerzoMinutos());
                bitacora.setTipo(cambio.getTipoActividad());
                bitacora.setFechaInicio(cambio.getFechaInicio());

                listaBitacoras.add(bitacora);

            }

            tvBitacoras.setItems(listaBitacoras);

        } else {

            Alertas.mostrarAlerta("Error", respuesta.get("mensaje")
                    .toString(),
                    AlertType.ERROR);

            Stage escenario = (Stage) vboxBitacorasEstudiante.getScene()
                    .getWindow();

            escenario.close();

        }

    }

    private void cambiarTablaActividades() {

        int numeroColumnas = 5;

        tvBitacoras.setItems(null);
        tvBitacoras.getColumns().clear();

        colTituloActividad.prefWidthProperty().bind(tvBitacoras.widthProperty()
                .divide(numeroColumnas));
        colEstadoActividad.prefWidthProperty().bind(tvBitacoras.widthProperty()
                .divide(numeroColumnas));
        colEsfuerzoActividad.prefWidthProperty().bind(tvBitacoras
                .widthProperty().divide(numeroColumnas));
        colTipoActividad.prefWidthProperty().bind(tvBitacoras.widthProperty()
                .divide(numeroColumnas));
        colFechaInicioActividad.prefWidthProperty().bind(tvBitacoras
                .widthProperty().divide(numeroColumnas));

        tvBitacoras.getColumns().addAll(
                colTituloActividad,
                colEstadoActividad,
                colEsfuerzoActividad,
                colTipoActividad,
                colFechaInicioActividad);

        HashMap<String, Object> respuesta = ActividadDAO
                .consultarActividadesEstudiante(
                        cbEstudiante.getSelectionModel().getSelectedItem()
                                .getIdEstudiante(),
                        idProyecto);

        if (!(Boolean) respuesta.get("error")) {

            listaActividades = FXCollections.observableArrayList();

            listaActividades.addAll((ArrayList<Actividad>) respuesta.get(
                    "actividades"));

            ObservableList<Bitacora> listaBitacoras = FXCollections
                    .observableArrayList();

            for (Actividad actividad : listaActividades) {

                Bitacora bitacora = new Bitacora();

                bitacora.setTitulo(actividad.getTitulo());
                bitacora.setEstado(actividad.getEstadoActividad());
                bitacora.setEsfuerzo(actividad.getEsfuerzoMinutos());
                bitacora.setTipo(actividad.getTipo());
                bitacora.setFechaInicio(actividad.getFechaInicio());

                listaBitacoras.add(bitacora);

            }

            tvBitacoras.setItems(listaBitacoras);

        } else {

            Alertas.mostrarAlerta("Error", respuesta.get("mensaje")
                    .toString(),
                    AlertType.ERROR);

            Stage escenario = (Stage) vboxBitacorasEstudiante.getScene()
                    .getWindow();

            escenario.close();

        }

    }

    private void cambiarTablaDefectos() {

        int numeroColumnas = 4;

        tvBitacoras.getColumns().clear();

        colTituloDefecto.prefWidthProperty().bind(tvBitacoras.widthProperty()
                .divide(numeroColumnas));
        colEstadoDefecto.prefWidthProperty().bind(tvBitacoras.widthProperty()
                .divide(numeroColumnas));
        colEsfuerzoDefecto.prefWidthProperty().bind(tvBitacoras.widthProperty()
                .divide(numeroColumnas));
        colFechaReporteDefecto.prefWidthProperty().bind(tvBitacoras
                .widthProperty().divide(numeroColumnas));

        tvBitacoras.getColumns().addAll(
                colTituloDefecto,
                colEstadoDefecto,
                colEsfuerzoDefecto,
                colFechaReporteDefecto);

        HashMap<String, Object> respuesta;
        respuesta = DefectoDAO.consultarDefectosEstudiante(
                cbEstudiante.getSelectionModel().getSelectedItem()
                        .getIdEstudiante(),
                idProyecto);

        if (!(Boolean) respuesta.get("error")) {

            listaDefectos = FXCollections.observableArrayList();

            listaDefectos.addAll((ArrayList<Defecto>) respuesta.get(
                    "defectos"));

            ObservableList<Bitacora> listaBitacoras = FXCollections
                    .observableArrayList();

            for (Defecto defecto : listaDefectos) {

                Bitacora bitacora = new Bitacora();

                bitacora.setTitulo(defecto.getTitulo());
                bitacora.setEstado(defecto.getEstadoDefecto());
                bitacora.setEsfuerzo(defecto.getEsfuerzoMinutos());
                bitacora.setFechaReporte(defecto.getFechaReporte());

                listaBitacoras.add(bitacora);

            }

            tvBitacoras.setItems(listaBitacoras);

        } else {

            Alertas.mostrarAlerta("Error", respuesta.get("mensaje")
                    .toString(),
                    AlertType.ERROR);

            Stage escenario = (Stage) vboxBitacorasEstudiante.getScene()
                    .getWindow();

            escenario.close();

        }

    }

    private void cambiarTablaSolicitudes() {

        int numeroColumnas = 3;

        tvBitacoras.getColumns().clear();

        colNumeroSolicitud.prefWidthProperty().bind(tvBitacoras.widthProperty()
                .divide(numeroColumnas));
        colTituloSolicitud.prefWidthProperty().bind(tvBitacoras.widthProperty()
                .divide(numeroColumnas));
        colFechaSolicitud.prefWidthProperty().bind(tvBitacoras.widthProperty()
                .divide(numeroColumnas));

        tvBitacoras.getColumns().addAll(
                colNumeroSolicitud,
                colTituloSolicitud,
                colFechaSolicitud);

        HashMap<String, Object> respuesta = SolicitudDeCambioDAO
                .consultarSolicitudesEstudiante(
                        cbEstudiante.getSelectionModel().getSelectedItem()
                                .getIdEstudiante(),
                        idProyecto);

        if (!(Boolean) respuesta.get("error")) {

            listaSolicitudes = FXCollections.observableArrayList();

            listaSolicitudes.addAll((ArrayList<SolicitudDeCambio>) respuesta
                    .get("solicitudes"));

            ObservableList<Bitacora> listaBitacoras = FXCollections
                    .observableArrayList();

            for (SolicitudDeCambio solicitud : listaSolicitudes) {

                Bitacora bitacora = new Bitacora();

                bitacora.setTitulo(solicitud.getTitulo());
                bitacora.setNumeroSolicitud(solicitud.getIdSolicitudDeCambio());
                bitacora.setFechaSolicitud(solicitud.getFechaCreacion());

                listaBitacoras.add(bitacora);

            }

            tvBitacoras.setItems(listaBitacoras);

        } else {

            Alertas.mostrarAlerta("Error", respuesta.get("mensaje")
                    .toString(),
                    AlertType.ERROR);

            Stage escenario = (Stage) vboxBitacorasEstudiante.getScene()
                    .getWindow();

            escenario.close();

        }

    }

    private void cargarBitacoras() {

        cbBitacora.getItems().clear();

        cbBitacora.getItems().addAll(
                "Cambios",
                "Defectos",
                "Solicitudes de cambio",
                "Actividades");

        ChangeListener<String> cambiarTabla = (observable,
                oldValue,
                newValue) -> {

            switch (newValue) {

                case "Cambios":
                    cambiarTablaCambios();
                    break;

                case "Defectos":
                    cambiarTablaDefectos();
                    break;

                case "Solicitudes de cambio":
                    cambiarTablaSolicitudes();
                    break;

                case "Actividades":
                    cambiarTablaActividades();
                    break;

            }

        };

        ChangeListener<Estudiante> cambiarEstudiante = (observable,
                oldValue,
                newValue) -> {

            if (newValue != null) {

                switch (cbBitacora.getSelectionModel().getSelectedItem()) {

                    case "Cambios":
                        cambiarTablaCambios();
                        break;

                    case "Defectos":
                        cambiarTablaDefectos();
                        break;

                    case "Solicitudes de cambio":
                        cambiarTablaSolicitudes();
                        break;

                    case "Actividades":
                        cambiarTablaActividades();
                        break;

                }

            }

        };

        cbBitacora.getSelectionModel().selectedItemProperty().addListener(
                cambiarTabla);
        cbEstudiante.getSelectionModel().selectedItemProperty().addListener(
                cambiarEstudiante);

        cbBitacora.getSelectionModel().selectFirst();

    }

    private void cargarEstudiantes() {

        HashMap<String, Object> respuesta = EstudianteDAO
                .consultarEstudiantesActivosProyecto(idProyecto);

        if (!(Boolean) respuesta.get("error")) {

            listaEstudiantes = FXCollections.observableArrayList();

            listaEstudiantes.addAll((ArrayList<Estudiante>) respuesta.get(
                    "estudiantes"));
            cbEstudiante.setItems(listaEstudiantes);
            cbEstudiante.getSelectionModel().selectFirst();

        } else {

            Alertas.mostrarAlerta("Error",
                    respuesta.get("mensaje").toString(),
                    AlertType.ERROR);

            Stage escenario = (Stage) vboxBitacorasEstudiante.getScene()
                    .getWindow();

            escenario.close();

        }

    }

}
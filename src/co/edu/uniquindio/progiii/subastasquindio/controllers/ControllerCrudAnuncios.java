package co.edu.uniquindio.progiii.subastasquindio.controllers;

import co.edu.uniquindio.progiii.subastasquindio.exceptions.CannotSelectPujaGanadora;
import co.edu.uniquindio.progiii.subastasquindio.exceptions.FileNotFoundException;
import co.edu.uniquindio.progiii.subastasquindio.exceptions.PostNotFoundException;
import co.edu.uniquindio.progiii.subastasquindio.exceptions.TooManyPostsException;
import co.edu.uniquindio.progiii.subastasquindio.model.*;
import co.edu.uniquindio.progiii.subastasquindio.services.DirPicker;
import co.edu.uniquindio.progiii.subastasquindio.services.FilePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerCrudAnuncios implements Initializable {

    SingletonController control = SingletonController.getInstance();

    // Estas dos variables se usan para actualizar la vista desde el mismo controlador
    URL urlGlobal;
    ResourceBundle rbGlobal;

    @FXML
    private ChoiceBox<Articulo> choiceArticulo;

    @FXML
    private ChoiceBox<Puja> choicePujaGanadora;
    @FXML
    private ChoiceBox<Estado> choiceEstado;

    @FXML
    private DatePicker dateFinal;

    @FXML
    private DatePicker dateInicial;

    @FXML
    private ListView<Publicacion> listViewAnuncios;

    @FXML
    private TextField txtValorInicial;

    @FXML
    private Label lblInfo;

    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnGuardarAnuncios;

    @FXML
    private void nuevo() {
        if (listViewAnuncios.getItems().size() == 3){
            // Guardamos la casa subastas para que todas las ventanas estén actualizadas
			control.serializarXMLServidor();
            try {

                lblInfo.setText("Tienes 3 publicaciones, borra una para crearla");
                throw new TooManyPostsException("El usuario "+control.getUsuarioLogeado().getNombreUsuario()+" tiene 3 pujas existentes");
            } catch (TooManyPostsException e) {
            }
        }
        else{
            lblInfo.setText("");
            // Se crea un nuevo anuncio, para ello ve que todos los campos necesitados no estén vacíos
            // Las fechas se ponen en texto para así no tener problemas luego con la persistencia
            if (choiceArticulo.getValue() != null && dateInicial.getAccessibleText() != "" && dateFinal.getAccessibleText() != "" && txtValorInicial.getText() != "" && choiceEstado.getValue() != null) {
                Publicacion publicacion = new Publicacion(dateInicial.getValue().format(DateTimeFormatter.ISO_DATE), dateFinal.getValue().format(DateTimeFormatter.ISO_DATE), Integer.parseInt(txtValorInicial.getText()), null, choiceEstado.getValue(), choiceArticulo.getValue());
                control.registrarPublicacion(publicacion);
                // Añadimos la publicación al ListView
                listViewAnuncios.getItems().add(publicacion);
                SingletonController.guardarCambiosCrudLog("Se ha creado un nuevo anuncio por " + control.getUsuarioLogeado().getNombreUsuario(), "Artículo anunciado: " + choiceArticulo.getValue().getNombre());
                // Guardamos la casa subastas para que todas las ventanas estén actualizadas
				control.serializarXMLServidor();
            }
            else{
                lblInfo.setText("Tienes que llenar todos los campos");
            }

        }

        this.initialize(urlGlobal, rbGlobal);

    }

    @FXML
    private void editar() {

    }

    @FXML
    private void eliminar() {

        // Obtenemos el usuario logueado desde el singleton
        Vendedor vendedor = (Vendedor) control.getUsuarioLogeado();
        // Obtenemos las publicaciones tanto del vendedor, como las guardadas en la casa subastas
        // Esto se hace porque son listas independientes y deben estar acordes la una con la otra
        ArrayList<Publicacion> publicaciones = vendedor.getPublicaciones();
        ArrayList<Publicacion> publicacionesGlobales = control.subastasQuindio.getListaPublicaciones();
        Publicacion publicacionSeleccionada = listViewAnuncios.getSelectionModel().getSelectedItem();

        for (int i = 0; i < publicaciones.size(); i++) {
            // Eliminamos la publicación seleccionada de la lista del vendedor
            if (publicaciones.get(i).getArticulo().getNombre().equals(publicacionSeleccionada.getArticulo().getNombre())) {
                publicaciones.remove(i);
                listViewAnuncios.getItems().clear();
                choiceArticulo.getItems().clear();
                // Al eliminar toca actualizar la ventana, por lo que se llama de nuevo al
                // método initialize y se le mandan los parámetros que se obtienen desde el
                // mismo initialize
            }

        }
        for (int i = 0; i < publicacionesGlobales.size(); i++) {
            // Eliminamos la publicación seleccionada de la lista de la casa de subastas
            if (publicacionesGlobales.get(i).getArticulo().getDescripcion().equals(publicacionSeleccionada.getArticulo().getDescripcion()) && publicacionesGlobales.get(i).getArticulo().getNombre().equals(publicacionSeleccionada.getArticulo().getNombre())) {
                publicacionesGlobales.remove(i);
            }

        }
        this.initialize(urlGlobal, rbGlobal);
    }

    @FXML
    private void guardarCambios() {
        // Método que guarda la puja ganadora
        Puja pujaGanadora = choicePujaGanadora.getValue();
        if (pujaGanadora != null) {
            setPujaGanadora(pujaGanadora);
            this.initialize(urlGlobal, rbGlobal);
        }
    }

    @FXML
    private void atras() throws IOException {
        control.setAnunciosStage((Stage) txtValorInicial.getScene().getWindow());
        control.atrasAnuncios();
    }

    @FXML
    private void verPujas() {

    }

    @FXML
    void selectionListView(MouseEvent event) {
        // Este método lo que hace es escuchar el evento del click para obtener la publicación
        // seleccionada por el usuario gráficamente para actualizar el ChoiceBox para obtener las pujas
        // y así poder seleccionar la puja ganadora
        choicePujaGanadora.getItems().clear(); // Limpia los que están para no haber repetidos
        Publicacion publicacionSeleccionada = listViewAnuncios.getSelectionModel().getSelectedItem();
        ArrayList<Publicacion> publicaciones = control.subastasQuindio.getListaPublicaciones();
        if (publicacionSeleccionada != null) {
            for (Publicacion publicacion : publicaciones) {
                if (publicacion.equals(publicacionSeleccionada)) {
                    System.out.println(publicacion.getPujas());
                    choicePujaGanadora.getItems().addAll(publicacion.getPujas());
                }
            }

        }
    }

    @FXML
    void addPujaGanadora(MouseEvent event) {

    }

    private void setPujaGanadora(Puja puja) {
        // Método que setea la puja en la publicación seleccionada y cambia el estado de tal
        // puja a en espera
        Publicacion publicacionSeleccionada = listViewAnuncios.getSelectionModel().getSelectedItem();
        if (publicacionSeleccionada.getPujaGanadora()==null){
            ArrayList<Publicacion> publicaciones = control.subastasQuindio.getListaPublicaciones();
            if (publicacionSeleccionada != null) {
                for (Publicacion publicacion : publicaciones) {
                    if (publicacion.equals(publicacionSeleccionada)) {
                        publicacion.setPujaGanadora(puja);
                        publicacion.setEstado(Estado.VENDIDO);
                        publicacionSeleccionada.setPujaGanadora(puja);
                        publicacionSeleccionada.setEstado(Estado.VENDIDO);
                        crearTransaccion(publicacionSeleccionada, puja);
                    }
                }

            }
        }
        else{
            lblInfo.setText("Este artículo ya está vendido");
            try {
                throw new CannotSelectPujaGanadora("No se ha podido seleccionar la puja porque el artículo está vendido");
            } catch (CannotSelectPujaGanadora e) {
            }
        }

    }

    private void crearTransaccion(Publicacion publicacionSeleccionada, Puja puja) {
        control.registrarTransaccion(publicacionSeleccionada, puja);
    }

    // TRANSACCION DE PRUEBA !!
    // USA UNA 
    @FXML
    private void generarTransaccion() throws IOException {

        // Método provisional que genera una transacción de prueba
        // Nota: ya se solucionó lo de la excepción

        Comprador comprador = new Comprador();
        comprador.setNombreUsuario("Comprador123");

        Vendedor vendedor = new Vendedor();
        vendedor.setNombreUsuario("Vendedor123");
        Articulo articulo = new Articulo();
        articulo.setNombre("Articulo de prueba");
        Publicacion publicacion = new Publicacion();
        publicacion.setArticulo(articulo);
        control.crearTransaccion(comprador, vendedor, publicacion);

    }

    @FXML
    private void nuevoArticulo() {
        control.setAnunciosStage((Stage) txtValorInicial.getScene().getWindow());
        control.subastasQuindio.setUsuarioLogeado(control.subastasQuindio.getUsuarioLogeado());
        control.openCrudArticulos();
        control.closeVentana();

    }

    @FXML
    private void guardarAnunciosCSV() throws Exception {
        String dir = escogerDireccion();
        control.guardarAnunciosCSV(dir);
    }

    private String escogerDireccion() throws Exception {
        String dir = "";
        // método que crea un filePicker para que el usuario pueda escoger
        // la imagen
        DirPicker filePicker = new DirPicker();
        try {
            File direccion = filePicker.getDireccionArchivo();
            if (direccion == null) {
                throw new FileNotFoundException("Seleccione una dirección válida");
            } else {
                dir=direccion.getParent();
                System.out.println(dir);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return dir;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.urlGlobal = url;
        this.rbGlobal = resourceBundle;
        btnEliminar.setDisable(true);
        listViewAnuncios.getItems().clear();
        choiceEstado.getItems().clear();
        Vendedor vendedor = (Vendedor) control.subastasQuindio.getUsuarioLogeado();
        control.subastasQuindio.setUsuarioLogeado(vendedor);
        choiceArticulo.getItems().addAll(vendedor.getArticulos());
        System.out.println(vendedor.getArticulos());
        choiceEstado.getItems().add(Estado.ACTIVO);
        choiceEstado.getItems().add(Estado.VENDIDO);
        choiceEstado.getItems().add(Estado.INACTIVO);
        choiceEstado.getItems().add(Estado.EN_ESPERA_PUJA_GANADORA);
        listViewAnuncios.getItems().addAll(vendedor.getPublicaciones());
        if (listViewAnuncios.getItems().size() >=1){
            btnEliminar.setDisable(false);
        }

    }

}

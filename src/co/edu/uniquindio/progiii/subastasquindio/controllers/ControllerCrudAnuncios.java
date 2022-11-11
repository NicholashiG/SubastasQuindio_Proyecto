package co.edu.uniquindio.progiii.subastasquindio.controllers;

import co.edu.uniquindio.progiii.subastasquindio.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private void nuevo() {
        // Se crea un nuevo anuncio, para ello ve que todos los campos necesitados no estén vacíos
        // Las fechas se ponen en texto para así no tener problemas luego con la persistencia
        if (choiceArticulo.getValue() != null && dateInicial.getAccessibleText() != "" && dateFinal.getAccessibleText() != "" && txtValorInicial.getText() != "" && choiceEstado.getValue() != null) {
            Publicacion publicacion = new Publicacion(dateInicial.getValue().format(DateTimeFormatter.ISO_DATE), dateFinal.getValue().format(DateTimeFormatter.ISO_DATE), Integer.parseInt(txtValorInicial.getText()), null, choiceEstado.getValue(), choiceArticulo.getValue());
            control.registrarPublicacion(publicacion);
            // Añadimos la publicación al ListView
            listViewAnuncios.getItems().add(publicacion);
            SingletonController.guardarCambiosCrudLog("Se ha creado un nuevo anuncio por " + control.getUsuarioLogeado().getNombreUsuario(), "Artículo anunciado: " + choiceArticulo.getValue().getNombre());
            try {
                // Guardamos la casa subastas para que todas las ventanas estén actualizadas
                control.guardarCasaSubastasXML(control.subastasQuindio);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


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
                // Al eliminar toca actualizar la ventana, por lo que se llama de nuevo al
                // método initialize y se le mandan los parámetros que se obtienen desde el
                // mismo initialize
                this.initialize(urlGlobal, rbGlobal);
            }

        }
        for (int i = 0; i < publicacionesGlobales.size(); i++) {
            // Eliminamos la publicación seleccionada de la lista de la casa de subastas
            if (publicacionesGlobales.get(i).getArticulo().getDescripcion().equals(publicacionSeleccionada.getArticulo().getDescripcion()) && publicacionesGlobales.get(i).getArticulo().getNombre().equals(publicacionSeleccionada.getArticulo().getNombre())) {
                publicacionesGlobales.remove(i);
            }

        }

    }

    @FXML
    private void guardarCambios() {

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
        // Método que cada que haya un click dentro del ChoiceBox de puja,
        // es decir, cada que se seleccione una puja ganadora, haga un set de la puja
        // en la publicación seleccionada
        Puja pujaGanadora = choicePujaGanadora.getValue();
        if (pujaGanadora != null) {
            setPujaGanadora(pujaGanadora);
        }
    }

    private void setPujaGanadora(Puja puja) {
        // Método que setea la puja en la publicación seleccionada y cambia el estado de tal
        // puja a en espera
        Publicacion publicacionSeleccionada = listViewAnuncios.getSelectionModel().getSelectedItem();
        ArrayList<Publicacion> publicaciones = control.subastasQuindio.getListaPublicaciones();
        if (publicacionSeleccionada != null) {
            for (Publicacion publicacion : publicaciones) {
                if (publicacion.equals(publicacionSeleccionada)) {
                    publicacion.setPujaGanadora(puja);
                    publicacion.setEstado(Estado.EN_ESPERA_PUJA_GANADORA);
                    publicacionSeleccionada.setPujaGanadora(puja);
                    publicacionSeleccionada.setEstado(Estado.EN_ESPERA_PUJA_GANADORA);
                }
            }

        }
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
        SingletonController.crearTransaccion(comprador, vendedor, publicacion);

    }

    @FXML
    private void nuevoArticulo() {
        control.setAnunciosStage((Stage) txtValorInicial.getScene().getWindow());
        control.subastasQuindio.setUsuarioLogeado(control.subastasQuindio.getUsuarioLogeado());
        control.openCrudArticulos();
        control.closeVentana();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.urlGlobal = url;
        this.rbGlobal = resourceBundle;
        Vendedor vendedor = (Vendedor) control.subastasQuindio.getUsuarioLogeado();
        control.subastasQuindio.setUsuarioLogeado(vendedor);
        choiceArticulo.getItems().addAll(vendedor.getArticulos());
        choiceEstado.getItems().add(Estado.ACTIVO);
        choiceEstado.getItems().add(Estado.VENDIDO);
        choiceEstado.getItems().add(Estado.INACTIVO);
        choiceEstado.getItems().add(Estado.EN_ESPERA_PUJA_GANADORA);
        listViewAnuncios.getItems().addAll(vendedor.getPublicaciones());

    }

}

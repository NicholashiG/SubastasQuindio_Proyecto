package co.edu.uniquindio.progiii.subastasquindio.controllers;

import co.edu.uniquindio.progiii.subastasquindio.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerCrudAnuncios implements Initializable {

    SingletonController control = SingletonController.getInstance();

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
    private void nuevo(){

        if(choiceArticulo.getValue() != null && dateInicial.getAccessibleText()!="" && dateFinal.getAccessibleText() != "" && txtValorInicial.getText() != "" && choiceEstado.getValue() != null){
            Publicacion publicacion = new Publicacion(dateInicial.getValue(),
                                                dateFinal.getValue(),
                                                Integer.parseInt(txtValorInicial.getText()),
                                                null, choiceEstado.getValue(),
                                                 choiceArticulo.getValue());
            control.registrarPublicacion(publicacion);
            listViewAnuncios.getItems().add(publicacion);
            SingletonController.guardarCambiosCrudLog("Se ha creado un nuevo anuncio por "+control.getUsuarioLogeado().getNombreUsuario(), "Artículo anunciado: "+choiceArticulo.getValue().getNombre());
            try {
                control.guardarCasaSubastasXML(control.subastasQuindio);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
    @FXML
    private void editar(){

    }
    @FXML
    private void eliminar(){

        Vendedor vendedor = (Vendedor) control.getUsuarioLogeado();
        ArrayList<Publicacion> publicaciones = vendedor.getPublicaciones();
        ArrayList<Publicacion> publicacionesGlobales = control.subastasQuindio.getListaPublicaciones();
        Publicacion publicacionSeleccionada = listViewAnuncios.getSelectionModel().getSelectedItem();
        for (int i = 0; i< publicaciones.size(); i++){
            if (publicaciones.get(i).getArticulo().getNombre().equals(publicacionSeleccionada.getArticulo().getNombre())){
                publicaciones.remove(i);
                listViewAnuncios.getItems().clear();
                this.initialize(urlGlobal, rbGlobal);
            }

        }
        for (int i = 0; i< publicacionesGlobales.size(); i++){
            if (publicacionesGlobales.get(i).getArticulo().getDescripcion().equals(publicacionSeleccionada.getArticulo().getDescripcion()) && publicacionesGlobales.get(i).getArticulo().getNombre().equals(publicacionSeleccionada.getArticulo().getNombre())){
                publicacionesGlobales.remove(i);
            }

        }

    }
    @FXML
    private void guardarCambios(){

    }
    @FXML
    private void atras() throws IOException {
        control.setAnunciosStage((Stage) txtValorInicial.getScene().getWindow());
        control.atrasAnuncios();
    }

    @FXML
    private void verPujas(){

    }

    @FXML
    void selectionListView(MouseEvent event) {
        choicePujaGanadora.getItems().clear();
        Publicacion publicacionSeleccionada = listViewAnuncios.getSelectionModel().getSelectedItem();
        ArrayList<Publicacion> publicaciones = control.subastasQuindio.getListaPublicaciones();
        if (publicacionSeleccionada != null){
            for (Publicacion publicacion: publicaciones){
                if (publicacion.equals(publicacionSeleccionada)){
                    System.out.println(publicacion.getPujas());
                    choicePujaGanadora.getItems().addAll(publicacion.getPujas());
                }
            }

        }
    }

    @FXML
    void addPujaGanadora(MouseEvent event) {
        Puja pujaGanadora = choicePujaGanadora.getValue();
        if(pujaGanadora != null){
            setPujaGanadora(pujaGanadora);
        }
    }

    private void setPujaGanadora(Puja puja){
        Publicacion publicacionSeleccionada = listViewAnuncios.getSelectionModel().getSelectedItem();
        ArrayList<Publicacion> publicaciones = control.subastasQuindio.getListaPublicaciones();
        if (publicacionSeleccionada != null){
            for (Publicacion publicacion: publicaciones){
                if (publicacion.equals(publicacionSeleccionada)){
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
    private void nuevoArticulo(){
        control.setAnunciosStage((Stage) txtValorInicial.getScene().getWindow());
        control.subastasQuindio.setUsuarioLogeado( control.subastasQuindio.getUsuarioLogeado());
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

    public URL getUrlGlobal() {
        return urlGlobal;
    }

    public void setUrlGlobal(URL urlGlobal) {
        this.urlGlobal = urlGlobal;
    }

    public ResourceBundle getRbGlobal() {
        return rbGlobal;
    }

    public void setRbGlobal(ResourceBundle rbGlobal) {
        this.rbGlobal = rbGlobal;
    }
}

package co.edu.uniquindio.progiii.subastasquindio.controllers;

import co.edu.uniquindio.progiii.subastasquindio.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerCrudAnuncios implements Initializable {

    SingletonController control = SingletonController.getInstance();

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
    private void nuevoArticulo(){
        control.setAnunciosStage((Stage) txtValorInicial.getScene().getWindow());
        control.subastasQuindio.setUsuarioLogeado( control.subastasQuindio.getUsuarioLogeado());
        control.openCrudArticulos();
        control.closeVentana();

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

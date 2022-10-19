package co.edu.uniquindio.progiii.subastasquindio.controllers;

import co.edu.uniquindio.progiii.subastasquindio.model.Articulo;
import co.edu.uniquindio.progiii.subastasquindio.model.TipoPublicacion;
import co.edu.uniquindio.progiii.subastasquindio.services.FilePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCrudArticulos implements Initializable {


    @FXML
    private ChoiceBox<TipoPublicacion> choiceTipo;

    @FXML
    private ListView<Articulo> listViewArticulos;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtNombre;

    @FXML
    private void nuevo(){



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
    private void atras(){

    }

    @FXML
    private void escogerImagen() throws Exception {
        FilePicker filePicker = new FilePicker();
        System.out.println(filePicker.getDireccionArchivo());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiceTipo.getItems().add(TipoPublicacion.BIEN_RAIZ);
        choiceTipo.getItems().add(TipoPublicacion.DEPORTES);
        choiceTipo.getItems().add(TipoPublicacion.HOGAR);
        choiceTipo.getItems().add(TipoPublicacion.TECNOLOGIA);
        choiceTipo.getItems().add(TipoPublicacion.VEHICULOS);

    }

}

package co.edu.uniquindio.progiii.subastasquindio.controllers;

import co.edu.uniquindio.progiii.subastasquindio.exceptions.FileNotFoundException;
import co.edu.uniquindio.progiii.subastasquindio.model.Articulo;
import co.edu.uniquindio.progiii.subastasquindio.model.TipoPublicacion;
import co.edu.uniquindio.progiii.subastasquindio.model.Usuario;
import co.edu.uniquindio.progiii.subastasquindio.model.Vendedor;
import co.edu.uniquindio.progiii.subastasquindio.services.FilePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCrudArticulos implements Initializable {

    SingletonController control = SingletonController.getInstance();
    @FXML
    private ChoiceBox<TipoPublicacion> choiceTipo;

    @FXML
    private ListView<Articulo> listViewArticulos;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtNombre;
    @FXML
    private Label txtRutaArchivo;
    @FXML
    private Button btnEscogerImg;

    @FXML
    private void nuevo(){

        if(choiceTipo.getValue() != null && txtDescripcion.getText() != "" && txtNombre.getText() != "" && txtRutaArchivo.getText() != ""){
            File img = new File(txtRutaArchivo.getText());
            Vendedor vendedor = (Vendedor) SingletonController.getInstance().subastasQuindio.getUsuarioLogeado();
            Articulo articulo = new Articulo(txtNombre.getText(),
                                            choiceTipo.getValue(),
                                            txtDescripcion.getText(),
                                            img, vendedor);
            control.registrarArticulo(articulo);
            listViewArticulos.getItems().add(articulo);
            control.setMainStage((Stage) btnEscogerImg.getScene().getWindow());
            control.openCrudAnuncios();
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
    private void atras(){

    }

    @FXML
    private void escogerImagen() throws Exception {
        FilePicker filePicker = new FilePicker();
        try {
            File direccion = filePicker.getDireccionArchivo();
            if (direccion==null){
                txtRutaArchivo.setText("Seleccione un archivo");
                throw new FileNotFoundException("Seleccione un archivo válido");
            }
            else{
                txtRutaArchivo.setText(direccion.toString());
                btnEscogerImg.setText(direccion.getName());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Vendedor vendedor = new Vendedor();
        control.subastasQuindio.setUsuarioLogeado(vendedor);
        choiceTipo.getItems().add(TipoPublicacion.BIEN_RAIZ);
        choiceTipo.getItems().add(TipoPublicacion.DEPORTES);
        choiceTipo.getItems().add(TipoPublicacion.HOGAR);
        choiceTipo.getItems().add(TipoPublicacion.TECNOLOGIA);
        choiceTipo.getItems().add(TipoPublicacion.VEHICULOS);
        listViewArticulos.getItems().addAll(vendedor.getArticulos());
    }

}

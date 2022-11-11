package co.edu.uniquindio.progiii.subastasquindio.controllers;

import co.edu.uniquindio.progiii.subastasquindio.application.Main;
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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerCrudArticulos implements Initializable {

    SingletonController control = SingletonController.getInstance();

    URL urlGlobal;

    ResourceBundle rbGlobal;
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
    private Button btnEliminar;

    @FXML
    private void nuevo() throws IOException {

        if (choiceTipo.getValue() != null && txtDescripcion.getText() != "" && txtNombre.getText() != "" && txtRutaArchivo.getText() != "") {
            File img = new File(txtRutaArchivo.getText());
            Vendedor vendedor = (Vendedor) SingletonController.getInstance().subastasQuindio.getUsuarioLogeado();
            Articulo articulo = new Articulo(txtNombre.getText(),
                    choiceTipo.getValue(),
                    txtDescripcion.getText(),
                    img.getAbsolutePath(), vendedor);
            control.registrarArticulo(articulo);
            listViewArticulos.getItems().add(articulo);
            control.getSubastasQuindio().setUsuarioLogeado(control.getUsuarioLogeado());
            control.setArticuloStage((Stage) txtDescripcion.getScene().getWindow());
            control.nuevoArticuloRefresh();
            SingletonController.guardarCambiosCrudLog("Se ha creado un nuevo artículo por " + control.getUsuarioLogeado().getNombreUsuario(), "Artículo nuevo: " + txtNombre.getText());

            try {
                control.guardarCasaSubastasXML(control.subastasQuindio);
                control.guardarArticulos(articulo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("No está completo");
        }

        this.initialize(urlGlobal, rbGlobal);

    }

    @FXML
    private void editar() {

    }

    @FXML
    private void eliminar() {
        Vendedor vendedor = (Vendedor) control.getUsuarioLogeado();
        ArrayList<Articulo> articulos = vendedor.getArticulos();
        Articulo articuloSeleccionado = listViewArticulos.getSelectionModel().getSelectedItem();
        for (int i = 0; i < articulos.size(); i++) {
            if (articulos.get(i).getNombre().equals(articuloSeleccionado.getNombre())) {
                articulos.remove(i);
                listViewArticulos.getItems().clear();
                // actualiza la ventana
            }

        }
        this.initialize(urlGlobal, rbGlobal);

    }

    @FXML
    private void guardarCambios() {

    }

    @FXML
    private void atras() throws IOException {
        control.setArticuloStage((Stage) txtDescripcion.getScene().getWindow());
        control.atrasArticulos();
    }

    @FXML
    private void escogerImagen() throws Exception {

        // método que crea un filePicker para que el usuario pueda escoger
        // la imagen
        FilePicker filePicker = new FilePicker();
        try {
            File direccion = filePicker.getDireccionArchivo();
            if (direccion == null) {
                txtRutaArchivo.setText("Seleccione un archivo");
                throw new FileNotFoundException("Seleccione un archivo válido");
            } else {
                txtRutaArchivo.setText(direccion.getAbsolutePath());
                btnEscogerImg.setText(direccion.getName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.urlGlobal = url;
        this.rbGlobal = resourceBundle;
        btnEliminar.setDisable(true);
        choiceTipo.getItems().clear();
        Vendedor vendedor = (Vendedor) control.subastasQuindio.getUsuarioLogeado();
        choiceTipo.getItems().add(TipoPublicacion.BIEN_RAIZ);
        choiceTipo.getItems().add(TipoPublicacion.DEPORTES);
        choiceTipo.getItems().add(TipoPublicacion.HOGAR);
        choiceTipo.getItems().add(TipoPublicacion.TECNOLOGIA);
        choiceTipo.getItems().add(TipoPublicacion.VEHICULOS);
        listViewArticulos.getItems().addAll(vendedor.getArticulos());
        if (listViewArticulos.getItems().size() >=1){
            btnEliminar.setDisable(false);
        }
    }

}

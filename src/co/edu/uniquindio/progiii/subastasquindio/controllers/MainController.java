package co.edu.uniquindio.progiii.subastasquindio.controllers;

import co.edu.uniquindio.progiii.subastasquindio.exceptions.InvalidBidException;
import co.edu.uniquindio.progiii.subastasquindio.model.Comprador;
import co.edu.uniquindio.progiii.subastasquindio.model.Publicacion;
import co.edu.uniquindio.progiii.subastasquindio.model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.progiii.subastasquindio.model.Usuario;

public class MainController implements Initializable {

    SingletonController control = SingletonController.getInstance();


    @FXML
    private AnchorPane anchorPaneArticuloSelec;

    @FXML
    private Text bienvenida;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Button btnPujar;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private Button btnVerAnuncios;

    @FXML
    private Button btnVerOfertas;

    @FXML
    private Hyperlink hyperlinkRegistroVendedor;

    @FXML
    private Label lblDescripcion;

    @FXML
    private Label lblInfo;

    @FXML
    private Label lblNombreArticulo;

    @FXML
    private Label lblVendedor;

    @FXML
    private Label lblPuja1;

    @FXML
    private Label lblPuja2;

    @FXML
    private Label lblPuja3;

    @FXML
    private Label lblPuja4;

    @FXML
    private Label lblPuja5;

    @FXML
    private Label lblTipoArticulo;
    @FXML
    private Label lblValorPujaInfo;

    @FXML
    private ListView<Publicacion> listViewInicio;

    @FXML
    private ImageView photoArticulo;

    @FXML
    private TextField txtValorPuja;

    @Override

    // ESTA FUNCION SE EJECUTA AL INCIO DE LA APLICACION

    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Se inicia la aplicación");
        System.out.println("Se cargan los anuncios");
        btnVerAnuncios.setVisible(false);
        anchorPaneArticuloSelec.setVisible(false);


        Usuario usuarioLogeado = control.getUsuarioLogeado();
        // Pregunta si hay un usuario logeado
        if (usuarioLogeado != null) {
            // si esta logeado hace invisible los botones
            // de registro y demas.
            btnIniciarSesion.setVisible(false);
            btnRegistrarse.setVisible(false);
            hyperlinkRegistroVendedor.setVisible(false);
            bienvenida.setText("Bienvenido, " + usuarioLogeado.getNombreUsuario() + "!");
            if (usuarioLogeado.getClass() == Vendedor.class) {
                // si es vendedor agrega opciones propias del vendedor
                btnVerAnuncios.setVisible(true);
            }
        }

        // SERIALIZACION AL INICIAR.

        try {
            control.setSubastasQuindio(control.cargarCasaSubastasAnunciosXML());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            control.guardarCasaSubastasXML(control.subastasQuindio);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            control.guardarCasaSubastasXMLRespaldo(control.subastasQuindio);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            control.setSubastasQuindio(control.cargarCasaSubastasAnunciosXML());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        listViewInicio.getItems().addAll(control.subastasQuindio.getListaPublicaciones());
        System.out.println(control.subastasQuindio.getListaPublicaciones());


    }

    @FXML
    void pujar(ActionEvent event) {
        // Acción cuando no está logueado
        // compara si el botón de iniciar sesión está visible, esto nos dice que no hay un
        // usuario que haya iniciado sesión
        if (btnIniciarSesion.isVisible()) {
            lblValorPujaInfo.setText("Debes estar logeado para hacer esto");
            try {
                throw new InvalidBidException("No está logueado");
            } catch (InvalidBidException e) {
            }
        }
        // Acción cuando sí está logueado
        // como el botón de iniciar sesión no está visible, hay un usuario logueado, por lo que
        // puede pujar
        else {
            int pujaRealizada = control.registrarPuja(Integer.parseInt(txtValorPuja.getText()));
            // compara si la puja fue realizada con éxito o hubo un error y lo muestra en la pantalla
            /* Valores de condiciones: 1. Correcto
                                       2. Error usuario vendedor
                                       3. Error muchas pujas
            */
            if (pujaRealizada == 1) {
                lblValorPujaInfo.setText("La puja se realizó con éxito :D");
            } else {
                if (pujaRealizada == 2) {
                    lblValorPujaInfo.setText("No se ha podido realizar la puja :(, cambia a comprador");
                } else {
                    if (pujaRealizada == 3) {
                        lblValorPujaInfo.setText("No se ha podido realizar la puja, tienes muchas pujas hechas");
                    }
                }
            }
        }
    }

    @FXML
    void selectionListView(MouseEvent event) {
        // Cada que haya una acción en el listView, se va a actualizar la publicación seleccionada
        Publicacion publicacion = listViewInicio.getSelectionModel().getSelectedItem();
        // se añade la publicación seleccionada en el singleton para hacer procesos desde otras ventanas
        control.subastasQuindio.setPublicacionSeleccionada(publicacion);
        if (publicacion != null) {
            System.out.println(publicacion);
            lblInfo.setVisible(false);
            anchorPaneArticuloSelec.setVisible(true);
            lblNombreArticulo.setText(publicacion.getArticulo().getNombre());
            lblVendedor.setText("Vendido por: " + publicacion.getArticulo().getVendedor().getNombreUsuario());
            lblDescripcion.setText(publicacion.getArticulo().getDescripcion());
            try {
                // Si hay una foto existente en el artículo, se pone tal foto
                String dir = publicacion.getArticulo().getFoto();
                if (dir != null) {
                    File file = new File(dir);
                    Image image = new Image(file.toURI().toString());
                    photoArticulo.setImage(image);
                    // de lo contrario, se pone una foto por default
                } else {
                    Image image1 = new Image(getClass().getResourceAsStream("../view/images/DefaultImg.jpeg"));
                    photoArticulo.setImage(image1);
                }

            } catch (NullPointerException e) {
            }
            lblTipoArticulo.setText(publicacion.getArticulo().getTipo().toString().toLowerCase());
            try {
                // se añaden 5 pujas en la pantalla
                // La primera puja es la última puja hecha
                // La segunda, cuarta y quinta son pujas al azar y, en caso de no existir, se deja vacío
                // la tercera puja será la primera puja hecha a esa publicación
                lblPuja1.setText("Puja 1: " + String.valueOf(publicacion.getPujas().get(publicacion.getPujas().size() - 1)));
                lblPuja2.setText("Puja 2: " + String.valueOf(publicacion.getPujas().get((int) (Math.random() * 10))));
                lblPuja3.setText("Puja 3: " + String.valueOf(publicacion.getPujas().get(0)));
                lblPuja4.setText("Puja 4: " + String.valueOf(publicacion.getPujas().get((int) (Math.random() * 10))));
                lblPuja5.setText("Puja 5: " + String.valueOf(publicacion.getPujas().get((int) (Math.random() * 10))));
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        }
    }

    @FXML
    void txtPujaFilled(KeyEvent event) {
        // Este método compara si el campo de la puja está lleno y con valores numéricos
        // en caso de que eso se cumpla, el botón se activa
        if (!txtValorPuja.getText().equals("") && isNumeric(txtValorPuja.getText())) {
            btnPujar.setDisable(false);
            lblValorPujaInfo.setText("");
        } else {
            if (!isNumeric(txtValorPuja.getText())) {
                lblValorPujaInfo.setText("Debe ingresar valores numéricos");
            }
            btnPujar.setDisable(true);
        }
    }


    public void iniciarSesion() {
        // ENVIO EL STAGE AL SINGLETON
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
        control.openLogin();
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
    }

    public void registrarse() {
        // ENVIO EL STAGE AL SINGLETON
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
        control.openRegistro();
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
    }

    public void registrarseVendedor() {
        // ENVIO EL STAGE AL SINGLETON
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
        control.openRegistroVendedores();
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
    }

    public void verAnuncios() {
        // ENVIO EL STAGE AL SINGLETON
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
        control.openCrudAnuncios();
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());

    }

    // Mira si es numérico (sacado de internet, está joya)
    private boolean isNumeric(String txt) {
        boolean num = txt.matches("[+-]?\\d*(\\.\\d+)?");
        return num;
    }
}
 
package co.edu.uniquindio.progiii.subastasquindio.controllers;

import co.edu.uniquindio.progiii.subastasquindio.exceptions.InvalidBidException;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.progiii.subastasquindio.model.Usuario;

import static co.edu.uniquindio.progiii.subastasquindio.persistencia.Persistencia.guardarPublicaciones;

public class MainController implements Initializable{

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
    if (usuarioLogeado != null)   {
    		// si esta logeado hace invisible los botones
    		// de registro y demas.
    		btnIniciarSesion.setVisible(false);
    		btnRegistrarse.setVisible(false);
            hyperlinkRegistroVendedor.setVisible(false);
    		bienvenida.setText("Bienvenido, " + usuarioLogeado.getNombreUsuario() + "!");
            if (usuarioLogeado.getClass() == Vendedor.class){
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
        if(btnIniciarSesion.isVisible()){
            lblValorPujaInfo.setText("Debes estar logeado para hacer esto");
            try {
                throw new InvalidBidException("No está logueado");
            } catch (InvalidBidException e) {
            }
        }
        // Acción cuando sí está logueado
        else{
            control.registrarPuja(Integer.parseInt(txtValorPuja.getText()));
        }
    }
    @FXML
    void selectionListView(MouseEvent event) {
        Publicacion publicacion = listViewInicio.getSelectionModel().getSelectedItem();
        control.subastasQuindio.setPublicacionSeleccionada(publicacion);
        if (publicacion != null){
            System.out.println(publicacion);
            lblInfo.setVisible(false);
            anchorPaneArticuloSelec.setVisible(true);
            lblNombreArticulo.setText(publicacion.getArticulo().getNombre());
            lblVendedor.setText("Vendido por: "+publicacion.getArticulo().getVendedor().getNombreUsuario());
            lblDescripcion.setText(publicacion.getArticulo().getDescripcion());
            //photoArticulo.setImage(new Image(publicacion.getArticulo().getFoto()));
            lblTipoArticulo.setText(publicacion.getArticulo().getTipo().toString().toLowerCase());
            try {
                lblPuja1.setText("Puja 1: " + String.valueOf(publicacion.getPujas().get(publicacion.getPujas().size()-1)));
                lblPuja2.setText("Puja 2: " + String.valueOf(publicacion.getPujas().get((int) (Math.random()*10))));
                lblPuja3.setText("Puja 3: " + String.valueOf(publicacion.getPujas().get(0)));
                lblPuja4.setText("Puja 4: " + String.valueOf(publicacion.getPujas().get((int) (Math.random()*10))));
                lblPuja5.setText("Puja 5: " + String.valueOf(publicacion.getPujas().get((int) (Math.random()*10))));
            }catch (Exception e){
                System.out.println(e.toString());
            }

        }
    }
    @FXML
    void txtPujaFilled(KeyEvent event) {
        if(!txtValorPuja.getText().equals("") && isNumeric(txtValorPuja.getText())){
            btnPujar.setDisable(false);
            lblValorPujaInfo.setText("");
        }
        else{
            if (!isNumeric(txtValorPuja.getText())){
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

    public void verAnuncios(){
    	// ENVIO EL STAGE AL SINGLETON
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
        control.openCrudAnuncios();
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());

    }

    // Mira si es numérico (sacado de internet, está joya)
    private boolean isNumeric (String txt){
        boolean num =  txt.matches("[+-]?\\d*(\\.\\d+)?");
        return num;
    }
}
 
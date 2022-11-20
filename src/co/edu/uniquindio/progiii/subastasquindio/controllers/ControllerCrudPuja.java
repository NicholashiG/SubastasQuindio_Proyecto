package co.edu.uniquindio.progiii.subastasquindio.controllers;

import co.edu.uniquindio.progiii.subastasquindio.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class ControllerCrudPuja implements Initializable {


    SingletonController control = SingletonController.getInstance();

    URL urlGlobal;

    ResourceBundle rbGlobal;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardarCambios;

    @FXML
    private Button btnNuevo;

    @FXML
    private ListView<Puja> listViewPujas;

    @FXML
    private Label txtEstadoPuja;

    @FXML
    private Label txtNamePub;

    @FXML
    private Label txtRutaArchivo;

    @FXML
    private Label txtValorPuja;

    @FXML
    void atras(ActionEvent event) throws IOException {
        control.setPujasStage((Stage) txtValorPuja.getScene().getWindow());
        control.atrasPujas();
    }


    @FXML
    void eliminar(ActionEvent event) {

        // Borra únicamente la puja del usuario, mas no de la publicación

        Comprador comprador = (Comprador) control.subastasQuindio.getUsuarioLogeado();
        ArrayList<Puja> pujas = comprador.getPujas();
        Puja pujaSeleccionada = listViewPujas.getSelectionModel().getSelectedItem();
        for (int i = 0; i < pujas.size(); i++) {
            if (pujas.get(i).getDineroOfrecido()==pujaSeleccionada.getDineroOfrecido() && pujas.get(i).getPublicacion().equals(pujaSeleccionada.getPublicacion())) {
            	
            	pujas.get(i).getPublicacion().getPujas().remove( pujas.get(i).getPublicacion().filtrarPuja(pujas.get(i)) );
            	pujas.remove(i);
                
                listViewPujas.getItems().clear();
                // actualiza la ventana
            }

        }
        this.initialize(urlGlobal, rbGlobal);
    }
 

    @FXML
    void guardarCambios(ActionEvent event) {

    }

    @FXML
    void selectionListView(MouseEvent event) {
        txtValorPuja.setText("Valor Puja: ");
        txtEstadoPuja.setText("Estado: ");
        txtNamePub.setText("Publicación: ");
        // Cada que haya una acción en el listView, se va a actualizar la publicación seleccionada
        Puja puja = listViewPujas.getSelectionModel().getSelectedItem();
        // se añade la publicación seleccionada en el singleton para hacer procesos desde otras ventanas
        if (puja != null) {
            txtNamePub.setText("Publicación: "+puja.getPublicacion().getArticulo().getNombre());
            try{
                Publicacion publicacion = puja.getPublicacion();
            }catch (NullPointerException e){
                System.out.println("La publicación no tiene pujas");
                txtValorPuja.setText("Valor Puja: *");
                txtEstadoPuja.setText("Estado: *");
                txtNamePub.setText("Esta publicación no tiene pujas");
            }
                Publicacion publicacion = puja.getPublicacion();
                try{
                    if (publicacion.getPujaGanadora().equals(this)){
                        txtEstadoPuja.setText("Estado: Escogieron esta puja como la ganadora!");
                    } else if (!publicacion.getPujaGanadora().equals(this)) {
                        txtEstadoPuja.setText("Estado: Escogieron otra puja como la ganadora");
                        puja.setGanadora(false);
                    }
            } catch (NullPointerException e){
                System.out.println(puja.getPublicacion());
                    txtEstadoPuja.setText("Estado: Aún no se ha escogido la puja ganadora");
                    puja.setGanadora(false);
            }

            txtValorPuja.setText("Valor Puja: "+puja.getDineroOfrecido());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.urlGlobal = url;
        this.rbGlobal = resourceBundle;
        Comprador comprador = (Comprador) control.subastasQuindio.getUsuarioLogeado();
        listViewPujas.getItems().addAll(comprador.getPujas());
        if (listViewPujas.getItems().size() >=1){
            btnEliminar.setDisable(false);
        }
    }
}

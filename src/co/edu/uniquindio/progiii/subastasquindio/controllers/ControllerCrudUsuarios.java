package co.edu.uniquindio.progiii.subastasquindio.controllers;

import co.edu.uniquindio.progiii.subastasquindio.model.Articulo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCrudUsuarios implements Initializable {

    @FXML
    private ChoiceBox<String> choiceTipoUsuario;

    @FXML
    private DatePicker datePickerFechaNacimiento;

    @FXML
    private ListView<Articulo> listViewArticulos;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPassword;


    @FXML
    private void nuevo() {

    }

    @FXML
    private void editar() {

    }

    @FXML
    private void eliminar() {

    }

    @FXML
    private void guardarCambios() {

    }

    @FXML
    private void atras() {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

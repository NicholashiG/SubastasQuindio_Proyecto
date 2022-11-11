package co.edu.uniquindio.progiii.subastasquindio.application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    // CARGA LA VENTANA INICAL
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/MainView.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ABRE LA VENTANA DE LOGIN
    public static void openLogin() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/Login.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ABRE LA VENTANA DE REGISTRO COMPRADORES
    public static void openRegistroUsuarios() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/Registro.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ABRE LA VENTANA DE REGISTRO VENDEDORES
    public static void openRegistroVendedores() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/RegistroVendedores.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // ABRE LA VENTANA DE CRUD ANUNCIOS
    public static void openCrudAnuncios() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/CrudAnuncios.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ABRE LA VENTANA DE CRUD ARTICULOS
    public static void openCrudArticulos() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/CrudArticulos.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Refresca la ventana de  main (Metodo temporal)
    public static void refreshMain(Stage mainStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/MainView.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Refresca la ventana de anuncios (Metodo temporal)
    public static void refreshAnuncios(Stage mainStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/CrudAnuncios.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Refresca la ventana de articulos (Metodo temporal)
    public static void refreshArticulos(Stage mainStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/CrudArticulos.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // CERRAR STAGES
    public static void closeWindow(Stage stage) {
        // TODO Auto-generated method stub
        stage.close();
    }


}

package co.edu.uniquindio.progiii.subastasquindio.application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import co.edu.uniquindio.progiii.subastasquindio.controllers.SingletonController;


public class Main extends Application {


	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/MainView.fxml"));
		try {
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setScene(scene);
            primaryStage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void openLogin() {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/Login.fxml"));
		try {
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void openRegistro() {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/Registro.fxml"));
		try {
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	
	//CAMBIAR LUEGO NO SÉ KAJSKRJD ESTO ES UN COPYPASTE
	public static void refreshMain(Stage mainStage) {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/MainView.fxml"));
		try {
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setScene(scene);
            mainStage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// ESTO LITERALMENTE CIERRA CUALQUIER VENTANA JAJAJA
	public static void closeLogin(Stage stage) {
		// TODO Auto-generated method stub
		stage.close();
	}

	

}

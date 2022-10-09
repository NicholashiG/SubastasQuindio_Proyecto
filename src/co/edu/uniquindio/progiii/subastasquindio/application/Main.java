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
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/MainView.fxml"));
	//	FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/CrudAnuncios.fxml"));
//		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/CrudArticulos.fxml"));
//		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/progiii/subastasquindio/view/CrudUsuarios.fxml"));
		try {
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setScene(scene);
            primaryStage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	

}

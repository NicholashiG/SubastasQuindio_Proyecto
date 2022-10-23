module SubastasQuindio_Proyecto{
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires java.logging;
	requires java.rmi;

	opens co.edu.uniquindio.progiii.subastasquindio.controllers to javafx.fxml;
	opens co.edu.uniquindio.progiii.subastasquindio.application to javafx.graphics, javafx.fxml;
	exports co.edu.uniquindio.progiii.subastasquindio.application;
	exports co.edu.uniquindio.progiii.subastasquindio.model;
}

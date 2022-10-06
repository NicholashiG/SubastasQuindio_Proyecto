module SubastasQuindio_Proyecto{
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;

	opens co.edu.uniquindio.progiii.subastasquindio.controllers to javafx.fxml;
	opens co.edu.uniquindio.progiii.subastasquindio.application to javafx.graphics, javafx.fxml;
	exports co.edu.uniquindio.progiii.subastasquindio.application;
}

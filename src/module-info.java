module SubastasQuindio_Proyecto{
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;

	opens controllers to javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
	exports application;
}

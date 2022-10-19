package co.edu.uniquindio.progiii.subastasquindio.services;

import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.rmi.server.ExportException;

public class FilePicker {

    FileChooser f = new FileChooser();

    public FilePicker() {
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    }
    public File getDireccionArchivo() {
        File direccion = f.showOpenDialog(null);
        return direccion;
    }

}
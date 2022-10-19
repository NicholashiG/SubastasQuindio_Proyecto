package co.edu.uniquindio.progiii.subastasquindio.services;

import javafx.stage.FileChooser;
import java.io.File;
public class FilePicker {

    FileChooser f = new FileChooser();
    public FilePicker() {
    }
    public File getDireccionArchivo(){
        File direccion = f.showOpenDialog(null);
        return direccion;
    }

}
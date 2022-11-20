package co.edu.uniquindio.progiii.subastasquindio.services;

import javafx.stage.FileChooser;

import java.io.File;

public class DirPicker {

    FileChooser f = new FileChooser();

    public DirPicker() {
    }
    public File getDireccionArchivo() {
        File direccion = f.showOpenDialog(null);
        return direccion;
    }

}
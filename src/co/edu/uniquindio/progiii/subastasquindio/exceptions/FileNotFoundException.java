package co.edu.uniquindio.progiii.subastasquindio.exceptions;

import co.edu.uniquindio.progiii.subastasquindio.controllers.SingletonController;

public class FileNotFoundException extends Exception{
	
    public FileNotFoundException() {
    }

    public FileNotFoundException(String message) {
        super(message);
        SingletonController.guardarExcepcion(message, this.toString());
    }
}

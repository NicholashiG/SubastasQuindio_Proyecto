package co.edu.uniquindio.progiii.subastasquindio.exceptions;

import co.edu.uniquindio.progiii.subastasquindio.controllers.SingletonController;

public class UsuarioException extends Exception{
    public UsuarioException() {
    }

    public UsuarioException(String message) {
        super(message);
        SingletonController.guardarExcepcion(message, this.toString());
    }
}

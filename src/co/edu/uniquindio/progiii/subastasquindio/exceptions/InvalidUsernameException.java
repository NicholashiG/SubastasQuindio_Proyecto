package co.edu.uniquindio.progiii.subastasquindio.exceptions;

// Excepcion cuando el nombre de usuario es invalido

import co.edu.uniquindio.progiii.subastasquindio.controllers.SingletonController;

public class InvalidUsernameException extends Exception{

	public InvalidUsernameException() {
		super();
	}

	public InvalidUsernameException(String message) {
		super(message);
		SingletonController.guardarExcepcion(message, this.toString());	}

}

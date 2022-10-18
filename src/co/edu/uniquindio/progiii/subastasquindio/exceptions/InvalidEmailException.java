package co.edu.uniquindio.progiii.subastasquindio.exceptions;

// Excepcion cuando el email es invalido

import co.edu.uniquindio.progiii.subastasquindio.controllers.SingletonController;

public class InvalidEmailException extends Exception{

	public InvalidEmailException() {
		super();
	}

	public InvalidEmailException(String message) {
		super(message);
		SingletonController.guardarExcepcion(message, this.toString());	}

}

package co.edu.uniquindio.progiii.subastasquindio.exceptions;

import co.edu.uniquindio.progiii.subastasquindio.controllers.SingletonController;

// Excepcion el usuario no existe
public class UserNotFoundException extends Exception{

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message) {
		super(message);
		SingletonController.guardarExcepcion(message, this.toString());
	}

}

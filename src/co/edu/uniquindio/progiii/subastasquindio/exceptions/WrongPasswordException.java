package co.edu.uniquindio.progiii.subastasquindio.exceptions;

import co.edu.uniquindio.progiii.subastasquindio.controllers.SingletonController;

// Excepcion la contraseña ingresada es invalida o equivocada
public class WrongPasswordException extends Exception{

	public WrongPasswordException() {
		super();
	}

	public WrongPasswordException(String message) {
		super(message);
		SingletonController.guardarExcepcion(message, this.toString());
	}

}

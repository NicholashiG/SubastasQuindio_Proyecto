package co.edu.uniquindio.progiii.subastasquindio.exceptions;

import co.edu.uniquindio.progiii.subastasquindio.controllers.SingletonController;

//Excepcion cuando el titulo del anuncio es invalido (null, "", etc)
public class InvalidPostTitleException extends Exception{

	public InvalidPostTitleException() {
		super();
	}

	public InvalidPostTitleException(String message) {
		super(message);
		SingletonController.guardarExcepcion(message, this.toString());
	}

}

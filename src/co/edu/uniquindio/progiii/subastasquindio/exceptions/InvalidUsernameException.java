package co.edu.uniquindio.progiii.subastasquindio.exceptions;

// Excepcion cuando el nombre de usuario es invalido

public class InvalidUsernameException extends Exception{

	public InvalidUsernameException() {
		super();
	}

	public InvalidUsernameException(String message) {
		super(message);
	}

}

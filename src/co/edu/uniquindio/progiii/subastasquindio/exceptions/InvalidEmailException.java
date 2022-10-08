package co.edu.uniquindio.progiii.subastasquindio.exceptions;

// Excepcion cuando el email es invalido

public class InvalidEmailException extends Exception{

	public InvalidEmailException() {
		super();
	}

	public InvalidEmailException(String message) {
		super(message);
	}

}

package co.edu.uniquindio.progiii.subastasquindio.exceptions;

// Excepcion cuando la fecha de la publicacion es invalidad (Fecha de ayer por ejemplo)

public class InvalidDateException extends Exception{

	public InvalidDateException() {
		super();
	}

	public InvalidDateException(String message) {
		super(message);
	}

}

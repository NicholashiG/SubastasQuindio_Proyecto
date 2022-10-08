package co.edu.uniquindio.progiii.subastasquindio.exceptions;

// Excepcion el usuario no existe
public class UserNotFoundException extends Exception{

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message) {
		super(message);
	}

}

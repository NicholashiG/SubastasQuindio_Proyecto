package co.edu.uniquindio.progiii.subastasquindio.exceptions;

// Excepcion la contraseña ingresada es invalida o equivocada
public class WrongPasswordException extends Exception{

	public WrongPasswordException() {
		super();
	}

	public WrongPasswordException(String message) {
		super(message);
	}

}

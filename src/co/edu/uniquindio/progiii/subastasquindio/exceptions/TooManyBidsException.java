package co.edu.uniquindio.progiii.subastasquindio.exceptions;


//Excepcion cuando un comprador ha superado
//el maximo de pujas permitidas por cuenta

import co.edu.uniquindio.progiii.subastasquindio.controllers.SingletonController;

public class TooManyBidsException extends Exception{

	public TooManyBidsException() {
		super();
	}

	public TooManyBidsException(String message) {
		super(message);
		SingletonController.guardarExcepcion(message, this.toString());	}

}

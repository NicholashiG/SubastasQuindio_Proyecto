package co.edu.uniquindio.progiii.subastasquindio.model;


import java.io.Serializable;

public class Usuario implements Serializable {

    //Constructor Vacío
    public Usuario() {
    }

    //Variables globales
    private String nombreUsuario;
    private String contrasena;
    private String email;
    private int edad;

    
    
    
    
    	// ESTE CONSTRUCTOR ES TEMPORAL PARA LAS PRUEBAS DE LOGIN
	public Usuario(String nombreUsuario, String contrasena) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
	}

		// ESTE TAMBIEN ES TEMPORAL PORQUE SOY TONTO SKJSJS
	public Usuario(String nombreUsuario, String contrasena, String email, int edad) {
			super();
			this.nombreUsuario = nombreUsuario;
			this.contrasena = contrasena;
			this.email = email;
			this.edad = edad;
		}



	//Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}

package co.edu.uniquindio.progiii.subastasquindio.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CasaSubastas implements Serializable {

	
	
	
	ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	Usuario usuarioLogeado;

	
	public CasaSubastas() {

	}
	
	
	public boolean login (String usuario, String contra) {
		
		for (Usuario user: listaUsuarios) {
			System.out.println("user: " + user.getNombreUsuario() + " - " + user.getContrasena() + "/n" + "Strings: " + usuario + " - " + contra );
			if (user.getNombreUsuario().equals(usuario) && user.getContrasena().equals(contra)) {
				usuarioLogeado = user;
				return true;
			}
			
		}
		return false;
	}

	public void registrarUsuario(Usuario user) {
		listaUsuarios.add(user);
	}
	

	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}


	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}


	public Usuario getUsuarioLogeado() {
		return usuarioLogeado;
	}


	public void setUsuarioLogeado(Usuario usuarioLogeado) {
		this.usuarioLogeado = usuarioLogeado;
	}



	
	
	
}

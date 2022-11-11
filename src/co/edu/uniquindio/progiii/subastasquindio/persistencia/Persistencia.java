package co.edu.uniquindio.progiii.subastasquindio.persistencia;

import co.edu.uniquindio.progiii.subastasquindio.exceptions.UsuarioException;
import co.edu.uniquindio.progiii.subastasquindio.model.Articulo;
import co.edu.uniquindio.progiii.subastasquindio.model.CasaSubastas;
import co.edu.uniquindio.progiii.subastasquindio.model.Publicacion;
import co.edu.uniquindio.progiii.subastasquindio.model.Transaccion;
import co.edu.uniquindio.progiii.subastasquindio.model.Usuario;

import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Persistencia {

    public static final String RUTA_ARCHIVO_USUARIOS = "src/co/edu/uniquindio/progiii/subastasquindio/persistencia/archivos/archivoUsuarios.txt";
    public static final String RUTA_ARCHIVO_PUBLICACIONES = "src/co/edu/uniquindio/progiii/subastasquindio/persistencia/archivos/archivoPublicaciones.xml";
    public static final String RUTA_ARCHIVO_ARTICULOS = "src/co/edu/uniquindio/progiii/subastasquindio/persistencia/archivos/archivoArticulos.txt";
    public static final String RUTA_ARCHIVO_LOG = "src/co/edu/uniquindio/progiii/subastasquindio/persistencia/log/archivoLog.log";
    public static final String RUTA_ARCHIVO_TRANSACCIONES = "src/co/edu/uniquindio/progiii/subastasquindio/persistencia/archivos/archivoTransacciones.txt";
    public static final String RUTA_ARCHIVO_MODELO_SUBASTAS_BINARIO = "src/co/edu/uniquindio/progiii/subastasquindio/persistencia/model.dat";
    public static final String RUTA_ARCHIVO_MODELO_SUBASTAS_BINARIO_RESPALDO = "src/co/edu/uniquindio/progiii/subastasquindio/persistencia/respaldo/model.dat";
    public static final String RUTA_ARCHIVO_MODELO_SUBASTAS_XML = "src/co/edu/uniquindio/progiii/subastasquindio/persistencia/model.xml";
    public static final String RUTA_ARCHIVO_MODELO_SUBASTAS_XML_RESPALDO = "src/co/edu/uniquindio/progiii/subastasquindio/persistencia/respaldo/model.xml";

    public static void cargarDatosArchivos(CasaSubastas subastasQuindio) throws Exception {


        //cargar archivo de clientes
        ArrayList<Usuario> usuariosCargados = cargarUsuarios();

        if (usuariosCargados.size() > 0)
            subastasQuindio.getListaUsuarios().addAll(usuariosCargados);


        //cargar archivos de publicaciones
        ArrayList<Publicacion> publicacionesCargadas = cargarPublicaciones();

        if (publicacionesCargadas.size() > 0)
            subastasQuindio.getListaPublicaciones().addAll(publicacionesCargadas);
    }


    /**
     * Guarda en un archivo de texto todos la informaci�n de las personas almacenadas en el ArrayList
     *
     * @param "objetos"
     * @param "ruta"
     * @throws IOException
     */
    public static void guardarUsuarios(ArrayList<Usuario> listaUsuarios) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";

        for (Usuario usuario : listaUsuarios) {
            contenido += usuario.toString() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, false);

    }


    public static void guardarPublicaciones(CasaSubastas subastasQuindio) throws IOException {

        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_PUBLICACIONES, subastasQuindio);
            // Revisar si en XML también guarda el tipo File y Date, de lo contrario, hay que buscar cómo hacerlo
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void guardarArticulo(Articulo articulo) throws IOException {
        String contenido = "";
        contenido = articulo.toStringSerializable() + "\n";
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ARTICULOS, contenido, true);
    }


    public static void guardarTransacciones(ArrayList<Transaccion> listaTransacciones) throws IOException {
        String contenido = "";
        for (Transaccion transaccion : listaTransacciones) {
            contenido += transaccion.toString() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_TRANSACCIONES, contenido, false);

    }


//	----------------------LOADS------------------------

    /**
     * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<Usuario> cargarUsuarios() throws FileNotFoundException, IOException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
        String linea = "";

        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(linea.split("@@")[0]);
            usuario.setContrasena(linea.split("@@")[1]);
            usuario.setEdad(Integer.parseInt(linea.split("@@")[2]));
            usuario.setEmail(linea.split("@@")[3]);
            usuarios.add(usuario);
        }
        return usuarios;
    }


    public static ArrayList<Transaccion> cargarTransacciones() throws FileNotFoundException, IOException {
        ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_TRANSACCIONES);
        String linea = "";

        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Transaccion transaccion = new Transaccion();
            transaccion.setId(Integer.parseInt((linea.split("@@")[0])));
            transaccion.setFecha(linea.split("@@")[1]);
            transaccion.setHora(linea.split("@@")[2]);
            // CARGA USUARIOS Y PUBLICACIONES EN STRING
            transaccion.setComprador(linea.split("@@")[3]);
            transaccion.setPublicacion(linea.split("@@")[4]);
            transaccion.setVendedor(linea.split("@@")[5]);
            transacciones.add(transaccion);
        }
        return transacciones;
    }

    public static ArrayList<Articulo> cargarArticulos() throws IOException {
        ArrayList<Articulo> articulos = new ArrayList<Articulo>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ARTICULOS);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Articulo articulo = new Articulo();
            articulo.setNombre(linea.split("@@")[0]);
            articulo.setTipo(linea.split("@@")[1]);
            articulo.setDescripcion(linea.split("@@")[2]);
            // CARGA USUARIOS Y PUBLICACIONES EN STRING
            articulo.setFoto(linea.split("@@")[3]);
            articulo.setVendedor(linea.split("@@")[4]);
            articulos.add(articulo);
        }


        return articulos;
    }


    private static ArrayList<Publicacion> cargarPublicaciones() throws Exception {
        CasaSubastas casaSubastas = (CasaSubastas) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_PUBLICACIONES);
        ArrayList<Publicacion> publicaciones = casaSubastas.getListaPublicaciones();
        return publicaciones;
    }


    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }


	/*public static boolean iniciarSesion(String usuario, String contrasenia) throws FileNotFoundException, IOException, UsuarioException, UsuarioException {
		
		if(validarUsuario(usuario,contrasenia)) {
			return true;
		}else {
			throw new UsuarioException("Usuario no existe");
		}
		
	}*/
	
	/*private static boolean validarUsuario(String usuario, String contrasenia) throws FileNotFoundException, IOException
	{
		ArrayList<Usuario> usuarios = Persistencia.cargarUsuarios(RUTA_ARCHIVO_USUARIOS);
		
		for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++) 
		{
			Usuario usuarioAux = usuarios.get(indiceUsuario);
			if(usuarioAux.getUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
				return true;
			}
		}
		return false;
	}*/


//	----------------------SAVES------------------------

    /**
     * Guarda en un archivo de texto todos la informaci�n de las personas almacenadas en el ArrayList
     *
     * @throws IOException
     */
	
	/*public static void guardarObjetos(ArrayList<Cliente> listaClientes, String ruta) throws IOException  {
		String contenido = "";
		
		for(Cliente clienteAux:listaClientes) {
			contenido+= clienteAux.getNombre()+","+clienteAux.getApellido()+","+clienteAux.getCedula()+clienteAux.getDireccion()
					     +","+clienteAux.getCorreo()+","+clienteAux.getFechaNacimiento()+","+clienteAux.getTelefono()+"\n";
		}
		ArchivoUtil.guardarArchivo(ruta, contenido, true);
	}

*/


    //------------------------------------SERIALIZACION  y XML
    public static CasaSubastas cargarRecursoCasaSubastasBinario() {

        CasaSubastas subastasQuindio = null;

        try {
            subastasQuindio = (CasaSubastas) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_SUBASTAS_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return subastasQuindio;
    }

    public static void guardarRecursoCasaSubastasBinario(CasaSubastas subastasQuindio) {

        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_SUBASTAS_BINARIO, subastasQuindio);
            // Revisar si en binario guarda el tipo File y Date, de lo contrario, hay que buscar cómo hacerlo
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static CasaSubastas cargarRecursoCasaSubastasXML() {

        CasaSubastas subastasQuindio = null;

        try {
            subastasQuindio = (CasaSubastas) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_SUBASTAS_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subastasQuindio;
    }


    public static void guardarRecursoCasaSubastasXML(CasaSubastas subastasQuindio) {

        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_SUBASTAS_XML, subastasQuindio);
            // Revisar si en XML también guarda el tipo File y Date, de lo contrario, hay que buscar cómo hacerlo
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void guardarRecursoCasaSubastasXMLRespaldo(CasaSubastas subastasQuindio) {

        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_SUBASTAS_XML_RESPALDO, subastasQuindio);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


}

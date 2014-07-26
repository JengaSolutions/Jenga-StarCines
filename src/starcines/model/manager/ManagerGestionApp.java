package starcines.model.manager;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.tomcat.util.buf.UDecoder;

import com.fasterxml.jackson.core.json.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import starcines.model.entities.Cartelera;
import starcines.model.entities.Genero;
import starcines.model.entities.Horario;
import starcines.model.entities.Sala;
import starcines.model.entities.Usuario;
import sun.org.mozilla.javascript.internal.json.JsonParser;

public class ManagerGestionApp {

	private ManagerDAO manager;

	/**
	 * Default constructor.
	 */
	public ManagerGestionApp() {
		manager = new ManagerDAO();
	}

	/**
	 * Metodo para la busqueda de usuarios dentro de la base de datos.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param orderBy
	 * 			Parametro de orednacion de la lista de salida.
	 * 			o.[columna de ordenacion]
	 * @return
	 * 		Retorna una lista con los clientes que existen en la base de datos.
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> findAllUsuarios(String orderBy) {
		return (List<Usuario>)manager.findAll(Usuario.class, orderBy);

	}

	/**
	 * Metodo para insertar nuevos usuarios a la BD.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param usu
	 * 			Nombre del usuario nuevo.
	 * @param pass
	 * 			Password del nuevo usuario.
	 * @throws Exception
	 * 			Problemas con la inserción en la BD.
	 */
	public void crearUsuario(String usu, String pass) throws Exception {
		Usuario u = new Usuario();
		u.setUsuNick(usu);
		u.setUsuPass(pass);
		manager.insertar(u);
	}

	/**
	 * Metodo que busca un usuario por su id.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param idUsuario
	 * 			nick del usuario
	 * @return
	 * 			retorna un usuario
	 * @throws Exception
	 * 			problemas en la busqueda
	 */
	public Usuario findByIdUsuario(String idUsuario) throws Exception{
		return (Usuario)manager.findById(Usuario.class, idUsuario);
	}

	/**
	 * Metodo que actualiza usuarios dentro de la BD
	 * Haciendo uso del componete 
	 * @param usu
	 * @param pass
	 * @throws Exception
	 */
	public void actualizarUsuario(String usu, String pass) throws Exception{
		Usuario u=new Usuario();
		u.setUsuNick(usu);
		u.setUsuPass(pass);
		manager.actualizar(u);
	}

	
	// METODOS PARA EL SERVICIO MOBIL
	/**
	 * finder de todos los datos de la base de datos para el servicio mobil. HAce uso de la API de 
	 * Google GSON para transformar los objetos a JSON. Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * 
	 * @param orderBy
	 *            Expresion que indica la propiedad de la entidad por la que se
	 *            desea ordenar la consulta. Debe utilizar el alias "o" para
	 *            nombrar a la(s) propiedad(es) por la que se va a ordenar. 
	 *            Puede aceptar null o una cadena vacia, en este caso no
	 *            ordenara el resultado.
	 *            
	 * @return Cadena JSON con los resultadoa obtenidos.
	 */	
	@SuppressWarnings("unchecked")
	public String getFullData() throws Exception
	{	
		List<Cartelera> lst = (List<Cartelera>)manager.findAll(Cartelera.class, null );
		//Instanciar las relaciones de cartelera.
		for(Cartelera c : lst){
			c.getHorarios();
			c.getPelicula().getGeneros();
		}
		//trasnformar a JSON
		String JSON="";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(df);
		JSON = mapper.writeValueAsString(lst);	
		return JSON;
	}
}
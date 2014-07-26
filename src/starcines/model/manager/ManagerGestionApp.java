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
import starcines.model.entities.Pelicula;
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
	
	//CRUD PARA USUARIOS

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
	 * Metodo que crear usuarios dentro de la BD.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param u
	 * 			usuario
	 * @throws Exception
	 * 		problema al crear usuarios
	 */
	public void crearUsuario(Usuario u) throws Exception {
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
	 * Metodo que actualiza un usuario en la BD.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param usuario
	 * @throws Exception
	 */
	public void actualizarUsuario(Usuario usuario) throws Exception{
		Usuario u=null;
		try{
			u=findByIdUsuario(usuario.getUsuNick());
			
			u.setUsuNick(usuario.getUsuNick());
			u.setUsuPass(usuario.getUsuPass());
			manager.actualizar(u);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
	}

	/**
	 * Metodo que elimina un usuario por el nick dentro de la BD.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param nick
	 * 			nombre de usuario
	 * @throws Exception
	 */
	public void eliminarUsuario(String nick) throws Exception{
		manager.eliminar(Usuario.class, nick);
	}
	
	//METODOS CRUD PARA GENERO
	
	/**
	 * Metodo para listar todos los generos de peliculas.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @return
	 * 		lista de generos
	 */
	@SuppressWarnings("unchecked")
	public List<Genero> findAllGeneros() {
		return manager.findAll(Genero.class, "o.gen_id");
	}
	
	/**
	 * Metodo para listar todos los generos de peliculas.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param codigoGenero
	 * 			codigo del genero
	 * @return
	 * 			retorna un genero mediante el id
	 * @throws Exception
	 * 			problema al buscar el genero
	 */
	public Genero findGeneroById(Integer codigoGenero) throws Exception {
		return (Genero) manager.findById(Genero.class, codigoGenero);
	}
	
	/**
	 * Metodo para crear generos.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param g
	 * @throws Exception
	 */
	public void insertarGenero(Genero g) throws Exception {
		manager.insertar(g);
	}
	
	/**
	 * Metodo para actualizar genero dentro de la BD.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param genero
	 * @throws Exception
	 */
	public void actualizarGenero(Genero genero) throws Exception{
		Genero g=null;
		try{
			g=findGeneroById(genero.getGenId());
			
			g.setGenTipo(genero.getGenTipo());
			manager.actualizar(g);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * Metodo para eliminar un genero por el id.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param id
	 * @throws Exception
	 */
	public void eliminarGenero(Integer id) throws Exception{
		manager.eliminar(Genero.class, id);
	}
	
	
	//METODOS CRUD PARA SALAS
	
	/**
	 * Metodo para listar salas.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Sala> findAllSalas() {
		return manager.findAll(Sala.class, "o.sal_nombre");
	}
	
	/**
	 * Metodo para buscar la sal por el id.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param codigoSala
	 * @return
	 * @throws Exception
	 */
	public Sala findSalaById(Integer codigoSala) throws Exception {
		return (Sala) manager.findById(Sala.class, codigoSala);
	}
	
	/**
	 * Metodo para crear salas dentro de la BD.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param s
	 * @throws Exception
	 */
	public void insertarSala(Sala s) throws Exception {
		manager.insertar(s);
	}
	
	/**
	 * Metodo para actualizar salas.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param sala
	 * @throws Exception
	 */
	public void actualizarSala(Sala sala) throws Exception{
		Sala s=null;
		try{
			s=findSalaById(sala.getSalId());
			
			s.setSalNombre(sala.getSalNombre());;
			s.setSalFormato(sala.getSalFormato());
			manager.actualizar(s);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * Metodo para eliminar Sala.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param id
	 * @throws Exception
	 */
	public void eliminarSala(Integer id) throws Exception{
		manager.eliminar(Sala.class, id);
	}
	
	//METODOS CRUD PARA PELICULAS
	
	/**
	 * Metodo para listar todas las peliculas.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	public List<Pelicula> findAllPelicula() {
		return manager.findAll(Pelicula.class, "o.pel_fecha");
	}
	
	/**
	 * Metodo que busca una pelicula por el id.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param codigoPelicula
	 * @return
	 * @throws Exception
	 */
	public Pelicula findPeliculaById(Integer codigoPelicula) throws Exception {
		return (Pelicula) manager.findById(Pelicula.class, codigoPelicula);
	}
	
	/**
	 * Metodo para crear peliculas.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param p
	 * @throws Exception
	 */
	public void insertarPelicula(Pelicula p) throws Exception {
		manager.insertar(p);
	}
	
	/**
	 * Metodo para actualizar campos de la tabla pelicula.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param pelicula
	 * @throws Exception
	 */
	public void actualizarPelicula(Pelicula pelicula) throws Exception{
		Pelicula p=null;
		try{
			p=findPeliculaById(pelicula.getPelId());
			
			p.setPelNombre(pelicula.getPelNombre());
			p.setPelFecha(pelicula.getPelFecha());
			p.setPelSinopsis(pelicula.getPelSinopsis());
			p.setPelDuracion(pelicula.getPelDuracion());
			p.setPelDirector(pelicula.getPelDirector());
			p.setPelActores(pelicula.getPelActores());
			p.setPelClasificacion(pelicula.getPelClasificacion());
			p.setPelIdioma(pelicula.getPelIdioma());
			p.setPelSubIdioma(pelicula.getPelSubIdioma());
			p.setPelTrailer(pelicula.getPelTrailer());
			p.setPelImagen(pelicula.getPelImagen());
			manager.actualizar(p);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * Metodo para elimimar una pelicula.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param id
	 * @throws Exception
	 */
	public void eliminarPelicula(Integer id) throws Exception{
		manager.eliminar(Pelicula.class, id);
	}
	
	//METODOS CRUD PARA HORARIO
	
	/**
	 * Metodo para listar todos los horarios.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Horario> findAllHorario() {
		return manager.findAll(Horario.class, "o.hor_hora");
	}
	
	/**
	 * Metodo para buscar e horario por el id.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param codigoHorario
	 * @return
	 * @throws Exception
	 */
	public Horario findHorarioById(Integer codigoHorario) throws Exception {
		return (Horario) manager.findById(Horario.class, codigoHorario);
	}
	
	/**
	 * Metodo para crear horarios.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param h
	 * @throws Exception
	 */
	public void insertarHorario(Horario h) throws Exception {
		manager.insertar(h);
	}
	
	/**
	 * Metodo para actualizar horario.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param horario
	 * @throws Exception
	 */
	public void actualizarHorario(Horario horario) throws Exception{
		Horario h=null;
		try{
			h=findHorarioById(horario.getHorId());
			
			h.setHorHora(horario.getHorHora());
			
			manager.actualizar(h);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * Metodo para eliminar horario.
	 * Hace uso del componente {@link starcines.model.manager.ManagerDAO}
	 * @param id
	 * @throws Exception
	 */
	public void eliminarHorario(Integer id) throws Exception{
		manager.eliminar(Horario.class, id);
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
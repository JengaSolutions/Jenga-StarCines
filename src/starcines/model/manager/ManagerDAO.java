package starcines.model.manager;

import java.sql.Time;
import java.util.List;
import java.util.Timer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import starcines.model.entities.Genero;
import starcines.model.entities.Horario;
import starcines.model.entities.Sala;
import starcines.model.entities.Usuario;



public class ManagerDAO {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerDAO() {
		if(emf==null)
			emf=Persistence.createEntityManagerFactory("StarCines");
		if(em==null)
			em=emf.createEntityManager();
	}

	//Usuarios

		//Listar Todos los Usuarios
		@SuppressWarnings("unchecked")
		public List<Usuario> findAllUsuarios(){
		 List<Usuario> listado;
		 Query q;
		 em.getTransaction().begin();
		 q=em.createQuery("SELECT u FROM Usuario u ORDER BY u.usu_nick");
		 listado= q.getResultList();
		 em.getTransaction().commit();
		 return listado;
		 
		}
		
		//metodo ingresar Usuario
		 public void crearUsuario (String usu,String pass){
			 em.getTransaction().begin();
			 Usuario u = new Usuario();
			 u.setUsuNick(usu);
			 u.setUsuPass(pass);
			 em.persist(u);
			 em.getTransaction().commit();
	 }
			 
		//metodo para buscar un usuario por id
		 public Usuario findByIdUsuario(String idUsuario){
			 em.getTransaction().begin();
			 Usuario u =em.find(Usuario.class, idUsuario);
			 em.getTransaction().commit();
			 return u;
		 }

		//metodo para actualizar un Usuario:
		 public void actualizarUsuario(String usu,String pass){
			 //buscamos el objeto que debe ser actualizado:
			 Usuario u = findByIdUsuario(usu);
			 em.getTransaction().begin();
			 // no se actualiza la clave primaria, en este caso solo la descripcion
			 u.setUsuNick(usu);
			 u.setUsuPass(pass);
			 em.merge(u);
			 em.getTransaction().commit();
		 }
			 
		
		//metodo para buscar por nombre
			 public Usuario findByNombre(String nombre){
					List<Usuario> listado;
					Usuario u=null;
					listado =findAllUsuarios();
					em.getTransaction().begin();
					for (Usuario us:listado){
						if (us.getUsuNick().equals(nombre)){
							u=us;
						}
					}
					em.getTransaction().commit();
					return u;
				}
			 
				//Generos

				//Listar Todos los generos
				@SuppressWarnings("unchecked")
				public List<Genero> findAllGeneros(){
				 List<Genero> listado;
				 Query q;
				 em.getTransaction().begin();
				 q=em.createQuery("SELECT u FROM Genero u ORDER BY u.gen_id");
				 listado= q.getResultList();
				 em.getTransaction().commit();
				 return listado;
				 
				}
				
				//metodo ingresar genero
					 public void crearGenero (Integer id,String tipo){
						 em.getTransaction().begin();
						 Genero g = new Genero();
						 g.setGenId(id);
						 g.setGenTipo(tipo);
						 em.persist(g);
						 em.getTransaction().commit();
						 
					 }
					 
				//metodo para buscar un genero por id
					 public Genero findByIdGenero(Integer id){
						 em.getTransaction().begin();
						 Genero g =em.find(Genero.class, id);
						 em.getTransaction().commit();
						 return g;
					 }

				//metodo para actualizar un genero:
					 public void actualizarGenero(Integer id,String tipo){
						 //buscamos el objeto que debe ser actualizado:
						 Genero g = findByIdGenero(id);
						 em.getTransaction().begin();
						 // no se actualiza la clave primaria, en este caso solo la descripcion
						 //g.setGenId(id);
						 g.setGenTipo(tipo);
						 em.merge(g);
						 em.getTransaction().commit();
					 }
					 
				
				//metodo para buscar por nombre
					 public Genero findByTipo(String tipo){
							List<Genero> listado;
							Genero u=null;
							listado =findAllGeneros();
							em.getTransaction().begin();
							for (Genero us:listado){
								if (us.getGenTipo().equals(tipo)){
									u=us;
								}
							}
							em.getTransaction().commit();
							return u;
						}
					 
					//Salas

						//Listar Todas las salas
						@SuppressWarnings("unchecked")
						public List<Sala> findAllSalas(){
						 List<Sala> listado;
						 Query q;
						 em.getTransaction().begin();
						 q=em.createQuery("SELECT u FROM Sala u ORDER BY u.sal_id");
						 listado= q.getResultList();
						 em.getTransaction().commit();
						 return listado;
						 
						}
						
						//metodo ingresar sala
							 public void crearSala (Integer id,String nombre,String formato){
								 em.getTransaction().begin();
								 Sala s = new Sala();
								 s.setSalId(id);
								 s.setSalNombre(nombre);
								 s.setSalFormato(formato);
								 em.persist(s);
								 em.getTransaction().commit();
								 
							 }
							 
						//metodo para buscar una sala por id
							 public Sala findByIdSala(Integer id){
								 em.getTransaction().begin();
								 Sala s =em.find(Sala.class, id);
								 em.getTransaction().commit();
								 return s;
							 }

						//metodo para actualizar una sala:
							 public void actualizarSala(Integer id,String nombre,String formato){
								 //buscamos el objeto que debe ser actualizado:
								 Sala s = findByIdSala(id);
								 em.getTransaction().begin();
								 // no se actualiza la clave primaria, en este caso solo la descripcion
								 s.setSalNombre(nombre);
								 s.setSalFormato(formato);
								 em.merge(s);
								 em.getTransaction().commit();
							 }
							 
						
						//metodo para buscar por nombre
							 public Sala findByNombreSala(String nombre){
									List<Sala> listado;
									Sala s=null;
									listado =findAllSalas();
									em.getTransaction().begin();
									for (Sala us:listado){
										if (us.getSalNombre().equals(nombre)){
											s=us;
										}
									}
									em.getTransaction().commit();
									return s;
								}
			
							
							 //Horarios
								//Listar Todos las horarios
								@SuppressWarnings("unchecked")
								public List<Horario> findAllHorarios(){
								 List<Horario> listado;
								 Query q;
								 em.getTransaction().begin();
								 q=em.createQuery("SELECT u FROM Horario u ORDER BY u.hor_id");
								 listado= q.getResultList();
								 em.getTransaction().commit();
								 return listado;
								 
								}
								
								//metodo ingresar horario
									 public void crearHorario (Integer id,Time hora){
										 em.getTransaction().begin();
										 Horario h = new Horario();
										 h.setHorId(id);
										 h.setHorHora(hora);
										 em.persist(h);
										 em.getTransaction().commit();
										 
									 }
									 
								//metodo para buscar un horario por id
									 public Horario findByIdHorario(Integer id){
										 em.getTransaction().begin();
										 Horario h =em.find(Horario.class, id);
										 em.getTransaction().commit();
										 return h;
									 }

								//metodo para actualizar una sala:
									 public void actualizarHorario(Integer id,Time hora){
										 //buscamos el objeto que debe ser actualizado:
										 Horario h = findByIdHorario(id);
										 em.getTransaction().begin();
										 // no se actualiza la clave primaria, en este caso solo la descripcion
										 h.setHorHora(hora);
										 em.merge(h);
										 em.getTransaction().commit();
									 }
									 
								
								//metodo para buscar por horario
					 public Horario findByNombreHorario(Time hora){
						List<Horario> listado;
						Horario h=null;
						listado =findAllHorarios();
						em.getTransaction().begin();
						for (Horario us:listado){
							if (us.getHorHora().equals(hora)){
									h=us;
								}
						}
						em.getTransaction().commit();
						return h;
				}
}
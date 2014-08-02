package starcines.controller;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import starcines.model.entities.Pelicula;
import starcines.model.manager.ManagerGestionApp;

@ManagedBean
@SessionScoped
public class BackingPelicula {

	private ManagerGestionApp manager;
	
	public BackingPelicula(){
		manager = new ManagerGestionApp();
	}
	
	private Integer id;
	private String nombre;
	private Date fecha;
	private String sinopsis;
	private String director;
	private String duracion;
	private String actores;
	private String clasificacion;
	private String idioma;
	private String subidioma;
	private String trailer;
	private String imagen;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getSinopsis() {
		return sinopsis;
	}
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public String getActores() {
		return actores;
	}
	public void setActores(String actores) {
		this.actores = actores;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getSubidioma() {
		return subidioma;
	}
	public void setSubidioma(String subidioma) {
		this.subidioma = subidioma;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	/**
	 * 
	 * @return
	 */
	public List<Pelicula> getListaPeliculas(){
		return manager.findAllPelicula();
	}
	
	/**
	 * 
	 * @return
	 */
	public String accionInsertar() {
		// Creamos un objeto temporal con los datos del formulario:
		Pelicula p=new Pelicula();
		p.setPelActores(actores);
		p.setPelClasificacion(clasificacion);
		p.setPelDirector(director);
		p.setPelDuracion(duracion);
		p.setPelFecha(fecha);
		p.setPelIdioma(idioma);
		p.setPelImagen(imagen);
		p.setPelNombre(nombre);
		p.setPelSinopsis(sinopsis);
		p.setPelSubIdioma(subidioma);
		p.setPelTrailer(trailer);
		try {
			// guardamos en la bdd:
			manager.insertarPelicula(p);
			// limpiamos los datos del formulario:
			actores=null;
			clasificacion=null;
			director=null;
			duracion=null;
			fecha=null;
			idioma=null;
			imagen=null;
			nombre=null;
			sinopsis=null;
			subidioma=null;
			trailer=null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * @param p
	 * @return
	 */
	public String accionEliminar(Pelicula p) {
		try {
			manager.eliminarPelicula(p.getPelId());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public String accionCargar(Pelicula p) {
		id=p.getPelId();
		actores=p.getPelActores();
		clasificacion=p.getPelClasificacion();
		director=p.getPelDirector();
		duracion=p.getPelDuracion();
		fecha=p.getPelFecha();
		idioma=p.getPelIdioma();
		imagen=p.getPelImagen();
		nombre=p.getPelNombre();
		sinopsis=p.getPelSinopsis();
		subidioma=p.getPelSubIdioma();
		trailer=p.getPelTrailer();
		return "peliculas_actualizacion";
	}
	
	/**
	 * 
	 * @return
	 */
	public String accionActualizar(){
		Pelicula p;
		//creamos un objeto temporal con los datos a actualizar:
		p=new Pelicula();
		p.setPelActores(actores);
		p.setPelClasificacion(clasificacion);
		p.setPelDirector(director);
		p.setPelDuracion(duracion);
		p.setPelFecha(fecha);
		p.setPelIdioma(idioma);
		p.setPelImagen(imagen);
		p.setPelNombre(nombre);
		p.setPelSinopsis(sinopsis);
		p.setPelSubIdioma(subidioma);
		p.setPelTrailer(trailer);
		try {
		//hacemos la actualizacion:
		manager.actualizarPelicula(p);
		//inicializamos las propiedades para limpiar el formulario:
		actores=null;
		clasificacion=null;
		director=null;
		duracion=null;
		fecha=null;
		idioma=null;
		imagen=null;
		nombre=null;
		sinopsis=null;
		subidioma=null;
		trailer=null;
		} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		e.printStackTrace();
		} //ir ala pagina productos.xhtml:
		return "peliculas";
		}
}

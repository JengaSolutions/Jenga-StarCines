package starcines.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import starcines.model.entities.Usuario;
import starcines.model.manager.ManagerGestionApp;


@SessionScoped
@ManagedBean
public class BeanUsuario {
	
	private ManagerGestionApp manager;
	private List<Usuario> lista;
	
	
	public BeanUsuario() {
		manager = new ManagerGestionApp();
	}
	
	public void actionLista()
	{
		lista = (List<Usuario>)manager.findAllUsuarios(null);	
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}
	
	

}

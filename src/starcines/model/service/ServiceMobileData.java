package starcines.model.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import starcines.model.manager.ManagerGestionApp;

/**
 * Servlet implementation class ServiceMobileData
 */
@WebServlet(description = "Servicio para accedo a datos de los dispositivos moviles.", urlPatterns = { "/mobileData" })
public class ServiceMobileData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ManagerGestionApp manager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServiceMobileData() {
		super();
		manager = new ManagerGestionApp();
	}

	/**
	 * Metodo para recibir peticiones de dispositivos mobiles para el acceso a
	 * los datos de la BD.
	 * 
	 * @Request.param.u usuario varible para identificarl al usuario que desea
	 *                  acceder al servicio, medio de control para el acceso al
	 *                  servicio.
	 * @Request.param.m metodo nombre del metodo a ejecutar.
	 * @Respose.return retorna los datos segun el metodo s¿seleccionado en
	 *                 formato JSON.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//agregar funcuinalidad de cross domain para ajax
		response.addHeader("Access-Control-Allow-Origin", "*");
	    response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
	    response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	    response.addHeader("Access-Control-Max-Age", "86400");
		//
		
		// obetenr nombre de usuario.
		String usuario = request.getParameter("u");
		// verificar usuario.
		if (!usuario.equals("Jenga")) {
			response.getWriter().write("[Error en la autenticación.]");
			response.getWriter().close();
			return;
		}

		// obtener metodo a ejecutar
		String metodo = request.getParameter("m");

		String JSON = "[empty]";
		if (metodo.equals("all")) {
			try {
				JSON = all();
			} catch (Exception e) {
				System.out.print(e.getMessage());
				//e.printStackTrace();
			}
		}
		response.getWriter().write(JSON);
		response.getWriter().close();
	}
	
	
	/**
	 * Metodo ALL para busqueda de datos desde cartelera.
	 * @return lista de carteeras.
	**/
	private String all() throws Exception
	{
		return manager.getFullData();
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

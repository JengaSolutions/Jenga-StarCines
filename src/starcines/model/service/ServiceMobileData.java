package starcines.model.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import starcines.model.manager.ManagerDAO;

/**
 * Servlet implementation class ServiceMobileData
 */
@WebServlet(description = "Servicio para accedo a datos de los dispositivos moviles.", urlPatterns = { "/mobileData" })
public class ServiceMobileData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ManagerDAO manager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServiceMobileData() {
		super();
		manager = new ManagerDAO();
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
				JSON = manager.getFullData();
			} catch (Exception e) {
				System.out.print(e.getMessage());
				//e.printStackTrace();
			}
		}
		response.getWriter().write(JSON);
		response.getWriter().close();
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

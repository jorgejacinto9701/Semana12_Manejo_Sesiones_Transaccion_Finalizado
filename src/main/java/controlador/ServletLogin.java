package controlador;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsuarioDAO;
import entidad.EnlaceBean;
import entidad.UsuarioBean;
import fabricas.Fabrica;


@WebServlet("/login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ServletLogin.class.getName());
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String user = request.getParameter("loginuser");
		String clave = request.getParameter("loginpassword");

		UsuarioBean bean = new UsuarioBean();
		bean.setLogin(user);
		bean.setPassword(clave);
		
		Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		UsuarioDAO dao = fabrica.getUsuarioDAO();
		
		try {
				
			UsuarioBean usuario = dao.login(bean);
			//Cuando el usuario no existe
			if(usuario == null){
				String mensaje ="El usuario no existe";
				request.setAttribute("mensaje", mensaje);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				
			}else{
				//Acceder a la session
				HttpSession session = request.getSession();
				
				//Se imprime la ID de la session
				String id =  session.getId();
				log.info(">>> ID session >> " + id);
				
				//Se guarda en sesion los datos de usuario
				//La session es un objeto que dura un determinado tiempo en el servidor
				session.setAttribute("objUsuario", usuario);
				
				//Se obtiene los men�s del usuario logeado y se guarda en la memoria sesi�n
				List<EnlaceBean> menus = dao.traerEnlacesDeUsuario(usuario.getIdUsuario());
				session.setAttribute("objMenus", menus);
				
				//Cuando el usuario existe
				response.sendRedirect("intranetHome.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}









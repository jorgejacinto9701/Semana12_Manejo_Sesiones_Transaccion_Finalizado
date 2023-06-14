package controlador;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/buscaProducto")
public class ServletBuscaProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ServletBuscaProducto.class.getName());

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		log.info("En ServletBuscaProducto");
		
		String filtro = request.getParameter("filtro");
		log.info("Filtro -> " + filtro);
		
	
	
	}

}

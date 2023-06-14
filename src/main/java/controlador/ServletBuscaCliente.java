package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoletaDao;
import entidad.ClienteBean;
import fabricas.Fabrica;

@WebServlet("/buscaCliente")
public class ServletBuscaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(ServletBuscaCliente.class.getName());
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("En ServletBuscaCliente");
		
		String filtro = request.getParameter("filtro");
		log.info("Filtro -> " + filtro);
		
		
		Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		BoletaDao dao = fabrica.getBoletaDao();
		
		if(filtro == null) filtro ="";
		List<ClienteBean> data = dao.consultaCliente(filtro);
		
		/*JsonArrayBuilder array = Json.createArrayBuilder();
		JsonObject obj = null;
		
		for (ClienteBean x : data) {
			obj = Json.createObjectBuilder().
					add("var_id", x.getIdCliente()).
					add("var_nombre", x.getNombre()).
					add("var_apellido", x.getApellido()).build();
			array.add(obj);
		}
		
		//Se imprime el resultado
		log.info(array.build());
		
		
		//Notificamos el tipo de archivo
		response.setContentType("application/json;charset=UTF-8");
		
		//Se genera el archivo JSON y se envia
		PrintWriter out =response.getWriter();
		out.println(array.build());*/
	}

}

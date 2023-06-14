package controlador;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.log.LogFactory;

import dao.BoletaDao;
import dao.impl.MySqlBoleta;
import entidad.ProductoBean;
import fabricas.Fabrica;

@WebServlet("/buscaProducto")
public class ServletBuscaProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ServletBuscaProducto.class.getName());

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		log.info("En ServletBuscaProducto");
		
		String filtro = request.getParameter("filtro");
		log.info("Filtro -> " + filtro);
		
		Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		BoletaDao dao = fabrica.getBoletaDao();
		
		if(filtro == null) filtro ="";
		List<ProductoBean> data = dao.consultaXNombre(filtro);

		/*JsonArrayBuilder array = Json.createArrayBuilder();
		JsonObject obj = null;
		
		for (ProductoBean x : data) {
			obj = Json.createObjectBuilder().
					add("var_id", x.getCodigo()).
					add("var_nombre", x.getNombre()).
					add("var_precio", x.getPrecio()).
					add("var_stock", x.getStock()).build();
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

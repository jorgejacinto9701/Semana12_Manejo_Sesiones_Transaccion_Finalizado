package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.BoletaDao;
import entidad.Boleta;
import entidad.DetalleBoleta;
import entidad.Producto;
import entidad.Respuesta;
import fabricas.Fabrica;

@WebServlet("/boleta")
public class ServletBoleta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ServletBoleta.class.getName());
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String metodo = request.getParameter("metodo");
		if(metodo.equals("agregaSeleccion")){
			agregar(request, response);
		}else if(metodo.equals("eliminaSeleccion")){
			eliminar(request, response);
		}else if(metodo.equals("registraBoleta")){
			registra(request, response);
		}
	}

	@SuppressWarnings("unchecked")
	protected void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("En agregar seleccion");
		
		String idProducto = request.getParameter("idProducto");
		String nombre = request.getParameter("nombreProducto");
		String precio = request.getParameter("precio");
		String cantidad = request.getParameter("cantidad");
		
		
		int idProd = Integer.parseInt(idProducto);
		int cant = Integer.parseInt(cantidad);
		double pre = Double.parseDouble(precio);
				
		ArrayList<Producto> boleta  ;
		
		//Se verifica si existe en sesion
		HttpSession session = request.getSession();
		if(session.getAttribute("dataDeGrilla") == null){
			boleta = new ArrayList<Producto>();
		}else{
			boleta = (ArrayList<Producto>)session.getAttribute("dataDeGrilla");
		}
		
		//Se crear el objeto
		Producto p = new Producto();
		p.setIdProducto(idProd);
		p.setNombre(nombre);
		p.setCantidad(cant);
		p.setPrecio(pre);
		
		boolean noExiste = true;
		//se verifica los repetidos
		for (int i = 0; i < boleta.size(); i++) {
			if(boleta.get(i).getIdProducto() == idProd){
				boleta.set(i, p);
				noExiste = false;
				break;
			}
		}
		
		//Si no existe se agrega
		if(noExiste){
			boleta.add(p);
		}
		
		//la lista se agrega a sesion
		session.setAttribute("dataDeGrilla", boleta);
		
		Respuesta objRespuesta = new Respuesta();
		objRespuesta.setMensaje("Se abgregó el producto >>  " + idProd +" >>> " + nombre);
		objRespuesta.setDatos(boleta);
		
		Gson gson = new Gson();
		String json = gson.toJson(objRespuesta);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		
			
	}
	
	@SuppressWarnings("unchecked")
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("En eliminar seleccion");
		
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		
		ArrayList<Producto> boleta = (ArrayList<Producto>)session.getAttribute("dataDeGrilla");

		//Se elimina
		for (Producto p : boleta) {
			if(p.getIdProducto() == Integer.parseInt(id)){
				boleta.remove(p);
				break;
			}
		}
		//la lista se agrega a sesion
		session.setAttribute("dataDeGrilla", boleta);
		
		Respuesta objRespuesta = new Respuesta();
		objRespuesta.setMensaje("Se eliminó el producto  " + id);
		objRespuesta.setDatos(boleta);
		
		Gson gson = new Gson();
		String json = gson.toJson(objRespuesta);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json);
	}
	
	@SuppressWarnings("unchecked")
	protected void registra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("En registra boleta");
		
		HttpSession session = request.getSession();

		//Boleta que esta en sesion
		ArrayList<Producto> boleta = (ArrayList<Producto>)session.getAttribute("dataDeGrilla");
		
		//Cliente
		String cliente = request.getParameter("idCliente");
		int idCliente = Integer.parseInt(cliente);
		
		//Creamos la Boleta
		Boleta b = new Boleta();
		b.setIdCliente(idCliente);
		
		//Creamos el detalle
		ArrayList<DetalleBoleta> detalles = new ArrayList<DetalleBoleta>();
		for (Producto x : boleta) {
			DetalleBoleta det = new DetalleBoleta();
			det.setCantidad(x.getCantidad());
			det.setIdProducto(x.getIdProducto());
			det.setPrecio(x.getPrecio());
			detalles.add(det);
		}
		
		//Se inserta la boleta
		Fabrica mysql = Fabrica.getFabrica(Fabrica.MYSQL);
		BoletaDao dao = mysql.getBoletaDao();
		
		dao.inserta(b, detalles);
		
		//limpiamos la sesion
		session.removeAttribute("dataDeGrilla");
		boleta.clear();
		
		Respuesta objRespuesta = new Respuesta();
		objRespuesta.setMensaje("Se registró la Boleta  ");
		objRespuesta.setDatos(boleta);
		
		Gson gson = new Gson();
		String json = gson.toJson(objRespuesta);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		
	}

}

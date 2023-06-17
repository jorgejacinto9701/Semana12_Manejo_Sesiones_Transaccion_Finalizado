package dao;

import java.util.ArrayList;
import java.util.List;

import entidad.Boleta;
import entidad.Cliente;
import entidad.DetalleBoleta;
import entidad.Producto;

public interface BoletaDao {
	
	public int inserta(Boleta boletaBean, List<DetalleBoleta> lstDetalle);
	public ArrayList<Cliente> consultaCliente(String filtro);
	public ArrayList<Producto> consultaXNombre(String filtro);
}

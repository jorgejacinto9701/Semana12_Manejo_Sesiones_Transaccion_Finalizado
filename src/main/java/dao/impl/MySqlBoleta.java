package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import dao.BoletaDao;
import entidad.Boleta;
import entidad.Cliente;
import entidad.DetalleBoleta;
import entidad.Producto;
import util.MySqlDBConexion;

public class MySqlBoleta implements BoletaDao{

	
	private static Logger log = Logger.getLogger(MySqlBoleta.class.getName());
	
	public int inserta(Boleta boletaBean, List<DetalleBoleta> lstDetalle){
		log.info("---> En MySqlBoleta-> inserta");
		
		
		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm1 = null, pstm2= null,pstm3= null, pstm4 = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			//Se anula el auto envio
			conn.setAutoCommit(false);
			
			//se crea el sql de la cabecera
			String sql1 ="insert into boleta values(null,curdate(),?,?)";
			pstm1 = conn.prepareStatement(sql1);
			pstm1.setInt(1, boletaBean.getIdCliente());
			pstm1.setInt(2, boletaBean.getIdUsuario());
			pstm1.executeUpdate();
			log.info(">> pstm1 >> " + pstm1);
			
			//se obtiene el idBoleta insertado
			String sql2 ="select max(idBoleta) from boleta";
			pstm2 =  conn.prepareStatement(sql2);
			log.info(">> pstm2 >> " + pstm2);
			ResultSet rs = pstm2.executeQuery();
			rs.next();
			int idBoleta = rs.getInt(1);
			
			//se inserta el detalle de boleta
			String sql3 ="insert into boleta_has_producto values(?,?,?,?)";
			pstm3 =  conn.prepareStatement(sql3);
			
			//Actualiza el stock
			String sql4 ="update producto set stock = stock - ? where idproducto = ?";
			pstm4 =  conn.prepareStatement(sql4);
			
			for (DetalleBoleta aux : lstDetalle) {
				pstm4.setInt(1, aux.getCantidad());
				pstm4.setInt(2, aux.getIdProducto());
				pstm4.executeUpdate();
				log.info(">> pstm4 >> " + pstm4);
				
				pstm3.setInt(1, aux.getIdProducto());
				pstm3.setInt(2, idBoleta);
				pstm3.setDouble(3, aux.getPrecio());
				pstm3.setInt(4, aux.getCantidad());
				pstm3.executeUpdate();
				log.info(">> pstm3 >> " + pstm3);
			}
			
			//se ejecuta todos los SQL en la base de datos
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
				//se vuelva a un inicio 
				//No permite un SQL por separado
			} catch (SQLException e1) {}
			e.printStackTrace();
		} finally{
			try {
				conn.close();
				pstm1.close();
				pstm2.close();
				pstm3.close();
				
			} catch (SQLException e) {
			}
		}
		return contador;
	}

	@Override
	public ArrayList<Cliente> consultaCliente(String filtro) {
		log.info("---> En MySqlBoleta-> consultaCliente ->" + filtro);
		
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Cliente bean = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			String sql = null;
			conn = MySqlDBConexion.getConexion();
			 sql ="select * from cliente where  nombre like ? or apellido like ? ";
					pstm = conn.prepareStatement(sql);
					pstm.setString(1, (filtro+"%"));
					pstm.setString(2, (filtro+"%"));
					
			log.info("---> SQL -> " + pstm);
					
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				bean = new Cliente();
				bean.setIdCliente(rs.getInt("idcliente"));
				bean.setNombre(rs.getString("nombre"));
				bean.setApellido(rs.getString("apellido"));
				bean.setEdad(rs.getInt("edad"));
				bean.setFecha(rs.getDate("fechaNacimiento"));
				data.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}

	@Override
	public ArrayList<Producto> consultaXNombre(String filtro) {
		log.info("---> En MySqlBoleta-> consultaXNombre ->" + filtro);
		
		ArrayList<Producto> data = new ArrayList<Producto>();
		Producto bean = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql ="select * from producto  where nombre like ? ";
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, (filtro+"%"));
		
			log.info("---> SQL -> " + pstm);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				bean = new Producto();
				bean.setIdProducto(rs.getInt("idproducto"));
				bean.setNombre(rs.getString("nombre"));
				bean.setMarca(rs.getString("marca"));
				bean.setPrecio(rs.getDouble("precio"));
				bean.setStock(rs.getInt("stock"));
				bean.setIdCategoria(rs.getInt("idcategoria"));
				data.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}
}

package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import dao.UsuarioDAO;
import entidad.EnlaceBean;
import entidad.UsuarioBean;
import util.MySqlDBConexion;

public class MySqlUsuarioDAO implements UsuarioDAO {

	private static Logger log = Logger.getLogger(MySqlUsuarioDAO.class.getName());
	

	
	@Override
	public UsuarioBean login(UsuarioBean bean) throws Exception {
		Connection conn= null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		UsuarioBean obj = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql ="select * from usuario where login = ? and password =? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bean.getLogin());
			pstm.setString(2, bean.getPassword());
			
			log.info(">>" + pstm);
			
			rs = pstm.executeQuery();
			while(rs.next()){
				obj = new UsuarioBean();
				obj.setIdUsuario(rs.getString(1));
				obj.setNombres(rs.getString(2));
				obj.setApellidos(rs.getString(3));
				obj.setLogin(rs.getString(4));
				obj.setPassword(rs.getString(5));
				obj.setCorreo(rs.getString(6));
				obj.setDni(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!= null) rs.close();
				if(pstm!= null) pstm.close();
				if(conn!= null) conn.close();
			} catch (Exception e2) {
			}
		}
		
		return obj;
	}

	@Override
	public List<EnlaceBean> traerEnlacesDeUsuario(String idUsuario)	throws Exception {
		Connection conn= null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<EnlaceBean> data = new ArrayList<EnlaceBean>();
		EnlaceBean obj = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			String sql ="SELECT DISTINCT	r.idEnlace,	r.descripcion,	r.ruta 	FROM ENLACE r, ROL_ENLACE p, ROL t,USUARIO_ROL q WHERE r.idEnlace = p.idEnlace AND	p.idRol = t.idRol AND	t.idRol = q.idRol AND	q.idUsuario = ? ORDER BY 2";     
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, idUsuario);

			System.out.println(pstm);
			
			rs = pstm.executeQuery();
			while(rs.next()){
				obj = new EnlaceBean();
				obj.setIdEnlace(rs.getString(1));
				obj.setDescripcion(rs.getString(2));
				obj.setRuta(rs.getString(3));
				data.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!= null) rs.close();
				if(pstm!= null) pstm.close();
				if(conn!= null) conn.close();
			} catch (Exception e2) {
			}
		}
		
		return data;
	}


	
}

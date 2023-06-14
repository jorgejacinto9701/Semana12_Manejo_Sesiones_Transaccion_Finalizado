package dao;

import java.util.List;

import entidad.EnlaceBean;
import entidad.UsuarioBean;



public interface UsuarioDAO {
	
	

	public UsuarioBean  login(UsuarioBean bean) throws Exception;
	public abstract List<EnlaceBean> traerEnlacesDeUsuario(String idUsuario) throws Exception;
	
	
	
}


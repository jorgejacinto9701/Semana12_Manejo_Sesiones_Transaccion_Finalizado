package dao;

import java.util.List;

import entidad.Enlace;
import entidad.Usuario;



public interface UsuarioDAO {
	
	

	public Usuario  login(Usuario bean) throws Exception;
	public abstract List<Enlace> traerEnlacesDeUsuario(String idUsuario) throws Exception;
	
	
	
}


package fabricas;

import dao.UsuarioDAO;
import dao.impl.MySqlUsuarioDAO;


//Es una subfabrica que tiene objetos que acceden
//a la base de datos MYSQL
public class FabricaMysql extends Fabrica{

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new MySqlUsuarioDAO();
	}


}






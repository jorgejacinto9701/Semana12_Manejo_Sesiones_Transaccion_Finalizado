package fabricas;

import dao.BoletaDao;
import dao.UsuarioDAO;
import dao.impl.MySqlBoleta;
import dao.impl.MySqlUsuarioDAO;


//Es una subfabrica que tiene objetos que acceden
//a la base de datos MYSQL
public class FabricaMysql extends Fabrica{

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new MySqlUsuarioDAO();
	}

	@Override
	public BoletaDao getBoletaDao() {
		return new MySqlBoleta();
	}


}






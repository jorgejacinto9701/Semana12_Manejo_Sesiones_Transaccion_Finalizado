package fabricas;

import dao.BoletaDao;
import dao.UsuarioDAO;


//Es una f�brica de objetos
//Se usa el patr�n DAO (Data Access Object)
public abstract class Fabrica {

	public static final int MYSQL = 1;
	public static final int SQLSERVER = 2;

	//Se inscribe el dao alumno a las f�bricas
	public abstract UsuarioDAO getUsuarioDAO(); 
	public abstract BoletaDao getBoletaDao();
	
	//Va fabricar subfabricas (Mysql y sqlserver)
	public static Fabrica getFabrica(int tipo){
		Fabrica salida = null;
		switch(tipo){
			case MYSQL: 
				salida = new FabricaMysql();
				break;
			
		}
		return salida;
	}
}

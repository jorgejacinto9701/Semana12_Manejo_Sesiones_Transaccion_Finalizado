package entidad;

public class UsuarioBean {

	private String idUsuario, nombres, apellidos, login, password, correo,dni;
	

	public String getNombres() {
		return nombres;
	}

	public String getNombreCompleto() {
		StringBuilder sb = new StringBuilder(nombres);
		sb.append(" ").append(apellidos);
		return sb.toString();
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getCorreo() {
		return correo;
	}

	public String getDni() {
		return dni;
	}

	

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

}

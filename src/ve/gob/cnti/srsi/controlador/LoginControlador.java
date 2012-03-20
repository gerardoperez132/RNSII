package ve.gob.cnti.srsi.controlador;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Correo;
import ve.gob.cnti.srsi.modelo.Usuario;

@SuppressWarnings("serial")
public class LoginControlador extends DAO {

	private String correo;
	private String password;
	private Usuario usuario = new Usuario();
	private Correo user_correo = new Correo();

	public String autenticarUsuario() {

		// user_correo = (Correo) read(correo, correo);
		user_correo = getUserEmail(correo);
		if (user_correo == null || user_correo.getId_usuario() == 0) {
			addFieldError("correo", "Correo no existe");
			return INPUT;
		} else {
			usuario = (Usuario) read(usuario, user_correo.getId_usuario());
			if (usuario == null) {
				addFieldError("correo",
						"Ha ocurrido un problema recuperando sus datos!!!");
				return INPUT;
			} else if (!usuario.getClave().contains(password)) {
				addFieldError("password", "La clave no coincide con el correo");
				return INPUT;
			} else {
				return SUCCESS;
			}
		}
	}

	public String mostrarLogin() {
		return SUCCESS;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
}

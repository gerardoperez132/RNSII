/* This file is part of SRSI.
 * 
 * SRSI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * SRSI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SRSI. If not, see <http://www.gnu.org/licenses/>.
 */
package ve.gob.cnti.srsi.controlador;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.dao.MD5Hashing;
import ve.gob.cnti.srsi.modelo.Ente;
import ve.gob.cnti.srsi.modelo.Usuario;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class UsuarioControlador extends DAO {

	private Usuario usuario = new Usuario();
	private String clave_actual;
	private String clave_nueva;
	private String clave_nueva_confirme;
	private boolean modificarDatos;
	private boolean modificarClave;
	private int intentos_fallidos;

	@SuppressWarnings("rawtypes")
	private Map session;
	private Ente ente;

	@SkipValidation
	public String header() {
		session = ActionContext.getContext().getSession();
		Usuario usuario = new Usuario();
		usuario = (Usuario) session.get("usuario");
		if (usuario == null) {
			return "errorSession";
		}
		ente = (Ente) read(new Ente(), usuario.getId_ente());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String modificarClave() throws NoSuchAlgorithmException {
		if (header().equals("errorSession") == true) {
			return "errorSession";
		} else {
			usuario = (Usuario) session.get("usuario");
			if (clave_actual.isEmpty() || clave_nueva.isEmpty()
					|| clave_nueva_confirme.isEmpty()) {
				addFieldError("password",
						error.getProperties().getProperty("error.login.fields"));
				return INPUT;
			} else if (usuario.getClave().equals(
					new MD5Hashing(clave_actual).getPassword().toString())) {
				if (!clave_nueva.equals(clave_nueva_confirme)) {
					addFieldError("password", error.getProperties()
							.getProperty("error.login.password.match"));
					return INPUT;
				} else if (clave_nueva.length() < 6) {
					addFieldError("password", error.getProperties()
							.getProperty("error.login.password.length"));
					return INPUT;
				} else {
					MD5Hashing pass = new MD5Hashing(clave_nueva);
					usuario.setClave(pass.getPassword());
					update(usuario, usuario.getId_usuario());
					modificarClave = false;
					// TODO Esto no es un error. ¿Qué se hace con esto?
					addActionMessage("Clave modificada satifactoriamente");
					return SUCCESS;
				}
			} else {
				try {
					intentos_fallidos = (Integer) session
							.get("intentos_fallidos");
				} catch (Exception e) {
					intentos_fallidos = 0;
				}
				intentos_fallidos++;
				if (intentos_fallidos == 3) {
					addFieldError("password", error.getProperties()
							.getProperty("error.login.attempts"));
					return "errorSession";
				}
				session.put("intentos_fallidos", intentos_fallidos);
				addFieldError(
						"password",
						error.getProperties()
								.getProperty("error.login.password.attempt")
								.replace("{0}",
										String.valueOf(intentos_fallidos)));
				// TODO Agregar intentos_fallidos
				return INPUT;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public String modificarDatos() {
		if (header().equals("errorSession") == true) {
			return "errorSession";
		} else {
			Usuario user = (Usuario) session.get("usuario");
			usuario.setClave(user.getClave());
			usuario.setId_correo(user.getId_correo());
			usuario.setId_ente(user.getId_ente());
			update(usuario, user.getId_usuario());
			session.remove("usuario");
			usuario = (Usuario) read(usuario, user.getId_usuario());
			session.put("usuario", usuario);
			modificarDatos = false;
			// TODO Otro mensaje.
			addActionMessage("Datos modificados satisfactoriamente");
		}
		return SUCCESS;
	}

	@SkipValidation
	public String configuracion() {
		return header();
	}

	@SkipValidation
	public String prepararFormulario() {
		header();
		usuario = (Usuario) session.get("usuario");
		if (modificarDatos = true) {
			usuario = (Usuario) read(usuario, usuario.getId_usuario());
		}
		return SUCCESS;
	}

	public void validate() {
		if (modificarDatos) {
			long ci;
			if (usuario.getNombre().trim().isEmpty()
					|| usuario.getApellido().trim().isEmpty()
					|| usuario.getCedula().trim().isEmpty()) {
				addFieldError("datos",
						error.getProperties().getProperty("error.login.fields"));
			}
			if (usuario.getNombre().length() < 4) {
				addFieldError("nombres",
						error.getProperties().getProperty("error.login.nombre"));
			}
			if (usuario.getApellido().length() < 4) {
				addFieldError(
						"apellidos",
						error.getProperties().getProperty(
								"error.login.apellido"));
			}
			if (!(usuario.getCedula().length() >= 4 && usuario.getCedula()
					.length() <= 9)) {
				addFieldError("cedula",
						error.getProperties().getProperty("error.login.cedula"));
			} else {
				try {
					ci = Integer.parseInt(usuario.getCedula());
					if (ci < 0) {
						addFieldError("cedula", error.getProperties()
								.getProperty("error.login.cedula.invalid"));
					}
				} catch (Exception e) {
					addFieldError(
							"cedula",
							error.getProperties().getProperty(
									"error.login.cedula.regex"));
				}
			}
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isModificarDatos() {
		return modificarDatos;
	}

	public void setModificarDatos(boolean modificarDatos) {
		this.modificarDatos = modificarDatos;
	}

	public boolean isModificarClave() {
		return modificarClave;
	}

	public void setModificarClave(boolean modificarClave) {
		this.modificarClave = modificarClave;
	}

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public String getClave_actual() {
		return clave_actual;
	}

	public void setClave_actual(String clave_actual) {
		this.clave_actual = clave_actual;
	}

	public String getClave_nueva() {
		return clave_nueva;
	}

	public void setClave_nueva(String clave_nueva) {
		this.clave_nueva = clave_nueva;
	}

	public int getIntentos_fallidos() {
		return intentos_fallidos;
	}

	public void setIntentos_fallidos(int intentos_fallidos) {
		this.intentos_fallidos = intentos_fallidos;
	}

	public String getClave_nueva_confirme() {
		return clave_nueva_confirme;
	}

	public void setClave_nueva_confirme(String clave_nueva_confirme) {
		this.clave_nueva_confirme = clave_nueva_confirme;
	}
}
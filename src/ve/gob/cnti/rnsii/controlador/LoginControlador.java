/* This file is part of RNSII.
 * 
 * RNSII is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * RNSII is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with RNSII. If not, see <http://www.gnu.org/licenses/>.
 */
package ve.gob.cnti.rnsii.controlador;

import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.rnsii.dao.DAO;
import ve.gob.cnti.rnsii.mail.EnviarCorreo;
import ve.gob.cnti.rnsii.mail.Mail;
import ve.gob.cnti.rnsii.modelo.Correo;
import ve.gob.cnti.rnsii.modelo.Ente;
import ve.gob.cnti.rnsii.modelo.Estado;
import ve.gob.cnti.rnsii.modelo.RecuperarClave;
import ve.gob.cnti.rnsii.modelo.ServicioInformacion;
import ve.gob.cnti.rnsii.modelo.Usuario;
import ve.gob.cnti.rnsii.util.EstadosTiempo;
import ve.gob.cnti.rnsii.util.MD5Hashing;
import ve.gob.cnti.rnsii.util.ReadXmlTime;
import ve.gob.cnti.rnsii.util.ServiciosPublicables;

import com.opensymphony.xwork2.ActionContext;

/**
 * Esta clase es el controlador del inicio de sesión del usuario.
 * 
 * @author Richard Ricciardelli
 * @author Joaquín Pereira
 * 
 */
@SuppressWarnings("serial")
public class LoginControlador extends DAO implements ServletRequestAware {

	private String correo;
	private String password;
	private String captcha;
	private Usuario usuario = new Usuario();
	private Correo user_correo = new Correo();
	private Ente ente = new Ente();
	private List<ServicioInformacion> servicios = new ArrayList<ServicioInformacion>();
	private List<ServiciosPublicables> ListaServicios = new ArrayList<ServiciosPublicables>();
	private List<Estado> estados = new ArrayList<Estado>();
	@SuppressWarnings("rawtypes")
	private Map session;
	private boolean recoveryPass;
	private boolean recoveryPassForm;
	private boolean datosEnviados;
	private String cuenta;
	private String clave_nueva;
	private String clave_nueva_confirme;
	private long peticionesNoLeidas;
	private long peticionesPendientes;
	private long solicitudesAceptadasRechazadas;
	private List<EstadosTiempo> estadosTiempo = new ArrayList<EstadosTiempo>();
	private Date fecha;
	private String msj_error;
	private String msj_actionInfo;

	@SuppressWarnings("unchecked")
	public String autenticarUsuario() throws Exception {

		session = ActionContext.getContext().getSession();
		if (correo == null && password == null && captcha == null) {
			return "404ERROR";
		}
		if (correo.isEmpty() || password.isEmpty() || captcha.isEmpty()) {
			msj_error = error.getProperties().getProperty("error.login.fields");
			return INPUT;
		}
		if (!((String) session.get("captcha")).toUpperCase().equals(
				captcha.toUpperCase())) {
			msj_error = error.getProperties()
					.getProperty("error.login.captcha");
			return INPUT;
		}
		if (!correo.matches(REGEX_EMAIL)) {
			msj_error = error.getProperties().getProperty("error.regex.email");
			return INPUT;
		}
		user_correo = (Correo) getUserEmail(correo);
		if (user_correo == null) {
			msj_error = error.getProperties()
					.getProperty("error.login.invalid");
			return INPUT;
		} else {
			usuario = (Usuario) read(usuario, user_correo.getId_usuario());
			if (usuario == null) {
				msj_error = error.getProperties().getProperty(
						"error.login.invalid");
				return INPUT;
			} else if (!usuario.getClave().equals(
					new MD5Hashing(password).getPassword().toString())) {
				msj_error = error.getProperties().getProperty(
						"error.login.invalid");
				return INPUT;
			} else {
				Ente ente = new Ente();
				ente = (Ente) read(ente, usuario.getId_ente());
				session.put("logueado", true);
				session.put("usuario", usuario);
				session.put("ente_sesion", ente);
				return SUCCESS;
			}
		}
	}

	@SkipValidation
	public String mostrarLogin() {

		return SUCCESS;
	}

	@SkipValidation
	public String inicio() {

		return SUCCESS;
	}

	// TODO Leer cantidad de peticiones a servicios
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String home() {

		session = ActionContext.getContext().getSession();
		boolean publicable = true;
		if (session.isEmpty()) {
			return INPUT;
		} else {
			usuario = (Usuario) session.get("usuario");
			if (usuario == null) {
				return INPUT;
			}
			ente = (Ente) read(ente, usuario.getId_ente());
			session.put("ente", ente);
			estados = (List<Estado>) read(new Estado());
			Object[] objetos = { new ServicioInformacion(), new Ente() };
			servicios = (ArrayList<ServicioInformacion>) read(objetos,
					ente.getId_ente(), -1);
			Iterator<ServicioInformacion> siIterado = servicios.iterator();
			ServicioInformacion servicio = new ServicioInformacion();
			while (siIterado.hasNext()) {
				publicable = true;
				servicio = siIterado.next();
				if (!isComplete(servicio)) {
					ListaServicios.add(new ServiciosPublicables(false,
							servicio, getIncompleteFields(servicio)));
				} else {
					ListaServicios.add(new ServiciosPublicables(publicable,
							servicio, null));
				}
			}
		}
		peticionesNoLeidas = peticionesSuscripcion(ente.getId_ente());
		peticionesPendientes = peticionesSuscripcionPendientes(ente
				.getId_ente());
		solicitudesAceptadasRechazadas = getNumeroSuscrionesAceptadasRechazadas(ente
				.getId_ente());
		return SUCCESS;
	}

	@SkipValidation
	public String desloguearUsuario() {

		session = ActionContext.getContext().getSession();
		session.clear();
		return SUCCESS;
	}

	@SkipValidation
	public String enviarDatos() throws NoSuchAlgorithmException,
			UnknownHostException {

		recoveryPass = true;
		session = ActionContext.getContext().getSession();
		if (correo.isEmpty() || captcha.isEmpty()) {
			msj_error = error.getProperties().getProperty("error.login.fields");
			return INPUT;
		}
		if (!correo.matches(REGEX_EMAIL)) {
			msj_error = error.getProperties().getProperty("error.regex.email");
			return INPUT;
		}
		if (!((String) session.get("captcha")).toUpperCase().equals(
				captcha.toUpperCase())) {
			msj_error = error.getProperties()
					.getProperty("error.login.captcha");
			return INPUT;
		}
		user_correo = (Correo) getUserEmail(correo);
		if (user_correo == null) {
			msj_error = error.getProperties()
					.getProperty("error.email.invalid");
			return INPUT;
		} else {
			usuario = (Usuario) read(usuario, user_correo.getId_usuario());
			if (usuario == null) {
				msj_error = error.getProperties().getProperty(
						"error.user.invalid");
				return INPUT;
			}
		}
		RecuperarClave r_clave = new RecuperarClave();
		Object[] models = { new RecuperarClave(), new Usuario() };
		if (read(models, usuario.getId_usuario())) {
			r_clave = (RecuperarClave) readf(models, usuario.getId_usuario());
			if (read(models, usuario.getId_usuario())) {
				if ((new Date().getTime() - r_clave.getFecha_creado().getTime()) > 172800000) {
					delete(r_clave, r_clave.getId_recuperar_clave());
					r_clave = new RecuperarClave();
				} else {
					msj_error = error.getProperties().getProperty(
							"error.request.isProccess");
					// String ruta =
					// LoginControlador.class.getProtectionDomain().getCodeSource().getLocation().getPath();
					// System.out.println("ruta: "+ruta);
					datosEnviados = true;
					recoveryPass = false;
					return INPUT;
				}
			}
		}
		MD5Hashing mail = new MD5Hashing(user_correo.getCorreo());
		MD5Hashing id = new MD5Hashing(getNextId(r_clave) + "");
		Mail sendmail = new Mail();
		// TODO Internacionalizar estos códigos.

		String asunto = "RNSII - Restablecer Contraseña";
		String mensaje = "\n\nEstimado "
				+ usuario.getNombre()
				+ " si se le ha olvidado su contraseña puede acceder al suguiente link para cambiarla por una nueva: \n\n"
				+ sendmail.getProperties().getProperty("domain")
				+ "pages/recuperarClave?cuenta="
				+ mail.getPassword().toString() + id.getPassword().toString();
		r_clave.setId_usuario(usuario.getId_usuario());
		r_clave.setUrl(mail.getPassword().toString()
				+ id.getPassword().toString());
		datosEnviados = true;
		recoveryPass = false;
		EnviarCorreo enviarMail = new EnviarCorreo();
		if (!enviarMail.send(user_correo.getCorreo(), asunto, mensaje)) {
			msj_error = error.getProperties().getProperty("error.email.fail");
		} else {
			create(r_clave);
			// TODO
			msj_actionInfo = "Su petición de recuperar la contraseña ya ha sido procesada, por favor revise su bandeja de correo para que proceda al cambio de su clave de ingreso";
		}
		return SUCCESS;
	}

	@SkipValidation
	public String prepararRecuperarPass() {

		RecuperarClave r_clave = new RecuperarClave();
		datosEnviados = true;
		if (cuenta == null) {
			datosEnviados = false;
			recoveryPass = true;
			return SUCCESS;
		}
		if (getUrlRecoveryPass(new RecuperarClave(), cuenta) != null) {
			r_clave = (RecuperarClave) getUrlRecoveryPass(new RecuperarClave(),
					cuenta);
			if ((new Date().getTime() - r_clave.getFecha_creado().getTime()) > 172800000) {
				delete(r_clave, r_clave.getId_recuperar_clave());
				r_clave = new RecuperarClave();
				msj_error = error.getProperties().getProperty(
						"error.recovery.old");
				return INPUT;
			} else {
				recoveryPassForm = true;
				datosEnviados = false;
				return SUCCESS;
			}
		} else {
			msj_error = error.getProperties().getProperty(
					"error.recovery.invalid");
			return INPUT;
		}
	}

	@SkipValidation
	public String modificarClave() throws NoSuchAlgorithmException {

		RecuperarClave r_clave = new RecuperarClave();
		r_clave = (RecuperarClave) getUrlRecoveryPass(new RecuperarClave(),
				cuenta);
		datosEnviados = true;
		if (!clave_nueva.equals(clave_nueva_confirme)) {
			msj_error = "Las contraseñas no coinciden";
			return INPUT;
		} else if (clave_nueva.length() < 6) {
			msj_error = "La nueva contraseña debe tener al menos 6 caracteres";
			return INPUT;
		} else {
			MD5Hashing pass = new MD5Hashing(clave_nueva);
			usuario = (Usuario) read(usuario, r_clave.getId_usuario());
			usuario.setClave(pass.getPassword());
			delete(r_clave, r_clave.getId_recuperar_clave());
			update(usuario, usuario.getId_usuario());
			msj_actionInfo = "Clave modificada satifactoriamente";
			return SUCCESS;
		}
	}

	public void validate() {

	}

	public String execute() throws Exception {
		// if the method * does not exist, we will return a "404ERROR" result
		return "404ERROR";

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

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public List<ServiciosPublicables> getListaServicios() {
		return ListaServicios;
	}

	public void setListaServicios(List<ServiciosPublicables> listaServicios) {
		ListaServicios = listaServicios;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public boolean isRecoveryPass() {
		return recoveryPass;
	}

	public void setRecoveryPass(boolean recoveryPass) {
		this.recoveryPass = recoveryPass;
	}

	public boolean isDatosEnviados() {
		return datosEnviados;
	}

	public void setDatosEnviados(boolean datosEnviados) {
		this.datosEnviados = datosEnviados;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public boolean isRecoveryPassForm() {
		return recoveryPassForm;
	}

	public void setRecoveryPassForm(boolean recoveryPassForm) {
		this.recoveryPassForm = recoveryPassForm;
	}

	public String getClave_nueva() {
		return clave_nueva;
	}

	public void setClave_nueva(String clave_nueva) {
		this.clave_nueva = clave_nueva;
	}

	public String getClave_nueva_confirme() {
		return clave_nueva_confirme;
	}

	public void setClave_nueva_confirme(String clave_nueva_confirme) {
		this.clave_nueva_confirme = clave_nueva_confirme;
	}

	public long getPeticionesNoLeidas() {
		return peticionesNoLeidas;
	}

	public void setPeticionesNoLeidas(long peticionesNoLeidas) {
		this.peticionesNoLeidas = peticionesNoLeidas;
	}

	public long getPeticionesPendientes() {
		return peticionesPendientes;
	}

	public void setPeticionesPendientes(long peticionesPendientes) {
		this.peticionesPendientes = peticionesPendientes;
	}

	public long getSolicitudesAceptadasRechazadas() {
		return solicitudesAceptadasRechazadas;
	}

	public void setSolicitudesAceptadasRechazadas(
			long solicitudesAceptadasRechazadas) {
		this.solicitudesAceptadasRechazadas = solicitudesAceptadasRechazadas;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public List<EstadosTiempo> getEstadosTiempo() {
		return estadosTiempo;
	}

	public void setEstadosTiempo(List<EstadosTiempo> estadosTiempo) {
		this.estadosTiempo = estadosTiempo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMsj_error() {
		return msj_error;
	}

	public void setMsj_error(String msj_error) {
		this.msj_error = msj_error;
	}

	public String getMsj_actionInfo() {
		return msj_actionInfo;
	}

	public void setMsj_actionInfo(String msj_actionInfo) {
		this.msj_actionInfo = msj_actionInfo;
	}

}

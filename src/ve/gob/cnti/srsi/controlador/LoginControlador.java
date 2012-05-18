package ve.gob.cnti.srsi.controlador;

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

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.dao.EnviarCorreo;
import ve.gob.cnti.srsi.dao.MD5Hashing;
import ve.gob.cnti.srsi.modelo.Correo;
import ve.gob.cnti.srsi.modelo.Ente;
import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Estado;
import ve.gob.cnti.srsi.modelo.Funcionalidad;
import ve.gob.cnti.srsi.modelo.RecuperarClave;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.Usuario;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class LoginControlador extends DAO implements ServletRequestAware {

	private String correo;
	private String password;
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

	@SuppressWarnings("unchecked")
	public String autenticarUsuario() throws Exception {
		session = ActionContext.getContext().getSession();
		if (correo == null && password == null) {
			return "404ERROR";			
		} else if (correo.isEmpty() || password.isEmpty()) {
			addFieldError("error",
					error.getProperties().getProperty("error.login.fields"));
			return INPUT;
		}
		user_correo = (Correo) getUserEmail(correo);
		if (user_correo == null) {
			addFieldError("correo",
					error.getProperties().getProperty("error.login.invalid"));
			return INPUT;
		} else {
			usuario = (Usuario) read(usuario, user_correo.getId_usuario());
			if (usuario == null) {
				addFieldError("error",
						error.getProperties()
								.getProperty("error.login.invalid"));
				return INPUT;
			} else if (!usuario.getClave().equals(
					new MD5Hashing(password).getPassword().toString())) {
				addFieldError("password",
						error.getProperties()
								.getProperty("error.login.invalid"));
				return INPUT;
			} else {
				session.put("logueado", true);
				session.put("usuario", usuario);
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
				servicio = siIterado.next();
				if (servicio.getId_estado() == 1) {
					ListaServicios
							.add(new ServiciosPublicables(false, servicio));
				} else if (!isComplete(servicio)) {
					ListaServicios
							.add(new ServiciosPublicables(false, servicio));
				} else {
					Object[] models = { new Funcionalidad(),
							new ServicioInformacion() };
					List<Funcionalidad> funcionalidades = (List<Funcionalidad>) read(
							models, servicio.getId_servicio_informacion(), -1);
					if (funcionalidades.isEmpty()) {
						ListaServicios.add(new ServiciosPublicables(false,
								servicio));
					} else {
						Iterator<Funcionalidad> fxIterado = funcionalidades
								.iterator();
						Funcionalidad fx = new Funcionalidad();
						while (fxIterado.hasNext()) {
							fx = fxIterado.next();
							Object[] models2 = { new EntradaSalida(),
									new Funcionalidad() };
							List<EntradaSalida> salidas_tmp = (List<EntradaSalida>) read(
									models2, fx.getId_funcionalidad(), SALIDA);
							if (salidas_tmp.isEmpty()) {
								publicable = false;
							}
						}
						ListaServicios.add(new ServiciosPublicables(publicable,
								servicio));
					}
				}
			}
		}
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
		user_correo = (Correo) getUserEmail(correo);
		recoveryPass = true;
		// TODO Validar las comillas simples.
		if (correo.matches(REGEX_QUOTES)) {
			addFieldError("correo",
					error.getProperties().getProperty("error.regex.email")
							+ "QUOTES");
			return INPUT;
		}
		if (!correo.matches(REGEX_EMAIL)) {
			addFieldError("correo",
					error.getProperties().getProperty("error.regex.email"));
			/**
			 * var s = ' function(){ return " Is big \\"problem\\", \\no? "; }';
			 * var m = s.match(/"(?:[^"\\]|\\.)*"/); if (m != null) alert(m);
			 */
			return INPUT;
		}
		if (user_correo == null) {
			addFieldError("correo",
					error.getProperties().getProperty("error.email.invalid"));
			return INPUT;
		} else {
			usuario = (Usuario) read(usuario, user_correo.getId_usuario());
			if (usuario == null) {
				addFieldError("error",
						error.getProperties().getProperty("error.user.invalid"));
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
					addFieldError(
							"error",
							error.getProperties().getProperty(
									"error.request.isProccess"));
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

		String asunto = "SRSI - Restablecer Contraseña";
		String mensaje = "\n\nEstimado "
				+ usuario.getNombre()
				+ " si se le ha olvidado su contraseña puede acceder al suguiente link para cambiarla por una nueva: \n\n"
				+ error.getProperties().getProperty("dominio")
				+ "pages/recuperarClave?cuenta="
				+ mail.getPassword().toString() + id.getPassword().toString();
		System.out.println(mensaje);
		r_clave.setId_usuario(usuario.getId_usuario());
		r_clave.setUrl(mail.getPassword().toString()
				+ id.getPassword().toString());
		datosEnviados = true;
		recoveryPass = false;
		EnviarCorreo enviarMail = new EnviarCorreo();
		if (!enviarMail.send(user_correo.getCorreo(), asunto, mensaje)) {
			addFieldError("error",
					error.getProperties().getProperty("error.email.fail"));
		} else {
			create(r_clave);
			System.out.println("m " + mensaje);
			addActionMessage("Su petición de recuperar la contraseña ya ha sido procesada, por favor revise su bandeja de correo para que proceda al cambio de su clave de ingreso");
		}
		return SUCCESS;
	}

	// TODO
	@SkipValidation
	public String prepararRecuperarPass() {
		RecuperarClave r_clave = new RecuperarClave();
		datosEnviados = true;
		if (cuenta == null) {
			datosEnviados = false;
			recoveryPass = true;
			return SUCCESS;
		}
		System.out.println("cuenta: " + cuenta);
		if (getUrlRecoveryPass(new RecuperarClave(), cuenta) != null) {
			r_clave = (RecuperarClave) getUrlRecoveryPass(new RecuperarClave(),
					cuenta);
			if ((new Date().getTime() - r_clave.getFecha_creado().getTime()) > 172800000) {
				delete(r_clave, r_clave.getId_recuperar_clave());
				r_clave = new RecuperarClave();
				addFieldError("error",
						error.getProperties().getProperty("error.recovery.old"));
				return INPUT;
			} else {
				recoveryPassForm = true;
				datosEnviados = false;
				return SUCCESS;
			}
		} else {
			addFieldError("error",
					error.getProperties().getProperty("error.recovery.invalid"));
			return INPUT;
		}
	}

	@SkipValidation
	public String modificarClave() throws NoSuchAlgorithmException {
		// TODO
		// IMPLEMENTAR CAPTCHA A LOS TRES INTENTOS
		RecuperarClave r_clave = new RecuperarClave();
		r_clave = (RecuperarClave) getUrlRecoveryPass(new RecuperarClave(),
				cuenta);
		datosEnviados = true;
		if (!clave_nueva.equals(clave_nueva_confirme)) {
			addFieldError("password", "Las contraseñas no coinciden");
			return INPUT;
		} else if (clave_nueva.length() < 6) {
			addFieldError("password",
					"La nueva contraseña debe tener al menos 6 caracteres");
			return INPUT;
		} else {
			MD5Hashing pass = new MD5Hashing(clave_nueva);
			usuario = (Usuario) read(usuario, r_clave.getId_usuario());
			usuario.setClave(pass.getPassword());
			delete(r_clave, r_clave.getId_recuperar_clave());
			update(usuario, usuario.getId_usuario());
			addActionMessage("Clave modificada satifactoriamente");
			return SUCCESS;
		}
	}

	public void validate() {
		
	}
	
	public String execute() throws Exception {
	    // if the method * does not exist, we will return a "404ERROR" result
		System.out.println("entro 2");
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
}

class ServiciosPublicables {

	boolean publicable;
	ServicioInformacion servicio = new ServicioInformacion();

	public ServiciosPublicables(boolean publicable, ServicioInformacion servicio) {
		super();
		this.publicable = publicable;
		this.servicio = servicio;
	}

	public boolean isPublicable() {
		return publicable;
	}

	public void setPublicable(boolean publicable) {
		this.publicable = publicable;
	}

	public ServicioInformacion getServicio() {
		return servicio;
	}

	public void setServicio(ServicioInformacion servicio) {
		this.servicio = servicio;
	}
}
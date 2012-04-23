package ve.gob.cnti.srsi.controlador;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.dao.MD5Hashing;
import ve.gob.cnti.srsi.modelo.Correo;
import ve.gob.cnti.srsi.modelo.Ente;
import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Funcionalidad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.Usuario;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class LoginControlador extends DAO {

	private String correo;
	private String password;
	private Usuario usuario = new Usuario();
	private Correo user_correo = new Correo();
	private Ente ente = new Ente();
	private List<ServicioInformacion> servicios = new ArrayList<ServicioInformacion>();
	private List<ServiciosPublicables> ListaServicios = new ArrayList<ServiciosPublicables>();
	@SuppressWarnings("rawtypes")
	private Map session;

	@SuppressWarnings("unchecked")
	public String autenticarUsuario() throws NoSuchAlgorithmException {
		user_correo = (Correo) getUserEmail(correo);
		if (user_correo == null) {
			addFieldError("correo", "Su usuario o contraseña son inválidos");
			return INPUT;
		} else {
			usuario = (Usuario) read(usuario, user_correo.getId_usuario());
			if (usuario == null) {
				addFieldError("error",
						"Ha ocurrido un problema recuperando sus datos!!!");
				return INPUT;
			} else if (!usuario.getClave().equals(
					new MD5Hashing(password).getPassword().toString())) {
				addFieldError("password",
						"Su usuario o contraseña son inválidos");
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

	public void validate() {
		session = ActionContext.getContext().getSession();
		if (correo == null && password == null) {
			addFieldError("error", "Debe autenticarse para entrar al sistema");
		} else if (correo.isEmpty() || password.isEmpty()) {
			addFieldError("error", "Debe insertar todos los campos");
		}
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
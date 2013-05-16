package ve.gob.cnti.rnsii.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import ve.gob.cnti.rnsii.dao.DAO;
import ve.gob.cnti.rnsii.modelo.Ente;
import ve.gob.cnti.rnsii.modelo.Nacionalidad;
import ve.gob.cnti.rnsii.modelo.ServicioInformacion;
import ve.gob.cnti.rnsii.modelo.Usuario;
import ve.gob.cnti.rnsii.util.MD5Hashing;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class Administration extends DAO implements ServletRequestAware {	
	private int pg_si;
	private boolean listarPublicados;
	@SuppressWarnings("rawtypes")
	private Map session;	
	private String password;
	private String captcha;
	private String msj_error;
	private int accion_usuario;
	private Usuario usuario = new Usuario();
	private Ente ente = new Ente();
	private List<Nacionalidad> nacionalidad = new ArrayList<Nacionalidad>();
	private List<Ente> entes = new ArrayList<Ente>();	
	private List<ServicioInformacion> servicios = new ArrayList<ServicioInformacion>();
	
	
	//Da acceso al formulario de control de acceso
	public String access(){			
		return SUCCESS;
	}
	
	
	//Da acceso a la vista principal del administrador
	@SuppressWarnings("unchecked")
	public String admin(){		
		servicios = getSIListPorPublicarDespublicar(pg_si,listarPublicados);
		entes = (List<Ente>) read(new Ente());		
		return SUCCESS;
	}	
	
	//Controla el acceso al modulo de administracción	
	@SuppressWarnings("unchecked")
	public String access_control() throws Exception {		
		session = ActionContext.getContext().getSession();	
		if (password == null && captcha == null) {
			msj_error = error.getProperties().getProperty("error.login.fields");			
			return INPUT;
		}
		if (password.isEmpty() || captcha.isEmpty()) {
			msj_error = error.getProperties().getProperty("error.login.fields");			
			return INPUT;
		}
		if (!password.equals("123456")) {
			 msj_error = error.getProperties().getProperty(
			 "error.login.invalid");			 
			 return INPUT;
		 } 
		 session.put("logueado", true);
		 session.put("usuario", usuario);
		 session.put("admin", true);
		 return SUCCESS;	
	}
	
	//Cierra la sesión del administrador
	public String close_access() {		
		session = ActionContext.getContext().getSession();
		session.clear();
		return SUCCESS;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getMsj_error() {
		return msj_error;
	}

	public void setMsj_error(String msj_error) {
		this.msj_error = msj_error;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// 		
	}

	public List<Nacionalidad> getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(List<Nacionalidad> nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public List<Ente> getEntes() {
		return entes;
	}

	public void setEntes(List<Ente> entes) {
		this.entes = entes;
	}

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public int getAccion_usuario() {
		return accion_usuario;
	}

	public void setAccion_usuario(int accion_usuario) {
		this.accion_usuario = accion_usuario;
	}

	public List<ServicioInformacion> getServicios() {
		return servicios;
	}

	public void setServicios(List<ServicioInformacion> servicios) {
		this.servicios = servicios;
	}

	public int getPg_si() {
		return pg_si;
	}

	public void setPg_si(int pg_si) {
		this.pg_si = pg_si;
	}


	public boolean isListarPublicados() {
		return listarPublicados;
	}


	public void setListarPublicados(boolean listarPublicados) {
		this.listarPublicados = listarPublicados;
	}

}

package ve.gob.cnti.rnsii.admin;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.rnsii.dao.DAO;
import ve.gob.cnti.rnsii.mail.EnviarCorreo;
import ve.gob.cnti.rnsii.modelo.Correo;
import ve.gob.cnti.rnsii.modelo.Ente;
import ve.gob.cnti.rnsii.modelo.Nacionalidad;
import ve.gob.cnti.rnsii.modelo.Usuario;
import ve.gob.cnti.rnsii.util.MD5Hashing;

@SuppressWarnings("serial")
public class UsuarioControl extends DAO  {
	
	private int accion_usuario;
	private String correo;
	private Usuario usuario = new Usuario();
	private Ente ente = new Ente();	
	private List<Nacionalidad> nacionalidad = new ArrayList<Nacionalidad>();
	private List<Ente> entes = new ArrayList<Ente>();	
	
	//Prepara el formulario para registrar un nuevo usuario
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String registrar_usuario() {		
		accion_usuario = 1;
		nacionalidad = (List<Nacionalidad>) read(new Nacionalidad());
		entes = (List<Ente>) read(new Ente());				
		return SUCCESS;
	}
	
	//Registrar un nuevo usuario
	public String registrar_usuario_execute() {
		try {
			accion_usuario = 0;
			String code = RandomStringUtils.randomAlphanumeric(8);
			Correo email = new Correo();
			MD5Hashing pass = new MD5Hashing(code.toUpperCase().toString());
			
			//1- Enviar al usuario un correo con la clave generada aleatoriamente 
			EnviarCorreo enviarMail = new EnviarCorreo();
			try {
				String asunto = "Datos de Registro en el RNSII";
				String mensaje= "Estimado usuario "+usuario.getNombre() +" la clave para"
						+" ingresar al sistema es: "+code.toString().toUpperCase() +" le recomendamos "
						+"que cambie su clave de ingreso lo mas pronto posible.";
				enviarMail.send(correo, asunto, mensaje);
			} catch (Exception e) {
				accion_usuario = -1;	
				addActionMessage("Ha ocurrido un error en el registro de usuario.  "+e.toString());
				return "error";
			}
			
			//2- Seteando correo y usuario para guardarlos en la BD
			email.setId_usuario(getNextId(usuario));
			email.setCorreo(correo);						
			usuario.setId_correo(getNextId(email));
			usuario.setClave(pass.getPassword());
			create(usuario);
			create(email);
			addActionMessage("Usuario registrado satisfactoriamente.");
			return SUCCESS;
		} catch (Exception e) {
			accion_usuario = -1;	
			addActionMessage(e.toString());
			return "error";
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void validate() {		
		long ci;
		if (usuario.getNombre().trim().isEmpty() || usuario.getApellido().trim().isEmpty()
				|| usuario.getCedula().trim().isEmpty() || correo.trim().isEmpty() || 
				(usuario.getId_ente() == -1 && accion_usuario==1))  {
			addFieldError("datos",error.getProperties().getProperty("error.login.fields"));
		}
		if (usuario.getNombre().trim().isEmpty()) {
			addFieldError("nombres",error.getProperties().getProperty("error.required"));
		}else if (usuario.getNombre().length() < 4) {
			addFieldError("nombres",error.getProperties().getProperty("error.login.nombre"));
		}else if (!usuario.getNombre().toUpperCase().matches(REGEX_TITLE)) {
			addFieldError("nombres",error.getProperties().getProperty("error.regex.title"));
		}
		
		if (usuario.getApellido().trim().isEmpty()) {
			addFieldError("apellidos",error.getProperties().getProperty("error.required"));
		}else if (usuario.getApellido().length() < 4) {
			addFieldError("apellidos",error.getProperties().getProperty("error.login.apellido"));
		}else if (!usuario.getApellido().toUpperCase().matches(REGEX_TITLE)) {
			addFieldError("apellidos",error.getProperties().getProperty("error.regex.title"));
		}	
			
		if(usuario.getCedula().trim().isEmpty()) {
			addFieldError("cedula",error.getProperties().getProperty("error.required"));
		}else{
			try {
				ci = Integer.parseInt(usuario.getCedula());
				// Verifica que la cedula sea entero positivo y mayor o igual a
				// 1
				if (ci < 1 || !usuario.getCedula().equals("" + ci)) {
					addFieldError("cedula",	error.getProperties().getProperty("error.login.cedula.invalid"));
				}else if(getUserCI(usuario.getCedula())!=null){
					addFieldError("cedula",error.getProperties().getProperty("error.ci.repeated"));
				}
			} catch (Exception e) {
				addFieldError("cedula",error.getProperties().getProperty("error.login.cedula.regex"));
			}
		}
		if(correo==null){
			addFieldError("correo",error.getProperties().getProperty("error.required"));
		}else if(correo.trim().isEmpty()){
			addFieldError("correo",error.getProperties().getProperty("error.required"));
		}else if (!correo.matches(REGEX_EMAIL)) {
			addFieldError("correo",error.getProperties().getProperty("error.regex.email"));			
		}else if(getUserEmail(correo)!=null){
			addFieldError("correo",error.getProperties().getProperty("error.email.repeated"));
		}	
		
		if(usuario.getId_ente() == -1 && accion_usuario==1){
			addFieldError("ente",error.getProperties().getProperty("error.required"));
		}
		
		nacionalidad = (List<Nacionalidad>) read(new Nacionalidad());	
		entes = (List<Ente>) read(new Ente());				
	}

	public int getAccion_usuario() {
		return accion_usuario;
	}
	public void setAccion_usuario(int accion_usuario) {
		this.accion_usuario = accion_usuario;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Ente getEnte() {
		return ente;
	}
	public void setEnte(Ente ente) {
		this.ente = ente;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}	
}

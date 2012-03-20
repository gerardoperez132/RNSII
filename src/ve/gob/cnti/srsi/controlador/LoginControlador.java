package ve.gob.cnti.srsi.controlador;


import com.opensymphony.xwork2.ActionContext;

import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;



import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Correo;
import ve.gob.cnti.srsi.modelo.Ente;
import ve.gob.cnti.srsi.modelo.Usuario;

@SuppressWarnings("serial")
public class LoginControlador extends DAO {
		
	private String correo;
    private String password;
    private Usuario usuario = new Usuario();
    private Correo user_correo = new Correo();
    private Ente ente = new Ente(); 
	@SuppressWarnings("rawtypes")
	private Map session;
		
	@SuppressWarnings("unchecked")
	public String autenticarUsuario(){		
		user_correo = (Correo) getUserEmail(correo);
		if(user_correo == null){
			addFieldError("correo","Correo no existe");
			return INPUT;
		}else{
			usuario = (Usuario) read(usuario, user_correo.getId_usuario());
			if(usuario==null){
				addFieldError("error","Ha ocurrido un problema recuperando sus datos!!!");
				return INPUT;
			}else if(!usuario.getClave().equals(password)){
				addFieldError("password","La clave no coincide con el correo");
				return INPUT;
			}else{				
				session.put("logueado",true); 
    			session.put("usuario",usuario);
				return SUCCESS;
			}
		}			
	}
	
	@SkipValidation
	public String mostrarLogin(){
		return SUCCESS;
	}
	
	@SkipValidation
	public String inicio(){		
		return SUCCESS;
	}
	
	@SkipValidation
	public String home(){
		session = ActionContext.getContext().getSession();  
		if(session.isEmpty()){
			return INPUT;
		}else{
			usuario = (Usuario) session.get("usuario");			
			ente = (Ente) read(ente, usuario.getId());			
		}
		return SUCCESS;
	}	
	
	@SkipValidation
	public String desloguearUsuario(){	 
		session = ActionContext.getContext().getSession();
	    session.clear();
		return SUCCESS;
	}
	
	public void validate(){
		session = ActionContext.getContext().getSession();		
		if(correo == null &&  password == null){
			addFieldError("error","Debe autenticarse para entrar al sistema");
		}else if(correo.isEmpty() || password.isEmpty()){
			addFieldError("error","Debe insertar todos los campos");
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
}

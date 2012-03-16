package ve.gob.cnti.srsi.controlador;


import com.opensymphony.xwork2.ActionContext;

import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;



import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Correo;
import ve.gob.cnti.srsi.modelo.Usuario;

@SuppressWarnings("serial")
public class LoginControlador extends DAO {
		
	private String correo;
    private String password;
    private Usuario usuario = new Usuario();
    private Correo user_correo = new Correo();
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
				Map session = ActionContext.getContext().getSession(); 
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
	
	@SuppressWarnings("rawtypes")
	@SkipValidation
	public String desloguearUsuario(){ 	
	    Map session = ActionContext.getContext().getSession(); 
	    session.clear();
		return SUCCESS;
	}
	
	public void validate(){
				
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
}

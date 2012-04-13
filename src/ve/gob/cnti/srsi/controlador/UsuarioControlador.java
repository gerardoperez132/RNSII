package ve.gob.cnti.srsi.controlador;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.dao.MD5Hashing;
import ve.gob.cnti.srsi.modelo.Ente;
import ve.gob.cnti.srsi.modelo.Usuario;

@SuppressWarnings("serial")
public class UsuarioControlador extends DAO{
	
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
	
	public String header(){
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
	public String modificarClave() throws NoSuchAlgorithmException{
		if(header().equals("errorSession")==true){
			return "errorSession";
		}else{
			usuario = (Usuario) session.get("usuario");
			if (clave_actual.isEmpty() || clave_nueva.isEmpty() || clave_nueva_confirme.isEmpty()){
				addFieldError("password", "Todos los campos son requeridos");
				return INPUT;
			}else if(usuario.getClave().equals(new MD5Hashing(clave_actual).getPassword().toString())){
				if(!clave_nueva.equals(clave_nueva_confirme)){
					addFieldError("password", "Las contraseñas no coinciden");
					return INPUT;
				}else if(clave_nueva.length()<6){
					addFieldError("password", "La nueva contraseña debe tener al menos 6 caracteres");
					return INPUT;
				}else{					
					MD5Hashing pass = new MD5Hashing(clave_nueva);					
					usuario.setClave(pass.getPassword());
					update(usuario,usuario.getId_usuario());
					modificarClave = false;
					addActionMessage("Clave modificada satifactoriamente");
					return SUCCESS;
				}				 
			}else{
				try {
					intentos_fallidos = (Integer) session.get("intentos_fallidos");
				} catch (Exception e) {
					intentos_fallidos = 0;
				}				
				intentos_fallidos ++;
				if(intentos_fallidos==3){
					addFieldError("password", "Su sesión ha sido cerrada por superar el máximo de intentos fallidos");
					return "errorSession";
				}
				session.put("intentos_fallidos", intentos_fallidos);
				addFieldError("password", "Contraseña incorrecta - Intento N°: " + intentos_fallidos);
				return INPUT;
			}					
		}			
	}
	
	public String configuracion(){		
		return header();
	}
	
	public String prepararFormulario(){				
		return header();
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

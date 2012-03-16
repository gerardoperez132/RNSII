package ve.gob.cnti.srsi.controlador;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Correo;
import ve.gob.cnti.srsi.modelo.Usuario;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LoginControlador extends DAO {
	
	private String userName;
	private String correo;
    private String password;
    private Usuario usuario = new Usuario();
    private Correo user_correo = new Correo();
	
	
	public String autenticarUsuario(){
		
		if(userName.contains("admin") && password.contains("admin")){
			System.out.println("valido");
		return SUCCESS;
		}else{
			System.out.println("invalido");
			addFieldError("userName",
					"Usuario o clave de acceso erroneos");
			return INPUT;
		}		
		
	}
	
	public String autenticarUsuario(){
		
		//user_correo = 
		usuario = (Usuario) read(usuario, user_correo.getId_usuario());
		
		if(correo.contains("admin") && password.contains("admin")){
			System.out.println("valido");
		return SUCCESS;
		}else{
			System.out.println("invalido");
			addFieldError("userName",
					"Usuario o clave de acceso erroneos");
			return INPUT;
		}		
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String mostrarLogin(){
		return SUCCESS;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
}

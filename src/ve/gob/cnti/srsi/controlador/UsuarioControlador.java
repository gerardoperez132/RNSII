package ve.gob.cnti.srsi.controlador;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Ente;
import ve.gob.cnti.srsi.modelo.Usuario;

@SuppressWarnings("serial")
public class UsuarioControlador extends DAO{
	
	private Usuario usuario = new Usuario();
	private boolean modificarDatos;
	private boolean modificarClave;
	
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
	
	public String configuracion(){
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

}

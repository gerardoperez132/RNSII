package ve.gob.cnti.rnsii.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import ve.gob.cnti.rnsii.dao.DAO;

import com.opensymphony.xwork2.ActionContext;

public class Administration extends DAO implements ServletRequestAware {
	private Map session;
	private String email;
	private String password;
	private String captcha;
	private String msj_error;
	
	public String access(){
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
//	public String access_control() throws Exception {
//
//		session = ActionContext.getContext().getSession();
//		if (email == null && password == null && captcha == null) {
//			return "404ERROR";
//		}
//		if (email.isEmpty() || password.isEmpty() || captcha.isEmpty()) {
//			msj_error = error.getProperties().getProperty("error.login.fields");
//			return INPUT;
//		}
//		if (!((String) session.get("captcha")).toUpperCase().equals(
//				captcha.toUpperCase())) {
//			msj_error = error.getProperties()
//					.getProperty("error.login.captcha");
//			return INPUT;
//		}
//		if (!email.matches(REGEX_EMAIL)) {
//			msj_error = error.getProperties().getProperty("error.regex.email");
//			return INPUT;
//		}
//		 user_email = (email) getUserEmail(email);
//		 if (user_email == null) {
//		 msj_error = error.getProperties()
//		 .getProperty("error.login.invalid");
//		 return INPUT;
//		 } else {
//		 usuario = (Usuario) read(usuario, user_email.getId_usuario());
//		 if (usuario == null) {
//		 msj_error = error.getProperties().getProperty(
//		 "error.login.invalid");
//		 return INPUT;
//		 } else if (!usuario.getClave().equals(
//		 new MD5Hashing(password).getPassword().toString())) {
//		 msj_error = error.getProperties().getProperty(
//		 "error.login.invalid");
//		 return INPUT;
//		 } else {
//		 Ente ente = new Ente();
//		 ente = (Ente) read(ente, usuario.getId_ente());
//		 session.put("logueado", true);
//		 session.put("usuario", usuario);
//		 session.put("ente_sesion", ente);
//		 return SUCCESS;
//		 }
//		 }
//		return SUCCESS;
//	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}

}

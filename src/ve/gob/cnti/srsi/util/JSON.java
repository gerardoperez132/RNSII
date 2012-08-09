package ve.gob.cnti.srsi.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import ve.gob.cnti.srsi.dao.Constants;
import ve.gob.cnti.srsi.i18n.Errors;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class JSON extends ActionSupport{

	private Map<String,String> errores = new HashMap<String, String>();
	private Map<String,String> constants = new HashMap<String, String>();
	private Errors error = new Errors();
	
	@SuppressWarnings("rawtypes")
	public String obtenerError()  {
				
		for (Enumeration e = error.getProperties().keys(); e.hasMoreElements() ; ) {
		    // Obtenemos el objeto
		    Object obj = e.nextElement();
		    System.out.println(obj.toString() + ": " + error.getProperties().getProperty(obj.toString()));
		    errores.put(obj.toString(), error.getProperties().getProperty(obj.toString()));
		    
		}
		
		constants.put("REGEX_EMAIL", Constants.REGEX_EMAIL);
		constants.put("REGEX_TITLE", Constants.REGEX_TITLE);
		
		return SUCCESS;
	}

	public Map<String, String> getErrores() {
		return errores;
	}

	public void setErrores(Map<String, String> errores) {
		this.errores = errores;
	}

	public Map<String, String> getConstants() {
		return constants;
	}

	public void setConstants(Map<String, String> constants) {
		this.constants = constants;
	}





	





}

class Valores{
	
	private String variable;
	private String valor;	
	
	public Valores(String variable, String valor) {
		super();
		this.variable = variable;
		this.valor = valor;
	}
	public String getVariable() {
		return variable;
	}
	public void setVariable(String variable) {
		this.variable = variable;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
package ve.gob.cnti.srsi.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import ve.gob.cnti.srsi.dao.Constants;
import ve.gob.cnti.srsi.i18n.Errors;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Esta clase genera información en formato JSON sobre los archivos de
 * propiedades de internacionalización.
 * 
 * @author Richard Ricciardelli
 * @author Joaquín Pereira
 * @see Errors
 */
@SuppressWarnings("serial")
public class JSON extends ActionSupport {

	private Map<String, String> errores = new HashMap<String, String>();
	private Map<String, String> constants = new HashMap<String, String>();
	private Errors error = new Errors();

	@SuppressWarnings("rawtypes")
	public String obtenerError() {
		for (Enumeration enumeration = error.getProperties().keys(); enumeration
				.hasMoreElements();) {
			Object object = enumeration.nextElement();
			errores.put(object.toString(),
					error.getProperties().getProperty(object.toString()));
		}
		constants.put("REGEX_TITLE", Constants.REGEX_TITLE);
		constants.put("REGEX_DESCRIPTION", Constants.REGEX_DESCRIPTION);
		constants.put("REGEX_EMAIL", Constants.REGEX_EMAIL);
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
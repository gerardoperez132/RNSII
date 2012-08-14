/* This file is part of SRSI.
 * 
 * SRSI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * SRSI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SRSI. If not, see <http://www.gnu.org/licenses/>.
 */
package ve.gob.cnti.srsi.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import ve.gob.cnti.srsi.dao.Constants;
import ve.gob.cnti.srsi.i18n.Errors;
import ve.gob.cnti.srsi.i18n.Messages;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Esta clase genera información en formato JSON sobre los archivos de
 * propiedades de internacionalización y las constantes con las expresiones
 * regulares.
 * 
 * @author Richard Ricciardelli
 * @author Joaquín Pereira
 * @see Errors
 * @see Messages
 */
@SuppressWarnings("serial")
public class JSON extends ActionSupport {

	private Map<String, String> errores = new HashMap<String, String>();
	private Map<String, String> mensajes = new HashMap<String, String>();
	private Map<String, String> constants = new HashMap<String, String>();
	private Errors error = new Errors();
	private Messages mensaje = new Messages();

	@SuppressWarnings("rawtypes")
	public String obtenerError() {		
		for (Enumeration enumeration = mensaje.getProperties().keys(); enumeration
				.hasMoreElements();) {
			Object object = enumeration.nextElement();
			mensajes.put(object.toString(),
					mensaje.getProperties().getProperty(object.toString()));
		}
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

	public Map<String, String> getMensajes() {
		return mensajes;
	}

	public void setMensajes(Map<String, String> mensajes) {
		this.mensajes = mensajes;
	}
}
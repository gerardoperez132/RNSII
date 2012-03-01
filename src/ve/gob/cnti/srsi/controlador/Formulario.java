package ve.gob.cnti.srsi.controlador;

import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * Interfaz con métodos para implementar en cada formulario.
 * 
 * @author Richard Ricciardelli
 * 
 */
public interface Formulario {
	/**
	 * Prepara el formulario del modelo dado.
	 * 
	 * @return "success"
	 */
	@SkipValidation
	public String prepararFormulario();
}

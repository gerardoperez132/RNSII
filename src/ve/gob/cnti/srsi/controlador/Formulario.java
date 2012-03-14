package ve.gob.cnti.srsi.controlador;

/**
 * Interfaz con métodos para implementar en cada formulario.
 * 
 * @author Richard Ricciardelli
 * 
 */
public interface Formulario {
	/**
	 * Prepara el formulario del modelo dado para su registro.
	 * 
	 * @return "success"
	 */
	public String prepararFormulario();

	/**
	 * Prepara el formulario del modelo dado para su modificación.
	 * 
	 * @return "success"
	 */
	public String prepararModificaciones();
}

package ve.gob.cnti.srsi.modelo;

/**
 * Interfaz de estados de registro.
 * 
 * @author Richard Ricciardelli
 * 
 */
public interface Status {
	/** Estado ACTIVO se muestra en la vista. */
	public static final int ACTIVO = 0;
	/** Estado MODIFICADO no se muestra en la vista. */
	public static final int MODIFICADO = 1;
	/** Estado ELIMINADO no se muestra en la vista. */
	public static final int ELIMINADO = 2;
}
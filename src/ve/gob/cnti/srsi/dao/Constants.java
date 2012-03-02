package ve.gob.cnti.srsi.dao;

/**
 * Interfaz de constantes del Sistema de Registro de Servicio de Información.
 * 
 * @author Richard Ricciardelli
 * 
 */
public interface Constants {
	/**
	 * Interfaz con los estados de un sistema de información.
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

	/**
	 * Interfaz con los tipos de documentos de los aspectos legales.
	 * 
	 * @author Richard Ricciardelli
	 * 
	 */
	public interface TipoDocumento {
		/** Tipo de documento legal. */
		public static final int LEGAL = 0;
		/** Tipo de documento SLA. */
		public static final int SLA = 1;
	}

	public interface TipoEntradaSalida {
		/** Entrada de una funcionalidad. */
		public static final int ENTRADA = 0;
		/** Salida de una funcionalidad. */
		public static final int SALIDA = 1;
	}
}
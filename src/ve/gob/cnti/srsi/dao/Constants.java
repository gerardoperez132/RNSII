package ve.gob.cnti.srsi.dao;

import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Funcionalidad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;

/**
 * Interfaz de constantes del Sistema de Registro de Servicio de Información.
 * 
 * @author Richard Ricciardelli
 * 
 */
public interface Constants {
	/**
	 * Códigos de área de un número telefónico en Venezuela.
	 */
	public static final String[] COD = { "212", "412", "414", "424", "416",
			"426", "234", "235", "237", "238", "239", "241", "242", "243",
			"244", "245", "246", "247", "248", "249", "251", "252", "253",
			"254", "255", "256", "257", "258", "261", "262", "263", "264",
			"266", "267", "268", "269", "271", "272", "273", "274", "275",
			"276", "277", "278", "281", "282", "283", "285", "286", "287",
			"288", "291", "292", "293", "294", "295" };

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

	/**
	 * Interfaz con los tipos de entrada en la tabla de entradas y salidas.
	 * 
	 * @author Richard Ricciardelli
	 * 
	 */
	public interface TipoEntradaSalida {
		/** Entrada de una funcionalidad. */
		public static final int ENTRADA = 0;
		/** Salida de una funcionalidad. */
		public static final int SALIDA = 1;
	}

	/**
	 * Interfaz con los tipos de datos en la tabla de tipo de datos.
	 * 
	 * @author Richard Ricciardelli
	 * 
	 */
	public interface ClaseDato {
		/** Tipo de dato compuesto. */
		public static final int COMPUESTO = 0;
		/** Tipo de dato simple. */
		public static final int SIMPLE = 1;
	}

	/**
	 * Interfaz con los arreglos de objetos para consultas tipo 1:N.
	 * 
	 * @author Richard Ricciardelli
	 * 
	 */
	public interface Modelos {
		/** Relación 1 Funcionalidad tiene N Entradas / Salidas. */
		public static final Object[] ESF = { new EntradaSalida(),
				new Funcionalidad() };
		/** Relación 1 Servicio de Información tiene N Funcionalidades. */
		public static final Object[] FSI = { new Funcionalidad(),
				new ServicioInformacion() };
	}

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
	}
}
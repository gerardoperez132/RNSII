package ve.gob.cnti.srsi.dao;

import ve.gob.cnti.srsi.modelo.AspectoLegal;
import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Funcionalidad;
import ve.gob.cnti.srsi.modelo.Sector;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.Visita;

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
	public static final String[] CODES = { "212", "412", "414", "424", "416",
			"426", "234", "235", "237", "238", "239", "241", "242", "243",
			"244", "245", "246", "247", "248", "249", "251", "252", "253",
			"254", "255", "256", "257", "258", "261", "262", "263", "264",
			"266", "267", "268", "269", "271", "272", "273", "274", "275",
			"276", "277", "278", "281", "282", "283", "285", "286", "287",
			"288", "291", "292", "293", "294", "295" };

	/**
	 * La ruta original para guardar los archivos.
	 */
	public static final String PATH = "/archivos/";

	/**
	 * Número de servicios de información más visitados.
	 */
	public static final int LIMITE_VISITADOS = 5;

	/**
	 * Número de sectores con más servicios de información publicados e
	 * implementados a ser mostrados en la vista final.
	 */
	public static final int LIMITE_SECTORES = 5;

	/**
	 * La expresión regular para validar los campos de los formularios.
	 * Considerando que la cadena con la que se compara está en mayúsculas.
	 */
	public static final String REGEX_TITLE = "^[A-Z _AÉÍÓÚÑ]*$";

	/**
	 * La expresión regular para validar los campos de los formularios que
	 * permiten introducir un área de texto más amplia. Considerando que la
	 * cadena con la que se compara está en mayúsculas.
	 */
	public static final String REGEX_DESCRIPTION = "^[A-Z0-9 _.()-AÉÍÓÚÑ]*$";

	/**
	 * La expresión regular para el correo electrónico válido.
	 */
	public static final String REGEX_EMAIL = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

	/**
	 * Interfaz con los estados de registro en la base de datos.
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
	 * Interfaz con los tipos de pestañas en la vista para el registro de un
	 * servicio de información.
	 * 
	 * @author Richard Ricciardelli
	 * 
	 */
	public interface Tabs {
		/** Pestaña de descripción general. */
		public static final int DESCRIPCION_GENERAL = 1;
		/** Pestaña de aspectos legales. */
		public static final int ASPECTOS_LEGALES = 2;
		/** Pestaña de descripción técnica. */
		public static final int DESCRIPCION_TECNICA = 3;
		/** Pestaña de descripción de soporte. */
		public static final int DESCRIPCION_SOPORTE = 4;
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
		/** Relación 1 Servicio de Información tiene N Aspectos Legales. */
		public static final Object[] ALSI = { new AspectoLegal(),
				new ServicioInformacion() };
		/** Relación 1 Sector tiene N Servicios de Información. */
		public static final Object[] SISE = { new ServicioInformacion(),
				new Sector() };
		/** Relación 1 Servicio de Información tiene N Visitas. */
		public static final Object[] SIVI = { new Visita(),
				new ServicioInformacion(), };
	}

	/**
	 * Interfaz con los tipos de ordenamiento para una consulta a la base de
	 * datos.
	 * 
	 * @author Richard Ricciardelli
	 * 
	 */
	public interface Order {
		/** Ordenar de forma ascendente. */
		public static final byte ASC = 0;
		/** Ordenar de forma descendente. */
		public static final byte DESC = 1;
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
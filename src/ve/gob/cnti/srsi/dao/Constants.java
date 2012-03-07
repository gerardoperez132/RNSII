package ve.gob.cnti.srsi.dao;

import ve.gob.cnti.srsi.modelo.Dato;
import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Funcionalidad;
import ve.gob.cnti.srsi.modelo.TipoDato;

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
	 * Interfaz con los arreglos de modelos preestablecidos para la consultas
	 * anidadas utilizando el método de lectura.
	 * 
	 * @author Richard Ricciardelli
	 * 
	 */
	public interface ArregloModelos {
		/** Cuando se requiere conocer el nombre del dato. */
		public static final Object[] NOMBRE_DATO = { new Dato(),
				new EntradaSalida(), new Funcionalidad() };
		/** Cuando se requiere conocer el nombre del tipo de dato. */
		public static final Object[] NOMBRE_TIPO_DATO = { new TipoDato(),
				new Dato(), new EntradaSalida() };
		/**
		 * Cuando se requiere conocer si el nommbre del dato no está duplicado
		 * en la base de datos.
		 */
		public static final Object[] NOMBRE_DATO_NO_DUPLICADO = { new Dato(),
				new EntradaSalida(), new Funcionalidad() };

	}
}
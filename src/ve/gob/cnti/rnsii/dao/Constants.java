/* This file is part of RNSII.
 * 
 * RNSII is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * RNSII is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with RNSII. If not, see <http://www.gnu.org/licenses/>.
 */
package ve.gob.cnti.rnsii.dao;

import ve.gob.cnti.rnsii.modelo.AspectoLegal;
import ve.gob.cnti.rnsii.modelo.EntradaSalida;
import ve.gob.cnti.rnsii.modelo.Funcionalidad;
import ve.gob.cnti.rnsii.modelo.Sector;
import ve.gob.cnti.rnsii.modelo.ServicioInformacion;
import ve.gob.cnti.rnsii.modelo.Visita;

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
	 * Longitud de caracteres mínimos para la contraseña.
	 */
	public static final byte MIN_PASSWORD = 6;

	/**
	 * Longitud de caracteres considerados para una contraseña regular.
	 */
	public static final byte MED_PASSWORD = 7;

	/**
	 * Longitud de caracteres considerados para una contraseña óptima.
	 */
	public static final byte MAX_PASSWORD = 8;

	/**
	 * Longitud máxima de caracteres para una contraseña.
	 */
	public static final byte MAX_LENGTH_PASSWORD = 20;

	/**
	 * Longitud del campo de descripción en la base de datos.
	 */
	public static final int DESCRIPTION_LENGTH = 0x400; // 1024

	/**
	 * Longitud del campo de título o nombre en la base de datos.
	 */
	public static final int TITLE_LENGTH = 0x80; // 128

	/**
	 * Longitud del campo motivo en la base de datos.
	 */
	public static final int MOTIVE_LENGTH = 0x800; // 2048

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
	public static final String REGEX_TITLE = "^[A-Z _ÁÉÍÓÚÑ]*$";

	/**
	 * La expresión regular para validar los campos de los formularios que
	 * permiten introducir un área de texto más amplia. Considerando que la
	 * cadena con la que se compara está en mayúsculas.
	 */
	public static final String REGEX_DESCRIPTION = "^[A-Z0-9 _.,()ÁÉÍÓÚÑ\\n]*$";

	/**
	 * La expresión regular para el correo electrónico válido.
	 */
	public static final String REGEX_EMAIL = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

	/**
	 * La expresión regular para las direcciones URL.
	 */
	public static final String REGEX_URL = "((https?|ftp|gopher|telnet|file|notes|ms-help):((//)|(\\\\\\\\))+[\\w\\d:#%/;$()~_?\\+-=\\\\\\.&]*)";

	/**
	 * La expresión regular para validar que una contraseña tenga mínimo 8
	 * caracteres, posea al menos un número o caracter especial y esté compuesta
	 * de minúsculas y mayúsculas.
	 */
	public static final String REGEX_PASS_STRONG = "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{"
			+ MAX_PASSWORD + ",})$";

	/**
	 * La expresión regular para validar que una contraseña tenga mínimo 7
	 * caracteres, posea al menos un número o caracter especial y esté compuesta
	 * de minúsculas y mayúsculas.
	 */
	public static final String REGEX_PASS_MEDIUM = "^(?=.{"
			+ MED_PASSWORD
			+ ",})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$";

	/**
	 * La expresión regular para validar que una contraseña tenga mínimo 6
	 * caracteres.
	 */
	public static final String REGEX_PASS_ENOUGH = "(?=.{" + MIN_PASSWORD
			+ ",}).*";

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
	 * Interfaz con los estados de sentencias en las solicitudes de suscripción
	 * en la base de datos.
	 * 
	 * @author Joaquín Pereira
	 * 
	 */
	public interface Sentencias {
		/**
		 * Estado PENDIENTE no ha sido revisado, independientemente si ha sido
		 * leído o no.
		 */
		public static final int PENDIENTE = 0;
		/**
		 * Estado ACEPTADO ha sido revisada la solicitud y aceptada para
		 * permitir la suscripción.
		 */
		public static final int ACEPTADO = 1;
		/**
		 * Estado RECHAZADO ha sido revisada la solicitud y rechazada para no
		 * permitir la suscripción.
		 */
		public static final int RECHAZADO = 2;
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
		public static final int Funcionalidades = 4;
		/** Pestaña de descripción de soporte. */
		public static final int DESCRIPCION_SOPORTE = 5;
		/** Pestaña de descripción de soporte. */
		public static final int SALIDAS_TAB = 6;
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

	/**
	 * Interfaz con los códigos de error.
	 * 
	 * @author Richard Ricciardelli
	 * 
	 */
	public interface ErrorServicio {
		public static final int SECTOR = 1;
		public static final int AREA = 2;
		public static final int ESTADO = 3;
		public static final int SEGURIDAD = 4;
		public static final int ARQUITECTURAS = 5;
		public static final int INTERCAMBIO = 6;
		public static final int TELEFONO = 7;
		public static final int CORREO = 8;
		public static final int FUNCIONALIDADES = 9;
		public static final int SALIDAS = 10;
	}

	/**
	 * Interfaz con los códigos de los estados de un servicio de información.
	 * 
	 * @author Richard Ricciardelli
	 * 
	 */
	public interface Estados {
		public static final int DESARROLLO = 1;
		public static final int IMPLEMENTADO = 2;
	}

	/**
	 * Interfaz con las constantes para formato de fechas en la aplicación.
	 * 
	 * @author Richard Ricciardelli
	 * 
	 */
	public interface DateFormatting {
		public static final String VET_FORMAT = "dd/MM/yyyy HH:mm:ss";
	}
		
}

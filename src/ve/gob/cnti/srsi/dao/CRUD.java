package ve.gob.cnti.srsi.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import ve.gob.cnti.modelo.temporales.ListaSImasVisitados;
import ve.gob.cnti.modelo.temporales.SectoresMasPublicados;
import ve.gob.cnti.modelo.temporales.Solicitud_Suscripcion;
import ve.gob.cnti.srsi.modelo.Correo;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.SolicitudSuscripcion;
import ve.gob.cnti.srsi.modelo.Telefono;
import ve.gob.cnti.srsi.modelo.Visita;

/**
 * Definición de métodos para las operaciones en la base de datos.
 * 
 * @author Richard Ricciardelli
 * @see DAO
 * 
 */
public interface CRUD {

	/**
	 * Permite guardar un registro en la base de datos de cualquier modelo.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en el cual se realizará el
	 *            registro.
	 */
	public void create(Object model);

	/**
	 * Permite guardar un registro en la base de datos cuando el modelo es de
	 * una relación de unión M:N.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en el cual se realizará el
	 *            registro.
	 */
	public void createUnion(Object model);

	/**
	 * Permite eliminar un registro en la base de datos cuando el modelo es de
	 * una relación de unión M:N.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en el cual se realizará la
	 *            eliminación.
	 * 
	 * @param model2
	 *            Es la clase, modelo o tabla que es padre de la unión
	 * 
	 * 
	 * @param id
	 *            Es el identificador de la clase, modelo o tabla que es padre
	 *            de la unión
	 */
	public void deleteUnion(Object[] models, long id_u, long id);

	/**
	 * Permite obtener todos los registros activos del modelo dado.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @return Una lista de objetos del modelo dado con sus atributos.
	 */
	public ArrayList<?> read(Object model);

	/**
	 * Permite obtener todos los registros activos del modelo resultante de la
	 * unión M:N de dos modelos dados.
	 * 
	 * @param unionModel
	 *            Modelo unión que contiene la relación resultante M:N de dos
	 *            tablas.
	 * @param model
	 *            Es la clase, modelo o tabla en el cual se realizará la
	 *            consulta como campo identificador.
	 * @param id
	 *            Es el identificador de la clase, modelo o tabla que sirve como
	 *            campo identificador.
	 * @return
	 */
	public ArrayList<?> readUnion(Object unionModel, Object model, long id);

	/**
	 * Permite obtener el registro activo especificado por el identificador del
	 * modelo dado.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @param id
	 *            Es el identificador único del registro a consultar.
	 * @return El objeto modelo con sus atributos.
	 */
	public Object read(Object model, long id);

	/**
	 * Permite obtener una lista de registros del primer modelo dado dentro del
	 * arreglo. Funciona para relaciones 1:M donde el segundo modelo sirve de
	 * llave foránea dentro de la primer tabla.
	 * 
	 * @param models
	 *            Son las clases, modelos o tablas en las cuales se realizará la
	 *            consulta. Siendo la primera de éstas el tipo de objeto
	 *            resultante.
	 * @param id
	 *            Identificador de llave foránea dentro del primer modelo del
	 *            arreglo.
	 * @param type
	 *            Tipo de objeto que no será tomado en cuenta si tiene un valor
	 *            negativo.
	 * @return El listado de objetos resultantes.
	 */
	public ArrayList<?> read(Object models[], long id, int type);

	/**
	 * Permite obtener un valor de verdad sobre el nombre repetido en la base de
	 * datos de acuerdo a un criterio de relación 1:N donde el segundo modelo
	 * sirve de llave foránea.
	 * 
	 * @param models
	 *            Son las clases, modelos o tablas en las cuales se realizará la
	 *            consulta. Siendo la primera de éstas el tipo de objeto
	 *            resultante.
	 * @param id
	 *            Identificador de llave foránea dentro del primer modelo del
	 *            arreglo.
	 * @param name
	 *            Nombre del objeto.
	 * @return {@code true} si se encuentran coincidencias.
	 */
	public boolean read(Object[] models, long id, String name);

	/**
	 * Permite obtener una lista de objetos del modelo dado ordenado por nombre
	 * de forma ascendente o descendente dependiendo del caso.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @param orderBy
	 *            Tipo de ordenamiento de la lista de objetos obtenida.
	 * @return
	 */
	public ArrayList<?> getSortedList(Object model, byte orderBy);

	/**
	 * Permite obtener el objeto correo de un usuario con el string dado.
	 * 
	 * @param email
	 *            El string del correo que se quiere buscar.
	 * @return {@code correo} asociado a un usuario.
	 */
	public Correo getUserEmail(String email);

	/**
	 * Permite obtener el objeto correo de un identificador de modelo dado.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @param id
	 *            Es el id del correo a consultar.
	 * @return {@code correo} asociado a un modelo.
	 */
	public Correo getEmail(Object model, long id);

	/**
	 * Permite modificar el registro especificado por id del modelo dado.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @param id
	 *            Es el id del registro a modificar.
	 */
	public void update(Object model, long id);

	/**
	 * Permite modificar el registro especificado por id del modelo resultante
	 * de una relación M:N.
	 * 
	 * @param unionModel
	 *            Es el modelo resultante de una relación M:N.
	 * @param modelParent
	 *            Es la clase, modelo o tabla padre en la relación.
	 * @param modelChild
	 *            Es la clase, modelo o tabla hija en la relación.
	 * @param idParent
	 *            Es el identificador en el modelo padre.
	 * @param children
	 *            Lista de objetos hijos.
	 * @throws Exception
	 *             Arroja una excepción de no poder realizarse la invocación de
	 *             un método de alguna de las clases.
	 */
	public void updateUnion(Object unionModel, Object modelParent,
			Object modelChild, long idParent, List<?> children)
			throws Exception;

	/**
	 * Permite eliminar el registro especificado por id del modelo dado.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @param id
	 *            Es el id del registro a eliminar.
	 */
	public void delete(Object model, long id);

	/**
	 * Permite obtener el id lógico correspondiente al modelo dado para poder
	 * crear un nuevo registro.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @return id Es el id lógico correspondiente.
	 */
	public long getNextId(Object model);

	/**
	 * Permite obtener el campo que será utilizado para obtener el id propio de
	 * cada modelo.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @return El campo en el formato que se encuentra en la base de datos.
	 */
	public String getField(Object model);

	/**
	 * Permite obtener el sufijo para ser agregado al campo que será utilizado
	 * para la consulta del id de cada modelo.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @return El sufijo del modelo en cuestión.
	 */
	public String getSuffix(Object model);

	/**
	 * Permite obtener los registros "padre" de un modelo determinado.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @return Lista de padres activos en esa tabla.
	 */
	public ArrayList<?> getParents(Object model);

	/**
	 * Permite obtener los registros "hijo" de un modelo determinado.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @return Lista de hijos activos en esa tabla.
	 */
	public ArrayList<?> getChildren(Object model);

	/**
	 * Permite obtener la lista de los datos simples cargados en base de datos.
	 * 
	 * @return Lista de datos simples.
	 */
	public ArrayList<?> getSimple();

	/**
	 * Permite obtener la lista de los datos compuestos cargados en base de
	 * datos.
	 * 
	 * @return Lista de datos compuestos.
	 */
	public ArrayList<?> getComplex();

	/**
	 * Permite obtener el telefono unsa clase dada
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @param id
	 *            Es el id del correo a consultar.
	 * @return {@code telefono}
	 */
	public Telefono getPhone(Object model, long id);

	/**
	 * Permite actualizar una misma tupla sobre los datos faltantes. No genera
	 * trazas de auditoría.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @throws NoSuchMethodException
	 *             Cuando no existe el método.
	 * @throws InvocationTargetException
	 *             Cuando la invocación no fue satisfactoria.
	 * @throws IllegalAccessException
	 *             Cuando es ilegal acceder a un comportamiento del objeto.
	 * @throws SecurityException
	 *             Excepción de seguridad.
	 * @throws IllegalArgumentException
	 *             Cuando es ilegal el argumento pasado.
	 */
	public void update(Object model) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	/**
	 * Permite saber si existe un registro activo mediante la llave foranea con
	 * las relaciones de las dos entidades, pasadas como argumentos.
	 * 
	 * @param models
	 *            Son las dos entidades relacionadas, primero la entidad padre y
	 *            luego la entidad hija
	 * @param id
	 *            clave foranea de la entidad hija
	 * @return retorna un valor booleano encaso de que que exista o no, un
	 *         registro activo
	 */
	public boolean read(Object[] models, long id);

	/**
	 * Permite saber si existe un registro activo mediante la llave foranea con
	 * las relaciones de las dos entidades, pasadas como argumentos.
	 * 
	 * @param models
	 *            Son las dos entidades relacionadas, primero la entidad padre y
	 *            luego la entidad hija
	 * @param id
	 *            clave foranea de la entidad hija
	 * @return retorna un objeto con sus atributos establecidos.
	 * 
	 */
	public Object readf(Object[] models, long id);

	/**
	 * Permite saber si existe un registro activo mediante la llave foranea con
	 * las relaciones de las dos entidades, pasadas como argumentos.
	 * 
	 * @param models
	 *            Son las dos entidades relacionadas, primero la entidad padre y
	 *            luego la entidad hija
	 * @param id
	 *            clave foranea de la entidad hija
	 * @return retorna un objeto con sus atributos establecidos.
	 * 
	 */
	public Object getUrlRecoveryPass(Object model, String Url);

	/**
	 * Permite saber el número de servicios de información publicados en el
	 * sector solicitado.
	 * 
	 * @param id
	 *            id del sector a consultar
	 * @return retorna el número de servicios publicados del sector
	 * 
	 */
	public long nSiSector(long id);

	/**
	 * Permite buscar servicios en el que el nombre de estos, se asemejen a la
	 * cadena pasada
	 * 
	 * @param cadena
	 *            Es la cadena a consultar
	 * 
	 * @return Retorna una lista de servicios
	 */
	public ArrayList<ServicioInformacion> buscarServicio(String cadena,
			byte orderBy);

	// /**
	// * Permite obtener el número de visitas de un modelo determinado. Aplica a
	// * servicios de información.
	// *
	// * @see ServicioInformacion
	// * @param model
	// * Modelo al cual se le desea conocer el número de visitas.
	// * @param id
	// * Identificador del modelo dado.
	// * @return El número de visitas del modelo dado.
	// */
	// public long getNumeroVisitas(Object model, long id);

	public void saveVisit(Visita visita);

	public long getVisits(long id);

	/**
	 * Permite verificar el acceso de un cliente en un límite de tiempo
	 * determinado para poder contarse como una visita única.
	 * 
	 * @param ip
	 *            Dirección IP del cliente que está realizando la visita
	 * @return {@code true} si es una visita nueva fuera del lapso establecido,
	 *         de lo contrario {@code false}
	 */
	public boolean verifyClientAccess(String ip, long id);

	/**
	 * Permite Consultar los 5 servicios de información más visitados.
	 * 
	 * @return Retorna una lista con los servicios de información más visitados.
	 */
	public List<ListaSImasVisitados> SImasVisitados();

	/**
	 * Permite consultar una cantidad n de sectores con más servicios de
	 * información implementados y publicados.
	 * 
	 * @param n
	 *            Número para limitar la consulta, un valor menor o igual a cero
	 *            se considera como una consulta sin limitante.
	 * @return Retorna una lista con los n sectores con más servicios de
	 *         información.
	 */
	public List<SectoresMasPublicados> sectoresMasPublicados(int n);

	/**
	 * Permite Consultar los servicios de información implemantados y
	 * públicados.
	 * 
	 * @param n
	 *            Numero para limitar la consulta, un valor menor o igual a cero
	 *            se considera como una consulta sin limitante.
	 * 
	 * @return Retorna una lista con los servicios de información implemantados
	 *         y públicados.
	 */
	public ArrayList<ServicioInformacion> getSIList(byte orderBy);

	/**
	 * Retorna una lista de los servicios de información implementados, activos
	 * y publicados en el sector dado.
	 * 
	 * @param id_sector
	 *            Identificador del sector al cual pertenecen los servicios de
	 *            información.
	 * @return Lista de los servicios de información con los criterios dados.
	 */
	public ArrayList<ServicioInformacion> getServicioInformacionPorSectorList(
			long id_sector, byte orderBy);

	/**
	 * Permite buscar servicios en el que el nombre de estos, se asemejen a la
	 * cadena pasada
	 * 
	 * @param cadena
	 *            Es la cadena a consultar id_ente Sirve para discriminar los
	 *            servicio de información del propio ente
	 * 
	 * @return Retorna una lista de servicios
	 */
	public ArrayList<ServicioInformacion> buscarServicio2(String cadena, byte orderBy,
			long id_ente);
	
	/**
	 * Permite saber el número solicitudes de suscripción no leidos que posee el
	 * ente
	 * 
	 * @param id
	 *            id del ente a consultar
	 * @return retorna el número solicitudes de suscripción no leidas
	 * 
	 */
	public long peticionesSuscripcion(long id);
	
	/**
	 * Permite saber el número solicitudes de suscripción pendientes que posee el
	 * ente
	 * 
	 * @param id
	 *            id del ente a consultar
	 * @return retorna el número solicitudes de suscripción pendientes
	 * 
	 */
	public long peticionesSuscripcionPendientes(long id);
	
	/**
	 * Retorna una lista de las solicitudes de suscrición a los servicios
	 * de información publicados por el ente
	 * 
	 * @param id_ente
	 *            Identificador del ente al cual pertenecen las solicitudes de 
	 *            suscripción a los servicios de información publicados.
	 * @return Lista de las solicitudes de suscrición a los servicios información.
	 */
	public ArrayList<Solicitud_Suscripcion> getSolicitudesSuscripcionPendientes(
			long id_ente, byte orderBy);
	
	/**
	 * Permite saber el número solicitudes de suscripción Aceptadas por leer
	 * 
	 * @param id
	 *            id del ente a consultar
	 * @return retorna el número solicitudes de suscripción Aceptadas por leer
	 * 
	 */
	public long getNumeroSuscrionesAceptadas(long id);
	
	/**
	 * Retorna una lista de las solicitudes de suscrición Aceptadas - Rechazadas
	 *  
	 * @param id_ente
	 *            Identificador del ente al cual pertenecen las solicitudes de 
	 *            suscripción aceptadas - rechazadas a buscar.
	 * @return Lista de las solicitudes de suscrición Aceptadas -Rechazadas.
	 */
	public ArrayList<Solicitud_Suscripcion> getlistaSolicitudesAceptadasRechazadas(long id_ente, byte orderBy);
	
	public boolean verifySuscriptionRequest(long service, long provider,
			long client);
}
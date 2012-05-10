package ve.gob.cnti.srsi.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import ve.gob.cnti.srsi.modelo.Correo;
import ve.gob.cnti.srsi.modelo.Telefono;

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
	 * Permite saber si existe un registro activo mediante la llave foranea
	 * con las relaciones de las dos entidades, pasadas como argumentos.
	 * 
	 * @param models
	 * 			Son las dos entidades relacionadas, primero la entidad padre y
	 * 				luego la entidad hija			
	 * @param id	
	 * 			clave foranea de la entidad hija
	 * @return
	 * 			retorna un valor booleano encaso de que que exista o no, un registro 
	 * 			activo
	 */
	public boolean read(Object[] models, long id);
	
	/**
	 * Permite saber si existe un registro activo mediante la llave foranea
	 * con las relaciones de las dos entidades, pasadas como argumentos.
	 * 
	 * @param models
	 * 			Son las dos entidades relacionadas, primero la entidad padre y
	 * 				luego la entidad hija			
	 * @param id	
	 * 			clave foranea de la entidad hija
	 * @return
	 * 			retorna un objeto con sus atributos establecidos.
	 * 			
	 */
	public Object readf(Object[] models, long id);
	
	/**
	 * Permite saber si existe un registro activo mediante la llave foranea
	 * con las relaciones de las dos entidades, pasadas como argumentos.
	 * 
	 * @param models
	 * 			Son las dos entidades relacionadas, primero la entidad padre y
	 * 				luego la entidad hija			
	 * @param id	
	 * 			clave foranea de la entidad hija
	 * @return
	 * 			retorna un objeto con sus atributos establecidos.
	 * 			
	 */
	public Object getUrlRecoveryPass(Object model, String Url);
	
	/**
	 * Permite saber el número de servicios de información publicados 
	 * en el sector solicitado.
	 *			
	 * @param id	
	 * 			id del sector a consultar
	 * @return
	 * 			retorna el número de servicios publicados del sector
	 * 			
	 */
	public long nSiSector(long id);
}
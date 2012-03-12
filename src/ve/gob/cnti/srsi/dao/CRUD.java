package ve.gob.cnti.srsi.dao;

import java.util.ArrayList;

/**
 * Definición de métodos para las operaciones en la base de datos.
 * 
 * @author Richard Ricciardelli
 * @see DAO
 * 
 */
public interface CRUD {

	/**
	 * Permite guardar un registro en la base de datos.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en el cual se realizará el
	 *            registro.
	 */
	public void create(Object model);

	/**
	 * Permite guardar un registro de una tabla unión M:M en la base de datos.
	 * 
	 * @param modelOne
	 *            Primer modelo escrito en la tabla unión de izquierda a
	 *            derecha.
	 * @param modelTwo
	 *            Segundo modelo escrito en la tabla unión de izquierda a
	 *            derecha.
	 * @param id
	 *            Id del segundo modelo a insertar
	 */
	
	public void create(Object model, long id);
	
	// NO ESTÁ LISTO
	public void create(Object modelOne, Object modelTwo, long id);

	/**
	 * Permite obtener el objeto resultante de la consulta anidada del número de
	 * modelos dado.
	 * 
	 * @param models
	 *            Arreglo de modelos donde el primero es el objeto resultante y
	 *            el orden es importante.
	 * @param type
	 *            Tipo de objeto en una tabla recursiva.
	 * @param id
	 *            Identificador del último modelo en el arreglo para realizar la
	 *            consulta en base a éste.
	 * @return El objeto resultante.
	 */
	public Object read(Object[] models, int type, long id);

	/**
	 * Permite obtener el objeto resultante de la consulta anidada del número de
	 * modelos dado.
	 * 
	 * @param models
	 *            Arreglo de modelos donde el primero es el objeto resultante y
	 *            el orden es importante.
	 * @param id
	 *            Identificador del último modelo en el arreglo para realizar la
	 *            consulta en base a éste.
	 * @return El objeto resultante.
	 */
	public Object read(Object[] models, long id);

	/**
	 * Permite obtener un valor de verdad sobre la consulta en un arreglo de
	 * modelos anidados y los valores repetidos del nombre en cadena de texto
	 * basado en el id suministrado.
	 * 
	 * @param models
	 *            Arreglo de modelos donde el primero es el objeto resultante y
	 *            el orden es importante.
	 * @param name
	 *            El nombre del campo a verificar si está repetido o no.
	 * @param id
	 *            Identificador del último modelo en el arreglo para realizar la
	 *            consulta en base a éste.
	 * @return {@code true} si está repetido, {@code false} si no lo está.
	 */
	public boolean read(Object[] models, String name, long id);

	/**
	 * Permite obtener el registro activo especificado por id del modelo dado.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @param id
	 *            Es el id del registro a consultar.
	 * @return El objeto modelo con sus atributos.
	 */
	public Object read(Object model, long id);

	/**
	 * Permite obtener una lista de todos los registros de una relación de 1:N
	 * 
	 * @param model
	 *            Un modelo que está relacionado de 1:N
	 * @param hasMany
	 *            Un modelo que pertenece a una relación 1:N
	 * @param id
	 *            El id a identificar en la relación
	 * @return Una lista de objetos del modelo.
	 */
	public ArrayList<?> read(Object model, Object belongsTo, long id); // PROBAR

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
}
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
	 * @return
	 */
	public String getField(Object model);

	/**
	 * Permite obtener sufijo para ser agregado al campo que será utilizado para
	 * la consulta del id de cada modelo.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @return
	 */
	public String getSuffix(Object model);
}
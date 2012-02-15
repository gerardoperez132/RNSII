package ve.gob.cnti.srsi.dao;

import java.util.ArrayList;

public interface CRUD {

	/**
	 * Permite guardar un registro en la base de datos.
	 * 
	 * @param model
	 *            Es el modelo o tabla en el cual se realizará el registro.
	 */
	public void create(Object model);

	public Object read(Object model, long id);

	public ArrayList<Object> read(Object model);

	public void update(Object model, String table, String column, long id);

	public void delete();
}

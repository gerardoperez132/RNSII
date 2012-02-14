package ve.gob.cnti.srsi.dao;

public interface CRUD {

	/**
	 * Permite guardar un registro en la base de datos.
	 * 
	 * @param model
	 *            Es el modelo o tabla en el cual se realizará el registro.
	 */
	public void create(Object model);

	public void read(String table, String column, long id);

	public void update(Object model, String table, String column, long id);

	public void delete();
}

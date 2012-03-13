package ve.gob.cnti.srsi.dao;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ve.gob.cnti.srsi.dao.Constants.ClaseDato;
import ve.gob.cnti.srsi.dao.Constants.Status;
import ve.gob.cnti.srsi.dao.Constants.TipoEntradaSalida;
import ve.gob.cnti.srsi.modelo.TipoDato;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase DAO de la cual se pueden usar los métodos por parte de todos los demás
 * controladores.
 * 
 * @author Joaquín Pereira
 * @author Richard Ricciardelli
 * @see CRUD
 * 
 */
@SuppressWarnings("serial")
public class DAO extends ActionSupport implements CRUD, Status, ClaseDato,
		TipoEntradaSalida {

	private static Session session;
	private static Transaction transaction;

	public void startConnection() {
		session = HibernateUtils.getSessionFactory().openSession();
		transaction = session.beginTransaction();
	}

	public void handleException(HibernateException he)
			throws HibernateException {
		transaction.rollback();
		throw new HibernateException(
				"Ocurrió un error en la capa de acceso a datos", he);
	}

	public void closeConnection() {
		session.close();
	}

	@Override
	public long getNextId(Object model) {
		long id = 1;
		try {
			startConnection();
			Query query = session.createQuery("SELECT MAX("
					+ getField(model).toString().replace("get", "")
							.toLowerCase() + ") FROM "
					+ model.getClass().getSimpleName().toString());
			if (query.uniqueResult() != null)
				id = Long.parseLong(String.valueOf(query.uniqueResult())) + 1;
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return id;
	}

	@Override
	public String getField(Object model) {
		for (Method method : model.getClass().getMethods()) {
			String pattern = method
					.getName()
					.toString()
					.replace(
							"getId" + getSuffix(model),
							"getId" + getSuffix(model)
									+ " DIOS NO JUEGA A LOS DADOS");
			if (!method.getName().toString().equals(pattern)) {
				return method.getName().toString().replace("get", "")
						.toString().toLowerCase();
			}
		}
		return null;
	}

	@Override
	public String getSuffix(Object model) {
		ArrayList<Character> newString = new ArrayList<Character>();
		for (int i = 0; i < model.getClass().getSimpleName().length(); i++) {
			if (model.getClass().getSimpleName().codePointAt(i) < 97) {
				newString.add('_');
			}
			newString.add(model.getClass().getSimpleName().charAt(i));
		}
		String result = "";
		for (char c : newString)
			result = result + c;
		return result.toString().toLowerCase();
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<?> getParents(Object model) {
		ArrayList<?> parents;
		try {
			startConnection();
			parents = (ArrayList<Object>) session
					.createQuery(
							"FROM "
									+ model.getClass().getSimpleName()
											.toString() + " WHERE status = "
									+ ACTIVO + " AND id_padre = 0").list();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return parents;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<?> getChildren(Object model) {
		ArrayList<?> children;
		try {
			startConnection();
			children = (ArrayList<Object>) session.createQuery(
					"FROM " + model.getClass().getSimpleName().toString()
							+ " WHERE status = " + ACTIVO
							+ " AND id_padre != 0").list();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return children;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<?> getSimple() {
		ArrayList<?> simple;
		try {
			startConnection();
			simple = (ArrayList<Object>) session.createQuery(
					"FROM "
							+ new TipoDato().getClass().getSimpleName()
									.toString() + " WHERE status = " + ACTIVO
							+ " AND tipo = " + SIMPLE).list();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return simple;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<?> getComplex() {
		ArrayList<?> complex;
		try {
			startConnection();
			complex = (ArrayList<Object>) session.createQuery(
					"FROM "
							+ new TipoDato().getClass().getSimpleName()
									.toString() + " WHERE status = " + ACTIVO
							+ " AND tipo = " + COMPUESTO).list();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return complex;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<?> getALL() {
		ArrayList<?> complex;
		try {
			startConnection();
			complex = (ArrayList<Object>) session.createQuery(
					"FROM "
							+ new TipoDato().getClass().getSimpleName()
									.toString() + " WHERE status = " + ACTIVO)
					.list();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return complex;
	}

	@Override
	public void create(Object model) {
		Date date = new Date();
		long id = getNextId(model);
		try {
			startConnection();
			session.save(model);
			session.createQuery(
					"UPDATE " + model.getClass().getSimpleName() + " SET "
							+ getField(model) + " = " + id
							+ ", fecha_creado = '" + date
							+ "', fecha_modificado = '" + date + "', status = "
							+ ACTIVO + " WHERE " + getField(model) + " = 0")
					.executeUpdate();
			transaction.commit();
		} catch (HibernateException he) {
			handleException(he);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void create(Object model, long id) {
		Date date = new Date();
		try {
			startConnection();
			session.save(model);
			session.createQuery(
					"UPDATE " + model.getClass().getSimpleName() + " SET "
							+ getField(model) + " = " + id
							+ ", fecha_creado = '" + date
							+ "', fecha_modificado = '" + date + "', status = "
							+ ACTIVO + " WHERE " + getField(model) + " = " + id)
					.executeUpdate();
			transaction.commit();
		} catch (HibernateException he) {
			handleException(he);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void create(Object modelOne, Object modelTwo, long id) {
		Date date = new Date();
		try {
			startConnection();
			session.save(modelOne);
			session.createQuery(
					"UPDATE " + modelOne.getClass().getSimpleName()
							+ " SET fecha_creado = '" + date
							+ "', fecha_modificado = '" + date + "', status = "
							+ ACTIVO + " WHERE " + getField(modelTwo) + " = "
							+ id).executeUpdate();
			transaction.commit();
		} catch (HibernateException he) {
			handleException(he);
		} finally {
			closeConnection();
		}
	}

	@Override
	public ArrayList<?> read(Object[] models, int type, long id) {
		ArrayList<?> result;
		String query = "SELECT "
				+ models[0].getClass().getSimpleName().toLowerCase()
				+ ".* FROM ";
		try {
			startConnection();
			for (short i = 0; i < models.length; i++) {
				if (i < 1)
					query += models[i].getClass().getAnnotation(Table.class)
							.name().toString()
							+ " AS "
							+ models[i].getClass().getSimpleName()
									.toLowerCase() + " INNER JOIN ";
				else if (i > 0 && i < (models.length - 1))
					query += models[i].getClass().getAnnotation(Table.class)
							.name().toString()
							+ " AS "
							+ models[i].getClass().getSimpleName()
									.toLowerCase()
							+ " ON "
							+ models[i - 1].getClass().getSimpleName()
									.toLowerCase()
							+ "."
							+ getField(models[i])
							+ " = "
							+ models[i].getClass().getSimpleName()
									.toLowerCase()
							+ "."
							+ getField(models[i])
							+ " INNER JOIN ";
				else
					query += models[i].getClass().getAnnotation(Table.class)
							.name().toString()
							+ " AS "
							+ models[i].getClass().getSimpleName()
									.toLowerCase()
							+ " ON "
							+ models[i].getClass().getSimpleName()
									.toLowerCase()
							+ "."
							+ getField(models[i])
							+ " = "
							+ models[i - 1].getClass().getSimpleName()
									.toLowerCase()
							+ "."
							+ getField(models[i])
							+ " WHERE "
							+ models[i].getClass().getSimpleName()
									.toLowerCase()
							+ "."
							+ getField(models[i])
							+ " = "
							+ id
							+ " AND "
							+ models[0].getClass().getSimpleName()
									.toLowerCase()
							+ ".status = "
							+ ACTIVO
							+ " AND "
							+ models[i].getClass().getSimpleName()
									.toLowerCase()
							+ ".status = "
							+ ACTIVO
							+ " AND "
							+ models[1].getClass().getSimpleName()
									.toLowerCase() + ".tipo = " + type;

			}
			System.out.println("QUERY => " + query);
			result = (ArrayList<?>) session.createSQLQuery(query)
					.addEntity(models[0].getClass()).list();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return result;
	}

	@Override
	public Object read(Object[] models, long id) {
		Object result;
		String query = "SELECT "
				+ models[0].getClass().getSimpleName().toLowerCase()
				+ ".* FROM ";
		try {
			startConnection();
			for (short i = 0; i < models.length; i++) {
				if (i < 1)
					query += models[i].getClass().getAnnotation(Table.class)
							.name().toString()
							+ " AS "
							+ models[i].getClass().getSimpleName()
									.toLowerCase() + " INNER JOIN ";
				else if (i > 0 && i < (models.length - 1))
					query += models[i].getClass().getAnnotation(Table.class)
							.name().toString()
							+ " AS "
							+ models[i].getClass().getSimpleName()
									.toLowerCase()
							+ " ON "
							+ models[i - 1].getClass().getSimpleName()
									.toLowerCase()
							+ "."
							+ getField(models[i - 1])
							+ " = "
							+ models[i].getClass().getSimpleName()
									.toLowerCase()
							+ "."
							+ getField(models[i - 1]) + " INNER JOIN ";
				else
					query += models[i].getClass().getAnnotation(Table.class)
							.name().toString()
							+ " AS "
							+ models[i].getClass().getSimpleName()
									.toLowerCase()
							+ " ON "
							+ models[i].getClass().getSimpleName()
									.toLowerCase()
							+ "."
							+ getField(models[i])
							+ " = "
							+ models[i - 1].getClass().getSimpleName()
									.toLowerCase()
							+ "."
							+ getField(models[i])
							+ " WHERE "
							+ models[i].getClass().getSimpleName()
									.toLowerCase()
							+ "."
							+ getField(models[i])
							+ " = "
							+ id
							+ " AND "
							+ models[0].getClass().getSimpleName()
									.toLowerCase() + ".status = " + ACTIVO;

			}
			System.out.println("QUERY => " + query);
			result = session.createSQLQuery(query)
					.addEntity(models[0].getClass()).uniqueResult();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return result;
	}

	@Override
	public boolean read(Object[] models, String name, long id) {
		boolean result = false;
		String query = "SELECT "
				+ models[0].getClass().getSimpleName().toLowerCase()
				+ ".* FROM ";
		try {
			startConnection();
			for (short i = 0; i < models.length; i++) {
				if (i < 1)
					query += models[i].getClass().getAnnotation(Table.class)
							.name().toString()
							+ " AS "
							+ models[i].getClass().getSimpleName()
									.toLowerCase() + " INNER JOIN ";
				else if (i > 0 && i < (models.length - 1))
					query += models[i].getClass().getAnnotation(Table.class)
							.name().toString()
							+ " AS "
							+ models[i].getClass().getSimpleName()
									.toLowerCase()
							+ " ON "
							+ models[i - 1].getClass().getSimpleName()
									.toLowerCase()
							+ "."
							+ getField(models[i])
							+ " = "
							+ models[i].getClass().getSimpleName()
									.toLowerCase()
							+ "."
							+ getField(models[i])
							+ " INNER JOIN ";
				else
					query += models[i].getClass().getAnnotation(Table.class)
							.name().toString()
							+ " AS "
							+ models[i].getClass().getSimpleName()
									.toLowerCase()
							+ " ON "
							+ models[i].getClass().getSimpleName()
									.toLowerCase()
							+ "."
							+ getField(models[i])
							+ " = "
							+ models[i - 1].getClass().getSimpleName()
									.toLowerCase()
							+ "."
							+ getField(models[i])
							+ " WHERE "
							+ models[i].getClass().getSimpleName()
									.toLowerCase()
							+ "."
							+ getField(models[i])
							+ " = "
							+ id
							+ " AND "
							+ models[0].getClass().getSimpleName()
									.toLowerCase()
							+ ".status = "
							+ ACTIVO
							+ " AND "
							+ models[0].getClass().getSimpleName()
									.toLowerCase() + ".nombre = '" + name + "'";
			}
			System.out.println("QUERY => " + query);
			if (session.createSQLQuery(query).addEntity(models[0].getClass())
					.uniqueResult() != null)
				result = true;
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return result;
	}

	@Override
	public Object read(Object model, long id) {
		Object result;
		try {
			startConnection();
			result = session.createQuery(
					"FROM "
							+ model.getClass().getSimpleName().toString()
							+ " WHERE "
							+ getField(model).toString().replace("get", "")
									.toLowerCase() + " = " + id
							+ " AND status = " + ACTIVO).uniqueResult();

		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<?> read(Object model, Object belongsTo, long id) {
		ArrayList<?> result;
		try {
			startConnection();
			result = (ArrayList<Object>) session.createQuery(
					"FROM " + model.getClass().getSimpleName().toString()
							+ " WHERE " + getField(belongsTo) + " = " + id
							+ " AND status = " + ACTIVO).list();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<?> read(Object model) {
		ArrayList<?> result;
		try {
			startConnection();
			result = (ArrayList<Object>) session.createQuery(
					"FROM " + model.getClass().getSimpleName().toString()
							+ " WHERE status = " + ACTIVO).list();

		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return result;
	}

	@Override
	public void update(Object model, long id) {

		try {
			startConnection();
			session.createQuery(
					"UPDATE " + model.getClass().getSimpleName()
							+ " SET status = " + MODIFICADO
							+ ", fecha_modificado = '" + new Date()
							+ "' WHERE " + getField(model) + " = " + id
							+ " AND status = " + ACTIVO).executeUpdate();
			session.save(model);
			session.createSQLQuery(
					"UPDATE "
							+ model.getClass().getAnnotation(Table.class)
									.name().toLowerCase()
							+ " SET status = "
							+ ACTIVO
							+ ", fecha_modificado = '"
							+ new Date()
							+ "', "
							+ getField(model)
							+ " = "
							+ id
							+ ", fecha_creado = (SELECT fecha_creado FROM "
							+ model.getClass().getAnnotation(Table.class)
									.name().toLowerCase() + " WHERE "
							+ getField(model) + " = " + id + " AND status = "
							+ MODIFICADO
							+ " ORDER BY fecha_modificado LIMIT 1) WHERE "
							+ getField(model) + " = 0").executeUpdate();
			transaction.commit();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}

	}

	@Override
	public void delete(Object model, long id) {
		try {
			startConnection();
			session.createQuery(
					"UPDATE " + model.getClass().getSimpleName()
							+ " SET status = " + ELIMINADO
							+ ", fecha_modificado = '" + new Date()
							+ "' WHERE status = " + ACTIVO + " AND "
							+ getField(model) + " = " + id).executeUpdate();
			transaction.commit();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
	}
}
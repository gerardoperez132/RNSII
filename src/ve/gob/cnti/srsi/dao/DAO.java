package ve.gob.cnti.srsi.dao;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ve.gob.cnti.srsi.dao.Constants.ClaseDato;
import ve.gob.cnti.srsi.dao.Constants.Status;
import ve.gob.cnti.srsi.dao.Constants.TipoEntradaSalida;
import ve.gob.cnti.srsi.modelo.Dato;
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
							+ " AND clase_dato = " + SIMPLE).list();
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
							+ " AND clase_dato = " + COMPUESTO).list();
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

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<?> readEntrada(long id) {
		ArrayList<?> result;
		try {
			startConnection();
			result = (ArrayList<Object>) session
					.createSQLQuery(
							"SELECT d.* FROM datos AS d INNER JOIN entradas_salidas AS es ON d.id_entrada_salida = es.id_entrada_salida INNER JOIN funcionalidades AS f ON f.id_funcionalidad = es.id_funcionalidad WHERE f.id_funcionalidad = "
									+ id
									+ " AND d.status = "
									+ ACTIVO
									+ " AND es.tipo = " + ENTRADA).list();
			Iterator<Dato> iterator = (Iterator<Dato>) result.iterator();
			while (iterator.hasNext()) {
				System.out.println(" DATO => " + iterator.next().toString());
			}

		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return result;

	}

	@Override
	public String read(long id) {

		String result;
		try {
			startConnection();
			result = (String) session

					.createSQLQuery(
							"SELECT td.nombre FROM tipos_datos AS td INNER JOIN datos AS d ON d.id_tipo_dato = td.id_tipo_dato INNER JOIN entradas_salidas AS es ON es.id_dato = d.id_dato WHERE es.id_entrada_salida = "
									+ id + " AND td.status = " + ACTIVO)
					.uniqueResult();
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
							+ "' WHERE " + getField(model) + " = " + id)
					.executeUpdate();
			session.save(model);
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
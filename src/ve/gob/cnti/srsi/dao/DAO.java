package ve.gob.cnti.srsi.dao;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Clase DAO de la cual heredan los métodos todos los demás controladores.
 * 
 * @author Joaquín Pereira
 * @author Richard Ricciardelli
 * @see CRUD
 * 
 */
public class DAO implements CRUD {

	private Session session;
	private Transaction transaction;

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
	public void create(Object model) {
		try {
			startConnection();
			session.save(model);
			transaction.commit();
		} catch (HibernateException he) {
			handleException(he);
		} finally {
			closeConnection();
		}
	}

	@Override
	public Object read(Object model, long id) {
		try {
			startConnection();
			return session.createQuery(
					"FROM "
							+ model.getClass().getName().toString()
							+ " WHERE "
							+ getField(model).toString().replace("get", "")
									.toLowerCase() + " = " + id
							+ " AND status = " + Status.ACTIVO).uniqueResult();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<?> read(Object model) {
		try {
			startConnection();
			return (ArrayList<Object>) session.createQuery(
					"FROM " + model.getClass().getName().toString()
							+ " WHERE status = " + Status.ACTIVO).list();

		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
	}

	@Override
	public void update(Object model, long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object model, long id) {
		// TODO Auto-generated method stub

	}
}
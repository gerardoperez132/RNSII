package ve.gob.cnti.srsi.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAO {

	public static Session session;
	public static Transaction transaction;

	public static void startConnection() {
		session = HibernateUtils.getSessionFactory().openSession();
		transaction = session.beginTransaction();
	}

	public static void handleException(HibernateException he)
			throws HibernateException {
		transaction.rollback();
		throw new HibernateException(
				"Ocurrió un error en la capa de acceso a datos", he);
	}

	public static void closeConnection() {
		session.close();
	}

	public static long getNextId(String table, String column) {
		long id = 0;
		try {
			startConnection();
			// Obtener el id mayor de la columna dada.
			// id = SELECT MAX(column) FROM table;
			// id++;
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return id;
	}

}

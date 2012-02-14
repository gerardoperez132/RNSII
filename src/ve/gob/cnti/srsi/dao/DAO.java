package ve.gob.cnti.srsi.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAO {

	private static Session session;
	private static Transaction transaction;

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		DAO.session = session;
	}

	public static Transaction getTransaction() {
		return transaction;
	}

	public static void setTransaction(Transaction transaction) {
		DAO.transaction = transaction;
	}

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
			Query query = session.createSQLQuery("SELECT MAX(" + column
					+ ") FROM " + table);
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

}
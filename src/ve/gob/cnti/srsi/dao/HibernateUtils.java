package ve.gob.cnti.srsi.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Clase con utilidades de Hibernate.
 * 
 * @author Richard Ricciardelli
 * 
 */
public class HibernateUtils {

	private static final SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new AnnotationConfiguration().configure()
					.buildSessionFactory();
		} catch (HibernateException he) {
			System.err
					.println("Ocurrió un error en la inicialización de la SessionFactory: "
							+ he);
			throw new ExceptionInInitializerError(he);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
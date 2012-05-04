package ve.gob.cnti.srsi.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ve.gob.cnti.srsi.dao.Constants.ClaseDato;
import ve.gob.cnti.srsi.dao.Constants.Status;
import ve.gob.cnti.srsi.dao.Constants.TipoEntradaSalida;
import ve.gob.cnti.srsi.i18n.Errors;
import ve.gob.cnti.srsi.modelo.Correo;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.Telefono;
import ve.gob.cnti.srsi.modelo.TipoDato;
import ve.gob.cnti.srsi.modelo.UnionAreaServicioInformacion;
import ve.gob.cnti.srsi.modelo.UnionArquitecturaServicioInformacion;

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

	public static Errors error = new Errors();
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
	public void createUnion(Object model) {
		Date date = new Date();
		try {
			startConnection();
			session.save(model);
			session.createQuery(
					"UPDATE " + model.getClass().getSimpleName()
							+ " SET fecha_creado = '" + date
							+ "', fecha_modificado = '" + date + "', status = "
							+ ACTIVO + " WHERE fecha_creado IS NULL")
					.executeUpdate();
			transaction.commit();
		} catch (HibernateException he) {
			handleException(he);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void deleteUnion(Object[] models, long id_u, long id) {
		try {
			startConnection();
			session.createQuery(
					"UPDATE " + models[0].getClass().getSimpleName()
							+ " SET status = " + ELIMINADO
							+ ", fecha_modificado = '" + new Date()
							+ "' WHERE " + getField(models[1]) + " = " + id
							+ " AND " + getField(models[2]) + " = " + id_u
							+ " AND status = " + ACTIVO).executeUpdate();
			transaction.commit();
		} catch (HibernateException he) {
			handleException(he);
		} finally {
			closeConnection();
		}
	}

	@Override
	public ArrayList<?> read(Object model) {
		ArrayList<?> result;
		try {
			startConnection();
			result = (ArrayList<?>) session.createQuery(
					"FROM " + model.getClass().getSimpleName()
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
	public ArrayList<?> readUnion(Object unionModel, Object model, long id) {
		ArrayList<?> result;
		try {
			startConnection();
			result = (ArrayList<?>) session.createQuery(
					"FROM " + unionModel.getClass().getSimpleName() + " WHERE "
							+ getField(model) + " = " + id + " AND status = "
							+ ACTIVO).list();
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
					"FROM " + model.getClass().getSimpleName() + " WHERE "
							+ getField(model) + " = " + id + " AND status = "
							+ ACTIVO).uniqueResult();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return result;
	}

	@Override
	public ArrayList<?> read(Object[] models, long id, int type) {
		ArrayList<?> result;
		try {
			startConnection();
			if (type >= 0)
				result = (ArrayList<?>) session.createQuery(
						"FROM " + models[0].getClass().getSimpleName()
								+ " WHERE " + getField(models[1]) + " = " + id
								+ " AND tipo = " + type + " AND status = "
								+ ACTIVO + " ORDER BY " + getField(models[0]))
						.list();
			else
				result = (ArrayList<?>) session.createQuery(
						"FROM " + models[0].getClass().getSimpleName()
								+ " WHERE " + getField(models[1]) + " = " + id
								+ " AND status = " + ACTIVO + " ORDER BY "
								+ getField(models[0])).list();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return result;
	}

	@Override
	public boolean read(Object[] models, long id, String name) {
		boolean result = false;
		try {
			startConnection();
			if (session.createQuery(
					"FROM " + models[0].getClass().getSimpleName() + " WHERE "
							+ getField(models[1]) + " = " + id
							+ " AND nombre = '" + name + "' AND status = "
							+ ACTIVO).uniqueResult() != null)
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
	public Correo getUserEmail(String email) {
		Correo result;
		try {
			startConnection();
			result = (Correo) session.createQuery(
					"FROM " + new Correo().getClass().getSimpleName()
							+ " WHERE correo = '" + email + "' AND status = "
							+ ACTIVO + " AND id_usuario != 0").uniqueResult();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return result;
	}

	@Override
	public Correo getEmail(Object model, long id) {
		Correo result;
		try {
			startConnection();
			result = (Correo) session.createQuery(
					"FROM " + new Correo().getClass().getSimpleName()
							+ " WHERE status = " + ACTIVO + " AND "
							+ getField(model) + " = " + id).uniqueResult();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return result;
	}

	@Override
	public Telefono getPhone(Object model, long id) {
		Telefono result;
		try {
			startConnection();
			result = (Telefono) session.createQuery(
					"FROM " + new Telefono().getClass().getSimpleName()
							+ " WHERE status = " + ACTIVO + " AND "
							+ getField(model) + " = " + id).uniqueResult();
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

	@SuppressWarnings("rawtypes")
	@Override
	public void update(Object model) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		try {
			startConnection();
			Class date[] = { Date.class };
			model.getClass().getMethod("setFecha_modificado", date)
					.invoke(model, new Date());
			session.update(model);
			transaction.commit();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
	}

	// TODO Quitar las advertencias
	@SuppressWarnings({ "rawtypes" })
	@Override
	public void updateUnion(Object unionModel, Object modelParent,
			Object modelChild, long idParent, List<?> children)
			throws Exception {
		List<?> parents = readUnion(unionModel, modelParent, idParent);
		try {
			startConnection();
			session.createQuery(
					"UPDATE " + unionModel.getClass().getSimpleName()
							+ " SET status = " + MODIFICADO
							+ ", fecha_modificado = '" + new Date()
							+ "' WHERE " + getField(modelParent) + " = "
							+ idParent + " AND status = " + ACTIVO)
					.executeUpdate();
			transaction.commit();
			closeConnection();
			for (Object child : children) {
				startConnection();
				Class parameters[] = { long.class };
				unionModel
						.getClass()
						.getMethod(
								"setId" + getField(modelParent).substring(2),
								parameters).invoke(unionModel, idParent);
				unionModel
						.getClass()
						.getMethod("setId" + getField(modelChild).substring(2),
								parameters).invoke(unionModel, child);
				session.save(unionModel);
				session.createQuery(
						"UPDATE "
								+ unionModel.getClass().getSimpleName()
								+ " SET fecha_creado = '"
								+ parents.get(0).getClass()
										.getMethod("getFecha_creado", null)
										.invoke(parents.get(0), null)
								+ "', fecha_modificado = '" + new Date()
								+ "', status = " + ACTIVO
								+ " WHERE fecha_creado IS NULL")
						.executeUpdate();
				transaction.commit();
			}
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

	@Override
	public long getNextId(Object model) {
		long id = 1;
		try {
			startConnection();
			Query query = session.createQuery("SELECT MAX(" + getField(model)
					+ ") FROM " + model.getClass().getSimpleName());
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
					"FROM " + model.getClass().getSimpleName()
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

	@SuppressWarnings({ "unchecked" })
	public boolean isComplete(ServicioInformacion servicio) {
		List<UnionAreaServicioInformacion> unionareas;
		List<UnionArquitecturaServicioInformacion> unionarquitecturas;
		if (servicio.getId_sector() == 0) {
			System.out.println("FALLÓ EN SECTOR");
			return false;
		}
		unionareas = (List<UnionAreaServicioInformacion>) readUnion(
				new UnionAreaServicioInformacion(), servicio,
				servicio.getId_servicio_informacion());
		if (unionareas.isEmpty()) {
			System.out.println("FALLÓ EN UNIÓN ÁREAS");
			return false;
		}
		if (servicio.getId_estado() == 0) {
			System.out.println("FALLÓ EN ESTADO");
			return false;
		}
		if (servicio.getId_seguridad() == 0) {
			System.out.println("FALLÓ EN SEGURIDAD");
			return false;
		}
		unionarquitecturas = (List<UnionArquitecturaServicioInformacion>) readUnion(
				new UnionArquitecturaServicioInformacion(), servicio,
				servicio.getId_servicio_informacion());
		if (unionarquitecturas.isEmpty()) {
			System.out.println("FALLÓ EN UNIÓN ARQUITECTURAS");
			return false;
		}
		if (servicio.getId_intercambio() == 0) {
			System.out.println("FALLÓ EN INTERCAMBIO");
			return false;
		}
		Telefono phone = new Telefono();
		phone = (Telefono) getPhone(servicio,
				servicio.getId_servicio_informacion());
		if (phone == null) {
			System.out.println("FALLÓ EN TELÉFONO");
			return false;
		}
		Correo email = new Correo();
		email = (Correo) getEmail(servicio,
				servicio.getId_servicio_informacion());
		if (email == null) {
			System.out.println("FALLÓ EN CORREO");
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<?> getSortedList(Object model, byte orderBy) {
		ArrayList<?> list;
		String order = orderBy > 0 ? "DESC" : "ASC";
		try {
			startConnection();
			list = (ArrayList<Object>) session.createQuery(
					"FROM " + model.getClass().getSimpleName()
							+ " WHERE status = " + ACTIVO + " ORDER BY nombre "
							+ order).list();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return list;
	}
}
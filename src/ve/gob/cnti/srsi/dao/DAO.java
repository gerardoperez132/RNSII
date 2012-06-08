package ve.gob.cnti.srsi.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ve.gob.cnti.modelo.temporales.ListaSImasVisitados;
import ve.gob.cnti.modelo.temporales.SectoresMasPublicados;
import ve.gob.cnti.modelo.temporales.Solicitud_Suscripcion;
import ve.gob.cnti.srsi.dao.Constants.ClaseDato;
import ve.gob.cnti.srsi.dao.Constants.Status;
import ve.gob.cnti.srsi.dao.Constants.Sentencias;
import ve.gob.cnti.srsi.dao.Constants.TipoEntradaSalida;
import ve.gob.cnti.srsi.i18n.Errors;
import ve.gob.cnti.srsi.modelo.Correo;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.SolicitudSuscripcion;
import ve.gob.cnti.srsi.modelo.Telefono;
import ve.gob.cnti.srsi.modelo.TipoDato;
import ve.gob.cnti.srsi.modelo.UnionAreaServicioInformacion;
import ve.gob.cnti.srsi.modelo.UnionArquitecturaServicioInformacion;
import ve.gob.cnti.srsi.modelo.Visita;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase DAO de la cual se pueden usar los métodos por parte de todos los demás
 * controladores.
 * 
 * @author Joaquín Pereira
 * @author Richard Ricciardelli
 * @see CRUD
 * @see Status
 * @see ClaseDato
 * @see TipoEntradaSalida
 */
@SuppressWarnings("serial")
public class DAO extends ActionSupport implements Constants, CRUD, Status,
		ClaseDato, TipoEntradaSalida,Sentencias {

	public static Errors error = new Errors();
	private static Session session;
	private static Transaction transaction;

	/** Inicia la conexión a la base de datos. */
	public void startConnection() {
		session = HibernateUtils.getSessionFactory().openSession();
		transaction = session.beginTransaction();
	}

	/** Captura la excepción cuando no se logra conectar a la base de datos. */
	public void handleException(HibernateException he)
			throws HibernateException {
		transaction.rollback();
		// TODO Esto debería manejarse en la vista también.
		throw new HibernateException(
				"Ocurrió un error en la capa de acceso a datos", he);
	}

	/** Cierra la conexión a la base de datos. */
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
	public boolean read(Object[] models, long id) {
		boolean result = false;
		try {
			startConnection();
			if (session.createQuery(
					"FROM " + models[0].getClass().getSimpleName() + " WHERE "
							+ getField(models[1]) + " = " + id
							+ " AND status = " + ACTIVO).uniqueResult() != null)
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
	public Object readf(Object[] models, long id) {
		Object result;
		try {
			startConnection();
			result = session.createQuery(
					"FROM " + models[0].getClass().getSimpleName() + " WHERE "
							+ getField(models[1]) + " = " + id
							+ " AND status = " + ACTIVO).uniqueResult();
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
							+ " ORDER BY fecha_modificado DESC LIMIT 1) WHERE "
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

	@Override
	public Object getUrlRecoveryPass(Object model, String Url) {
		Object result;
		try {
			startConnection();
			result = session.createQuery(
					"FROM " + model.getClass().getSimpleName() + " WHERE "
							+ " url = '" + Url + "' AND status = " + ACTIVO)
					.uniqueResult();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return result;
	}

	public long nSiSector(long id) {
		long result;
		try {
			startConnection();
			result = session
					.createQuery(
							"FROM ServicioInformacion WHERE " + " id_sector = "
									+ id + " AND status = " + ACTIVO
									+ " AND publicado = true").list().size();
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
	public ArrayList<ServicioInformacion> buscarServicio(String cadena,
			byte orderBy) {
		ArrayList<ServicioInformacion> list;
		String order = orderBy > 0 ? "DESC" : "ASC";
		try {
			startConnection();
			list = (ArrayList<ServicioInformacion>) session
					.createQuery(
							" FROM ServicioInformacion s WHERE s.status = "
									+ ACTIVO
									+ " AND "
									+ " s.publicado = TRUE"
									+ " AND "
									+ " s.id_estado = 2 "
									+ " AND "
									+ " (UPPER(translate(s. nombre, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')) "
									+ " LIKE UPPER(translate('%"
									+ cadena
									+ "%', 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')) "
									+ " or UPPER(translate(s. descripcion, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')) "
									+ " LIKE UPPER(translate('%" + cadena
									+ "%', 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
									+ " ORDER BY nombre " + order).list();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ServicioInformacion> buscarServicio2(String cadena,
			byte orderBy, long id_ente) {
		ArrayList<ServicioInformacion> list;
		String order = orderBy > 0 ? "DESC" : "ASC";
		try {
			startConnection();
			list = (ArrayList<ServicioInformacion>) session
					.createQuery(
							" FROM ServicioInformacion s WHERE s.status = "
									+ ACTIVO
									+ " AND "
									+ " s.publicado = TRUE"
									+ " AND "
									+ " s.id_estado = 2 "
									+ " AND "
									+ " s.id_ente !=  "
									+ id_ente
									+ " AND "
									+ " (UPPER(translate(s. nombre, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')) "
									+ " LIKE UPPER(translate('%"
									+ cadena
									+ "%', 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')) "
									+ " or UPPER(translate(s. descripcion, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')) "
									+ " LIKE UPPER(translate('%" + cadena
									+ "%', 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
									+ " ORDER BY nombre " + order).list();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<ListaSImasVisitados> SImasVisitados() {
		List<ListaSImasVisitados> result = new ArrayList<ListaSImasVisitados>();
		try {
			startConnection();
			Query query = session
					.createSQLQuery(" select visitas.id_servicio_informacion, Servicios_informacion.nombre, count(visitas.id_servicio_informacion) "
							+ " from visitas, Servicios_informacion "
							+ " where (select Servicios_informacion.status where Servicios_informacion.id_servicio_informacion = visitas.id_servicio_informacion) = 0 "
							+ " AND "
							+ " (select Servicios_informacion.id_estado where Servicios_informacion.id_servicio_informacion = visitas.id_servicio_informacion) = 2 "
							+ " AND "
							+ " (select Servicios_informacion.publicado where Servicios_informacion.id_servicio_informacion = visitas.id_servicio_informacion) = TRUE "
							+ " GROUP BY visitas.id_servicio_informacion, Servicios_informacion.nombre "
							+ " ORDER BY count(visitas.id_servicio_informacion) desc "
							+ " limit " + LIMITE_VISITADOS);
			List list = query.list();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Object[] st = (Object[]) it.next();
				ListaSImasVisitados si = new ListaSImasVisitados();
				si.setId_servicio_informacion((Long) Long.parseLong(st[0]
						.toString()));
				si.setNombre((String) st[1]);
				si.setVisitas((Long) Long.parseLong(st[2].toString()));
				result.add(si);
			}
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return result;
	}
	//TODO
	@SuppressWarnings("rawtypes")
	@Override
	public List<SectoresMasPublicados> sectoresMasPublicados(int n) {
		List<SectoresMasPublicados> result = new ArrayList<SectoresMasPublicados>();
		try {
			startConnection();
			String consulta;
			if (n > 0) {
				consulta = " select sectores.id_sector, sectores.nombre, "
						+ "((select count(servicios_informacion.id_sector) from servicios_informacion where servicios_informacion.id_sector = sectores.id_sector AND Servicios_informacion.status = 0 AND Servicios_informacion.id_estado = 2 AND Servicios_informacion.publicado =TRUE)) "
						+ "from  sectores,Servicios_informacion "
						+ "where (select Servicios_informacion.status where Servicios_informacion.id_sector = sectores.id_sector) = 0 "
						+ "AND "
						+ "(select Servicios_informacion.id_estado where Servicios_informacion.id_sector = sectores.id_sector) = 2  "
						+ "AND "
						+ "(select Servicios_informacion.publicado where Servicios_informacion.id_sector = sectores.id_sector) = TRUE  "
						+ "GROUP BY sectores.nombre, sectores.id_sector ORDER BY count(Servicios_informacion.id_sector) limit "
						+ n;
			} else {
				consulta = "select sectores.id_sector, sectores.nombre, "
						+ "((select count(servicios_informacion.id_sector) from servicios_informacion where servicios_informacion.id_sector = sectores.id_sector AND Servicios_informacion.status = 0 AND Servicios_informacion.id_estado = 2 AND Servicios_informacion.publicado =TRUE)) as S "
						+ "from  sectores,Servicios_informacion GROUP BY sectores.nombre, sectores.id_sector "
						+ "ORDER BY s Desc";
			}			
			Query query = session.createSQLQuery(consulta);
			List list = query.list();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Object[] st = (Object[]) it.next();
				SectoresMasPublicados si = new SectoresMasPublicados();
				si.setId_sector((Long) Long.parseLong(st[0].toString()));
				si.setNombre((String) st[1]);
				si.setN((Long) Long.parseLong(st[2].toString()));
				result.add(si);
			}
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
	public ArrayList<ServicioInformacion> getSIList(byte orderBy) {
		ArrayList<ServicioInformacion> list;
		String order = orderBy > 0 ? "DESC" : "ASC";
		try {
			startConnection();
			list = (ArrayList<ServicioInformacion>) session.createQuery(
					" FROM ServicioInformacion s WHERE s.status = " + ACTIVO
							+ " AND " + " s.publicado = TRUE " + " AND "
							+ " s.id_estado = 2 "
							+ " ORDER BY s.id_servicio_informacion " + order)
					.list();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ServicioInformacion> getServicioInformacionPorSectorList(
			long id_sector, byte orderBy) {
		ArrayList<ServicioInformacion> list;
		String order = orderBy > 0 ? "DESC" : "ASC";
		try {
			startConnection();
			list = (ArrayList<ServicioInformacion>) session.createQuery(
					" FROM ServicioInformacion s WHERE s.status = " + ACTIVO
							+ " AND " + " s.publicado = TRUE " + " AND "
							+ " s.id_estado = 2 " + " AND s.id_sector = "
							+ id_sector
							+ " ORDER BY s.id_servicio_informacion " + order)
					.list();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return list;
	}

	@Override
	public void saveVisit(Visita visita) {
		visita.setId_visita(getNextId(visita));
		visita.setFecha(new Date());
		try {
			startConnection();
			session.save(visita);
			transaction.commit();
		} catch (HibernateException he) {
			handleException(he);
		} finally {
			closeConnection();
		}
	}

	@Override
	public long getVisits(long id) {
		try {
			startConnection();
			return (Long) session.createQuery(
					"SELECT COUNT(id_servicio_informacion) FROM "
							+ new Visita().getClass().getSimpleName()
							+ " WHERE id_servicio_informacion = " + id)
					.uniqueResult();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
	}

	@Override
	public boolean verifyClientAccess(String ip, long id) {
		try {
			startConnection();
			try {
				return (new Date().getTime() - ((Date) session.createSQLQuery(
						"SELECT fecha FROM visitas WHERE ip = '" + ip
								+ "' AND id_servicio_informacion = " + id
								+ " ORDER BY fecha DESC LIMIT 1")
						.uniqueResult()).getTime()) > (3600 * 24 * 1000);
			} catch (Exception e) {
				return true;
			}
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
	}

	
	@Override
	public long peticionesSuscripcion(long id) {
		long result;
		try {
			startConnection();
			result = session
					.createQuery(
							"FROM SolicitudSuscripcion WHERE " + " id_ente_proveedor = "
									+ id + " AND status = " + ACTIVO
									+ " AND leido = false").list().size();
		

		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}	
		return result;
	}

	@Override
	public boolean verifySuscriptionRequest(long service, long provider,
			long client) {
		try {
			startConnection();
			try {
				return ((SolicitudSuscripcion) session.createQuery(
						"FROM " + SolicitudSuscripcion.class.getSimpleName()
								+ " WHERE id_servicio_informacion = " + service
								+ " AND id_ente_proveedor = " + provider
								+ " AND id_ente_solicitante = " + client)
						.uniqueResult()) != null;
			} catch (Exception e) {
				return true;
			}

		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
	}
	
	@Override
	public long peticionesSuscripcionPendientes(long id){
		long result;
		try {
			startConnection();
			result = session
					.createQuery(
							"FROM SolicitudSuscripcion WHERE " + " id_ente_proveedor = "
									+ id + " AND status = " + ACTIVO
									+ " AND sentencia = " + PENDIENTE).list().size();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return result;
	}
	
	//TODO
	@Override
	public ArrayList<Solicitud_Suscripcion> getSolicitudesSuscripcionPendientes(
			long id_ente, byte orderBy) {
		List<Solicitud_Suscripcion> result =  new ArrayList<Solicitud_Suscripcion>();
		ArrayList<?> list;
		Query query;
		String order = orderBy > 0 ? "DESC" : "ASC";
		try {
			startConnection();
			query = session.createSQLQuery("select s.id_solicitud_suscripcion,s.id_servicio_informacion,s.leido,s.fecha_creado," +						
						" (select si.nombre from servicios_informacion as si where si.id_servicio_informacion = s.id_servicio_informacion and si.status=0) as servicio, "+
						" (select e.siglas from entes as e where e.id_ente = s.id_ente_solicitante and e.status=0) as ente"+
						" from solicitudes_suscripciones as s"+
						" where s.id_ente_proveedor = " + id_ente +
						" AND s.status = 0" +
						" ORDER BY s.leido "+ order);
			list = (ArrayList<?>) query.list();			
			Iterator<?> it = list.iterator();
			while (it.hasNext()) {
				Object[] st = (Object[]) it.next();
				Solicitud_Suscripcion s = new Solicitud_Suscripcion();
				s.setId_suscripcion((Long) Long.parseLong(st[0].toString()));
				s.setId_servicio_informacion((Long) Long.parseLong(st[1].toString()));
				s.setLeido((Boolean) Boolean.parseBoolean(st[2].toString()));
				s.setFecha_creado((Date)st[3]);				
				s.setServicio((String)st[4].toString());
				s.setEnte((String)st[5].toString());				
				result.add(s);
			}
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return (ArrayList<Solicitud_Suscripcion>) result;
	}
}
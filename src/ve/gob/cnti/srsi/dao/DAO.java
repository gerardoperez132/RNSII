/* This file is part of SRSI.
 * 
 * SRSI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * SRSI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SRSI. If not, see <http://www.gnu.org/licenses/>.
 */
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

import ve.gob.cnti.srsi.dao.Constants.ClaseDato;
import ve.gob.cnti.srsi.dao.Constants.ErrorServicio;
import ve.gob.cnti.srsi.dao.Constants.Estados;
import ve.gob.cnti.srsi.dao.Constants.Sentencias;
import ve.gob.cnti.srsi.dao.Constants.Status;
import ve.gob.cnti.srsi.dao.Constants.TipoEntradaSalida;
import ve.gob.cnti.srsi.i18n.Errors;
import ve.gob.cnti.srsi.modelo.Correo;
import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Funcionalidad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.SolicitudSuscripcion;
import ve.gob.cnti.srsi.modelo.Telefono;
import ve.gob.cnti.srsi.modelo.TipoDato;
import ve.gob.cnti.srsi.modelo.UnionAreaServicioInformacion;
import ve.gob.cnti.srsi.modelo.UnionArquitecturaServicioInformacion;
import ve.gob.cnti.srsi.modelo.Visita;
import ve.gob.cnti.srsi.util.ListaServiciosVisitados;
import ve.gob.cnti.srsi.util.SectoresMasPublicados;
import ve.gob.cnti.srsi.util.SubscriptionRequest;
import ve.gob.cnti.srsi.util.SubscriptionResponse;

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
		ClaseDato, TipoEntradaSalida, Sentencias, ErrorServicio, Estados {

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

	@Override
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
		if (servicio.getId_estado() == DESARROLLO) {
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

		Object[] models = { new Funcionalidad(), new ServicioInformacion() };
		List<Funcionalidad> funcionalidades = new ArrayList<Funcionalidad>();
		funcionalidades = (List<Funcionalidad>) read(models,
				servicio.getId_servicio_informacion(), -1);
		if (funcionalidades.isEmpty()) {
			System.out.println("FALLÓ EN FUNCIONALIDADES");
			return false;
		} else {
			Iterator<Funcionalidad> fxIterado = funcionalidades.iterator();
			Funcionalidad fx = new Funcionalidad();
			while (fxIterado.hasNext()) {
				fx = fxIterado.next();
				Object[] models2 = { new EntradaSalida(), new Funcionalidad() };
				List<EntradaSalida> salidas_tmp = new ArrayList<EntradaSalida>();
				salidas_tmp = (List<EntradaSalida>) read(models2,
						fx.getId_funcionalidad(), SALIDA);
				if (salidas_tmp.isEmpty()) {
					System.out.println("FALLÓ EN DATOS DE SALIDAS");
					return false;
				}
			}
		}
		return true;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<String> getIncompleteFields(ServicioInformacion servicio) {
		List<UnionAreaServicioInformacion> unionareas;
		List<UnionArquitecturaServicioInformacion> unionarquitecturas;
		List<String> incompletos = new ArrayList<String>();
		if (servicio.getId_sector() == 0) {
			System.out.println("FALLÓ EN SECTOR");
			incompletos.add(error.getProperties().getProperty(
					"error.servicio.incomplete.sector"));
		}
		unionareas = (List<UnionAreaServicioInformacion>) readUnion(
				new UnionAreaServicioInformacion(), servicio,
				servicio.getId_servicio_informacion());
		if (unionareas.isEmpty()) {
			System.out.println("FALLÓ EN UNIÓN ÁREAS");
			incompletos.add(error.getProperties().getProperty(
					"error.servicio.incomplete.area"));
		}
		System.out.println("EL OBJETO SERVICIO (ID ESTADO) => "
				+ servicio.getId_estado());
		if (servicio.getId_estado() == DESARROLLO) {
			System.out.println("FALLÓ EN ESTADO");
			incompletos.add(error.getProperties().getProperty(
					"error.servicio.incomplete.estado"));
		}
		if (servicio.getId_seguridad() == 0) {
			System.out.println("FALLÓ EN SEGURIDAD");
			incompletos.add(error.getProperties().getProperty(
					"error.servicio.incomplete.seguridad"));
		}
		unionarquitecturas = (List<UnionArquitecturaServicioInformacion>) readUnion(
				new UnionArquitecturaServicioInformacion(), servicio,
				servicio.getId_servicio_informacion());
		if (unionarquitecturas.isEmpty()) {
			System.out.println("FALLÓ EN UNIÓN ARQUITECTURAS");
			incompletos.add(error.getProperties().getProperty(
					"error.servicio.incomplete.arquitectura"));
		}
		if (servicio.getId_intercambio() == 0) {
			System.out.println("FALLÓ EN INTERCAMBIO");
			incompletos.add(error.getProperties().getProperty(
					"error.servicio.incomplete.intercambio"));
		}
		Telefono phone = new Telefono();
		phone = (Telefono) getPhone(servicio,
				servicio.getId_servicio_informacion());
		if (phone == null) {
			System.out.println("FALLÓ EN TELÉFONO");
			incompletos.add(error.getProperties().getProperty(
					"error.servicio.incomplete.telefono"));
		}
		Correo email = new Correo();
		email = (Correo) getEmail(servicio,
				servicio.getId_servicio_informacion());
		if (email == null) {
			System.out.println("FALLÓ EN CORREO");
			incompletos.add(error.getProperties().getProperty(
					"error.servicio.incomplete.correo"));
		}
		Object[] models = { new Funcionalidad(), new ServicioInformacion() };
		List<Funcionalidad> funcionalidades = new ArrayList<Funcionalidad>();
		funcionalidades = (List<Funcionalidad>) read(models,
				servicio.getId_servicio_informacion(), -1);
		if (funcionalidades.isEmpty()) {
			System.out.println("FALLÓ EN FUNCIONALIDADES");
			incompletos.add(error.getProperties().getProperty(
					"error.servicio.incomplete.funcionalidades"));
		} else {
			Iterator<Funcionalidad> fxIterado = funcionalidades.iterator();
			Funcionalidad fx = new Funcionalidad();
			while (fxIterado.hasNext()) {
				fx = fxIterado.next();
				Object[] models2 = { new EntradaSalida(), new Funcionalidad() };
				List<EntradaSalida> salidas_tmp = new ArrayList<EntradaSalida>();
				salidas_tmp = (List<EntradaSalida>) read(models2,
						fx.getId_funcionalidad(), SALIDA);
				if (salidas_tmp.isEmpty()) {
					System.out.println("FALLÓ EN DATOS DE SALIDAS");
					incompletos.add(error.getProperties().getProperty(
							"error.servicio.incomplete.salidas"));
					break;
				}
			}
		}
		return incompletos;
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
	public List<ListaServiciosVisitados> SImasVisitados() {
		List<ListaServiciosVisitados> result = new ArrayList<ListaServiciosVisitados>();
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
							+ " AND EXISTS "
							+ "   (Select * from funcionalidades as f "
							+ "	   where Servicios_informacion.id_servicio_informacion = f.id_servicio_informacion "
							+ "	   AND f.status = 0 "
							+ "	   AND EXISTS "
							+ "         (Select * from entradas_salidas as io "
							+ "			WHERE io.id_funcionalidad = f.id_funcionalidad "
							+ "			AND io.status = 0 AND io.tipo = 1))"
							+ " GROUP BY visitas.id_servicio_informacion, Servicios_informacion.nombre "
							+ " ORDER BY count(visitas.id_servicio_informacion) desc "
							+ " limit " + LIMITE_VISITADOS);
			List list = query.list();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Object[] st = (Object[]) it.next();
				ListaServiciosVisitados si = new ListaServiciosVisitados();
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
						+ "AND EXISTS (Select * from funcionalidades as f "
						+ "				where Servicios_informacion.id_servicio_informacion = f.id_servicio_informacion "
						+ "				AND f.status = 0 "
						+ "				AND EXISTS (Select * from entradas_salidas as io "
						+ "				WHERE io.id_funcionalidad = f.id_funcionalidad "
						+ "				AND io.status = 0 AND io.tipo = 1))"
						+ "GROUP BY sectores.nombre, sectores.id_sector ORDER BY count(Servicios_informacion.id_sector) limit "
						+ n;
			} else {
				consulta = "select sectores.id_sector, sectores.nombre, "
						+ "( select count(servicios_informacion.id_sector) from servicios_informacion "
						+ "  where servicios_informacion.id_sector = sectores.id_sector "
						+ "  AND Servicios_informacion.status = 0 AND Servicios_informacion.id_estado = 2 "
						+ "  AND Servicios_informacion.publicado =TRUE"
						+ "  AND EXISTS (Select * from funcionalidades as f "
						+ "				where Servicios_informacion.id_servicio_informacion = f.id_servicio_informacion "
						+ "				AND f.status = 0 "
						+ "				AND EXISTS (Select * from entradas_salidas as io "
						+ "				WHERE io.id_funcionalidad = f.id_funcionalidad "
						+ "				AND io.status = 0 AND io.tipo = 1))"
						+ ") as S "
						+ "from  sectores,Servicios_informacion "
						+ "GROUP BY sectores.nombre, sectores.id_sector ORDER BY s Desc";
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
			list = (ArrayList<ServicioInformacion>) session
					.createQuery(
							" FROM ServicioInformacion s WHERE s.status = "
									+ ACTIVO
									+ " AND "
									+ " s.publicado = TRUE "
									+ " AND "
									+ " s.id_estado = 2 "
									+ " AND EXISTS "
									+ "   (FROM Funcionalidad as f "
									+ "	   where s.id_servicio_informacion = f.id_servicio_informacion "
									+ "	   AND f.status = 0 "
									+ "	   AND EXISTS "
									+ "         (FROM EntradaSalida as io "
									+ "			WHERE io.id_funcionalidad = f.id_funcionalidad "
									+ "			AND io.status = 0 AND io.tipo = 1))"
									+ " ORDER BY s.id_servicio_informacion "
									+ order).list();
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
							"FROM SolicitudSuscripcion WHERE "
									+ " id_ente_proveedor = " + id
									+ " AND status = " + ACTIVO
									+ " AND leido = false"
									+ " AND sentencia = " + PENDIENTE).list()
					.size();

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
	public long peticionesSuscripcionPendientes(long id) {
		long result;
		try {
			startConnection();
			result = session
					.createQuery(
							"FROM SolicitudSuscripcion WHERE "
									+ " id_ente_proveedor = " + id
									+ " AND status = " + ACTIVO
									+ " AND sentencia = " + PENDIENTE).list()
					.size();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return result;
	}

	@Override
	public ArrayList<SubscriptionRequest> getSolicitudesSuscripcionPendientes(
			long id_ente, byte orderBy) {
		List<SubscriptionRequest> result = new ArrayList<SubscriptionRequest>();
		ArrayList<?> list;
		Query query;
		String order = orderBy > 0 ? "DESC" : "ASC";
		try {
			startConnection();
			query = session
					.createSQLQuery("select s.id_solicitud_suscripcion,s.id_servicio_informacion,s.leido,s.fecha_creado,"
							+ " (select si.nombre from servicios_informacion as si where si.id_servicio_informacion = s.id_servicio_informacion and si.status=0) as servicio, "
							+ " (select e.siglas from entes as e where e.id_ente = s.id_ente_solicitante and e.status=0) as ente, "
							+ " s.sentencia"
							+ " from solicitudes_suscripciones as s"
							+ " where s.id_ente_proveedor = "
							+ id_ente
							+ " AND s.status = 0"
							+ " ORDER BY s.leido "
							+ order
							+ " , s.fecha_creado "
							+ order
							+ " , s.sentencia " + order);
			list = (ArrayList<?>) query.list();
			Iterator<?> it = list.iterator();
			while (it.hasNext()) {
				Object[] st = (Object[]) it.next();
				SubscriptionRequest s = new SubscriptionRequest();
				s.setId_suscripcion((Long) Long.parseLong(st[0].toString()));
				s.setId_servicio_informacion((Long) Long.parseLong(st[1]
						.toString()));
				s.setLeido((Boolean) Boolean.parseBoolean(st[2].toString()));
				s.setFecha_creado((Date) st[3]);
				s.setServicio((String) st[4].toString());
				s.setEnte((String) st[5].toString());
				s.setSentencia((int) Integer.parseInt(st[6].toString()));
				result.add(s);
			}
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return (ArrayList<SubscriptionRequest>) result;
	}

	@Override
	public long getNumeroSuscrionesAceptadasRechazadas(long id) {
		long result;
		try {
			startConnection();
			result = session
					.createQuery(
							"FROM SolicitudSuscripcion WHERE "
									+ " id_ente_solicitante = " + id
									+ " AND status = " + ACTIVO
									+ " AND leido = false"
									+ " AND sentencia != " + PENDIENTE).list()
					.size();

		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return result;
	}

	@Override
	public ArrayList<SubscriptionResponse> getlistaSolicitudesAceptadasRechazadas(
			long id_ente, byte orderBy) {
		List<SubscriptionResponse> result = new ArrayList<SubscriptionResponse>();
		ArrayList<?> list;
		Query query;
		String order = orderBy > 0 ? "DESC" : "ASC";
		try {
			startConnection();
			query = session
					.createSQLQuery("select s.id_solicitud_suscripcion,s.id_servicio_informacion,s.leido,s.fecha_creado,"
							+ " (select si.nombre from servicios_informacion as si where si.id_servicio_informacion = s.id_servicio_informacion and si.status=0) as servicio, "
							+ " (select e.siglas from entes as e where e.id_ente = s.id_ente_proveedor and e.status=0) as ente,"
							+ " s.sentencia "
							+ " from solicitudes_suscripciones as s"
							+ " where s.id_ente_solicitante = "
							+ id_ente
							+ " AND s.status = 0"
							+ " AND s.sentencia != "
							+ PENDIENTE
							+ " ORDER BY s.leido "
							+ order
							+ " , s.fecha_creado "
							+ order
							+ " , s.sentencia "
							+ order);
			list = (ArrayList<?>) query.list();
			Iterator<?> it = list.iterator();
			while (it.hasNext()) {
				Object[] st = (Object[]) it.next();
				SubscriptionResponse s = new SubscriptionResponse();
				s.setId_suscripcion((Long) Long.parseLong(st[0].toString()));
				s.setId_servicio_informacion((Long) Long.parseLong(st[1]
						.toString()));
				s.setLeido((Boolean) Boolean.parseBoolean(st[2].toString()));
				s.setFecha_creado((Date) st[3]);
				s.setServicio((String) st[4].toString());
				s.setEnte((String) st[5].toString());
				s.setSentencia((int) Integer.parseInt(st[6].toString()));
				result.add(s);
			}
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
		return (ArrayList<SubscriptionResponse>) result;
	}

	@Override
	public long getId_solicitud_sucripcion(long service, long provider,
			long client) {
		try {
			startConnection();
			try {
				return ((SolicitudSuscripcion) session.createQuery(
						"FROM " + SolicitudSuscripcion.class.getSimpleName()
								+ " WHERE id_servicio_informacion = " + service
								+ " AND id_ente_proveedor = " + provider
								+ " AND id_ente_solicitante = " + client
								+ " AND status = " + ACTIVO).uniqueResult())
						.getId_solicitud_suscripcion();
			} catch (Exception e) {
				return 0;
			}
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
	}

	@Override
	public boolean hasChildren(EntradaSalida es) {
		try {
			startConnection();
			try {
				return session
						.createQuery(
								"FROM " + EntradaSalida.class.getSimpleName()
										+ " WHERE id_padre = "
										+ es.getId_entrada_salida()
										+ " AND status = " + ACTIVO).list()
						.size() > 0;
			} catch (Exception e) {
				return false;
			}
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			closeConnection();
		}
	}
}
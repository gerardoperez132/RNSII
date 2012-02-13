package ve.gob.cnti.srsi.dao;

import java.util.Date;

import ve.gob.cnti.srsi.modelo.Estado;

/**
 * Controlador del Servicio de Información.
 * 
 * @author Richard Ricciardelli
 * 
 */
public class ServicioInformacionDAO extends DAO {
	// Prueba conceptual con modelo de Estado.
	private static Estado estado;

	public static void main(String args[]) {
		estado = new Estado();
		long id = getNextId("estados", "id_estado");
		Date fecha = new Date();
		/** Colocar el id correspondiente en base al algoritmo. */
		estado.setId_estado(id != 0 ? id : 1);
		estado.setNombre("En desarrollo");
		estado.setStatus(Status.ACTIVO);
		estado.setFecha_creado(fecha);
		estado.setFecha_modificado(fecha);
		createEstado();
	}

	private static void createEstado() {
		startConnection();
		getSession().save(estado);
		getTransaction().commit();
		closeConnection();
		// TODO Auto-generated method stub
	}
}
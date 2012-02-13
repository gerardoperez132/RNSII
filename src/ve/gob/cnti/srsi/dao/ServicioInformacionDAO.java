package ve.gob.cnti.srsi.dao;

import java.util.Date;

import ve.gob.cnti.srsi.modelo.Estado;
import ve.gob.cnti.srsi.modelo.Status;

/**
 * Controlador del Servicio de Información.
 * 
 * @author Richard Ricciardelli
 * 
 */
public class ServicioInformacionDAO extends DAO implements CRUD {
	// Prueba conceptual con modelo de Estado.
	private static Estado estado;

	public static void main(String args[]) {
		estado = new Estado();
		Date fecha = new Date();
		/** Colocar el id correspondiente en base al algoritmo. */
		estado.setId_estado(getNextId("estados", "id_estado"));
		estado.setNombre("En desarrollo");
		estado.setStatus(Status.ACTIVO);
		estado.setFecha_creado(fecha);
		estado.setFecha_modificado(fecha);
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
	}

	@Override
	public void read() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}
}
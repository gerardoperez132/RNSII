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

	public static void main(String args[]) {
		// Probando actualizaciones
		Date fecha = new Date();

		Estado estado = new Estado();
		estado.setId_estado(getNextId("estados", "id_estado"));
		estado.setNombre("Implementado");
		estado.setStatus(Status.ACTIVO);
		estado.setFecha_creado(fecha);
		estado.setFecha_modificado(fecha);
	}
}
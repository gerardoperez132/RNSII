package ve.gob.cnti.srsi.dao;

import java.util.Date;

import ve.gob.cnti.srsi.modelo.Sector;

/**
 * Controlador de los sectores a los que pertenecen los entes o los servicios de
 * información.
 * 
 * @author Richard Ricciardelli
 * 
 */
public class SectorDAO extends DAO {

	public static void main(String args[]) {
		Date fecha = new Date();
		String[] sectores = { "Ciencia y tecnología", "Salud", "Educación",
				"Transporte", "Comercio", "Finanzas,Banca y Seguros",
				"Energía y Minas", "Alimentación", "Agrícola", "Cultura",
				"Vivienda", "Ambiente y Turismo",
				"Defensa y Política Exterior", "Protección Social",
				"Legislativo, Contraloría y Auditoría", "Judicial",
				"Comunicación e Información" };
		Sector sector = new Sector();
		for (short i = 0; i < sectores.length; i++) {
			// sector.setId_sector(getNextId(sector));
			sector.setNombre(sectores[i]);
			sector.setStatus(Status.ACTIVO);
			sector.setFecha_creado(fecha);
			sector.setFecha_modificado(fecha);
			// saveSector(sector);
		}
	}
}
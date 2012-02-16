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
		// sector.setNombre("Crazy!");
		// sector.setId_sector(getNext(sector));
		// sector.setStatus(Status.ACTIVO);
		// sector.setFecha_creado(sector.getFecha_creado());
		// sector.setFecha_modificado(fecha);
		// System.out.println("SECTOR => " + sector.toString());

		// for (int i = 0; i < sector.getClass().getMethods().length; i++) {
		// System.out.println("MÉTODO DE SECTOR => "
		// + sector.getClass().getMethods()[i].toString());
		// }
		//
		//
		// for (int i = 0; i < estado.getClass().getMethods().length; i++) {
		// System.out.println("MÉTODO DE ESTADO => "
		// + estado.getClass().getMethods()[i].toString());
		// }

		// System.out.println("FIELD => " + getField(new Area()));
		// System.out.println("FIELD => " + getField(new Arquitectura()));
		// System.out.println("FIELD => " + getField(new AspectoLegal()));
		// System.out.println("FIELD => " + getField(new Correo()));
		// System.out.println("FIELD => " + getField(new Dato()));
		// System.out.println("FIELD => " + getField(new Ente()));
		// System.out.println("FIELD => " + getField(new EntradaSalida()));
		// System.out.println("FIELD => " + getField(new Estado()));
		// System.out.println("FIELD => " + getField(new Funcionalidad()));
		// System.out.println("FIELD => " + getField(new Intercambio()));
		// System.out.println("FIELD => " + getField(new Rol()));
		// System.out.println("FIELD => " + getField(new Sector()));
		// System.out.println("FIELD => " + getField(new Seguridad()));
		// System.out.println("FIELD => " + getField(new
		// ServicioInformacion()));
		// System.out.println("FIELD => " + getField(new Suscrito()));
		// System.out.println("FIELD => " + getField(new Telefono()));
		// System.out.println("FIELD => " + getField(new TipoDato()));
		// System.out.println("FIELD => "
		// + getField(new UnionAreaSistemaInformacion()));
		// System.out.println("FIELD => "
		// + getField(new UnionArquitecturaSistemaInformacion()));
		// System.out.println("FIELD => " + getField(new UnionDatoTipoDato()));
		// System.out.println("FIELD => " + getField(new UnionSectorEnte()));
		// System.out.println("FIELD => " + getField(new UnionUsuarioRol()));
		// System.out.println("FIELD => " + getField(new Url()));
		// System.out.println("FIELD => " + getField(new Usuario()));

		// getField(estado);
		// updateSomething(sector, 17);

		// for (short i = 0; i < sectores.length; i++) {
		// // sector.setId_sector(getNextId(sector));
		// sector.setNombre(sectores[i]);
		// sector.setStatus(Status.ACTIVO);
		// sector.setFecha_creado(fecha);
		// sector.setFecha_modificado(fecha);
		// // saveSector(sector);
		// }

		// ArrayList<Sector> listaSectores = (ArrayList<Sector>)
		// getSectores(sector);
		// Iterator iterator = listaSectores.iterator();
		// while (iterator.hasNext()) {
		// sector = (Sector) iterator.next();
		// System.out.println("SECTOR => " + sector.getNombre());
		// }

	}
}
package ve.gob.cnti.srsi.modelo;

import java.util.Date;

public class Suscrito {
	private int id;
	private int id_suscrito;
	private int id_ente; // Ente que está suscrito.
	private int id_servicio_informacion; // Servicio de información al cual está
											// suscrito.
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

}

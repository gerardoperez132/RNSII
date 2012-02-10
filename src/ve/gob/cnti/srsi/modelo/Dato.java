package ve.gob.cnti.srsi.modelo;

import java.util.Date;

public class Dato {
	private int id;
	private int dato;
	private int entrada_salida;
	private int id_padre; // Tendrá padre si y sólo si, es un dato compuesto.
	private String nombre;
	private String descripcion;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

}

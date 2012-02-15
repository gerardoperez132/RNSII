package ve.gob.cnti.srsi.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Modelo de tipo de intercambio de un servicio de información. Puede ser en
 * línea o fuera de línea de manera disyuntiva. De ser en línea se contempla
 * tipo de intercambio síncrono o asíncrono. De ser fuera de línea se contempla
 * el tipo de intercambio en lote.
 * 
 * @author Richard Ricciardelli
 * 
 */
@Entity
@Table(name = "intercambios")
public class Intercambio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_intercambio;
	// private long id_padre;
	private String nombre;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

}
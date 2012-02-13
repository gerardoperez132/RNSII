package ve.gob.cnti.srsi.modelo;

import java.util.Date;

/**
 * La clase dato guarda la relación del dato que tiene una entrada o salida y,
 * para conocer el tipo de dato de que se trata, obtiene la información de la
 * tabla TipoDato. Un dato puede tener un padre si el padre es de tipo
 * compuesto.
 * 
 * @see TipoDato
 * @see EntradaSalida
 * 
 * @author Richard Ricciardelli
 * 
 */
public class Dato {
	private long id;
	private long id_dato;
	private long id_entrada_salida;
	private long id_padre; // Tendrá padre si y sólo si, el padre es un dato
							// compuesto.
	private String nombre;
	private String descripcion;
	private String longitud; // ¿Qué se guarda aquí? Definir.
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_dato() {
		return id_dato;
	}

	public void setId_dato(long id_dato) {
		this.id_dato = id_dato;
	}

	public long getId_entrada_salida() {
		return id_entrada_salida;
	}

	public void setId_entrada_salida(long id_entrada_salida) {
		this.id_entrada_salida = id_entrada_salida;
	}

	public long getId_padre() {
		return id_padre;
	}

	public void setId_padre(long id_padre) {
		this.id_padre = id_padre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getFecha_creado() {
		return fecha_creado;
	}

	public void setFecha_creado(Date fecha_creado) {
		this.fecha_creado = fecha_creado;
	}

	public Date getFecha_modificado() {
		return fecha_modificado;
	}

	public void setFecha_modificado(Date fecha_modificado) {
		this.fecha_modificado = fecha_modificado;
	}

	@Override
	public String toString() {
		return "Dato [id=" + id + ", id_dato=" + id_dato
				+ ", id_entrada_salida=" + id_entrada_salida + ", id_padre="
				+ id_padre + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", longitud=" + longitud + ", status=" + status
				+ ", fecha_creado=" + fecha_creado + ", fecha_modificado="
				+ fecha_modificado + "]";
	}
}
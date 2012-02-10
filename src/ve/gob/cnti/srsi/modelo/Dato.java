package ve.gob.cnti.srsi.modelo;

import java.util.Date;

public class Dato {
	private int id;
	private int id_dato;
	private int id_entrada_salida;
	private int id_padre; // Tendrá padre si y sólo si, es un dato compuesto.
	private String nombre;
	private String descripcion;
	private String longitud; // ¿Qué se guarda aquí?
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_dato() {
		return id_dato;
	}

	public void setId_dato(int id_dato) {
		this.id_dato = id_dato;
	}

	public int getId_entrada_salida() {
		return id_entrada_salida;
	}

	public void setId_entrada_salida(int id_entrada_salida) {
		this.id_entrada_salida = id_entrada_salida;
	}

	public int getId_padre() {
		return id_padre;
	}

	public void setId_padre(int id_padre) {
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
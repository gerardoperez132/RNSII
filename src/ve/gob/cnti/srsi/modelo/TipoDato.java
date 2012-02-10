package ve.gob.cnti.srsi.modelo;

import java.util.Date;

public class TipoDato {

	private int id;
	private int id_tipo_dato;
	private int clase_dato; // Simple o compuesto
	private String nombre;
	private String descripcion;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_tipo_dato() {
		return id_tipo_dato;
	}

	public void setId_tipo_dato(int id_tipo_dato) {
		this.id_tipo_dato = id_tipo_dato;
	}

	public int getClase_dato() {
		return clase_dato;
	}

	public void setClase_dato(int clase_dato) {
		this.clase_dato = clase_dato;
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
		return "TipoDato [id=" + id + ", id_tipo_dato=" + id_tipo_dato
				+ ", clase_dato=" + clase_dato + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + ", status=" + status
				+ ", fecha_creado=" + fecha_creado + ", fecha_modificado="
				+ fecha_modificado + "]";
	}
}
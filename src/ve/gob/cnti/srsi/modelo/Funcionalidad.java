package ve.gob.cnti.srsi.modelo;

import java.util.Date;

/**
 * Una funcionalidad es igual a una operación en un servicio web. Un servicio de
 * información puede tener muchas funcionalidades.
 * 
 * @see ServicioInformacion
 * @author Richard Ricciardelli
 * 
 */
public class Funcionalidad {
	private long id;
	private long id_funcionalidad;
	private long id_servicio_informacion;
	private String nombre;
	private String descripcion;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public Funcionalidad() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_funcionalidad() {
		return id_funcionalidad;
	}

	public void setId_funcionalidad(long id_funcionalidad) {
		this.id_funcionalidad = id_funcionalidad;
	}

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
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
		return "Funcionalidad [id=" + id + ", id_funcionalidad="
				+ id_funcionalidad + ", id_servicio_informacion="
				+ id_servicio_informacion + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + ", status=" + status
				+ ", fecha_creado=" + fecha_creado + ", fecha_modificado="
				+ fecha_modificado + "]";
	}

}
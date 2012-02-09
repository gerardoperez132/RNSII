package ve.gob.cnti.srsi.modelo;

import java.util.Date;

public class Funcionalidad {
	private int id;
	private int funcionalidad;
	private int servicio_informacion;
	private String nombre;
	private String descripcion;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public Funcionalidad() {
	}

	public Funcionalidad(int id, int funcionalidad, int servicio_informacion,
			String nombre, String descripcion, int status, Date fecha_creado,
			Date fecha_modificado) {
		this.id = id;
		this.funcionalidad = funcionalidad;
		this.servicio_informacion = servicio_informacion;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.status = status;
		this.fecha_creado = fecha_creado;
		this.fecha_modificado = fecha_modificado;
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public int getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(int funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

	public int getServicio_informacion() {
		return servicio_informacion;
	}

	public void setServicio_informacion(int servicio_informacion) {
		this.servicio_informacion = servicio_informacion;
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

}
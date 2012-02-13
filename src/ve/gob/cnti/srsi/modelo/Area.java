package ve.gob.cnti.srsi.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Esta es la área que es igual a "orientado a" en el modelo.
 * 
 * @author Richard Ricciardelli
 * 
 */
@Entity
@Table(name = "areas")
public class Area {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_area;
	private String nombre;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_area() {
		return id_area;
	}

	public void setId_area(long id_area) {
		this.id_area = id_area;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		return "Area [id=" + id + ", id_area=" + id_area + ", nombre=" + nombre
				+ ", status=" + status + ", fecha_creado=" + fecha_creado
				+ ", fecha_modificado=" + fecha_modificado + "]";
	}

}
package ve.gob.cnti.srsi.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * El tipo de arquitectura del servicio. Puede ser Web Semántica, Servicio Web u
 * otro tipo que sería generalmente la combinación de ambas.
 * 
 * @author Richard Ricciardelli
 * 
 */
@Entity
@Table(name = "arquitecturas")
public class Arquitectura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_arquitectura;
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

	public long getId_arquitectura() {
		return id_arquitectura;
	}

	public void setId_arquitectura(long id_arquitectura) {
		this.id_arquitectura = id_arquitectura;
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
		return "Arquitectura [id=" + id + ", id_arquitectura="
				+ id_arquitectura + ", nombre=" + nombre + ", status=" + status
				+ ", fecha_creado=" + fecha_creado + ", fecha_modificado="
				+ fecha_modificado + "]";
	}
}
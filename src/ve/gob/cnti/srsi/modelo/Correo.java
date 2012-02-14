package ve.gob.cnti.srsi.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase modelo con los atributos de la tabla correos.
 * 
 * @author Joaquín Pereira
 * 
 */
@Entity
@Table(name = "correos")
public class Correo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_correo;
	private long id_padre;
	private long origen;
	private String correo;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public Correo() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_correo() {
		return id_correo;
	}

	public void setId_correo(long id_correo) {
		this.id_correo = id_correo;
	}

	public long getId_padre() {
		return id_padre;
	}

	public void setId_padre(long id_padre) {
		this.id_padre = id_padre;
	}

	public long getOrigen() {
		return origen;
	}

	public void setOrigen(long origen) {
		this.origen = origen;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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
		return "Correo [id=" + id + ", id_correo=" + id_correo + ", id_padre="
				+ id_padre + ", origen=" + origen + ", correo=" + correo
				+ ", status=" + status + ", fecha_creado=" + fecha_creado
				+ ", fecha_modificado=" + fecha_modificado + "]";
	}

}
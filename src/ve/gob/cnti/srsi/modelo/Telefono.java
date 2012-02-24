package ve.gob.cnti.srsi.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase modelo con los atributos de la tabla teléfonos.
 * 
 * @author Joaquín Pereira
 */
@Entity
@Table(name = "telefonos")
public class Telefono {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_telefono;
	private long id_ente;
	private long id_usuario;
	private long id_servicio_informacion;
	private String telefono;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public Telefono() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_telefono() {
		return id_telefono;
	}

	public void setId_telefono(long id_telefono) {
		this.id_telefono = id_telefono;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public long getId_ente() {
		return id_ente;
	}

	public void setId_ente(long id_ente) {
		this.id_ente = id_ente;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
	}

	@Override
	public String toString() {
		return "Telefono [id=" + id + ", id_telefono=" + id_telefono
				+ ", id_ente=" + id_ente + ", id_usuario=" + id_usuario
				+ ", id_servicio_informacion=" + id_servicio_informacion
				+ ", telefono=" + telefono + ", status=" + status
				+ ", fecha_creado=" + fecha_creado + ", fecha_modificado="
				+ fecha_modificado + "]";
	}
	
}
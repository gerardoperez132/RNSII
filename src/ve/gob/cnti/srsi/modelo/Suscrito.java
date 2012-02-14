package ve.gob.cnti.srsi.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase resultante de la relación de muchos a muchos entre entes y servicio de
 * información. Entes suscritos a servicios de información de otros entes.
 * 
 * @see Ente
 * @see ServicioInformacion
 * @author Richard Ricciardelli
 * 
 */
@Entity
@Table(name = "suscritos")
public class Suscrito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_suscrito;
	/** Ente que está suscrito. */
	private long id_ente;
	/** Servicio de información al cual está suscrito. */
	private long id_servicio_informacion;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public Suscrito() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_suscrito() {
		return id_suscrito;
	}

	public void setId_suscrito(long id_suscrito) {
		this.id_suscrito = id_suscrito;
	}

	public long getId_ente() {
		return id_ente;
	}

	public void setId_ente(long id_ente) {
		this.id_ente = id_ente;
	}

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
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
		return "Suscrito [id=" + id + ", id_suscrito=" + id_suscrito
				+ ", id_ente=" + id_ente + ", id_servicio_informacion="
				+ id_servicio_informacion + ", status=" + status
				+ ", fecha_creado=" + fecha_creado + ", fecha_modificado="
				+ fecha_modificado + "]";
	}
}
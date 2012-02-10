package ve.gob.cnti.srsi.modelo;

import java.util.Date;

public class Suscrito {
	private int id;
	private int id_suscrito;
	private int id_ente; // Ente que está suscrito.
	private int id_servicio_informacion; // Servicio de información al cual está
											// suscrito.
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_suscrito() {
		return id_suscrito;
	}

	public void setId_suscrito(int id_suscrito) {
		this.id_suscrito = id_suscrito;
	}

	public int getId_ente() {
		return id_ente;
	}

	public void setId_ente(int id_ente) {
		this.id_ente = id_ente;
	}

	public int getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public void setId_servicio_informacion(int id_servicio_informacion) {
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
package ve.gob.cnti.srsi.modelo;

import java.util.Date;

public class Union_usuario_rol {

	private int id;	
	private int id_sector;	
	private int id_ente;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;
	
	public Union_usuario_rol() {	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_sector() {
		return id_sector;
	}

	public void setId_sector(int id_sector) {
		this.id_sector = id_sector;
	}

	public int getId_ente() {
		return id_ente;
	}

	public void setId_ente(int id_ente) {
		this.id_ente = id_ente;
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
		return "Union_usuario_rol [id=" + id + ", id_sector=" + id_sector
				+ ", id_ente=" + id_ente + ", status=" + status
				+ ", fecha_creado=" + fecha_creado + ", fecha_modificado="
				+ fecha_modificado + "]";
	}	
	
}

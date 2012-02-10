package ve.gob.cnti.srsi.modelo;

import java.util.Date;

public class Correo {
	
	private int id;
	private int id_correo;
	private int id_padre;
	private int origen;
	private String correo;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;
	
	public Correo() {	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_correo() {
		return id_correo;
	}
	public void setId_correo(int id_correo) {
		this.id_correo = id_correo;
	}
	public int getId_padre() {
		return id_padre;
	}
	public void setId_padre(int id_padre) {
		this.id_padre = id_padre;
	}
	public int getOrigen() {
		return origen;
	}
	public void setOrigen(int origen) {
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

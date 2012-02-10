package ve.gob.cnti.srsi.modelo;

import java.util.Date;

public class Url {

	private int id;
	private int id_url;
	private int id_ente;	
	private String url;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;
	
	public Url() {	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public int getId_url() {
		return id_url;
	}
	public void setId_url(int id_url) {
		this.id_url = id_url;
	}
	public int getId_ente() {
		return id_ente;
	}
	public void setId_ente(int id_ente) {
		this.id_ente = id_ente;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
		return "Url [id=" + id + ", id_url=" + id_url + ", id_ente=" + id_ente
				+ ", url=" + url + ", status=" + status + ", fecha_creado="
				+ fecha_creado + ", fecha_modificado=" + fecha_modificado + "]";
	}
	
}

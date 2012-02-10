package ve.gob.cnti.srsi.modelo;

import java.util.Date;

public class Ente {
	
	private int id;
	private int id_ente;
	private int id_padre;
	private String nombre;
	private String rif;
	private String direccion;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;
		
	public Ente() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_ente() {
		return id_ente;
	}
	public void setId_ente(int id_ente) {
		this.id_ente = id_ente;
	}
	public int getId_padre() {
		return id_padre;
	}
	public void setId_padre(int id_padre) {
		this.id_padre = id_padre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRif() {
		return rif;
	}
	public void setRif(String rif) {
		this.rif = rif;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
		return "Ente [id=" + id + ", id_ente=" + id_ente + ", id_padre="
				+ id_padre + ", nombre=" + nombre + ", rif=" + rif
				+ ", direccion=" + direccion + ", status=" + status
				+ ", fecha_creado=" + fecha_creado + ", fecha_modificado="
				+ fecha_modificado + "]";
	}	
	
}
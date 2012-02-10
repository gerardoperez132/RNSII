package ve.gob.cnti.srsi.modelo;

import java.util.Date;

public class Usuario {

	private int id;	
	private int id_usuario;
	private int id_ente;
	private String nombre;
	private String apellido;
	private String cedula;
	private String clave;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;
	
	public Usuario() {	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getEnte() {
		return id_ente;
	}

	public void setEnte(int id_ente) {
		this.id_ente = id_ente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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
		return "usuario [id=" + id + ", id_usuario=" + id_usuario + ", ente="
				+ id_ente + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", cedula=" + cedula + ", clave=" + clave + ", status="
				+ status + ", fecha_creado=" + fecha_creado
				+ ", fecha_modificado=" + fecha_modificado + "]";
	}
		
}

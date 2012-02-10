package ve.gob.cnti.srsi.modelo;

import java.util.Date;

public class Servicio_Informacion {
	
	private int id;
	private int id_servicio_informacion;
	private int id_ente;	
	private int id_usuario;
	private String nombre;	
	private String version;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;
	private int id_estado;
	private int id_aspectos_legales;
	private int id_nivel_seguridad;
	private int id_tipo_intercambio;
	
	public Servicio_Informacion() {	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public void setId_servicio_informacion(int id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
	}

	public int getId_ente() {
		return id_ente;
	}

	public void setId_ente(int id_ente) {
		this.id_ente = id_ente;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	public int getId_estado() {
		return id_estado;
	}

	public void setId_estado(int id_estado) {
		this.id_estado = id_estado;
	}

	public int getId_aspectos_legales() {
		return id_aspectos_legales;
	}

	public void setId_aspectos_legales(int id_aspectos_legales) {
		this.id_aspectos_legales = id_aspectos_legales;
	}

	public int getId_nivel_seguridad() {
		return id_nivel_seguridad;
	}

	public void setId_nivel_seguridad(int id_nivel_seguridad) {
		this.id_nivel_seguridad = id_nivel_seguridad;
	}

	public int getId_tipo_intercambio() {
		return id_tipo_intercambio;
	}

	public void setId_tipo_intercambio(int id_tipo_intercambio) {
		this.id_tipo_intercambio = id_tipo_intercambio;
	}
	
}

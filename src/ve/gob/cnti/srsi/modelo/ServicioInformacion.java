package ve.gob.cnti.srsi.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase modelo con los atributos de los servicios de información del estado
 * Venezolano.
 * 
 * @author Joaquín Pereira
 * @see Ente
 * @see Usuario
 * @see Estado
 * @see AspectoLegal
 * @see Seguridad
 */
@Entity
@Table(name = "servicios_informacion")
public class ServicioInformacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_servicio_informacion;
	private long id_ente;
	private long id_usuario;
	private String nombre;
	private String version;
	private boolean publicado;
	private long id_estado;
	private long id_aspectos_legales;
	private long id_seguridad;
	private long id_tipo_intercambio;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public ServicioInformacion() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
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

	public boolean isPublicado() {
		return publicado;
	}

	public void setPublicado(boolean publicado) {
		this.publicado = publicado;
	}

	public long getId_estado() {
		return id_estado;
	}

	public void setId_estado(long id_estado) {
		this.id_estado = id_estado;
	}

	public long getId_aspectos_legales() {
		return id_aspectos_legales;
	}

	public void setId_aspectos_legales(long id_aspectos_legales) {
		this.id_aspectos_legales = id_aspectos_legales;
	}

	public long getId_seguridad() {
		return id_seguridad;
	}

	public void setId_seguridad(long id_seguridad) {
		this.id_seguridad = id_seguridad;
	}

	public long getId_tipo_intercambio() {
		return id_tipo_intercambio;
	}

	public void setId_tipo_intercambio(long id_tipo_intercambio) {
		this.id_tipo_intercambio = id_tipo_intercambio;
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
		return "ServicioInformacion [id=" + id + ", id_servicio_informacion="
				+ id_servicio_informacion + ", id_ente=" + id_ente
				+ ", id_usuario=" + id_usuario + ", nombre=" + nombre
				+ ", version=" + version + ", publicado=" + publicado
				+ ", id_estado=" + id_estado + ", id_aspectos_legales="
				+ id_aspectos_legales + ", id_nivel_seguridad=" + id_seguridad
				+ ", id_tipo_intercambio=" + id_tipo_intercambio + ", status="
				+ status + ", fecha_creado=" + fecha_creado
				+ ", fecha_modificado=" + fecha_modificado + "]";
	}
}
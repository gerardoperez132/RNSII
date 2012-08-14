package ve.gob.cnti.srsi.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ve.gob.cnti.srsi.dao.Constants;

/**
 * Clase que maneja las solicitudes de suscripción a un servicio de información.
 * 
 * @see ServicioInformacion
 * @see Usuario
 * @see Ente
 * @author Joaquín Pereira
 * 
 */
@Entity
@Table(name = "solicitudes_suscripciones")
public class SolicitudSuscripcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_solicitud_suscripcion;
	private long id_ente_solicitante;
	private long id_ente_proveedor;
	private long id_servicio_informacion;
	private long id_usuario;
	private boolean leido;
	private int sentencia;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;
	private String solicitante;
	private String cargo;
	private String telefono;
	private String correo;
	@Column(length = Constants.MOTIVE_LENGTH)
	private String motivo_solicitante;
	@Column(length = Constants.MOTIVE_LENGTH)
	private String motivo_proveedor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_solicitud_suscripcion() {
		return id_solicitud_suscripcion;
	}

	public void setId_solicitud_suscripcion(long id_solicitud_suscripcion) {
		this.id_solicitud_suscripcion = id_solicitud_suscripcion;
	}

	public long getId_ente_solicitante() {
		return id_ente_solicitante;
	}

	public void setId_ente_solicitante(long id_ente_solicitante) {
		this.id_ente_solicitante = id_ente_solicitante;
	}

	public long getId_ente_proveedor() {
		return id_ente_proveedor;
	}

	public void setId_ente_proveedor(long id_ente_proveedor) {
		this.id_ente_proveedor = id_ente_proveedor;
	}

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	public int getSentencia() {
		return sentencia;
	}

	public void setSentencia(int sentencia) {
		this.sentencia = sentencia;
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

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getMotivo_solicitante() {
		return motivo_solicitante;
	}

	public void setMotivo_solicitante(String motivo_solicitante) {
		this.motivo_solicitante = motivo_solicitante;
	}

	public String getMotivo_proveedor() {
		return motivo_proveedor;
	}

	public void setMotivo_proveedor(String motivo_proveedor) {
		this.motivo_proveedor = motivo_proveedor;
	}

	@Override
	public String toString() {
		return "SolicitudSuscripcion [id=" + id + ", id_solicitud_suscripcion="
				+ id_solicitud_suscripcion + ", id_ente_solicitante="
				+ id_ente_solicitante + ", id_ente_proveedor="
				+ id_ente_proveedor + ", id_servicio_informacion="
				+ id_servicio_informacion + ", id_usuario=" + id_usuario
				+ ", leido=" + leido + ", sentencia=" + sentencia + ", status="
				+ status + ", fecha_creado=" + fecha_creado
				+ ", fecha_modificado=" + fecha_modificado + ", solicitante="
				+ solicitante + ", cargo=" + cargo + ", telefono=" + telefono
				+ ", correo=" + correo + ", motivo_solicitante="
				+ motivo_solicitante + ", motivo_proveedor=" + motivo_proveedor
				+ "]";
	}
}
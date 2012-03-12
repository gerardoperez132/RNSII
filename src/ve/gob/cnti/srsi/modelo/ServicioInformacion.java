/* This file is part of SRSI.
 * 
 * SRSI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * SRSI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SRSI. If not, see <http://www.gnu.org/licenses/>.
 */
package ve.gob.cnti.srsi.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase modelo con los atributos de los servicios de información del Estado
 * Venezolano.
 * 
 * @author Joaquín Pereira
 * @see Ente
 * @see Estado
 * @see AspectoLegal
 * @see Seguridad
 * @see Correo
 * @see Telefono
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
	@Column(unique = true)
	private String nombre;
	private String descripcion;
	private String version;
	private String responsable;
	private boolean publicado;
	private long id_sector;
	private long id_estado;
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

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public long getId_ente() {
		return id_ente;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getVersion() {
		return version;
	}

	public String getResponsable() {
		return responsable;
	}

	public boolean isPublicado() {
		return publicado;
	}

	public long getId_sector() {
		return id_sector;
	}

	public long getId_estado() {
		return id_estado;
	}

	public long getId_seguridad() {
		return id_seguridad;
	}

	public long getId_tipo_intercambio() {
		return id_tipo_intercambio;
	}

	public int getStatus() {
		return status;
	}

	public Date getFecha_creado() {
		return fecha_creado;
	}

	public Date getFecha_modificado() {
		return fecha_modificado;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
	}

	public void setId_ente(long id_ente) {
		this.id_ente = id_ente;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public void setPublicado(boolean publicado) {
		this.publicado = publicado;
	}

	public void setId_sector(long id_sector) {
		this.id_sector = id_sector;
	}

	public void setId_estado(long id_estado) {
		this.id_estado = id_estado;
	}

	public void setId_seguridad(long id_seguridad) {
		this.id_seguridad = id_seguridad;
	}

	public void setId_tipo_intercambio(long id_tipo_intercambio) {
		this.id_tipo_intercambio = id_tipo_intercambio;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setFecha_creado(Date fecha_creado) {
		this.fecha_creado = fecha_creado;
	}

	public void setFecha_modificado(Date fecha_modificado) {
		this.fecha_modificado = fecha_modificado;
	}

	@Override
	public String toString() {
		return "ServicioInformacion [id=" + id + ", id_servicio_informacion="
				+ id_servicio_informacion + ", id_ente=" + id_ente
				+ ", id_usuario=" + id_usuario + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + ", version=" + version
				+ ", responsable=" + responsable + ", publicado=" + publicado
				+ ", id_sector=" + id_sector + ", id_estado=" + id_estado
				+ ", id_seguridad=" + id_seguridad + ", id_tipo_intercambio="
				+ id_tipo_intercambio + ", status=" + status
				+ ", fecha_creado=" + fecha_creado + ", fecha_modificado="
				+ fecha_modificado + "]";
	}
}
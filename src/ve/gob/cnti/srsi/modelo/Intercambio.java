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

import ve.gob.cnti.srsi.dao.Constants;

/**
 * Modelo de tipo de intercambio de un servicio de información. Puede ser en
 * línea o fuera de línea de manera disyuntiva. De ser en línea se contempla
 * tipo de intercambio síncrono o asíncrono. De ser fuera de línea se contempla
 * el tipo de intercambio en lote.
 * 
 * @author Richard Ricciardelli
 * @see ServicioInformacion
 * 
 */
@Entity
@Table(name = "intercambios")
public class Intercambio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_intercambio;
	private long id_padre;
	@Column(length = Constants.TITLE_LENGTH)
	private String nombre;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;
	private long mod_user;

	public Intercambio() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_intercambio() {
		return id_intercambio;
	}

	public void setId_intercambio(long id_intercambio) {
		this.id_intercambio = id_intercambio;
	}

	public long getId_padre() {
		return id_padre;
	}

	public void setId_padre(long id_padre) {
		this.id_padre = id_padre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public long getMod_user() {
		return mod_user;
	}

	public void setMod_user(long mod_user) {
		this.mod_user = mod_user;
	}

	@Override
	public String toString() {
		return "Intercambio [id=" + id + ", id_intercambio=" + id_intercambio
				+ ", id_padre=" + id_padre + ", nombre=" + nombre + ", status="
				+ status + ", fecha_creado=" + fecha_creado
				+ ", fecha_modificado=" + fecha_modificado + ", mod_user="
				+ mod_user + "]";
	}

}

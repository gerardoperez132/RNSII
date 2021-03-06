/* This file is part of RNSII.
 * 
 * RNSII is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * RNSII is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with RNSII. If not, see <http://www.gnu.org/licenses/>.
 */
package ve.gob.cnti.rnsii.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ve.gob.cnti.rnsii.dao.Constants;

/**
 * Esta clase contendrá los tipos de estados que tiene un servicio de
 * información, hasta el momento son en desarrollo e implementado.
 * 
 * @author Richard Ricciardelli
 */
@Entity
@Table(name = "estados")
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_estado;
	@Column(length = Constants.TITLE_LENGTH)
	private String nombre;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;
	private long mod_user;

	public Estado() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_estado() {
		return id_estado;
	}

	public void setId_estado(long id_estado) {
		this.id_estado = id_estado;
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
		return "Estado [id=" + id + ", id_estado=" + id_estado + ", nombre="
				+ nombre + ", status=" + status + ", fecha_creado="
				+ fecha_creado + ", fecha_modificado=" + fecha_modificado
				+ ", mod_user=" + mod_user + "]";
	}

}

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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase modelo que tiene la relación muchos a muchos entre las tablas sectores
 * y entes.
 * 
 * @author Joaquín Pereira
 * @see Sector
 * @see Ente
 * @see Usuario
 * 
 */
@Entity
@Table(name = "union_sectores_entes")
public class UnionSectorEnte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_sector;
	private long id_ente;
	private long id_usuario;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;
	private long mod_user;

	public UnionSectorEnte() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_sector() {
		return id_sector;
	}

	public void setId_sector(long id_sector) {
		this.id_sector = id_sector;
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
		return "UnionSectorEnte [id=" + id + ", id_sector=" + id_sector
				+ ", id_ente=" + id_ente + ", id_usuario=" + id_usuario
				+ ", status=" + status + ", fecha_creado=" + fecha_creado
				+ ", fecha_modificado=" + fecha_modificado + ", mod_user="
				+ mod_user + "]";
	}

}

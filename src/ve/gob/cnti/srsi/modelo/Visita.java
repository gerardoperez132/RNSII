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
 * Visitas únicas diarias que recibe un servicio de información particular.
 * 
 * @author Joaquín Pereira
 * @author Richard Ricciardelli
 * @see ServicioInformacion
 */
@Entity
@Table(name = "visitas")
public class Visita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_visita;
	private long id_servicio_informacion;
	private String ip;
	private Date fecha;
	private long mod_user;

	public Visita() {
	}

	public long getId_visita() {
		return id_visita;
	}

	public void setId_visita(long id_visita) {
		this.id_visita = id_visita;
	}

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public long getMod_user() {
		return mod_user;
	}

	public void setMod_user(long mod_user) {
		this.mod_user = mod_user;
	}

	@Override
	public String toString() {
		return "Visita [id_visita=" + id_visita + ", id_servicio_informacion="
				+ id_servicio_informacion + ", ip=" + ip + ", fecha=" + fecha
				+ ", mod_user=" + mod_user + "]";
	}

}

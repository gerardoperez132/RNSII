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
 * Aspectos legales del servicio de información.
 * 
 * @author Joaquín Pereira
 * 
 */
@Entity
@Table(name = "visitas")
public class Visita {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_visita;
	private long id_servicio_informacion;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;
	
	public Visita(){}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
		return "Visita [id=" + id + ", id_visita=" + id_visita
				+ ", id_servicio_informacion=" + id_servicio_informacion
				+ ", status=" + status + ", fecha_creado=" + fecha_creado
				+ ", fecha_modificado=" + fecha_modificado + "]";
	}

}

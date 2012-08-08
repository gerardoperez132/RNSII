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
package ve.gob.cnti.srsi.util;

import java.util.Date;

/**
 * Clase temporal creada para llevar los datos que son necesarios a la petición
 * de listar las respuestas solicitudes de suscripción a servicios de
 * información de otros entes, evitando llevar datos de más que se encuentran en
 * el modelo.
 * 
 * @author Joaquín Pereira
 */
public class SolicitudRespuesta {

	private long id_suscripcion;
	private long id_servicio_informacion;
	private boolean leido;
	private Date fecha_creado;
	private String servicio;
	private String ente;
	private int sentencia;

	public long getId_suscripcion() {
		return id_suscripcion;
	}

	public void setId_suscripcion(long id_suscripcion) {
		this.id_suscripcion = id_suscripcion;
	}

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	public Date getFecha_creado() {
		return fecha_creado;
	}

	public void setFecha_creado(Date fecha_creado) {
		this.fecha_creado = fecha_creado;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getEnte() {
		return ente;
	}

	public void setEnte(String ente) {
		this.ente = ente;
	}

	public int getSentencia() {
		return sentencia;
	}

	public void setSentencia(int sentencia) {
		this.sentencia = sentencia;
	}

	@Override
	public String toString() {
		return "Solicitud_Respuesta [id_suscripcion=" + id_suscripcion
				+ ", id_servicio_informacion=" + id_servicio_informacion
				+ ", leido=" + leido + ", fecha_creado=" + fecha_creado
				+ ", servicio=" + servicio + ", ente=" + ente + ", sentencia="
				+ sentencia + "]";
	}
}

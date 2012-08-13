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

import java.util.List;

import ve.gob.cnti.srsi.modelo.ServicioInformacion;

/**
 * Esta clase contiene servicios de información que pueden ser publicados.
 * 
 * @author Joaquín Pereira
 * 
 */
public class ServiciosPublicables {
	private List<String> incompletos;
	boolean publicable;
	ServicioInformacion servicio = new ServicioInformacion();

	public ServiciosPublicables(boolean publicable,
			ServicioInformacion servicio, List<String> incompletos) {
		super();
		this.publicable = publicable;
		this.servicio = servicio;
		this.incompletos = incompletos;
	}

	public boolean isPublicable() {
		return publicable;
	}

	public void setPublicable(boolean publicable) {
		this.publicable = publicable;
	}

	public ServicioInformacion getServicio() {
		return servicio;
	}

	public void setServicio(ServicioInformacion servicio) {
		this.servicio = servicio;
	}

	public List<String> getIncompletos() {
		return incompletos;
	}

	public void setIncompletos(List<String> incompletos) {
		this.incompletos = incompletos;
	}
}
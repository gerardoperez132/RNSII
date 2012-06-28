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

/**
 * Modelo para listar los servicios de información más visitados.
 * 
 * @author Joaquín Pereira
 * 
 */
public class ListaSImasVisitados {

	private long id_servicio_informacion;
	private String nombre;
	private long visitas;

	public ListaSImasVisitados() {
	}

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getVisitas() {
		return visitas;
	}

	public void setVisitas(long visitas) {
		this.visitas = visitas;
	}

	@Override
	public String toString() {
		return "ListaSImasVisitados [id_servicio_informacion="
				+ id_servicio_informacion + ", nombre=" + nombre + ", visitas="
				+ visitas + "]";
	}
}
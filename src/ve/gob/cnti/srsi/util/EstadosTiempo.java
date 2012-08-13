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
 * Esta clase sirve durante el <i>parser</i> del archivo XML con los datos de
 * temperatura del INAMEH.
 * 
 * @author Joaquín Pereira
 * 
 */
public class EstadosTiempo {

	private int codigo;
	private String nombre;
	private int t_max;
	private int t_min;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getT_max() {
		return t_max;
	}

	public void setT_max(int t_max) {
		this.t_max = t_max;
	}

	public int getT_min() {
		return t_min;
	}

	public void setT_min(int t_min) {
		this.t_min = t_min;
	}

	@Override
	public String toString() {
		return "Estados_Tiempo [codigo=" + codigo + ", nombre=" + nombre
				+ ", t_max=" + t_max + ", t_min=" + t_min + "]";
	}
}

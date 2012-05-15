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
package ve.gob.cnti.modelo.temporales;

/**
 * Clase que sirve para encapsular los sectores que poseen 
 * mayor numero de servicios de información implementados.
 * 
 * @author Joaquín Pereira
 * 
 */
public class SectoresMasPublicados {	
	private long id_sector;
	private String nombre;
	private long n;
		
	public SectoresMasPublicados() {		
	}
	
	public long getId_sector() {
		return id_sector;
	}
	public void setId_sector(long id_sector) {
		this.id_sector = id_sector;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
	public long getN() {
		return n;
	}
	public void setN(long n) {
		this.n = n;
	}
}

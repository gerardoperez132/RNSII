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
 * Tipo de dato que se está utilizando. Entre ellos están los datos tipo
 * {@link String}, {@link Integer}, Compuestos, entre otros.
 * 
 * @author Richard Ricciardelli
 * 
 */
@Entity
@Table(name = "tipos_datos")
public class TipoDato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_tipo_dato;
	/** Simple (1) o compuesto (0). */
	private int tipo;
	@Column(length = Constants.TITLE_LENGTH)
	private String nombre;
	@Column(length = Constants.DESCRIPTION_LENGTH)
	private String descripcion;
	private boolean hasLength;
	private boolean hasformatted;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_tipo_dato() {
		return id_tipo_dato;
	}

	public void setId_tipo_dato(long id_tipo_dato) {
		this.id_tipo_dato = id_tipo_dato;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public boolean isHasLength() {
		return hasLength;
	}

	public void setHasLength(boolean hasLength) {
		this.hasLength = hasLength;
	}

	public boolean isHasformatted() {
		return hasformatted;
	}

	public void setHasformatted(boolean hasformatted) {
		this.hasformatted = hasformatted;
	}

	@Override
	public String toString() {
		return "TipoDato [id=" + id + ", id_tipo_dato=" + id_tipo_dato
				+ ", tipo=" + tipo + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", hasLength=" + hasLength + ", hasformatted="
				+ hasformatted + ", status=" + status + ", fecha_creado="
				+ fecha_creado + ", fecha_modificado=" + fecha_modificado + "]";
	}

}
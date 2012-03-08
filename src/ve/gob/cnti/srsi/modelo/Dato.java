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
 * La clase dato guarda la relación del dato que tiene una entrada o salida y,
 * para conocer el tipo de dato de que se trata, obtiene la información de la
 * tabla TipoDato. Un dato puede tener un padre si el padre es de tipo
 * compuesto.
 * 
 * @see TipoDato
 * @see EntradaSalida
 * 
 * @author Richard Ricciardelli
 * 
 */
@Entity
@Table(name = "datos")
public class Dato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_dato;
	private long id_entrada_salida;
	private long id_tipo_dato;
	/** Tendrá padre solamente si éste es de tipo compuesto */
	private long id_padre;
	private String nombre;
	private String descripcion;
	private String longitud; // ¿Qué se guarda aquí? Definir.
	private String formato; // ¿Qué demonios es el formato? Definir.
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_dato() {
		return id_dato;
	}

	public void setId_dato(long id_dato) {
		this.id_dato = id_dato;
	}

	public long getId_entrada_salida() {
		return id_entrada_salida;
	}

	public void setId_entrada_salida(long id_entrada_salida) {
		this.id_entrada_salida = id_entrada_salida;
	}

	public long getId_padre() {
		return id_padre;
	}

	public void setId_padre(long id_padre) {
		this.id_padre = id_padre;
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

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
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

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public long getId_tipo_dato() {
		return id_tipo_dato;
	}

	public void setId_tipo_dato(long id_tipo_dato) {
		this.id_tipo_dato = id_tipo_dato;
	}

	@Override
	public String toString() {
		return "Dato [id=" + id + ", id_dato=" + id_dato
				+ ", id_entrada_salida=" + id_entrada_salida
				+ ", id_tipo_dato=" + id_tipo_dato + ", id_padre=" + id_padre
				+ ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", longitud=" + longitud + ", formato=" + formato
				+ ", status=" + status + ", fecha_creado=" + fecha_creado
				+ ", fecha_modificado=" + fecha_modificado + "]";
	}
}
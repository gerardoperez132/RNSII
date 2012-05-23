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
 * Esta clase indica las diferentes entradas y salidas que tiene una
 * Funcionalidad.
 * 
 * @see Funcionalidad
 * @see TipoDato
 * @see Usuario
 * @author Richard Ricciardelli
 * 
 */
@Entity
@Table(name = "entradas_salidas")
public class EntradaSalida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_entrada_salida;
	private long id_funcionalidad;
	private long id_usuario;
	private long id_tipo_dato;
	private long id_padre;
	private String nombre;
	private String descripcion;
	private long id_formato;
	private String longitud;
	/** Entrada o Salida. */
	private int tipo;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public EntradaSalida() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_entrada_salida() {
		return id_entrada_salida;
	}

	public void setId_entrada_salida(long id_entrada_salida) {
		this.id_entrada_salida = id_entrada_salida;
	}

	public long getId_funcionalidad() {
		return id_funcionalidad;
	}

	public void setId_funcionalidad(long id_funcionalidad) {
		this.id_funcionalidad = id_funcionalidad;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public long getId_tipo_dato() {
		return id_tipo_dato;
	}

	public void setId_tipo_dato(long id_tipo_dato) {
		this.id_tipo_dato = id_tipo_dato;
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

	public long getId_formato() {
		return id_formato;
	}

	public void setId_formato(long id_formato) {
		this.id_formato = id_formato;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
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
		return "EntradaSalida [id=" + id + ", id_entrada_salida="
				+ id_entrada_salida + ", id_funcionalidad=" + id_funcionalidad
				+ ", id_usuario=" + id_usuario + ", id_tipo_dato="
				+ id_tipo_dato + ", id_padre=" + id_padre + ", nombre="
				+ nombre + ", descripcion=" + descripcion + ", id_formato="
				+ id_formato + ", longitud=" + longitud + ", tipo=" + tipo
				+ ", status=" + status + ", fecha_creado=" + fecha_creado
				+ ", fecha_modificado=" + fecha_modificado + "]";
	}
}
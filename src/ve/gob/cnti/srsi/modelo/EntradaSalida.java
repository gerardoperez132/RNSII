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
 * @see Dato
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
	private long id_funcionalidad; // FK
	private long id_dato;
	private int tipo; // Tipo Entrada o Salida (0 ó 1)
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

	public long getId_dato() {
		return id_dato;
	}

	public void setId_dato(long id_dato) {
		this.id_dato = id_dato;
	}

	@Override
	public String toString() {
		return "EntradaSalida [id=" + id + ", id_entrada_salida="
				+ id_entrada_salida + ", id_funcionalidad=" + id_funcionalidad
				+ ", id_dato=" + id_dato + ", tipo=" + tipo + ", status="
				+ status + ", fecha_creado=" + fecha_creado
				+ ", fecha_modificado=" + fecha_modificado + "]";
	}
}
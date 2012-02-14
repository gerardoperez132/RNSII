package ve.gob.cnti.srsi.modelo;

import java.util.Date;

/**
 * Clase resultante de la relación de muchos a muchos entre dato y tipo de dato.
 * 
 * @author Richard Ricciardelli
 * @see Dato
 * @see TipoDato
 * 
 */
public class UnionDatoTipoDato {

	private long id;
	private long id_dato;
	private long id_tipo_dato;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public UnionDatoTipoDato() {
	}

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

	public long getId_tipo_dato() {
		return id_tipo_dato;
	}

	public void setId_tipo_dato(long id_tipo_dato) {
		this.id_tipo_dato = id_tipo_dato;
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
		return "UnionDatoTipoDato [id=" + id + ", id_dato=" + id_dato
				+ ", id_tipo_dato=" + id_tipo_dato + ", status=" + status
				+ ", fecha_creado=" + fecha_creado + ", fecha_modificado="
				+ fecha_modificado + "]";
	}
}
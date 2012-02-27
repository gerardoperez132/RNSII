package ve.gob.cnti.srsi.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Aspectos legales del servicio de información.
 * 
 * @author Richard Ricciardelli
 * 
 */
@Entity
@Table(name = "aspectos_legales")
public class AspectoLegal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_aspecto_legal;
	private long id_servicio_informacion;
	/** Tipo de documento => Legal (0) o SLA (1) */
	private int tipo;
	private String nombre;
	private String url;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public AspectoLegal() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_aspecto_legal() {
		return id_aspecto_legal;
	}

	public void setId_aspecto_legal(long id_aspecto_legal) {
		this.id_aspecto_legal = id_aspecto_legal;
	}

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		return "AspectoLegal [id=" + id + ", id_aspecto_legal="
				+ id_aspecto_legal + ", id_servicio_informacion="
				+ id_servicio_informacion + ", tipo=" + tipo + ", nombre="
				+ nombre + ", url=" + url + ", status=" + status
				+ ", fecha_creado=" + fecha_creado + ", fecha_modificado="
				+ fecha_modificado + "]";
	}
}
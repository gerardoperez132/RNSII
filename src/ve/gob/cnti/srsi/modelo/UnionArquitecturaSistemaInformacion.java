package ve.gob.cnti.srsi.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase resultante de la relación de muchos a muchos entre Arquitectura y
 * Servicios de Información.
 * 
 * @author Richard Ricciardelli
 * @see Arquitectura
 * @see SistemaInformacion
 * 
 */
@Entity
@Table(name = "union_arquitectura_sistema_informacion")
public class UnionArquitecturaSistemaInformacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_servicio_informacion;
	private long id_arquitectura;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public UnionArquitecturaSistemaInformacion() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
	}

	public long getId_arquitectura() {
		return id_arquitectura;
	}

	public void setId_arquitectura(long id_arquitectura) {
		this.id_arquitectura = id_arquitectura;
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
		return "UnionArquitecturaSistemaInformacion [id=" + id
				+ ", id_servicio_informacion=" + id_servicio_informacion
				+ ", id_arquitectura=" + id_arquitectura + ", status=" + status
				+ ", fecha_creado=" + fecha_creado + ", fecha_modificado="
				+ fecha_modificado + "]";
	}

}

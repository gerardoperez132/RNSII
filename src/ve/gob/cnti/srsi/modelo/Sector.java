package ve.gob.cnti.srsi.modelo;

/****
 *
 * @author joaquin
 * 
 * Clase modelo con los atributos de la tabla sectores
 *
 */

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sectores")
public class Sector {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;	
	private int id_sector;	
	private String nombre;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;
	
	public Sector() {	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_sector() {
		return id_sector;
	}

	public void setId_sector(int id_sector) {
		this.id_sector = id_sector;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		return "Sector [id=" + id + ", id_sector=" + id_sector + ", nombre="
				+ nombre + ", status=" + status + ", fecha_creado="
				+ fecha_creado + ", fecha_modificado=" + fecha_modificado + "]";
	}
	
	
}

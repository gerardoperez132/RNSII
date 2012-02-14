package ve.gob.cnti.srsi.modelo;

/****
 *
 * @author joaquin
 * 
 * Clase modelo con los atributos de la tabla teléfonos 
 *
 */

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="telefonos")
public class Telefono {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int id_telefono;
	private int id_padre;
	private int origen;
	private String telefono;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;
		
	public Telefono() {		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_telefono() {
		return id_telefono;
	}
	public void setId_telefono(int id_telefono) {
		this.id_telefono = id_telefono;
	}
	public int getId_padre() {
		return id_padre;
	}
	public void setId_padre(int id_padre) {
		this.id_padre = id_padre;
	}
	public int getOrigen() {
		return origen;
	}
	public void setOrigen(int origen) {
		this.origen = origen;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
		return "Telefono [id=" + id + ", id_telefono=" + id_telefono
				+ ", id_padre=" + id_padre + ", origen=" + origen
				+ ", telefono=" + telefono + ", status=" + status
				+ ", fecha_creado=" + fecha_creado + ", fecha_modificado="
				+ fecha_modificado + "]";
	}	
		
}

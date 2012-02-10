package ve.gob.cnti.srsi.modelo;

import java.util.Date;

public class EntradaSalida {

	private int id;
	private int id_entrada_salida;
	private int id_funcionalidad; // FK
	private int tipo; // Tipo Entrada o Salida (0 ó 1)
	private String formato; // ¿Codificación?
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public EntradaSalida() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_entrada_salida() {
		return id_entrada_salida;
	}

	public void setId_entrada_salida(int id_entrada_salida) {
		this.id_entrada_salida = id_entrada_salida;
	}

	public int getId_funcionalidad() {
		return id_funcionalidad;
	}

	public void setId_funcionalidad(int id_funcionalidad) {
		this.id_funcionalidad = id_funcionalidad;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
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
				+ ", tipo=" + tipo + ", formato=" + formato + ", status="
				+ status + ", fecha_creado=" + fecha_creado
				+ ", fecha_modificado=" + fecha_modificado + "]";
	}
}
package ve.gob.cnti.srsi.modelo;

import java.util.Date;

public class EntradaSalida {

	private int id;
	private int entrada_salida;
	private int funcionalidad;
	private int tipo; //Tipo Entrada o Salida
	private String formato;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public EntradaSalida() {

	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public int getEnte() {
		return entrada_salida;
	}

	public void setEnte(int entrada_salida) {
		this.entrada_salida = entrada_salida;
	}

	public int getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(int funcionalidad) {
		this.funcionalidad = funcionalidad;
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
		return "EntradaSalida [id=" + id + ", entrada_salida=" + entrada_salida
				+ ", funcionalidad=" + funcionalidad + ", tipo=" + tipo
				+ ", formato=" + formato + ", status=" + status
				+ ", fecha_creado=" + fecha_creado + ", fecha_modificado="
				+ fecha_modificado + "]";
	}

}
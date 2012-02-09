package ve.gob.cnti.srsi.modelo;

import java.util.Date;

public class EntradaSalida {

	private int id;
	private int entrada_salida;
	private int funcionalidad;
	private int tipo;
	private String formato;
	private int status;
	private Date fecha_creado;
	private Date fecha_modificado;

	public EntradaSalida() {

	}

	public EntradaSalida(int id, int entrada_salida, int funcionalidad,
			int tipo, String formato, int status, Date fecha_creado,
			Date fecha_modificado) {
		this.id = id;
		this.entrada_salida = entrada_salida;
		this.funcionalidad = funcionalidad;
		this.tipo = tipo;
		this.formato = formato;
		this.status = status;
		this.fecha_creado = fecha_creado;
		this.fecha_modificado = fecha_modificado;
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

}
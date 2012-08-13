package ve.gob.cnti.srsi.util;

import java.util.List;

import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Funcionalidad;

public class FuncionalidadesPublicables {
	private List<EntradaSalida> entradas;
	private List<EntradaSalida> salidas;
	private boolean publicable;
	Funcionalidad funcionalidad = new Funcionalidad();

	public FuncionalidadesPublicables(List<EntradaSalida> entradas,
			List<EntradaSalida> salidas, boolean publicable,
			Funcionalidad funcionalidad) {
		super();
		this.entradas = entradas;
		this.salidas = salidas;
		this.publicable = publicable;
		this.funcionalidad = funcionalidad;
	}

	public List<EntradaSalida> getEntradas() {
		return entradas;
	}

	public void setEntradas(List<EntradaSalida> entradas) {
		this.entradas = entradas;
	}

	public List<EntradaSalida> getSalidas() {
		return salidas;
	}

	public void setSalidas(List<EntradaSalida> salidas) {
		this.salidas = salidas;
	}

	public boolean isPublicable() {
		return publicable;
	}

	public void setPublicable(boolean publicable) {
		this.publicable = publicable;
	}

	public Funcionalidad getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(Funcionalidad funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

	@Override
	public String toString() {
		return "FuncionalidadesPublicables [entradas=" + entradas
				+ ", salidas=" + salidas + ", publicable=" + publicable
				+ ", funcionalidad=" + funcionalidad + "]";
	}
}
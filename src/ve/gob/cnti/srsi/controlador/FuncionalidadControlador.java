package ve.gob.cnti.srsi.controlador;

import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Funcionalidad;

@SuppressWarnings("serial")
public class FuncionalidadControlador extends FuncionalidadFormulario {
	private Funcionalidad funcionalidad;
	private EntradaSalida entrada;
	private EntradaSalida salida;

	public String registrarFuncionalidad() {
		System.out.println(funcionalidad.getNombre());
		System.out.println(funcionalidad.getDescripcion());
		return SUCCESS;

	}

	public EntradaSalida getEntrada() {
		return entrada;
	}

	public void setEntrada(EntradaSalida entrada) {
		this.entrada = entrada;
	}

	public EntradaSalida getSalida() {
		return salida;
	}

	public void setSalida(EntradaSalida salida) {
		this.salida = salida;
	}

	public Funcionalidad getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(Funcionalidad funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

}
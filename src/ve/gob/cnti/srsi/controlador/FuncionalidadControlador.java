package ve.gob.cnti.srsi.controlador;

import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Funcionalidad;

@SuppressWarnings("serial")
public class FuncionalidadControlador extends FuncionalidadFormulario {
	private Funcionalidad funcionalidad;
	private EntradaSalida entrada;
	private EntradaSalida salida;

	public String registrarFuncionalidad() {
		funcionalidad.setId(100);
		System.out.println(funcionalidad.getId());
		System.out.println(funcionalidad.getNombre());
		System.out.println(funcionalidad.getDescripcion());
		return SUCCESS;

	}

	public Funcionalidad getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(Funcionalidad funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

}
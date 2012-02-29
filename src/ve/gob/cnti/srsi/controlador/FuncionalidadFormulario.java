package ve.gob.cnti.srsi.controlador;

import java.util.List;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Funcionalidad;

@SuppressWarnings("serial")
public class FuncionalidadFormulario extends DAO implements Formulario {

	private List<Funcionalidad> funcionalidades;

	@SuppressWarnings("unchecked")
	@Override
	public String prepararFormulario() {
		Funcionalidad funcionalidad = new Funcionalidad();
		funcionalidades = (List<Funcionalidad>) read(funcionalidad);
		return SUCCESS;
	}

	// @Override
	// public void validate() {
	// super.validate();
	// prepararFormulario();
	// if (funcionalidades.isEmpty())
	// addFieldError("funcionalidades",
	// "No hay funcionalidades asociadas a este servicio de información.");
	// }

	public List<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}
}
package ve.gob.cnti.srsi.controlador;

import java.util.List;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Funcionalidad;

import com.opensymphony.xwork2.validator.annotations.Validations;

@Validations
@SuppressWarnings("serial")
public class FuncionalidadFormulario extends DAO implements Formulario {

	private List<Funcionalidad> funcionalidades;

	@SuppressWarnings("unchecked")
	@Override
	public String prepararFormulario() {
		Funcionalidad funcionalidad = new Funcionalidad();
		funcionalidades = (List<Funcionalidad>) read(funcionalidad);
		if (funcionalidades.isEmpty())
			addFieldError("funcionalidades",
					"No hay funcionalidades asociadas.");
		return SUCCESS;
	}

	public List<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}
}
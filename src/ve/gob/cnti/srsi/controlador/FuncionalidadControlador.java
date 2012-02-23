package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.List;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Funcionalidad;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class FuncionalidadControlador extends ActionSupport {

	private List<Funcionalidad> funcionalidades = new ArrayList<Funcionalidad>();
	private DAO dao = new DAO();

	@SuppressWarnings("unchecked")
	public String prepararRegistroFuncionalidad() {
		Funcionalidad funcionalidad = new Funcionalidad();
		funcionalidades = (List<Funcionalidad>) dao.read(funcionalidad);

		return SUCCESS;
	}

	public List<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}
}
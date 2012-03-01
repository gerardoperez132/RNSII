package ve.gob.cnti.srsi.controlador;

import java.util.List;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Funcionalidad;
import ve.gob.cnti.srsi.modelo.TipoDato;

@SuppressWarnings("serial")
public class FuncionalidadControlador extends DAO implements Formulario {

	private List<Funcionalidad> funcionalidades;
	private List<TipoDato> tipoDatos;
	private Funcionalidad funcionalidad;
	private EntradaSalida entrada;
	private EntradaSalida salida;

	public String registrarFuncionalidad() {
		System.out.println(funcionalidad.getNombre());
		System.out.println(funcionalidad.getDescripcion());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String prepararFormulario() {
		Funcionalidad funcionalidad = new Funcionalidad();
		TipoDato tipoDato = new TipoDato();
		funcionalidades = (List<Funcionalidad>) read(funcionalidad);
		tipoDatos = (List<TipoDato>) read(tipoDato);

		if (funcionalidades.isEmpty())
			addFieldError("funcionalidades",
					"No hay funcionalidades asociadas a este servicio de información.");
		return SUCCESS;
	}

	public List<TipoDato> getTipoDatos() {
		return tipoDatos;
	}

	public void setTipoDatos(List<TipoDato> tipoDatos) {
		this.tipoDatos = tipoDatos;
	}

	public List<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
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
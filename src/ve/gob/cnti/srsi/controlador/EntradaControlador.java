package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Funcionalidad;
import ve.gob.cnti.srsi.modelo.TipoDato;

@SuppressWarnings("serial")
public class EntradaControlador extends DAO implements Formulario {
	private List<EntradaSalida> entradas = new ArrayList<EntradaSalida>();
	private List<EntradaSalida> salidas = new ArrayList<EntradaSalida>();
	private List<TipoDato> tipoDatos = new ArrayList<TipoDato>();

	private Funcionalidad funcionalidad;
	private EntradaSalida entrada = new EntradaSalida();
	private EntradaSalida salida = new EntradaSalida();

	@SuppressWarnings("unchecked")
	@Override
	@SkipValidation
	public String prepararFormulario() {
		tipoDatos = (List<TipoDato>) getSimple();

		return SUCCESS;
	}

	
	public String registrarEntradaSimple(){
				
		return SUCCESS;
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

	public Funcionalidad getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(Funcionalidad funcionalidad) {
		this.funcionalidad = funcionalidad;
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

	public List<TipoDato> getTipoDatos() {
		return tipoDatos;
	}

	public void setTipoDatos(List<TipoDato> tipoDatos) {
		this.tipoDatos = tipoDatos;
	}

}

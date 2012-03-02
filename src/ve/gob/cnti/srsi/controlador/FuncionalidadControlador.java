package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Funcionalidad;

@SuppressWarnings("serial")
public class FuncionalidadControlador extends DAO implements Formulario {

	private List<EntradaSalida> entradas = new ArrayList<EntradaSalida>();
	private List<EntradaSalida> salidas = new ArrayList<EntradaSalida>();

	private Funcionalidad funcionalidad = new Funcionalidad();
	private EntradaSalida entrada = new EntradaSalida();
	private EntradaSalida salida = new EntradaSalida();
	
	private long idServicioInformacion;
	
	private long idsi;

	public String registrarFuncionalidad() {
		
		return SUCCESS;
	}

	public void validate() {
		if (funcionalidad.getNombre().isEmpty())
			addFieldError("funcionalidad.nombre", "Debe introducir un nombre.");
		if (funcionalidad.getDescripcion().isEmpty())
			addFieldError("funcionalidad.descripcion",
					"Debe introducir una descripción.");
	}

	@Override
	@SkipValidation
	public String prepararFormulario() {
		
		String nombre_dato = read(1);
		System.out.println("El dato de la entrada 1 es => " + nombre_dato);
		System.out.println("id "+idServicioInformacion);
		System.out.println("idsi "+idsi);
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

	public long getIdServicioInformacion() {
		return idServicioInformacion;
	}

	public void setIdServicioInformacion(long idServicioInformacion) {
		this.idServicioInformacion = idServicioInformacion;
	}

	public long getIdsi() {
		return idsi;
	}

	public void setIdsi(long idsi) {
		this.idsi = idsi;
	}

}
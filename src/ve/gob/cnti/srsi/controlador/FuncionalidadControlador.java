package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Funcionalidad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;

@SuppressWarnings("serial")
public class FuncionalidadControlador extends DAO implements Formulario {

	private List<EntradaSalida> entradas = new ArrayList<EntradaSalida>();
	private List<EntradaSalida> salidas = new ArrayList<EntradaSalida>();
	private List<Funcionalidad> funcionalidades = new ArrayList<Funcionalidad>();

	private ServicioInformacion servicio = new ServicioInformacion();
	private Funcionalidad funcionalidad = new Funcionalidad();
	private EntradaSalida entrada = new EntradaSalida();
	private EntradaSalida salida = new EntradaSalida();

	private long idServicioInformacion;
	private long idFuncionalidad;
	
	private boolean modificar;

	public String registrarFuncionalidad() {
		/*
		 * idFuncionalidadVariable necesaria para persistir la E/S de la
		 * funcionalidad en los otros controladores
		 */
		idFuncionalidad = getNextId(funcionalidad);
		funcionalidad.setId_servicio_informacion(idServicioInformacion);
		create(funcionalidad);
		return SUCCESS;
	}

	public void validate() {
		if (funcionalidad.getNombre().isEmpty())
			addFieldError("funcionalidad.nombre", "Debe introducir un nombre.");
		if (funcionalidad.getDescripcion().isEmpty())
			addFieldError("funcionalidad.descripcion",
					"Debe introducir una descripción.");
	}

	@SuppressWarnings("unchecked")
	@Override
	@SkipValidation
	public String prepararFormulario() {
		
		System.out.println("idf: "+ idFuncionalidad);
		System.out.println("id s: "+ idServicioInformacion);
		
		servicio = (ServicioInformacion) read(servicio, idServicioInformacion);
		if(idFuncionalidad>0){
			funcionalidad = (Funcionalidad) read(funcionalidad, idFuncionalidad);
			funcionalidades = null;
			funcionalidades = ((List<Funcionalidad>) read(funcionalidad,
					new ServicioInformacion(), idFuncionalidad));
		}		
		return SUCCESS;
	}
	
	public String modificarFuncionalidad() {
		
		System.out.println("idf: "+ idFuncionalidad);
		System.out.println("id s: "+ idServicioInformacion);
		update(funcionalidad, idFuncionalidad);
				
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

	public ServicioInformacion getServicio() {
		return servicio;
	}

	public void setServicio(ServicioInformacion servicio) {
		this.servicio = servicio;
	}

	public long getIdServicioInformacion() {
		return idServicioInformacion;
	}

	public void setIdServicioInformacion(long idServicioInformacion) {
		this.idServicioInformacion = idServicioInformacion;
	}

	public long getIdFuncionalidad() {
		return idFuncionalidad;
	}

	public void setIdFuncionalidad(long idFuncionalidad) {
		this.idFuncionalidad = idFuncionalidad;
	}

	public List<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public boolean isModificar() {
		return modificar;
	}

	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}

}
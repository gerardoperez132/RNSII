package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.dao.Constants.ArregloModelos;
import ve.gob.cnti.srsi.dao.Constants.TipoEntradaSalida;
import ve.gob.cnti.srsi.modelo.Dato;
import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Funcionalidad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;

@SuppressWarnings("serial")
public class FuncionalidadControlador extends DAO implements Formulario ,
TipoEntradaSalida, ArregloModelos {
	
	private List<EntradaSalida> entradas = new ArrayList<EntradaSalida>();
	private List<EntradaSalida> salidas = new ArrayList<EntradaSalida>();
	private List<Funcionalidad> funcionalidades = new ArrayList<Funcionalidad>();
	private List<Dato> datosEntradas = new ArrayList<Dato>();
	private List<Dato> datosSalidas = new ArrayList<Dato>();

	private ServicioInformacion servicio = new ServicioInformacion();
	private Funcionalidad funcionalidad = new Funcionalidad();
	private EntradaSalida entrada = new EntradaSalida();
	private EntradaSalida salida = new EntradaSalida();

	private long idServicioInformacion;
	private long idFuncionalidad;
	
	private boolean modificar;
	private boolean resumen;

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

	@Override
	@SkipValidation
	public String prepararModificaciones() {
		funcionalidad = (Funcionalidad) read(funcionalidad, idFuncionalidad);
		return SUCCESS;
	}
		
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararResumen() {
		System.out.println("mod: "+modificar);
		resumen = true;
		servicio = (ServicioInformacion) read(servicio, idServicioInformacion);
		funcionalidad = (Funcionalidad) read(funcionalidad, idFuncionalidad);
		datosEntradas = (ArrayList<Dato>) read(NOMBRE_DATO, ENTRADA, idFuncionalidad);
		datosSalidas = (ArrayList<Dato>) read(NOMBRE_DATO, SALIDA, idFuncionalidad);
		if (datosSalidas.size()<1){
			addFieldError("Salidas",
					"Aún no ha cargado datos de salidas");
			return INPUT;
		}
		
		return SUCCESS;
	}
	
	
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararFuncionalidades() {
	
		servicio = (ServicioInformacion) read(servicio, idServicioInformacion);			
		funcionalidades = ((List<Funcionalidad>) read(funcionalidad,
				new ServicioInformacion(), idServicioInformacion));				
		return SUCCESS;
	}

	public String modificarFuncionalidad() {

		funcionalidad.setId_servicio_informacion(idServicioInformacion);		
		update(funcionalidad, idFuncionalidad);			

		return SUCCESS;
	}
	
	
	@SkipValidation
	public String eliminarFuncionalidad() {
				
		delete(funcionalidad, idFuncionalidad);			
		prepararFuncionalidades();
		
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
	
		servicio = (ServicioInformacion) read(servicio, idServicioInformacion);
		if(idFuncionalidad>0){
			funcionalidad = (Funcionalidad) read(funcionalidad, idFuncionalidad);			
			funcionalidades = ((List<Funcionalidad>) read(funcionalidad,
					new ServicioInformacion(), idFuncionalidad));
		}		
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

	public List<Dato> getDatosEntradas() {
		return datosEntradas;
	}

	public void setDatosEntradas(List<Dato> datosEntradas) {
		this.datosEntradas = datosEntradas;
	}

	public List<Dato> getDatosSalidas() {
		return datosSalidas;
	}

	public void setDatosSalidas(List<Dato> datosSalidas) {
		this.datosSalidas = datosSalidas;
	}

	public boolean isResumen() {
		return resumen;
	}

	public void setResumen(boolean resumen) {
		this.resumen = resumen;
	}

}
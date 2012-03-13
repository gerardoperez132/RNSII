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
import ve.gob.cnti.srsi.modelo.TipoDato;

@SuppressWarnings("serial")
public class SalidaControlador extends DAO implements Formulario,
		TipoEntradaSalida, ArregloModelos {
	private List<EntradaSalida> entradas = new ArrayList<EntradaSalida>();
	private List<EntradaSalida> salidas = new ArrayList<EntradaSalida>();
	private List<TipoDato> tipoDatos = new ArrayList<TipoDato>();
	private List<Dato> datos = new ArrayList<Dato>();

	private ServicioInformacion servicio = new ServicioInformacion();
	private Funcionalidad funcionalidad = new Funcionalidad();
	private EntradaSalida entrada = new EntradaSalida();
	private EntradaSalida salida = new EntradaSalida();
	private Dato dato = new Dato();

	private long id_dato;
	private long idServicioInformacion;
	private long idFuncionalidad;
	private boolean complejo;

	@SuppressWarnings("unchecked")
	@Override
	@SkipValidation
	public String prepararFormulario() {
		
		funcionalidad = (Funcionalidad) read(funcionalidad, idFuncionalidad);
		servicio = (ServicioInformacion) read(servicio, idServicioInformacion);
		datos = (ArrayList<Dato>) read(NOMBRE_DATO, SALIDA, idFuncionalidad);
		tipoDatos = (List<TipoDato>) getALL();
		complejo = false;
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararFormularioSimple() {

		funcionalidad = (Funcionalidad) read(funcionalidad, idFuncionalidad);
		servicio = (ServicioInformacion) read(servicio, idServicioInformacion);
		datos = (ArrayList<Dato>) read(NOMBRE_DATO, SALIDA, idFuncionalidad);
		tipoDatos = null;
		tipoDatos = (List<TipoDato>) getSimple();
		complejo = false;

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararFormularioComplejo() {

		funcionalidad = (Funcionalidad) read(funcionalidad, idFuncionalidad);
		servicio = (ServicioInformacion) read(servicio, idServicioInformacion);
		datos = (ArrayList<Dato>) read(NOMBRE_DATO, SALIDA, idFuncionalidad);
		tipoDatos = null;
		tipoDatos = (List<TipoDato>) getComplex();
		complejo = true;

		return SUCCESS;
	}

	public String registrarSalidaSimple() {

		salida.setId_funcionalidad(idFuncionalidad);
		salida.setTipo(SALIDA);
		dato.setId_entrada_salida(getNextId(salida));
		if( id_dato >0 ){
			dato.setId_padre(id_dato);
		}
		create(salida);
		create(dato);

		return SUCCESS;
	}

	public String registrarSalidaCompleja() {

		salida.setId_funcionalidad(idFuncionalidad);
		salida.setTipo(SALIDA);
		dato.setId_entrada_salida(getNextId(salida));
		create(salida);
		create(dato);
		complejo = false;
		return SUCCESS;
	}

	public void validate() {
		if (dato.getNombre().isEmpty())
			addFieldError("dato.nombre",
					"Debe introducir un nombre que identifique el dato");
		if (dato.getDescripcion().isEmpty())
			addFieldError("dato.descripcion",
					"Debe introducir una descripción.");
		if (dato.getId_tipo_dato() == -1)
			addFieldError("tipodato", "Debe seleccionar un tipo de dato");
		if (read(NOMBRE_DATO_NO_DUPLICADO, dato.getNombre(), idFuncionalidad)) {
			addFieldError("dato.nombre",
					"Nombre de Entrada Duplicado, cambie el nombre por favor");
		}
		if (complejo == true) {
			prepararFormularioComplejo();
		} else {
			prepararFormularioSimple();
		}
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

	public ServicioInformacion getServicio() {
		return servicio;
	}

	public void setServicio(ServicioInformacion servicio) {
		this.servicio = servicio;
	}

	public Dato getDato() {
		return dato;
	}

	public void setDato(Dato dato) {
		this.dato = dato;
	}

	public List<Dato> getDatos() {
		return datos;
	}

	public void setDatos(List<Dato> datos) {
		this.datos = datos;
	}

	public boolean isComplejo() {
		return complejo;
	}

	public void setComplejo(boolean complejo) {
		this.complejo = complejo;
	}

	public long getId_dato() {
		return id_dato;
	}

	public void setId_dato(long id_dato) {
		this.id_dato = id_dato;
	}

	@Override
	public String prepararModificaciones() {
		// TODO Auto-generated method stub
		return null;
	}
}

package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.Constants.ArregloModelos;
import ve.gob.cnti.srsi.dao.Constants.TipoEntradaSalida;
import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Dato;
import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Funcionalidad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.TipoDato;

@SuppressWarnings("serial")
public class EntradaControlador extends DAO implements Formulario,
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

	private long idServicioInformacion;
	private long idFuncionalidad;

	@SuppressWarnings("unchecked")
	@Override
	@SkipValidation
	public String prepararFormulario() {

		funcionalidad = (Funcionalidad) read(funcionalidad, idFuncionalidad);
		servicio = (ServicioInformacion) read(servicio, idServicioInformacion);
		// datos = (ArrayList<Dato>) readEntrada(idFuncionalidad);
		datos = (ArrayList<Dato>) read(NOMBRE_DATO, ENTRADA, idFuncionalidad);
		Iterator<Dato> iterator = datos.iterator();
		while (iterator.hasNext()) {

			System.out.println(" DATO => " + iterator.next().getNombre());
		}
		tipoDatos = (List<TipoDato>) getSimple();

		return SUCCESS;
	}

	public String registrarEntradaSimple() {
		entrada.setId_funcionalidad(idFuncionalidad);
		entrada.setTipo(ENTRADA);
		dato.setId_entrada_salida(getNextId(entrada));
		create(entrada);
		create(dato);

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
		prepararFormulario();
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

}

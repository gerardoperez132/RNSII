package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.Constants.Modelos;
import ve.gob.cnti.srsi.dao.Constants.TipoEntradaSalida;
import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Funcionalidad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.TipoDato;

import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public class EntradaControlador extends DAO implements Preparable,
		TipoEntradaSalida, Modelos {

	private List<EntradaSalida> entradas;
	private List<TipoDato> tipoDatos;

	private ServicioInformacion servicio = new ServicioInformacion();
	private Funcionalidad funcionalidad = new Funcionalidad();
	private EntradaSalida entrada = new EntradaSalida();

	private long id_entrada_salida;
	private long id_servicio_informacion;
	private long id_funcionalidad;
	private boolean complejo;
	private boolean modificar;

	public List<EntradaSalida> getEntradas() {
		return entradas;
	}

	public List<TipoDato> getTipoDatos() {
		return tipoDatos;
	}

	public ServicioInformacion getServicio() {
		return servicio;
	}

	public Funcionalidad getFuncionalidad() {
		return funcionalidad;
	}

	public EntradaSalida getEntrada() {
		return entrada;
	}

	public long getId_entrada_salida() {
		return id_entrada_salida;
	}

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public long getId_funcionalidad() {
		return id_funcionalidad;
	}

	public boolean isComplejo() {
		return complejo;
	}

	public boolean isModificar() {
		return modificar;
	}

	public void setEntradas(List<EntradaSalida> entradas) {
		this.entradas = entradas;
	}

	public void setTipoDatos(List<TipoDato> tipoDatos) {
		this.tipoDatos = tipoDatos;
	}

	public void setServicio(ServicioInformacion servicio) {
		this.servicio = servicio;
	}

	public void setFuncionalidad(Funcionalidad funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

	public void setEntrada(EntradaSalida entrada) {
		this.entrada = entrada;
	}

	public void setId_entrada_salida(long id_entrada_salida) {
		this.id_entrada_salida = id_entrada_salida;
	}

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
	}

	public void setId_funcionalidad(long id_funcionalidad) {
		this.id_funcionalidad = id_funcionalidad;
	}

	public void setComplejo(boolean complejo) {
		this.complejo = complejo;
	}

	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararFormularioSimple() {
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		entradas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad,
				ENTRADA);
		tipoDatos = (List<TipoDato>) getSimple();
		complejo = false;
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararFormularioComplejo() {
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		entradas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad,
				ENTRADA);
		tipoDatos = (List<TipoDato>) getComplex();
		complejo = true;
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararModificarEntradaSimple() {
		entrada = (EntradaSalida) read(entrada, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		tipoDatos = (List<TipoDato>) getSimple();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararModificarEntradaCompleja() {
		entrada = (EntradaSalida) read(entrada, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		tipoDatos = (List<TipoDato>) getComplex();
		return SUCCESS;
	}

	public String registrarEntrada() {
		entrada.setId_funcionalidad(id_funcionalidad);
		entrada.setTipo(ENTRADA);
		if (id_entrada_salida > 0) {
			entrada.setId_padre(id_entrada_salida);
		}
		create(entrada);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String modificarEntrada() {
		EntradaSalida modificada = new EntradaSalida();
		modificada = (EntradaSalida) read(entrada, id_entrada_salida);
		modificada.setNombre(entrada.getNombre());
		modificada.setDescripcion(entrada.getDescripcion());
		modificada.setId_tipo_dato(entrada.getId_tipo_dato());
		update(modificada, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		entradas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad,
				ENTRADA);
		tipoDatos = (List<TipoDato>) read(new TipoDato());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String eliminarEntradaSimple() {
		delete(entrada, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		entradas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad,
				ENTRADA);
		tipoDatos = (List<TipoDato>) read(new TipoDato());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String eliminarEntradaCompleja() {
		entradas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad,
				ENTRADA);
		Iterator<EntradaSalida> iterator = entradas.iterator();
		while (iterator.hasNext()) {
			entrada = iterator.next();
			if (entrada.getId_padre() == id_entrada_salida)
				delete(entrada, entrada.getId_entrada_salida());
		}
		delete(entrada, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		entradas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad,
				ENTRADA);
		tipoDatos = (List<TipoDato>) read(new TipoDato());
		return SUCCESS;
	}

	@Override
	public void validate() {
		if (entrada.getNombre().isEmpty())
			addFieldError("entrada.nombre",
					"Debe introducir un nombre que identifique el dato");
		if (entrada.getDescripcion().isEmpty())
			addFieldError("entrada.descripcion",
					"Debe introducir una descripción.");
		if (entrada.getId_tipo_dato() == -1)
			addFieldError("tipodato", "Debe seleccionar un tipo de dato");
		if (read(ESF, id_funcionalidad, entrada.getNombre()) && !modificar) {
			addFieldError("entrada.nombre",
					"Nombre de entrada duplicado, cambie el nombre por favor");
		}
		if (complejo) {
			prepararFormularioComplejo();
		} else {
			prepararFormularioSimple();
		}
	}

	@Override
	public void prepare() throws Exception {
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		entradas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad,
				ENTRADA);
		tipoDatos = (List<TipoDato>) read(new TipoDato());
		complejo = false;
	}
}
package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.Constants;
import ve.gob.cnti.srsi.dao.Constants.Formulario;
import ve.gob.cnti.srsi.dao.Constants.Modelos;
import ve.gob.cnti.srsi.dao.Constants.TipoEntradaSalida;
import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Funcionalidad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.Usuario;

@SuppressWarnings("serial")
public class FuncionalidadControlador extends DAO implements Formulario,
		TipoEntradaSalida, Modelos, Constants {

	private List<EntradaSalida> entradas;
	private List<EntradaSalida> salidas;
	private List<Funcionalidad> funcionalidades;

	private ServicioInformacion servicio = new ServicioInformacion();
	private Funcionalidad funcionalidad = new Funcionalidad();
	private EntradaSalida entrada = new EntradaSalida();
	private EntradaSalida salida = new EntradaSalida();
	private Map session;

	private long id_servicio_informacion;
	private long id_funcionalidad;

	private boolean modificar;
	private boolean modificarf;
	private boolean resumen;

	@SuppressWarnings("unchecked")
	@Override
	@SkipValidation
	public String prepararFormulario() {
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		if (id_funcionalidad > 0) {
			funcionalidad = (Funcionalidad) read(funcionalidad,
					id_funcionalidad);
			funcionalidades = (List<Funcionalidad>) read(FSI, id_funcionalidad,
					-1);
		}
		return SUCCESS;
	}

	public String registrarFuncionalidad() {
		Usuario user = new Usuario();
		user = (Usuario) session.get("usuario");
		id_funcionalidad = getNextId(funcionalidad);
		funcionalidad.setId_servicio_informacion(id_servicio_informacion);
		funcionalidad.setId_usuario(user.getId_usuario());
		create(funcionalidad);
		return SUCCESS;
	}

	@SkipValidation
	public String prepararModificaciones() {
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararResumen() {
		resumen = true;
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		entradas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad,
				ENTRADA);
		salidas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad, SALIDA);
		if (salidas.size() < 1) {
			addFieldError("Salidas", "Aún no ha cargado datos de salidas");
			return INPUT;
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararFuncionalidades() {
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		funcionalidades = (List<Funcionalidad>) read(FSI,
				id_servicio_informacion, -1);
		return SUCCESS;
	}

	public String modificarFuncionalidad() {
		Usuario user = new Usuario();
		user = (Usuario) session.get("usuario");
		funcionalidad.setId_servicio_informacion(id_servicio_informacion);
		funcionalidad.setId_usuario(user.getId_usuario());
		update(funcionalidad, id_funcionalidad);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String eliminarFuncionalidad() {
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio,
				funcionalidad.getId_servicio_informacion());
		funcionalidades = (List<Funcionalidad>) read(FSI,
				id_servicio_informacion, -1);
		if (funcionalidades.size() == 0) {
			servicio.setPublicado(false);
			update(servicio, id_servicio_informacion);
		}
		delete(funcionalidad, id_funcionalidad);
		prepararFuncionalidades();
		return SUCCESS;
	}

	public void validate() {
		if (funcionalidad.getNombre().trim().isEmpty())
			addFieldError("funcionalidad.nombre", error.getProperties()
					.getProperty("error.funcionalidad.nombre"));
		if (!funcionalidad.getNombre().toUpperCase().matches(REGEX_TITLE))
			addFieldError("funcionalidad.nombre", error.getProperties()
					.getProperty("error.regex.title"));
		if (funcionalidad.getDescripcion().trim().isEmpty())
			addFieldError("funcionalidad.descripcion", error.getProperties()
					.getProperty("error.funcionalidad.descripcion"));
		if (!funcionalidad.getDescripcion().toUpperCase()
				.matches(REGEX_DESCRIPTION))
			addFieldError("funcionalidad.descripcion", error.getProperties()
					.getProperty("error.regex.description"));
	}

	public List<EntradaSalida> getEntradas() {
		return entradas;
	}

	public List<EntradaSalida> getSalidas() {
		return salidas;
	}

	public List<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
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

	public EntradaSalida getSalida() {
		return salida;
	}

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public long getId_funcionalidad() {
		return id_funcionalidad;
	}

	public boolean isModificar() {
		return modificar;
	}

	public boolean isResumen() {
		return resumen;
	}

	public void setEntradas(List<EntradaSalida> entradas) {
		this.entradas = entradas;
	}

	public void setSalidas(List<EntradaSalida> salidas) {
		this.salidas = salidas;
	}

	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
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

	public void setSalida(EntradaSalida salida) {
		this.salida = salida;
	}

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
	}

	public void setId_funcionalidad(long id_funcionalidad) {
		this.id_funcionalidad = id_funcionalidad;
	}

	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}

	public void setResumen(boolean resumen) {
		this.resumen = resumen;
	}

	public boolean isModificarf() {
		return modificarf;
	}

	public void setModificarf(boolean modificarf) {
		this.modificarf = modificarf;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}
}
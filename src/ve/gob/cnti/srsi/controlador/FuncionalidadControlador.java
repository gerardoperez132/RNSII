/* This file is part of SRSI.
 * 
 * SRSI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * SRSI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SRSI. If not, see <http://www.gnu.org/licenses/>.
 */
package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.Date;
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
import ve.gob.cnti.srsi.util.EstadosTiempo;
import ve.gob.cnti.srsi.util.ReadXmlTime;

import com.opensymphony.xwork2.ActionContext;

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
	@SuppressWarnings("rawtypes")
	private Map session;

	private long id_servicio_informacion;
	private long id_funcionalidad;

	private boolean modificar;
	private boolean modificarf;
	private boolean resumen;

	private List<EstadosTiempo> estadosTiempo = new ArrayList<EstadosTiempo>();
	private Date fecha;

	@SuppressWarnings("unchecked")
	@Override
	@SkipValidation
	public String prepararFormulario() {
		getTiempoFecha();
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
		getTiempoFecha();
		Usuario user = new Usuario();
		session = ActionContext.getContext().getSession();
		user = (Usuario) session.get("usuario");
		id_funcionalidad = getNextId(funcionalidad);
		funcionalidad.setId_servicio_informacion(id_servicio_informacion);
		funcionalidad.setId_usuario(user.getId_usuario());
		create(funcionalidad);
		return SUCCESS;
	}

	@SkipValidation
	public String prepararModificaciones() {
		getTiempoFecha();
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararResumen() {
		getTiempoFecha();
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
		getTiempoFecha();
		if (id_servicio_informacion == 0) {
			session = ActionContext.getContext().getSession();
			servicio = (ServicioInformacion) session.get("servicio");
			id_servicio_informacion = servicio.getId_servicio_informacion();
		} else {
			servicio = (ServicioInformacion) read(new ServicioInformacion(),
					id_servicio_informacion);
		}
		funcionalidades = (List<Funcionalidad>) read(FSI,
				id_servicio_informacion, -1);
		return SUCCESS;
	}

	public String modificarFuncionalidad() {
		getTiempoFecha();
		Usuario user = new Usuario();
		session = ActionContext.getContext().getSession();
		user = (Usuario) session.get("usuario");
		funcionalidad.setId_servicio_informacion(id_servicio_informacion);
		funcionalidad.setId_usuario(user.getId_usuario());
		update(funcionalidad, id_funcionalidad);
		return SUCCESS;
	}

	// TODO Eliminar identificando el usuario
	// TODO Eliminar los datos E/S de la funcionalidad
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String eliminarFuncionalidad() {
		getTiempoFecha();
		Usuario user = new Usuario();
		session = ActionContext.getContext().getSession();
		user = (Usuario) session.get("usuario");
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		funcionalidad.setId_usuario(user.getId_usuario());
		servicio = (ServicioInformacion) read(servicio,
				funcionalidad.getId_servicio_informacion());
		funcionalidades = (List<Funcionalidad>) read(FSI,
				id_servicio_informacion, -1);
		if (funcionalidades.size() == 0) {
			servicio.setPublicado(false);
			update(servicio, id_servicio_informacion);
		}
		List<EntradaSalida> ios = (List<EntradaSalida>) read(ESF,
				id_funcionalidad, -1);
		for (EntradaSalida io : ios)
			delete(io, io.getId_entrada_salida());
		delete(funcionalidad, id_funcionalidad);
		prepararFuncionalidades();
		return SUCCESS;
	}

	public void validate() {
		getTiempoFecha();
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

	public void getTiempoFecha() {
		ReadXmlTime read = new ReadXmlTime();
		fecha = read.getFechaTiempo();
		estadosTiempo = read.getEstadosTiempo();
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

	public List<EstadosTiempo> getEstadosTiempo() {
		return estadosTiempo;
	}

	public void setEstadosTiempo(List<EstadosTiempo> estadosTiempo) {
		this.estadosTiempo = estadosTiempo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
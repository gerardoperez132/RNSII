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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.Constants;
import ve.gob.cnti.srsi.dao.Constants.Formulario;
import ve.gob.cnti.srsi.dao.Constants.Modelos;
import ve.gob.cnti.srsi.dao.Constants.TipoEntradaSalida;
import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Formato;
import ve.gob.cnti.srsi.modelo.Funcionalidad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.TipoDato;
import ve.gob.cnti.srsi.modelo.Usuario;
import ve.gob.cnti.srsi.util.EstadosTiempo;
import ve.gob.cnti.srsi.util.ReadXmlTime;

import com.opensymphony.xwork2.ActionContext;

/**
 * Esta clase es el controlador de las entradas.
 * 
 * @author Richard Ricciardelli
 * @author Joaquín Pereira
 * 
 */
@SuppressWarnings("serial")
public class EntradaControlador extends DAO implements TipoEntradaSalida,
		Modelos, Formulario, Constants {

	private List<EntradaSalida> entradas;
	private List<TipoDato> tipoDatos;
	private List<Formato> formatos = new ArrayList<Formato>();

	private ServicioInformacion servicio = new ServicioInformacion();
	private Funcionalidad funcionalidad = new Funcionalidad();
	private EntradaSalida entrada = new EntradaSalida();
	@SuppressWarnings("rawtypes")
	private Map session;

	private long id_entrada_salida;
	private long id_entrada_padre;
	private long id_servicio_informacion;
	private long id_funcionalidad;
	private long id_tipo_dato;
	private boolean complejo;
	private boolean modificar;
	private boolean hasLength;
	private boolean hasformatted;
	private List<EstadosTiempo> estadosTiempo = new ArrayList<EstadosTiempo>();
	private Date fecha;

	@SuppressWarnings("unchecked")
	@Override
	@SkipValidation
	public String prepararFormulario() {
		getTiempoFecha();
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		entradas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad,
				ENTRADA);
		tipoDatos = (ArrayList<TipoDato>) read(new TipoDato());
		complejo = false;
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararRegistroEntrada() {
		getTiempoFecha();
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		entradas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad,
				ENTRADA);
		tipoDatos = (List<TipoDato>) read(new TipoDato());
		formatos = (ArrayList<Formato>) read(new Formato());
		complejo = false;
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararFormularioSimple() {
		getTiempoFecha();
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		entradas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad,
				ENTRADA);
		tipoDatos = (List<TipoDato>) getSimple();
		formatos = (ArrayList<Formato>) read(new Formato());
		complejo = false;
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararFormularioComplejo() {
		getTiempoFecha();
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
	public String prepararModificarEntrada() {
		getTiempoFecha();
		entrada = (EntradaSalida) read(entrada, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		tipoDatos = (ArrayList<TipoDato>) read(new TipoDato());
		formatos = (ArrayList<Formato>) read(new Formato());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararModificarEntradaSimple() {
		getTiempoFecha();
		entrada = (EntradaSalida) read(entrada, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		tipoDatos = (List<TipoDato>) getSimple();
		formatos = (ArrayList<Formato>) read(new Formato());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararModificarEntradaCompleja() {
		getTiempoFecha();
		entrada = (EntradaSalida) read(entrada, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		tipoDatos = (List<TipoDato>) getComplex();
		return SUCCESS;
	}

	public String registrarEntrada() {
		getTiempoFecha();
		Usuario user = new Usuario();
		session = ActionContext.getContext().getSession();
		user = (Usuario) session.get("usuario");
		entrada.setId_funcionalidad(id_funcionalidad);
		entrada.setId_usuario(user.getId_usuario());
		entrada.setTipo(ENTRADA);
		if (id_entrada_padre > 0) {
			entrada.setId_padre(id_entrada_padre);
		}
		create(entrada);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String modificarEntrada() {
		getTiempoFecha();
		Usuario user = new Usuario();
		session = ActionContext.getContext().getSession();
		user = (Usuario) session.get("usuario");
		EntradaSalida modificada = new EntradaSalida();
		modificada = (EntradaSalida) read(entrada, id_entrada_salida);
		modificada.setNombre(entrada.getNombre());
		modificada.setDescripcion(entrada.getDescripcion());
		modificada.setId_tipo_dato(entrada.getId_tipo_dato());
		modificada.setId_formato(entrada.getId_formato());
		modificada.setLongitud(entrada.getLongitud());
		modificada.setId_usuario(user.getId_usuario());
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
		getTiempoFecha();
		Usuario user = new Usuario();
		session = ActionContext.getContext().getSession();
		user = (Usuario) session.get("usuario");
		entrada.setId_usuario(user.getId_usuario());
		delete(entrada, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		entradas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad,
				ENTRADA);
		tipoDatos = (List<TipoDato>) read(new TipoDato());
		return SUCCESS;
	}

	// TODO hay que borrar con le id del usuario, seria bueno que sea
	// directamente del metodo del dao
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String eliminarEntradaCompleja() {
		getTiempoFecha();
		entradas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad,
				ENTRADA);
		Iterator<EntradaSalida> iterator = entradas.iterator();
		while (iterator.hasNext()) {
			entrada = iterator.next();
			if (entrada.getId_padre() == id_entrada_salida) {
				delete(entrada, entrada.getId_entrada_salida());
			}
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
		getTiempoFecha();
		if (entrada.getNombre().trim().isEmpty())
			addFieldError("entrada.nombre",
					error.getProperties().getProperty("error.entrada.nombre"));
		if (!entrada.getNombre().toUpperCase().matches(REGEX_TITLE))
			addFieldError("entrada.nombre",
					error.getProperties().getProperty("error.regex.title"));
		if (entrada.getDescripcion().trim().isEmpty())
			addFieldError("entrada.descripcion", error.getProperties()
					.getProperty("error.entrada.descripcion"));
		if (!entrada.getNombre().toUpperCase().matches(REGEX_DESCRIPTION))
			addFieldError("entrada.descripcion", error.getProperties()
					.getProperty("error.regex.description"));
		if (entrada.getId_tipo_dato() == -1) {
			addFieldError("tipodato",
					error.getProperties().getProperty("error.entrada.tipodato"));
			entrada.setId_formato((long) -1);
			entrada.setLongitud("");
		} else {
			TipoDato td = (TipoDato) read(new TipoDato(),
					entrada.getId_tipo_dato());
			if (td.isHasformatted()) {
				if (entrada.getId_formato() == -1) {
					addFieldError(
							"formato",
							error.getProperties().getProperty(
									"error.entrada.format"));
				} else {
					Formato f = (Formato) read(new Formato(),
							entrada.getId_formato());
					if (f.getId_tipo_dato() != entrada.getId_tipo_dato())
						addFieldError("formato", error.getProperties()
								.getProperty("error.entrada.format"));
				}
			} else {
				entrada.setId_formato((long) -1);
			}
			if (td.isHasLength()) {
				if (!entrada.getLongitud().isEmpty()) {
					if (entrada.getId_tipo_dato() == 4) {
						try {
							float num = Float.parseFloat(entrada.getLongitud()
									.toString());
							if (num <= 0) {
								addFieldError("longitud", error.getProperties()
										.getProperty("error.entrada.longitud"));
							}
						} catch (Exception e) {
							addFieldError("longitud", error.getProperties()
									.getProperty("error.entrada.longitud"));
						}
					} else {
						try {
							Long num = Long.parseLong(entrada.getLongitud()
									.toString());
							if (num <= 0) {
								addFieldError("longitud", error.getProperties()
										.getProperty("error.entrada.longitud"));
							}
						} catch (Exception e) {
							addFieldError("longitud", error.getProperties()
									.getProperty("error.entrada.longitud"));
						}
					}
				} else {
					addFieldError("longitud", error.getProperties()
							.getProperty("error.entrada.digit"));
				}
			} else {
				entrada.setLongitud(error.getProperties().getProperty(
						"error.length"));
			}
		}
		if (entradaSalidaDuplicada(id_funcionalidad, id_entrada_salida,
				ENTRADA, entrada.getNombre())) {
			addFieldError(
					"entrada.nombre",
					error.getProperties().getProperty(
							"error.entrada.duplicated"));
		}
		if (complejo) {
			prepararFormularioComplejo();
		} else if (id_entrada_padre > 0) {
			prepararFormularioSimple();
		} else
			prepararRegistroEntrada();
	}

	@SkipValidation
	public String dato_haslength() {
		getTiempoFecha();
		TipoDato td = new TipoDato();
		td = (TipoDato) read(td, id_tipo_dato);
		hasLength = td.isHasLength();
		return SUCCESS;
	}

	@SkipValidation
	public String dato_hasformatted() {
		getTiempoFecha();
		TipoDato td = new TipoDato();
		td = (TipoDato) read(td, id_tipo_dato);
		hasformatted = td.isHasformatted();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String list_format() {
		getTiempoFecha();
		Object[] models = { new Formato(), new TipoDato() };
		formatos = (List<Formato>) read(models, id_tipo_dato, -1);
		return SUCCESS;
	}

	public void getTiempoFecha() {
		ReadXmlTime read = new ReadXmlTime();
		fecha = read.getFechaTiempo();
		estadosTiempo = read.getEstadosTiempo();
	}

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

	public List<Formato> getFormatos() {
		return formatos;
	}

	public void setFormatos(List<Formato> formatos) {
		this.formatos = formatos;
	}

	public boolean isHasLength() {
		return hasLength;
	}

	public void setHasLength(boolean hasLength) {
		this.hasLength = hasLength;
	}

	public long getId_tipo_dato() {
		return id_tipo_dato;
	}

	public void setId_tipo_dato(long id_tipo_dato) {
		this.id_tipo_dato = id_tipo_dato;
	}

	public boolean isHasformatted() {
		return hasformatted;
	}

	public void setHasformatted(boolean hasformatted) {
		this.hasformatted = hasformatted;
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

	public long getId_entrada_padre() {
		return id_entrada_padre;
	}

	public void setId_entrada_padre(long id_entrada_padre) {
		this.id_entrada_padre = id_entrada_padre;
	}
}
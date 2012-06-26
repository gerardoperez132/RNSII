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

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class SalidaControlador extends DAO implements Formulario,
		TipoEntradaSalida, Modelos, Constants {

	private List<EntradaSalida> salidas;
	private List<TipoDato> tipoDatos;
	private List<Formato> formatos;

	private ServicioInformacion servicio = new ServicioInformacion();
	private Funcionalidad funcionalidad = new Funcionalidad();
	private EntradaSalida salida = new EntradaSalida();
	@SuppressWarnings("rawtypes")
	private Map session;

	private long id_entrada_salida;
	private long id_servicio_informacion;
	private long id_funcionalidad;
	private boolean complejo;
	private boolean modificar;

	@SuppressWarnings("unchecked")
	@Override
	@SkipValidation
	public String prepararFormulario() {
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		salidas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad, SALIDA);
		tipoDatos = (List<TipoDato>) read(new TipoDato());
		complejo = false;
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararFormularioSimple() {
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		salidas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad, SALIDA);
		tipoDatos = (List<TipoDato>) getSimple();
		formatos = (ArrayList<Formato>) read(new Formato());
		complejo = false;
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararFormularioComplejo() {
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		salidas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad, SALIDA);
		tipoDatos = (List<TipoDato>) getComplex();
		complejo = true;
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararModificarSalidaSimple() {
		salida = (EntradaSalida) read(salida, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		tipoDatos = (List<TipoDato>) getSimple();
		formatos = (ArrayList<Formato>) read(new Formato());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararModificarSalidaCompleja() {
		salida = (EntradaSalida) read(salida, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		tipoDatos = (List<TipoDato>) getComplex();
		return SUCCESS;
	}

	public String registrarSalida() {
		Usuario user = new Usuario();
		session = ActionContext.getContext().getSession();
		user = (Usuario) session.get("usuario");
		salida.setId_funcionalidad(id_funcionalidad);
		salida.setId_usuario(user.getId_usuario());
		salida.setTipo(SALIDA);
		if (id_entrada_salida > 0) {
			salida.setId_padre(id_entrada_salida);
		}
		create(salida);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String modificarSalida() {
		Usuario user = new Usuario();
		session = ActionContext.getContext().getSession();
		user = (Usuario) session.get("usuario");
		EntradaSalida modificada = new EntradaSalida();
		modificada = (EntradaSalida) read(salida, id_entrada_salida);
		modificada.setNombre(salida.getNombre());
		modificada.setDescripcion(salida.getDescripcion());
		modificada.setId_tipo_dato(salida.getId_tipo_dato());
		modificada.setId_formato(salida.getId_formato());
		modificada.setLongitud(salida.getLongitud());
		modificada.setId_usuario(user.getId_usuario());
		update(modificada, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		salidas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad, SALIDA);
		tipoDatos = (List<TipoDato>) read(new TipoDato());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String eliminarSalidaSimple() {
		delete(salida, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		salidas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad, SALIDA);
		if (salidas.size() == 0) {
			servicio.setPublicado(false);
			update(servicio, id_servicio_informacion);
		}
		tipoDatos = (List<TipoDato>) read(new TipoDato());
		return SUCCESS;
	}

	// TODO hay que borrar con le id del usuario, seria bueno que sea
	// directamente del metodo del dao
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String eliminarSalidaCompleja() {
		salidas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad, SALIDA);
		Iterator<EntradaSalida> iterator = salidas.iterator();
		while (iterator.hasNext()) {
			salida = iterator.next();
			if (salida.getId_padre() == id_entrada_salida) {
				delete(salida, salida.getId_entrada_salida());
			}
		}
		delete(salida, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		salidas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad, SALIDA);
		if (salidas.size() == 0) {
			servicio.setPublicado(false);
			update(servicio, id_servicio_informacion);
		}
		tipoDatos = (List<TipoDato>) read(new TipoDato());
		return SUCCESS;
	}

	@Override
	public void validate() {
		if (salida.getNombre().trim().isEmpty())
			addFieldError("salida.nombre",
					error.getProperties().getProperty("error.salida.nombre"));
		if (!salida.getNombre().toUpperCase().matches(REGEX_TITLE))
			addFieldError("salida.nombre",
					error.getProperties().getProperty("error.regex.title"));
		if (salida.getDescripcion().trim().isEmpty())
			addFieldError("salida.descripcion", error.getProperties()
					.getProperty("error.salida.descripcion"));
		if (!salida.getDescripcion().toUpperCase().matches(REGEX_DESCRIPTION))
			addFieldError("salida.descripcion", error.getProperties()
					.getProperty("error.regex.description"));
		if (salida.getId_tipo_dato() == -1) {
			addFieldError("tipodato",
					error.getProperties().getProperty("error.salida.tipodato"));
			salida.setId_formato((long) -1);
			salida.setLongitud("");
		} else {
			TipoDato td = (TipoDato) read(new TipoDato(),
					salida.getId_tipo_dato());
			if (td.isHasformatted()) {
				if (salida.getId_formato() == -1) {
					addFieldError(
							"formato",
							error.getProperties().getProperty(
									"error.salida.format"));
				} else {
					Formato f = (Formato) read(new Formato(),
							salida.getId_formato());
					if (f.getId_tipo_dato() != salida.getId_tipo_dato())
						addFieldError("formato", error.getProperties()
								.getProperty("error.salida.format"));
				}
			} else {
				salida.setId_formato((long) -1);
			}
			if (td.isHasLength()) {
				if (!salida.getLongitud().isEmpty()) {
					if (salida.getId_tipo_dato() == 4) {
						try {
							float num = Float.parseFloat(salida.getLongitud()
									.toString());
							if (num <= 0) {
								addFieldError("longitud", error.getProperties()
										.getProperty("error.salida.longitud"));
							}
						} catch (Exception e) {
							addFieldError("longitud", error.getProperties()
									.getProperty("error.salida.longitud"));
						}
					} else {
						try {
							Long num = Long.parseLong(salida.getLongitud()
									.toString());
							if (num <= 0) {
								addFieldError("longitud", error.getProperties()
										.getProperty("error.salida.longitud"));
							}
						} catch (Exception e) {
							addFieldError("longitud", error.getProperties()
									.getProperty("error.salida.longitud"));
						}
					}
				} else {
					addFieldError("longitud", error.getProperties()
							.getProperty("error.salida.digit"));
				}
			} else {
				salida.setLongitud(error.getProperties().getProperty(
						"error.length"));
			}
		}
		if (read(ESF, id_funcionalidad, salida.getNombre()) && !modificar) {
			addFieldError("salida.nombre",
					error.getProperties()
							.getProperty("error.salida.duplicated"));
		}
		if (complejo) {
			prepararFormularioComplejo();
		} else {
			prepararFormularioSimple();
		}
	}

	public List<EntradaSalida> getSalidas() {
		return salidas;
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

	public EntradaSalida getSalida() {
		return salida;
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

	public void setSalidas(List<EntradaSalida> salidas) {
		this.salidas = salidas;
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

	public void setSalida(EntradaSalida salida) {
		this.salida = salida;
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

}
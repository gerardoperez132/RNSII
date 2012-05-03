package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.Constants.Formulario;
import ve.gob.cnti.srsi.dao.Constants.Modelos;
import ve.gob.cnti.srsi.dao.Constants.TipoEntradaSalida;
import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Formato;
import ve.gob.cnti.srsi.modelo.Funcionalidad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.TipoDato;

@SuppressWarnings("serial")
public class SalidaControlador extends DAO implements Formulario,
		TipoEntradaSalida, Modelos {

	private List<EntradaSalida> salidas;
	private List<TipoDato> tipoDatos;
	private List<Formato> formatos;

	private ServicioInformacion servicio = new ServicioInformacion();
	private Funcionalidad funcionalidad = new Funcionalidad();
	private EntradaSalida salida = new EntradaSalida();

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
		salida.setId_funcionalidad(id_funcionalidad);
		salida.setTipo(SALIDA);
		if (id_entrada_salida > 0) {
			salida.setId_padre(id_entrada_salida);
		}
		create(salida);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String modificarSalida() {
		EntradaSalida modificada = new EntradaSalida();
		modificada = (EntradaSalida) read(salida, id_entrada_salida);
		modificada.setNombre(salida.getNombre());
		modificada.setDescripcion(salida.getDescripcion());
		modificada.setId_tipo_dato(salida.getId_tipo_dato());
		update(modificada, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		salidas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad, SALIDA);
		tipoDatos = (List<TipoDato>) read(new TipoDato());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String eliminarEntradaSimple() {
		delete(salida, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		salidas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad, SALIDA);
		tipoDatos = (List<TipoDato>) read(new TipoDato());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String eliminarEntradaCompleja() {
		salidas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad, SALIDA);
		Iterator<EntradaSalida> iterator = salidas.iterator();
		while (iterator.hasNext()) {
			salida = iterator.next();
			if (salida.getId_padre() == id_entrada_salida)
				delete(salida, salida.getId_entrada_salida());
		}
		delete(salida, id_entrada_salida);
		funcionalidad = (Funcionalidad) read(funcionalidad, id_funcionalidad);
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		salidas = (ArrayList<EntradaSalida>) read(ESF, id_funcionalidad, SALIDA);
		tipoDatos = (List<TipoDato>) read(new TipoDato());
		return SUCCESS;
	}

	@Override
	public void validate() {
		if (salida.getNombre().isEmpty())
			addFieldError("salida.nombre",
					"Debe introducir un nombre que identifique el dato");
		if (salida.getDescripcion().isEmpty())
			addFieldError("salida.descripcion",
					"Debe introducir una descripción.");
		if (salida.getId_tipo_dato() == -1) {
			addFieldError("tipodato", "Debe seleccionar un tipo de dato");
			salida.setId_formato((long) -1);
			salida.setLongitud("");
		} else {
			TipoDato td = (TipoDato) read(new TipoDato(),
					salida.getId_tipo_dato());
			if (td.isHasformatted()) {
				if (salida.getId_formato() == -1) {
					addFieldError("formato",
							"Debe seleccionar un tipo formato "
									+ "que corresponda con el dato elegido");
				} else {
					Formato f = (Formato) read(new Formato(),
							salida.getId_formato());
					if (f.getId_tipo_dato() != salida.getId_tipo_dato())
						addFieldError("formato",
								"Debe seleccionar un tipo formato "
										+ "que corresponda con el dato elegido");
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
								addFieldError("longitud",
										"Exprese la longitud sólo con números positivos mayores que cero");
							}
						} catch (Exception e) {
							addFieldError("longitud",
									"Exprese la longitud sólo con números");
						}
					} else {
						try {
							Long num = Long.parseLong(salida.getLongitud()
									.toString());
							if (num <= 0) {
								addFieldError("longitud",
										"Exprese la longitud sólo con números positivos mayores que cero");
							}
						} catch (Exception e) {
							addFieldError("longitud",
									"Exprese la longitud sólo con números");
						}
					}
				} else {
					addFieldError("longitud",
							"Debe indicar la cantidad de digitos que acepta el dato");
				}
			} else {
				salida.setLongitud("No aplica");
			}
		}

		if (read(ESF, id_funcionalidad, salida.getNombre()) && !modificar) {
			addFieldError("salida.nombre",
					"Nombre de salida duplicado, cambie el nombre por favor");
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
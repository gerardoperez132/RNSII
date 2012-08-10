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
import ve.gob.cnti.srsi.dao.Constants.Modelos;
import ve.gob.cnti.srsi.dao.Constants.Order;
import ve.gob.cnti.srsi.dao.Constants.Sentencias;
import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Ente;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.SolicitudSuscripcion;
import ve.gob.cnti.srsi.modelo.Suscrito;
import ve.gob.cnti.srsi.modelo.Usuario;
import ve.gob.cnti.srsi.util.EstadosTiempo;
import ve.gob.cnti.srsi.util.ReadXmlTime;
import ve.gob.cnti.srsi.util.SubscriptionRequest;
import ve.gob.cnti.srsi.util.SubscriptionResponse;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class SuscripcionControlador extends DAO implements Constants, Order,
		Modelos, Sentencias {

	private List<SubscriptionRequest> solicitudes = new ArrayList<SubscriptionRequest>();
	private List<SubscriptionResponse> solicitudesRespondidas = new ArrayList<SubscriptionResponse>();

	private String codigo;
	private String codigos[] = CODES;
	private ServicioInformacion servicio = new ServicioInformacion();
	private Ente ente = new Ente();
	private SolicitudSuscripcion solicitud = new SolicitudSuscripcion();
	@SuppressWarnings("rawtypes")
	private Map session;

	/** Identificador del servicio de información. */
	private long id_servicio;
	private long id_solicitud_suscripcion;
	private boolean suscripcion_form;
	private boolean invalid;
	private boolean requested;
	private boolean ListarSuscricionesPendientes;
	private boolean detalles_solicitud;
	private boolean detalles_respuesta;
	private boolean aprobarRechasar;
	private boolean ListarSuscricionesAceptadasRechazadas;
	private boolean solicitarSuscripcion;
	private String sentencia[] = { "Aceptado", "Rechazado" };
	private List<EstadosTiempo> estadosTiempo = new ArrayList<EstadosTiempo>();
	private Date fecha;

	@SkipValidation
	public String prepararSuscripcion() {
		getTiempoFecha();
		if (!sessionValidate())
			return INPUT;
		if (!verificarLong(id_servicio))
			return INPUT;
		servicio = (ServicioInformacion) read(servicio, id_servicio);
		suscripcion_form = true;
		prepareRequest();
		if (read(solicitud).size() > 0)
			if (verifySuscriptionRequest(
					solicitud.getId_servicio_informacion(),
					solicitud.getId_ente_proveedor(),
					solicitud.getId_ente_solicitante())) {
				addFieldError(
						"error",
						error.getProperties()
								.getProperty("error.suscripcion.duplicated")
								.replace("{0}", ente.getSiglas().toUpperCase()));
				id_solicitud_suscripcion = getId_solicitud_sucripcion(
						solicitud.getId_servicio_informacion(),
						solicitud.getId_ente_proveedor(),
						solicitud.getId_ente_solicitante());
				setInvalid(true);
				setRequested(true);
			}
		return SUCCESS;
	}

	// TODO Mandar notificación por correo al ente proveedor. Un mensaje
	// cualquier para ir probando...
	public String solicitarSuscripcion() {
		getTiempoFecha();
		if (!sessionValidate())
			return INPUT;
		prepareRequest();
		solicitud.setTelefono(codigo + solicitud.getTelefono());
		create(solicitud);
		setSuscripcion_form(false);
		addActionMessage("Su solicitud de suscripción ha sido procesada.");
		System.out.println(solicitud.toString());
		return SUCCESS;
	}

	private void prepareRequest() {
		getTiempoFecha();
		session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usuario");
		ente = (Ente) session.get("ente");
		solicitud.setId_ente_proveedor(((ServicioInformacion) read(servicio,
				id_servicio)).getId_ente());
		solicitud.setId_ente_solicitante(user.getId_ente());
		solicitud.setId_servicio_informacion(id_servicio);
		solicitud.setId_usuario(user.getId_usuario());
		solicitud.setSentencia(PENDIENTE);
	}

	@Override
	public void validate() {
		getTiempoFecha();
		if (!isRequested()) {
			if (solicitud.getSolicitante().trim().isEmpty()) {
				addFieldError(
						"solicitante",
						error.getProperties().getProperty(
								"error.suscripcion.solicitante"));
				setInvalid(true);
			}
			if (!solicitud.getSolicitante().toUpperCase().matches(REGEX_TITLE)) {
				addFieldError("solicitante",
						error.getProperties().getProperty("error.regex.title"));
				setInvalid(true);
			}
			if (solicitud.getCargo().trim().isEmpty()) {
				addFieldError(
						"cargo",
						error.getProperties().getProperty(
								"error.suscripcion.cargo"));
				setInvalid(true);
			}
			if (!solicitud.getCargo().toUpperCase().matches(REGEX_TITLE)) {
				addFieldError("cargo",
						error.getProperties().getProperty("error.regex.title"));
				setInvalid(true);
			}
			if (solicitud.getCorreo().trim().isEmpty()) {
				addFieldError(
						"correo",
						error.getProperties().getProperty(
								"error.suscripcion.email"));
				setInvalid(true);
			}
			if (!solicitud.getCorreo().matches(REGEX_EMAIL)) {
				addFieldError("correo",
						error.getProperties().getProperty("error.regex.email"));
				setInvalid(true);
			}
			if (solicitud.getTelefono().trim().isEmpty()) {
				addFieldError(
						"telefono",
						error.getProperties().getProperty(
								"error.suscripcion.telefono"));
				setInvalid(true);
			}
			if (solicitud.getTelefono().length() > 0
					&& solicitud.getTelefono().length() < 7) {
				addFieldError(
						"telefono",
						error.getProperties().getProperty(
								"error.suscripcion.telefono.digit"));
				setInvalid(true);
			}
			if (!solicitud.getTelefono().matches("\\d.*")
					&& !solicitud.getTelefono().trim().isEmpty()) {
				addFieldError(
						"telefono",
						error.getProperties().getProperty(
								"error.suscripcion.telefono.regex"));
				setInvalid(true);
			}
			if (solicitud.getMotivo_solicitante().trim().isEmpty()) {
				addFieldError(
						"motivo",
						error.getProperties().getProperty(
								"error.suscripcion.motivo"));
				setInvalid(true);
			}
			if (!solicitud.getMotivo_solicitante().matches(REGEX_DESCRIPTION)) {
				addFieldError(
						"motivo",
						error.getProperties().getProperty(
								"error.regex.description"));
				setInvalid(true);
			}
		}
		if (isInvalid())
			prepararSuscripcion();
		else
			solicitarSuscripcion();
	}

	public boolean verificarLong(long n) {
		try {
			return n != 0;
		} catch (Exception e) {
			return false;
		}
	}

	@SkipValidation
	public String listarSolicitudesAceptadasRechazadas() {
		getTiempoFecha();
		if (!sessionValidate())
			return INPUT;
		// Lista solicitudes en base a las no leidas, pendientes,
		session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usuario");
		setSolicitudesRespondidas((List<SubscriptionResponse>) getlistaSolicitudesAceptadasRechazadas(
				user.getId_ente(), ASC));
		ListarSuscricionesAceptadasRechazadas = true;
		return SUCCESS;
	}

	@SkipValidation
	public String listaSuscripcionesPendientes() {
		getTiempoFecha();
		if (!sessionValidate())
			return INPUT;
		// Lista solicitudes en base a las no leidas, pendientes,
		session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usuario");
		solicitudes = (List<SubscriptionRequest>) getSolicitudesSuscripcionPendientes(
				user.getId_ente(), ASC);
		ListarSuscricionesPendientes = true;
		return SUCCESS;
	}

	@SkipValidation
	public String examinarSolicitud() {
		getTiempoFecha();
		if (!sessionValidate())
			return INPUT;
		session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usuario");
		solicitud = (SolicitudSuscripcion) read(solicitud,
				id_solicitud_suscripcion);
		// Valida que un trol quiera acceder a las solicitudes de otros entes
		if (!detalles_respuesta) {
			if (user.getId_ente() != solicitud.getId_ente_proveedor())
				return INPUT;
		} else {
			if (user.getId_ente() != solicitud.getId_ente_solicitante())
				return INPUT;
		}
		// Guardo que la solicitud ya ha sido revisada y por quien fue leida
		if (!solicitud.isLeido()) {
			solicitud.setLeido(true);
			solicitud.setId_usuario(user.getId_usuario());
			update(solicitud, id_solicitud_suscripcion);
		}
		if (!detalles_respuesta) {
			detalles_solicitud = true;
			ente = (Ente) read(ente, solicitud.getId_ente_solicitante());
		} else {
			ente = (Ente) read(ente, solicitud.getId_ente_proveedor());
		}
		servicio = (ServicioInformacion) read(servicio,
				solicitud.getId_servicio_informacion());
		return SUCCESS;
	}

	@SkipValidation
	public String preparar_AprobarRechasarSuscripcion() {
		getTiempoFecha();
		if (!sessionValidate())
			return INPUT;
		// Lee los detalles de la suscripción
		session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usuario");
		solicitud = (SolicitudSuscripcion) read(solicitud,
				id_solicitud_suscripcion);
		// Valida que un trol quiera acceder a las solicitudes de otros entes
		if (user.getId_ente() != solicitud.getId_ente_proveedor())
			return INPUT;
		// Valida que la solicitud.sentencia sea igual a cero (0 = pendiente).
		if (!(solicitud.getSentencia() == 0))
			return INPUT;
		ente = (Ente) read(ente, solicitud.getId_ente_solicitante());
		servicio = (ServicioInformacion) read(servicio,
				solicitud.getId_servicio_informacion());
		// Variable necesaria para la vista.
		aprobarRechasar = true;
		return SUCCESS;
	}

	// TODO Enviar notificación por correo, falta colocar el mensaje registro
	// exitoso para la vista
	@SkipValidation
	public String AprobarRechasarSuscripcion() {
		getTiempoFecha();
		if (!sessionValidate())
			return INPUT;
		boolean err = false;
		String motivo_proveedor;
		int decision;
		// validar datos y guardar
		motivo_proveedor = solicitud.getMotivo_proveedor();
		decision = solicitud.getSentencia();
		session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usuario");
		if (solicitud.getMotivo_proveedor().trim().isEmpty()) {
			addFieldError("motivo_proveedor", error.getProperties()
					.getProperty("error.suscripcion.motivo_proveedor"));
			err = true;
		} else if (!solicitud.getMotivo_proveedor().toUpperCase()
				.matches(REGEX_TITLE)) {
			addFieldError("motivo_proveedor", error.getProperties()
					.getProperty("error.regex.title"));
			err = true;
		}
		if (!(solicitud.getSentencia() >= 1 && solicitud.getSentencia() <= 2)) {
			addFieldError(
					"sentencia",
					error.getProperties().getProperty(
							"error.suscripcion.sentencia"));
			err = true;
		}
		solicitud = (SolicitudSuscripcion) read(solicitud,
				id_solicitud_suscripcion);
		solicitud.setSentencia(decision);
		solicitud.setMotivo_proveedor(motivo_proveedor);
		ente = (Ente) read(ente, solicitud.getId_ente_solicitante());
		servicio = (ServicioInformacion) read(servicio,
				solicitud.getId_servicio_informacion());
		if (err)
			return INPUT;
		// Valida que un trol quiera acceder a las solicitudes de otros entes
		if (user.getId_ente() != solicitud.getId_ente_proveedor())
			return INPUT;
		// Valida que la solicitud.sentencia sea igual a cero (0 = pendiente).
		if (solicitud.getSentencia() == 0)
			return INPUT;
		// Si la desición es ACEPTADO se crea el registro suscrito
		if (decision == 1) {
			Suscrito suscrito = new Suscrito();
			suscrito.setId_ente(solicitud.getId_ente_solicitante());
			suscrito.setId_servicio_informacion(solicitud
					.getId_servicio_informacion());
			create(suscrito);
		}
		// Coloco a false leido para utilizarlo con el ente solicitante, en las
		// notificaciones.
		solicitud.setLeido(false);
		// Actualiza la solicitud de suscripción
		update(solicitud, solicitud.getId_solicitud_suscripcion());
		addActionMessage("EL VEREDICTO DE LA SOLICITUD DE SUSCRIPCIÓN HA SIDO PROCESADA CORRECTAMENTE");
		aprobarRechasar = false;
		return SUCCESS;
	}

	// TODO Hay que listar los sectores y los servicios publicados
	@SkipValidation
	public String prepararSolicitarSuscripcion() {
		getTiempoFecha();
		if (!sessionValidate())
			return INPUT;
		// variable para la vista.
		solicitarSuscripcion = true;
		return SUCCESS;
	}

	// Valida que la sesión este activa
	@SkipValidation
	public boolean sessionValidate() {
		session = ActionContext.getContext().getSession();
		Usuario usuario = new Usuario();
		usuario = (Usuario) session.get("usuario");
		if (usuario == null) {
			return false;
		}
		return true;
	}

	public void getTiempoFecha() {
		ReadXmlTime read = new ReadXmlTime();
		fecha = read.getFechaTiempo();
		estadosTiempo = read.getEstadosTiempo();
	}

	public ServicioInformacion getServicio() {
		return servicio;
	}

	public void setServicio(ServicioInformacion servicio) {
		this.servicio = servicio;
	}

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public boolean isSuscripcion_form() {
		return suscripcion_form;
	}

	public void setSuscripcion_form(boolean suscripcion_form) {
		this.suscripcion_form = suscripcion_form;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String[] getCodigos() {
		return codigos;
	}

	public void setCodigos(String[] codigos) {
		this.codigos = codigos;
	}

	public SolicitudSuscripcion getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudSuscripcion solicitud) {
		this.solicitud = solicitud;
	}

	public long getid_servicio() {
		return id_servicio;
	}

	public void setid_servicio(long id_servicio) {
		this.id_servicio = id_servicio;
	}

	public long getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(long id_servicio) {
		this.id_servicio = id_servicio;
	}

	public boolean isInvalid() {
		return invalid;
	}

	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}

	public boolean isRequested() {
		return requested;
	}

	public void setRequested(boolean requested) {
		this.requested = requested;
	}

	public boolean isListarSuscricionesPendientes() {
		return ListarSuscricionesPendientes;
	}

	public void setListarSuscricionesPendientes(
			boolean listarSuscricionesPendientes) {
		ListarSuscricionesPendientes = listarSuscricionesPendientes;
	}

	public List<SubscriptionRequest> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<SubscriptionRequest> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public boolean isDetalles_solicitud() {
		return detalles_solicitud;
	}

	public void setDetalles_solicitud(boolean detalles_solicitud) {
		this.detalles_solicitud = detalles_solicitud;
	}

	public long getId_solicitud_suscripcion() {
		return id_solicitud_suscripcion;
	}

	public void setId_solicitud_suscripcion(long id_solicitud_suscripcion) {
		this.id_solicitud_suscripcion = id_solicitud_suscripcion;
	}

	public boolean isAprobarRechasar() {
		return aprobarRechasar;
	}

	public void setAprobarRechasar(boolean aprobarRechasar) {
		this.aprobarRechasar = aprobarRechasar;
	}

	public String[] getSentencia() {
		return sentencia;
	}

	public void setSentencia(String sentencia[]) {
		this.sentencia = sentencia;
	}

	public boolean isListarSuscricionesAceptadasRechazadas() {
		return ListarSuscricionesAceptadasRechazadas;
	}

	public void setListarSuscricionesAceptadasRechazadas(
			boolean listarSuscricionesAceptadasRechazadas) {
		ListarSuscricionesAceptadasRechazadas = listarSuscricionesAceptadasRechazadas;
	}

	public List<SubscriptionResponse> getSolicitudesRespondidas() {
		return solicitudesRespondidas;
	}

	public void setSolicitudesRespondidas(
			List<SubscriptionResponse> solicitudesRespondidas) {
		this.solicitudesRespondidas = solicitudesRespondidas;
	}

	public boolean isDetalles_respuesta() {
		return detalles_respuesta;
	}

	public void setDetalles_respuesta(boolean detalles_respuesta) {
		this.detalles_respuesta = detalles_respuesta;
	}

	public boolean isSolicitarSuscripcion() {
		return solicitarSuscripcion;
	}

	public void setSolicitarSuscripcion(boolean solicitarSuscripcion) {
		this.solicitarSuscripcion = solicitarSuscripcion;
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
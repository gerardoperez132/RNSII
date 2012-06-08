package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.modelo.temporales.Solicitud_Suscripcion;
import ve.gob.cnti.srsi.dao.Constants;
import ve.gob.cnti.srsi.dao.Constants.Modelos;
import ve.gob.cnti.srsi.dao.Constants.Order;
import ve.gob.cnti.srsi.dao.Constants.Sentencias;
import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Ente;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.SolicitudSuscripcion;
import ve.gob.cnti.srsi.modelo.Usuario;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class SuscripcionControlador extends DAO implements Constants, Order,
		Modelos, Sentencias {
	
	private List<Solicitud_Suscripcion> solicitudes = new ArrayList<Solicitud_Suscripcion>();

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
	private boolean ListarSuscricionesPendientes;
	private boolean detalles_solicitud;

	@SkipValidation
	public String prepararSuscripcion() {
		if (!verificarLong(id_servicio))
			return INPUT;
		servicio = (ServicioInformacion) read(servicio, id_servicio);
		ente = (Ente) read(ente, servicio.getId_ente());
		suscripcion_form = true;
		prepareRequest();
		if (read(solicitud).size() > 0)
			if (verifySuscriptionRequest(
					solicitud.getId_servicio_informacion(),
					solicitud.getId_ente_proveedor(),
					solicitud.getId_ente_solicitante())) {
				addFieldError(
						"error",
						"El "
								+ ente.getSiglas().toUpperCase()
								+ " ya ha solicitado la suscripción a este servicio de información");
				setInvalid(true);
			}
		return SUCCESS;
	}

	// TODO Mandar notificación por correo al ente proveedor. Un mensaje
	// cualquier para ir probando...
	public String solicitarSuscripcion() {
		prepareRequest();
		solicitud.setTelefono(codigo + solicitud.getTelefono());
		create(solicitud);
		System.out.println(solicitud.toString());
		return SUCCESS;
	}

	private void prepareRequest() {
		session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usuario");
		solicitud.setId_ente_proveedor(((ServicioInformacion) read(servicio,
				id_servicio)).getId_ente());
		solicitud.setId_ente_solicitante(user.getId_ente());
		solicitud.setId_servicio_informacion(id_servicio);
		solicitud.setId_usuario(user.getId_usuario());
		solicitud.setSentencia(PENDIENTE);
	}

	@Override
	public void validate() {
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
			addFieldError("cargo",
					error.getProperties()
							.getProperty("error.suscripcion.cargo"));
			setInvalid(true);
		}
		if (!solicitud.getCargo().toUpperCase().matches(REGEX_TITLE)) {
			addFieldError("cargo",
					error.getProperties().getProperty("error.regex.title"));
			setInvalid(true);
		}
		if (solicitud.getCorreo().trim().isEmpty()) {
			addFieldError("correo",
					error.getProperties()
							.getProperty("error.suscripcion.email"));
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
		if (solicitud.getMotivo().trim().isEmpty()) {
			addFieldError(
					"motivo",
					error.getProperties().getProperty(
							"error.suscripcion.motivo"));
			setInvalid(true);
		}
		if (!solicitud.getMotivo().toUpperCase().matches(REGEX_DESCRIPTION)) {
			addFieldError("motivo",
					error.getProperties()
							.getProperty("error.regex.description"));
			setInvalid(true);
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
	public String listaSuscripcionesPendientes() {
		//Lista solicitudes en base a las no leidas, pendientes,
		session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usuario");
		solicitudes = (List<Solicitud_Suscripcion>) getSolicitudesSuscripcionPendientes(user.getId_ente(),ASC);
		ListarSuscricionesPendientes = true;
		return SUCCESS;
	}
	
	//TODO examinar solicitud examinarSolicitud, poner a true leido
	@SkipValidation
	public String examinarSolicitud() {		
		session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usuario");				
		solicitud = (SolicitudSuscripcion) read(solicitud, id_solicitud_suscripcion);
		//Valida que un trol quiera acceder a las solicitudes de otros entes	
		if(user.getId_ente() != solicitud.getId_ente_proveedor())
			return INPUT;
		//Guardo que la solicitud ya ha sido revisada y por quien fue leida
		if(!solicitud.isLeido()){
			solicitud.setLeido(true);
			solicitud.setId_usuario(user.getId_usuario());
			update(solicitud, id_solicitud_suscripcion);
		}
		ente = (Ente) read(ente, solicitud.getId_ente_solicitante());
		servicio = (ServicioInformacion) read(servicio,solicitud.getId_servicio_informacion());
		detalles_solicitud = true;
		return SUCCESS;
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

	public boolean isListarSuscricionesPendientes() {
		return ListarSuscricionesPendientes;
	}

	public void setListarSuscricionesPendientes(boolean listarSuscricionesPendientes) {
		ListarSuscricionesPendientes = listarSuscricionesPendientes;
	}

	public List<Solicitud_Suscripcion> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<Solicitud_Suscripcion> solicitudes) {
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
}


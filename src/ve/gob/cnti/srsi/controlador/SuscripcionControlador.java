package ve.gob.cnti.srsi.controlador;

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
import ve.gob.cnti.srsi.modelo.Usuario;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class SuscripcionControlador extends DAO implements Constants, Order,
		Modelos, Sentencias {

	private String codigo;
	private String codigos[] = CODES;
	private ServicioInformacion servicio = new ServicioInformacion();
	private Ente ente = new Ente();
	private SolicitudSuscripcion solicitud = new SolicitudSuscripcion();
	private Map session;

	private long id_servicio;
	private boolean suscripcion_form;

	@SkipValidation
	public String prepararSuscripcion() {
		if (!verificarLong(id_servicio))
			return INPUT;
		servicio = (ServicioInformacion) read(servicio, id_servicio);
		ente = (Ente) read(ente, servicio.getId_ente());
		suscripcion_form = true;
		return SUCCESS;
	}

	// TODO Mandar notificación por correo al ente proveedor. Un mensaje
	// cualquier para ir probando...
	public String solicitarSuscripcion() {
		session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usuario");
		solicitud.setId_ente_proveedor(((ServicioInformacion) read(servicio,
				id_servicio)).getId_ente());
		solicitud.setId_ente_solicitante(user.getId_ente());
		solicitud.setId_servicio_informacion(id_servicio);
		solicitud.setId_usuario(user.getId_usuario());
		solicitud.setSentencia(PENDIENTE);
		System.out.println(solicitud.toString());
		create(solicitud);
		return SUCCESS;
	}

	@Override
	public void validate() {
		if (solicitud.getSolicitante().trim().isEmpty())
			addFieldError("solicitante",
					"El nombre no puede estar vacío, no sea animal");
		prepararSuscripcion();
	}

	public boolean verificarLong(long n) {
		try {
			return n != 0;
		} catch (Exception e) {
			return false;
		}
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

}

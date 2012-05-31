package ve.gob.cnti.srsi.controlador;

import ve.gob.cnti.srsi.dao.Constants;
import ve.gob.cnti.srsi.dao.Constants.Modelos;
import ve.gob.cnti.srsi.dao.Constants.Order;
import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Ente;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;

@SuppressWarnings("serial")
public class SuscripcionControlador extends DAO implements Constants, Order,
		Modelos {

	private String codigo;
	private String codigos[] = CODES;
	private ServicioInformacion servicio = new ServicioInformacion();
	private Ente ente = new Ente();

	private long id_servicio;
	private boolean suscripcion_form;

	public String prepararSuscripcion() {
		if (!verificarLong(id_servicio))
			return INPUT;
		servicio = (ServicioInformacion) read(servicio, id_servicio);
		ente = (Ente) read(ente, servicio.getId_ente());
		suscripcion_form = true;
		return SUCCESS;
	}

	public String solicitarSuscripcion() {
		return SUCCESS;
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

	public long getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(long id_servicio) {
		this.id_servicio = id_servicio;
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

}

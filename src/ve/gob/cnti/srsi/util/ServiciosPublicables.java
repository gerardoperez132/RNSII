package ve.gob.cnti.srsi.util;

import java.util.List;

import ve.gob.cnti.srsi.modelo.ServicioInformacion;

public class ServiciosPublicables {
	private List<String> incompletos;
	boolean publicable;
	ServicioInformacion servicio = new ServicioInformacion();

	public ServiciosPublicables(boolean publicable,
			ServicioInformacion servicio, List<String> incompletos) {
		super();
		this.publicable = publicable;
		this.servicio = servicio;
		this.incompletos = incompletos;
	}

	public boolean isPublicable() {
		return publicable;
	}

	public void setPublicable(boolean publicable) {
		this.publicable = publicable;
	}

	public ServicioInformacion getServicio() {
		return servicio;
	}

	public void setServicio(ServicioInformacion servicio) {
		this.servicio = servicio;
	}

	public List<String> getIncompletos() {
		return incompletos;
	}

	public void setIncompletos(List<String> incompletos) {
		this.incompletos = incompletos;
	}
}
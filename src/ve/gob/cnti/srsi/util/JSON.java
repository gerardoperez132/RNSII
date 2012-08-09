package ve.gob.cnti.srsi.util;

import ve.gob.cnti.srsi.i18n.Errors;

import com.opensymphony.xwork2.Action;

public class JSON {
	private String busqueda;
	private String respuesta;
	private Errors error = new Errors();

	public JSON() {
		System.out.println("BÚSQUEDA => " + busqueda);
		respuesta = error.getProperties().getProperty("error.num");
		System.out.println("RESPUESTA => " + respuesta);
	}

	public String execute() {
		return Action.SUCCESS;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}
}
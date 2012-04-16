package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.Constants;
import ve.gob.cnti.srsi.dao.Constants.Tabs;
import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Area;
import ve.gob.cnti.srsi.modelo.Arquitectura;
import ve.gob.cnti.srsi.modelo.Ente;
import ve.gob.cnti.srsi.modelo.Estado;
import ve.gob.cnti.srsi.modelo.Intercambio;
import ve.gob.cnti.srsi.modelo.Sector;
import ve.gob.cnti.srsi.modelo.Seguridad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.UnionAreaServicioInformacion;
import ve.gob.cnti.srsi.modelo.UnionArquitecturaServicioInformacion;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class ServicioInformacionControlador extends DAO implements Constants,
		Tabs {
	private List<Sector> sectores = new ArrayList<Sector>();
	private List<Estado> estados = new ArrayList<Estado>();
	private List<Area> areas = new ArrayList<Area>();
	private List<Seguridad> niveles = new ArrayList<Seguridad>();
	private List<Arquitectura> arquitecturas = new ArrayList<Arquitectura>();
	private List<Intercambio> parents = new ArrayList<Intercambio>();
	private List<Intercambio> children = new ArrayList<Intercambio>();

	private List<UnionAreaServicioInformacion> unionareas = new ArrayList<UnionAreaServicioInformacion>();
	private List<UnionArquitecturaServicioInformacion> unionarquitecturas = new ArrayList<UnionArquitecturaServicioInformacion>();

	private boolean modificar = false;
	private int tab;
	private long sector;
	private long estado;
	private List<Long> area = new ArrayList<Long>();
	private long seguridad;
	private List<Long> arquitectura = new ArrayList<Long>();
	private long intercambio;
	private String telefono;
	private String correo;
	private String codigo;
	private String codigos[] = COD;

	private HttpServletRequest servletRequest;
	private Ente ente;
	private Map session;
	private ServicioInformacion servicio = new ServicioInformacion();

	public List<Sector> getSectores() {
		return sectores;
	}

	public void setSectores(List<Sector> sectores) {
		this.sectores = sectores;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public List<Seguridad> getNiveles() {
		return niveles;
	}

	public void setNiveles(List<Seguridad> niveles) {
		this.niveles = niveles;
	}

	public List<Arquitectura> getArquitecturas() {
		return arquitecturas;
	}

	public void setArquitecturas(List<Arquitectura> arquitecturas) {
		this.arquitecturas = arquitecturas;
	}

	public List<Intercambio> getParents() {
		return parents;
	}

	public void setParents(List<Intercambio> parents) {
		this.parents = parents;
	}

	public List<Intercambio> getChildren() {
		return children;
	}

	public void setChildren(List<Intercambio> children) {
		this.children = children;
	}

	public List<UnionAreaServicioInformacion> getUnionareas() {
		return unionareas;
	}

	public void setUnionareas(List<UnionAreaServicioInformacion> unionareas) {
		this.unionareas = unionareas;
	}

	public List<UnionArquitecturaServicioInformacion> getUnionarquitecturas() {
		return unionarquitecturas;
	}

	public void setUnionarquitecturas(
			List<UnionArquitecturaServicioInformacion> unionarquitecturas) {
		this.unionarquitecturas = unionarquitecturas;
	}

	public boolean isModificar() {
		return modificar;
	}

	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}

	public int getTab() {
		return tab;
	}

	public void setTab(int tab) {
		this.tab = tab;
	}

	public long getSector() {
		return sector;
	}

	public void setSector(long sector) {
		this.sector = sector;
	}

	public long getEstado() {
		return estado;
	}

	public void setEstado(long estado) {
		this.estado = estado;
	}

	public List<Long> getArea() {
		return area;
	}

	public void setArea(List<Long> area) {
		this.area = area;
	}

	public long getSeguridad() {
		return seguridad;
	}

	public void setSeguridad(long seguridad) {
		this.seguridad = seguridad;
	}

	public List<Long> getArquitectura() {
		return arquitectura;
	}

	public void setArquitectura(List<Long> arquitectura) {
		this.arquitectura = arquitectura;
	}

	public long getIntercambio() {
		return intercambio;
	}

	public void setIntercambio(long intercambio) {
		this.intercambio = intercambio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public ServicioInformacion getServicio() {
		return servicio;
	}

	public void setServicio(ServicioInformacion servicio) {
		this.servicio = servicio;
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

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararDescripcionGeneral() {
		sessionStack();
		tab = DESCRIPCION_GENERAL;
		System.out.println("Estoy en prepararDescripcionGeneral");
		sectores = (List<Sector>) read(new Sector());
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		System.out.println("Servicio en Preparar Descripción General=> "
				+ servicio.toString());
		return SUCCESS;
	}

	@SkipValidation
	public String prepararAspectosLegales() {
		// TODO La tablita esa.
		sessionStack();
		System.out.println("Estoy en prepararAspectosLegales");
		System.out.println("Servicio en Preparar Aspectos Legales => "
				+ servicio.toString());
		tab = ASPECTOS_LEGALES;
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararDescripcionTecnica() {
		tab = DESCRIPCION_TECNICA;
		System.out.println("Servicio en Preparar Descripción Técnica => "
				+ servicio.toString());
		niveles = (List<Seguridad>) read(new Seguridad());
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		parents = (List<Intercambio>) getParents(new Intercambio());
		children = (List<Intercambio>) getChildren(new Intercambio());
		return SUCCESS;
	}

	@SkipValidation
	public String prepararDescripcionSoporte() {
		// TODO Falta.
		tab = DESCRIPCION_SOPORTE;
		return SUCCESS;
	}

	public String registrarDescripcionGeneral() {
		sessionStack();
		System.out.println("Estoy en registrarDescripcionGeneral");
		setModificar(servicio != null);
		System.out.println("SERVICIO => " + servicio.toString() + "\nMODI => "
				+ isModificar());
		// create(servicio);
		return SUCCESS;
	}

	public String registrarAspectosLegales() {
		// update(servicio, servicio.getId());
		return SUCCESS;
	}

	public String registrarDescripcionTecnica() {
		// update(servicio, servicio.getId());
		return SUCCESS;
	}

	public String registrarDescripcionSoporte() {
		// update(servicio, servicio.getId());
		return SUCCESS;
	}

	@Override
	public void validate() {
		switch (tab) {
		case DESCRIPCION_GENERAL:
			if (sector < 0)
				addFieldError("sector", "Debe seleccionar un sector");
			if (servicio.getNombre().trim().isEmpty())
				addFieldError("servicio.nombre", "Debe introducir un nombre");
			if (servicio.getDescripcion().trim().isEmpty())
				addFieldError("servicio.descripcion",
						"Debe introducir una descripción");
			if (area.size() == 0)
				addFieldError("area",
						"Debe seleccionar un área a la que está orientado el servicio");
			if (estado < 0)
				addFieldError("estado",
						"Debe seleccionar el estado del servicio");
			System.out.println("Estoy en el validate()");
			prepararDescripcionGeneral();
			break;
		case ASPECTOS_LEGALES:
			System.out.println("Validando ASPECTOS_LEGALES");
			break;
		case DESCRIPCION_TECNICA:
			if (seguridad < 0)
				addFieldError("seguridad",
						"Debe seleccionar un nivel de seguridad");
			if (arquitectura.size() == 0)
				addFieldError("arquitectura",
						"Debe seleccionar un tipo de arquitectura");
			if (servicio.getVersion().trim().isEmpty())
				addFieldError("servicio.version", "Debe introducir la versión");
			// TODO Se debe validar que la sesión esté conformada solamente por
			// números y un solo punto. Además de que el formato sea con 0.X en
			// caso de no introducir un primer caracter.
			if (intercambio < 0)
				addFieldError("intercambio",
						"Debe seleccionar un tipo de intercambio");
			prepararDescripcionTecnica();
			break;
		case DESCRIPCION_SOPORTE:
			if (servicio.getResponsable().trim().isEmpty())
				addFieldError("servicio.responsable",
						"Debe introducir el nombre del responsable del servicio");
			if (telefono.trim().isEmpty())
				addFieldError("telefono",
						"Debe introducir un número de teléfono");
			if (telefono.length() > 0 && telefono.length() < 7)
				addFieldError("telefono",
						"Debe introducir un número telefónico válido de 7 dígitos");
			if (!telefono.matches("\\d.*") && !telefono.trim().isEmpty())
				addFieldError("telefono",
						"El teléfono sólo puede estar conformado por números");
			// TODO Se debe validar que el teléfono esté solamente conformado
			// por números.
			if (correo.trim().isEmpty())
				addFieldError("correo", "Debe introducir un correo electrónico");
			// TODO Se debe validar que el correo tenga un formato válido a
			// través de una expresión regular u otro método.
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unchecked")
	protected void sessionStack() {
		session = ActionContext.getContext().getSession();
		session.put("servicio", servicio);
		try {
			servicio = (ServicioInformacion) session.get("servicio");
		} catch (Exception e) {
			System.out.println("FUCK");
		}
	}
}
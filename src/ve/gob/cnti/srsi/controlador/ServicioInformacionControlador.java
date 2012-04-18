package ve.gob.cnti.srsi.controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.Constants;
import ve.gob.cnti.srsi.dao.Constants.Tabs;
import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Area;
import ve.gob.cnti.srsi.modelo.Arquitectura;
import ve.gob.cnti.srsi.modelo.AspectoLegal;
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
		ServletRequestAware, Tabs {
	private List<Sector> sectores = new ArrayList<Sector>();
	private List<Estado> estados = new ArrayList<Estado>();
	private List<Area> areas = new ArrayList<Area>();
	private List<Seguridad> niveles = new ArrayList<Seguridad>();
	private List<Arquitectura> arquitecturas = new ArrayList<Arquitectura>();
	private List<Intercambio> parents = new ArrayList<Intercambio>();
	private List<Intercambio> children = new ArrayList<Intercambio>();

	private List<UnionAreaServicioInformacion> unionareas = new ArrayList<UnionAreaServicioInformacion>();
	private List<UnionArquitecturaServicioInformacion> unionarquitecturas = new ArrayList<UnionArquitecturaServicioInformacion>();

	private boolean modificar;
	private long id_servicio_informacion;
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

	private List<Archivo> files;
	private File file;
	private String fileContentType;
	private String fileFileName;
	private String name;

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

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
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

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public List<Archivo> getFiles() {
		return files;
	}

	public void setFiles(List<Archivo> files) {
		this.files = files;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararDescripcionGeneral() {
		getSessionStack();
		tab = DESCRIPCION_GENERAL;
		sectores = (List<Sector>) read(new Sector());
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		return SUCCESS;
	}

	@SkipValidation
	public String prepararAspectosLegales() {
		// TODO La tablita esa.
		getSessionStack();
		tab = ASPECTOS_LEGALES;
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararDescripcionTecnica() {
		getSessionStack();
		tab = DESCRIPCION_TECNICA;
		niveles = (List<Seguridad>) read(new Seguridad());
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		parents = (List<Intercambio>) getParents(new Intercambio());
		children = (List<Intercambio>) getChildren(new Intercambio());
		return SUCCESS;
	}

	@SkipValidation
	public String prepararDescripcionSoporte() {
		getSessionStack();
		tab = DESCRIPCION_SOPORTE;
		return SUCCESS;
	}

	public String registrarDescripcionGeneral() {
		setSessionStack();
		System.out.println("ID => " + id_servicio_informacion);
		if (id_servicio_informacion == 0) {
			servicio.setId_ente(1);
			servicio.setId_estado(estado);
			servicio.setId_intercambio(intercambio);
			servicio.setId_sector(sector);
			servicio.setId_seguridad(seguridad);
			servicio.setId_usuario(1);
			create(servicio);
			id_servicio_informacion = getNextId(servicio) - 1;
			System.out.println("ID => " + id_servicio_informacion);
		}
		return SUCCESS;
	}

	public String registrarAspectosLegales() throws IOException {
		setSessionStack();
		System.out.println("File => " + file);
		System.out.println("Name => " + name);
		System.out.println("FileName => " + fileFileName);
		System.out.println("ContentType => " + fileContentType);
		Archivo archivo = new Archivo();
		archivo.setFile(file);
		archivo.setName(name);
		archivo.setFileFileName(fileFileName);
		archivo.setFileContentType(fileContentType);
		// files.add(archivo);
		AspectoLegal documento = new AspectoLegal();
		documento.setId_servicio_informacion(id_servicio_informacion);
		documento.setNombre(name);
		documento.setUrl(saveFile());
		create(documento);
		// update(servicio, servicio.getId());
		return SUCCESS;
	}

	private String saveFile() throws IOException {
		session = ActionContext.getContext().getSession();
		String siglas = ((Ente) session.get("ente")).getSiglas().toString()
				.toLowerCase();
		String path = servletRequest.getSession().getServletContext()
				.getRealPath("/archivos/" + siglas + "/");
		FileUtils.copyFile(file, new File(path, fileFileName));
		return "/archivos/" + siglas + "/" + fileFileName;
	}

	public String registrarDescripcionTecnica() {
		setSessionStack();
		// update(servicio, servicio.getId());
		return SUCCESS;
	}

	public String registrarDescripcionSoporte() {
		setSessionStack();
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
			prepararDescripcionGeneral();
			break;
		case ASPECTOS_LEGALES:
			// TODO Esta validación parece no estar funcionando.
			// if (name.trim().isEmpty() && file != null) {
			// addFieldError("name",
			// "Si va a subir un archivo debe introducir un nombre");
			// addFieldError("file", "Suba nuevamente el archivo");
			// }
			// if (!name.trim().isEmpty() && file == null) {
			// addFieldError("name",
			// "Si va a colocar un nombre debe subir un archivo");
			// addFieldError("file",
			// "Por favor seleccione un archivo para subir");
			// }
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
	private void setSessionStack() {
		session = ActionContext.getContext().getSession();
		session.put("servicio", servicio);
		session.put("sector", sector);
		session.put("area", area);
		session.put("estado", estado);
		session.put("seguridad", seguridad);
		session.put("arquitectura", arquitectura);
		session.put("intercambio", intercambio);
		session.put("telefono", telefono);
		session.put("correo", correo);
	}

	@SuppressWarnings("unchecked")
	private void getSessionStack() {
		session = ActionContext.getContext().getSession();
		try {
			servicio = (ServicioInformacion) session.get("servicio");
			sector = (Long) session.get("sector");
			area = (List<Long>) session.get("area");
			estado = (Long) session.get("estado");
			seguridad = (Long) session.get("seguridad");
			arquitectura = (List<Long>) session.get("arquitectura");
			intercambio = (Long) session.get("intercambio");
			telefono = (String) session.get("telefono");
			correo = (String) session.get("correo");
		} catch (Exception e) {
			// TODO Handling the exception?!
		}
	}
}
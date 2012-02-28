package ve.gob.cnti.srsi.controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Area;
import ve.gob.cnti.srsi.modelo.Arquitectura;
import ve.gob.cnti.srsi.modelo.AspectoLegal;
import ve.gob.cnti.srsi.modelo.Correo;
import ve.gob.cnti.srsi.modelo.Estado;
import ve.gob.cnti.srsi.modelo.Intercambio;
import ve.gob.cnti.srsi.modelo.Sector;
import ve.gob.cnti.srsi.modelo.Seguridad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.Telefono;
import ve.gob.cnti.srsi.modelo.UnionAreaServicioInformacion;
import ve.gob.cnti.srsi.modelo.UnionArquitecturaServicioInformacion;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@SuppressWarnings("serial")
public class ServicioInformacionControlador extends ActionSupport implements
		ServletRequestAware {

	private List<Area> areas = new ArrayList<Area>();
	private List<Estado> estados = new ArrayList<Estado>();
	private List<Seguridad> l_seguridad = new ArrayList<Seguridad>();
	private List<Arquitectura> arquitecturas = new ArrayList<Arquitectura>();
	private List<Sector> sectores = new ArrayList<Sector>();
	private List<Intercambio> intercambiosPadres = new ArrayList<Intercambio>();
	private List<Intercambio> intercambiosHijos = new ArrayList<Intercambio>();

	private long idServicioInformacion;
	private String sector;
	private String nombre;
	private String descripcion;
	private String estado;
	private String aspectoLegal;
	private String slaNombre;
	private List<String> area;
	private String seguridad;
	private String version;
	private List<String> arquitectura;
	private String intercambio;
	private String responsable;
	private String codArea;
	private String telefonoContacto;
	private String correoContacto;

	private File archivo;
	private String archivoContentType;
	private String archivoFileName;

	private File slaArchivo;
	private String slaArchivoFileName;

	private HttpServletRequest servletRequest;

	private DAO dao = new DAO();

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararRegistroServicioInformacion() {

		Area area = new Area();
		Estado est = new Estado();
		Seguridad seg = new Seguridad();
		Arquitectura arq = new Arquitectura();
		Sector sector = new Sector();
		Intercambio intercambio = new Intercambio();

		areas = (List<Area>) dao.read(area);
		estados = (List<Estado>) dao.read(est);
		l_seguridad = (List<Seguridad>) dao.read(seg);
		arquitecturas = (List<Arquitectura>) dao.read(arq);
		sectores = (List<Sector>) dao.read(sector);

		intercambiosPadres = (List<Intercambio>) dao.getParents(intercambio);
		intercambiosHijos = (List<Intercambio>) dao.getChildren(intercambio);

		responsable = "Usuario";

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public void validate() {
		Area area = new Area();
		Estado est = new Estado();
		Seguridad seg = new Seguridad();
		Arquitectura arq = new Arquitectura();
		Sector sector = new Sector();
		Intercambio intercambio = new Intercambio();

		/*
		 * Para Validar la que la cadena sea decimal y con el rango (0,999.999)
		 * No encontre una anotación en struts validation que lo hiciera
		 */
		float ver;
		try {
			ver = Float.parseFloat(version);
			if (ver < 0.0 || ver > 999.999) {
				addFieldError(
						"version",
						getText("Su número de versión se sale del rango, el formato es XXX.XXX"));
				System.out.println("Entro a el error de rango");
			}
		} catch (NumberFormatException ex) {
			addFieldError(
					"version",
					getText("La versión solo debe tener números en un formato XXX.XXX"));
		}

		if (archivoFileName != null && aspectoLegal.isEmpty() == true) {// valida
																		// que
																		// ambos
																		// campos
																		// existan
			addFieldError(
					"aspectoLegal",
					getText("Si va a subir un documento debe proporcionar el nombre con que se va a guardar"));
			addFieldError(
					"archivo",
					getText("Si va a subir un documento debe proporcionar el archivo a guardar"));
		}
		if (archivoFileName == null && aspectoLegal.isEmpty() == false) {// valida
																			// que
																			// ambos
																			// campos
																			// existan
			addFieldError(
					"archivo",
					getText("Si va a subir un documento debe proporcionar el archivo a guardar"));
		}

		if (slaArchivoFileName != null && slaNombre.isEmpty() == true) {// valida
																		// que
																		// ambos
																		// campos
																		// existan
			addFieldError(
					"slaNombre",
					getText("Si va a subir un documento debe proporcionar el nombre con que se va a guardar"));
			addFieldError(
					"slaArchivo",
					getText("Si va a subir un documento debe proporcionar el archivo a guardar"));
		}
		if (slaArchivoFileName == null && slaNombre.isEmpty() == false) {// valida
																			// que
																			// ambos
																			// campos
																			// existan
			addFieldError(
					"slaArchivo",
					getText("Si va a subir un documento debe proporcionar el archivo a guardar"));
		}

		areas = (List<Area>) dao.read(area);
		estados = (List<Estado>) dao.read(est);
		l_seguridad = (List<Seguridad>) dao.read(seg);
		arquitecturas = (List<Arquitectura>) dao.read(arq);
		sectores = (List<Sector>) dao.read(sector);

		intercambiosPadres = (List<Intercambio>) dao.getParents(intercambio);
		intercambiosHijos = (List<Intercambio>) dao.getChildren(intercambio);

		responsable = "Usuario";

	}

	public String registrarServicioInformacion() {

		ServicioInformacion si = new ServicioInformacion();

		long id_si = dao.getNextId(si);

		// consultar ente
		si.setId_ente(1);
		// consultar usuario
		si.setId_usuario(1);

		// Seteando el SECTOR
		si.setId_sector(Long.parseLong(sector));

		// Seteando el NOMBRE
		si.setNombre(nombre);

		// Seteando el DESCRIPCION
		si.setDescripcion(descripcion);

		// Seteando el ESTADO
		si.setId_estado(Long.parseLong(estado));

		// Seteando el SEGURIDAD
		si.setId_seguridad(Long.parseLong(seguridad));

		// Seteando el VERSION
		si.setVersion(version);

		// Seteando el TIPO DE INTERCAMBIO
		si.setId_tipo_intercambio(Long.parseLong(intercambio));

		try {
			dao.create(si);
		} catch (Exception e) {
			addFieldError("nombre",
					getText("Este nombre ya existe. Proporcione otro."));
			return INPUT;
		}

		// Seteando el AREA
		UnionAreaServicioInformacion unionarea = new UnionAreaServicioInformacion();
		for (int i = 0; i < area.size(); i++) {
			unionarea.setId_area(Long.parseLong(String.valueOf(area.get(i))));
			unionarea.setId_servicio_informacion(id_si);
			dao.create(unionarea, id_si);
		}

		// Seteando el ARQUITECTURA
		UnionArquitecturaServicioInformacion unionarquitectura = new UnionArquitecturaServicioInformacion();
		for (int i = 0; i < arquitectura.size(); i++) {
			unionarquitectura.setId_arquitectura(Long.parseLong(String
					.valueOf(arquitectura.get(i))));
			unionarquitectura.setId_servicio_informacion(id_si);
			dao.create(unionarquitectura, id_si);
		}

		// Seteando el TELEFONO DE CONTACTO
		Telefono telf = new Telefono();
		telf.setTelefono(codArea + "-" + telefonoContacto);
		telf.setId_servicio_informacion(id_si);
		dao.create(telf);

		// Seteando el CORREO DE CONTACTO
		Correo correo = new Correo();
		correo.setCorreo(correoContacto);
		correo.setId_servicio_informacion(id_si);
		dao.create(correo);

		// Seteando el documento legal
		// valida que ambos campos existan
		if (archivoFileName != null && aspectoLegal.isEmpty() == false) {
			AspectoLegal al = new AspectoLegal();
			try {
				al.setUrl(saveFile());
			} catch (IOException e) {
				// levantar action error
				e.printStackTrace();
			}
			al.setNombre(aspectoLegal);
			al.setTipo(0);
			al.setId_servicio_informacion(id_si);
			dao.create(al);
		}

		// Seteando el documento legal
		// valida que ambos campos existan
		if (slaArchivo != null && slaNombre.isEmpty() == false) {
			AspectoLegal al = new AspectoLegal();
			try {
				al.setUrl(saveFile2());
			} catch (IOException e) {
				// levantar action error
				e.printStackTrace();
			}
			al.setNombre(aspectoLegal);
			al.setTipo(1);
			al.setId_servicio_informacion(id_si);
			dao.create(al);
		}

		return SUCCESS;
	}

	private String saveFile() throws IOException {
		String INSTITUCION = "cnti"; // Obtener desde la base de datos.

		String filePath = servletRequest.getSession().getServletContext()
				.getRealPath("/archivos/" + INSTITUCION.toString());

		System.out.println("Server path: " + filePath);
		System.out.println(archivoFileName);
		File fileToCreate = new File(filePath, this.archivoFileName);

		FileUtils.copyFile(this.archivo, fileToCreate);

		return "/archivos/" + INSTITUCION.toString() + "/" + archivoFileName;
	}

	private String saveFile2() throws IOException {
		String INSTITUCION = "cnti"; // Obtener desde la base de datos.

		String filePath = servletRequest.getSession().getServletContext()
				.getRealPath("/archivos/" + INSTITUCION.toString());

		System.out.println("Server path: " + filePath);
		System.out.println(slaArchivoFileName);
		File fileToCreate = new File(filePath, this.slaArchivoFileName);

		FileUtils.copyFile(this.slaArchivo, fileToCreate);

		return "/archivos/" + INSTITUCION.toString() + "/" + slaArchivoFileName;
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

	public List<Arquitectura> getArquitecturas() {
		return arquitecturas;
	}

	public void setArquitecturas(List<Arquitectura> arquitecturas) {
		this.arquitecturas = arquitecturas;
	}

	public List<Sector> getSectores() {
		return sectores;
	}

	public void setSectores(List<Sector> sectores) {
		this.sectores = sectores;
	}

	public List<Intercambio> getIntercambiosPadres() {
		return intercambiosPadres;
	}

	public void setIntercambiosPadres(List<Intercambio> intercambiosPadres) {
		this.intercambiosPadres = intercambiosPadres;
	}

	public List<Intercambio> getIntercambiosHijos() {
		return intercambiosHijos;
	}

	public void setIntercambiosHijos(List<Intercambio> intercambiosHijos) {
		this.intercambiosHijos = intercambiosHijos;
	}

	public List<Seguridad> getL_seguridad() {
		return l_seguridad;
	}

	public void setL_seguridad(List<Seguridad> l_seguridad) {
		this.l_seguridad = l_seguridad;
	}

	@RequiredStringValidator(message = "Introduzca la versión del Servicio de Información")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@FieldExpressionValidator(expression = "!sector.equals(\"-1\")", message = "Seleccione un valor. ")
	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	@RequiredStringValidator(message = "Introduzca El nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@RequiredStringValidator(message = "Proporcione una descripción ")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@FieldExpressionValidator(expression = "!sector.equals(\"-1\")", message = "Seleccione un valor. ")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getAspectoLegal() {
		return aspectoLegal;
	}

	public void setAspectoLegal(String aspectoLegal) {
		this.aspectoLegal = aspectoLegal;
	}

	@FieldExpressionValidator(expression = "!area.isEmpty()", message = "Seleccione un valor. ")
	public List<String> getArea() {
		return area;
	}

	public void setArea(List<String> area) {
		this.area = area;
	}

	@FieldExpressionValidator(expression = "!seguridad.equals(\"-1\")", message = "Seleccione un valor. ")
	public String getSeguridad() {
		return seguridad;
	}

	public void setSeguridad(String seguridad) {
		this.seguridad = seguridad;
	}

	@FieldExpressionValidator(expression = "!arquitectura.isEmpty()", message = "Seleccione un valor. ")
	public List<String> getArquitectura() {
		return arquitectura;
	}

	public void setArquitectura(List<String> arquitectura) {
		this.arquitectura = arquitectura;
	}

	@FieldExpressionValidator(expression = "!intercambio.equals(\"-1\")", message = "Seleccione un valor. ")
	public String getIntercambio() {
		return intercambio;
	}

	public void setIntercambio(String intercambio) {
		this.intercambio = intercambio;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	@FieldExpressionValidator(expression = "!(codArea.length() < 3)", message = "Proporcione un código de área teléfonico Válido")
	public String getCodArea() {
		return codArea;
	}

	public void setCodArea(String codArea) {
		this.codArea = codArea;
	}

	@FieldExpressionValidator(expression = "!(telefonoContacto.length() < 7)", message = "Proporcione un número telefónico Válido")
	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	@RequiredStringValidator(message = "Proporcione una dirección de correo para el soporte técnico ")
	@EmailValidator(message = "Proporcione una dirección valida de correo para el soporte técnico")
	public String getCorreoContacto() {
		return correoContacto;
	}

	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	public String getArchivoContentType() {
		return archivoContentType;
	}

	public void setArchivoContentType(String archivoContentType) {
		this.archivoContentType = archivoContentType;
	}

	public String getArchivoFileName() {
		return archivoFileName;
	}

	public void setArchivoFileName(String archivoFileName) {
		this.archivoFileName = archivoFileName;
	}

	public File getSlaArchivo() {
		return slaArchivo;
	}

	public void setSlaArchivo(File slaArchivo) {
		this.slaArchivo = slaArchivo;
	}

	public String getSlaArchivoFileName() {
		return slaArchivoFileName;
	}

	public void setSlaArchivoFileName(String slaArchivoFileName) {
		this.slaArchivoFileName = slaArchivoFileName;
	}

	public String getSlaNombre() {
		return slaNombre;
	}

	public void setSlaNombre(String slaNombre) {
		this.slaNombre = slaNombre;
	}

	public long getIdServicioInformacion() {
		return idServicioInformacion;
	}

	public void setIdServicioInformacion(long idServicioInformacion) {
		this.idServicioInformacion = idServicioInformacion;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

}
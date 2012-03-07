package ve.gob.cnti.srsi.controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.Constants.ArregloModelos;
import ve.gob.cnti.srsi.dao.Constants.TipoDocumento;
import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Area;
import ve.gob.cnti.srsi.modelo.Arquitectura;
import ve.gob.cnti.srsi.modelo.AspectoLegal;
import ve.gob.cnti.srsi.modelo.Correo;
import ve.gob.cnti.srsi.modelo.Dato;
import ve.gob.cnti.srsi.modelo.Estado;
import ve.gob.cnti.srsi.modelo.Intercambio;
import ve.gob.cnti.srsi.modelo.Sector;
import ve.gob.cnti.srsi.modelo.Seguridad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.Telefono;
import ve.gob.cnti.srsi.modelo.TipoDato;
import ve.gob.cnti.srsi.modelo.UnionAreaServicioInformacion;
import ve.gob.cnti.srsi.modelo.UnionArquitecturaServicioInformacion;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@SuppressWarnings("serial")
public class ServicioInformacionControlador extends DAO implements
		ServletRequestAware, Formulario, TipoDocumento, ArregloModelos {

	private List<Area> areas = new ArrayList<Area>();
	private List<Estado> estados = new ArrayList<Estado>();
	private List<Seguridad> l_seguridad = new ArrayList<Seguridad>();
	private List<Arquitectura> arquitecturas = new ArrayList<Arquitectura>();
	private List<Sector> sectores = new ArrayList<Sector>();
	private List<Intercambio> intercambiosPadres = new ArrayList<Intercambio>();
	private List<Intercambio> intercambiosHijos = new ArrayList<Intercambio>();

	private ServicioInformacion servicio = new ServicioInformacion();

	private long idServicioInformacion;
	private String sector;
	private String nombre;
	private String descripcion;
	private String estado;
	private List<String> area;
	private String seguridad;
	private String version;
	private List<String> arquitectura;
	private String intercambio;
	private String responsable;
	private String codArea;
	private String telefonoContacto;
	private String correoContacto;

	private File documentoLegal;
	private String documentoLegalNombre;
	private String documentoLegalFileName;

	private File sla;
	private String slaNombre;
	private String slaFileName;

	private HttpServletRequest servletRequest;

	@SuppressWarnings("unchecked")
	@Override
	@SkipValidation
	public String prepararFormulario() {

		Area area = new Area();
		Estado est = new Estado();
		Seguridad seg = new Seguridad();
		Arquitectura arq = new Arquitectura();
		Sector sector = new Sector();
		Intercambio intercambio = new Intercambio();

		areas = (List<Area>) read(area);
		estados = (List<Estado>) read(est);
		l_seguridad = (List<Seguridad>) read(seg);
		arquitecturas = (List<Arquitectura>) read(arq);
		sectores = (List<Sector>) read(sector);

		intercambiosPadres = (List<Intercambio>) getParents(intercambio);
		intercambiosHijos = (List<Intercambio>) getChildren(intercambio);

		// Debe venir de la sesión de usuario.
		responsable = "Usuario";

		System.out.println("NOMBRE DEL DATO => "
				+ ((Dato) read(NOMBRE_DATO, ENTRADA, 3)).getNombre());

		System.out.println("NOMBRE TIPO DATO => "
				+ ((TipoDato) read(NOMBRE_TIPO_DATO, 1)).getNombre());

		System.out.println("DATO DUPLICADO => "
				+ read(NOMBRE_DATO_NO_DUPLICADO, "Dato Simple 1", 3));

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public void validate() {
		/*
		 * Para Validar la que la cadena sea decimal y con el rango (0,999.999)
		 * No encontre una anotación en struts validation que lo hiciera (ESO ES
		 * UN BONO MENOS)
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

		// valida que ambos campos existan
		if (documentoLegalFileName != null
				&& documentoLegalNombre.isEmpty() == true) {
			addFieldError(
					"documentoLegalNombre",
					getText("Si va a subir un documento debe proporcionar el nombre con que se va a guardar"));
			addFieldError(
					"documentoLegal",
					getText("Si va a subir un documento debe proporcionar el archivo a guardar"));
		}
		// valida que ambos campos existan
		if (documentoLegalFileName == null
				&& documentoLegalNombre.isEmpty() == false) {
			addFieldError(
					"documentoLegal",
					getText("Si va a subir un documento debe proporcionar el archivo a guardar"));
		}

		// valida que ambos campos existan
		if (slaFileName != null && slaNombre.isEmpty() == true) {
			addFieldError(
					"slaNombre",
					getText("Si va a subir un documento debe proporcionar el nombre con que se va a guardar"));
			addFieldError(
					"sla",
					getText("Si va a subir un documento debe proporcionar el archivo a guardar"));
		}
		// valida que ambos campos existan
		if (sla == null && slaNombre.isEmpty() == false) {
			addFieldError(
					"sla",
					getText("Si va a subir un documento debe proporcionar el archivo a guardar"));
		}
		prepararFormulario();
	}

	public String registrarServicioInformacion() {

		idServicioInformacion = getNextId(servicio);
		// consultar ente
		servicio.setId_ente(1);
		// consultar usuario
		servicio.setId_usuario(1);

		// Seteando el SECTOR
		servicio.setId_sector(Long.parseLong(sector));

		// Seteando el NOMBRE
		servicio.setNombre(nombre);

		// Seteando el DESCRIPCION
		servicio.setDescripcion(descripcion);

		// Seteando el ESTADO
		servicio.setId_estado(Long.parseLong(estado));

		// Seteando el SEGURIDAD
		servicio.setId_seguridad(Long.parseLong(seguridad));

		// Seteando el VERSION
		servicio.setVersion(version);

		// Seteando el TIPO DE INTERCAMBIO
		servicio.setId_tipo_intercambio(Long.parseLong(intercambio));

		try {
			create(servicio);
		} catch (Exception e) {
			addFieldError("nombre",
					getText("Este nombre ya existe. Proporcione otro."));
			return INPUT;
		}

		// Seteando el AREA
		UnionAreaServicioInformacion unionarea = new UnionAreaServicioInformacion();
		for (int i = 0; i < area.size(); i++) {
			unionarea.setId_area(Long.parseLong(String.valueOf(area.get(i))));
			unionarea.setId_servicio_informacion(idServicioInformacion);
			// create(unionarea, id_si);
		}

		// Seteando el ARQUITECTURA
		UnionArquitecturaServicioInformacion unionarquitectura = new UnionArquitecturaServicioInformacion();
		for (int i = 0; i < arquitectura.size(); i++) {
			unionarquitectura.setId_arquitectura(Long.parseLong(String
					.valueOf(arquitectura.get(i))));
			unionarquitectura.setId_servicio_informacion(idServicioInformacion);
			// create(unionarquitectura, id_si);
		}

		// Seteando el TELEFONO DE CONTACTO
		Telefono telf = new Telefono();
		telf.setTelefono(codArea + "-" + telefonoContacto);
		telf.setId_servicio_informacion(idServicioInformacion);
		create(telf);

		// Seteando el CORREO DE CONTACTO
		Correo correo = new Correo();
		correo.setCorreo(correoContacto);
		correo.setId_servicio_informacion(idServicioInformacion);
		create(correo);

		// Seteando el documento legal
		// valida que ambos campos existan
		if (documentoLegalFileName != null
				&& documentoLegalNombre.isEmpty() == false) {
			AspectoLegal al = new AspectoLegal();
			try {
				al.setUrl(saveFile(documentoLegal, documentoLegalFileName));
			} catch (IOException e) {
				// levantar action error
				e.printStackTrace();
			}
			al.setNombre(documentoLegalNombre);
			al.setTipo(LEGAL);
			al.setId_servicio_informacion(idServicioInformacion);
			create(al);
		}

		// Seteando el documento legal
		// valida que ambos campos existan
		if (sla != null && slaNombre.isEmpty() == false) {
			AspectoLegal al = new AspectoLegal();
			try {
				al.setUrl(saveFile(sla, slaFileName));
			} catch (IOException e) {
				// levantar action error
				e.printStackTrace();
			}
			al.setNombre(slaNombre);
			al.setTipo(SLA);
			al.setId_servicio_informacion(idServicioInformacion);
			create(al);
		}
		return SUCCESS;
	}

	private String saveFile(File file, String fileName) throws IOException {
		String INSTITUCION = "cnti"; // Obtener desde la base de datos.
		String filePath = servletRequest.getSession().getServletContext()
				.getRealPath("/archivos/" + INSTITUCION.toString());
		File fileToCreate = new File(filePath, fileName);
		FileUtils.copyFile(file, fileToCreate);

		return "/archivos/" + INSTITUCION.toString() + "/" + fileName;
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

	public ServicioInformacion getServicio() {
		return servicio;
	}

	public void setServicio(ServicioInformacion servicio) {
		this.servicio = servicio;
	}

	public File getDocumentoLegal() {
		return documentoLegal;
	}

	public void setDocumentoLegal(File documentoLegal) {
		this.documentoLegal = documentoLegal;
	}

	public String getDocumentoLegalNombre() {
		return documentoLegalNombre;
	}

	public void setDocumentoLegalNombre(String documentoLegalNombre) {
		this.documentoLegalNombre = documentoLegalNombre;
	}

	public String getDocumentoLegalFileName() {
		return documentoLegalFileName;
	}

	public void setDocumentoLegalFileName(String documentoLegalFileName) {
		this.documentoLegalFileName = documentoLegalFileName;
	}

	public File getSla() {
		return sla;
	}

	public void setSla(File sla) {
		this.sla = sla;
	}

	public String getSlaNombre() {
		return slaNombre;
	}

	public void setSlaNombre(String slaNombre) {
		this.slaNombre = slaNombre;
	}

	public String getSlaFileName() {
		return slaFileName;
	}

	public void setSlaFileName(String slaFileName) {
		this.slaFileName = slaFileName;
	}
}
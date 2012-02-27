package ve.gob.cnti.srsi.controlador;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Area;
import ve.gob.cnti.srsi.modelo.Arquitectura;
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
public class ServicioInformacionControlador extends ActionSupport {

	private List<Area> areas = new ArrayList<Area>();
	private List<Estado> estados = new ArrayList<Estado>();
	private List<Seguridad> l_seguridad = new ArrayList<Seguridad>();
	private List<Arquitectura> arquitecturas = new ArrayList<Arquitectura>();
	private List<Sector> sectores = new ArrayList<Sector>();
	private List<Intercambio> intercambiosPadres = new ArrayList<Intercambio>();
	private List<Intercambio> intercambiosHijos = new ArrayList<Intercambio>();

	private String sector;
	private String nombre;
	private String descripcion;
	private String estado;
	private String aspectoLegal;
	private List<String> area;
	private String seguridad;
	private String version;
	private List<String> arquitectura;
	private String intercambio;
	private String responsable;
	private String codArea;
	private String telefonoContacto;
	private String correoContacto;
	private File documento;
	private String documentoContentType;
	private String documentoFileName;

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
	public void validate(){
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
	}

	public String registrarServicioInformacion() {
				
		Date fecha = new Date();
		ServicioInformacion si = new ServicioInformacion();		
		long id_si = dao.getNextId(si); 
		
		si.setId_ente(1);
		si.setId_servicio_informacion(id_si);
		si.setId_usuario(1);
		
		//Seteando el SECTOR (FALTA VALIDAR)
		si.setId_sector(Long.parseLong(sector));		

		//Seteando el NOMBRE (FALTA VALIDAR)	
		si.setNombre(nombre);
		
		//Seteando el DESCRIPCION (FALTA VALIDAR)
		si.setDescripcion(descripcion);

		//Seteando el ESTADO (FALTA VALIDAR)
		si.setId_estado(Long.parseLong(estado));
					
		//Seteando el SEGURIDAD (FALTA VALIDAR)
		si.setId_seguridad(Long.parseLong(seguridad));
				
		//Seteando el VERSION (FALTA VALIDAR)
		si.setVersion(version);
		
		//Seteando el TIPO DE INTERCAMBIO (FALTA VALIDAR)
		si.setId_tipo_intercambio(Long.parseLong(intercambio));
		
		si.setStatus(0);
		si.setFecha_creado(fecha);
		si.setFecha_modificado(fecha);
		
		dao.create(si);
		
		//Seteando el AREA (FALTA VALIDAR)			
		UnionAreaServicioInformacion unionarea = new UnionAreaServicioInformacion();
		for(int i = 0; i<area.size();i++){
			unionarea.setId_area(Long.parseLong(String.valueOf(area.get(i))));
			unionarea.setId_servicio_informacion(id_si);
			unionarea.setStatus(0);
			unionarea.setFecha_creado(fecha);
			unionarea.setFecha_modificado(fecha);
			dao.create(unionarea);
		}
		
		//Seteando el ARQUITECTURA (FALTA VALIDAR)		
		UnionArquitecturaServicioInformacion unionarquitectura = new UnionArquitecturaServicioInformacion();
		for(int i = 0; i<arquitectura.size();i++){
			unionarquitectura.setId_servicio_informacion(Long.parseLong(String.valueOf(arquitectura.get(i))));
			unionarquitectura.setId_servicio_informacion(id_si);
			unionarquitectura.setStatus(0);
			unionarquitectura.setFecha_creado(fecha);
			unionarquitectura.setFecha_modificado(fecha);
			dao.create(unionarquitectura);
		}
		
		//Seteando el TELEFONO DE CONTACTO (FALTA VALIDAR)
		Telefono telf = new Telefono();
		telf.setId_telefono(dao.getNextId(telf));
		telf.setTelefono(codArea+"-"+telefonoContacto);
		telf.setId_servicio_informacion(id_si);
		telf.setStatus(0);
		telf.setFecha_creado(fecha);
		telf.setFecha_modificado(fecha);
		dao.create(telf);
		
		//Seteando el CORREO DE CONTACTO (FALTA VALIDAR)
		Correo correo = new Correo();
		correo.setId_correo(dao.getNextId(correo));
		correo.setCorreo(correoContacto);
		correo.setId_servicio_informacion(id_si);
		correo.setStatus(0);
		correo.setFecha_creado(fecha);
		correo.setFecha_modificado(fecha);
		dao.create(correo);

		return SUCCESS;
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

	@RequiredStringValidator(message="Introduzca la versión del Servicio de Información")
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

	@RequiredStringValidator(message="Introduzca El nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@RequiredStringValidator(message="Proporcione una descripción ")
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

	@RequiredStringValidator(message="Proporcione el nombre del documento legal")
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

	@RequiredStringValidator(message="Proporcione una dirección de correo para el soporte técnico ")
	@EmailValidator(message="Proporcione una dirección valida de correo para el soporte técnico")
	public String getCorreoContacto() {
		return correoContacto;
	}

	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}

	public File getDocumento() {
		return documento;
	}

	public void setDocumento(File documento) {
		this.documento = documento;
	}
	
	public String getDocumentoContentType() {
		return documentoContentType;
	}

	public void setDocumentoContentType(String documentoContentType) {
		this.documentoContentType = documentoContentType;
	}

	public String getDocumentoFileName() {
		return documentoFileName;
	}

	public void setDocumentoFileName(String documentoFileName) {
		this.documentoFileName = documentoFileName;
	}	

}
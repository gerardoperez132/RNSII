package ve.gob.cnti.srsi.controlador;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.Constants;
import ve.gob.cnti.srsi.dao.Constants.Formulario;
import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Area;
import ve.gob.cnti.srsi.modelo.Arquitectura;
import ve.gob.cnti.srsi.modelo.Estado;
import ve.gob.cnti.srsi.modelo.Intercambio;
import ve.gob.cnti.srsi.modelo.Sector;
import ve.gob.cnti.srsi.modelo.Seguridad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.UnionAreaServicioInformacion;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@SuppressWarnings("serial")
public class ServicioInformacionControlador extends DAO implements Formulario,
		Constants {

	private List<Sector> sectores;
	private List<Estado> estados;
	private List<Area> areas;
	private List<Seguridad> niveles;
	private List<Arquitectura> arquitecturas;
	private List<Intercambio> parents;
	private List<Intercambio> children;
	private HttpServletRequest servletRequest;

	private File file;
	private String name;
	private String filename;

	private String[] codigos = COD;
	private int codigo;

	private long sector;
	private long estado;
	private List<Long> area;
	private long seguridad;
	private List<Long> arquitectura;
	private long intercambio;
	private String telefono;
	private String correo;

	private long id_servicio_informacion;
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

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@FieldExpressionValidator(expression = "sector > 0", message = "Debe seleccionar un sector")
	public long getSector() {
		return sector;
	}

	public void setSector(long sector) {
		this.sector = sector;
	}

	@FieldExpressionValidator(expression = "estado > 0", message = "Debe seleccionar un estado")
	public long getEstado() {
		return estado;
	}

	public void setEstado(long estado) {
		this.estado = estado;
	}

	@FieldExpressionValidator(expression = "!area.isEmpty()", message = "Debe seleccionar algún area que esté orientado el servicio")
	public List<Long> getArea() {
		return area;
	}

	public void setArea(List<Long> area) {
		this.area = area;
	}

	@FieldExpressionValidator(expression = "seguridad > 0", message = "Debe seleccionar un nivel de seguridad")
	public long getSeguridad() {
		return seguridad;
	}

	public void setSeguridad(long seguridad) {
		this.seguridad = seguridad;
	}

	@FieldExpressionValidator(expression = "!arquitectura.isEmpty()", message = "Debe seleccionar algún tipo de arquitectura")
	public List<Long> getArquitectura() {
		return arquitectura;
	}

	public void setArquitectura(List<Long> arquitectura) {
		this.arquitectura = arquitectura;
	}

	@FieldExpressionValidator(expression = "intercambio > 0", message = "Debe seleccionar un tipo de intercambio")
	public long getIntercambio() {
		return intercambio;
	}

	public void setIntercambio(long intercambio) {
		this.intercambio = intercambio;
	}

	@RequiredStringValidator(message = "Debe introducir un número de teléfono")
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@EmailValidator(fieldName = "correo", message = "Debe introducir un correo electrónico válido")
	@RequiredStringValidator(message = "Debe introducir una dirección de correo electrónico")
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

	public ServicioInformacion getServicio() {
		return servicio;
	}

	public void setServicio(ServicioInformacion servicio) {
		this.servicio = servicio;
	}

	public String[] getCodigos() {
		return codigos;
	}

	public void setCodigos(String[] codigos) {
		this.codigos = codigos;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public void validate() {
		if (servicio.getNombre().isEmpty())
			addFieldError("servicio.nombre",
					"Proporcione un nombre para el servicio");

		if (servicio.getDescripcion().isEmpty())
			addFieldError("servicio.descripcion",
					"Debe proporcionar una descripción para el servicio de información");

		if (servicio.getResponsable().isEmpty())
			addFieldError(
					"servicio.responsable",
					getText("Debe introducir el nombre del responsable del servicio"));

		try {
			float version = Float.parseFloat(servicio.getVersion().toString());
			if (version < 0.0 || version > 999.999) {
				addFieldError(
						"servicio.version",
						getText("Su número de versión se sale del rango, el formato es XXX.XXX"));
				System.out.println("ENTRÓ");
			}
		} catch (NumberFormatException ex) {
			addFieldError(
					"version",
					getText("La versión solo debe tener números en un formato XXX.XXX"));
		}

		if (servicio.getVersion().isEmpty())
			addFieldError("servicio.version",
					getText("Debe introducir un número de versión"));
		// TODO ¿Validar que sólo tiene un punto?

		if (filename != null && name.isEmpty()) {
			addFieldError(
					"name",
					getText("Si va a subir un documento debe proporcionar el nombre con que se va a guardar"));
			addFieldError(
					"file",
					getText("Si va a subir un documento debe proporcionar el archivo a guardar"));
		}

		if (filename == null && !name.isEmpty()) {
			addFieldError(
					"file",
					getText("Si va a subir un documento debe proporcionar el archivo a guardar"));
		}

		if (telefono.length() > 0 && telefono.length() < 7)
			addFieldError("telefono",
					"Debe introducir un número telefónico válido de 7 dígitos");
		prepararFormulario();
	}

	public String registrarServicioInformacion() {
		id_servicio_informacion = getNextId(servicio);
		// TODO Este identificador debe venir de la base de datos.
		servicio.setId_ente(1);
		// TODO Este identificador debe venir de la base de datos.
		servicio.setId_usuario(1);
		servicio.setId_sector(sector);
		servicio.setId_estado(estado);
		servicio.setId_seguridad(seguridad);
		servicio.setId_intercambio(intercambio);
		// TODO Verificar que el nombre no esté repetido.
		create(servicio);

		UnionAreaServicioInformacion unionAreaServicioInformacion = new UnionAreaServicioInformacion();
		for (int i = 0; i < area.size(); i++) {
			unionAreaServicioInformacion.setId_area(Long.parseLong(String
					.valueOf(area.get(i))));
			unionAreaServicioInformacion
					.setId_servicio_informacion(id_servicio_informacion);
			createUnion(unionAreaServicioInformacion);
		}
		//
		// // Seteando el ARQUITECTURA
		// UnionArquitecturaServicioInformacion unionarquitectura = new
		// UnionArquitecturaServicioInformacion();
		// for (int i = 0; i < arquitectura.size(); i++) {
		// unionarquitectura.setId_arquitectura(Long.parseLong(String
		// .valueOf(arquitectura.get(i))));
		// unionarquitectura
		// .setId_servicio_informacion(id_servicio_informacion);
		// // create(unionarquitectura, id_si);
		// }
		//
		// // Seteando el TELEFONO DE CONTACTO
		// Telefono telf = new Telefono();
		// telf.setTelefono(codigo + "-" + telefono);
		// telf.setId_servicio_informacion(id_servicio_informacion);
		// create(telf);
		//
		// // Seteando el CORREO DE CONTACTO
		// Correo email = new Correo();
		// email.setCorreo(correo);
		// email.setId_servicio_informacion(id_servicio_informacion);
		// create(email);
		//
		// // Seteando el documento legal
		// // valida que ambos campos existan
		// if (documentoFileName != null && documentoNombre.isEmpty() == false)
		// {
		// AspectoLegal al = new AspectoLegal();
		// try {
		// al.setUrl(saveFile(documento, documentoFileName));
		// } catch (IOException e) {
		// // levantar action error
		// e.printStackTrace();
		// }
		// al.setNombre(documentoNombre);
		// al.setTipo(LEGAL);
		// al.setId_servicio_informacion(id_servicio_informacion);
		// create(al);
		// }
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	@Override
	public String prepararFormulario() {
		sectores = (List<Sector>) read(new Sector());
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		niveles = (List<Seguridad>) read(new Seguridad());
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		parents = (List<Intercambio>) getParents(new Intercambio());
		children = (List<Intercambio>) getChildren(new Intercambio());
		return SUCCESS;
	}

	/**
	 * Regresa la ruta en la que se guardará el archivo que cargó el usuario.
	 * 
	 * @param file
	 *            Archivo a guardar.
	 * @param name
	 *            Nombre del archivo a guardar.
	 * @return {@code String} Ruta donde se guarda el archivo.
	 * @throws IOException
	 */
	private String saveFile(File file, String name) throws IOException {
		// TODO Obtener el nombre de la institución desde la base de datos.
		String ENTE = "CNTI".toLowerCase();
		String path = servletRequest.getSession().getServletContext()
				.getRealPath("/archivos/" + ENTE);
		FileUtils.copyFile(file, new File(path, name));
		return "/archivos/" + ENTE + "/" + name;
	}
}
package ve.gob.cnti.srsi.controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.Constants;
import ve.gob.cnti.srsi.dao.Constants.Formulario;
import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Area;
import ve.gob.cnti.srsi.modelo.Arquitectura;
import ve.gob.cnti.srsi.modelo.Correo;
import ve.gob.cnti.srsi.modelo.Ente;
import ve.gob.cnti.srsi.modelo.EntradaSalida;
import ve.gob.cnti.srsi.modelo.Estado;
import ve.gob.cnti.srsi.modelo.Funcionalidad;
import ve.gob.cnti.srsi.modelo.Intercambio;
import ve.gob.cnti.srsi.modelo.Sector;
import ve.gob.cnti.srsi.modelo.Seguridad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;
import ve.gob.cnti.srsi.modelo.Telefono;
import ve.gob.cnti.srsi.modelo.UnionAreaServicioInformacion;
import ve.gob.cnti.srsi.modelo.UnionArquitecturaServicioInformacion;
import ve.gob.cnti.srsi.modelo.Usuario;

import com.opensymphony.xwork2.ActionContext;
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
	private List<Funcionalidad> funcionalidades;
	private List<List<EntradaSalida>> ios = new ArrayList<List<EntradaSalida>>();
	private List<UnionAreaServicioInformacion> unionareas;
	private List<UnionArquitecturaServicioInformacion> unionarquitecturas;

	private HttpServletRequest servletRequest;
	private Ente ente;
	private ServicioInformacion servicio = new ServicioInformacion();
	private Funcionalidad funcionalidad = new Funcionalidad();

	private List<File> files = new ArrayList<File>();
	private List<String> fileContentTypes = new ArrayList<String>();
	private List<String> fileFileNames = new ArrayList<String>();
	private String name;
	private List<String> names;

	private String[] codigos = COD;
	private String codigo;

	private long sector;
	private long estado;
	private List<Long> area = new ArrayList<Long>();
	private long seguridad;
	private List<Long> arquitectura = new ArrayList<Long>();
	private long intercambio;
	private String telefono;
	private String correo;
	private boolean modificar;

	private List<Archivos> archivos;

	public List<Archivos> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<Archivos> archivos) {
		this.archivos = archivos;
	}

	@SuppressWarnings("rawtypes")
	private Map session;

	private long id_servicio_informacion;

	@SuppressWarnings({ "unchecked" })
	@SkipValidation
	public String eliminarServicioInformacion() {
		Funcionalidad funcion_del = new Funcionalidad();
		EntradaSalida io_del = new EntradaSalida();
		List<EntradaSalida> ios_del = new ArrayList<EntradaSalida>();

		Object[] models = { new Funcionalidad(), new ServicioInformacion() };
		funcionalidades = (List<Funcionalidad>) read(models,
				id_servicio_informacion, -1);
		Iterator<Funcionalidad> iterador = funcionalidades.iterator();

		while (iterador.hasNext()) {
			funcion_del = iterador.next();

			Object[] models2 = { new EntradaSalida(), new Funcionalidad() };
			ios_del = (List<EntradaSalida>) read(models2,
					funcion_del.getId_funcionalidad(), -1);
			Iterator<EntradaSalida> iterador2 = ios_del.iterator();

			while (iterador2.hasNext()) {
				io_del = iterador2.next();
				delete(io_del, io_del.getId_entrada_salida());
			}
			delete(funcion_del, funcion_del.getId_funcionalidad());
		}
		delete(new ServicioInformacion(), id_servicio_informacion);
		return SUCCESS;
	}

	@SkipValidation
	public String publicarServicioInformacion() {
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		servicio.setPublicado(true);
		update(servicio, id_servicio_informacion);
		return SUCCESS;
	}

	@SkipValidation
	public String despublicarServicioInformacion() {
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		servicio.setPublicado(false);
		update(servicio, id_servicio_informacion);
		return SUCCESS;
	}

	@SuppressWarnings({ "unchecked" })
	@SkipValidation
	public String examinarServicioInformacion() {
		session = ActionContext.getContext().getSession();
		Usuario usuario = new Usuario();
		usuario = (Usuario) session.get("usuario");
		if (usuario == null) {
			return "errorSession";
		}
		ente = (Ente) read(new Ente(), usuario.getId_ente());
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		Object[] models = { new Funcionalidad(), new ServicioInformacion() };
		funcionalidades = (List<Funcionalidad>) read(models,
				id_servicio_informacion, -1);
		Iterator<Funcionalidad> iterador = funcionalidades.iterator();
		while (iterador.hasNext()) {
			funcionalidad = iterador.next();
			Object[] models2 = { new EntradaSalida(), new Funcionalidad() };
			List<EntradaSalida> es_tmp = (List<EntradaSalida>) read(models2,
					funcionalidad.getId_funcionalidad(), -1);
			ios.add(es_tmp);
		}
		sectores = (List<Sector>) read(new Sector());
		estados = (List<Estado>) read(new Estado());
		sectores = (List<Sector>) read(new Sector());
		areas = (List<Area>) read(new Area());
		unionareas = (List<UnionAreaServicioInformacion>) readUnion(
				new UnionAreaServicioInformacion(), servicio,
				id_servicio_informacion);
		niveles = (List<Seguridad>) read(new Seguridad());
		unionarquitecturas = (List<UnionArquitecturaServicioInformacion>) readUnion(
				new UnionArquitecturaServicioInformacion(), servicio,
				id_servicio_informacion);
		System.out.println(unionarquitecturas.size());
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		System.out.println(arquitecturas.size());
		children = (List<Intercambio>) read(new Intercambio());
		Telefono phone = new Telefono();
		phone = (Telefono) getPhone(servicio,
				servicio.getId_servicio_informacion());
		telefono = phone.getTelefono();
		Correo email = new Correo();
		email = (Correo) getEmail(servicio,
				servicio.getId_servicio_informacion());
		correo = email.getCorreo();
		return SUCCESS;
	}

	@Override
	public void validate() {
		Iterator<Archivos> iterador = archivos.iterator();
		while (iterador.hasNext()) {
			System.out.println("Archivo => " + iterador.next().toString());
		}

		// System.out.println("NAME=>" + name.toString());
		// String[] splits = name.split(",");
		// names = Arrays.asList(splits);
		// for (String n : names)
		// System.out.println("NAMES => " + n);

		// int i = 0;
		// for (String n : names) {
		// if (n.trim().equalsIgnoreCase(""))
		// addFieldError("name" + i,
		// "Si va a subir un documento, debe proporcionar un nombre");
		// i++;
		// }
		try {
			saveFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		// TODO Validaciones de archivos
		// if (files != null && name.isEmpty()) {
		// // System.out.println("file: " + files.getPath());
		// addFieldError(
		// "name",
		// getText("Si va a subir un documento debe proporcionar el nombre con que se va a guardar"));
		// }
		//
		// if (files == null && !name.isEmpty()) {
		// addFieldError(
		// "file",
		// getText("Si va a subir un documento debe proporcionar el archivo a guardar"));
		// }

		if (telefono.length() > 0 && telefono.length() < 7)
			addFieldError("telefono",
					"Debe introducir un número telefónico válido de 7 dígitos");
		prepararFormulario();
	}

	public String registrarServicioInformacion() {
		session = ActionContext.getContext().getSession();
		Usuario usuario = new Usuario();
		usuario = (Usuario) session.get("usuario");
		if (usuario == null) {
			return "errorSession";
		}
		id_servicio_informacion = getNextId(servicio);
		servicio.setId_ente(usuario.getId_ente());
		servicio.setId_usuario(usuario.getId_usuario());
		servicio.setId_sector(sector);
		servicio.setId_estado(estado);
		servicio.setId_seguridad(seguridad);
		servicio.setId_intercambio(intercambio);
		// TODO Verificar que el nombre no esté repetido.
		create(servicio);

		UnionAreaServicioInformacion unionAreaServicioInformacion = new UnionAreaServicioInformacion();
		for (int i = 0; i < area.size(); i++) {
			unionAreaServicioInformacion.setId_area(area.get(i));
			unionAreaServicioInformacion
					.setId_servicio_informacion(id_servicio_informacion);
			createUnion(unionAreaServicioInformacion);
		}

		UnionArquitecturaServicioInformacion unionArquitecturaServicioInformacion = new UnionArquitecturaServicioInformacion();
		for (int i = 0; i < arquitectura.size(); i++) {
			unionArquitecturaServicioInformacion
					.setId_arquitectura(arquitectura.get(i));
			unionArquitecturaServicioInformacion
					.setId_servicio_informacion(id_servicio_informacion);
			createUnion(unionArquitecturaServicioInformacion);
		}

		Telefono phone = new Telefono();
		phone.setTelefono(codigo + telefono);
		phone.setId_servicio_informacion(id_servicio_informacion);
		create(phone);

		Correo email = new Correo();
		email.setCorreo(correo);
		email.setId_servicio_informacion(id_servicio_informacion);
		create(email);

		// if (fileFileName != null && !name.isEmpty()) {
		// AspectoLegal documento = new AspectoLegal();
		// documento.setId_servicio_informacion(id_servicio_informacion);
		// documento.setNombre(name);
		// // TODO Colocar el tipo de documento, ¿cuáles son? =/
		// // documento.setTipo(0);
		// // try {
		// // // TODO Colocar la ruta del archivo correspondiente.
		// // // documento.setUrl(saveFile(file, fileFileName));
		// // } catch (IOException e) {
		// // // TODO Auto-generated catch block
		// // e.printStackTrace();
		// // }
		// create(documento);
		// }
		return SUCCESS;
	}

	public String modificarServicioInformacion() {
		session = ActionContext.getContext().getSession();
		Usuario usuario = new Usuario();
		usuario = (Usuario) session.get("usuario");
		if (usuario == null) {
			return "errorSession";
		}
		ServicioInformacion servicio2 = (ServicioInformacion) read(servicio,
				id_servicio_informacion);
		servicio2 = (ServicioInformacion) read(servicio,
				id_servicio_informacion);
		servicio.setFecha_creado(servicio2.getFecha_creado());
		servicio.setFecha_modificado(servicio2.getFecha_modificado());
		servicio.setId_ente(usuario.getId_ente());
		servicio.setId_usuario(usuario.getId_usuario());
		servicio.setId_sector(sector);
		servicio.setId_estado(estado);
		servicio.setId_seguridad(seguridad);
		servicio.setId_intercambio(intercambio);
		// TODO Verificar que el nombre no esté repetido.
		update(servicio, id_servicio_informacion);

		try {
			updateUnion(new UnionAreaServicioInformacion(),
					new ServicioInformacion(), new Area(),
					id_servicio_informacion, area);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			updateUnion(new UnionArquitecturaServicioInformacion(),
					new ServicioInformacion(), new Arquitectura(),
					id_servicio_informacion, arquitectura);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Telefono phone = new Telefono();
		phone = (Telefono) read(phone, id_servicio_informacion);
		phone.setTelefono(codigo + telefono);
		update(phone, phone.getId_telefono());

		Correo email = new Correo();
		email = (Correo) getEmail(servicio, id_servicio_informacion);
		email.setCorreo(correo);
		update(email, email.getId_correo());
		// TODO actualizar documento

		modificar = false;
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararModificarServicioInformacion() {
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		sector = servicio.getId_sector();
		unionareas = (List<UnionAreaServicioInformacion>) readUnion(
				new UnionAreaServicioInformacion(), servicio,
				id_servicio_informacion);
		Iterator<UnionAreaServicioInformacion> iterador = unionareas.iterator();
		while (iterador.hasNext()) {
			area.add(iterador.next().getId_area());
		}
		estado = servicio.getId_estado();
		seguridad = servicio.getId_seguridad();
		unionarquitecturas = (List<UnionArquitecturaServicioInformacion>) readUnion(
				new UnionArquitecturaServicioInformacion(), servicio,
				id_servicio_informacion);
		Iterator<UnionArquitecturaServicioInformacion> iterador2 = unionarquitecturas
				.iterator();
		while (iterador2.hasNext()) {
			arquitectura.add(iterador2.next().getId_arquitectura());
		}
		intercambio = servicio.getId_intercambio();
		Telefono phone = new Telefono();
		phone = (Telefono) read(phone, id_servicio_informacion);
		telefono = phone.getTelefono().substring(3, 10);
		codigo = phone.getTelefono().substring(0, 3);
		Correo email = new Correo();
		email = (Correo) getEmail(servicio, id_servicio_informacion);
		correo = email.getCorreo();
		sectores = (List<Sector>) read(new Sector());
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		niveles = (List<Seguridad>) read(new Seguridad());
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		parents = (List<Intercambio>) getParents(new Intercambio());
		children = (List<Intercambio>) getChildren(new Intercambio());
		modificar = true;
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
		session = ActionContext.getContext().getSession();
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
	private String saveFile() throws IOException {
		// TODO Obtener el nombre de la institución desde la base de datos.
		for (File u : files) {
			System.out.println("*** " + u + "\t" + u.length());
		}
		System.out.println("filenames:");
		for (String n : fileFileNames) {
			System.out.println("*** " + n);
		}
		System.out.println("content types:");
		for (String c : fileContentTypes) {
			System.out.println("*** " + c);
		}
		// String ENTE = "CNTI".toLowerCase();
		// String path = servletRequest.getSession().getServletContext()
		// .getRealPath("/archivos/" + ENTE);
		// FileUtils.copyFile(file, new File(path, name));
		// return "/archivos/" + ENTE + "/" + name;
		return "";
	}

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

	public List<File> getFiles() {
		return files;
	}

	public List<String> getFileContentTypes() {
		return fileContentTypes;
	}

	public List<String> getFileFileNames() {
		return fileFileNames;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public void setFileContentTypes(List<String> fileContentTypes) {
		this.fileContentTypes = fileContentTypes;
	}

	public void setFileFileNames(List<String> fileFileNames) {
		this.fileFileNames = fileFileNames;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public List<UnionAreaServicioInformacion> getUnionareas() {
		return unionareas;
	}

	public void setUnionareas(List<UnionAreaServicioInformacion> unionareas) {
		this.unionareas = unionareas;
	}

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public Funcionalidad getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(Funcionalidad funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

	public List<List<EntradaSalida>> getIos() {
		return ios;
	}

	public void setIos(List<List<EntradaSalida>> ios) {
		this.ios = ios;
	}

	public void setFile(List<File> files) {
		this.files = files;
	}

	public void setFileContentType(List<String> fileContentTypes) {
		this.fileContentTypes = fileContentTypes;
	}

	public void setFileFileName(List<String> fileFileNames) {
		this.fileFileNames = fileFileNames;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public boolean isModificar() {
		return modificar;
	}

	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}

	public List<UnionArquitecturaServicioInformacion> getUnionarquitecturas() {
		return unionarquitecturas;
	}

	public void setUnionarquitecturas(
			List<UnionArquitecturaServicioInformacion> unionarquitecturas) {
		this.unionarquitecturas = unionarquitecturas;
	}

	// @SuppressWarnings("unchecked")
	// @SkipValidation
	// public String registrarPrueba() {
	// id_servicio_informacion = getNextId(servicio);
	// servicio.setId_ente(1);
	// servicio.setId_usuario(1);
	// servicio.setId_sector(1);
	// servicio.setNombre("Nombre" + new Date());
	// servicio.setDescripcion("Descripción");
	// servicio.setId_estado(1);
	// servicio.setId_seguridad(1);
	// servicio.setVersion("1.0");
	// servicio.setId_intercambio(1);
	// servicio.setResponsable(responsable);
	// create(servicio);
	//
	// funcionalidad.setId_servicio_informacion(id_servicio_informacion);
	// funcionalidad.setNombre("Funcionalidad" + new Date());
	// funcionalidad.setDescripcion("Descripción");
	// create(funcionalidad);
	//
	// funcionalidades = (List<Funcionalidad>) read(FSI,
	// id_servicio_informacion, -1);
	// // funcionalidades = ((List<Funcionalidad>) read(funcionalidad,
	// // new ServicioInformacion(), getNextId(servicio) - 1));
	//
	// // UnionAreaServicioInformacion unionarea = new
	// // UnionAreaServicioInformacion();
	// // for (int i = 0; i < area.size(); i++) {
	// // unionarea.setId_area(Long.parseLong(String.valueOf(area.get(i))));
	// // unionarea.setId_servicio_informacion(id_servicio_informacion);
	// // // create(unionarea, id_si);
	// // }
	// //
	// // // Seteando el ARQUITECTURA
	// // UnionArquitecturaServicioInformacion unionarquitectura = new
	// // UnionArquitecturaServicioInformacion();
	// // for (int i = 0; i < arquitectura.size(); i++) {
	// // unionarquitectura.setId_arquitectura(Long.parseLong(String
	// // .valueOf(arquitectura.get(i))));
	// // unionarquitectura.setId_servicio_informacion(id_servicio_informacion);
	// // // create(unionarquitectura, id_si);
	// // }
	// //
	// // // Seteando el TELEFONO DE CONTACTO
	// // Telefono telf = new Telefono();
	// // telf.setTelefono(codArea + "-" + telefonoContacto);
	// // telf.setId_servicio_informacion(id_servicio_informacion);
	// // create(telf);
	// //
	// // // Seteando el CORREO DE CONTACTO
	// // Correo correo = new Correo();
	// // correo.setCorreo(correoContacto);
	// // correo.setId_servicio_informacion(id_servicio_informacion);
	// // create(correo);
	// return SUCCESS;
	// }
}
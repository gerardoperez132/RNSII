package ve.gob.cnti.srsi.controlador;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.Constants;
import ve.gob.cnti.srsi.dao.Constants.Modelos;
import ve.gob.cnti.srsi.dao.Constants.Tabs;
import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Area;
import ve.gob.cnti.srsi.modelo.Arquitectura;
import ve.gob.cnti.srsi.modelo.AspectoLegal;
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

@SuppressWarnings("serial")
public class ServicioInformacionControlador extends DAO implements Constants,
		ServletRequestAware, Tabs, Modelos {
	private List<Sector> sectores = new ArrayList<Sector>();
	private List<Estado> estados = new ArrayList<Estado>();
	private List<Area> areas = new ArrayList<Area>();
	private List<Seguridad> niveles = new ArrayList<Seguridad>();
	private List<Arquitectura> arquitecturas = new ArrayList<Arquitectura>();
	private List<Intercambio> parents = new ArrayList<Intercambio>();
	private List<Intercambio> children = new ArrayList<Intercambio>();

	private List<UnionAreaServicioInformacion> unionareas = new ArrayList<UnionAreaServicioInformacion>();
	private List<UnionArquitecturaServicioInformacion> unionarquitecturas = new ArrayList<UnionArquitecturaServicioInformacion>();

	private List<Funcionalidad> funcionalidades = new ArrayList<Funcionalidad>();
	private Funcionalidad funcionalidad;

	private List<List<EntradaSalida>> ios = new ArrayList<List<EntradaSalida>>();

	private boolean modificar;
	private boolean nuevo;
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
	private String codigos[] = CODES;

	private HttpServletRequest servletRequest;
	private Ente ente;
	@SuppressWarnings("rawtypes")
	private Map session;
	private ServicioInformacion servicio = new ServicioInformacion();

	private List<AspectoLegal> files = new ArrayList<AspectoLegal>();
	@SuppressWarnings("unused")
	private List<AspectoLegal> files2 = new ArrayList<AspectoLegal>();
	private File file;
	private String fileContentType;
	private String fileFileName;
	private String name;
	private boolean isValidate;

	private long id_servicio_informacion;
	private long id_aspecto_legal;

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararRegistro() {
		tab = DESCRIPCION_GENERAL;
		sectores = (List<Sector>) read(new Sector());
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		id_servicio_informacion = getNextId(servicio);
		System.out.println("ID en prepararRegistro => "
				+ id_servicio_informacion);
		setNuevo(true);
		cleanSessionStack();
		setSessionStack();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararDescripcionGeneral() {
		getSessionStack(isValidate);
		if (isComplete(servicio))
			setModificar(true);
		tab = DESCRIPCION_GENERAL;
		sectores = (List<Sector>) read(new Sector());
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararAspectosLegales() {		
		getSessionStack(isValidate);
		try {
			files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
			if (isComplete(servicio))
				setModificar(true);
		} catch (Exception e) {
		}
		tab = ASPECTOS_LEGALES;
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararDescripcionTecnica() {
		getSessionStack(isValidate);
		if (isComplete(servicio))
			setModificar(true);
		tab = DESCRIPCION_TECNICA;
		niveles = (List<Seguridad>) read(new Seguridad());
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		parents = (List<Intercambio>) getParents(new Intercambio());
		children = (List<Intercambio>) getChildren(new Intercambio());
		return SUCCESS;
	}

	@SkipValidation
	public String prepararDescripcionSoporte() {
		getSessionStack(isValidate);
		if(modificar){
			Telefono phone = new Telefono();
			try {
				phone = (Telefono) getPhone(servicio, id_servicio_informacion);
				telefono = phone.getTelefono();
			} catch (Exception e) {}
			Correo email = new Correo();		
			try {
				email = (Correo) getEmail(servicio, id_servicio_informacion);
				correo = email.getCorreo();
			} catch (Exception e) {}
		}
		if (isComplete(servicio)) {
			setModificar(true);
		}
		tab = DESCRIPCION_SOPORTE;
		return SUCCESS;
	}

	public String registrarDescripcionGeneral()
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// Obtener de la sesión
		try {
			setNuevo((Boolean) session.get("nuevo"));
			setModificar((Boolean) session.get("modificar"));
			setEnte(((Ente) session.get("ente")));
			setId_servicio_informacion((Long) session
					.get("id_servicio_informacion"));
			Usuario user = new Usuario();
			user = (Usuario) session.get("usuario");
			servicio.setId_usuario(user.getId_usuario());
		} catch (Exception e) {
			// Exception.
		}		
		String nombre = servicio.getNombre();
		String descripcion = servicio.getDescripcion();
		servicio.setId_ente(ente.getId_ente());
		servicio.setId_estado(estado);
		servicio.setId_sector(sector);
		if (isModificar() && isComplete(servicio)) {
			update(servicio, id_servicio_informacion);
			try {
				updateUnion(new UnionAreaServicioInformacion(),
						new ServicioInformacion(), new Area(),
						id_servicio_informacion, area);
			} catch (Exception e) {
				System.out.println("Error guardando las áreas");
			}
		}
		if (isNuevo()) {
			create(servicio);
			UnionAreaServicioInformacion unionAreaServicioInformacion = new UnionAreaServicioInformacion();
			for (int i = 0; i < area.size(); i++) {
				unionAreaServicioInformacion.setId_area(area.get(i));
				unionAreaServicioInformacion
						.setId_servicio_informacion(id_servicio_informacion);
				createUnion(unionAreaServicioInformacion);
			}
			setNuevo(false);
		} else {
			servicio = (ServicioInformacion) read(servicio,
					id_servicio_informacion);
			servicio.setNombre(nombre);
			servicio.setDescripcion(descripcion);
			servicio.setId_ente(ente.getId_ente());
			servicio.setId_estado(estado);			
			servicio.setId_sector(sector);			
			update(servicio);
			try {
				updateUnion(new UnionAreaServicioInformacion(),
						new ServicioInformacion(), new Area(),
						id_servicio_informacion, area);
			} catch (Exception e) {
				System.out.println("Error guardando las áreas");
			}
		}
		setSessionStackGeneral();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String registrarAspectosLegales() throws IOException {
		getSessionStack(isValidate);
		if (name.trim().isEmpty() && file != null) {
			addFieldError("name",
					"Si va a subir un archivo debe introducir un nombre");
			addFieldError("file", "Suba nuevamente el archivo");
			files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
			return INPUT;
		}
		if (!name.trim().isEmpty() && file == null) {
			addFieldError("name",
					"Si va a colocar un nombre debe subir un archivo");
			addFieldError("file", "Por favor seleccione un archivo para subir");
			files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
			return INPUT;
		}
		if (name.trim().isEmpty() && file == null) {
			addFieldError("name",
					"Si va a subir un documento, rellene todos los campos");
			files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
			return INPUT;
		}
		if (!fileContentType.equals("application/pdf")
				&& !fileContentType.equals("application/x-pdf")
				&& !fileContentType.equals("application/x-bzpdf")
				&& !fileContentType.equals("application/x-gzpdf")) {
			addFieldError("file", "Sólo se admiten archivos PDF");
			files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
			return INPUT;
		}
		if (file.length() > (FileUtils.ONE_MB * 2)) {
			addFieldError("file", "Tamaño máximo por archivo => 2 MB");
			files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
			return INPUT;
		}
		System.out.println("IMPRIMIENDO EN SET ASPECTO LEGAL => "
				+ id_servicio_informacion);
		AspectoLegal documento = new AspectoLegal();
		documento.setId_servicio_informacion(id_servicio_informacion);
		documento.setNombre(name);
		documento.setUrl(saveFile());
		create(documento);
		files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
		name = "";
		return SUCCESS;
	}

	private String saveFile() throws IOException {
		session = ActionContext.getContext().getSession();
		String siglas = ((Ente) session.get("ente")).getSiglas().toString()
				.toLowerCase();
		String path = servletRequest.getSession().getServletContext()
				.getRealPath(PATH + siglas + "/");
		String filename = name.replace(" ", "_") + "_" + new Date().getTime()
				+ ".pdf";
		FileUtils.copyFile(file, new File(path, filename));
		return PATH + siglas + "/" + filename;
	}

	public String deleteFile() {		
		getSessionStack(isValidate);
		AspectoLegal documento = (AspectoLegal) read(new AspectoLegal(),
				id_aspecto_legal);
		if (id_servicio_informacion == documento.getId_servicio_informacion()) {
			String path = servletRequest.getSession().getServletContext()
					.getRealPath(documento.getUrl());
			File file = new File(path);
			file.delete();
			delete(documento, documento.getId_aspecto_legal());
			prepararAspectosLegales();
			return SUCCESS;
		} else {
			addFieldError("file", "Error. Credenciales inválidas");
			prepararAspectosLegales();
			return INPUT;
		}
	}

	public String registrarDescripcionTecnica() throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// Obtener de la sesión TODO
		try {		
			setModificar((Boolean) session.get("modificar"));			
			setId_servicio_informacion((Long) session
					.get("id_servicio_informacion"));
		} catch (Exception e) {
			// Exception.
		}	
		String version = servicio.getVersion();
		List<Long> arq = new ArrayList<Long>();
		long seguridad_tmp = seguridad;
		long intercambio_tmp = intercambio;
		arq = arquitectura;
		getSessionStack(isValidate);
		servicio.setId_seguridad(seguridad);
		servicio.setId_intercambio(intercambio);
		servicio.setVersion(version);
		arquitectura = arq;
		seguridad = seguridad_tmp;
		intercambio = intercambio_tmp;				
		if (isModificar() && isComplete(servicio)) {
			update(servicio, id_servicio_informacion);
			try {
				updateUnion(new UnionArquitecturaServicioInformacion(),
						new ServicioInformacion(), new Arquitectura(),
						id_servicio_informacion, arquitectura);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			servicio = (ServicioInformacion) read(servicio,
					id_servicio_informacion);
			servicio.setId_seguridad(seguridad);
			servicio.setId_intercambio(intercambio);
			servicio.setVersion(version);
			update(servicio);
		}
		UnionArquitecturaServicioInformacion unionArquitecturaServicioInformacion = new UnionArquitecturaServicioInformacion();
		for (int i = 0; i < arquitectura.size(); i++) {
			unionArquitecturaServicioInformacion
					.setId_arquitectura(arquitectura.get(i));
			unionArquitecturaServicioInformacion
					.setId_servicio_informacion(id_servicio_informacion);
			createUnion(unionArquitecturaServicioInformacion);
		}
		setSessionStacktechnique();
		return SUCCESS;
	}

	public String registrarDescripcionSoporte() throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {		
		// TODO Utilizar otro método para la inserción de los datos en el mismo
		// registro.
		try {		
			setModificar((Boolean) session.get("modificar"));			
			setId_servicio_informacion((Long) session
					.get("id_servicio_informacion"));
		} catch (Exception e) {
			// Exception.
		}
		String responsable = servicio.getResponsable();
		Telefono phone = new Telefono();
		phone.setId_servicio_informacion(id_servicio_informacion);
		phone.setTelefono(codigo + telefono);
		Correo email = new Correo();
		email.setId_servicio_informacion(id_servicio_informacion);
		email.setCorreo(correo);
		
		servicio = (ServicioInformacion) read(servicio,
				id_servicio_informacion);
		servicio.setResponsable(responsable);
		if (!isModificar()) {			
			servicio.setResponsable(responsable);
			update(servicio);
			create(phone);
			create(email);
		} else if (isModificar() && isComplete(servicio)) {			
			update(servicio, id_servicio_informacion);
			update(phone, id_servicio_informacion);
			update(email, id_servicio_informacion);
		}else{
			Telefono phone2 = getPhone(phone, id_servicio_informacion);
			if(phone2 == null){
				update(servicio, id_servicio_informacion);
				create(phone);
				create(email);
			}else{
				update(servicio, id_servicio_informacion);
				update(phone, id_servicio_informacion);
				update(email, id_servicio_informacion);
			}			
		}		
		return SUCCESS;
	}

	@Override
	public void validate() {
		isValidate = true;
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
			// números y un solo punto. Además que el formato sea con 0.X en
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
			if (!correo
					.matches("^[a-zA-Z0-9_-].{1,}@[a-zA-Z0-9_-]{2,}\\.[a-zA-Z]{2,4}(\\.[a-zA-Z]{2,4})?$"))
				addFieldError("correo",
						"Debe introducir una dirección de correo válida");
			// TODO Se debe validar que la expresión regular acepte solamente un
			// arroba.
			prepararDescripcionSoporte();
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
		session.put("nuevo", nuevo);
		session.put("modificar", modificar);
		session.put("id_servicio_informacion", id_servicio_informacion);
	}
	
	@SuppressWarnings("unchecked")
	private void setSessionStackGeneral() {
		session = ActionContext.getContext().getSession();
		session.put("servicio", servicio);
		session.put("sector", sector);
		session.put("area", area);
		session.put("estado", estado);
		session.put("nuevo", nuevo);
		session.put("modificar", modificar);
		session.put("id_servicio_informacion", id_servicio_informacion);
	}
	
	@SuppressWarnings("unchecked")
	private void setSessionStacktechnique() {
		session = ActionContext.getContext().getSession();
		session.put("servicio", servicio);		
		session.put("seguridad", seguridad);
		session.put("arquitectura", arquitectura);
		session.put("intercambio", intercambio);		
		session.put("modificar", modificar);
		session.put("id_servicio_informacion", id_servicio_informacion);
	}

	@SuppressWarnings("unchecked")
	private void getSessionStack(boolean isValidate) {
		session = ActionContext.getContext().getSession();
		if (!isValidate) {
			try {
				servicio = (ServicioInformacion) session.get("servicio");
				sector = (Long) session.get("sector");
				area = (List<Long>) session.get("area");
				estado = (Long) session.get("estado");
				seguridad = (Long) session.get("seguridad");
				arquitectura = (List<Long>) session.get("arquitectura");
				intercambio = (Long) session.get("intercambio");
			} catch (Exception e) {
				// TODO Handling the exception?!
				System.out.println("NO HAY NADA EN LA PILA");
			}
		}
		try {
			setModificar((Boolean) session.get("modificar"));
			setNuevo((Boolean) session.get("nuevo"));
			setId_servicio_informacion((Long) session
					.get("id_servicio_informacion"));
		} catch (Exception e) {
			System.out.println("Ha habido un problema.");
		}
	}

	private void cleanSessionStack() {
		session = ActionContext.getContext().getSession();
		try {
			session.remove("servicio");
			session.remove("sector");
			session.remove("area");
			session.remove("estado");
			session.remove("seguridad");
			session.remove("arquitectura");
			session.remove("intercambio");
			session.remove("telefono");
			session.remove("correo");
			session.remove("nuevo");
			session.remove("modificar");
		} catch (Exception e) {
			// TODO Handling the exception?!
		}
	}

	// Examinar
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String examinarServicioInformacion() {
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		try {
			unionareas = (List<UnionAreaServicioInformacion>) readUnion(
					new UnionAreaServicioInformacion(), servicio,
					id_servicio_informacion);
		} catch (Exception e) {
		}
		try {
			unionarquitecturas = (List<UnionArquitecturaServicioInformacion>) readUnion(
					new UnionArquitecturaServicioInformacion(), servicio,
					id_servicio_informacion);
		} catch (Exception e) {
		}
		Telefono phone = new Telefono();
		phone = (Telefono) getPhone(servicio, id_servicio_informacion);
		try {
			telefono = phone.getTelefono();
		} catch (Exception e) {
		}
		Correo email = new Correo();
		email = (Correo) getEmail(servicio, id_servicio_informacion);
		try {
			correo = email.getCorreo();
		} catch (Exception e) {
		}
		funcionalidades = (List<Funcionalidad>) read(FSI,
				id_servicio_informacion, -1);
		Iterator<Funcionalidad> iterador = funcionalidades.iterator();
		while (iterador.hasNext()) {
			funcionalidad = iterador.next();
			try {
				List<EntradaSalida> es_tmp = (List<EntradaSalida>) read(ESF,
						funcionalidad.getId_funcionalidad(), -1);
				ios.add(es_tmp);
			} catch (Exception e) {
				// No tiene entradas ni salidas.
			}
		}
		sectores = (List<Sector>) read(new Sector());
		estados = (List<Estado>) read(new Estado());
		sectores = (List<Sector>) read(new Sector());
		areas = (List<Area>) read(new Area());
		niveles = (List<Seguridad>) read(new Seguridad());
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		children = (List<Intercambio>) read(new Intercambio());
		return SUCCESS;
	}

	// Publicar
	@SkipValidation
	public String publicarServicioInformacion() {
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		servicio.setPublicado(true);
		update(servicio, id_servicio_informacion);
		return SUCCESS;
	}

	// Despublicar
	@SkipValidation
	public String despublicarServicioInformacion() {
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		servicio.setPublicado(false);
		update(servicio, id_servicio_informacion);
		return SUCCESS;
	}

	// Eliminar
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String eliminarServicioInformacion() {
		Funcionalidad funcion_del = new Funcionalidad();
		EntradaSalida io_del = new EntradaSalida();
		List<EntradaSalida> ios_del = new ArrayList<EntradaSalida>();
		funcionalidades = (List<Funcionalidad>) read(FSI,
				id_servicio_informacion, -1);
		Iterator<Funcionalidad> iterador = funcionalidades.iterator();
		while (iterador.hasNext()) {
			funcion_del = iterador.next();
			ios_del = (List<EntradaSalida>) read(ESF,
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

	// Modificar
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
		try {
			telefono = phone.getTelefono();
			phone = (Telefono) read(phone, id_servicio_informacion);
			telefono = phone.getTelefono().substring(3, 10);
			codigo = phone.getTelefono().substring(0, 3);
		} catch (Exception e) {
		}
		Correo email = new Correo();
		try {
			email = (Correo) getEmail(servicio, id_servicio_informacion);
			correo = email.getCorreo();
		} catch (Exception e) {
		}
		sectores = (List<Sector>) read(new Sector());
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		niveles = (List<Seguridad>) read(new Seguridad());
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		parents = (List<Intercambio>) getParents(new Intercambio());
		children = (List<Intercambio>) getChildren(new Intercambio());
		setModificar(true);
		files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
		setSessionStack();
		prepararDescripcionGeneral();
		return SUCCESS;
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

	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
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

	@SuppressWarnings("rawtypes")
	public Map getSession() {
		return session;
	}

	public void setSession(@SuppressWarnings("rawtypes") Map session) {
		this.session = session;
	}

	public ServicioInformacion getServicio() {
		return servicio;
	}

	public void setServicio(ServicioInformacion servicio) {
		this.servicio = servicio;
	}

	public List<AspectoLegal> getFiles() {
		return files;
	}

	public void setFiles(List<AspectoLegal> files) {
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

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
	}

	public List<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public Funcionalidad getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(Funcionalidad funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

	public boolean isModificar() {
		return modificar;
	}

	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}

	public List<List<EntradaSalida>> getIos() {
		return ios;
	}

	public void setIos(List<List<EntradaSalida>> ios) {
		this.ios = ios;
	}

	public boolean isValidate() {
		return isValidate;
	}

	public void setValidate(boolean isValidate) {
		this.isValidate = isValidate;
	}

	public long getId_aspecto_legal() {
		return id_aspecto_legal;
	}

	public void setId_aspecto_legal(long id_aspecto_legal) {
		this.id_aspecto_legal = id_aspecto_legal;
	}

	@SuppressWarnings("unchecked")
	public List<AspectoLegal> getFiles2() {
		return (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
	}

	public void setFiles2(List<AspectoLegal> files2) {
		this.files2 = files2;
	}
}
package ve.gob.cnti.srsi.controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
	private File file;
	private String fileContentType;
	private String fileFileName;
	private String name;
	private boolean isValidate;

	private long id_servicio_informacion;

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararRegistro() {
		tab = DESCRIPCION_GENERAL;
		sectores = (List<Sector>) read(new Sector());
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		setNuevo(true);
		cleanSessionStack();
		setSessionStack();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararDescripcionGeneral() {
		getSessionStack(isValidate);
		if (servicio == null)
			setNuevo(true);
		else {
			setNuevo(false);
			if (isComplete(servicio))
				setModificar(true);
		}
		tab = DESCRIPCION_GENERAL;
		sectores = (List<Sector>) read(new Sector());
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararAspectosLegales() {
		// TODO La tablita esa.
		getSessionStack(isValidate);
		try {
			files = (List<AspectoLegal>) read(ALSI,
					servicio.getId_servicio_informacion(), -1);
			if (isComplete(servicio))
				setModificar(true);
		} catch (Exception e) {}				
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
		if (isComplete(servicio)){
			setModificar(true);			
		}
		tab = DESCRIPCION_SOPORTE;
		return SUCCESS;
	}

	public String registrarDescripcionGeneral() {
		session = ActionContext.getContext().getSession();
		try {
			servicio.setId_usuario(((Usuario) session.get("usuario"))
					.getId_usuario());
			nuevo = (Boolean)session.get("nuevo");
		} catch (Exception e) {
			return "errorSession";
		}
		servicio.setId_ente(1);
		servicio.setId_estado(estado);
		servicio.setId_intercambio(intercambio);
		servicio.setId_sector(sector);
		servicio.setId_seguridad(seguridad);		
		if (isNuevo()) {
			create(servicio);
			UnionAreaServicioInformacion unionAreaServicioInformacion = new UnionAreaServicioInformacion();
			for (int i = 0; i < area.size(); i++) {
				unionAreaServicioInformacion.setId_area(area.get(i));
				unionAreaServicioInformacion
						.setId_servicio_informacion(getNextId(servicio) - 1);
				createUnion(unionAreaServicioInformacion);
			}
		}
		// TODO else update
		
		servicio.setId_servicio_informacion(getNextId(servicio) - 1);
		setSessionStack();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String registrarAspectosLegales() throws IOException {
		getSessionStack(isValidate);
		// TODO Borrar estos logs.
		System.out.println("File => " + file);
		System.out.println("Name => " + name);
		System.out.println("FileName => " + fileFileName);
		System.out.println("ContentType => " + fileContentType);
		if (name.trim().isEmpty() && file != null) {
			addFieldError("name",
					"Si va a subir un archivo debe introducir un nombre");
			addFieldError("file", "Suba nuevamente el archivo");
			files = (List<AspectoLegal>) read(ALSI,
					servicio.getId_servicio_informacion(), -1);
			return INPUT;
		}
		if (!name.trim().isEmpty() && file == null) {
			addFieldError("name",
					"Si va a colocar un nombre debe subir un archivo");
			addFieldError("file", "Por favor seleccione un archivo para subir");
			files = (List<AspectoLegal>) read(ALSI,
					servicio.getId_servicio_informacion(), -1);
			return INPUT;
		}
		System.out.println("IMPRIMIENDO EN SET ASPECTO LEGAL => "
				+ servicio.getId_servicio_informacion());
		AspectoLegal documento = new AspectoLegal();
		documento.setId_servicio_informacion(servicio
				.getId_servicio_informacion());
		documento.setNombre(name);
		documento.setUrl(saveFile());
		create(documento);
		files = (List<AspectoLegal>) read(ALSI,
				servicio.getId_servicio_informacion(), -1);
		for (AspectoLegal as : files)
			System.out.println("Aspectos Legales (registrar) => "
					+ as.toString());
		// update(servicio, servicio.getId());
		return SUCCESS;
	}

	private String saveFile() throws IOException {
		session = ActionContext.getContext().getSession();
		String siglas = ((Ente) session.get("ente")).getSiglas().toString()
				.toLowerCase();
		String path = servletRequest.getSession().getServletContext()
				.getRealPath(PATH + siglas + "/");
		FileUtils.copyFile(file, new File(path, fileFileName));
		return PATH + siglas + "/" + fileFileName;
	}

	public String registrarDescripcionTecnica() {	
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
		setSessionStack();		
		// TODO Utilizar otro método para la inserción de los datos en el mismo
		// registro.
		// update(servicio, servicio.getId_servicio_informacion());
		if (isComplete(servicio)) {
			update(servicio, servicio.getId_servicio_informacion());
		} else {
			// TODO Inserción física en la misma tupla.
		}
		UnionArquitecturaServicioInformacion unionArquitecturaServicioInformacion = new UnionArquitecturaServicioInformacion();
		for (int i = 0; i < arquitectura.size(); i++) {
			unionArquitecturaServicioInformacion
					.setId_arquitectura(arquitectura.get(i));
			unionArquitecturaServicioInformacion
					.setId_servicio_informacion(id_servicio_informacion);
			createUnion(unionArquitecturaServicioInformacion);
		}		
		return SUCCESS;
	}

	public String registrarDescripcionSoporte() {
		getSessionStack(isValidate);
		// TODO Utilizar otro método para la inserción de los datos en el mismo
		// registro.
		Telefono phone = new Telefono();
		phone.setId_servicio_informacion(servicio.getId_servicio_informacion());
		phone.setTelefono(codigo + telefono);
		Correo email = new Correo();
		email.setId_servicio_informacion(servicio.getId_servicio_informacion());
		email.setCorreo(correo);
		id_servicio_informacion = servicio.getId_servicio_informacion();
		create(phone);
		create(email);
		// update(servicio, servicio.getId_servicio_informacion());
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
	}

	@SuppressWarnings("unchecked")
	private void getSessionStack(boolean isValidate) {
		session = ActionContext.getContext().getSession();	
		if(!isValidate){
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
			nuevo = (Boolean) session.get("nuevo");
			modificar = (Boolean) session.get("modificar");
		} catch (Exception e) {
			// TODO Handling the exception?!
			System.out.println("NO HAY NADA EN LA PILA");
		}
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
		phone = (Telefono) getPhone(servicio,
				servicio.getId_servicio_informacion());
		try {
			telefono = phone.getTelefono();
		} catch (Exception e) {
		}
		Correo email = new Correo();
		email = (Correo) getEmail(servicio,
				servicio.getId_servicio_informacion());
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
	public String modificarServicioInformacion() {
		getSessionStack(isValidate);
		Usuario usuario = new Usuario();
		usuario = (Usuario) session.get("usuario");
		if (usuario == null) {
			return "errorSession";
		}
		ServicioInformacion servicio2 = (ServicioInformacion) read(servicio,
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
		setModificar(false);
		setNuevo(false);
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
		try {
			telefono = phone.getTelefono();
			phone = (Telefono) read(phone, id_servicio_informacion);
			telefono = phone.getTelefono().substring(3, 10);
			codigo = phone.getTelefono().substring(0, 3);
		} catch (Exception e) {}
		Correo email = new Correo();		
		try {
			email = (Correo) getEmail(servicio,
					servicio.getId_servicio_informacion());
			correo = email.getCorreo();
		} catch (Exception e) {}
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
}
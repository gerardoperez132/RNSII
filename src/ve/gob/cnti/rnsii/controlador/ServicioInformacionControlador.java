/* This file is part of RNSII.
 * 
 * RNSII is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * RNSII is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with RNSII. If not, see <http://www.gnu.org/licenses/>.
 */
package ve.gob.cnti.rnsii.controlador;

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

import ve.gob.cnti.rnsii.dao.Constants;
import ve.gob.cnti.rnsii.dao.Constants.Modelos;
import ve.gob.cnti.rnsii.dao.Constants.Order;
import ve.gob.cnti.rnsii.dao.Constants.Tabs;
import ve.gob.cnti.rnsii.dao.DAO;
import ve.gob.cnti.rnsii.i18n.Messages;
import ve.gob.cnti.rnsii.modelo.Area;
import ve.gob.cnti.rnsii.modelo.Arquitectura;
import ve.gob.cnti.rnsii.modelo.AspectoLegal;
import ve.gob.cnti.rnsii.modelo.Correo;
import ve.gob.cnti.rnsii.modelo.Ente;
import ve.gob.cnti.rnsii.modelo.EntradaSalida;
import ve.gob.cnti.rnsii.modelo.Estado;
import ve.gob.cnti.rnsii.modelo.Funcionalidad;
import ve.gob.cnti.rnsii.modelo.Intercambio;
import ve.gob.cnti.rnsii.modelo.Sector;
import ve.gob.cnti.rnsii.modelo.Seguridad;
import ve.gob.cnti.rnsii.modelo.ServicioInformacion;
import ve.gob.cnti.rnsii.modelo.Telefono;
import ve.gob.cnti.rnsii.modelo.UnionAreaServicioInformacion;
import ve.gob.cnti.rnsii.modelo.UnionArquitecturaServicioInformacion;
import ve.gob.cnti.rnsii.modelo.Url;
import ve.gob.cnti.rnsii.modelo.Usuario;
import ve.gob.cnti.rnsii.util.Tabs_incompletes;

import com.opensymphony.xwork2.ActionContext;

/**
 * Esta clase es el controlador de los servicios de información.
 * 
 * @author Richard Ricciardelli
 * @author Joaquín Pereira
 * 
 */
@SuppressWarnings("serial")
public class ServicioInformacionControlador extends DAO implements Constants,
		ServletRequestAware, Tabs, Modelos, Order {
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
	private String wsdl;
	private String telefono;
	private String correo;
	private String codigo;
	private String codigos[] = CODES;

	private HttpServletRequest servletRequest;
	private Ente ente = new Ente();
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
	private long nVisitas;

	private String submit;	

	private Date fecha;
	
	private List<Tabs_incompletes> tabs_incompletas = new ArrayList<Tabs_incompletes>(); 
	
	public static Messages message = new Messages();

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararRegistro() {

		tab = DESCRIPCION_GENERAL;
		// sectores = (List<Sector>) read(new Sector());
		sectores = (List<Sector>) getSortedList(new Sector(), ASC);
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		id_servicio_informacion = getNextId(servicio);
		setNuevo(true);
		if (cleanSessionStack().equals("error"))
			return "errorSession";
		if (setSessionStack().equals("error"))
			return "errorSession";
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararDescripcionGeneral() {

		if (getSessionStack(isValidate).equals("error"))
			return "errorSession";
		if (isComplete(servicio))
			setModificar(true);		
		tab = DESCRIPCION_GENERAL;
		// sectores = (List<Sector>) read(new Sector());
		sectores = (List<Sector>) getSortedList(new Sector(), ASC);
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		if(servicio.getId_servicio_informacion()!=0)
			tabs_incompletas = getIncompleteFields2(servicio);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararAspectosLegales() {		

		if (getSessionStack(isValidate).equals("error"))
			return "errorSession";
		try {
			files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
			if (isComplete(servicio))
				setModificar(true);
		} catch (Exception e) {
			return "error";
		}
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		tabs_incompletas = getIncompleteFields2(servicio);
		tab = ASPECTOS_LEGALES;
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String prepararDescripcionTecnica() {

		if (getSessionStack(isValidate).equals("error"))
			return "errorSession";
		if (isComplete(servicio))
			setModificar(true);
		tab = DESCRIPCION_TECNICA;
		niveles = (List<Seguridad>) read(new Seguridad());
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		parents = (List<Intercambio>) getParents(new Intercambio());
		children = (List<Intercambio>) getChildren(new Intercambio());
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		tabs_incompletas = getIncompleteFields2(servicio);
		return SUCCESS;
	}

	@SkipValidation
	public String prepararDescripcionSoporte() {

		if (getSessionStack(isValidate).equals("error"))
			return "errorSession";
		if (isComplete(servicio)) {
			setModificar(true);
		}
		tab = DESCRIPCION_SOPORTE;
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		tabs_incompletas = getIncompleteFields2(servicio);
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
			Usuario user = (Usuario) session.get("usuario");
			servicio.setId_usuario(user.getId_usuario());
		} catch (Exception e) {
			return "errorSession";
		}
		String nombre = servicio.getNombre();
		String descripcion = servicio.getDescripcion();
		long estado_tmp = estado;
		long sector_tmp = sector;
		List<Long> area_tmp = area;
		System.out.println("ESTADO ANTES => " + estado_tmp);
		getSessionStack(false);
		estado = estado_tmp;
		sector = sector_tmp;
		System.out.println("ESTADO después => " + estado_tmp);
		area = area_tmp;
		servicio.setId_ente(ente.getId_ente());
		servicio.setId_estado(estado);
		servicio.setId_sector(sector);
		
		if (isModificar() && isComplete(servicio)) {
			update(servicio, id_servicio_informacion);
			Usuario user = (Usuario) session.get("usuario");
			UnionAreaServicioInformacion unionAreaServicioInformacion = new UnionAreaServicioInformacion();
			unionAreaServicioInformacion.setId_usuario(user.getId_usuario());
			try {
				updateUnion(unionAreaServicioInformacion,
						new ServicioInformacion(), new Area(),
						id_servicio_informacion, area);
			} catch (Exception e) {
				System.out.println("Error guardando las áreas");
			}
		}
		System.out.println("NUEVO ES => " + isNuevo());
		if (isNuevo()) {
			servicio.setNombre(nombre);
			servicio.setDescripcion(descripcion);
			create(servicio);
			UnionAreaServicioInformacion unionAreaServicioInformacion = new UnionAreaServicioInformacion();
			for (int i = 0; i < area.size(); i++) {
				Usuario user = (Usuario) session.get("usuario");
				unionAreaServicioInformacion.setId_area(area.get(i));
				unionAreaServicioInformacion
						.setId_usuario(user.getId_usuario());
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
			Usuario user = (Usuario) session.get("usuario");
			UnionAreaServicioInformacion unionAreaServicioInformacion = new UnionAreaServicioInformacion();
			unionAreaServicioInformacion.setId_usuario(user.getId_usuario());
			try {
				updateUnion(unionAreaServicioInformacion,
						new ServicioInformacion(), new Area(),
						id_servicio_informacion, area);
			} catch (Exception e) {
				System.out.println("Error guardando las áreas");
			}
		}		
		servicio.setId_servicio_informacion(id_servicio_informacion);
		if (setSessionStack().equals("error"))
			return "errorSession";
		
		if(submit != null && submit.contentEquals(message.getProperties().getProperty("registro.finalizar"))){			
				addActionMessage(message.getProperties().getProperty("registro.servicio.success"));
			return "end";
		}
			
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String registrarAspectosLegales() throws IOException {

		getSessionStack(false);
		if (name.trim().isEmpty() && file != null) {
			addFieldError("name",error.getProperties().getProperty("error.servicio.file.name"));
			addFieldError("file",error.getProperties().getProperty("error.servicio.file"));
			files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
			return INPUT;
		}
		if (!name.trim().isEmpty() && file == null) {
			addFieldError("name",error.getProperties().getProperty("error.servicio.file.file"));
			addFieldError("file",error.getProperties().getProperty("error.servicio.file"));
			files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
			return INPUT;
		}
		if (name.trim().isEmpty() && file == null) {
			addFieldError("name",error.getProperties().getProperty(	"error.servicio.file.save"));
			files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
			return INPUT;
		}
		if (!fileContentType.equals("application/pdf")
				&& !fileContentType.equals("application/x-pdf")
				&& !fileContentType.equals("application/x-bzpdf")
				&& !fileContentType.equals("application/x-gzpdf")) {
			addFieldError("file",error.getProperties().getProperty("error.servicio.file.format"));
			files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
			return INPUT;
		}
		if (read(ALSI, id_servicio_informacion, name)) {
			addFieldError("name",error.getProperties().getProperty("error.servicio.file.repeated"));
			files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
			return INPUT;
		}
		Usuario user = new Usuario();
		user = (Usuario) session.get("usuario");
		AspectoLegal documento = new AspectoLegal();
		documento.setId_servicio_informacion(id_servicio_informacion);
		documento.setId_usuario(user.getId_usuario());
		documento.setNombre(name);
		documento.setUrl(saveFile());
		create(documento);
		files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
		name = "";
		
		servicio = (ServicioInformacion) read(servicio, id_servicio_informacion);
		tabs_incompletas = getIncompleteFields2(servicio);
		
		if(submit != null && submit.contentEquals(message.getProperties().getProperty("registro.finalizar"))){			
				addActionMessage(message.getProperties().getProperty("registro.servicio.success"));
			return "end";
		}
		
		return SUCCESS;
	}

	private String saveFile() throws IOException {
		session = ActionContext.getContext().getSession();
		String siglas = ((Ente) session.get("ente")).getSiglas().toString().toLowerCase();
		String path = servletRequest.getSession().getServletContext().getRealPath(PATH + siglas + "/");
		String filename = name.replace(" ", "_") + "_" + new Date().getTime() + ".pdf";
		FileUtils.copyFile(file, new File(path, filename));
		return PATH + siglas + "/" + filename;
	}

	public String deleteFile() {
		if (getSessionStack(isValidate).equals("error"))
			return "errorSession";
		AspectoLegal documento = (AspectoLegal) read(new AspectoLegal(),
				id_aspecto_legal);
		if (id_servicio_informacion == documento.getId_servicio_informacion()) {
			String path = servletRequest.getSession().getServletContext().getRealPath(documento.getUrl());
			File file = new File(path);
			file.delete();
			delete(documento, documento.getId_aspecto_legal());
			prepararAspectosLegales();
			return SUCCESS;
		} else {
			addFieldError("file",error.getProperties().getProperty("error.servicio.file.invalid"));
			prepararAspectosLegales();
			return INPUT;
		}
	}

	@SuppressWarnings("unchecked")
	public String registrarDescripcionTecnica()
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		try {
			setModificar((Boolean) session.get("modificar"));
			setId_servicio_informacion((Long) session
					.get("id_servicio_informacion"));
		} catch (Exception e) {
			// Exception.
		}
		
		Url url = new Url();
		url.setId_servicio_informacion(id_servicio_informacion);
		url.setUrl(wsdl);
		
		String version = servicio.getVersion();
		
		List<Long> arq = new ArrayList<Long>();
		
		long seguridad_tmp = seguridad;
		
		long intercambio_tmp = intercambio;
		
		String wsdl_tmp = wsdl;
		
		arq = arquitectura;
		
		getSessionStack(false);
		
		servicio.setId_seguridad(seguridad);
		servicio.setId_intercambio(intercambio);
		servicio.setVersion(String.valueOf(Float.parseFloat(version)));
		arquitectura = arq;
		seguridad = seguridad_tmp;
		intercambio = intercambio_tmp;
		wsdl = wsdl_tmp;
		
		if (isModificar()) {
			url = getUrl(servicio, id_servicio_informacion);
			if (url != null) {
				url.setId_servicio_informacion(id_servicio_informacion);
				url.setUrl(wsdl);
				update(url, url.getId_url());
			} else {
				url = new Url();
				url.setId_servicio_informacion(id_servicio_informacion);
				url.setUrl(wsdl);
				create(url);
			}
		} else {
			url = new Url();
			url.setId_servicio_informacion(id_servicio_informacion);
			url.setUrl(wsdl);
			create(url);
		}
		
		
		if (isModificar() && isComplete(servicio)) {
			update(servicio, id_servicio_informacion);			
			Usuario user = (Usuario) session.get("usuario");
			UnionArquitecturaServicioInformacion unionArquitecturaServicioInformacion = new UnionArquitecturaServicioInformacion();
			unionArquitecturaServicioInformacion.setId_usuario(user.getId_usuario());
			try {
				updateUnion(unionArquitecturaServicioInformacion,
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
			servicio.setVersion(String.valueOf(Float.parseFloat(version)));
			update(servicio);					
			UnionArquitecturaServicioInformacion unionArquitecturaServicioInformacion = new UnionArquitecturaServicioInformacion();
			unionarquitecturas = (List<UnionArquitecturaServicioInformacion>) readUnion(
					new UnionArquitecturaServicioInformacion(), servicio, id_servicio_informacion);
			if(unionarquitecturas.size()==0){
				for (int i = 0; i < arquitectura.size(); i++) {
					Usuario user = (Usuario) session.get("usuario");
					unionArquitecturaServicioInformacion
							.setId_arquitectura(arquitectura.get(i));
					unionArquitecturaServicioInformacion
							.setId_servicio_informacion(id_servicio_informacion);
					unionArquitecturaServicioInformacion.setId_usuario(user
							.getId_usuario());
					createUnion(unionArquitecturaServicioInformacion);
				}
			}else{
				Usuario user = (Usuario) session.get("usuario");
				unionArquitecturaServicioInformacion.setId_usuario(user.getId_usuario());
				try {
					updateUnion(unionArquitecturaServicioInformacion,
							new ServicioInformacion(), new Arquitectura(),
							id_servicio_informacion, arquitectura);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
		if (setSessionStack().equals("error"))
			return "errorSession";
		
		if(submit != null && submit.contentEquals(message.getProperties().getProperty("registro.finalizar"))){			
				addActionMessage(message.getProperties().getProperty("registro.servicio.success"));
			return "end";
		}
		
		return SUCCESS;
	}

	public String registrarDescripcionSoporte()
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		/*
		 * Recuperando de la sesión la variables modificar
		 * yid_servicio_informacion necesarias para el registro
		 */
		try {
			setModificar((Boolean) session.get("modificar"));
			setId_servicio_informacion((Long) session
					.get("id_servicio_informacion"));
		} catch (Exception e) {
		}

		// Creando objetos temporales para el registro de una petición de
		// creación ó modificación.
		Telefono phone = new Telefono();
		Correo email = new Correo();

		/*
		 * Creando variables temporales para utilizarlas en el caso de que el
		 * registro sea una modificación y tener respaldo de los valores que
		 * lleggan del formulario y se pierden al utilizar el
		 * getSessionStack(false);
		 */
		String responsable = servicio.getResponsable();
		String telefono_tmp = telefono;
		String email_tmp = correo;
		String codigo_tmp = codigo;

		/*
		 * Asignando los valores del teléfono, usados en la creación de un
		 * registro teléfono.
		 */
		phone.setId_servicio_informacion(id_servicio_informacion);
		phone.setTelefono(codigo + telefono);

		/*
		 * Asignando los valores del correo, usados en la creación de un
		 * registro correo.
		 */
		email.setId_servicio_informacion(id_servicio_informacion);
		email.setCorreo(correo);

		// recuperando los valores de la sesion
		getSessionStack(false);

		/*
		 * Asignando los valores de las variables del formulario descripción
		 * técnica, los cuales son usados si el registro es de modificación.
		 */
		servicio.setResponsable(responsable);
		telefono = telefono_tmp;
		codigo = codigo_tmp;
		correo = email_tmp;

		/*
		 * Bifurcaciones en el que se evalua: 1 - si el registro de es creación
		 * se realizan las tareas a cabo. 2 - si el registro es de modificación
		 * se intentan recuperar el el teléfono y correo del servicio y se
		 * evalua: 2.1 - Si el correo y/o el teléfono distintos de null, se
		 * actulizan los dato. 2.2 - Si el correo y/o el teléfono son iguales a
		 * null, se crean los dato.
		 */
		if (!isModificar()) {
			update(servicio, id_servicio_informacion);
			create(phone);
			create(email);
			setModificar(true);
			setNuevo(false);
		} else {
			email = getEmail(new ServicioInformacion(), id_servicio_informacion);
			phone = getPhone(new ServicioInformacion(), id_servicio_informacion);
			if (email != null) {
				email.setId_servicio_informacion(id_servicio_informacion);
				email.setCorreo(correo);
				update(email, email.getId_correo());
			} else {
				email = new Correo();
				email.setId_servicio_informacion(id_servicio_informacion);
				email.setCorreo(correo);
				create(email);
			}
			if (phone != null) {
				phone.setId_servicio_informacion(id_servicio_informacion);
				phone.setTelefono(codigo + telefono);
				update(phone, phone.getId_telefono());
			} else {
				phone = new Telefono();
				phone.setId_servicio_informacion(id_servicio_informacion);
				phone.setTelefono(codigo + telefono);
				create(phone);
			}
			update(servicio, id_servicio_informacion);
		}
		/*
		 * Colocando los valores del formulario recien creado ó modificado para
		 * que esten disponibles para el resto de las pestañas
		 */
		if (setSessionStack().equals("error"))
			return "errorSession";
		
		if(submit != null && submit.contentEquals(message.getProperties().getProperty("registro.finalizar"))){			
				addActionMessage(message.getProperties().getProperty("registro.servicio.success"));
			return "end";
		}
		
		return SUCCESS;
	}

	@Override
	public void validate() {

		isValidate = true;
		switch (tab) {
		case DESCRIPCION_GENERAL:
			if (sector < 0)
				addFieldError(
						"sector",
						error.getProperties().getProperty(
								"error.servicio.sector"));
			if (servicio.getNombre().trim().isEmpty())
				addFieldError("servicio.nombre", error.getProperties()
						.getProperty("error.servicio.nombre").toString());
			if (!servicio.getNombre().toUpperCase().matches(REGEX_TITLE))
				addFieldError("servicio.nombre", error.getProperties()
						.getProperty("error.regex.title"));
			if (servicio.getDescripcion().trim().isEmpty())
				addFieldError("servicio.descripcion", error.getProperties()
						.getProperty("error.servicio.descripcion"));
			if (!servicio.getDescripcion().toUpperCase()
					.matches(REGEX_DESCRIPTION))
				addFieldError("servicio.descripcion", error.getProperties()
						.getProperty("error.regex.description"));
			if (area.size() == 0)
				addFieldError("area",
						error.getProperties()
								.getProperty("error.servicio.area"));
			if (estado < 0)
				addFieldError(
						"estado",
						error.getProperties().getProperty(
								"error.servicio.estado"));
			prepararDescripcionGeneral();
			break;
		case DESCRIPCION_TECNICA:
			if (seguridad < 0)
				addFieldError(
						"seguridad",
						error.getProperties().getProperty(
								"error.servicio.seguridad"));
			if (arquitectura.size() == 0)
				addFieldError("arquitectura", error.getProperties()
						.getProperty("error.servicio.arquitectura"));
			if (servicio.getVersion().trim().isEmpty())
				addFieldError("servicio.version", error.getProperties()
						.getProperty("error.servicio.version"));
			try {
				float version = Float.parseFloat(servicio.getVersion()
						.toString());
				if (version < 0.0 || version > 999.999)
					addFieldError("servicio.version", error.getProperties()
							.getProperty("error.servicio.version.range"));

			} catch (NumberFormatException ex) {
				addFieldError("servicio.version", error.getProperties()
						.getProperty("error.servicio.version.format"));
			}
			if (intercambio < 0)
				addFieldError("intercambio",error.getProperties().getProperty(
								"error.servicio.intercambio"));
			// TODO FIX THIS!
			long estado = ((ServicioInformacion) read(servicio,id_servicio_informacion)).getId_estado();
			if (wsdl.trim().isEmpty() && estado == IMPLEMENTADO)
				addFieldError("wsdl",error.getProperties().getProperty("error.servicio.wsdl"));
			else if (!wsdl.matches(REGEX_URL) && !wsdl.trim().isEmpty())
				addFieldError("wsdl",error.getProperties().getProperty("error.regex.url"));
			prepararDescripcionTecnica();
			break;
		case DESCRIPCION_SOPORTE:
			if (servicio.getResponsable().trim().isEmpty())
				addFieldError("servicio.responsable", error.getProperties()
						.getProperty("error.servicio.responsable"));
			if (!servicio.getResponsable().toUpperCase().matches(REGEX_TITLE))
				addFieldError("servicio.responsable", error.getProperties()
						.getProperty("error.regex.title"));
			if (telefono.trim().isEmpty())
				addFieldError(
						"telefono",
						error.getProperties().getProperty(
								"error.servicio.telefono"));
			if (telefono.length() > 0 && telefono.length() < 7)
				addFieldError(
						"telefono",
						error.getProperties().getProperty(
								"error.servicio.telefono.digit"));
			if (!telefono.matches("\\d.*") && !telefono.trim().isEmpty())
				addFieldError(
						"telefono",
						error.getProperties().getProperty(
								"error.servicio.telefono.regex"));
			if (codigo == null)
				addFieldError(
						"telefono",
						error.getProperties().getProperty(
								"error.servicio.codigo"));
			if (codigo.length() != 3)
				addFieldError(
						"telefono",
						error.getProperties().getProperty(
								"error.servicio.codigo.digit"));
			if (!codigo.matches("\\d.*") && !codigo.trim().isEmpty())
				addFieldError(
						"telefono",
						error.getProperties().getProperty(
								"error.servicio.codigo.digit"));
			if (correo.trim().isEmpty())
				addFieldError(
						"correo",
						error.getProperties().getProperty(
								"error.servicio.correo"));
			if (!correo.matches(REGEX_EMAIL))
				addFieldError("correo",
						error.getProperties().getProperty("error.regex.email"));
			prepararDescripcionSoporte();
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unchecked")
	private String setSessionStack() {
		try {
			session = ActionContext.getContext().getSession();
			session.put("servicio", servicio);
			session.put("sector", sector);
			session.put("area", area);
			session.put("estado", estado);
			session.put("seguridad", seguridad);
			session.put("arquitectura", arquitectura);
			session.put("intercambio", intercambio);
			session.put("wsdl", wsdl);
			session.put("codigo", codigo);
			session.put("telefono", telefono);
			session.put("correo", correo);
			session.put("nuevo", nuevo);
			session.put("modificar", modificar);
			session.put("id_servicio_informacion", id_servicio_informacion);
		} catch (Exception e) {
			return "error";
		}
		return "";
	}

	@SuppressWarnings("unchecked")
	private String getSessionStack(boolean isValidate) {
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
				wsdl = (String) session.get("wsdl");
				codigo = (String) session.get("codigo");
				telefono = (String) session.get("telefono");
				correo = (String) session.get("correo");
			} catch (Exception e) {
				return "error";
			}
		}
		try {
			setModificar((Boolean) session.get("modificar"));
			setNuevo((Boolean) session.get("nuevo"));
			setId_servicio_informacion((Long) session
					.get("id_servicio_informacion"));
		} catch (Exception e) {
			return "error";
		}
		return "";
	}

	private String cleanSessionStack() {
		try {
			session = ActionContext.getContext().getSession();
			session.remove("servicio");
			session.remove("sector");
			session.remove("area");
			session.remove("estado");
			session.remove("seguridad");
			session.remove("arquitectura");
			session.remove("intercambio");
			session.remove("wsdl");
			session.remove("telefono");
			session.remove("codigo");
			session.remove("correo");
			session.remove("nuevo");
			session.remove("modificar");
		} catch (Exception e) {
			return "error";
		}
		return "";
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
		phone = getPhone(servicio, id_servicio_informacion);
		try {
			telefono = phone.getTelefono().substring(0, 3) + "-"
					+ phone.getTelefono().substring(3, 10);
		} catch (Exception e) {
		}
		Correo email = new Correo();
		email = getEmail(servicio, id_servicio_informacion);
		try {
			correo = email.getCorreo();
		} catch (Exception e) {
		}
		// TODO Tal vez aquí no deba ir...
		Url url = new Url();
		url = getUrl(servicio, id_servicio_informacion);
		try {
			wsdl = url.getUrl();
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
		ente = (Ente) read(ente, servicio.getId_ente());
		// sectores = (List<Sector>) read(new Sector());
		sectores = (List<Sector>) getSortedList(new Sector(), ASC);
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		niveles = (List<Seguridad>) read(new Seguridad());
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		children = (List<Intercambio>) read(new Intercambio());
		files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
		setnVisitas(getVisits(id_servicio_informacion));
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
		List<AspectoLegal> documentos = new ArrayList<AspectoLegal>();
		AspectoLegal documento = new AspectoLegal();
		documentos = (List<AspectoLegal>) read(ALSI, id_servicio_informacion,
				-1);
		Iterator<AspectoLegal> docIterator = documentos.iterator();
		while (docIterator.hasNext()) {
			documento = docIterator.next();
			String path = servletRequest.getSession().getServletContext()
					.getRealPath(documento.getUrl());
			File file = new File(path);
			file.delete();
			delete(documento, documento.getId_aspecto_legal());
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
		for (UnionArquitecturaServicioInformacion a : unionarquitecturas)
			arquitectura.add(a.getId_arquitectura());
		intercambio = servicio.getId_intercambio();
		Url url = new Url();
		try {
			url = getUrl(servicio, id_servicio_informacion);
			wsdl = url.getUrl();
		} catch (Exception e) {
		}
		Telefono phone = new Telefono();
		try {
			phone = getPhone(servicio, id_servicio_informacion);
			codigo = phone.getTelefono().substring(0, 3);
			telefono = phone.getTelefono().substring(3, 10);
		} catch (Exception e) {
		}
		Correo email = new Correo();
		try {
			email = getEmail(servicio, id_servicio_informacion);
			correo = email.getCorreo();
		} catch (Exception e) {
		}
		// sectores = (List<Sector>) read(new Sector());
		sectores = (List<Sector>) getSortedList(new Sector(), ASC);
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		niveles = (List<Seguridad>) read(new Seguridad());
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		parents = (List<Intercambio>) getParents(new Intercambio());
		children = (List<Intercambio>) getChildren(new Intercambio());
		setModificar(true);
		files = (List<AspectoLegal>) read(ALSI, id_servicio_informacion, -1);
		if (setSessionStack().equals("error"))
			return "errorSession";		
		prepararDescripcionGeneral();
		return SUCCESS;
	}
	
	public String terminar_registro_si() {		
		addActionMessage(message.getProperties().getProperty("registro.servicio.success"));
		session = ActionContext.getContext().getSession();
		if (session.isEmpty()) {
			return "errorSession";
		}
		Usuario usuario = (Usuario) session.get("usuario");
		if (usuario == null) {
			return "errorSession";
		}	
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

	public long getnVisitas() {
		return nVisitas;
	}

	public void setnVisitas(long nVisitas) {
		this.nVisitas = nVisitas;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getWsdl() {
		return wsdl;
	}

	public void setWsdl(String wsdl) {
		this.wsdl = wsdl;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public List<Tabs_incompletes> getTabs_incompletas() {
		return tabs_incompletas;
	}

	public void setTabs_incompletas(List<Tabs_incompletes> tabs_incompletas) {
		this.tabs_incompletas = tabs_incompletas;
	}
	
}

package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;

import ve.gob.cnti.modelo.temporales.ListaSImasVisitados;
import ve.gob.cnti.modelo.temporales.SectoresMasPublicados;
import ve.gob.cnti.srsi.dao.Constants;
import ve.gob.cnti.srsi.dao.Constants.Modelos;
import ve.gob.cnti.srsi.dao.Constants.Order;
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
import ve.gob.cnti.srsi.modelo.Visita;

@SuppressWarnings("serial")
public class ConsultasControlador extends DAO implements Constants, Order,
		Modelos {

	private Sector sector = new Sector();
	private ServicioInformacion servicio = new ServicioInformacion();
	private Funcionalidad funcionalidad;
	private Ente ente = new Ente();
	private Visita visita = new Visita();

	private List<Estado> estados = new ArrayList<Estado>();
	private List<Area> areas = new ArrayList<Area>();
	private List<Seguridad> niveles = new ArrayList<Seguridad>();
	private List<Arquitectura> arquitecturas = new ArrayList<Arquitectura>();
	private List<Intercambio> parents = new ArrayList<Intercambio>();
	private List<Intercambio> children = new ArrayList<Intercambio>();
	private List<UnionAreaServicioInformacion> unionareas = new ArrayList<UnionAreaServicioInformacion>();
	private List<UnionArquitecturaServicioInformacion> unionarquitecturas = new ArrayList<UnionArquitecturaServicioInformacion>();
	private List<AspectoLegal> files = new ArrayList<AspectoLegal>();
	private List<Funcionalidad> funcionalidades = new ArrayList<Funcionalidad>();
	private List<List<EntradaSalida>> ios = new ArrayList<List<EntradaSalida>>();
	private List<Sector> sectores = new ArrayList<Sector>();
	private List<Ente> entes = new ArrayList<Ente>();
	private List<ServicioInformacion> servicios = new ArrayList<ServicioInformacion>();
	List<SectoresMasPublicados> listaSectores = new ArrayList<SectoresMasPublicados>();
	List<SectoresMasPublicados> listaSectores2 = new ArrayList<SectoresMasPublicados>();
	private List<ListaSImasVisitados> SI_masVisitados = new ArrayList<ListaSImasVisitados>();	
	@SuppressWarnings("rawtypes")
	private Map session;
	private String cadena;
	private String telefono;
	private String correo;
	private String codigo;
	private String codigos[] = CODES;

	private long nVisitas;
	private long id_sector;
	private long id_servicio;

	private boolean consulta_SIxSector;
	private boolean consulta_listarSectores;
	private boolean consulta_listarServicios;
	private boolean examinarServicio;
	private boolean buscarServicio;

	public String inicio() {
		listaSectores = sectoresMasPublicados(LIMITE_SECTORES);
		SI_masVisitados = SImasVisitados();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String listarSector() {
		listaSectores = sectoresMasPublicados(LIMITE_SECTORES);
		SI_masVisitados = SImasVisitados();
		if (!verificarLong(id_sector))
			return INPUT;
		sector = (Sector) read(sector, id_sector);
		if (sector == null)
			return INPUT;
		servicios = getServicioInformacionPorSectorList(id_sector, DESC);
		entes = (List<Ente>) read(new Ente());
		consulta_SIxSector = true;
		return SUCCESS;
	}

	public String listarSectores() {
		consulta_listarSectores = true;
		listaSectores = sectoresMasPublicados(LIMITE_SECTORES);
		listaSectores2 = sectoresMasPublicados(0);
		SI_masVisitados = SImasVisitados();
		System.out.println("ls " + listaSectores2.size());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String listarServicios() {
		listaSectores = sectoresMasPublicados(LIMITE_SECTORES);
		SI_masVisitados = SImasVisitados();
		consulta_listarServicios = true;
		servicios = getSIList(ASC);
		entes = (List<Ente>) read(new Ente());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String buscar_servicio() {
		listaSectores = sectoresMasPublicados(LIMITE_SECTORES);
		SI_masVisitados = SImasVisitados();
		buscarServicio = true;
		if (!cadena.toString().toUpperCase().matches(REGEX_TITLE)) {
			addFieldError("error",
					error.getProperties().getProperty("error.regex.title"));
			buscarServicio = false;
			return INPUT;
		}
		if (cadena.toString().trim().isEmpty()) {
			addFieldError("error",
					error.getProperties().getProperty("error.search.empty"));
			buscarServicio = false;
			return INPUT;
		}
		servicios = buscarServicio(cadena, ASC);
		entes = (List<Ente>) read(new Ente());
		return SUCCESS;
	}
		
	@SuppressWarnings("unchecked")
	public String buscar_servicio2() {
		session = ActionContext.getContext().getSession();
		if (session.isEmpty()) {
			return INPUT;
		}
		Usuario usuario = (Usuario) session.get("usuario");
		if (usuario == null) {
			return INPUT;
		}
		buscarServicio = true;
		if (!cadena.toUpperCase().matches(REGEX_TITLE)) {
			addFieldError("error",
					error.getProperties().getProperty("error.regex.title"));
			buscarServicio = false;
			return INPUT;
		}
		servicios = buscarServicio2(cadena, ASC,usuario.getId_ente());	
		entes = (List<Ente>) read(new Ente());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@SkipValidation
	public String examinarServicioInformacion() {				
		if (!verificarLong(id_servicio))
			return INPUT;
		listaSectores = sectoresMasPublicados(LIMITE_VISITADOS);
		examinarServicio = true;
		servicio = (ServicioInformacion) read(servicio, id_servicio);
		if(!servicio.isPublicado())
			return INPUT;
		if(!isComplete(servicio))
			return INPUT;
		try {
			session = ActionContext.getContext().getSession();		
			Usuario usuario = (Usuario) session.get("usuario");
			if (usuario != null) {
				if(servicio.getId_ente() == usuario.getId_ente()){
					return INPUT;
				}
			}
		} catch (Exception e) {	}
		try {
			unionareas = (List<UnionAreaServicioInformacion>) readUnion(
					new UnionAreaServicioInformacion(), servicio, id_servicio);
		} catch (Exception e) {
		}
		try {
			unionarquitecturas = (List<UnionArquitecturaServicioInformacion>) readUnion(
					new UnionArquitecturaServicioInformacion(), servicio,
					id_servicio);
		} catch (Exception e) {
		}
		Telefono phone = new Telefono();
		phone = (Telefono) getPhone(servicio, id_servicio);
		try {
			telefono = phone.getTelefono();
		} catch (Exception e) {
		}
		Correo email = new Correo();
		email = (Correo) getEmail(servicio, id_servicio);
		try {
			correo = email.getCorreo();
		} catch (Exception e) {
		}
		funcionalidades = (List<Funcionalidad>) read(FSI, id_servicio, -1);
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
		sectores = (List<Sector>) getSortedList(new Sector(), ASC);
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		niveles = (List<Seguridad>) read(new Seguridad());
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		children = (List<Intercambio>) read(new Intercambio());
		files = (List<AspectoLegal>) read(ALSI, id_servicio, -1);
		visita.setId_servicio_informacion(id_servicio);
		HttpServletRequest request = ServletActionContext.getRequest();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		visita.setIp(ipAddress);
		if (verifyClientAccess(ipAddress, id_servicio))
			saveVisit(visita);
		nVisitas = getVisits(id_servicio);
		SI_masVisitados = SImasVisitados();
		return SUCCESS;
	}

	public boolean verificarLong(long n) {
		try {
			return n != 0;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isExaminarServicio() {
		return examinarServicio;
	}

	public void setExaminarServicio(boolean examinarServicio) {
		this.examinarServicio = examinarServicio;
	}

	public long getId_sector() {
		return id_sector;
	}

	public void setId_sector(long id_sector) {
		this.id_sector = id_sector;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public boolean isConsulta_SIxSector() {
		return consulta_SIxSector;
	}

	public void setConsulta_SIxSector(boolean consulta_SIxSector) {
		this.consulta_SIxSector = consulta_SIxSector;
	}

	public boolean isConsulta_listarSectores() {
		return consulta_listarSectores;
	}

	public void setConsulta_listarSectores(boolean consulta_listarSectores) {
		this.consulta_listarSectores = consulta_listarSectores;
	}

	public long getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(long id_servicio) {
		this.id_servicio = id_servicio;
	}

	public List<Sector> getSectores() {
		return sectores;
	}

	public void setSectores(List<Sector> sectores) {
		this.sectores = sectores;
	}

	public List<Ente> getEntes() {
		return entes;
	}

	public void setEntes(List<Ente> entes) {
		this.entes = entes;
	}

	public List<ServicioInformacion> getServicios() {
		return servicios;
	}

	public void setServicios(List<ServicioInformacion> servicios) {
		this.servicios = servicios;
	}

	public ServicioInformacion getServicio() {
		return servicio;
	}

	public void setServicio(ServicioInformacion servicio) {
		this.servicio = servicio;
	}

	public Funcionalidad getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(Funcionalidad funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
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

	public List<AspectoLegal> getFiles() {
		return files;
	}

	public void setFiles(List<AspectoLegal> files) {
		this.files = files;
	}

	public List<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public List<List<EntradaSalida>> getIos() {
		return ios;
	}

	public void setIos(List<List<EntradaSalida>> ios) {
		this.ios = ios;
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

	public boolean isConsulta_listarServicios() {
		return consulta_listarServicios;
	}

	public void setConsulta_listarServicios(boolean consulta_listarServicios) {
		this.consulta_listarServicios = consulta_listarServicios;
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public boolean isBuscarServicio() {
		return buscarServicio;
	}

	public void setBuscarServicio(boolean buscarServicio) {
		this.buscarServicio = buscarServicio;
	}

	public long getnVisitas() {
		return nVisitas;
	}

	public void setnVisitas(long nVisitas) {
		this.nVisitas = nVisitas;
	}

	public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

	public List<ListaSImasVisitados> getSI_masVisitados() {
		return SI_masVisitados;
	}

	public void setSI_masVisitados(List<ListaSImasVisitados> sI_masVisitados) {
		SI_masVisitados = sI_masVisitados;
	}

	public List<SectoresMasPublicados> getListaSectores() {
		return listaSectores;
	}

	public void setListaSectores(List<SectoresMasPublicados> listaSectores) {
		this.listaSectores = listaSectores;
	}

	public List<SectoresMasPublicados> getListaSectores2() {
		return listaSectores2;
	}

	public void setListaSectores2(List<SectoresMasPublicados> listaSectores2) {
		this.listaSectores2 = listaSectores2;
	}
}
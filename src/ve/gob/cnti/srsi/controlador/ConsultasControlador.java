package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.modelo.temporales.ListaSImasVisitados;
import ve.gob.cnti.srsi.dao.Constants;
import ve.gob.cnti.srsi.dao.Constants.Order;
import ve.gob.cnti.srsi.dao.Constants.Modelos;
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
import ve.gob.cnti.srsi.modelo.Visita;


@SuppressWarnings("serial")
public class ConsultasControlador extends DAO implements Constants, Order, Modelos{
	
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
	private List<ListaSectores> listaSectores = new ArrayList<ListaSectores>();
	private List<ListaSImasVisitados> SI_masVisitados = new ArrayList<ListaSImasVisitados>();
	
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
	
	
	
	public String inicio(){
		numeroDeServiciosPorSector();
		SI_masVisitados = SImasVisitados();		
		return SUCCESS;		
	}
	
	@SuppressWarnings("unchecked")
	public String listarSector(){
		numeroDeServiciosPorSector();
		SI_masVisitados = SImasVisitados();	
		if(!verificarLong(id_sector))
			return INPUT;
		sector = (Sector)read(sector, id_sector);
		if(sector == null)
			return INPUT;		
		servicios =(List<ServicioInformacion>) read(SISE, id_sector, -1);
		entes = (List<Ente>) read(new Ente());
		consulta_SIxSector = true;
		return SUCCESS;
	}
		
	public String listarSectores(){
		consulta_listarSectores = true;
		numeroDeServiciosPorSector();
		SI_masVisitados = SImasVisitados();	
		return SUCCESS;
	}
		
	@SuppressWarnings("unchecked")
	public String listarServicios(){				
		numeroDeServiciosPorSector();
		SI_masVisitados = SImasVisitados();	
		consulta_listarServicios = true;
		servicios = (List<ServicioInformacion>) getSortedList(servicio, ASC);
		entes = (List<Ente>) read(new Ente());
		return SUCCESS;
	}
		
	@SuppressWarnings("unchecked")
	public String buscar_servicio(){				
		numeroDeServiciosPorSector();
		SI_masVisitados = SImasVisitados();	
		buscarServicio = true;
		if (!cadena.toUpperCase().matches(REGEX_TITLE)){
			addFieldError("error",
					error.getProperties().getProperty("error.regex.title"));
			buscarServicio = false;
		}						
		servicios = buscarServicio(cadena, ASC);
		entes = (List<Ente>) read(new Ente());
		return SUCCESS;
	}
			
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void numeroDeServiciosPorSector(){
		sectores = (List<Sector>) getSortedList(new Sector(), DESC);
		Iterator<Sector> ite = sectores.iterator();
		Sector sector = new Sector();
		long n;
		while(ite.hasNext()){
			sector = ite.next();
			n = nSiSector(sector.getId_sector());			
			listaSectores.add(new ListaSectores(sector.getId_sector(),sector.getNombre(), n));			
		}
		
		Collections.sort(listaSectores, new Comparator() {
            public int compare(Object o1, Object o2) {
                ListaSectores e1 = (ListaSectores) o1;
                ListaSectores e2 = (ListaSectores) o2;
                long n1 = e1.getN();
                long n2 = e2.getN();
                if (n1 < n2) {
                    return 1;
                } else if (n1 > n2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
	}
	
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String examinarServicioInformacion() {
		if(!verificarLong(id_servicio))
			return INPUT;		
		numeroDeServiciosPorSector();		
		examinarServicio=true;		
		servicio = (ServicioInformacion) read(servicio, id_servicio);
		try {
			unionareas = (List<UnionAreaServicioInformacion>) readUnion(
					new UnionAreaServicioInformacion(), servicio,
					id_servicio);
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
		funcionalidades = (List<Funcionalidad>) read(FSI,
				id_servicio, -1);
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
		System.out.println("id ente " + servicio.getId_ente());		
		ente = (Ente) read(ente, servicio.getId_ente());		
		sectores = (List<Sector>) getSortedList(new Sector(), ASC);
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		niveles = (List<Seguridad>) read(new Seguridad());
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		children = (List<Intercambio>) read(new Intercambio());
		files = (List<AspectoLegal>) read(ALSI, id_servicio, -1);
		visita.setId_servicio_informacion(id_servicio);				
		create(visita);		
		nVisitas = readf(visita, id_servicio);
		SI_masVisitados = SImasVisitados();	
		return SUCCESS;
	}
	
	
	
	public boolean verificarLong(long n){
		try {			
			if(n == 0)
				return false;
		} catch (Exception e) {	return false;	}
		return true;
	}
	
	public boolean isExaminarServicio() {
		return examinarServicio;
	}

	public void setExaminarServicio(boolean examinarServicio) {
		this.examinarServicio = examinarServicio;
	}
	
	public List<ListaSectores> getListaSectores() {
		return listaSectores;
	}

	public void setListaSectores(List<ListaSectores> listaSectores) {
		this.listaSectores = listaSectores;
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

	
}

class ListaSectores{
	private long id_sector;
	private String nombre;
	private long n;
		
	public ListaSectores(long id_sector, String nombre, long n) {
		super();
		this.id_sector = id_sector;
		this.nombre = nombre;
		this.n = n;
	}
	
	public long getId_sector() {
		return id_sector;
	}
	public void setId_sector(long id_sector) {
		this.id_sector = id_sector;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
	public long getN() {
		return n;
	}
	public void setN(long n) {
		this.n = n;
	}	
}


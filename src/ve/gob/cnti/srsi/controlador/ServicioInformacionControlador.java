package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;


import ve.gob.cnti.srsi.modelo.*;
import ve.gob.cnti.srsi.dao.*;

@SuppressWarnings("serial")
public class ServicioInformacionControlador extends ActionSupport{
	
	private List<Area> areas = new ArrayList<Area>();	
	private List<Estado> estados = new ArrayList<Estado>();
	private List<Seguridad> seguridad = new ArrayList<Seguridad>();
	private List<Arquitectura> arquitecturas = new ArrayList<Arquitectura>();
	private List<Sector> sectores = new ArrayList<Sector>();
	private List<Intercambio> intercambios = new ArrayList<Intercambio>();	
	private List<Intercambio> intercambiosPadres = new ArrayList<Intercambio>();
	private List<Intercambio> intercambiosHijos = new ArrayList<Intercambio>();
	
 	
	private DAO dao = new DAO();
	
	
	@SuppressWarnings("unchecked")	
	public String prepararRegistroServicioInformacion(){
		
		Area area = new Area();		
		Estado est = new Estado();
		Seguridad seg = new Seguridad();
		Arquitectura arq = new Arquitectura();
		Sector sector = new Sector();
		Intercambio intercambio = new Intercambio();
		
		areas =(List<Area>) dao.read(area);		
		estados = (List<Estado>)dao.read(est);
		seguridad = (List<Seguridad>) dao.read(seg);
		arquitecturas = (List<Arquitectura>) dao.read(arq);
		sectores = (List<Sector>) dao.read(sector);
		
		intercambios = (List<Intercambio>) dao.read(intercambio);		
		Iterator<Intercambio> iterador = intercambios.iterator();	
						
		while(iterador.hasNext()){
			intercambio = iterador.next();
			if(intercambio.getId_padre()==0){				
				intercambiosPadres.add(intercambio);
			}
		}
				
		iterador = intercambios.iterator();
		
		while(iterador.hasNext()){
			intercambio = iterador.next();
			if(intercambio.getId_padre()!=0){			
				intercambiosHijos.add(intercambio);
			}
		}
						
		return "SUCCESS";
	}
	
	
	

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Seguridad> getSeguridad() {
		return seguridad;
	}

	public void setSeguridad(List<Seguridad> seguridad) {
		this.seguridad = seguridad;
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

	public List<Intercambio> getIntercambios() {
		return intercambios;
	}

	public void setIntercambios(List<Intercambio> intercambios) {
		this.intercambios = intercambios;
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
	
}

class Padres{
	
	private long id;
	private String nombre;  
		     
	public Padres(long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Padres [id=" + id + ", nombre=" + nombre + "]";
	}
    
	
}

class Hijos{
	
	private long id;
	private long id_padre;
	private String nombre;
		
	public Hijos(long id, long id_padre, String nombre) {
		super();
		this.id = id;
		this.id_padre = id_padre;
		this.nombre = nombre;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId_padre() {
		return id_padre;
	}
	public void setId_padre(long id_padre) {
		this.id_padre = id_padre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Hijos [id=" + id + ", id_padre=" + id_padre + ", nombre="
				+ nombre + "]";
	}	
	
	
}




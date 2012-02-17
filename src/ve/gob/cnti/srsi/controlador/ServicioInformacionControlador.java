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
	private List<TipoIntercambio> tiposdeIntercambios = new ArrayList<TipoIntercambio>(); 
		
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
		Iterator<Intercambio> iterador2 = intercambios.iterator();
		
		
		List<String> padres = new ArrayList<String>();
		while(iterador.hasNext()){
			intercambio = iterador.next();
			if(intercambio.getId_padre()==0){			
				padres.add(intercambio.getNombre());
			}
		}
		
		List<String> hijos = new ArrayList<String>();
		while(iterador.hasNext()){
			intercambio = iterador.next();
			if(intercambio.getId_padre()!=0){			
				hijos.add(intercambio.getNombre());
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

	public List<TipoIntercambio> getTiposdeIntercambios() {
		return tiposdeIntercambios;
	}

	public void setTiposdeIntercambios(List<TipoIntercambio> tiposdeIntercambios) {
		this.tiposdeIntercambios = tiposdeIntercambios;
	}
	
}

class TipoIntercambio{
	
	 private String nombre;  
     private List<String> hijos;  
   
     public TipoIntercambio(String nombre, List<String> hijos) {  
         this.nombre = nombre;  
         this.hijos = hijos;  
     }  
   
     public String getNombre() {  
         return nombre;  
     }  
   
     public List<String> getCanciones() {  
         return hijos;  
     } 
}


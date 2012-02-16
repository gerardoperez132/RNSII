package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;


import ve.gob.cnti.srsi.modelo.*;
import ve.gob.cnti.srsi.dao.*;

@SuppressWarnings("serial")
@Namespace("/")
@Result(name="success",location="pages/login.jsp")
public class ServicioInformacion extends ActionSupport{
	
	private List<Area> areas = new ArrayList<Area>();	
	private List<Estado> estados = new ArrayList<Estado>();
	private List<Seguridad> seguridad = new ArrayList<Seguridad>();
	private List<Arquitectura> arquitecturas = new ArrayList<Arquitectura>();
	private List<Sector> sectores = new ArrayList<Sector>();
	
	
	private DAO dao = new DAO();
	
	
	@SuppressWarnings("unchecked")	
	public String prepararRegistroServicioInformacion(){
		
		Area area = new Area();		
		Estado est = new Estado();
		Seguridad seg = new Seguridad();
		Arquitectura arq = new Arquitectura();
		Sector sector = new Sector();
		
		areas =(List<Area>) dao.read(area);		
		estados = (List<Estado>)dao.read(est);
		seguridad = (List<Seguridad>) dao.read(seg);
		arquitecturas = (List<Arquitectura>) dao.read(arq);
		sectores = (List<Sector>) dao.read(sector);
				
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

		
	
}

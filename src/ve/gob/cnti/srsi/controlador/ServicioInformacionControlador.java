package ve.gob.cnti.srsi.controlador;

import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Area;
import ve.gob.cnti.srsi.modelo.Arquitectura;
import ve.gob.cnti.srsi.modelo.Estado;
import ve.gob.cnti.srsi.modelo.Intercambio;
import ve.gob.cnti.srsi.modelo.Sector;
import ve.gob.cnti.srsi.modelo.Seguridad;

@SuppressWarnings("serial")
public class ServicioInformacionControlador extends DAO implements Formulario {

	private List<Sector> sectores;
	private List<Estado> estados;
	private List<Area> areas;
	private List<Seguridad> niveles;
	private List<Arquitectura> arquitecturas;
	private List<Intercambio> parents;
	private List<Intercambio> children;

	public List<Sector> getSectores() {
		return sectores;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public List<Seguridad> getNiveles() {
		return niveles;
	}

	public List<Arquitectura> getArquitecturas() {
		return arquitecturas;
	}

	public List<Intercambio> getParents() {
		return parents;
	}

	public List<Intercambio> getChildren() {
		return children;
	}

	public void setSectores(List<Sector> sectores) {
		this.sectores = sectores;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public void setNiveles(List<Seguridad> niveles) {
		this.niveles = niveles;
	}

	public void setArquitecturas(List<Arquitectura> arquitecturas) {
		this.arquitecturas = arquitecturas;
	}

	public void setParents(List<Intercambio> parents) {
		this.parents = parents;
	}

	public void setChildren(List<Intercambio> children) {
		this.children = children;
	}

	@SkipValidation
	@Override
	public String prepararFormulario() {
		sectores = (List<Sector>) read(new Sector());
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		niveles = (List<Seguridad>) read(new Seguridad());
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		parents = (List<Intercambio>) read(new Intercambio());
		children = (List<Intercambio>) read(new Intercambio());
		return SUCCESS;
	}

	@Override
	public String prepararModificaciones() {
		// TODO Auto-generated method stub
		return null;
	}

}
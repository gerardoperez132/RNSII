package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.List;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Area;
import ve.gob.cnti.srsi.modelo.Arquitectura;
import ve.gob.cnti.srsi.modelo.Estado;
import ve.gob.cnti.srsi.modelo.Intercambio;
import ve.gob.cnti.srsi.modelo.Sector;
import ve.gob.cnti.srsi.modelo.Seguridad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ServicioInformacionControlador extends ActionSupport {

	private List<Area> areas = new ArrayList<Area>();
	private List<Estado> estados = new ArrayList<Estado>();
	private List<Seguridad> l_seguridad = new ArrayList<Seguridad>();
	private List<Arquitectura> arquitecturas = new ArrayList<Arquitectura>();
	private List<Sector> sectores = new ArrayList<Sector>();
	private List<Intercambio> intercambiosPadres = new ArrayList<Intercambio>();
	private List<Intercambio> intercambiosHijos = new ArrayList<Intercambio>();

	private String sector;
	private String nombre;
	private String descripcion;
	private String estado;
	private String aspectoLegal;
	private String area;
	private String seguridad;
	private String arquitectura;
	private String intercambio;
	private String responsable;
	private String telefonoContacto;
	private String correoContacto;

	private DAO dao = new DAO();

	@SuppressWarnings("unchecked")
	public String prepararRegistroServicioInformacion() {

		Area area = new Area();
		Estado est = new Estado();
		Seguridad seg = new Seguridad();
		Arquitectura arq = new Arquitectura();
		Sector sector = new Sector();
		Intercambio intercambio = new Intercambio();

		areas = (List<Area>) dao.read(area);
		estados = (List<Estado>) dao.read(est);
		l_seguridad = (List<Seguridad>) dao.read(seg);
		arquitecturas = (List<Arquitectura>) dao.read(arq);
		sectores = (List<Sector>) dao.read(sector);

		intercambiosPadres = (List<Intercambio>) dao.getParents(intercambio);
		intercambiosHijos = (List<Intercambio>) dao.getChildren(intercambio);

		responsable = "Joaquín";

		return SUCCESS;
	}

	public String registrarServicioInformacion() {

		ServicioInformacion si = new ServicioInformacion();

		si.setId_sector(Long.parseLong(sector));

		si.setNombre(nombre);
		si.setDescripcion(descripcion);

		si.setId_estado(Long.parseLong(estado));

		// int[] a = { area };

		System.out
				.println("******************************************************************");
		System.out.println(sector);
		System.out.println(nombre);
		System.out.println(descripcion);
		System.out.println(estado);
		System.out.println(aspectoLegal);
		System.out.println(area);
		System.out.println(seguridad);
		System.out.println(arquitectura);
		System.out.println(intercambio);
		System.out.println(responsable);
		System.out.println(telefonoContacto);
		System.out.println(correoContacto);
		System.out
				.println("******************************************************************");

		return SUCCESS;
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

	public List<Seguridad> getL_seguridad() {
		return l_seguridad;
	}

	public void setL_seguridad(List<Seguridad> l_seguridad) {
		this.l_seguridad = l_seguridad;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getAspectoLegal() {
		return aspectoLegal;
	}

	public void setAspectoLegal(String aspectoLegal) {
		this.aspectoLegal = aspectoLegal;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSeguridad() {
		return seguridad;
	}

	public void setSeguridad(String seguridad) {
		this.seguridad = seguridad;
	}

	public String getArquitectura() {
		return arquitectura;
	}

	public void setArquitectura(String arquitectura) {
		this.arquitectura = arquitectura;
	}

	public String getIntercambio() {
		return intercambio;
	}

	public void setIntercambio(String intercambio) {
		this.intercambio = intercambio;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public String getCorreoContacto() {
		return correoContacto;
	}

	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}

}
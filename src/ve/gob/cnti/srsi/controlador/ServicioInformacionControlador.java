package ve.gob.cnti.srsi.controlador;

import java.util.List;

import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Area;
import ve.gob.cnti.srsi.modelo.Arquitectura;
import ve.gob.cnti.srsi.modelo.Estado;
import ve.gob.cnti.srsi.modelo.Intercambio;
import ve.gob.cnti.srsi.modelo.Sector;
import ve.gob.cnti.srsi.modelo.Seguridad;
import ve.gob.cnti.srsi.modelo.ServicioInformacion;

import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public class ServicioInformacionControlador extends DAO implements Preparable {

	private List<Sector> sectores;
	private List<Estado> estados;
	private List<Area> areas;
	private List<Seguridad> niveles;
	private List<Arquitectura> arquitecturas;
	private List<Intercambio> parents;
	private List<Intercambio> children;
	private long id_servicio_informacion;
	private ServicioInformacion servicio = new ServicioInformacion();

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

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public ServicioInformacion getServicio() {
		return servicio;
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

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
	}

	public void setServicio(ServicioInformacion servicio) {
		this.servicio = servicio;
	}

	@Override
	public void validate() {
		// TODO Validaciones.
	}

	public String registrarServicioInformacion() {
		id_servicio_informacion = getNextId(servicio);
		// TODO Este identificador debe venir de la base de datos.
		servicio.setId_ente(1);
		// TODO Este identificador debe venir de la base de datos.
		servicio.setId_usuario(1);
		create(servicio);
		System.out.println("ARQUITECTURAS SELECCIONADAS => "
				+ arquitecturas.get(0).getNombre());
		return SUCCESS;
	}

	@Override
	public void prepare() throws Exception {
		sectores = (List<Sector>) read(new Sector());
		estados = (List<Estado>) read(new Estado());
		areas = (List<Area>) read(new Area());
		niveles = (List<Seguridad>) read(new Seguridad());
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		parents = (List<Intercambio>) getParents(new Intercambio());
		children = (List<Intercambio>) getChildren(new Intercambio());
	}
}
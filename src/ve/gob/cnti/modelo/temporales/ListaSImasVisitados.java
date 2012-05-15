package ve.gob.cnti.modelo.temporales;

/**
 * Modelo para listar los servicios de información más visitados.
 * 
 * @author Joaquín Pereira
 * 
 */
public class ListaSImasVisitados {

	private long id_servicio_informacion;
	private String nombre;
	private long visitas;

	public ListaSImasVisitados() {
	}

	public long getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public void setId_servicio_informacion(long id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getVisitas() {
		return visitas;
	}

	public void setVisitas(long visitas) {
		this.visitas = visitas;
	}

	@Override
	public String toString() {
		return "ListaSImasVisitados [id_servicio_informacion="
				+ id_servicio_informacion + ", nombre=" + nombre + ", visitas="
				+ visitas + "]";
	}
}
package ve.gob.cnti.srsi.modelo;

public class Funcionalidad {
	private int id;
	private int id_fun;
	private int id_si;
	private String nombre;
	private String descripcion;

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public int getId_fun() {
		return id_fun;
	}

	public void setId_fun(int id_fun) {
		this.id_fun = id_fun;
	}

	public int getId_si() {
		return id_si;
	}

	public void setId_si(int id_si) {
		this.id_si = id_si;
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

}
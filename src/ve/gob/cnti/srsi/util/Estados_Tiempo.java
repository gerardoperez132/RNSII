package ve.gob.cnti.srsi.util;

public class Estados_Tiempo {

	private int codigo;
	private String nombre;
	private int t_max;
	private int t_min;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getT_max() {
		return t_max;
	}

	public void setT_max(int t_max) {
		this.t_max = t_max;
	}

	public int getT_min() {
		return t_min;
	}

	public void setT_min(int t_min) {
		this.t_min = t_min;
	}

	@Override
	public String toString() {
		return "Estados_Tiempo [codigo=" + codigo + ", nombre=" + nombre
				+ ", t_max=" + t_max + ", t_min=" + t_min + "]";
	}
}

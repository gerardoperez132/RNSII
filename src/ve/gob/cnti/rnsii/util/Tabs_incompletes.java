package ve.gob.cnti.rnsii.util;

import java.util.List;

/**
 * Clase temporal ideada para categorizar los 
 * errores presentes en el registro de un 
 * servicio de información.
 * 
 * @author Joaquín Pereira
 */
public class Tabs_incompletes {
	
	private int tab;
	private List<String> detalles;
	
	public int getTab() {
		return tab;
	}
	public void setTab(int tab) {
		this.tab = tab;
	}
	public List<String> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<String> detalles) {
		this.detalles = detalles;
	}	
		
	@Override
	public String toString() {
		return "Tabs_incompletes [tab=" + tab + ", detalles=" + detalles + "]";
	}
	
}

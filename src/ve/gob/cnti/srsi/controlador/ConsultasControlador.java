package ve.gob.cnti.srsi.controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import ve.gob.cnti.srsi.dao.Constants;
import ve.gob.cnti.srsi.dao.Constants.Order;
import ve.gob.cnti.srsi.dao.DAO;
import ve.gob.cnti.srsi.modelo.Sector;

@SuppressWarnings("serial")
public class ConsultasControlador extends DAO implements Constants, Order{
	
	private List<Sector> sectores = new ArrayList<Sector>();
	private List<ListaSectores> listaSectores = new ArrayList<ListaSectores>();
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String inicio(){
		sectores = (List<Sector>) getSortedList(new Sector(), DESC);
		Iterator<Sector> ite = sectores.iterator();
		Sector sector = new Sector();
		long n;
		while(ite.hasNext()){
			sector = ite.next();
			n = nSiSector(sector.getId_sector());			
			listaSectores.add(new ListaSectores(sector.getId_sector(),sector.getNombre(), n));			
		}
		
		Collections.sort(listaSectores, new Comparator() {
            public int compare(Object o1, Object o2) {
                ListaSectores e1 = (ListaSectores) o1;
                ListaSectores e2 = (ListaSectores) o2;
                long n1 = e1.getN();
                long n2 = e2.getN();
                if (n1 < n2) {
                    return 1;
                } else if (n1 > n2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
		
				
		
		return SUCCESS;		
	}
	
	
	
	public List<ListaSectores> getListaSectores() {
		return listaSectores;
	}

	public void setListaSectores(List<ListaSectores> listaSectores) {
		this.listaSectores = listaSectores;
	}
}

class ListaSectores{
	private long id_sector;
	private String nombre;
	private long n;
		
	public ListaSectores(long id_sector, String nombre, long n) {
		super();
		this.id_sector = id_sector;
		this.nombre = nombre;
		this.n = n;
	}
	
	public long getId_sector() {
		return id_sector;
	}
	public void setId_sector(long id_sector) {
		this.id_sector = id_sector;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
	public long getN() {
		return n;
	}
	public void setN(long n) {
		this.n = n;
	}	
}
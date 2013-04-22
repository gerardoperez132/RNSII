package ve.gob.cnti.rnsii.admin;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.interceptor.validation.SkipValidation;

import ve.gob.cnti.rnsii.dao.DAO;
import ve.gob.cnti.rnsii.modelo.Ente;

@SuppressWarnings("serial")
public class EnteControl extends DAO {	
	private int accion_ente;
	private String tipo_rif;
	private Ente ente = new Ente();
	private List<Ente> entes = new ArrayList<Ente>();
	private List<String> rif_type = new ArrayList<String>();
	
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String registrar_ente() {		
		accion_ente = 1;		
		getTipoRIF();
		entes = (List<Ente>) read(new Ente());				
		return SUCCESS;
	}
			
	public String registrar_ente_execute() {		
		ente.setRif(tipo_rif+"-"+ente.getRif());
		if(ente.getId_padre()==-1)
			ente.setId_padre(0);		
		System.out.println(ente.toString());
		create(ente);	
		addActionMessage("Ente registrado satisfactoriamente.");
		accion_ente = 0;		
		return SUCCESS;
	}
	
	public void validate() {			
		if(ente.getNombre().trim().isEmpty()){
			addFieldError("nombre",error.getProperties().getProperty("error.required"));
		}else if(ente.getNombre().length()<4){
			addFieldError("nombre",error.getProperties().getProperty("error.ente.nombre.min"));
		}else if (!ente.getNombre().toUpperCase().matches(REGEX_TITLE)) {
			addFieldError("nombre",error.getProperties().getProperty("error.regex.title"));
		} 		
		
		if(ente.getDireccion().trim().isEmpty()){
			addFieldError("direccion",error.getProperties().getProperty("error.required"));
		}else if(ente.getDireccion().length()<10){
			addFieldError("direccion",error.getProperties().getProperty("error.ente.direccion.min"));
		}else if (!ente.getDireccion().toUpperCase().matches(REGEX_DESCRIPTION)) {
			addFieldError("direccion",error.getProperties().getProperty("error.regex.description"));
		} 		
		
		if(ente.getSiglas().trim().isEmpty()){
			addFieldError("sigla",error.getProperties().getProperty("error.required"));
		}else if(ente.getSiglas().length()<2){
			addFieldError("sigla",error.getProperties().getProperty("error.ente.siglas.min"));
		}else if (!ente.getSiglas().toUpperCase().matches(REGEX_TITLE)) {
			addFieldError("sigla",error.getProperties().getProperty("error.regex.title"));
		}	
		
		if(ente.getRif().trim().isEmpty()){
			addFieldError("rif",error.getProperties().getProperty("error.required"));
		}else{
			try {
				int rif = Integer.parseInt(ente.getRif());				
				if (rif < 1 || ente.getRif().length()<9) {
					addFieldError("rif",error.getProperties().getProperty("error.ente.rif.range"));
				}
			} catch (Exception e) {
				addFieldError("rif",error.getProperties().getProperty("error.ente.rif.range"));
			}
		}	
		getTipoRIF();
	}
	
	public void getTipoRIF(){
		rif_type.add("J");
		rif_type.add("G");
	}

	public int getAccion_ente() {
		return accion_ente;
	}

	public void setAccion_ente(int accion_ente) {
		this.accion_ente = accion_ente;
	}

	public List<Ente> getEntes() {
		return entes;
	}

	public void setEntes(List<Ente> entes) {
		this.entes = entes;
	}

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public List<String> getRif_type() {
		return rif_type;
	}

	public void setRif_type(List<String> rif_type) {
		this.rif_type = rif_type;
	}

	public String getTipo_rif() {
		return tipo_rif;
	}

	public void setTipo_rif(String tipo_rif) {
		this.tipo_rif = tipo_rif;
	}
}

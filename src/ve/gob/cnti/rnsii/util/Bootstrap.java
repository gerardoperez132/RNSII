package ve.gob.cnti.rnsii.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ve.gob.cnti.rnsii.dao.Constants;
import ve.gob.cnti.rnsii.dao.DAO;
import ve.gob.cnti.rnsii.dao.Constants.Modelos;
import ve.gob.cnti.rnsii.dao.Constants.Order;
import ve.gob.cnti.rnsii.dao.Constants.Tabs;
import ve.gob.cnti.rnsii.modelo.Area;
import ve.gob.cnti.rnsii.modelo.Arquitectura;
import ve.gob.cnti.rnsii.modelo.Estado;
import ve.gob.cnti.rnsii.modelo.Formato;
import ve.gob.cnti.rnsii.modelo.Intercambio;
import ve.gob.cnti.rnsii.modelo.Nacionalidad;
import ve.gob.cnti.rnsii.modelo.Sector;
import ve.gob.cnti.rnsii.modelo.Seguridad;
import ve.gob.cnti.rnsii.modelo.TipoDato;

@SuppressWarnings("serial")
public class Bootstrap extends DAO implements Constants, Tabs, Modelos, Order{
	
	private List<Sector> sectores = new ArrayList<Sector>();
	private List<Estado> estados = new ArrayList<Estado>();
	private List<Area> areas = new ArrayList<Area>();
	private List<Seguridad> niveles = new ArrayList<Seguridad>();
	private List<Arquitectura> arquitecturas = new ArrayList<Arquitectura>();
	private List<Intercambio> intercambios = new ArrayList<Intercambio>();
	private List<TipoDato> tipoDatos = new ArrayList<TipoDato>();
	private List<Formato> formatos = new ArrayList<Formato>();
	private List<Nacionalidad> nacionalidades = new ArrayList<Nacionalidad>();
	
	public Bootstrap() {		
		super();		
	}

	@SuppressWarnings("unchecked")
	public boolean isBDEmpty(){
		
		sectores = (List<Sector>) read(new Sector());
		if(sectores.size()<1)
			return true;
		
		estados = (List<Estado>) read(new Estado());
		if(estados.size()<1)
			return true;
		
		areas = (List<Area>) read(new Area());
		if(areas.size()<1)
			return true;
		
		niveles = (List<Seguridad>) read(new Seguridad());
		if(niveles.size()<1)
			return true;
		
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		if(arquitecturas.size()<1)
			return true;
			
		intercambios = (List<Intercambio>) read(new Intercambio());
		if(intercambios.size()<1)
			return true;
		
		tipoDatos = (List<TipoDato>) read(new TipoDato());
		if(tipoDatos.size()<1)
			return true;
						
		formatos = (List<Formato>) read(new Formato());
		if(formatos.size()<1)
			return true;
		
		nacionalidades = (List<Nacionalidad>) read(new Nacionalidad());
		if(nacionalidades.size()<1)
			return true;
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public void bootstrap_execute(){
		
		sectores = (List<Sector>) read(new Sector());
		if(sectores.size()<1)
			create_sectores();		
		
		estados = (List<Estado>) read(new Estado());
		if(estados.size()<1)
			create_estados();	
		
		areas = (List<Area>) read(new Area());
		if(areas.size()<1)
			create_areas();
		
		niveles = (List<Seguridad>) read(new Seguridad());
		if(niveles.size()<1)
			create_niveles();
		
		arquitecturas = (List<Arquitectura>) read(new Arquitectura());
		if(arquitecturas.size()<1)
			create_arquitecturas();
			
		intercambios = (List<Intercambio>) read(new Intercambio());
		if(intercambios.size()<1)
			create_intercambios();
		
		tipoDatos = (List<TipoDato>) read(new TipoDato());
		if(tipoDatos.size()<1)
			create_tipoDatos();
						
		formatos = (List<Formato>) read(new Formato());
		if(formatos.size()<1)
			create_formatos();
		
		nacionalidades = (List<Nacionalidad>) read(new Nacionalidad());
		if(nacionalidades.size()<1)
			create_nacionalidades();
				
	}
	
	private void create_sectores(){
		Sector sector = new Sector();
		String[] arr= {
				"Ciencia y Tecnología","Salud","Educación",
				"Transporte","Comercio","Finanzas, Banca y Seguros",
				"Energía y Minas","Alimentación","Agrícola",
				"Cultura","Vivienda","Ambiente y Turismo",
				"Defensa y Política Exterior","Protección Social",
				"Legislativo, Contraloría y Auditoría","Judicial",
				"Comunicación e Información"
		};
		for(String str: arr){
	        sector.setNombre(str);
	        create(sector);
		}
	}
	
	private void create_estados(){
		Estado obj = new Estado();
		String[] arr= {
				"En desarrollo","Implementado"
		};
		for(String str: arr){
	        obj.setNombre(str);
	        create(obj);
		}
	}
	
	private void create_areas(){
		Area obj = new Area();
		String[] arr= {
				"Natural","Jurídico","Gobierno"
		};
		for(String str: arr){
	        obj.setNombre(str);
	        create(obj);
		}
	}
	
	private void create_niveles(){
		Seguridad obj = new Seguridad();
		String[] arr= {
				"Público","Irrestricto","Autorizable"
		};
		for(String str: arr){
	        obj.setNombre(str);
	        create(obj);
		}
	}
	
	private void create_arquitecturas(){
		Arquitectura obj = new Arquitectura();
		String[] arr= {
				"Servicio Web","Web Semántica"
		};
		for(String str: arr){
	        obj.setNombre(str);
	        create(obj);
		}
	}

	private void create_intercambios(){
		Intercambio obj = new Intercambio();
		String[] arr= {
				"En Línea",	"Fuera de Línea"
		};
		for(String str: arr){
	        obj.setNombre(str);obj.setId_padre(0);
	        create(obj);
		}
		String[] arr2= {
				"Síncrono","Asíncrono"
		};
		for(String str: arr2){
	        obj.setNombre(str);obj.setId_padre(1);
	        create(obj);
		}
		obj.setNombre("En Lote");obj.setId_padre(2);
        create(obj);
	}
		
	private void create_tipoDatos(){
		save_tipoDatos("Lista de datos simples",0,false,false);	
		save_tipoDatos("String",1,true,false);
		save_tipoDatos("Integer",1,true,true);
		save_tipoDatos("Decimal",1,true,true);
		save_tipoDatos("Boolean",1,false,true);
		save_tipoDatos("Date",1,false,true);
		save_tipoDatos("Time",1,false,true);
	}
	
	private void save_tipoDatos(String cadena,long tipo,boolean formato,boolean longitud){
		TipoDato obj = new TipoDato();		
		Date date = new Date();
		obj.setNombre(cadena);obj.setId_tipo_dato(tipo);
		obj.setHasformatted(formato);obj.setHasLength(longitud);
		obj.setFecha_creado(date);obj.setFecha_modificado(date);
		obj.setDescripcion("Sin información");
        create(obj);
	}
	
	private void create_formatos(){
		save_formato("Con Signo",3);
		save_formato("Sin Signo",3);
		save_formato("True - False",5);
		save_formato("Verdadero - Falso",5);
		save_formato("1 - 0",5);
		save_formato("YYYY-MM-DD",6);
		save_formato("DD-MM-YYYY",6);
		save_formato("YYYY-MM-DDThh:mm:ss",6);
		save_formato("DD-MM-YYYYThh:mm:ss",6);
		save_formato("hh:mm:ss",7);
		save_formato("hh:mm",7);
		save_formato("Con Signo",4);
		save_formato("Sin Signo",4);
	}
	
	private void save_formato(String cadena,long id_tipo_dato){
		Formato obj = new Formato();		
		obj.setFormato(cadena);obj.setId_tipo_dato(id_tipo_dato);		
        create(obj);
	}
	
	private void create_nacionalidades(){
		Nacionalidad obj = new Nacionalidad();
		String[] arr= {
				"V","E"
		};
		for(String str: arr){
	        obj.setNombre(str);
	        create(obj);
		}
	}

}

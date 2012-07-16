package ve.gob.cnti.srsi.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ReadXmlTime {
	
	public List<Estados_Tiempo> getEstados_Tiempo(){
		SAXBuilder builder = new SAXBuilder();		
		List<Estados_Tiempo> estados = new ArrayList<Estados_Tiempo>();	
		try {
			URL xml_doc = new URL("http://www.inameh.gob.ve/pronostico.php");
			URLConnection xmlConnection = xml_doc.openConnection();					
			try {
				Document document = (Document) builder.build(xmlConnection.getInputStream());
				Element rootNode = document.getRootElement();
				@SuppressWarnings("rawtypes")
				List list = rootNode.getChildren("zona");
				for (int i = 0; i < list.size(); i++) {
					Estados_Tiempo estado = new Estados_Tiempo();
					Element node = (Element) list.get(i);
					estado.setCodigo(Integer.parseInt(node.getChildText("codigo")));
					estado.setNombre(node.getChildText("nombre"));
					estado.setT_max(Integer.parseInt(node.getChildText("temp_m")));
					estado.setT_min(Integer.parseInt(node.getChildText("temp_mi")));
					estados.add(estado);
				}
			} catch (IOException io) {
				System.out.println(io.getMessage());
			} catch (JDOMException jdomex) {
				System.out.println(jdomex.getMessage());
			}			
		} catch (MalformedURLException me) {
			System.out.println("MalformedURLException: " + me);
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe);
		}
		return estados;
	}
	
	public Date getFechaTiempo(){
		return new Date();
	}
	
}

/* This file is part of SRSI.
 * 
 * SRSI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * SRSI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SRSI. If not, see <http://www.gnu.org/licenses/>.
 */
package ve.gob.cnti.srsi.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * Esta clase permite obtener los estados del tiempo leyendo el archivo XML
 * dispuesto por el INAMEH.
 * 
 * @author Joaquín Pereira
 * 
 */
public class ReadXmlTime implements ServletRequestAware {
	private HttpServletRequest servletRequest;

	public List<EstadosTiempo> getEstadosTiempo() {
		List<EstadosTiempo> estados = new ArrayList<EstadosTiempo>();
		try {
			URL xml_doc = new URL("http://www.inameh.gob.ve/pronostico.php");
			URLConnection xmlConnection = xml_doc.openConnection();
			xmlConnection.setConnectTimeout(1000);
			xmlConnection.setReadTimeout(1000);
			estados = connect(xmlConnection);
		} catch (MalformedURLException me) {
			System.out.println("MalformedURLException: " + me);
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe);
		}
		return estados;
	}

	private List<EstadosTiempo> connect(URLConnection urlConnection)
			throws IOException {
		SAXBuilder builder = new SAXBuilder();
		List<EstadosTiempo> estados = new ArrayList<EstadosTiempo>();
		// String path = servletRequest.getSession().getServletContext()
		// .getRealPath("/");
		String path = servletRequest.getSession().getServletContext()
				.getContextPath();
		System.out.println("path => " + path);
		URL xml_doc = new URL(path);
		URLConnection xmlConnection = xml_doc.openConnection();
		xmlConnection.setConnectTimeout(1000);
		xmlConnection.setReadTimeout(1000);
		try {
			Document document = (Document) builder.build(urlConnection
					.getInputStream());
			Element rootNode = document.getRootElement();
			@SuppressWarnings("rawtypes")
			List list = rootNode.getChildren("zona");
			for (int i = 0; i < list.size(); i++) {
				EstadosTiempo estado = new EstadosTiempo();
				Element node = (Element) list.get(i);
				estado.setCodigo(Integer.parseInt(node.getChildText("codigo")));
				estado.setNombre(node.getChildText("nombre"));
				estado.setT_max(Integer.parseInt(node.getChildText("temp_m")));
				estado.setT_min(Integer.parseInt(node.getChildText("temp_mi")));
				estados.add(estado);
			}
		} catch (IOException io) {
			System.out.println("EL PRIMERO " + io.getMessage());
			estados = connect(xmlConnection);
		} catch (JDOMException jdomex) {
			System.out.println("EL SEGUNDO" + jdomex.getMessage());
		}
		return estados;
	}

	public Date getFechaTiempo() {
		return new Date();
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
}
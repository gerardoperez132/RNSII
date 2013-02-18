/* This file is part of RNSII.
 * 
 * RNSII is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * RNSII is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with RNSII. If not, see <http://www.gnu.org/licenses/>.
 */
package ve.gob.cnti.rnsii.util;

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

import ve.gob.cnti.rnsii.i18n.Messages;

/**
 * Esta clase permite obtener los estados del tiempo leyendo el archivo XML
 * dispuesto por el INAMEH.
 * 
 * @author Joaquín Pereira
 * 
 */
public class ReadXmlTime {
	private static int mTimeout = 1000;

	public List<EstadosTiempo> getEstadosTiempo() {
		List<EstadosTiempo> estados = new ArrayList<EstadosTiempo>();
		try {
			URL xml_doc = new URL("http://www.inameh.gob.ve/pronostico.php");
			URLConnection xmlConnection = xml_doc.openConnection();
			xmlConnection.setConnectTimeout(mTimeout);
			xmlConnection.setReadTimeout(mTimeout);
			estados = connect(xmlConnection);
		} catch (MalformedURLException me) {
			System.out.println("MalformedURLException: " + me);
		} catch (IOException io) {
			System.out.println("IOException: " + io);
		}
		return estados;
	}

	/**
	 * Este método realiza la conexión al archivo XML. Además maneja la
	 * excepción para cuando el servicio del INAMEH no esté disponible en el
	 * tiempo de respuesta usual, para realizar una llamada recursiva utilizando
	 * otros parámetros de conexión.
	 * 
	 * @author Richard Ricciardelli
	 * @author Joaquín Pereira
	 * @param urlConnection
	 *            Objeto con los parámetros de conexión.
	 * @return Lista de los estados del tiempo según el archivo XML.
	 * @throws IOException
	 *             Excepción suscitada durante la formación del objeto URL
	 *             alternativo.
	 */
	private List<EstadosTiempo> connect(URLConnection urlConnection)
			throws IOException {
		SAXBuilder builder = new SAXBuilder();
		List<EstadosTiempo> estados = new ArrayList<EstadosTiempo>();
		// TODO What the hell is going on here?
		// String path = servletRequest.getSession().getServletContext()
		// .getRealPath("/");
		URL xml_doc = new URL(new Messages().getProperties().getProperty(
				"localhost.tiempo"));
		URLConnection xmlConnection = xml_doc.openConnection();
		xmlConnection.setConnectTimeout(mTimeout);
		xmlConnection.setReadTimeout(mTimeout);
		try {
			Document document = (Document) builder.build(urlConnection
					.getInputStream());
			Element rootNode = document.getRootElement();
			List<Element> list = rootNode.getChildren("zona");
			for (Element node : list) {
				EstadosTiempo estado = new EstadosTiempo();
				estado.setCodigo(Integer.parseInt(node.getChildText("codigo")));
				estado.setNombre(node.getChildText("nombre"));
				estado.setT_max(Integer.parseInt(node.getChildText("temp_m")));
				estado.setT_min(Integer.parseInt(node.getChildText("temp_mi")));
				estados.add(estado);
			}
		} catch (IOException io) {
			System.out.println("Recuperando del archivo de respaldo XML ["
					+ io.getMessage() + "]");
			estados = connect(xmlConnection);
		} catch (JDOMException jdomex) {
			System.out.println("Manejando excepción: " + jdomex.getMessage());
		}
		return estados;
	}

	public Date getFechaTiempo() {
		return new Date();
	}
}
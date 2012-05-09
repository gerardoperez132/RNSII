package ve.gob.cnti.srsi.i18n;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Carga el archivo de propiedades de internacionalización con los mensajes de
 * error y excepciones consideradas en la aplicación.
 * 
 * @author Richard Ricciardelli
 * 
 */
public class Errors {
	private Properties properties = new Properties();

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Errors() {
		try {
			properties.load(Errors.class
					.getResourceAsStream("errors.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
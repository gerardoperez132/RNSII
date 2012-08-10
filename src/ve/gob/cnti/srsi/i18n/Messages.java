package ve.gob.cnti.srsi.i18n;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Carga el archivo de propiedades de internacionalización con los mensajes de
 * generales en la aplicación.
 * 
 * @author Richard Ricciardelli
 * 
 */
public class Messages {
	private Properties properties = new Properties();

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Messages() {
		try {
			properties.load(Messages.class
					.getResourceAsStream("messages.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
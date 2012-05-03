package ve.gob.cnti.srsi.i18n;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Errors {
	private Properties properties = new Properties();

	public Properties getProperties() {
		String path = "/home/rricciardelli/eclipse/workspace/SRSI/src/ve/gob/cnti/srsi/i18n/errors.properties";
		System.out.println("PATH ERRORS => " + path);
		try {
			properties.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DAMN IT FILE NOT FOUND!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DAMN IT IO EXCEPTION FUCK OFF!!!");
		}
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
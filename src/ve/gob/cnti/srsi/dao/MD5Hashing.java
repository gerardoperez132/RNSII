package ve.gob.cnti.srsi.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase que se encarga de encriptar una cadena de texto utilizando el algoritmo
 * de codificación MD5.
 * 
 * @author Richard Ricciardelli
 * 
 */
public class MD5Hashing {
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MD5Hashing(String password) throws NoSuchAlgorithmException {
		super();
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++)
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		this.password = sb.toString();
	}

	@Override
	public String toString() {
		return "MD5Hashing [password=" + password + "]";
	}
}
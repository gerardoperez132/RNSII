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
package ve.gob.cnti.srsi.dao;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Clase para enviar correo electrónico.
 * 
 * @author Joaquín Pereira
 */
@SuppressWarnings("serial")
public class EnviarCorreo extends DAO {

	public EnviarCorreo() {
	}

	public boolean send(String email, String asunto, String mensaje) {
		String mail_smtp_host = error.getProperties().getProperty(
				"mail_smtp_host");
		String mail_smtp_starttls_enable = error.getProperties().getProperty(
				"mail_smtp_starttls_enable");
		String mail_smtp_port = error.getProperties().getProperty(
				"mail_smtp_port");
		String mail_smtp_user = error.getProperties().getProperty(
				"mail_smtp_user");
		String mail_smtp_auth = error.getProperties().getProperty(
				"mail_smtp_auth");
		String pass = error.getProperties().getProperty("pass");
		try {
			// Propiedades de la conexión
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", mail_smtp_host);
			props.setProperty("mail.smtp.starttls.enable",
					mail_smtp_starttls_enable);
			props.setProperty("mail.smtp.port", mail_smtp_port);
			props.setProperty("mail.smtp.user", mail_smtp_user);
			props.setProperty("mail.smtp.auth", mail_smtp_auth);

			// Preparamos la sesion
			Session session = Session.getDefaultInstance(props);

			// Construimos el mensaje
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mail_smtp_user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					mail_smtp_user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					email.toString()));
			message.setSubject(asunto.toString().toUpperCase());
			message.setText(mensaje);

			// Lo enviamos.
			Transport t = session.getTransport("smtp");
			t.connect(mail_smtp_user, pass);
			t.sendMessage(message, message.getAllRecipients());

			// Cierre.
			t.close();
		} catch (Exception e) {
			System.out.println(">> MailSender.send() error = " + e);
			return false;
		}
		return true;
	}
}
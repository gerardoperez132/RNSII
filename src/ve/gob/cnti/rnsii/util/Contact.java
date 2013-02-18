package ve.gob.cnti.rnsii.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import ve.gob.cnti.rnsii.dao.Constants;
import ve.gob.cnti.rnsii.dao.DAO;
import ve.gob.cnti.rnsii.mail.EnviarCorreo;
import ve.gob.cnti.rnsii.modelo.Correo;
import ve.gob.cnti.rnsii.modelo.ServicioInformacion;

import com.opensymphony.xwork2.ActionContext;

/**
 * Clase para el contacto a través de un formulario.
 * 
 * @author Richard Ricciardelli
 * 
 */
public class Contact extends DAO implements Constants.DateFormatting {

	/**
	 * Default serial version.
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String subject;
	private String message;
	private String captcha;
	private int id_servicio_informacion;
	@SuppressWarnings("rawtypes")
	private Map session;
	private List<String> errors;

	public boolean validation() {
		boolean e = false;
		session = ActionContext.getContext().getSession();
		errors = new ArrayList<String>();
		if (name.trim().isEmpty()) {
			errors.add(error.getProperties().getProperty("error.contact.name"));
			e = true;
		} else if (!name.toUpperCase().matches(REGEX_TITLE)) {
			errors.add(error.getProperties().getProperty("error.regex.title"));
			e = true;
		} else
			errors.add("");

		if (email.trim().isEmpty()) {
			errors.add(error.getProperties().getProperty("error.contact.email"));
			e = true;
		} else if (!email.matches(REGEX_EMAIL)) {
			errors.add(error.getProperties().getProperty("error.regex.email"));
			e = true;
		} else
			errors.add("");

		if (subject.trim().isEmpty()) {
			errors.add(error.getProperties().getProperty(
					"error.contact.subject"));
			e = true;
		} else if (!subject.toUpperCase().matches(REGEX_TITLE)) {
			errors.add(error.getProperties().getProperty("error.regex.title"));
			e = true;
		} else
			errors.add("");

		if (message.trim().isEmpty()) {
			errors.add(error.getProperties().getProperty(
					"error.contact.message"));
			e = true;
		} else if (!message.toUpperCase().matches(REGEX_DESCRIPTION)) {
			errors.add(error.getProperties().getProperty(
					"error.regex.description"));
			e = true;
		} else
			errors.add("");

		if (captcha.trim().isEmpty()) {
			errors.add(error.getProperties().getProperty("error.login.captcha"));
			e = true;
		} else if (!session.get("captcha").toString().toUpperCase()
				.equals(captcha.toUpperCase())) {
			System.out.println("CAPTCHA SESSION => "
					+ session.get("captcha").toString());
			System.out.println("CAPTCHA INTRODUCIDO => " + captcha);
			errors.add(error.getProperties().getProperty("error.login.captcha"));
			e = true;
		} else
			errors.add("");
		remove();
		put();
		return e;
	}

	private void remove() {
		session.remove("name");
		session.remove("email");
		session.remove("subject");
		session.remove("message");
		session.remove("errors");
		session.remove("id_error");
	}

	@SuppressWarnings("unchecked")
	private void put() {
		session.put("name", name);
		session.put("email", email);
		session.put("subject", subject);
		session.put("message", message);
		session.put("errors", errors);
		session.put("id_error", id_servicio_informacion);
	}

	public String sendEmail() {
		if (validation())
			return INPUT;
		Correo recipient = new Correo();
		try {
			recipient = getEmail(new ServicioInformacion(),
					id_servicio_informacion);
		} catch (Exception e) {
			System.out.print("No se pudo recuperar el correo");
		}
		System.out.println(toString());
		System.out.println("CORREO => " + recipient.getCorreo());
		String header = "Mensaje enviado por " + name + " el día "
				+ new SimpleDateFormat(VET_FORMAT).format(new Date()) + "\n\n";
		String footer = "\n\nEnviado desde la Plataforma para el Registro de Servicios de Información Interoperables.";
		EnviarCorreo enviarCorreo = new EnviarCorreo();
		// if (enviarCorreo.send("ricciardelli2021@gmail.com", subject, header
		// + message + footer)) {
		if (enviarCorreo.send(recipient.getCorreo(), subject, header + message
				+ footer))
			remove();
		else
			System.out.print("Error enviando mensaje");
		return SUCCESS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public int getId_servicio_informacion() {
		return id_servicio_informacion;
	}

	public void setId_servicio_informacion(int id_servicio_informacion) {
		this.id_servicio_informacion = id_servicio_informacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", email=" + email + ", subject="
				+ subject + ", message=" + message + ", captcha=" + captcha
				+ ", id_servicio_informacion=" + id_servicio_informacion + "]";
	}

}

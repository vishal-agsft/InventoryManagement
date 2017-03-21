package com.agile.bl.utility;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * @author Vishal Arora
 * 
 *         mailing utility for the system when the user accepts or declines the
 *         request for an inventory item
 *
 */
public class MailFunctionality {

	private static Logger log = Logger.getLogger(MailFunctionality.class);
	/**
	 * @param to : recipient of the email 
	 * @param subject : subject of the email (approve/decline)
	 * @param body : content of the email
	 */
	public static void sendEmail(String to, String subject, String body) throws AddressException, MessagingException {

		
		final String from = "agilesoft2015@gmail.com";
		final String username = "agilesoft2015@gmail.com";
		final String password = "welcome123$";
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// final String host = "mail.india.com";
		final String host = "smtp.gmail.com";

		Properties props = System.getProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		// props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.host", host);
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.port", "465");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// Create a default MimeMessage object.
		Message message = new MimeMessage(session);

		// Set From: header field of the header.
		message.setFrom(new InternetAddress(from));
		// Set To: header field of the header.
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

		// Set Subject: header field
		message.setSubject(subject);

		// Now set the actual message
		message.setText(body);

		// Send message
		Transport.send(message);

		log.debug("Sent message successfully to : " + to);
	}

}
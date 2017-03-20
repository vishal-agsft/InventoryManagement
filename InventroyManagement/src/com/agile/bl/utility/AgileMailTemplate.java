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

public class AgileMailTemplate {

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
	//	props.put("mail.smtp.ssl.enable", "true");
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
		System.out.println("Set with session");

		// Set From: header field of the header.
		message.setFrom(new InternetAddress(from));
		System.out.println("Set with i[");

		// Set To: header field of the header.
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

		// Set Subject: header field
		message.setSubject(subject);

		// Now set the actual message
		message.setText(body);

		// Send message
		Transport.send(message);

		System.out.println("Sent message successfully....");
	}

}
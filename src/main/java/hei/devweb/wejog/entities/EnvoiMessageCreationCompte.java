package hei.devweb.wejog.entities;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoiMessageCreationCompte {
	
public static void main(String email, String firstName, String password){
		
		String to = email;
	    String from =  "weejog@gmail.com";

	     Properties props = new Properties();
	     props.setProperty("mail.transport.protocol", "smtp");
	     props.setProperty("mail.host", "smtp.gmail.com");
	     props.put("mail.smtp.starttls.enable", "true");
	     props.put("mail.smtp.auth", "true");
	     props.put("mail.smtp.starttls.enable", "true");

	     Session session = Session.getDefaultInstance(props);
	     session.setDebug(true);

	     try {
	         MimeMessage mess = new MimeMessage(session);

	         mess.setFrom(new InternetAddress(from));

	         mess.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         mess.setSubject("Welcome to WeJog");

	         mess.setText("Hi "+firstName+", welcome to WeJog !"+"\n"+"\n"+"Here are your account's informations : "+"\n"+"Your ident : "+email+"\n"+"Your password : "+password);

	         Transport trans = session.getTransport("smtp");
	         trans.connect("smtp.gmail.com", 587, "weejog@gmail.com", "benallalminaud");
	         trans.sendMessage(mess, mess.getAllRecipients());

	         System.out.println("Message Sent !");

	     } catch (MessagingException mex) {
	         mex.printStackTrace();
	     }
	   
	}

}

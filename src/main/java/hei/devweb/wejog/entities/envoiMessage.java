package hei.devweb.wejog.entities;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import hei.devweb.wejog.servlets.ContactServlet;

public class envoiMessage{
	
	public static void main(String email, String name, String message){
		
		String to = "weejog@gmail.com";
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

	         mess.setSubject("Message from website");

	         mess.setText("Mail adresse : "+email+"\n"+"\n"+"Person's name : "+name+"\n"+"\n"+"Message : "+message);

	         Transport trans = session.getTransport("smtp");
	         trans.connect("smtp.gmail.com", 587, "weejog@gmail.com", "benallalminaud");
	         trans.sendMessage(mess, mess.getAllRecipients());

	         System.out.println("Message Sent !");

	     } catch (MessagingException mex) {
	         mex.printStackTrace();
	     }
	   
	}
			
		 
}
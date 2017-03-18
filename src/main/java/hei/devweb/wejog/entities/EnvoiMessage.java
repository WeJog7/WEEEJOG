package hei.devweb.wejog.entities;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoiMessage {

	public static void main(String email, String firstName, String contenu, String typeOfMail){

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
			
			switch (typeOfMail){
			
			case "changePassword" :
				mess.setSubject("Password modification");

				mess.setText("Hi "+firstName+", your password has been changed,"+"\n"+"\n"
						+"Here are your new account's informations : "+"\n"
						+"Your ident : "+email+"\n"+"Your password : "+contenu+"\n"+"\n"
						+"If you didn't ask for this change, please contact us as quick as possible."+"\n"+"\n"
						+"Please do not answer to this message.");
				break;
				
				
			case "Recovery Password":
				mess.setSubject("Password modification");

				mess.setText("Hi "+firstName+", your password has been changed,"+"\n"+"\n"
						+"Here are your new account's informations : "+"\n"
						+"Your ident : "+email+"\n"+"Your password : "+contenu+"\n"+"\n"
						+"If you didn't ask for this change, please contact us as quick as possible."+"\n"+"\n"
						+"Please do not answer to this message.");
				break;
				
			case "contactUs":
				mess.setSubject("Message from website");

		        mess.setText("Mail adresse : "+email+"\n"+"\n"+"Person's name : "+firstName+"\n"+"\n"+"Message : "+contenu);
		         
				break;
				
			case "createAccount" :
				mess.setSubject("Welcome to WeJog");

		         mess.setText("Hi "+firstName+", welcome to WeJog !"+"\n"+"\n"
		        		 +"Here are your account's informations : "+"\n"
		        		 +"Your ident : "+email+"\n"
		        		 +"Your password : "+contenu+"\n"+"\n"
		        		 +"Please do not answer to this message.");
		         break;
			}
				
			
			Transport trans = session.getTransport("smtp");
			trans.connect("smtp.gmail.com", 587, "weejog@gmail.com", "benallalminaud");
			trans.sendMessage(mess, mess.getAllRecipients());

			System.out.println("Message Sent !");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

}

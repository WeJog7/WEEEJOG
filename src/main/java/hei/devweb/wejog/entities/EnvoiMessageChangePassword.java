package hei.devweb.wejog.entities;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoiMessageChangePassword {

	public static void main(String email, String firstName, String password, String typeOfMail){

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

			if(typeOfMail.equals("forgetPassword")){

				mess.setSubject("Password modification");

				mess.setText("Hi "+firstName+", your password has been changed,"+"\n"+"\n"
						+"Here are your new account's informations : "+"\n"
						+"Your ident : "+email+"\n"+"Your password : "+password+"\n"+"\n"
						+"If you didn't ask for this change, please contact us as quick as possible."+"\n"+"\n"
						+"Please do not answer to this message.");
			}

			else{
				
				mess.setSubject("Recovery Password");

				mess.setText("Hi "+firstName+", your have informed us that you have forgotten your password,"+"\n"+"\n"
						+"Here are your new account's informations : "+"\n"
						+"Your ident : "+email+"\n"+"Your password : "+password+"\n"+"\n"
						+"If you didn't ask for this change, please contact us as quick as possible."+"\n"+"\n"
						+"Please do not answer to this message.");
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

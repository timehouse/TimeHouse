package _03_member_password.controller;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class GmailSendMailviaTLS {
	
	public static void main(String []args){
		GmailSendMailviaTLS mail = new GmailSendMailviaTLS();		
		mail.sendEmail();
	}
	
	 public void sendEmail(){
	  String host = "smtp.gmail.com";
	  int port = 587;
	  						//sender
	  final String username = "timehouse.eeit83@gmail.com";
	  final String password = "theeit83";//your password

	  Properties props = new Properties();
	  props.put("mail.smtp.host", host);
	  props.put("mail.smtp.auth", "true");
	  props.put("mail.smtp.starttls.enable", "true");
	  props.put("mail.smtp.port", port);
	  Session session = Session.getInstance(props, new Authenticator() {
	   protected PasswordAuthentication getPasswordAuthentication() {
	    return new PasswordAuthentication(username, password);	
	   }
	  });

	  try {

	   Message message = new MimeMessage(session);
	   message.setFrom(new InternetAddress("leochen1105@gmail.com"));
	   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("eeit83.timehouse@gmail.com"));
	   message.setSubject("測試寄信.");
	   message.setText("Dear Levin, \n\n 測試 測試 測試 測試 測試 測試 email !");

	   Transport transport = session.getTransport("smtp");
	   transport.connect(host, port, username, password);

	   Transport.send(message);

	   System.out.println("寄送email結束.");

	  } catch (MessagingException e) {
	   throw new RuntimeException(e);
	  }
	 }
	}
package _03_member_password.controller;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class Email {
	private final String username = "timehouse.eeit83@gmail.com";
	private final String password = "theeit83";
	private int port = 587;
	private String host = "smtp.gmail.com";
	
	public boolean sendEmail(String toMailAdd,String mailTitle,String mailBody){
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
		   message.setFrom(new InternetAddress(username));
		   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMailAdd));
		   message.setSubject(mailTitle);  //這是信件名稱
		   message.setText(mailBody);  //這是內容

		   Transport transport = session.getTransport("smtp");
		   transport.connect(host, port, username, password);

		   Transport.send(message);

		   return true;

		  } catch (MessagingException e) {	
			  e.printStackTrace();
		  }
		  return false;
	}
	
	
	
	public String messageFromFile(String FileName) {
		File file = null;
		FileReader input = null;
		BufferedReader br = null;
		String message=null;
		

		try {
			file = new File(this.getClass().getResource("/").toURI().getPath()
					+ "/message/"+FileName);
			input = new FileReader(file);
			br = new BufferedReader(input);
			String in = null;
			while ((in = br.readLine()) != null) {
				if(message ==null){
					message=in+"\r";
					
				}else{
		        message=message+in+"\r";		
		        }
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return message;

	}
	
	
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	
	public boolean sendEmail2(String toMailAdd,String mailTitle,String mailBody,String mailImage){
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
//mail主體
		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(username));
		   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMailAdd));
		   message.setSubject(mailTitle);  //這是信件名稱
		  // message.setText(mailBody);  //這是內容
		 //  System.out.println(mailBody);
		   
		   
		   //文字部分
		   MimeBodyPart textPart = new MimeBodyPart();
           StringBuffer html = new StringBuffer();
           html.append("<h2>自由服務網</h2><br>");
        //   html.append("<h3>這是第二行，下面會是圖</h3><br>");
           html.append("<div>"+mailBody+"</div>");
           html.append("<img src=\'cid:image\'>"); // \'cid:image\' 

           textPart.setContent(html.toString(), "text/html; charset=UTF-8");
		   
		   
           MimeBodyPart picturePart = new MimeBodyPart();	
           File file = new File(this.getClass().getResource("/").toURI().getPath()+ "/message/"+mailImage);
           FileDataSource fds = new FileDataSource(file);
           picturePart.setDataHandler(new DataHandler(fds));
           picturePart.setFileName(fds.getName());  
           picturePart.setHeader("Content-ID", "<image>");
           if(mailImage.indexOf("gif")!= -1){
        	   picturePart.addHeader("Content-Type", "image/gif");
           }else if(mailImage.indexOf("jpg")!= -1){
        	   picturePart.addHeader("Content-Type", "image/jpg");
           }else if(mailImage.indexOf("jpeg")!= -1){
        	   picturePart.addHeader("Content-Type", "image/jpeg");
           }
           
		   
           Multipart email = new MimeMultipart();
           email.addBodyPart(textPart);
           email.addBodyPart(picturePart);

           message.setContent(email);
//           message.addRecipient(Message.RecipientType.TO, new InternetAddress(
//           		                 "davidrfdg@gmail.com"));
		   
		   

		   Transport transport = session.getTransport("smtp");
		   transport.connect(host, port, username, password);

		   Transport.send(message);
		   
		  // transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
           transport.close();

		   return true;

		  } catch (MessagingException e) {	
			  e.printStackTrace();
		      
		  } catch (URISyntaxException e){
			  e.printStackTrace();
		  }
		  return false;
		  
		  
		
	}
	
	
	
	
	
	public String messageFromFile2(String FileName) {
		File file = null;
		FileReader input = null;
		BufferedReader br = null;
		String message=null;
		

		try {
			file = new File(this.getClass().getResource("/").toURI().getPath()
					+ "/message/"+FileName);
			input = new FileReader(file);
			br = new BufferedReader(input);
			String in = null;
			while ((in = br.readLine()) != null) {
				if(message ==null){
					message=in+"<br>";
					
				}else{
		        message=message+in+"<br>";		
		        }
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return message;

	}
	
	
	
	
	
	
	public static void main(String args[]) {
		
//     	Email test=new Email();	
     	//純文字
//		boolean type =test.sendEmail("eeit83.timehouse@gmail.com", "Time House，Email認證~~","<h1>qw831</h1>");  
     	
     	//文字+圖片
    //	boolean type =test.sendEmail("jerrygod55123@gmail.com", "自由服務網，Email認證~~","123");
    // 	boolean type =test.sendEmail2("carlos20050610@hotmail.com", "自由服務網，Email認證~~", test.messageFromFile2("email.txt"),"ssdj165asd.gif");
     	//boolean type =test.textPicture();
//     	if(type){
//	    	System.out.println("傳送成功");
//	    }else{
//	    	System.out.println("傳送失敗");
//	    }
//		
     	Email test=new Email();	
     	//純文字
     	int i = (int) ((Math.random()*100)+1);
     	
		boolean type =test.sendEmail("eeit83.timehouse@gmail.com", "Time House，Email認證~~","請點選網址回到網站首頁已完成認證:"
				+ "http://localhost:8080/ProjectNew/01_Client/index.jsp");
		
     	if(type){
	    	System.out.println("傳送成功");
	    }else{
	    	System.out.println("傳送失敗");
	    }
		
     	
	}
	

}


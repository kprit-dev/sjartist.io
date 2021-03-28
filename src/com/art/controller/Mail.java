package com.art.controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;


public class Mail {
	
	public static void composeMail(String recp,String msg) {
		
		String message=msg;
		String subject="Confirmation";
		String to=recp;
		String from="sjartandcreation@gmail.com";
		
		sendMail(to,subject,message,from);
	}
	
	public static void sendMail(String to,String subject,String message,String from) {
		
		//String host="smtp.gmail.com";
		
		Properties properties=System.getProperties();
		System.out.println("Properties : "+properties);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
    Session session=Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("sjartandcreation@gmail.com","Anna@2021");
			}
			
		}); 
    session.setDebug(true);
    MimeMessage m=new MimeMessage(session);
    try {
    m.setFrom(from);
    m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    m.setSubject(subject);
    m.setText(message);
    
    Transport.send(m);
    
    System.out.println("Sending mail success...........");
	}catch(Exception e	) {
		e.printStackTrace();
	}
	}
	
}

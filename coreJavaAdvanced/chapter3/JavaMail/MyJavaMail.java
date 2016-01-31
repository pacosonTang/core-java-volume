package com.corejava.chapter3;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class MyJavaMail
{
	public static void main(String[] args)
	{
		Properties props = new Properties();
		props.put("mail.host", "smtp.163.com"); // for i use 163.com email
		props.setProperty("mali.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		// public static Session getInstance(Properties props,
        // Authenticator authenticator)
		Session session = Session.getInstance(props, null);

		try
		{
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom("**@163.com"); // type your sender email, Из *@163.com 
			msg.setRecipients(Message.RecipientType.TO, "*@sina.com"); // type your receiver email, Из *@sina.com
			msg.setSubject("JavaMail hello world example");
			msg.setSentDate(new Date());
			msg.setText("Hello, world! this msg is from xiaotantang ! \n");
			
			Transport.send(msg, "*@163.com", "*"); // type your account and pass of sender email
		} catch (MessagingException mex)
		{
			System.out.println("send failed, exception: " + mex);
		}
	}
}




















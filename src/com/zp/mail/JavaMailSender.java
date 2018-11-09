package com.zp.mail;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class JavaMailSender {
	/*
	public static void main(String[] args) {
		 qqSender();
	}
	*/

	/*
	 * 通过qq邮箱发送邮件,qq邮箱需要在设置里开启POP3/SMTP的授权，通过用户名+授权码方式才能发邮件
	 */
	public static void qqSender(String EContent) {
		MailSSLSocketFactory msf = null;
		
		try {
			msf = new MailSSLSocketFactory();
			msf.setTrustAllHosts(true);
		} catch (GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Properties props = new Properties();
		// 开启调试
		props.setProperty("mail.debug", "true");
		// 是否需要验证
		props.setProperty("mail.smtp.auth", "true");
		//设置端口
		props.setProperty("mail.smtp.port", "465");  
		
		//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// 发送邮件服务器
		props.setProperty("mail.smtp.host", "smtp.qq.com");
		// 发送邮件协议名称
		props.setProperty("mail.transport.protocol", "SMTP");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", msf);

		// 使用匿名内部类，用邮箱进行验证
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 通过用户名和密码进行验证
				return new PasswordAuthentication("2907142698@qq.com", "rixlwkmqfmcvddcf");
			}

		});
		Message message = new MimeMessage(session);
		try {
			// 设置邮件发送方
			message.setFrom(new InternetAddress("2907142698@qq.com"));
			// 设置邮件标题
			message.setSubject("提货单配送状态推送异常报告");
			// 设置邮件内容
			message.setContent(EContent, "text/html;charset=utf-8");
			// 设置邮件接收方
			message.addRecipient(RecipientType.TO, new InternetAddress("lalala719@126.com"));
			message.addRecipient(RecipientType.TO, new InternetAddress("806394605@qq.com"));
			message.addRecipient(RecipientType.TO, new InternetAddress("lg7768075@qq.com"));

			// 发送邮件
			Transport.send(message);

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
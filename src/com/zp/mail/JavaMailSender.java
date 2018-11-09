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
	 * ͨ��qq���䷢���ʼ�,qq������Ҫ�������￪��POP3/SMTP����Ȩ��ͨ���û���+��Ȩ�뷽ʽ���ܷ��ʼ�
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
		// ��������
		props.setProperty("mail.debug", "true");
		// �Ƿ���Ҫ��֤
		props.setProperty("mail.smtp.auth", "true");
		//���ö˿�
		props.setProperty("mail.smtp.port", "465");  
		
		//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// �����ʼ�������
		props.setProperty("mail.smtp.host", "smtp.qq.com");
		// �����ʼ�Э������
		props.setProperty("mail.transport.protocol", "SMTP");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", msf);

		// ʹ�������ڲ��࣬�����������֤
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// ͨ���û��������������֤
				return new PasswordAuthentication("2907142698@qq.com", "rixlwkmqfmcvddcf");
			}

		});
		Message message = new MimeMessage(session);
		try {
			// �����ʼ����ͷ�
			message.setFrom(new InternetAddress("2907142698@qq.com"));
			// �����ʼ�����
			message.setSubject("���������״̬�����쳣����");
			// �����ʼ�����
			message.setContent(EContent, "text/html;charset=utf-8");
			// �����ʼ����շ�
			message.addRecipient(RecipientType.TO, new InternetAddress("lalala719@126.com"));
			message.addRecipient(RecipientType.TO, new InternetAddress("806394605@qq.com"));
			message.addRecipient(RecipientType.TO, new InternetAddress("lg7768075@qq.com"));

			// �����ʼ�
			Transport.send(message);

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
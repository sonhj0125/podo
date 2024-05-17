package util.gmail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GoogleMail {

	public void send_certification_code(String recipient, String certification_code) throws Exception { 

    	Properties prop = new Properties(); 

    	prop.put("mail.smtp.user", "rtw1024sj@gmail.com"); 

    	prop.put("mail.smtp.host", "smtp.gmail.com");

    	prop.put("mail.smtp.port", "465");
    	prop.put("mail.smtp.starttls.enable", "true");
    	prop.put("mail.smtp.auth", "true");
    	prop.put("mail.smtp.debug", "true");
    	prop.put("mail.smtp.socketFactory.port", "465");
    	prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	prop.put("mail.smtp.socketFactory.fallback", "false");

    	prop.put("mail.smtp.ssl.enable", "true");
    	prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    	prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

    	Authenticator smtpAuth = new MySMTPAuthenticator();
    	Session ses = Session.getInstance(prop, smtpAuth);

    	ses.setDebug(true);

    	MimeMessage msg = new MimeMessage(ses);

    	// 제목 설정
    	String subject = "PODO 비밀번호 찾기";
    	msg.setSubject(subject);

    	// 보내는 사람의 메일주소
    	String sender = "redtree041901@gmail.com";
    	Address fromAddr = new InternetAddress(sender);
    	msg.setFrom(fromAddr);

    	// 받는 사람의 메일주소
    	Address toAddr = new InternetAddress(recipient);
    	msg.addRecipient(Message.RecipientType.TO, toAddr);

    	// 메시지 본문의 내용과 형식, 캐릭터 셋 설정
    	msg.setContent("발송된 인증코드 : <span style='font-size:14pt; color:red;'>"+certification_code+"</span>", "text/html;charset=UTF-8"); 

    	// 메일 발송하기
    	Transport.send(msg);

	}// end of public void send_certification_code(String recipient, String certification_code) throws Exception--------	

}
package com.java8.emailsender.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.java8.emailsender.dto.EmailRequest;
import com.java8.emailsender.dto.EmailResponse;

import lombok.Data;

@Service
@Data
public class EmailsService {
	
	@Autowired
	private SimpleMailMessage mailMessage;
	
	private JavaMailSender primaryJavaMailSender;
    private JavaMailSender secondaryJavaMailSender;
    private JavaMailSender brokerJavaMailSender;
    private JavaMailSender buyerJavaMailSender;
    private JavaMailSender manufacturerJavaMailSender;
    private JavaMailSender clientsJavaMailSender;
    
    @Value("${spring.mail.primary.username}")
    private String primaryEmail;
    @Value("${spring.mail.secondary.username}")
    private String secondaryEmail;
    @Value("${spring.mail.broker.username}")
    private String brokerEmail;
    @Value("${spring.mail.buyer.username}")
    private String buyerEmail;
    @Value("${spring.mail.manufacturer.username}")
    private String manufacturerEmail;
    @Value("${spring.mail.client.username}")
    private String clientEmail;
    
    
    

    public EmailsService(
        @Qualifier("primarySender") JavaMailSender primarySender,
        @Qualifier("secondarySender") JavaMailSender secondarySender,
        @Qualifier("brokerSender") JavaMailSender brokerJavaMailSender,
        @Qualifier("buyerSender") JavaMailSender buyerJavaMailSender,
        @Qualifier("manufacturerSender") JavaMailSender manufacturerJavaMailSender,
        @Qualifier("clientsSender") JavaMailSender clientsJavaMailSender){
    	
        this.primaryJavaMailSender = primarySender;
        this.secondaryJavaMailSender = secondarySender;
        this.brokerJavaMailSender = brokerJavaMailSender;
        this.buyerJavaMailSender = buyerJavaMailSender;
        this.manufacturerJavaMailSender = manufacturerJavaMailSender;
        this.clientsJavaMailSender = clientsJavaMailSender;
    }



	public ResponseEntity<EmailResponse> sendEmailPrimary(EmailRequest emailRequest) {
		
		String[] toArray = new String[1];
        //String[] strArray = new String[5];//declaration with size
		
		toArray[0] = emailRequest.getTo();
		List<String> attachmens = new ArrayList<>();
		
		EmailResponse emailResponse = new EmailResponse();
		emailResponse.setTo(toArray);
		emailResponse.setFrom(primaryEmail);
		emailResponse.setSentDate(new Date().toString());
		emailResponse.setSubject(emailRequest.getSubject());
		emailResponse.setMessage(emailRequest.getMessage());
		emailResponse.setStatus(HttpStatus.OK);
		emailResponse.setAttachments(attachmens);
		
		
		//SimpleMailMessage
		mailMessage.setSentDate(new Date());
		mailMessage.setTo(emailRequest.getTo());
		mailMessage.setFrom(primaryEmail);
		mailMessage.setSubject(emailRequest.getSubject());
		mailMessage.setText(emailRequest.getMessage());
		//JavaMailSender
		primaryJavaMailSender.send(mailMessage);// sending email
		        
	    System.out.println("Email Sent Successfully using primaryJavaMailSender");
	      
		return  new ResponseEntity<>(emailResponse, HttpStatus.OK);
	}



	public ResponseEntity<EmailResponse> sendEmailSecondary(EmailRequest emailRequest) {
		
		return null;
	}



	public ResponseEntity<EmailResponse> sendEmailBroker(EmailRequest emailRequest) {
		
		return null;
	}



	public ResponseEntity<EmailResponse> sendEmailBuyer(EmailRequest emailRequest) {
		
		return null;
	}



	public ResponseEntity<EmailResponse> sendEmailManufacturer(EmailRequest emailRequest) {
		
		return null;
	}



	public ResponseEntity<EmailResponse> sendEmailClient(EmailRequest emailRequest) {
		
		String[] toArray = new String[1];
        //String[] strArray = new String[5];//declaration with size
		
		toArray[0] = emailRequest.getTo();
		List<String> attachmens = new ArrayList<>();
		
		EmailResponse emailResponse = new EmailResponse();
		emailResponse.setTo(toArray);
		emailResponse.setFrom(clientEmail);
		emailResponse.setSentDate(new Date().toString());
		emailResponse.setSubject(emailRequest.getSubject());
		emailResponse.setMessage(emailRequest.getMessage());
		emailResponse.setStatus(HttpStatus.OK);
		emailResponse.setAttachments(attachmens);
		
		//---------------------email with attachment and html content--------------------
		
		  MimeMessagePreparator preparator = mimeMessage -> {
		  
		  MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,"UTF-8"); 
		  message.setTo(new InternetAddress(emailRequest.getTo()));
		  message.setFrom(new InternetAddress(clientEmail)); 
		  message.setSubject(emailRequest.getSubject());
		  message.setText(emailRequest.getMessage());//ok
		  
		  //message.setText("<b>Dear friend</b>,<br><i>Please find the book attached.</i> ", true);//ok
		  
		  
		  FileSystemResource file = new FileSystemResource(new File("src\\main\\resources\\static\\image\\Journal-1.png"));
		 // message.addAttachment("logo.jpg", file);// ok
		  message.addAttachment("attachment :" + file.getFilename(), file);
		  };
		  try {
			    	
			  clientsJavaMailSender.send(preparator);
			    	
			  System.out.println("Message sent using clientsJavaMailSender");
		  }catch (MailException ex) {
			        
			   System.err.println(ex.getMessage());
			   return new ResponseEntity<>(emailResponse, HttpStatus.NO_CONTENT);
			}
		//-----------------------------------------
		
		return new ResponseEntity<>(emailResponse, HttpStatus.OK);
	}

}

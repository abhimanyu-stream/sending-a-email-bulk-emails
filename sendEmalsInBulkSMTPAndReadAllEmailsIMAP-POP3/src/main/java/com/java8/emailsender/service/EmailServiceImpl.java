package com.java8.emailsender.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.java8.emailsender.dto.EmailListRequest;
import com.java8.emailsender.dto.EmailListResponse;
import com.java8.emailsender.dto.EmailRequest;
import com.java8.emailsender.dto.EmailResponse;


@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	@Resource
    private JavaMailSender javaMailSender;
     
    @Autowired
    private SimpleMailMessage mailMessage;
    
    @Value("${sender.email}")
    private String sendEmailID;

	private MimeMessagePreparator preparator = null;
    
	@SuppressWarnings("null")
	public ResponseEntity<EmailResponse> sendSimpleEmail(EmailRequest emailRequest) {
		
		
		//---------------
		 	//final MimeMessage mail = javaMailSender.createMimeMessage();
		   // final MimeMessageHelper helper = new MimeMessageHelper( mail, true );
		    //helper.setTo( email );
		   // helper.setSubject( "Notification" );
		   // helper.setText( "text/html", payload );
		  //javaMailSender.send( mail );
		
		//---------------------

		String[] toArray = new String[1];
        //String[] strArray = new String[5];//declaration with size
		
		toArray[0] = emailRequest.getTo();
		List<String> attachmens = new ArrayList<>();
		
		
		
		EmailResponse emailResponse = new EmailResponse();
		emailResponse.setTo(toArray);
		emailResponse.setFrom(sendEmailID);
		emailResponse.setSentDate(new Date().toString());
		emailResponse.setSubject(emailRequest.getSubject());
		emailResponse.setMessage(emailRequest.getMessage());
		emailResponse.setStatus(HttpStatus.OK);
		emailResponse.setAttachments(attachmens);
		
		//SimpleMailMessage
		mailMessage.setSentDate(new Date());
		mailMessage.setTo(emailRequest.getTo());
		mailMessage.setFrom(sendEmailID);
		mailMessage.setSubject(emailRequest.getSubject());
		mailMessage.setText(emailRequest.getMessage());
		//JavaMailSender
		javaMailSender.send(mailMessage);// sending email
		System.out.println("Message sent by javaMailSender");
		return new ResponseEntity<>(emailResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EmailResponse> sendSimpleEmailAttachments(EmailRequest emailRequest) {

		String[] toArray = new String[1];
        //String[] strArray = new String[5];//declaration with size
		
		toArray[0] = emailRequest.getTo();
		List<String> attachmens = new ArrayList<>();
		
		EmailResponse emailResponse = new EmailResponse();
		emailResponse.setTo(toArray);
		emailResponse.setFrom(sendEmailID);
		emailResponse.setSentDate(new Date().toString());
		emailResponse.setSubject(emailRequest.getSubject());
		emailResponse.setMessage(emailRequest.getMessage());
		emailResponse.setStatus(HttpStatus.OK);
		emailResponse.setAttachments(attachmens);
		
		//SimpleMailMessage
		//mailMessage.setSentDate(new Date());
		//mailMessage.setTo(emailRequest.getTo());
		//mailMessage.setFrom(sendEmailID);
		//mailMessage.setSubject(emailRequest.getSubject());
		//mailMessage.setText(emailRequest.getMessage());
		//JavaMailSender
		//javaMailSender.send(mailMessage);// sending email
        
		
		
		//-----------------------------------------
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setTo(new InternetAddress(emailRequest.getTo()));
				message.setFrom(new InternetAddress(sendEmailID));
				message.setSubject(emailRequest.getSubject());
				message.setText(emailRequest.getMessage());// ok

				// message.setText("<b>Dear friend</b>,<br><i>Please find the book attached.</i>
				// ", true);//ok

				FileSystemResource file = new FileSystemResource(new File("src\\main\\resources\\static\\image\\Journal-1.png"));
				//message.addAttachment("attachments", file);//ok
				message.addAttachment("attachment :" + file.getFilename(), file);
			}

		};
	    try {
	    	javaMailSender.send(preparator);
	    	System.out.println("Message sent by javaMailSender");
	    }
	    catch (MailException ex) {
	        
	        System.err.println(ex.getMessage());
	    }
		//-----------------------------------------
		
	    return new ResponseEntity<>(emailResponse, HttpStatus.OK);
	}

	
	
	@Override
	public ResponseEntity<EmailListResponse> sendBulkEmailAttachments(EmailListRequest emailListRequest) {
		
		int count = 1;
		
		//String[] strEmailArray = new String[emailRequestDTO.getToEmailList().size()];//declaration with size
				//strEmailArray = emailRequestDTO.getToEmailList().stream().toArray(String[]::new);
				
				//String[] strEmailArray = new String[generateEmailList().size()];//declaration with size
				//strEmailArray = generateEmailList().stream().toArray(String[]::new);
				
				EmailListResponse emailListResponse = new EmailListResponse();
				emailListResponse.setEmailTOList(generateEmailList());
				emailListResponse.setFrom(sendEmailID);
				emailListResponse.setMessage(emailListRequest.getMessage());//message body contents
				emailListResponse.setSubject(emailListRequest.getSubject());
				emailListResponse.setStatus(HttpStatus.OK);
				emailListResponse.setAttachments(emailListRequest.getAttachments());
				emailListResponse.setSentDate(new Date().toString());
				
				long startTime = System.currentTimeMillis();
			    //Sending Bulk Emails
				for(String email:generateEmailList()) {
					
					//SimpleMailMessage
					mailMessage.setTo(email);
					mailMessage.setSubject(emailListRequest.getSubject());
					mailMessage.setText(emailListRequest.getMessage());
					//add attachments is remaining
					//JavaMailSender
					javaMailSender.send(mailMessage);// sending email
					System.out.println("Email sent number  : " + count);
					count++;
					
				}
				long endtime = System.currentTimeMillis();
				System.out.println("Time taken for Execution is : " + (endtime-startTime) + "ms");
				
		
		
		return new ResponseEntity<>(emailListResponse, HttpStatus.OK);
	}

	
	//Time taken for Execution is : 470166ms
	private List<String> generateEmailList(){
		List<String> emailList = new ArrayList<>();
		
		for(int i = 0; i < 100; i++) {
			emailList.add("koel.kunj@gmail.com");
			
		}
		return emailList;
	}
	
	 
	  

}

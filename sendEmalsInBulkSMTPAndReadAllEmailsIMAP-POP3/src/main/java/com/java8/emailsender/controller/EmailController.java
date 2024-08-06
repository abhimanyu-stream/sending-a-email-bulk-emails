package com.java8.emailsender.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java8.emailsender.dto.EmailListRequest;
import com.java8.emailsender.dto.EmailListResponse;
import com.java8.emailsender.dto.EmailRequest;
import com.java8.emailsender.dto.EmailResponse;
import com.java8.emailsender.service.EmailServiceImpl;
import com.java8.emailsender.service.ReadEmailsIMAP;
import com.java8.emailsender.service.ReadEmailsPOP3;

@RestController
public class EmailController {
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	@Autowired
	private ReadEmailsIMAP readEmailsIMAP;
	
	@Autowired
	private ReadEmailsPOP3 readEmailsPOP3;
	
	
	//SMTP
	@PostMapping("/sendsimpleemail")
	ResponseEntity<EmailResponse> sendSimpleEmail(@RequestBody EmailRequest emailRequest) {
		
		return  emailServiceImpl.sendSimpleEmail(emailRequest);
	}
	//SMTP
	@PostMapping("/sendsimpleemailattach")
	ResponseEntity<EmailResponse> sendSimpleEmailAttachments(@RequestBody EmailRequest emailRequest) {
		
		return emailServiceImpl.sendSimpleEmailAttachments(emailRequest);
		
	}
	//SMTP
	@PostMapping(path = "/sendbulkemailattach", consumes = { MediaType.APPLICATION_JSON_VALUE})
	ResponseEntity<EmailListResponse> sendBulkEmailAttachments(@RequestBody EmailListRequest emailListRequest) {
		
		return emailServiceImpl.sendBulkEmailAttachments(emailListRequest);
		
	}
	
	
	
	
	//IMAP
	@GetMapping("/reademailsimap")
	void readEmailIMAP() throws MessagingException, IOException {
		
		readEmailsIMAP.readEmailsUsingIMAP();
	}
	//POP3
	@GetMapping("/reademailspop3")
	void readEmailspop3() throws MessagingException, IOException {
		
		readEmailsPOP3.readEmailsUsingPOP3();
	}
	

}

package com.java8.emailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java8.emailsender.dto.EmailRequest;
import com.java8.emailsender.dto.EmailResponse;

import com.java8.emailsender.service.EmailsService;


@RestController
public class EmailController {
	
	@Autowired
	private EmailsService emailsService;
	
	

	
	@PostMapping("/sendemailprimary")
	ResponseEntity<EmailResponse> sendEmailPrimary(@RequestBody EmailRequest emailRequest) throws Exception {
		
		return emailsService.sendEmailPrimary(emailRequest);
		
	}
	
	@PostMapping("/sendemailsecondary")
	ResponseEntity<EmailResponse> sendEmailSecondary(@RequestBody EmailRequest emailRequest) throws Exception {
		
		return emailsService.sendEmailSecondary(emailRequest);
		
	}
	@PostMapping("/sendemailbroker")
	ResponseEntity<EmailResponse> sendEmailBroker(@RequestBody EmailRequest emailRequest) throws Exception {
		
		return emailsService.sendEmailBroker(emailRequest);
		
	}
	@PostMapping("/sendemailbuyer")
	ResponseEntity<EmailResponse> sendEmailBuyer(@RequestBody EmailRequest emailRequest) throws Exception {
		
		return emailsService.sendEmailBuyer(emailRequest);
		
	}
	@PostMapping("/sendemailmanufacturer")
	ResponseEntity<EmailResponse> sendEmailManufacturer(@RequestBody EmailRequest emailRequest) throws Exception {
		
		return emailsService.sendEmailManufacturer(emailRequest);
		
	}
	@PostMapping("/sendemailclient")
	ResponseEntity<EmailResponse> sendEmailClient(@RequestBody EmailRequest emailRequest) throws Exception {
		
		return emailsService.sendEmailClient(emailRequest);
		
	}
	
	
	
}

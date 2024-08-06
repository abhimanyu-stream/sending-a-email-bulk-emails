package com.java8.emailsender.service;

import org.springframework.http.ResponseEntity;

import com.java8.emailsender.dto.EmailListRequest;
import com.java8.emailsender.dto.EmailListResponse;
import com.java8.emailsender.dto.EmailRequest;
import com.java8.emailsender.dto.EmailResponse;

public interface EmailService {
	
	public ResponseEntity<EmailResponse> sendSimpleEmail(EmailRequest emailRequest);
	public ResponseEntity<EmailResponse> sendSimpleEmailAttachments(EmailRequest emailRequest);
	public ResponseEntity<EmailListResponse> sendBulkEmailAttachments(EmailListRequest emailListRequest);
	
	

}

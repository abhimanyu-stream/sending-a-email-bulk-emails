package com.java8.emailsender.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class EmailListResponse {
	
	
	private List<String> emailTOList;
	private String message;
	private String subject;
	private String from;
	private String sentDate;
	private HttpStatus status;
	private List<String> attachments;
	

	public void setEmailTOList(List<String> emailList) {
		
		this.emailTOList = emailList;
		
	}
	

}

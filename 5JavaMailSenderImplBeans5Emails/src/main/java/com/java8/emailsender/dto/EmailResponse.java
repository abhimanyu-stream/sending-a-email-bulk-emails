package com.java8.emailsender.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmailResponse implements Serializable{

	private static final long serialVersionUID = 5012493757700803190L;
	
	
	private String[] to;
	private List<String> toEmailList;
	private String message;
	private String subject;
	private String from;
	private String sentDate;
	private HttpStatus status;
	private List<String> attachments;


	
	
	
	
	
	

}

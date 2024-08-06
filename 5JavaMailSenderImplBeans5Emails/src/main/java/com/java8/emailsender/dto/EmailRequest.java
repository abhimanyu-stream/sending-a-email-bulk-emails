package com.java8.emailsender.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest implements Serializable{
	
	private static final long serialVersionUID = 4601018343976437312L;
	private String to;
	private List<String> toEmailList;
	private String message;
	private List<String> attachments;
	private String subject;
	

}

package com.java8.emailsender.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class EmailListRequest implements Serializable{
	
	private static final long serialVersionUID = 1724695635431040765L;
	private String message;
	private String subject;
	private List<String> attachments;
	
	//NOTE:- SENDER EMAILID IS READ FROM application.properties file, It can be mentioned here too

}

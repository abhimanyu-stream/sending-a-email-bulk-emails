package com.java8.emailsender.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
@ConfigurationProperties

public class EmailConfiguration {
	
	
	@Bean
	public SimpleMailMessage mailMessageTemplate() {
		
		SimpleMailMessage message = new SimpleMailMessage();
		return message;
		
	}

	
	
	
	

}

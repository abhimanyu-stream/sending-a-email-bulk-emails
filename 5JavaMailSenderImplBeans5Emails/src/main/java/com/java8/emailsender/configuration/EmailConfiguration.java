package com.java8.emailsender.configuration;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
@ConfigurationProperties
public class EmailConfiguration {
	
	@Autowired
    Environment env;

	
	@Bean("primarySender")
	@ConfigurationProperties(prefix = "spring.mail.primary")
	public JavaMailSender primarySender() {
	    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
	    javaMailSender.setPassword(env.getProperty("spring.mail.primary.password"));//env.getProperty("spring.mail.properties.mail.smtp.socketFactory.class")
	    javaMailSender.setUsername(env.getProperty("spring.mail.primary.username"));
	   
	    javaMailSender.setProtocol(env.getProperty("spring.mail.protocol"));
	    
	    Properties props = new Properties();
	    props.put("mail.smtp.host", env.getProperty("spring.mail.primary.host")); //SMTP Host
		props.put("mail.smtp.port", env.getProperty("spring.mail.primary.port")); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		props.put("mail.smtp.timeout", env.getProperty("spring.mail.properties.mail.smtp.timeout"));
		props.put("mail.smtp.socketFactory.port",env.getProperty("spring.mail.properties.mail.smtp.socketFactory.port"));
		props.put("mail.smtp.from", env.getProperty("spring.mail.primary.username"));
		props.put("mail.smtp.socketFactory.fallback", false);
		//props.put("mail.smtp.socketFactory.class", env.getProperty("spring.mail.properties.mail.smtp.socketFactory.class"));
		
		
		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
		//override the getPasswordAuthentication method
		protected PasswordAuthentication getPasswordAuthentication() {
				 return new PasswordAuthentication("spring.mail.primary.username", "spring.mail.primary.password");
			}
		};
		Session session = Session.getInstance(props, auth);
		javaMailSender.setSession(session);
	    javaMailSender.setJavaMailProperties(props);
	    //return javaMailSenderWithProperties(javaMailSender);
	    return javaMailSender;
	}

	@Bean("secondarySender")
	@ConfigurationProperties(prefix = "spring.mail.secondary")
	public JavaMailSender secondarySender() {
	    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
	    javaMailSender.setPassword("spring.mail.secondary.password");
	    javaMailSender.setUsername("spring.mail.secondary.username");
	   
	    javaMailSender.setProtocol("spring.mail.protocol");
	    
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "spring.mail.secondary.host"); //SMTP Host
		props.put("mail.smtp.port", "spring.mail.secondary.port"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		props.put("mail.smtp.timeout", "spring.mail.properties.mail.smtp.timeout");
		props.put("mail.smtp.socketFactory.port", "spring.mail.properties.mail.smtp.socketFactory.port");
		props.put("mail.smtp.from", "spring.mail.secondary.username");
		props.put("mail.smtp.socketFactory.fallback", false);
		props.put("mail.smtp.socketFactory.class", "spring.mail.properties.mail.smtp.socketFactory.class");
		
		
		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
		//override the getPasswordAuthentication method
		protected PasswordAuthentication getPasswordAuthentication() {
				 return new PasswordAuthentication("spring.mail.secondary.username", "spring.mail.secondary.password");
			}
		};
		Session session = Session.getInstance(props, auth);
		javaMailSender.setSession(session);
	    javaMailSender.setJavaMailProperties(props);
	    //return javaMailSenderWithProperties(javaMailSender);
	    return javaMailSender;
	}
	@Bean("brokerSender")
	@ConfigurationProperties(prefix = "spring.mail.broker")
	public JavaMailSender brokerSender() {
	    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
	    javaMailSender.setPassword("spring.mail.broker.password");
	    javaMailSender.setUsername("spring.mail.broker.username");
	   
	    javaMailSender.setProtocol("spring.mail.protocol");
	    
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "spring.mail.broker.host"); //SMTP Host
		props.put("mail.smtp.port", "spring.mail.broker.port"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		props.put("mail.smtp.timeout", "spring.mail.properties.mail.smtp.timeout");
		props.put("mail.smtp.socketFactory.port", "spring.mail.properties.mail.smtp.socketFactory.port");
		props.put("mail.smtp.from", "spring.mail.broker.username");
		props.put("mail.smtp.socketFactory.fallback", false);
		props.put("mail.smtp.socketFactory.class", "spring.mail.properties.mail.smtp.socketFactory.class");
		
		
		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
		//override the getPasswordAuthentication method
		protected PasswordAuthentication getPasswordAuthentication() {
				 return new PasswordAuthentication("spring.mail.broker.username", "spring.mail.broker.password");
			}
		};
		Session session = Session.getInstance(props, auth);
		javaMailSender.setSession(session);
	    javaMailSender.setJavaMailProperties(props);
	    //return javaMailSenderWithProperties(javaMailSender);
	    return javaMailSender;
	}
	@Bean("buyerSender")
	@ConfigurationProperties(prefix = "spring.mail.buyer")
	public JavaMailSender buyerSender() {
	    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
	    javaMailSender.setPassword("spring.mail.buyer.password");
	    javaMailSender.setUsername("spring.mail.buyer.username");
	   
	    javaMailSender.setProtocol("spring.mail.protocol");
	    
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "spring.mail.buyer.host"); //SMTP Host
		props.put("mail.smtp.port", "spring.mail.buyer.port"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		props.put("mail.smtp.timeout", "spring.mail.properties.mail.smtp.timeout");
		props.put("mail.smtp.socketFactory.port", "spring.mail.properties.mail.smtp.socketFactory.port");
		props.put("mail.smtp.from", "spring.mail.buyer.username");
		props.put("mail.smtp.socketFactory.fallback", false);
		props.put("mail.smtp.socketFactory.class", "spring.mail.properties.mail.smtp.socketFactory.class");
		
		
		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
		//override the getPasswordAuthentication method
		protected PasswordAuthentication getPasswordAuthentication() {
				 return new PasswordAuthentication("spring.mail.buyer.username", "spring.mail.buyer.password");
			}
		};
		Session session = Session.getInstance(props, auth);
		javaMailSender.setSession(session);
	    javaMailSender.setJavaMailProperties(props);
	    //return javaMailSenderWithProperties(javaMailSender);
	    return javaMailSender;
	}
	@Bean("manufacturerSender")
	@ConfigurationProperties(prefix = "spring.mail.manufacturer")
	public JavaMailSender manufacturerSender() {
	    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
	    javaMailSender.setPassword("spring.mail.manufacturer.password");
	    javaMailSender.setUsername("spring.mail.manufacturer.username");
	   
	    javaMailSender.setProtocol("spring.mail.protocol");
	    
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "spring.mail.manufacturer.host"); //SMTP Host
		props.put("mail.smtp.port", "spring.mail.manufacturer.port"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		props.put("mail.smtp.timeout", "spring.mail.properties.mail.smtp.timeout");
		props.put("mail.smtp.socketFactory.port", "spring.mail.properties.mail.smtp.socketFactory.port");
		props.put("mail.smtp.from", "spring.mail.manufacturer.username");
		props.put("mail.smtp.socketFactory.fallback", false);
		props.put("mail.smtp.socketFactory.class", "spring.mail.properties.mail.smtp.socketFactory.class");
		
		
		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
		//override the getPasswordAuthentication method
		protected PasswordAuthentication getPasswordAuthentication() {
				 return new PasswordAuthentication("spring.mail.manufacturer.username", "spring.mail.manufacturer.password");
			}
		};
		Session session = Session.getInstance(props, auth);
		javaMailSender.setSession(session);
	    javaMailSender.setJavaMailProperties(props);
	    //return javaMailSenderWithProperties(javaMailSender);
	    return javaMailSender;
	}
	
	@Bean("clientsSender")
	@ConfigurationProperties(prefix = "spring.mail.client")
	public JavaMailSender clientsSender() {
	    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
	    
	    javaMailSender.setPassword(env.getProperty("spring.mail.client.password"));
	    javaMailSender.setUsername(env.getProperty("spring.mail.client.username"));
	   
	    javaMailSender.setProtocol(env.getProperty("spring.mail.protocol"));
	    
	    Properties props = new Properties();
	    props.put("mail.smtp.host", env.getProperty("spring.mail.client.host")); //SMTP Host
		props.put("mail.smtp.port", env.getProperty("spring.mail.client.port")); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		props.put("mail.smtp.timeout", env.getProperty("spring.mail.properties.mail.smtp.timeout"));
		props.put("mail.smtp.socketFactory.port", env.getProperty("spring.mail.properties.mail.smtp.socketFactory.port"));
		props.put("mail.smtp.from", env.getProperty("spring.mail.client.username"));
		props.put("mail.smtp.socketFactory.fallback", false);
		//props.put("mail.smtp.socketFactory.class", env.getProperty("spring.mail.properties.mail.smtp.socketFactory.class"));
		
		
		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
		//override the getPasswordAuthentication method
		protected PasswordAuthentication getPasswordAuthentication() {
				 return new PasswordAuthentication("spring.mail.client.username", "spring.mail.client.password");
			}
		};
		Session session = Session.getInstance(props, auth);
		javaMailSender.setSession(session);
	    javaMailSender.setJavaMailProperties(props);
	    //return javaMailSenderWithProperties(javaMailSender);
	    return javaMailSender;
	}
	 
	


	@Bean
    public SimpleMailMessage createSimpleMailMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        
        return message;
    }
	
	
	

}

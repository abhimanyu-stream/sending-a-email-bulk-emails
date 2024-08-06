package com.receive.emails;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

// IMAP configuration
// Retrieve Emails + Time Calculation for fetching each email + Time Calculation for fetching all emails separately + 
// Print TO, CC, BCC, Subject, Body Content, Find and download attachments and also print files name which are in the attachments
public class ReceiveMails {
	
	// Note:- If you want put new email and password then accordingly you have change settings in gmail, like enable IMAP + enable less secure apps + etc
	
	public static final String USERNAME = "youremail@gmail.com";
	public static final String PASSWORD = "password";
	static int x = 0;
	static String saveDirectory = "C://Temp";
	static long start = 0;
	static long end = 0;
	static long timetaken =0;
	static long starttotal = 0;
	static long endtotal = 0;
	static long timetakentotal =0;
	
	
	public static void main(String[] args) throws Exception {
		
		// 1. Setup properties for the mail session.
		Properties props = new Properties();
		props.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.imap.socketFactory.fallback", "false");
		props.put("mail.imap.socketFactory.port", "993");
		props.put("mail.imap.port", "993");
		props.put("mail.imap.host", "imap.gmail.com");
		props.put("mail.imap.user", ReceiveMails.USERNAME);
		props.put("mail.store.protocol", "imap");
		props.put("mail.imap.ssl.protocols", "TLSv1.2");

		// 2. Creates a javax.mail.Authenticator object.
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ReceiveMails.USERNAME, ReceiveMails.PASSWORD);
			}
		};

		// 3. Creating mail session.
		Session session = Session.getDefaultInstance(props, auth);

		// 4. Get the imap store provider and connect to the store.
		Store store = session.getStore("imap");
		store.connect("imap.gmail.com", ReceiveMails.USERNAME, ReceiveMails.PASSWORD);

		// 5. Get folder and open the INBOX folder in the store.
		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);

		// 6. Retrieve the messages from the folder.
		Message[] messages = inbox.getMessages();
		System.out.println("=========================================================================================\n");

		// retrieve the messages from the folder in an array and print it

		System.out.println("messages.length---" + messages.length);
		starttotal = System.nanoTime();
		for (int i = 0, n = messages.length; i < n; i++) {
			// get the start time
		    start = System.nanoTime();
			Message message = messages[i];
			System.out.println("---------------------------------");
			System.out.println("Email Number " + (i + 1));
			System.out.println("Subject: " + message.getSubject());
			System.out.println("From: " + message.getFrom()[0]);
			 

			if (!message.getFlags().contains(Flags.Flag.SEEN)) {
				Address[] fromAddresses = message.getFrom();
				System.out.println("...................");
				System.out.println("From: " + fromAddresses[0].toString());
				System.out.println("To: " + parseAddresses(message.getRecipients(RecipientType.TO)));
				System.out.println("BCC: " + parseAddresses(message.getRecipients(RecipientType.BCC)));
				System.out.println("CC: " + parseAddresses(message.getRecipients(RecipientType.CC)));
				System.out.println("Subject: " + message.getSubject());
				System.out.println("Sent Date:" + message.getSentDate().toString());
				System.out.println("Received Date:" + message.getReceivedDate().toString());
				
				//1. REMAINING  now try to get content of email
				Object output = getTextFromMessage(message);
				System.out.println(output);
				
				//2.  REMAINING now try to get attachments
				List<String> attachments = downloadAttachments(message);
				for(String s:attachments) {
					System.out.println(s.toString());
				}
				
			}
			 end = System.nanoTime();
			 timetaken = end -  start;
			 System.out.println("Email Number " + (i + 1) + "  Time Taken To be Fetched" + timetaken);

		}
		 endtotal = System.nanoTime();
		 timetakentotal =  endtotal - starttotal;
		 System.out.println("Total Time taken to fetch all : "+ messages.length + " emails " + timetakentotal);
		//convert to seconds 
		 double totalSeconds = timetakentotal/1000000000;// Converting into Seconds from Nanoseconds
		 System.out.println(totalSeconds  + " seconds");		 
		
		 // 7. Close folder and close store.
		inbox.close(false);
		store.close();
	}

	// Parsing Addresses
	private static String parseAddresses(Address[] address) {

		String listOfAddress = "";
		if ((address == null) || (address.length < 1))
			return null;
		if (!(address[0] instanceof InternetAddress))
			return null;

		for (int i = 0; i < address.length; i++) {
			InternetAddress internetAddress = (InternetAddress) address[0];
			listOfAddress += internetAddress.getAddress() + ",";
		}
		return listOfAddress;
	}
	
	// Reading Body Content
	private static Object getTextFromMessage(Message message) throws MessagingException, IOException {
	    Object result = "";
	    if (message.isMimeType("text/plain")) {
	    	//message.getContent() != null && !((Properties) message.getContent()).isEmpty() && !message.getContent().equals(""))
	    	if(message != null) {
	    		result = message;//.toString();
	    	}
	        
	    } else if (message.isMimeType("multipart/*")) {
	    	 MimeMultipart mimeMultipart = null;
	    	if(message.getContent() != null ) {
	    		mimeMultipart = (MimeMultipart) message.getContent();
	    	}
	       
	        result = getTextFromMimeMultipart(mimeMultipart);
	    }
	    return result;
	}

	// Reading Body Content
	private static String getTextFromMimeMultipart(
	        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
	}
	
	// Downloading attachments and returning its names as list object
	public static List<String> downloadAttachments(Message message) throws IOException, MessagingException, NullPointerException {
	    List<String> downloadedAttachments = new ArrayList<String>();
	    Multipart multiPart = (Multipart) message.getContent();//raising NullPointerException
	    int numberOfParts = multiPart.getCount();
	    for (int partCount = 0; partCount < numberOfParts; partCount++) {
	        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
	        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
	            String file = part.getFileName();
	            try {
					part.saveFile(saveDirectory + File.separator + part.getFileName());
				} catch (IOException e) {
					// 
					e.printStackTrace();
				} catch (MessagingException e) {
					// 
					e.printStackTrace();
				}
	            downloadedAttachments.add(file);
	        }
	    }
	    return downloadedAttachments;
	}  
}
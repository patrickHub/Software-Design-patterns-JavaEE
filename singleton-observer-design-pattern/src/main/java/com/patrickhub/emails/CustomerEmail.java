/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.emails;

import com.patrickhub.beans.Customer;
import com.patrickhub.events.CustomerEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author PatrickHub
 */
public class CustomerEmail {
    
    private Properties properties;
    private Session session;
    private MimeMessage message;
    private Customer customer;
    
    
    public void sendMail(@Observes @CustomerEvent(CustomerEvent.Type.ADD) Customer customer){
        this.customer = customer;
        setGmailServerProperties();
        prepareEmail();
    }
    
    /**
     * setting up configuration for the email connection to the google SMTP
     * server using TLS.
     * 
     */
    private void setGmailServerProperties(){
        // get Gmail STMP port
        String port = "587";
        
        // get system properties
        properties = System.getProperties();
        
        // set email properties
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.hos", "true");
        
        
    }
     /**
     * setting up mail in order to send it to new customer
     * server using TLS.
     * 
     */
    private void prepareEmail(){
        
        setGmailServerProperties();
        // establish a session with required gmail credentials
        session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("blablycars@gmail.com", "Blably29");
            }
        });
        
        // create message object in order to set email content
        message = new MimeMessage(session);
        
        try {
            // prepared recipient email
            InternetAddress address = new InternetAddress(customer.getEmail());
            
            // set recipient in message object
            message.setRecipient(Message.RecipientType.TO, address);
            
            // set mail subject
            message.setSubject("Welcome Message");
            
            // prepare a welcome message
            String boby = "Dear " + customer.getFirstName()+ " " + customer.getLastName()
                                     + ", \n\n We are so happy to welcome you in our shop."
                                    + "\n\n\n\n\n Your Shop Team.";
            // set mail content
            message.setText(boby);
            
            // set heander
            message.setHeader("XPriority", "1");
            
            // then send email to new customer
            Transport.send(message);
            Logger.getLogger(CustomerEmail.class.getName()).info("Sending customer welcome email successfully");
        } catch (AddressException ex) {
            Logger.getLogger(CustomerEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(CustomerEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}

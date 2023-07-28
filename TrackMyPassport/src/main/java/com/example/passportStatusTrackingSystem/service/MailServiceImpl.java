package com.example.passportStatusTrackingSystem.service;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.passportStatusTrackingSystem.model.Mail;

@Service("mailService")
public class MailServiceImpl implements MailService {

	 @Autowired
	 JavaMailSender mailSender;
	 
	  public int sendEmail(Mail mail,long applicantId) {
	        MimeMessage mimeMessage = mailSender.createMimeMessage();
	        int flag=0;
	 
	        try {
	        	FileSystemResource file = new FileSystemResource(new File("C:\\Deveopment_avecto\\"+applicantId+"simple.pdf"));
	            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
	 
	            mimeMessageHelper.setSubject(mail.getMailSubject());
	            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "passportSystem.com"));
	            mimeMessageHelper.setTo(mail.getMailTo());
	            mimeMessageHelper.setText(mail.getMailContent());
	            mimeMessageHelper.addAttachment("E-Passport.pdf",file);
	            
	            mailSender.send(mimeMessageHelper.getMimeMessage());
	            flag=1;
	 
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        
	        return flag;
	    }
	 
	    public int sendEmail(Mail mail) {
	        MimeMessage mimeMessage = mailSender.createMimeMessage();
	        int flag=0;
	 
	        try {
	 
	            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
	 
	            mimeMessageHelper.setSubject(mail.getMailSubject());
	            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "passportSystem.com"));
	            mimeMessageHelper.setTo(mail.getMailTo());
	            mimeMessageHelper.setText(mail.getMailContent());
	 
	            mailSender.send(mimeMessageHelper.getMimeMessage());
	            flag=1;
	 
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        
	        return flag;
	    }
}

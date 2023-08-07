/**
This service class provides implementations for sending emails using the Spring Boot JavaMailSender.
*/

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

	/**
	 * Sends an email with an attachment (PDF) to the provided recipient email
	 * address.
	 *
	 * @param mail        The Mail object containing email details.
	 * @param applicantId The ID of the applicant for whom the email is being sent.
	 * @return 1 if the email is sent successfully, otherwise 0.
	 */
	public int sendEmail(Mail mail, long applicantId) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		int flag = 0;

		try {
			FileSystemResource file = new FileSystemResource(
					new File("C:\\Deveopment_avecto\\" + applicantId + "simple.pdf"));
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setSubject(mail.getMailSubject());
			mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "passportSystem.com"));
			mimeMessageHelper.setTo(mail.getMailTo());
			mimeMessageHelper.setText(mail.getMailContent());
			mimeMessageHelper.addAttachment("E-Passport.pdf", file);

			mailSender.send(mimeMessageHelper.getMimeMessage());
			flag = 1;

		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * Sends a simple email without an attachment to the provided recipient email
	 * address.
	 *
	 * @param mail The Mail object containing email details.
	 * @return 1 if the email is sent successfully, otherwise 0.
	 */
	public int sendEmail(Mail mail) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		int flag = 0;

		try {

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setSubject(mail.getMailSubject());
			mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "passportSystem.com"));
			mimeMessageHelper.setTo(mail.getMailTo());
			mimeMessageHelper.setText(mail.getMailContent());

			mailSender.send(mimeMessageHelper.getMimeMessage());
			flag = 1;

		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return flag;
	}
}

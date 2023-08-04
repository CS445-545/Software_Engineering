//https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/  used for reference
// This interface defines methods for sending emails in the passport status tracking system.

package com.example.passportStatusTrackingSystem.service;

import com.example.passportStatusTrackingSystem.model.Mail;

public interface MailService {
	// Sends an email using the provided Mail object and returns an integer status code.
    public int sendEmail(Mail mail);

	// Sends an email using the provided Mail object and applicant ID, and returns an integer status code.
    // The applicant ID is used to retrieve applicant-specific details for email content, if needed.
	public int sendEmail(Mail mail,long applicantId);
}

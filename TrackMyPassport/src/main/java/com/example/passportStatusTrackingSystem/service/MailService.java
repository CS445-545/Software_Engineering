package com.example.passportStatusTrackingSystem.service;

import com.example.passportStatusTrackingSystem.model.Mail;

public interface MailService {
	 public int sendEmail(Mail mail);
	 public int sendEmail(Mail mail,long applicantId);
}

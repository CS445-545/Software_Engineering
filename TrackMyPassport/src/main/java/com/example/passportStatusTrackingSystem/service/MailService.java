package com.example.passportStatusTrackingSystem.service;

import com.example.passportStatusTrackingSystem.model.mail;


public interface mailService {
	/**
	 * Sends an email using the provided Mail object and returns an integer status
	 * code.
	 *
	 * @param mail
	 *            The Mail object containing email details.
	 * @return 1 if the email is sent successfully, otherwise 0.
	 */
	public int sendEmail(mail mail);

	/**
	 * Sends an email using the provided Mail object and applicant ID, and returns
	 * an integer status code.
	 * The applicant ID is used to retrieve applicant-specific details for email
	 * content, if needed.
	 *
	 * @param mail
	 *            The Mail object containing email details.
	 * @param applicantId
	 *            The ID of the applicant for whom the email is being sent.
	 * @return 1 if the email is sent successfully, otherwise 0.
	 */
	public int sendEmail(mail mail, long applicantId);

}

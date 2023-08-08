package com.example.passportStatusTrackingSystem.model;

import java.util.Date;
import java.util.List;

/**
 * This class represents an email message used in the passport status tracking
 * system.
 * It is used to store the necessary details to construct an email, such as the
 * sender, recipients, subject, content, and attachments.
 * Reference: https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/
 */
public class Mail {
    // Properties representing email details

    /** The sender email address */
    private String mailFrom;

    /** The recipient email address */
    private String mailTo;

    /** The carbon copy (cc) email addresses */
    private String mailCc;

    /** The blind carbon copy (bcc) email addresses */
    private String mailBcc;

    /** The email subject */
    private String mailSubject;

    /** The email content */
    private String mailContent;

    /** The content type of the email */
    private String contentType;

    /** The list of attachments for the email */
    private List<Object> attachments;

    /**
     * Constructs a new Mail object with default content type as "text/plain".
     */
    public Mail() {
        contentType = "text/plain";
    }

    /**
     * Get the content type of the email.
     * 
     * @return The content type of the email.
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Set the content type of the email.
     * 
     * @param contentType
     *            The content type of the email.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Get the blind carbon copy (bcc) email addresses.
     * 
     * @return The blind carbon copy (bcc) email addresses.
     */
    public String getMailBcc() {
        return mailBcc;
    }

    /**
     * Set the blind carbon copy (bcc) email addresses.
     * 
     * @param mailBcc
     *            The blind carbon copy (bcc) email addresses.
     */
    public void setMailBcc(String mailBcc) {
        this.mailBcc = mailBcc;
    }

    /**
     * Get the carbon copy (cc) email addresses.
     * 
     * @return The carbon copy (cc) email addresses.
     */
    public String getMailCc() {
        return mailCc;
    }

    /**
     * Set the carbon copy (cc) email addresses.
     * 
     * @param mailCc
     *            The carbon copy (cc) email addresses.
     */
    public void setMailCc(String mailCc) {
        this.mailCc = mailCc;
    }

    /**
     * Get the sender email address.
     * 
     * @return The sender email address.
     */
    public String getMailFrom() {
        return mailFrom;
    }

    /**
     * Set the sender email address.
     * 
     * @param mailFrom
     *            The sender email address.
     */
    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    /**
     * Get the email subject.
     * 
     * @return The email subject.
     */
    public String getMailSubject() {
        return mailSubject;
    }

    /**
     * Set the email subject.
     * 
     * @param mailSubject
     *            The email subject.
     */
    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    /**
     * Get the recipient email address.
     * 
     * @return The recipient email address.
     */
    public String getMailTo() {
        return mailTo;
    }

    /**
     * Set the recipient email address.
     * 
     * @param mailTo
     *            The recipient email address.
     */
    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    /**
     * Get the email send date (current date and time).
     * 
     * @return The email send date (current date and time).
     */
    public Date getMailSendDate() {
        return new Date();
    }

    /**
     * Get the email content.
     * 
     * @return The email content.
     */
    public String getMailContent() {
        return mailContent;
    }

    /**
     * Set the email content.
     * 
     * @param mailContent
     *            The email content.
     */
    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    /**
     * Get the list of attachments for the email.
     * 
     * @return The list of attachments for the email.
     */
    public List<Object> getAttachments() {
        return attachments;
    }

    /**
     * Set the list of attachments for the email.
     * 
     * @param attachments
     *            The list of attachments for the email.
     */
    public void setAttachments(List<Object> attachments) {
        this.attachments = attachments;
    }
}

package com.example.passportStatusTrackingSystem.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Applicant_details {

	/** Unique application ID assigned to each applicant */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long application_id;

	/** Passport ID of the applicant */
	private long passport_id;

	/** First name of the applicant */
	private String first_name;

	/** Last name of the applicant */
	private String last_name;

	/** Date of birth of the applicant */
	private Date dob;

	/** Gender of the applicant */
	private String gender;

	/** Address of the applicant */
	private String address;

	/** Email ID of the applicant */
	private String email_id;

	/** Social Security Number (SSN) of the applicant */
	private long ssn_no;

	/** Place of birth of the applicant */
	private String birth_place;

	/** Mobile number of the applicant */
	private String mobile_no;

	/** Passport Office of the applicant */
	private String po_office;

	/** Date of the appointment for the applicant */
	private Date appointment_date;

	/** Start date of the applicant's application process */
	private Date start_date;

	/** End date of the applicant's application process */
	private Date end_date;

	/** Flag to represent the status of the application */
	private int flag;

	/** Description of the applicant details */
	private String description;

	/** One Time Password (OTP) for verification */
	private int otp;

	// Getters and setters for accessing applicant details

	/** Get the OTP for verification */
	public int getOtp() {
		return otp;
	}

	/** Set the OTP for verification */
	public void setOtp(int otp) {
		this.otp = otp;
	}

	/** Get the description of the applicant details */
	public String getDescription() {
		return description;
	}

	/** Set the description of the applicant details */
	public void setDescription(String description) {
		this.description = description;
	}

	/** Get the unique application ID assigned to each applicant */
	public long getApplication_id() {
		return application_id;
	}

	/** Set the unique application ID for the applicant */
	public void setApplication_id(long application_id) {
		this.application_id = application_id;
	}

	/** Get the passport ID of the applicant */
	public long getPassport_id() {
		return passport_id;
	}

	/** Set the passport ID for the applicant */
	public void setPassport_id(long passport_id) {
		this.passport_id = passport_id;
	}

	/** Get the first name of the applicant */
	public String getFirst_name() {
		return first_name;
	}

	/** Set the first name for the applicant */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/** Get the last name of the applicant */
	public String getLast_name() {
		return last_name;
	}

	/** Set the last name for the applicant */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/** Get the date of birth of the applicant */
	public Date getDob() {
		return dob;
	}

	/** Set the date of birth for the applicant */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/** Get the gender of the applicant */
	public String getGender() {
		return gender;
	}

	/** Set the gender for the applicant */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/** Get the address of the applicant */
	public String getAddress() {
		return address;
	}

	/** Set the address for the applicant */
	public void setAddress(String address) {
		this.address = address;
	}

	/** Get the email ID of the applicant */
	public String getEmail_id() {
		return email_id;
	}

	/** Set the email ID for the applicant */
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	/** Get the Social Security Number (SSN) of the applicant */
	public long getSsn_no() {
		return ssn_no;
	}

	/** Set the Social Security Number (SSN) for the applicant */
	public void setSsn_no(long ssn_no) {
		this.ssn_no = ssn_no;
	}

	/** Get the place of birth of the applicant */
	public String getBirth_place() {
		return birth_place;
	}

	/** Set the place of birth for the applicant */
	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}

	/** Get the mobile number of the applicant */
	public String getMobile_no() {
		return mobile_no;
	}

	/** Set the mobile number for the applicant */
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	/** Get the Passport Office of the applicant */
	public String getPo_office() {
		return po_office;
	}

	/** Set the Passport Office for the applicant */
	public void setPo_office(String po_office) {
		this.po_office = po_office;
	}

	/** Get the date of the appointment for the applicant */
	public Date getAppointment_date() {
		return appointment_date;
	}

	/** Set the date of the appointment for the applicant */
	public void setAppointment_date(Date appointment_date) {
		this.appointment_date = appointment_date;
	}

	/** Get the start date of the applicant's application process */
	public Date getStart_date() {
		return start_date;
	}

	/** Set the start date of the applicant's application process */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	/** Get the end date of the applicant's application process */
	public Date getEnd_date() {
		return end_date;
	}

	/** Set the end date of the applicant's application process */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	/** Get the flag to represent the status of the application */
	public int getFlag() {
		return flag;
	}

	/** Set the flag to represent the status of the application */
	public void setFlag(int flag) {
		this.flag = flag;
	}
}

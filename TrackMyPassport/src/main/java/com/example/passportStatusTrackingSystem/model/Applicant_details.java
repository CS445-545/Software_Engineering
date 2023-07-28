package com.example.passportStatusTrackingSystem.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Applicant_details {
		private long application_id;
		private long passport_id;
		private String first_name;
		private String last_name;
		private Date dob;
		private String gender;
		private String address;
		private String email_id;
		private long ssn_no;
		private String birth_place;
		private String mobile_no;
		private String po_office;
		private Date appointment_date;
		private Date start_date;
		private Date end_date;
		private int flag;
		private String description;
		private int otp;
		
		public int getOtp() {
			return otp;
		}
		
		public void setOtp(int otp) {
			this.otp = otp;
		}
		
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
		
		@Id @GeneratedValue(strategy=GenerationType.AUTO)
		 public long getApplication_id() {
			return application_id;
		}
		
		public void setApplication_id(long application_id) {
			this.application_id = application_id;
		}
		
		public long getPassport_id() {
			return passport_id;
		}
		
		public void setPassport_id(long passport_id) {
			this.passport_id = passport_id;
		}
		
		public String getFirst_name() {
			return first_name;
		}
		
		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}
		
		public String getLast_name() {
			return last_name;
		}
		
		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}
		
		public Date getDob() {
			return dob;
		}
		
		public void setDob(Date dob) {
			this.dob = dob;
		}
		
		public String getGender() {
			return gender;
		}
		
		public void setGender(String gender) {
			this.gender = gender;
		}
		
		public String getAddress() {
			return address;
		}
		
		public void setAddress(String address) {
			this.address = address;
		}
		
		public String getEmail_id() {
			return email_id;
		}
		
		public void setEmail_id(String email_id) {
			this.email_id = email_id;
		}
		
		public long getSsn_no() {
			return ssn_no;
		}
		
		public void setSsn_no(long ssn_no) {
			this.ssn_no = ssn_no;
		}
		
		public String getBirth_place() {
			return birth_place;
		}
		
		public void setBirth_place(String birth_place) {
			this.birth_place = birth_place;
		}
		
		public String getMobile_no() {
			return mobile_no;
		}
		
		public void setMobile_no(String mobile_no) {
			this.mobile_no = mobile_no;
		}

		public String getPo_office() {
			return po_office;
		}
		
		public void setPo_office(String po_office) {
			this.po_office = po_office;
		}
		
		public Date getAppointment_date() {
			return appointment_date;
		}
		
		public void setAppointment_date(Date appointment_date) {
			this.appointment_date = appointment_date;
		}
		
		public Date getStart_date() {
			return start_date;
		}
		
		public void setStart_date(Date start_date) {
			this.start_date = start_date;
		}
		
		public Date getEnd_date() {
			return end_date;
		}
		
		public void setEnd_date(Date end_date) {
			this.end_date = end_date;
		}
		
		public int getFlag() {
			return flag;
		}
		
		public void setFlag(int flag) {
			this.flag = flag;
		}
	
}

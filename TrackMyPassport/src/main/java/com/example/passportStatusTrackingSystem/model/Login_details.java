package com.example.passportStatusTrackingSystem.model;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Login_details {
	
		private long application_id;
		private String user_name;
		private String email_id;
		private String password;
		
		@GeneratedValue(strategy=GenerationType.AUTO)
		public long getApplication_id() {
			return application_id;
		}
		
		public void setApplication_id(long application_id) {
			this.application_id = application_id;
		}
		
		public String getUser_name() {
			return user_name;
		}
		
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		
		@Id
		public String getEmail_id() {
			return email_id;
		}
		
		public void setEmail_id(String email_id) {
			this.email_id = email_id;
		}
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
}

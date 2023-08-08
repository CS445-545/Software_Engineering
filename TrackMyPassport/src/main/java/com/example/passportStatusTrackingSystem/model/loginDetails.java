package com.example.passportStatusTrackingSystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class represents the Login_details entity in the application.
 * It is used to store and manage user login details for the passport status
 * tracking system.
 */
@Entity
public class loginDetails {
    // Properties representing user login details
    /** Email ID of the applicant (also used as the primary key) */
    @Id
    private String email_id;
    /** User name of the applicant */
    private String user_name;

    /** Password of the applicant's account */
    private String password;

    // Getters and setters for accessing login details

    /** Get the user name of the applicant */
    public String getUser_name() {
        return user_name;
    }

    /** Set the user name for the applicant */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /** Get the email ID of the applicant (also used as the primary key) */
    public String getEmail_id() {
        return email_id;
    }

    /** Set the email ID for the applicant (also used as the primary key) */
    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    /** Get the password of the applicant's account */
    public String getPassword() {
        return password;
    }

    /** Set the password for the applicant's account */
    public void setPassword(String password) {
        this.password = password;
    }
}

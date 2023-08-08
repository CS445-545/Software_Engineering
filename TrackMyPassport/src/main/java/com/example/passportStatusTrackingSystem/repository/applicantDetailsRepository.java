package com.example.passportStatusTrackingSystem.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.passportStatusTrackingSystem.model.applicantDetails;

public interface applicantDetailsRepository extends JpaRepository<applicantDetails, Long> {
	/*
	 * Custom query to find applicants with a specific status and appointment date
	 * equal to the current date.
	 */
	@Query("select ad from applicantDetails ad where ad.flag=:st and ad.appointment_date=curdate()")
	public List<applicantDetails> findByCurrentDateAndStatus(@Param("st") int st);

	/*
	 * Custom query to find an applicant by their application ID and date of birth.
	 */
	@Query("select ad from applicantDetails ad where ad.application_id=:applicationId and ad.dob=:dob")
	public applicantDetails findByIdAndDob(@Param("applicationId") long applicationId, @Param("dob") Date dob);

	/* Custom query to find an applicant by their Social Security Number (SSN). */
	@Query("select ad from applicantDetails ad where ad.ssn_no=:ssnId")
	public applicantDetails findBySsnId(@Param("ssnId") long ssnId);

	/* Custom query to find an applicant by their email ID. */
	@Query("select ad from applicantDetails ad where ad.email_id=:emailId")
	public applicantDetails findByEmailId(@Param("emailId") String emailId);

	/* Custom query to find an applicant by their passport ID. */
	@Query("select ad from applicantDetails ad where ad.passport_id=:passportId")
	public applicantDetails findByPassportId(@Param("passportId") long passportId);
}

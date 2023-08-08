package com.example.passportStatusTrackingSystem.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.passportStatusTrackingSystem.model.Applicant_details;

public interface Applicant_detailsRepository extends JpaRepository<Applicant_details, Long> {
	/*
	 * Custom query to find applicants with a specific status and appointment date
	 * equal to the current date.
	 */
	@Query("select ad from Applicant_details ad where ad.flag=:st and ad.appointment_date=curdate()")
	public List<Applicant_details> findByCurrentDateAndStatus(@Param("st") int st);

	/*
	 * Custom query to find an applicant by their application ID and date of birth.
	 */
	@Query("select ad from Applicant_details ad where ad.application_id=:applicationId and ad.dob=:dob")
	public Applicant_details findByIdAndDob(@Param("applicationId") long applicationId, @Param("dob") Date dob);

	/* Custom query to find an applicant by their Social Security Number (SSN). */
	@Query("select ad from Applicant_details ad where ad.ssn_no=:ssnId")
	public Applicant_details findBySsnId(@Param("ssnId") long ssnId);

	/* Custom query to find an applicant by their email ID. */
	@Query("select ad from Applicant_details ad where ad.email_id=:emailId")
	public Applicant_details findByEmailId(@Param("emailId") String emailId);

	/* Custom query to find an applicant by their passport ID. */
	@Query("select ad from Applicant_details ad where ad.passport_id=:passportId")
	public Applicant_details findByPassportId(@Param("passportId") long passportId);
}

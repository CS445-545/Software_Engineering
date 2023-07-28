package com.example.passportStatusTrackingSystem.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.passportStatusTrackingSystem.model.Applicant_details;

public interface Applicant_detailsRepository extends JpaRepository<Applicant_details, Long> {
	@Query("select ad from Applicant_details ad where ad.flag=:st and ad.appointment_date=curdate()")
	public List<Applicant_details> findByCurrentDateAndStatus(@Param("st") int st);

	@Query("select ad from Applicant_details ad where ad.application_id=:applicationId and ad.dob=:dob")
	public Applicant_details findByIdAndDob(@Param("applicationId") long applicationId, @Param("dob") Date dob);

	@Query("select ad from Applicant_details ad where ad.ssn_no=:ssnId")
	public Applicant_details findBySsnId(@Param("ssnId") long ssnId);

	@Query("select ad from Applicant_details ad where ad.email_id=:emailId")
	public Applicant_details findByEmailId(@Param("emailId") String emailId);

	@Query("select ad from Applicant_details ad where ad.passport_id=:passportId")
	public Applicant_details findByPassportId(@Param("passportId") long passportId);

}

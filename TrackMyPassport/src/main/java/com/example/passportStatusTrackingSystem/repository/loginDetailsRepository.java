package com.example.passportStatusTrackingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.passportStatusTrackingSystem.model.loginDetails;

public interface loginDetailsRepository extends JpaRepository<loginDetails, String> {

	/* Custom query to find login details by email ID. */
	@Query("select ld from loginDetails ld where email_id=:email_id")
	public loginDetails findByEmailId(@Param("email_id") String email_id);

	/* Custom query to find login details by email ID and password. */
	@Query("select ad from loginDetails ad where ad.email_id=:emailId and password=:password")
	public loginDetails findByEmailIdandPassword(@Param("emailId") String emailId, @Param("password") String password);
}

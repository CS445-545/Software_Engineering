// This repository interface handles data access and database interactions for the Login_details entity.
// It extends JpaRepository, providing out-of-the-box CRUD operations for the entity.

package com.example.passportStatusTrackingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.passportStatusTrackingSystem.model.Login_details;

public interface Login_detailsRepository extends JpaRepository<Login_details, String> {

	// Custom query to find login details by email ID.
	@Query("select ld from Login_details ld where email_id=:email_id")
	public Login_details findByEmailId(@Param("email_id") String email_id);

	// Custom query to find login details by email ID and password.
	@Query("select ad from Login_details ad where ad.email_id=:emailId and password=:password")
	public Login_details findByEmailIdandPassword(@Param("emailId") String emailId, @Param("password") String password);

}

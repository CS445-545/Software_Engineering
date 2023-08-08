package com.example.passportStatusTrackingSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.passportStatusTrackingSystem.model.Login_details;
import com.example.passportStatusTrackingSystem.repository.Login_detailsRepository;

@Service
@Transactional
public class Login_detailsService {
	@Autowired
	private Login_detailsRepository repo;

	/**
	 * Retrieves a list of all Login_details objects.
	 *
	 * @return A list of Login_details objects.
	 */
	public List<Login_details> listAll() {
		return repo.findAll();
	}

	/**
	 * Finds a Login_details object by its email ID.
	 *
	 * @param emailId
	 *            The email ID to search for.
	 * @return The Login_details object with the specified email ID, if found;
	 *         otherwise, null.
	 */
	public Login_details findByEmailId(String emailId) {
		return repo.findByEmailId(emailId);
	}

	/**
	 * Saves a new Login_details object or updates an existing one.
	 *
	 * @param login_details
	 *            The Login_details object to be saved or updated.
	 */
	public void save(Login_details login_details) {
		repo.save(login_details);
	}

	/**
	 * Retrieves a Login_details object by its email ID.
	 *
	 * @param email_id
	 *            The email ID of the Login_details object to retrieve.
	 * @return The Login_details object with the specified email ID, if found;
	 *         otherwise, null.
	 */
	public Login_details get(String email_id) {
		return repo.findById(email_id).get();
	}

	/**
	 * Deletes a Login_details object with the specified email ID.
	 *
	 * @param email_id
	 *            The email ID of the Login_details object to delete.
	 */
	public void delete(String email_id) {
		repo.deleteById(email_id);
	}

	/**
	 * Finds a Login_details object by its email ID.
	 *
	 * @param email_id
	 *            The email ID to search for.
	 * @return The Login_details object with the specified email ID, if found;
	 *         otherwise, null.
	 */
	public Login_details findEmail(String email_id) {
		return repo.findByEmailId(email_id);
	}

	/**
	 * Finds a Login_details object by its email ID and password.
	 *
	 * @param emailId
	 *            The email ID to search for.
	 * @param password
	 *            The password to search for.
	 * @return The Login_details object with the specified email ID and password, if
	 *         found; otherwise, null.
	 */
	public Login_details findByEmailIdandPassword(String emailId, String password) {
		return repo.findByEmailIdandPassword(emailId, password);
	}
}

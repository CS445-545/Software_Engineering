package com.example.passportStatusTrackingSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.passportStatusTrackingSystem.model.loginDetails;
import com.example.passportStatusTrackingSystem.repository.loginDetailsRepository;

@Service
@Transactional
public class loginDetailsService {
	@Autowired
	private loginDetailsRepository repo;

	/**
	 * Retrieves a list of all loginDetails objects.
	 *
	 * A list of loginDetails objects.
	 */
	public List<loginDetails> listAll() {
		return repo.findAll();
	}

	/**
	 * Finds a loginDetails object by its email ID.
	 *
	 * @param emailId
	 *                The email ID to search for.
	 * @return The loginDetails object with the specified email ID, if found;
	 *         otherwise, null.
	 */
	public loginDetails findByEmailId(String emailId) {
		return repo.findByEmailId(emailId);
	}

	/**
	 * Saves a new loginDetails object or updates an existing one.
	 *
	 * @param loginDetails
	 *                      The loginDetails object to be saved or updated.
	 */
	public void save(loginDetails loginDetails1) {
		repo.save(loginDetails1);
	}

	/**
	 * Retrieves a loginDetails object by its email ID.
	 *
	 * @param email_id
	 *                 The email ID of the loginDetails object to retrieve.
	 * @return The loginDetails object with the specified email ID, if found;
	 *         otherwise, null.
	 */
	public loginDetails get(String emailId) {
		return repo.findById(emailId).get();
	}

	/**
	 * Deletes a loginDetails object with the specified email ID.
	 *
	 * @param email_id
	 *                 The email ID of the loginDetails object to delete.
	 */
	public void delete(String emailId) {
		repo.deleteById(emailId);
	}

	/**
	 * Finds a loginDetails object by its email ID.
	 *
	 * @param email_id
	 *                 The email ID to search for.
	 * @return The loginDetails object with the specified email ID, if found;
	 *         otherwise, null.
	 */
	public loginDetails findEmail(String emailId) {
		return repo.findByEmailId(emailId);
	}

	/**
	 * Finds a loginDetails object by its email ID and password.
	 *
	 * @param emailId
	 *                 The email ID to search for.
	 * @param password
	 *                 The password to search for.
	 * @return The loginDetails object with the specified email ID and password, if
	 *         found; otherwise, null.
	 */
	public loginDetails findByEmailIdandPassword(String emailId, String password) {
		return repo.findByEmailIdandPassword(emailId, password);
	}
}

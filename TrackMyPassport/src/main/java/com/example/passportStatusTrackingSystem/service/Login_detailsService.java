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
	     
	    public List<Login_details> listAll() {
	        return repo.findAll();
	    }
	     
	    public Login_details findByEmailId(String emailId)
	    {
	    	return repo.findByEmailId(emailId);
	    }
	    
	    public void save(Login_details login_details) {
	        repo.save(login_details);
	    }
	     
	    public Login_details get(String email_id) {
	        return repo.findById(email_id).get();
	    }
	     
	    public void delete(String email_id) {
	        repo.deleteById(email_id);
	    }
	    
	    public Login_details findEmail(String email_id)
	    {
	    	return repo.findByEmailId(email_id);
	    }
	    
	    public Login_details findByEmailIdandPassword(String emailId,String password)
	    {
	    	return repo.findByEmailIdandPassword(emailId,password);
	    }
}

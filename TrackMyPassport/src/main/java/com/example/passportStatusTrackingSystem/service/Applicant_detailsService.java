package com.example.passportStatusTrackingSystem.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.awt.Color;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.example.passportStatusTrackingSystem.model.Applicant_details;
import com.example.passportStatusTrackingSystem.repository.Applicant_detailsRepository;

@Service
@Transactional
public class Applicant_detailsService {
	 @Autowired
	    private Applicant_detailsRepository repo;
	     
	    public List<Applicant_details> listAll() {
	        return repo.findAll();
	    }
	     
	    public void save(Applicant_details applicant_details) {
	        repo.save(applicant_details);
	    }
	    
	    public Applicant_details get(long application_id) {
	        return repo.findById(application_id).get();
	    }
	     
	    public void delete(long application_id) {
	        repo.deleteById(application_id);
	    }
	    
	    public Applicant_details update(long applicationId)
	    {
	    	Applicant_details applicant_details=repo.findById(applicationId).get();
	    	applicant_details.setFlag(2);
	    	repo.save(applicant_details);
	    	return applicant_details;
	    	
	    }
	    
	    
	    
	    public Applicant_details trackDetails(long applicationId,Date dob)
	    {
	    	Applicant_details applicant_details=repo.findByIdAndDob(applicationId,dob);
	    	return applicant_details;
	    }
	    
	    public Applicant_details findByEmailId(String emailId)
	    {
	    	return repo.findByEmailId(emailId);
	    }
	    
	    public Applicant_details findBySsn(long ssn_id)
	    {
	    	return repo.findBySsnId(ssn_id);
	    }
	    
	    public Applicant_details findByPassportId(long passportId)
	    {
	    	return repo.findByPassportId(passportId);
	    }
	    
	    
			
		


}

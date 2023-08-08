package com.example.passportStatusTrackingSystem.service;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.passportStatusTrackingSystem.model.applicantDetails;
import com.example.passportStatusTrackingSystem.repository.applicantDetailsRepository;

@Service
@Transactional
public class applicantDetailsService {
	@Autowired
	private applicantDetailsRepository repo;

	/* Retrieves all applicants' details */
	public List<applicantDetails> listAll() {
		return repo.findAll();
	}

	/* Saves applicant details */
	public void save(applicantDetails applicantDetails) {
		repo.save(applicantDetails);
	}

	/* Retrieves applicants' details based on status */
	public List<applicantDetails> listStatus(int status) {
		return repo.findByCurrentDateAndStatus(status);
	}

	/* Retrieves an applicant's details by application ID */
	public applicantDetails get(long application_id) {
		return repo.findById(application_id).get();
	}

	/* Deletes an applicant's details by application ID */
	public void delete(long application_id) {
		repo.deleteById(application_id);
	}

	/* Updates an applicant's flag status */
	public applicantDetails update(long applicationId) {
		applicantDetails applicantDetails = repo.findById(applicationId).get();
		applicantDetails.setFlag(2);
		repo.save(applicantDetails);
		return applicantDetails;

	}

	/* Retrieves an applicant's details without updating */
	public applicantDetails update1(long applicationId) {
		applicantDetails applicantDetails = repo.findById(applicationId).get();
		return applicantDetails;

	}

	/* Updates an applicant's flag and police-related details */
	public applicantDetails updatePolice(long applicationId) {
		long millis = System.currentTimeMillis();
		java.sql.Date currentDate = new java.sql.Date(millis);
		LocalDate localDate = currentDate.toLocalDate();
		int day = localDate.getDayOfMonth();
		int months = localDate.getMonthValue();
		int years = localDate.getYear() + 10;
		java.sql.Date endDate = java.sql.Date.valueOf(years + "-" + months + "-" + day);
		applicantDetails applicantDetails = repo.findById(applicationId).get();
		applicantDetails.setFlag(3);
		applicantDetails.setStart_date(new java.sql.Date(millis));
		applicantDetails.setEnd_date(endDate);
		applicantDetails.setPassport_id(applicantDetails.getApplication_id() + 100);
		repo.save(applicantDetails);
		return applicantDetails;
	}

	/* Sets the flag to indicate a passport officer request deletion */
	public void deletePoRequest(long applicationId) {
		applicantDetails applicantDetails = repo.findById(applicationId).get();
		applicantDetails.setFlag(4);
		repo.save(applicantDetails);
	}

	/* Sets the flag to indicate a police request deletion */
	public void deletePoliceRequest(long applicationId) {
		applicantDetails applicantDetails = repo.findById(applicationId).get();
		applicantDetails.setFlag(5);
		repo.save(applicantDetails);
	}

	/* Tracks applicant details using application ID and date of birth */
	public applicantDetails trackDetails(long applicationId, Date dob) {
		applicantDetails applicantDetails = repo.findByIdAndDob(applicationId, dob);
		return applicantDetails;
	}

	/* Finds applicant details by email ID */
	public applicantDetails findByEmailId(String emailId) {
		return repo.findByEmailId(emailId);
	}

	/* Finds applicant details by SSN */
	public applicantDetails findBySsn(long ssn_id) {
		return repo.findBySsnId(ssn_id);
	}

	/* Finds applicant details by passport ID */
	public applicantDetails findByPassportId(long passportId) {
		return repo.findByPassportId(passportId);
	}

	/* Generates a PDF with applicant passport information */
	public void pdfCreation(long applicantId) {
		String filepath = "C:\\Deveopment_avecto\\" + applicantId + "simple.pdf";
		applicantDetails applicantDetails_pdf = repo.findById(applicantId).get();
		String passportID = Long.toString(applicantDetails_pdf.getPassport_id());
		String F_Name = applicantDetails_pdf.getFirst_name();
		String L_name = applicantDetails_pdf.getLast_name();
		Date DOB = applicantDetails_pdf.getDob();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String Dob = formatter.format(DOB);
		String Gender = applicantDetails_pdf.getGender();
		String Address = applicantDetails_pdf.getAddress();
		Date Strt_Date = applicantDetails_pdf.getStart_date();
		String Start_date = formatter.format(Strt_Date);
		Date End_Date = applicantDetails_pdf.getEnd_date();
		String end_date = formatter.format(End_Date);
		try {
			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			contentStream.beginText();
			contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 14);
			contentStream.setLeading(14.5f);
			contentStream.setNonStrokingColor(Color.BLUE);
			contentStream.newLineAtOffset(150, 700);
			contentStream.showText("UNITED STATES OF AMERICA");
			contentStream.newLine();
			contentStream.newLine();
			contentStream.showText("PASSPORT");
			contentStream.setFont(PDType1Font.TIMES_ROMAN, 11);
			contentStream.setLeading(14.5f);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.newLine();
			contentStream.newLine();
			contentStream.showText("Passport NO: " + passportID);
			contentStream.newLine();
			contentStream.newLine();
			contentStream.showText("First Name: " + F_Name + "        Last Name: " + L_name);
			contentStream.newLine();
			contentStream.newLine();
			contentStream.showText("Date Of Birth: " + Dob + "        Gender: " + Gender);
			contentStream.newLine();
			contentStream.newLine();
			contentStream.showText("Address: " + Address);
			contentStream.newLine();
			contentStream.newLine();
			contentStream.showText("Date Of Issue: " + Start_date + "        Date Of Expiry: " + end_date);
			contentStream.endText();
			contentStream.close();
			document.save(filepath);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package com.example.passportStatusTrackingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.passportStatusTrackingSystem.model.applicantDetails;
import com.example.passportStatusTrackingSystem.model.loginDetails;
import com.example.passportStatusTrackingSystem.model.mail;
import com.example.passportStatusTrackingSystem.service.applicantDetailsService;
import com.example.passportStatusTrackingSystem.service.loginDetailsService;
import com.example.passportStatusTrackingSystem.service.mailService;

@Controller
public class mainController {

	int randomPIN = 0;
	@Autowired
	private applicantDetailsService service;

	@Autowired
	private loginDetailsService service1;

	@Autowired
	private mailService mailService;

	String emailId, password;
	long applicationId;

	/**
	 * Displays the main login page.
	 * 
	 * @return The login page view name.
	 */
	@RequestMapping(value = "/login")
	public String homeScreen() {
		return "login";
	}

	/**
	 * Displays the admin login page (for passport officer and police officer).
	 * 
	 * @return The admin login page view name.
	 */
	@RequestMapping(value = "/loginAdmin")
	public String loginAdmin() {
		return "loginAdmin";
	}

	/**
	 * Displays the welcome page after successful admin login.
	 * 
	 * @return The welcome page view name.
	 */
	@RequestMapping(value = "/welcomeAdmin")
	public String welcomeAdmin() {
		return "welcomeAdmin";
	}

	/**
	 * Displays the list of applicants' details for Passport Officer approval.
	 * 
	 * @param model
	 *              The model to store attributes.
	 * @return The view name of the page displaying applicant details for Passport
	 *         Officer.
	 */
	@RequestMapping(value = "/viewApplicant")
	public String poApproval(Model model) {
		List<applicantDetails> listApplicant = service.listStatus(1);
		model.addAttribute("listApplicant", listApplicant);
		String description = null;
		model.addAttribute("description", description);
		return "viewApplicant";
	}

	/**
	 * Displays the list of applicants' details for Police Officer approval.
	 * 
	 * @param model
	 *              The model to store attributes.
	 * @return The view name of the page displaying applicant details for Police
	 *         Officer.
	 */
	@RequestMapping(value = "/viewApplicant2")
	public String policeApproval(Model model) {
		List<applicantDetails> listApplicant = service.listStatus(2);
		model.addAttribute("listApplicant", listApplicant);
		model.addAttribute("number", 3);

		return "viewApplicant2";
	}

	/**
	 * Displays the welcome page for the applicant after successful login or
	 * registration.
	 *
	 * @return The view name for the welcome page.
	 */
	@RequestMapping(value = "/welcomeApplicant")
	public String welcomeApplicant() {
		return "welcomeApplicant";
	}

	/**
	 * Displays the login page for existing applicant users.
	 *
	 * @param model
	 *              The model to store attributes.
	 * @return The view name for the existing applicant login page.
	 */
	@RequestMapping(value = "/existingApplicantLogin")
	public String existingApplicationLogin(Model model) {

		loginDetails loginDetails = new loginDetails();
		model.addAttribute("loginDetails", loginDetails);

		return "existingApplicantLogin";
	}

	/**
	 * Displays the status table of the applicant.
	 *
	 * @param model
	 *                         The model to store attributes.
	 * @param applicantDetails
	 *                         The applicant details to track the status.
	 * @return The view name for the status table page.
	 */
	@PostMapping(value = "/trackStatusTable")
	public String trackStatusTable(Model model,
			@ModelAttribute("applicantDetails") applicantDetails applicantDetails) {

		applicantDetails applicantDetails1 = service.trackDetails(applicantDetails.getApplication_id(),
				applicantDetails.getDob());

		try {
			int flag1 = applicantDetails1.getFlag();
			if (flag1 == 1) {
				flag1 = 2;
			} else if (flag1 == 2) {
				flag1 = 3;
			} else if (flag1 == 3) {
				flag1 = 6;
			} else if (flag1 == 4) {
				flag1 = 2;
			} else if (flag1 == 5) {
				flag1 = 3;
			}

			applicantDetails1.getApplication_id();
			model.addAttribute("applicantDetails", applicantDetails1);
			model.addAttribute("flag", flag1);
			return "trackStatusTable";

		} catch (NullPointerException e) {
			return "redirect:/loginTrackStatus";
		}
	}

	/**
	 * Displays the new applicant page.
	 *
	 * @param model
	 *              The model to store attributes.
	 * @return The view name for the new applicant page.
	 */
	@RequestMapping(value = "/newapplicant")
	public String newapplicant(Model model) {

		applicantDetails applicantDetails = new applicantDetails();
		model.addAttribute("applicantDetails", applicantDetails);
		return "newapplicant";

	}

	/**
	 * Saves the new applicant's details and sends an email with appointment
	 * details.
	 *
	 * @param model
	 *                         The model to store attributes.
	 * @param applicantDetails
	 *                         The new applicant's details.
	 * @param loginDetails
	 *                         The login details.
	 * @return The view name for the login page.
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(Model model, @ModelAttribute("applicantDetails") applicantDetails applicantDetails,
			@ModelAttribute("loginDetails") loginDetails loginDetails) {
		String str = null;
		applicantDetails ad1 = service.findBySsn(applicantDetails.getSsn_no());
		try {
			if (ad1 != null) {
				model.addAttribute("logError", "logError");
				str = "newapplicant";
			} else {
				applicantDetails.setFlag(1);
				service.save(applicantDetails);

				mail mail1 = new mail();
				mail1.setMailFrom("passportstatustracking@gmail.com");
				mail1.setMailTo(applicantDetails.getEmail_id());
				mail1.setMailSubject("User Details About Application");
				mail1.setMailContent(
						"Application Id : " + applicantDetails.getApplication_id() + "\nAppointment Date : "
								+ applicantDetails.getAppointment_date());

				mailService.sendEmail(mail1);

				str = "login";
			}
		} catch (NullPointerException e) {

			throw e;
		}

		return str;
	}

	/**
	 * Saves the existing user's (applicant) appointment details.
	 *
	 * @param model
	 *                         The model to store attributes.
	 * @param applicantDetails
	 *                         The existing applicant's details.
	 * @return The view name for the login page.
	 */
	@RequestMapping(value = "/save1", method = RequestMethod.POST)
	public String saveApplication(Model model,
			@ModelAttribute("applicantDetails") applicantDetails applicantDetails) {

		applicantDetails ad1 = service.findByPassportId(applicantDetails.getPassport_id());

		try {
			if (ad1 != null) {

				ad1.setAddress(applicantDetails.getAddress());
				ad1.setEmail_id(applicantDetails.getEmail_id());
				ad1.setMobile_no(applicantDetails.getMobile_no());
				ad1.setAppointment_date(applicantDetails.getAppointment_date());
				ad1.setFlag(1);

				service.save(ad1);
				applicantDetails ap = new applicantDetails();
				model.addAttribute("applicantDetails", ap);
				return "login";
			} else {
				applicantDetails ap = new applicantDetails();
				model.addAttribute("applicantDetails", ap);
				model.addAttribute("logError", "logError");
				return "renewApplication";
			}

		} catch (NullPointerException e) {
			throw e;

		}
	}

	/**
	 * Displays the login page for existing applicant users.
	 *
	 * @param model
	 *              The model to store attributes.
	 * @return The view name for the existing applicant login page.
	 */
	@RequestMapping(value = "/loginTrackStatus")
	public String loginTrackStatus(Model model) {
		applicantDetails applicantDetails = new applicantDetails();
		model.addAttribute("applicantDetails", applicantDetails);
		return "loginTrackStatus";

	}

	/**
	 * Displays the registration page for new users (applicants).
	 *
	 * @param model
	 *              The model to store attributes.
	 * @return The view name for the new user registration page.
	 */
	@RequestMapping(value = "/newUserRegister")
	public String newUserRegister(Model model) {

		loginDetails loginDetails = new loginDetails();
		model.addAttribute("loginDetails", loginDetails);
		return "newUserRegister";

	}

	/**
	 * Sends the user's password to their registered email.
	 *
	 * @param model
	 *                     The model to store attributes.
	 * @param loginDetails
	 *                     The login details containing the email for password
	 *                     retrieval.
	 * @return The view name for the login page or an error page if email not found.
	 */
	@RequestMapping(value = "/sendPassword", method = RequestMethod.POST)
	public String sendPass(Model model, @ModelAttribute("loginDetails") loginDetails loginDetails,
			@ModelAttribute("applicantDetails") applicantDetails applicantDetails) {
		loginDetails loginDetails1 = service1.findByEmailId(loginDetails.getEmail_id());
		applicantDetails applicantDetails1 = service.findByEmailId(applicantDetails.getEmail_id());
		try {

			applicantDetails.getApplication_id();
			mail mail1 = new mail();
			mail1.setMailFrom("passportstatustracking@gmail.com");
			mail1.setMailTo(loginDetails.getEmail_id());
			mail1.setMailSubject("User Details About Password");
			mail1.setMailContent("Application Id : " + applicantDetails1.getApplication_id() + "\nPassword : "
					+ loginDetails1.getPassword());

			mailService.sendEmail(mail1);

			loginDetails loginDetails2 = new loginDetails();
			model.addAttribute("loginDetails", loginDetails2);
			return "existingApplicantLogin";

		} catch (NullPointerException e) {

			applicantDetails ap = new applicantDetails();
			model.addAttribute("applicantDetails", ap);
			return "redirect:/emailForgot";

		}
	}

	/**
	 * Sends an OTP to the user's registered email for verification.
	 *
	 * @param model
	 *                     The model to store attributes.
	 * @param loginDetails
	 *                     The login details containing the email for OTP sending.
	 * @return The view name for the OTP verification page or an error page if email
	 *         not found.
	 */
	@RequestMapping(value = "/sendOtp", method = RequestMethod.POST)
	public String sendOtp(Model model, @ModelAttribute("loginDetails") loginDetails loginDetails,
			@ModelAttribute("applicantDetails") applicantDetails applicantDetails) {
		loginDetails loginDetails1 = service1.findByEmailId(loginDetails.getEmail_id());
		applicantDetails applicantDetails1 = service.findByEmailId(applicantDetails.getEmail_id());
		try {
			randomPIN = (int) (Math.random() * 9000) + 1000;
			emailId = loginDetails1.getEmail_id();
			password = loginDetails1.getPassword();
			applicationId = applicantDetails1.getApplication_id();
			mail mail1 = new mail();
			mail1.setMailFrom("passportstatustracking@gmail.com");
			mail1.setMailTo(loginDetails1.getEmail_id());
			mail1.setMailSubject("Account Verification - OTP");
			mail1.setMailContent("Please enter the otp for verification.....\n\n Your One Time Password is : "
					+ randomPIN + " \n\nThank You.....");

			mailService.sendEmail(mail1);
			int num = 0;
			applicantDetails applicantDetails2 = new applicantDetails();

			model.addAttribute("applicantDetails", applicantDetails2);

			return "otp2";

		} catch (NullPointerException e) {
			applicantDetails ap = new applicantDetails();
			model.addAttribute("applicantDetails", ap);
			return "redirect:/emailForgot";
		}
	}

	/**
	 * Retrieves existing applicant details for renewal.
	 *
	 * @param model
	 *                      The model to store attributes.
	 * @param loginDetails1
	 *                      The login details containing the email and password for
	 *                      validation.
	 * @return The view name for the application renewal page or the login page if
	 *         credentials are invalid.
	 */
	@RequestMapping(value = "/renewApplicant", method = RequestMethod.POST)
	public String renewApp(Model model, @ModelAttribute("loginDetails") loginDetails loginDetails1) {
		loginDetails loginDetails2 = service1.findByEmailIdandPassword(loginDetails1.getEmail_id(),
				loginDetails1.getPassword());
		try {
			loginDetails2.getEmail_id();
			applicantDetails applicantDetails = new applicantDetails();
			model.addAttribute("applicantDetails", applicantDetails);

			return "renewApplication";
		} catch (NullPointerException e) {
			return "redirect:/existingApplicantLogin";
		}
	}

	/**
	 * Displays the forgot password page.
	 *
	 * @param model
	 *              The model to store attributes.
	 * @return The view name for the forgot password page.
	 */
	@RequestMapping(value = "/emailForgot")
	public String forgot(Model model) {
		loginDetails ad = new loginDetails();
		model.addAttribute("loginDetails", ad);
		return "emailForgot";
	}

	/**
	 * Saves the new user (applicant) details and sends an OTP to the registered
	 * email.
	 *
	 * @param model
	 *                     The model to store attributes.
	 * @param loginDetails
	 *                     The login details containing the email for registration.
	 * @return The view name for OTP verification page or an error page if
	 *         registration fails.
	 */
	@RequestMapping(value = "/save2", method = RequestMethod.POST)
	public String saveNewUser(Model model, @ModelAttribute("loginDetails") loginDetails loginDetails) {

		String str = null;
		int flag = 0;
		loginDetails ld1 = service1.findEmail(loginDetails.getEmail_id());
		try {
			ld1.getEmail_id();
			loginDetails ld = new loginDetails();
			model.addAttribute("loginDetails", ld);
			str = "newUserRegister";
		} catch (NullPointerException e) {
			flag = 1;
		}
		if (flag == 1) {
			randomPIN = (int) (Math.random() * 9000) + 1000;
			mail mail = new mail();
			mail.setMailFrom("passportstatustracking@gmail.com");
			mail.setMailTo(loginDetails.getEmail_id());
			mail.setMailSubject("Account Verification - OTP");
			mail.setMailContent("Please enter the otp for verification.....\n\n Your One Time Password is : "
					+ randomPIN + " \n\nThank You.....");

			int status = mailService.sendEmail(mail);
			if (status == 1) {

				service1.save(loginDetails);
				applicantDetails ap = new applicantDetails();
				model.addAttribute("applicantDetails", ap);
				str = "otp1";
			} else {
				loginDetails ld = new loginDetails();
				model.addAttribute("loginDetails", ld);
				model.addAttribute("logError", "logError");
				str = "newUserRegister";
			}
		}
		return str;
	}

	/**
	 * Compares the entered OTP with the generated OTP for new applicant
	 * verification.
	 *
	 * @param model
	 *                         The model to store attributes.
	 * @param applicantDetails
	 *                         The applicant details containing the entered OTP.
	 * @return The view name for the new applicant registration page or an error
	 *         page if OTP is incorrect.
	 */
	@PostMapping(value = "/compareOTP")
	public String compareOTP(Model model, @ModelAttribute("applicantDetails") applicantDetails applicantDetails) {
		if (applicantDetails.getOtp() == randomPIN) {
			applicantDetails ad = new applicantDetails();
			model.addAttribute("applicantDetails", ad);
			return "/newApplicant";
		} else {
			applicantDetails ad = new applicantDetails();
			model.addAttribute("applicantDetails", ad);
			model.addAttribute("logError", "logError");
			return "otp1";
		}
	}

	/**
	 * Compares the entered OTP with the generated OTP for existing applicant
	 * password retrieval.
	 *
	 * @param model
	 *                         The model to store attributes.
	 * @param loginDetails
	 *                         The login details containing the entered OTP.
	 * @param applicantDetails
	 *                         The applicant details containing the entered OTP.
	 * @return The view name for the login page or an error page if OTP is
	 *         incorrect.
	 */
	@PostMapping(value = "/compareOTPForgot")
	public String compareOTPForgot(Model model, @ModelAttribute("loginDetails") loginDetails loginDetails,
			@ModelAttribute("applicantDetails") applicantDetails applicantDetails) {
		if (applicantDetails.getOtp() == randomPIN) {
			mail mail = new mail();
			mail.setMailFrom("passportstatustracking@gmail.com");
			mail.setMailTo(emailId);
			mail.setMailSubject("User Details About Password");
			mail.setMailContent("Application Id : " + applicationId + "\nPassword : " + password);

			mailService.sendEmail(mail);

			loginDetails ad = new loginDetails();
			model.addAttribute("loginDetails", ad);
			return "/existingApplicantLogin";
		} else {
			loginDetails ad = new loginDetails();
			model.addAttribute("loginDetails", ad);
			model.addAttribute("logError", "logError");
			return "otp2";
		}
	}

	/**
	 * Handles the approval of an application by the Passport Officer.
	 *
	 * @param model
	 *                         The model to store attributes.
	 * @param applicationId
	 *                         The ID of the application to be approved.
	 * @param applicantDetails
	 *                         The applicant details containing the application ID.
	 * @return The view name for the applicant details page.
	 */
	@GetMapping(value = "/approve/{applicationId}/po")
	public String approveRequestPO(Model model, @PathVariable long applicationId,
			@ModelAttribute("applicantDetails") applicantDetails applicantDetails) {

		applicantDetails ap = service.update(applicationId);
		mail mail1 = new mail();
		mail1.setMailFrom("passportstatustracking@gmail.com");
		mail1.setMailTo(ap.getEmail_id());
		mail1.setMailSubject("Passport Application APPROVED by Passport Officer");
		mail1.setMailContent(
				"Your Application is Approved by Passport Officer.\n\n Application is going to the Police department for verification. \n\nThank You.....");
		mailService.sendEmail(mail1);

		return "redirect:/viewApplicant";
	}

	/**
	 * Handles the request when Passport Officer (PO) rejects an application.
	 *
	 * @param model
	 *                         The model to store attributes.
	 * @param applicationId
	 *                         The ID of the application to be rejected.
	 * @param applicantDetails
	 *                         The applicant details model attribute.
	 * @return The view name to redirect after the rejection process.
	 */
	@RequestMapping(value = { "/reject/{applicationId}/po" })
	public String RejectRequestPORequest(Model model, @PathVariable long applicationId,
			@ModelAttribute("applicantDetails") applicantDetails applicantDetails) {
		applicantDetails ap = service.update1(applicationId);
		mail mail1 = new mail();
		mail1.setMailFrom("passportstatustracking@gmail.com");
		mail1.setMailTo(ap.getEmail_id());
		mail1.setMailSubject("Passport Application is REJECTED by Passport Officer");
		mail1.setMailContent(
				"Your Application is rejected by Passport Officer.\n\nPlease apply again later. \n\nThank You.....");
		mailService.sendEmail(mail1);
		service.deletePoRequest(applicationId);
		return "redirect:/viewApplicant";
	}

	/**
	 * Handles the request when Police Officer (PO) approves an application.
	 *
	 * @param model
	 *                         The model to store attributes.
	 * @param applicationId
	 *                         The ID of the application to be approved.
	 * @param applicantDetails
	 *                         The applicant details model attribute.
	 * @return The view name to redirect after the approval process.
	 */
	@GetMapping(value = "/approve/{applicationId}/police")
	public String approveRequestPolice(Model model, @PathVariable long applicationId,
			@ModelAttribute("applicantDetails") applicantDetails applicantDetails) {
		applicantDetails ap = service.updatePolice(applicationId);
		service.pdfCreation(applicationId);
		mail mail1 = new mail();
		mail1.setMailFrom("passportstatustracking@gmail.com");
		mail1.setMailTo(ap.getEmail_id());
		mail1.setMailSubject("Passport Application Approved by Police and E-Passport");
		mail1.setMailContent("Your Application is  Approved by Police Officer.. \n\nThank You! Have a great day.");
		mailService.sendEmail(mail1, ap.getApplication_id());

		return "redirect:/viewApplicant2";
	}

	/**
	 * Handles the request when Police Officer (PO) rejects an application.
	 *
	 * @param model
	 *                         The model to store attributes.
	 * @param applicationId
	 *                         The ID of the application to be rejected.
	 * @param applicantDetails
	 *                         The applicant details model attribute.
	 * @return The view name to redirect after the rejection process.
	 */
	@RequestMapping(value = { "/reject/{applicationId}/police" })
	public String rejectRequestPoliceRequest(Model model, @PathVariable long applicationId,
			@ModelAttribute("applicantDetails") applicantDetails applicantDetails) {
		applicantDetails ap = service.update1(applicationId);
		mail mail1 = new mail();
		mail1.setMailFrom("passportstatustracking@gmail.com");
		mail1.setMailTo(ap.getEmail_id());
		mail1.setMailSubject("Passport Application Rejected by Police Officer");
		mail1.setMailContent(
				"Your Application is rejected by Passport Officer.\n\n Please apply again later. \n\nThank You.....");
		mailService.sendEmail(mail1);
		service.deletePoliceRequest(applicationId);
		return "redirect:/viewApplicant2";
	}

}
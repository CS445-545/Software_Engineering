/**
 * The main controller class that handles all the HTTP requests and
 * business logic of the Passport Status Tracking System.
 */
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

import com.example.passportStatusTrackingSystem.model.Applicant_details;
import com.example.passportStatusTrackingSystem.model.Login_details;
import com.example.passportStatusTrackingSystem.model.Mail;
import com.example.passportStatusTrackingSystem.service.Applicant_detailsService;
import com.example.passportStatusTrackingSystem.service.Login_detailsService;
import com.example.passportStatusTrackingSystem.service.MailService;

@Controller
public class MainController {

	int randomPIN = 0;
	@Autowired
	private Applicant_detailsService service;

	@Autowired
	private Login_detailsService service1;

	@Autowired
	private MailService mailService;

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
	 * @param model The model to store attributes.
	 * @return The view name of the page displaying applicant details for Passport
	 *         Officer.
	 */
	@RequestMapping(value = "/viewApplicant")
	public String poApproval(Model model) {
		List<Applicant_details> listApplicant = service.listStatus(1);
		model.addAttribute("listApplicant", listApplicant);
		String description = null;
		model.addAttribute("description", description);
		return "viewApplicant";
	}

	/**
	 * Displays the list of applicants' details for Police Officer approval.
	 * 
	 * @param model The model to store attributes.
	 * @return The view name of the page displaying applicant details for Police
	 *         Officer.
	 */
	@RequestMapping(value = "/viewApplicant2")
	public String policeApproval(Model model) {
		List<Applicant_details> listApplicant = service.listStatus(2);
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
	 * @param model The model to store attributes.
	 * @return The view name for the existing applicant login page.
	 */
	@RequestMapping(value = "/existingApplicantLogin")
	public String existingApplicationLogin(Model model) {

		Login_details login_details = new Login_details();
		model.addAttribute("login_details", login_details);

		return "existingApplicantLogin";

	}

	/**
	 * Displays the status table of the applicant.
	 *
	 * @param model             The model to store attributes.
	 * @param applicant_details The applicant details to track the status.
	 * @return The view name for the status table page.
	 */
	@PostMapping(value = "/trackStatusTable")
	public String trackStatusTable(Model model,
			@ModelAttribute("applicant_details") Applicant_details applicant_details) {

		Applicant_details applicant_details1 = service.trackDetails(applicant_details.getApplication_id(),
				applicant_details.getDob());

		try {
			int flag1 = applicant_details1.getFlag();
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

			applicant_details1.getApplication_id();
			model.addAttribute("applicant_details", applicant_details1);
			model.addAttribute("flag", flag1);
			return "trackStatusTable";

		} catch (NullPointerException e) {
			System.out.println("Null Pointer exception");
			return "redirect:/loginTrackStatus";
		}
	}

	/**
	 * Displays the new applicant page.
	 *
	 * @param model The model to store attributes.
	 * @return The view name for the new applicant page.
	 */
	@RequestMapping(value = "/newapplicant")
	public String newapplicant(Model model) {

		Applicant_details applicant_details = new Applicant_details();
		model.addAttribute("applicant_details", applicant_details);
		return "newapplicant";

	}

	/**
	 * Saves the new applicant's details and sends an email with appointment
	 * details.
	 *
	 * @param model             The model to store attributes.
	 * @param applicant_details The new applicant's details.
	 * @param login_details     The login details.
	 * @return The view name for the login page.
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(Model model, @ModelAttribute("applicant_details") Applicant_details applicant_details,
			@ModelAttribute("login_details") Login_details login_details) {
		String str = null;
		Applicant_details ad1 = service.findBySsn(applicant_details.getSsn_no());
		try {
			if (ad1 != null) {
				model.addAttribute("logError", "logError");
				str = "newapplicant";
			} else {
				applicant_details.setFlag(1);
				service.save(applicant_details);

				Mail mail = new Mail();
				mail.setMailFrom("passportstatustracking@gmail.com");
				mail.setMailTo(applicant_details.getEmail_id());
				mail.setMailSubject("User Details About Application");
				mail.setMailContent(
						"Application Id : " + applicant_details.getApplication_id() + "\nAppointment Date : "
								+ applicant_details.getAppointment_date());

				mailService.sendEmail(mail);

				str = "login";
				System.out.println("Null Pointer exception");
			}
		} catch (NullPointerException e) {

			throw e;
		}

		return str;
	}

	/**
	 * Saves the existing user's (applicant) appointment details.
	 *
	 * @param model             The model to store attributes.
	 * @param applicant_details The existing applicant's details.
	 * @return The view name for the login page.
	 */
	@RequestMapping(value = "/save1", method = RequestMethod.POST)
	public String saveApplication(Model model,
			@ModelAttribute("applicant_details") Applicant_details applicant_details) {

		Applicant_details ad1 = service.findByPassportId(applicant_details.getPassport_id());

		try {
			if (ad1 != null) {
				System.out.println(applicant_details.getPassport_id());
				System.out.println(ad1.getPassport_id());

				ad1.setAddress(applicant_details.getAddress());
				ad1.setEmail_id(applicant_details.getEmail_id());
				ad1.setMobile_no(applicant_details.getMobile_no());
				ad1.setAppointment_date(applicant_details.getAppointment_date());
				ad1.setFlag(1);

				service.save(ad1);
				Applicant_details ap = new Applicant_details();
				model.addAttribute("applicant_details", ap);
				return "login";
			} else {
				Applicant_details ap = new Applicant_details();
				model.addAttribute("applicant_details", ap);
				model.addAttribute("logError", "logError");
				return "renewApplication";
			}

		} catch (NullPointerException e) {
			System.out.println("null pointer exception");
			throw e;

		}
	}

	/**
	 * Displays the login page for existing applicant users.
	 *
	 * @param model The model to store attributes.
	 * @return The view name for the existing applicant login page.
	 */
	@RequestMapping(value = "/loginTrackStatus")
	public String loginTrackStatus(Model model) {
		Applicant_details applicant_details = new Applicant_details();
		model.addAttribute("applicant_details", applicant_details);
		return "loginTrackStatus";

	}

	/**
	 * Displays the registration page for new users (applicants).
	 *
	 * @param model The model to store attributes.
	 * @return The view name for the new user registration page.
	 */
	@RequestMapping(value = "/newUserRegister")
	public String newUserRegister(Model model) {

		Login_details login_details = new Login_details();
		model.addAttribute("login_details", login_details);
		return "newUserRegister";

	}

	/**
	 * Sends the user's password to their registered email.
	 *
	 * @param model         The model to store attributes.
	 * @param login_details The login details containing the email for password
	 *                      retrieval.
	 * @return The view name for the login page or an error page if email not found.
	 */
	@RequestMapping(value = "/sendPassword", method = RequestMethod.POST)
	public String sendPass(Model model, @ModelAttribute("login_details") Login_details login_details) {
		Login_details login_details1 = service1.findByEmailId(login_details.getEmail_id());
		try {

			login_details1.getApplication_id();
			Mail mail = new Mail();
			mail.setMailFrom("passportstatustracking@gmail.com");
			mail.setMailTo(login_details.getEmail_id());
			mail.setMailSubject("User Details About Password");
			mail.setMailContent("Application Id : " + login_details1.getApplication_id() + "\nPassword : "
					+ login_details1.getPassword());

			mailService.sendEmail(mail);

			Login_details login_details2 = new Login_details();
			model.addAttribute("login_details", login_details2);
			return "existingApplicantLogin";

		} catch (NullPointerException e) {

			System.out.println("Null Pointer Exception");
			Applicant_details ap = new Applicant_details();
			model.addAttribute("applicant_details", ap);
			return "redirect:/emailForgot";

		}
	}

	/**
	 * Sends an OTP to the user's registered email for verification.
	 *
	 * @param model         The model to store attributes.
	 * @param login_details The login details containing the email for OTP sending.
	 * @return The view name for the OTP verification page or an error page if email
	 *         not found.
	 */
	@RequestMapping(value = "/sendOtp", method = RequestMethod.POST)
	public String sendOtp(Model model, @ModelAttribute("login_details") Login_details login_details) {
		Login_details login_details1 = service1.findByEmailId(login_details.getEmail_id());
		try {
			randomPIN = (int) (Math.random() * 9000) + 1000;
			System.out.println(randomPIN);
			emailId = login_details1.getEmail_id();
			password = login_details1.getPassword();
			applicationId = login_details1.getApplication_id();
			Mail mail = new Mail();
			mail.setMailFrom("passportstatustracking@gmail.com");
			mail.setMailTo(login_details1.getEmail_id());
			mail.setMailSubject("Account Verification - OTP");
			mail.setMailContent("Please enter the otp for verification.....\n\n Your One Time Password is : "
					+ randomPIN + " \n\nThank You.....");

			mailService.sendEmail(mail);
			int num = 0;
			Applicant_details applicant_details1 = new Applicant_details();

			model.addAttribute("applicant_details", applicant_details1);

			return "otp2";

		} catch (NullPointerException e) {
			System.out.println("Null Pointer Exception");
			Applicant_details ap = new Applicant_details();
			model.addAttribute("applicant_details", ap);
			return "redirect:/emailForgot";
		}
	}

	/**
	 * Retrieves existing applicant details for renewal.
	 *
	 * @param model          The model to store attributes.
	 * @param login_details1 The login details containing the email and password for
	 *                       validation.
	 * @return The view name for the application renewal page or the login page if
	 *         credentials are invalid.
	 */
	@RequestMapping(value = "/renewApplicant", method = RequestMethod.POST)
	public String renewApp(Model model, @ModelAttribute("login_details") Login_details login_details1) {
		Login_details login_details2 = service1.findByEmailIdandPassword(login_details1.getEmail_id(),
				login_details1.getPassword());
		try {
			login_details2.getEmail_id();
			Applicant_details applicant_details = new Applicant_details();
			model.addAttribute("applicant_details", applicant_details);

			return "renewApplication";
		} catch (NullPointerException e) {
			return "redirect:/existingApplicantLogin";
		}
	}

	/**
	 * Displays the forgot password page.
	 *
	 * @param model The model to store attributes.
	 * @return The view name for the forgot password page.
	 */
	@RequestMapping(value = "/emailForgot")
	public String forgot(Model model) {
		Login_details ad = new Login_details();
		model.addAttribute("login_details", ad);
		return "emailForgot";
	}

	/**
	 * Saves the new user (applicant) details and sends an OTP to the registered
	 * email.
	 *
	 * @param model         The model to store attributes.
	 * @param login_details The login details containing the email for registration.
	 * @return The view name for OTP verification page or an error page if
	 *         registration fails.
	 */
	@RequestMapping(value = "/save2", method = RequestMethod.POST)
	public String saveNewUser(Model model, @ModelAttribute("login_details") Login_details login_details) {

		String str = null;
		int flag = 0;
		Login_details ld1 = service1.findEmail(login_details.getEmail_id());
		try {
			ld1.getEmail_id();
			Login_details ld = new Login_details();
			model.addAttribute("login_details", ld);
			str = "newUserRegister";
		} catch (NullPointerException e) {
			flag = 1;
			System.out.println("Null Pointer exception");
		}
		if (flag == 1) {
			randomPIN = (int) (Math.random() * 9000) + 1000;
			System.out.println(randomPIN);
			Mail mail = new Mail();
			mail.setMailFrom("passportstatustracking@gmail.com");
			mail.setMailTo(login_details.getEmail_id());
			mail.setMailSubject("Account Verification - OTP");
			mail.setMailContent("Please enter the otp for verification.....\n\n Your One Time Password is : "
					+ randomPIN + " \n\nThank You.....");

			int status = mailService.sendEmail(mail);
			if (status == 1) {

				service1.save(login_details);
				Applicant_details ap = new Applicant_details();
				model.addAttribute("applicant_details", ap);
				str = "otp1";
			} else {
				Login_details ld = new Login_details();
				model.addAttribute("login_details", ld);
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
	 * @param model             The model to store attributes.
	 * @param applicant_details The applicant details containing the entered OTP.
	 * @return The view name for the new applicant registration page or an error
	 *         page if OTP is incorrect.
	 */
	@PostMapping(value = "/compareOTP")
	public String compareOTP(Model model, @ModelAttribute("applicant_details") Applicant_details applicant_details) {
		if (applicant_details.getOtp() == randomPIN) {
			Applicant_details ad = new Applicant_details();
			model.addAttribute("applicant_details", ad);
			return "/newApplicant";
		} else {
			Applicant_details ad = new Applicant_details();
			model.addAttribute("applicant_details", ad);
			model.addAttribute("logError", "logError");
			return "otp1";
		}
	}

	/**
	 * Compares the entered OTP with the generated OTP for existing applicant
	 * password retrieval.
	 *
	 * @param model             The model to store attributes.
	 * @param login_details     The login details containing the entered OTP.
	 * @param applicant_details The applicant details containing the entered OTP.
	 * @return The view name for the login page or an error page if OTP is
	 *         incorrect.
	 */
	@PostMapping(value = "/compareOTPForgot")
	public String compareOTPForgot(Model model, @ModelAttribute("login_details") Login_details login_details,
			@ModelAttribute("applicant_details") Applicant_details applicant_details) {
		if (applicant_details.getOtp() == randomPIN) {
			Mail mail = new Mail();
			mail.setMailFrom("passportstatustracking@gmail.com");
			mail.setMailTo(emailId);
			mail.setMailSubject("User Details About Password");
			mail.setMailContent("Application Id : " + applicationId + "\nPassword : " + password);

			mailService.sendEmail(mail);

			Login_details ad = new Login_details();
			model.addAttribute("login_details", ad);
			return "/existingApplicantLogin";
		} else {
			Login_details ad = new Login_details();
			model.addAttribute("login_details", ad);
			model.addAttribute("logError", "logError");
			return "otp2";
		}
	}

	/**
	 * Handles the approval of an application by the Passport Officer.
	 *
	 * @param model             The model to store attributes.
	 * @param applicationId     The ID of the application to be approved.
	 * @param applicant_details The applicant details containing the application ID.
	 * @return The view name for the applicant details page.
	 */
	@GetMapping(value = "/approve/{applicationId}/po")
	public String approveRequestPO(Model model, @PathVariable long applicationId,
			@ModelAttribute("applicant_details") Applicant_details applicant_details) {

		Applicant_details ap = service.update(applicationId);
		Mail mail = new Mail();
		mail.setMailFrom("passportstatustracking@gmail.com");
		mail.setMailTo(ap.getEmail_id());
		mail.setMailSubject("Passport Application APPROVED by Passport Officer");
		mail.setMailContent(
				"Your Application is Approved by Passport Officer.\n\n Application is going to the Police department for verification. \n\nThank You.....");
		mailService.sendEmail(mail);

		return "redirect:/viewApplicant";
	}

	/**
	 * Handles the request when Passport Officer (PO) rejects an application.
	 *
	 * @param model             The model to store attributes.
	 * @param applicationId     The ID of the application to be rejected.
	 * @param applicant_details The applicant details model attribute.
	 * @return The view name to redirect after the rejection process.
	 */
	@RequestMapping(value = { "/reject/{applicationId}/po" })
	public String RejectRequestPORequest(Model model, @PathVariable long applicationId,
			@ModelAttribute("applicant_details") Applicant_details applicant_details) {
		Applicant_details ap = service.update1(applicationId);
		Mail mail = new Mail();
		mail.setMailFrom("passportstatustracking@gmail.com");
		mail.setMailTo(ap.getEmail_id());
		mail.setMailSubject("Passport Application is REJECTED by Passport Officer");
		mail.setMailContent(
				"Your Application is rejected by Passport Officer.\n\nPlease apply again later. \n\nThank You.....");
		mailService.sendEmail(mail);
		service.deletePoRequest(applicationId);
		return "redirect:/viewApplicant";
	}

	/**
	 * Handles the request when Police Officer (PO) approves an application.
	 *
	 * @param model             The model to store attributes.
	 * @param applicationId     The ID of the application to be approved.
	 * @param applicant_details The applicant details model attribute.
	 * @return The view name to redirect after the approval process.
	 */
	@GetMapping(value = "/approve/{applicationId}/police")
	public String approveRequestPolice(Model model, @PathVariable long applicationId,
			@ModelAttribute("applicant_details") Applicant_details applicant_details) {
		Applicant_details ap = service.updatePolice(applicationId);
		service.pdfCreation(applicationId);
		Mail mail = new Mail();
		mail.setMailFrom("passportstatustracking@gmail.com");
		mail.setMailTo(ap.getEmail_id());
		mail.setMailSubject("Passport Application Approved by Police and E-Passport");
		mail.setMailContent("Your Application is  Approved by Police Officer.. \n\nThank You! Have a great day.");
		mailService.sendEmail(mail, ap.getApplication_id());

		return "redirect:/viewApplicant2";
	}

	/**
	 * Handles the request when Police Officer (PO) rejects an application.
	 *
	 * @param model             The model to store attributes.
	 * @param applicationId     The ID of the application to be rejected.
	 * @param applicant_details The applicant details model attribute.
	 * @return The view name to redirect after the rejection process.
	 */
	@RequestMapping(value = { "/reject/{applicationId}/police" })
	public String RejectRequestPoliceRequest(Model model, @PathVariable long applicationId,
			@ModelAttribute("applicant_details") Applicant_details applicant_details) {
		Applicant_details ap = service.update1(applicationId);
		Mail mail = new Mail();
		mail.setMailFrom("passportstatustracking@gmail.com");
		mail.setMailTo(ap.getEmail_id());
		mail.setMailSubject("Passport Application Rejected by Police Officer");
		mail.setMailContent(
				"Your Application is rejected by Passport Officer.\n\n Please apply again later. \n\nThank You.....");
		mailService.sendEmail(mail);
		service.deletePoliceRequest(applicationId);
		return "redirect:/viewApplicant2";
	}

}
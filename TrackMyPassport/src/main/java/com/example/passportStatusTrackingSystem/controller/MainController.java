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

	
	//login page will be displayed.
	@RequestMapping(value = "/login")
	public String homeScreen() {
		return "login";
	}

	// th:href="@{/reject/{applicationId}/police(applicationId=${applicant_details.application_id})}"

	@RequestMapping(value = "/newapplicant")
	public String newapplicant(Model model) {

		Applicant_details applicant_details = new Applicant_details();
		model.addAttribute("applicant_details", applicant_details);
		return "newapplicant";

	}

	//User will enter all the information and book an appointment. After Submit will return to login page.
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(Model model, @ModelAttribute("applicant_details") Applicant_details applicant_details,
			@ModelAttribute("login_details") Login_details login_details) {
		String str = null;
		Applicant_details ad1 = service.findBySsn(applicant_details.getSsn_no());
		try {
			ad1.getSsn_no();
			model.addAttribute("logError", "logError");
			str = "newapplicant";
		} catch (NullPointerException e) {

			applicant_details.setFlag(1);
			service.save(applicant_details);

			Mail mail = new Mail();
			mail.setMailFrom("passportstatustracking@gmail.com");
			mail.setMailTo(applicant_details.getEmail_id());
			mail.setMailSubject("User Details About Application");
			mail.setMailContent("Application Id : " + applicant_details.getApplication_id() + "\nAppointment Date : "
					+ applicant_details.getAppointment_date());

			mailService.sendEmail(mail);

			str = "login";
			System.out.println("Null Pointer exception");
		}

		return str;
	}

	//Login details to create account.
	@RequestMapping(value = "/newUserRegister")
	public String newUserRegister(Model model) {

		Login_details login_details = new Login_details();
		model.addAttribute("login_details", login_details);
		return "newUserRegister";

	}

	@RequestMapping(value = "/sendOtp", method = RequestMethod.POST)
	public String sendOtp(Model model, @ModelAttribute("login_details") Login_details login_details) {
		Login_details login_details1 = service1.findByEmailId(login_details.getEmail_id());
		try {
			randomPIN = (int) (Math.random() * 9000) + 1000;
			System.out.println(randomPIN);
			emailId = login_details1.getEmail_id();
			password = login_details1.getPassword();
			applicationId = login_details1.getApplication_id();
			// login_details1.getApplication_id();
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


	//OTP web page
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

	

}

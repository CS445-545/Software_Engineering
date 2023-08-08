package com.example.passportStatusTrackingSystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.ui.ConcurrentModel;

import com.example.passportStatusTrackingSystem.model.applicantDetails;
import com.example.passportStatusTrackingSystem.model.loginDetails;
import com.example.passportStatusTrackingSystem.service.applicantDetailsService;
import com.example.passportStatusTrackingSystem.service.loginDetailsService;
import com.example.passportStatusTrackingSystem.service.mailService;

public class mainControllerTest {

    @InjectMocks
    private mainController mainController;

    @Mock
    private applicantDetailsService applicantDetailsService;

    @Mock
    private loginDetailsService loginDetailsServices;

    @Mock
    private mailService mailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginTrackStatus() {
        Model model = mock(Model.class);
        String result = mainController.loginTrackStatus(model);
        assertEquals("loginTrackStatus", result);
        verify(model, times(1)).addAttribute(eq("applicantDetails"), any(applicantDetails.class));
    }

    @Test
    void testNewUserRegister() {
        Model model = mock(Model.class);
        String result = mainController.newUserRegister(model);
        assertEquals("newUserRegister", result);
        verify(model, times(1)).addAttribute(eq("loginDetails"), any(loginDetails.class));
    }

    @Test
    void testPoApproval() {
        Model model = mock(Model.class);
        List<applicantDetails> mockApplicantList = new ArrayList<>();
        when(applicantDetailsService.listStatus(1)).thenReturn(mockApplicantList);
        String result = mainController.poApproval(model);
        assertEquals("viewApplicant", result);
        verify(model, times(1)).addAttribute(eq("listApplicant"), eq(mockApplicantList));
        verify(model, times(1)).addAttribute(eq("description"), isNull());
    }

    @Test
    void testPoliceApproval() {
        Model model = mock(Model.class);
        List<applicantDetails> mockApplicantList = new ArrayList<>();
        when(applicantDetailsService.listStatus(2)).thenReturn(mockApplicantList);
        String result = mainController.policeApproval(model);
        assertEquals("viewApplicant2", result);
        verify(model, times(1)).addAttribute(eq("listApplicant"), eq(mockApplicantList));
        verify(model, times(1)).addAttribute(eq("number"), eq(3));
    }

    @Test
    void testNewapplicant() {
        Model model = mock(Model.class);
        String result = mainController.newapplicant(model);
        assertEquals("newapplicant", result);
        verify(model, times(1)).addAttribute(eq("applicantDetails"), any(applicantDetails.class));
    }

    @Test
    void testSendOtp_EmailNotFound() {
        Model model = mock(Model.class);
        loginDetails mockLoginDetails = new loginDetails();
        applicantDetails mockApplicantDetails= new applicantDetails();
        mockLoginDetails.setEmail_id("nonexistent@example.com"); // Replace with a non-existent email
        mockApplicantDetails.setApplication_id(1);
        when(loginDetailsServices.findByEmailId(mockLoginDetails.getEmail_id())).thenReturn(null);
        String result = mainController.sendOtp(model, mockLoginDetails, mockApplicantDetails);
        assertEquals("redirect:/emailForgot", result);
        verify(model, times(1)).addAttribute(eq("applicantDetails"), any(applicantDetails.class));
    }

    @Test
    public void testHomeScreen() {
        mainController controller = new mainController();
        String result = controller.homeScreen();
        assertEquals("login", result);
    }

    @Test
    public void testLoginAdmin() {
        mainController controller = new mainController();
        String result = controller.loginAdmin();
        assertEquals("loginAdmin", result);
    }

    @Test
    public void testWelcomeAdmin() {
        mainController controller = new mainController();
        String result = controller.welcomeAdmin();
        assertEquals("welcomeAdmin", result);
    }

    @Test
    public void testWelcomeApplicant() {
        mainController controller = new mainController();
        String result = controller.welcomeApplicant();
        assertEquals("welcomeApplicant", result);
    }

    @Test
    public void testExistingApplicationLogin() {
        Model model = mock(Model.class);
        mainController controller = new mainController();
        String result = controller.existingApplicationLogin(model);
        assertEquals("existingApplicantLogin", result);
        verify(model).addAttribute(eq("loginDetails"), any(loginDetails.class));
    }

    @Test
    public void testInvalidExistingApplicationLogin() {
        Model model = new ConcurrentModel();
        mainController controller = new mainController();
        String result = controller.existingApplicationLogin(model);
        assertEquals("existingApplicantLogin", result);
        loginDetails loginDetails = (loginDetails) model.getAttribute("loginDetails");
        assertNull(loginDetails.getUser_name());
        assertNull(loginDetails.getPassword());
    }

    @Test
    void testForgot() {
        Model model = mock(Model.class);
        String result = mainController.forgot(model);
        assertEquals("emailForgot", result);
        verify(model, times(1)).addAttribute(eq("loginDetails"), any(loginDetails.class));
    }
}

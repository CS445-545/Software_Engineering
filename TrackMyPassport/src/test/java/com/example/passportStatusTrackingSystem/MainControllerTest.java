package com.example.passportStatusTrackingSystem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import com.example.passportStatusTrackingSystem.controller.MainController;
import com.example.passportStatusTrackingSystem.model.Login_details;
import com.example.passportStatusTrackingSystem.service.Applicant_detailsService;
import com.example.passportStatusTrackingSystem.service.Login_detailsService;
import com.example.passportStatusTrackingSystem.service.MailService;

public class MainControllerTest {
    @Mock
    private Applicant_detailsService applicantDetailsService;

    @Mock
    private MailService mailService;

    @InjectMocks
    private MainController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Login_detailsService loginDetailsServicel;

    /**
     * Test for the homeScreen() method in MainController.
     */
    @Test
    public void testHomeScreen() {
        MainController controller = new MainController();
        String result = controller.homeScreen();
        assertEquals("login", result);
    }

    /**
     * Test for the loginAdmin() method in MainController.
     */
    @Test
    public void testLoginAdmin() {
        MainController controller = new MainController();
        String result = controller.loginAdmin();
        assertEquals("loginAdmin", result);
    }

    /**
     * Test for the welcomeApplicant() method in MainController.
     */
    @Test
    public void testWelcomeApplicant() {
        MainController controller = new MainController();
        String result = controller.welcomeApplicant();
        assertEquals("welcomeApplicant", result);
    }

    /**
     * Test for the existingApplicationLogin() method in MainController.
     */
    @Test
    public void testExistingApplicationLogin() {
        Model model = mock(Model.class);
        MainController controller = new MainController();
        String result = controller.existingApplicationLogin(model);
        assertEquals("existingApplicantLogin", result);

        // Verify that the model has the "login_details" attribute
        verify(model).addAttribute(eq("login_details"), any(Login_details.class));
    }

}
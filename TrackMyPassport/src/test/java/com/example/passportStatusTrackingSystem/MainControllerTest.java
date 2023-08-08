package com.example.passportStatusTrackingSystem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareConcurrentModel;

import com.example.passportStatusTrackingSystem.controller.MainController;
import com.example.passportStatusTrackingSystem.model.Applicant_details;
import com.example.passportStatusTrackingSystem.model.Login_details;
import com.example.passportStatusTrackingSystem.service.Applicant_detailsService;
import com.example.passportStatusTrackingSystem.service.Login_detailsService;
import com.example.passportStatusTrackingSystem.service.MailService;

public class MainControllerTest {

    private Applicant_detailsService applicantDetailsService;

    private Model model;

    private MailService mailService;

    private MainController controller;

    private Applicant_details tempApplicant;

    private Login_details tempLoginDetails;

    private Applicant_detailsService service;
    
    private Applicant_details existingApplicant;
    
    private long testPassportId = 12345678;
    @MockBean
    private Login_detailsService loginDetailsServicel;

    @BeforeEach
    public void setUp() {
        controller = new MainController();
        model = new BindingAwareConcurrentModel();
        tempApplicant = new Applicant_details();
        tempApplicant.setApplication_id(1);
        tempApplicant.setDob(Date.valueOf("1990-01-01")); // assuming date format "yyyy-MM-dd"

        tempLoginDetails = new Login_details();
        service = new Applicant_detailsService();
    }

    @Test
    public void testHomeScreen() {
        
        String result = controller.homeScreen();
        assertEquals("login", result);
    }

    @Test
    public void testLoginAdmin() {
        
        String result = controller.loginAdmin();
        assertEquals("loginAdmin", result);
    }
    
    @Test
    public void testPoApproval() {
        String result = controller.poApproval(model);
        assertEquals("viewApplicant", result);

        // Verify that the model has the "listApplicant" attribute and it is not null
        assertTrue(model.containsAttribute("listApplicant"));
        assertNotNull(model.getAttribute("listApplicant"));
        assertTrue(model.getAttribute("listApplicant") instanceof List);

        // Verify that the model has the "description" attribute and it is null
        assertTrue(model.containsAttribute("description"));
        assertNull(model.getAttribute("description"));
    }
    @Test
    public void testPoliceApproval() {
        String result = controller.policeApproval(model);
        assertEquals("viewApplicant2", result);

        // Verify that the model has the "listApplicant" attribute
        assertTrue(model.containsAttribute("listApplicant"));
        assertNotNull(model.getAttribute("listApplicant"));
        assertTrue(model.getAttribute("listApplicant") instanceof List);

        // Verify that the model has the "number" attribute
        assertTrue(model.containsAttribute("number"));
        assertEquals(3, model.getAttribute("number"));
    }   
    @Test
    public void testWelcomeApplicant() {
        String result = controller.welcomeApplicant();
        assertEquals("welcomeApplicant", result);
    }
    /**
     * Test for the existingApplicationLogin() method in MainController.
     */
    @Test
    public void testExistingApplicationLogin() {
        String result = controller.existingApplicationLogin(model);
        assertEquals("existingApplicantLogin", result);

        // Verify that the model has the "login_details" attribute
        verify(model).addAttribute(eq("login_details"), any(Login_details.class));
    }

    @Test
    public void testTrackStatusTable() {
        String result = controller.trackStatusTable(model, tempApplicant);

        // Validate view name
        assertTrue(result.equals("trackStatusTable") || result.equals("redirect:/loginTrackStatus"));

        // If the result is "trackStatusTable", check model attributes
        if (result.equals("trackStatusTable")) {
            assertTrue(model.containsAttribute("applicant_details"));
            Applicant_details returnedApplicant = (Applicant_details) model.getAttribute("applicant_details");
            assertNotNull(returnedApplicant);
            
            assertTrue(model.containsAttribute("flag"));
            int returnedFlag = (int) model.getAttribute("flag");
            
            // Validate the flag transformation logic using a switch statement
            int originalFlag = returnedApplicant.getFlag();
            switch (originalFlag) {
                case 1:
                    assertEquals(2, returnedFlag);
                    break;
                case 2:
                    assertEquals(3, returnedFlag);
                    break;
                case 3:
                    assertEquals(6, returnedFlag);
                    break;
                case 4:
                    assertEquals(2, returnedFlag);
                    break;
                case 5:
                    assertEquals(3, returnedFlag);
                    break;
                default:
                    fail("Unexpected flag value: " + originalFlag);
                    break;
            }
        }
    }

    @Test
    public void testNewapplicant() {
        String result = controller.newapplicant(model);

        assertEquals("newapplicant", result);
        assertTrue(model.containsAttribute("applicant_details"));
        assertTrue(model.getAttribute("applicant_details") instanceof Applicant_details);
    }

    @Test
    public void testSaveProduct() {
        // Mocking service.findBySsn to return a value. 
        // In a real-world scenario, we'd use a mock framework like Mockito.
        Applicant_details existingApplicant = new Applicant_details();
        when(service.findBySsn(anyLong())).thenReturn(existingApplicant);

        String result = controller.saveProduct(model, tempApplicant, tempLoginDetails);
        
        if(existingApplicant != null) {
            assertEquals("newapplicant", result);
            assertTrue(model.containsAttribute("logError"));
        }
    }
    @Test
    public void testSaveApplicationForExistingUser() {
        tempApplicant.setPassport_id(testPassportId);
        
        String result = controller.saveApplication(model, tempApplicant);
        
        assertEquals("login", result);
        verify(model).addAttribute(eq("applicant_details"), any(Applicant_details.class));
    }

    @Test
    public void testSaveApplicationForNonExistingUser() {
        tempApplicant.setPassport_id(87654321); // Different passport id
        
        String result = controller.saveApplication(model, tempApplicant);
        
        assertEquals("renewApplication", result);
        verify(model).addAttribute(eq("applicant_details"), any(Applicant_details.class));
        verify(model).addAttribute("logError", "logError");
    }

    @Test
    public void testLoginTrackStatus() {
        String result = controller.loginTrackStatus(model);
    
        assertEquals("loginTrackStatus", result);
        verify(model).addAttribute(eq("applicant_details"), any(Applicant_details.class));
    }

    @Test
    public void testNewUserRegister() {
        String result = controller.newUserRegister(model);
        
        assertEquals("newUserRegister", result);
        verify(model).addAttribute(eq("login_details"), any(Login_details.class));
    }
    

}
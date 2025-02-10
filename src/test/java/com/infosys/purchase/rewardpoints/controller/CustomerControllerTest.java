package com.infosys.purchase.rewardpoints.controller;


import static org.mockito.Mockito.*;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.context.MessageSource;
import com.infosys.purchase.rewardpoints.request.CustomerRegisterRequest;
import com.infosys.purchase.rewardpoints.service.CustomerService;
import com.infosys.purchase.rewardpoints.util.ResponseUtil;

public class CustomerControllerTest {

    private CustomerController customerController;
    private CustomerService customerService;
    private ResponseUtil responseUtil;
    private MessageSource messageSource;

    @BeforeEach
    public void setUp() {
        customerService = mock(CustomerService.class);
        responseUtil = mock(ResponseUtil.class);
        messageSource = mock(MessageSource.class);
        customerController = new CustomerController();
        customerController.customerService = customerService;
        customerController.responseUtil = responseUtil;
        customerController.messageSource = messageSource;
    }

    @Test
    public void registerCustomerSuccessfully() {
        CustomerRegisterRequest request = new CustomerRegisterRequest();
        when(messageSource.getMessage("customer.register.success", null, Locale.getDefault())).thenReturn("Success");
        when(responseUtil.createResponse("Success", null)).thenAnswer(invocation -> ResponseEntity.ok("Success"));

        ResponseEntity<?> response = customerController.register(request);

        assertEquals(ResponseEntity.ok("Success"), response);
        verify(customerService).register(request);
    }

    @Test
    public void registerCustomerFails() {
        CustomerRegisterRequest request = new CustomerRegisterRequest();
        doThrow(new RuntimeException()).when(customerService).register(request);
        when(messageSource.getMessage("customer.register.failed", null, Locale.getDefault())).thenReturn("Failed");
        when(responseUtil.createErrorResponse("Failed", null)).thenAnswer(invocation -> ResponseEntity.status(500).body("Failed"));

        ResponseEntity<?> response = customerController.register(request);

        assertEquals(ResponseEntity.status(500).body("Failed"), response);
        verify(customerService).register(request);
    }

    @Test
    public void loginCustomerSuccessfully() {
        // Add implementation when login logic is available
    }

    @Test
    public void logoutCustomerSuccessfully() {
        // Add implementation when logout logic is available
    }
}

package com.infosys.purchase.rewardpoints.controller;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Locale;
import com.infosys.purchase.rewardpoints.request.CustomerTransactionRequest;
import com.infosys.purchase.rewardpoints.request.CustomerTransactionDetailsRequest;
import com.infosys.purchase.rewardpoints.response.CustomerTransactionResponse;
import com.infosys.purchase.rewardpoints.service.CustomerTransactionService;
import com.infosys.purchase.rewardpoints.util.ResponseUtil;

public class CustomerTransactionControllerTest {

    private CustomerTransactionController customerTransactionController;
    private CustomerTransactionService customerTransactionService;
    private ResponseUtil responseUtil;
    private MessageSource messageSource;

    @BeforeEach
    public void setUp() {
        customerTransactionService = mock(CustomerTransactionService.class);
        responseUtil = mock(ResponseUtil.class);
        messageSource = mock(MessageSource.class);
        customerTransactionController = new CustomerTransactionController();
        customerTransactionController.customerTransactionService = customerTransactionService;
        customerTransactionController.responseUtil = responseUtil;
        customerTransactionController.messageSource = messageSource;
    }

    @Test
    public void getTransactionSuccessfully() {
        CustomerTransactionRequest request = new CustomerTransactionRequest();
        CustomerTransactionResponse response = new CustomerTransactionResponse(20, 101, "ORD_03_100225", 167.0, LocalDate.of(2025, 02, 10));
        when(customerTransactionService.get(request)).thenReturn(response);
        when(responseUtil.createResponse(null, response)).thenAnswer(invocation -> ResponseEntity.ok(response));

        ResponseEntity<?> result = customerTransactionController.get(request);

        assertEquals(ResponseEntity.ok(response), result);
        verify(customerTransactionService).get(request);
    }

    @Test
    public void getTransactionFails() {
        CustomerTransactionRequest request = new CustomerTransactionRequest();
        when(customerTransactionService.get(request)).thenThrow(new RuntimeException());
        when(messageSource.getMessage("transaction.get.failed", null, Locale.getDefault())).thenReturn("Failed");
        when(responseUtil.createErrorResponse("Failed", null)).thenAnswer(invocation -> ResponseEntity.status(500).body("Failed"));

        ResponseEntity<?> result = customerTransactionController.get(request);

        assertEquals(ResponseEntity.status(500).body("Failed"), result);
        verify(customerTransactionService).get(request);
    }

    @Test
    public void addTransactionSuccessfully() {
        CustomerTransactionDetailsRequest request = new CustomerTransactionDetailsRequest();
        Integer transactionId = 1;
        when(customerTransactionService.add(request)).thenReturn(transactionId);
        when(messageSource.getMessage("transaction.add.success", null, Locale.getDefault())).thenReturn("Success");
        when(responseUtil.createResponse("Success for transaction id: 1", null)).thenAnswer(invocation -> ResponseEntity.ok("Success for transaction id: 1"));

        ResponseEntity<?> result = customerTransactionController.add(request);

        assertEquals(ResponseEntity.ok("Success for transaction id: 1"), result);
        verify(customerTransactionService).add(request);
    }

    @Test
    public void addTransactionFails() {
        CustomerTransactionDetailsRequest request = new CustomerTransactionDetailsRequest();
        when(customerTransactionService.add(request)).thenThrow(new RuntimeException());
        when(messageSource.getMessage("transaction.add.failed", null, Locale.getDefault())).thenReturn("Failed");
        when(responseUtil.createErrorResponse("Failed", null)).thenAnswer(invocation -> ResponseEntity.status(500).body("Failed"));

        ResponseEntity<?> result = customerTransactionController.add(request);

        assertEquals(ResponseEntity.status(500).body("Failed"), result);
        verify(customerTransactionService).add(request);
    }

    @Test
    public void editTransactionSuccessfully() {
        CustomerTransactionDetailsRequest request = new CustomerTransactionDetailsRequest();
        Integer transactionId = 1;
        when(customerTransactionService.edit(request)).thenReturn(transactionId);
        when(messageSource.getMessage("transaction.edit.success", null, Locale.getDefault())).thenReturn("Success");
        when(responseUtil.createResponse("Success for transaction id: 1", null)).thenAnswer(invocation -> ResponseEntity.ok("Success for transaction id: 1"));

        ResponseEntity<?> result = customerTransactionController.edit(request);

        assertEquals(ResponseEntity.ok("Success for transaction id: 1"), result);
        verify(customerTransactionService).edit(request);
    }

    @Test
    public void editTransactionFails() {
        CustomerTransactionDetailsRequest request = new CustomerTransactionDetailsRequest();
        when(customerTransactionService.edit(request)).thenThrow(new RuntimeException());
        when(messageSource.getMessage("transaction.edit.failed", null, Locale.getDefault())).thenReturn("Failed");
        when(responseUtil.createErrorResponse("Failed", null)).thenAnswer(invocation -> ResponseEntity.status(500).body("Failed"));

        ResponseEntity<?> result = customerTransactionController.edit(request);

        assertEquals(ResponseEntity.status(500).body("Failed"), result);
        verify(customerTransactionService).edit(request);
    }

    @Test
    public void deleteTransactionSuccessfully() {
        CustomerTransactionRequest request = new CustomerTransactionRequest();
        when(messageSource.getMessage("transaction.delete.success", null, Locale.getDefault())).thenReturn("Success");
        when(responseUtil.createResponse("Success", null)).thenAnswer(invocation -> ResponseEntity.ok("Success"));

        ResponseEntity<?> result = customerTransactionController.delete(request);

        assertEquals(ResponseEntity.ok("Success"), result);
        verify(customerTransactionService).delete(request);
    }

    @Test
    public void deleteTransactionFails() {
        CustomerTransactionRequest request = new CustomerTransactionRequest();
        doThrow(new RuntimeException()).when(customerTransactionService).delete(request);
        when(messageSource.getMessage("transaction.delete.failed", null, Locale.getDefault())).thenReturn("Failed");
        when(responseUtil.createErrorResponse("Failed", null)).thenAnswer(invocation -> ResponseEntity.status(500).body("Failed"));

        ResponseEntity<?> result = customerTransactionController.delete(request);

        assertEquals(ResponseEntity.status(500).body("Failed"), result);
        verify(customerTransactionService).delete(request);
    }
}


package com.infosys.purchase.rewardpoints.service;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import com.infosys.purchase.rewardpoints.events.CustomerTransactionEvent;
import com.infosys.purchase.rewardpoints.model.CustomerTransaction;
import com.infosys.purchase.rewardpoints.repo.CustomerTransactionRepo;
import com.infosys.purchase.rewardpoints.request.CustomerTransactionDetailsRequest;
import com.infosys.purchase.rewardpoints.request.CustomerTransactionRequest;
import com.infosys.purchase.rewardpoints.response.CustomerTransactionResponse;

public class CustomerTransactionServiceTest {

    @Mock
    private CustomerTransactionRepo customerTransactionRepo;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private CustomerTransactionService customerTransactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCustomerTransactionSuccessfully() {
        CustomerTransactionDetailsRequest request = new CustomerTransactionDetailsRequest();
        request.setAmount(100.0);
        request.setCustomerId(1);
        request.setDate(LocalDate.now());
        request.setSpentDetails("Purchase");

        when(customerTransactionRepo.save(any(CustomerTransaction.class))).thenReturn(new CustomerTransaction());

        customerTransactionService.add(request);

        verify(customerTransactionRepo, times(1)).save(any(CustomerTransaction.class));
        verify(applicationEventPublisher, times(1)).publishEvent(any(CustomerTransactionEvent.class));
    }

    @Test
    void addCustomerTransactionWithNullAmount() {
        CustomerTransactionDetailsRequest request = new CustomerTransactionDetailsRequest();
        request.setAmount(null);
        request.setCustomerId(1);
        request.setDate(LocalDate.now());
        request.setSpentDetails("Purchase");

        Assertions.assertThrows(Exception.class, () -> customerTransactionService.add(request));
    }

    @Test
    void editCustomerTransactionSuccessfully() {
        CustomerTransactionDetailsRequest request = new CustomerTransactionDetailsRequest();
        request.setId(1);
        request.setAmount(200.0);
        request.setCustomerId(1);
        request.setDate(LocalDate.now());
        request.setSpentDetails("Updated Purchase");

        when(customerTransactionRepo.save(any(CustomerTransaction.class))).thenReturn(new CustomerTransaction());

        customerTransactionService.edit(request);

        verify(customerTransactionRepo, times(1)).save(any(CustomerTransaction.class));
    }
    
    @Test
    void editCustomerTransactionWithNullRequest() {
        CustomerTransactionDetailsRequest request = null;

        Assertions.assertThrows(Exception.class, () -> customerTransactionService.edit(request));
    }

    @Test
    void getCustomerTransactionSuccessfully() {
        CustomerTransactionRequest request = new CustomerTransactionRequest();
        request.setId(1);

        CustomerTransaction customerTransaction = new CustomerTransaction();
        customerTransaction.setId(1);
        customerTransaction.setCustomerId(1);
        customerTransaction.setSpentDetails("Purchase");
        customerTransaction.setAmount(100.0);
        customerTransaction.setDate(LocalDate.now());

        when(customerTransactionRepo.findById(1)).thenReturn(Optional.of(customerTransaction));

        CustomerTransactionResponse response = customerTransactionService.get(request);

        Assertions.assertEquals(1, response.getId());
        Assertions.assertEquals(1, response.getCustomerId());
        Assertions.assertEquals("Purchase", response.getSpentDetails());
        Assertions.assertEquals(100.0, response.getAmount());
        Assertions.assertEquals(LocalDate.now(), response.getDate());
    }
    
    @Test
    void getCustomerTransactionWithNullRequest() {
    	CustomerTransactionRequest request = null;

        Assertions.assertThrows(Exception.class, () -> customerTransactionService.get(request));
    }

    @Test
    void deleteCustomerTransactionSuccessfully() {
        CustomerTransactionRequest request = new CustomerTransactionRequest();
        request.setId(1);

        doNothing().when(customerTransactionRepo).deleteById(1);

        customerTransactionService.delete(request);

        verify(customerTransactionRepo, times(1)).deleteById(1);
    }
    
    @Test
    void deleteCustomerTransactionWithNullRequest() {
    	CustomerTransactionRequest request = null;

        Assertions.assertThrows(Exception.class, () -> customerTransactionService.delete(request));
    }
}


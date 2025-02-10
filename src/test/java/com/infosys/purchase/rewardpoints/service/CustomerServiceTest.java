package com.infosys.purchase.rewardpoints.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.infosys.purchase.rewardpoints.model.Customer;
import com.infosys.purchase.rewardpoints.repo.CustomerRepo;
import com.infosys.purchase.rewardpoints.request.CustomerRegisterRequest;

@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepo customerRepo;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerCustomerSuccessfully() {
        CustomerRegisterRequest request = new CustomerRegisterRequest();
        request.setName("Thomas Wyne");
        request.setEmail("thomas.wyne@gmail.com");
        request.setMobile("1234567890");

        customerService.register(request);

        verify(customerRepo, times(1)).save(any(Customer.class));
    }
    
    @Test
    void registerCustomerWithNullRequest() {
    	CustomerRegisterRequest request = null;

        Assertions.assertThrows(Exception.class, () ->  customerService.register(request));
    }
}

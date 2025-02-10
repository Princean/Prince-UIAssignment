package com.infosys.purchase.rewardpoints.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.purchase.rewardpoints.model.Customer;
import com.infosys.purchase.rewardpoints.repo.CustomerRepo;
import com.infosys.purchase.rewardpoints.request.CustomerRegisterRequest;

@Service
public class CustomerService {

	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepo customerRepo;
	
	/**
	  * Registers a new customer.
	  *
	  * @param request the customer registration request containing customer details
	  */
	public void register(CustomerRegisterRequest request) {
		
		log.info("Customer service register service - starts");
		Customer customer = null;
		try {
			customer = new Customer();
			LocalDate date = LocalDate.now();
			
			customer.setName(request.getName());
			customer.setEmail(request.getEmail());
			customer.setMobile(request.getMobile());
			customer.setStatus(true);
			customer.setCreatedBy(1);
			customer.setCreatedDate(date);
			
			customerRepo.save(customer);
			
		} catch (Exception e) {
			log.error("Error occurred while registering customer", e);
			throw e;
		}
		log.info("Customer service register service - ends");
		
	}
}

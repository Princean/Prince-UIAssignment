package com.infosys.purchase.rewardpoints.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infosys.purchase.rewardpoints.request.CustomerRegisterRequest;
import com.infosys.purchase.rewardpoints.service.CustomerService;
import com.infosys.purchase.rewardpoints.util.ResponseUtil;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	ResponseUtil responseUtil;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired 
	public CustomerService customerService;
	
	/**
	  * Registers a customer.
	  *
	  * @param request the customer registration request
	  * @return a ResponseEntity indicating the result of the registration
	  */
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody CustomerRegisterRequest request) {
		
		log.info("Customer controller register - starts");
		try {
			customerService.register(request);
		} catch (Exception e) {
			e.printStackTrace();
			return responseUtil
					.createErrorResponse(messageSource.getMessage("customer.register.failed", null, Locale.getDefault()), null);
		}
		log.info("Customer controller register - ends");
		return responseUtil
				.createResponse(messageSource.getMessage("customer.register.success", null, Locale.getDefault()), null);
	}
	
	/**
	 * Login a customer
	 * @return ResponseEntity
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login() {
		try {
			
		} catch (Exception e) {
			
		}
		return null;
	}
	
	/**
	 * Logout a customer
	 * @return ResponseEntity
	 */
	@PostMapping("/logout")
	public ResponseEntity<?> logout() {
		try {
			
		} catch (Exception e) {
			
		}
		return null;
	}
}

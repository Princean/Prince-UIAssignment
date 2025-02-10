package com.infosys.purchase.rewardpoints.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infosys.purchase.rewardpoints.request.CustomerTransactionDetailsRequest;
import com.infosys.purchase.rewardpoints.request.CustomerTransactionRequest;
import com.infosys.purchase.rewardpoints.response.CustomerTransactionResponse;
import com.infosys.purchase.rewardpoints.service.CustomerTransactionService;
import com.infosys.purchase.rewardpoints.util.ResponseUtil;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/customertransactions")
public class CustomerTransactionController {

	private static final Logger log = LoggerFactory.getLogger(CustomerTransactionController.class);
	
	@Autowired
	ResponseUtil responseUtil;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	CustomerTransactionService customerTransactionService;
	
	/**
	  * Get transaction by id.
	  *
	  * @param request the customer transaction request
	  * @return a ResponseEntity containing the transaction details or an error message
	  */
	@GetMapping("/get")
	public ResponseEntity<?> get(@Valid @RequestBody CustomerTransactionRequest request ) {
		
		log.info("Customer Transaction controller get - starts");
		CustomerTransactionResponse customerTransactionResponse = null;
		try {
			customerTransactionResponse = customerTransactionService.get(request);
		} catch (Exception e) {
			e.printStackTrace();
			return responseUtil
					.createErrorResponse(messageSource.getMessage("transaction.get.failed", null, Locale.getDefault()), null);
		}
		log.info("Customer Transaction controller get - ends");
		return responseUtil
				.createResponse(null, customerTransactionResponse);
	}
	
	/**
	  * Add transaction.
	  *
	  * @param request the customer transaction details request
	  * @return a ResponseEntity containing the success message or an error message
	  */
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody CustomerTransactionDetailsRequest request) {
		
		Integer transactionId = null;
		log.info("Customer Transaction controller add - starts");
		
		try {
			transactionId = customerTransactionService.add(request);
		} catch (Exception e) {
			e.printStackTrace();
			return responseUtil
					.createErrorResponse(messageSource
							.getMessage("transaction.add.failed", null, Locale.getDefault()), null);
		}
		log.info("Customer Transaction controller add - ends");
		return responseUtil
				.createResponse(messageSource
						.getMessage("transaction.add.success", null, Locale.getDefault()) 
						+ " for transaction id: " + transactionId, null);
	}
	
	/**
	  * Edit transaction.
	  *
	  * @param request the customer transaction details request
	  * @return a ResponseEntity containing the success message or an error message
	  */
	@PutMapping("/edit")
	public ResponseEntity<?> edit(@Valid @RequestBody CustomerTransactionDetailsRequest request) {

		Integer transactionId = null;
		log.info("Customer Transaction controller edit - starts");
		
		try {
			transactionId = customerTransactionService.edit(request);
		} catch (Exception e) {
			e.printStackTrace();
			return responseUtil
					.createErrorResponse(messageSource
							.getMessage("transaction.edit.failed", null, Locale.getDefault()), null);
		}
		log.info("Customer Transaction controller edit - ends");
		return responseUtil
				.createResponse(messageSource
						.getMessage("transaction.edit.success", null, Locale.getDefault()) 
						+ " for transaction id: " + transactionId, null);
	}
	
	/**
	  * Delete transaction.
	  *
	  * @param request the customer transaction request
	  * @return a ResponseEntity containing the success message or an error message
	  */
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@Valid @RequestBody CustomerTransactionRequest request) {
		
		log.info("Customer Transaction controller delete - starts");
		try {
			customerTransactionService.delete(request);
		} catch (Exception e) {
			e.printStackTrace();
			return responseUtil
					.createErrorResponse(messageSource
							.getMessage("transaction.delete.failed", null, Locale.getDefault()) 
							, null);
		}
		log.info("Customer Transaction controller delete - ends");
		return responseUtil
				.createResponse(messageSource
						.getMessage("transaction.delete.success", null, Locale.getDefault()) 
						, null);
	}
}

package com.infosys.purchase.rewardpoints.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.infosys.purchase.rewardpoints.events.CustomerTransactionEvent;
import com.infosys.purchase.rewardpoints.model.CustomerTransaction;
import com.infosys.purchase.rewardpoints.repo.CustomerTransactionRepo;
import com.infosys.purchase.rewardpoints.request.CustomerTransactionDetailsRequest;
import com.infosys.purchase.rewardpoints.request.CustomerTransactionRequest;
import com.infosys.purchase.rewardpoints.response.CustomerTransactionResponse;

@Service
public class CustomerTransactionService {

	private static final Logger log = LoggerFactory.getLogger(CustomerTransactionService.class);
	
	@Autowired
	private CustomerTransactionRepo customerTransactionRepo;
	
	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;
	
	/**
	  * Adds a new customer transaction.
	  *
	  * @param request the customer transaction details request
	  * @return the ID of the newly added customer transaction
	  */
	public Integer add(CustomerTransactionDetailsRequest request) {
		log.info("Customer transaction service add - starts");
		CustomerTransaction customerTransaction = null;
		try {
				customerTransaction = new CustomerTransaction();
			    customerTransaction.setAmount(request.getAmount());
			    customerTransaction.setCustomerId(request.getCustomerId());
			    customerTransaction.setDate(request.getDate());
			    customerTransaction.setSpentDetails(request.getSpentDetails());
			    
			    customerTransaction = customerTransactionRepo.save(customerTransaction);
			    
			    applicationEventPublisher.publishEvent(new CustomerTransactionEvent(customerTransaction, request.getAmount()));

        } catch (Exception e) {
        	log.error("Error occurred while adding customer transaction", e);
            throw e;
        }
		log.info("Customer transaction service add - ends");
	    return customerTransaction.getId();
		
	}

	/**
	  * Edits an existing customer transaction.
	  *
	  * @param request the customer transaction details request
	  * @return the ID of the edited customer transaction
	  */
	public Integer edit(CustomerTransactionDetailsRequest request) {
		log.info("Customer transaction service add - edit");
		CustomerTransaction customerTransaction = null;
		try {
				customerTransaction = new CustomerTransaction();
				customerTransaction.setId(request.getId());
			    customerTransaction.setAmount(request.getAmount());
			    customerTransaction.setCustomerId(request.getCustomerId());
			    customerTransaction.setDate(request.getDate());
			    customerTransaction.setSpentDetails(request.getSpentDetails());
			    
			    customerTransaction = customerTransactionRepo.save(customerTransaction);

        } catch (Exception e) {
        	log.error("Error occurred while adding customer transaction", e);
            throw e;
        }
		log.info("Customer transaction service edit - ends");
	    return customerTransaction.getId();
		
	}

	/**
	  * Retrieves a customer transaction.
	  *
	  * @param request the customer transaction request
	  * @return the customer transaction response
	  */
	public CustomerTransactionResponse get(CustomerTransactionRequest request) {
		
		log.info("Customer transaction service get - starts");
		CustomerTransaction customerTransaction = null;
		try {
			customerTransaction = customerTransactionRepo.findById(request.getId()).get();
		} catch (Exception e) {
			log.error("Error occurred while getting customer transaction", e);
			throw e;
		}
		log.info("Customer transaction service get - ends");
		return new CustomerTransactionResponse(customerTransaction.getId(), 
				customerTransaction.getCustomerId(), 
				customerTransaction.getSpentDetails(), 
				customerTransaction.getAmount(), 
				customerTransaction.getDate());
		
	}

	/**
	  * Deletes a customer transaction.
	  *
	  * @param request the customer transaction request
	  */
	public void delete(CustomerTransactionRequest request) {
		log.info("Customer transaction service delete - starts");
		try {
			customerTransactionRepo.deleteById(request.getId());
		} catch (Exception e) {
			log.error("Error occurred while deleting customer transaction", e);
			throw e;
		}
		log.info("Customer transaction service delete - ends");
	}
}

package com.infosys.purchase.rewardpoints.events;

import org.springframework.context.ApplicationEvent;

/**
 * Event representing a customer transaction.
 * This event is published when a customer makes a transaction.
 */
public class CustomerTransactionEvent extends ApplicationEvent {

	private static final long serialVersionUID = -6175033561906342695L;
	private Double amount;
	
	/**
     * Constructs a new CustomerTransactionEvent.
     *
     * @param source the object on which the event initially occurred
     * @param amount the amount of the transaction
     */
	public CustomerTransactionEvent(Object source, Double amount) {
		super(source);
		this.amount = amount;
	}
	
	/**
     * Gets the amount of the transaction.
     *
     * @return the transaction amount
     */
	public Double getAmount() {
		return amount;
	}

}

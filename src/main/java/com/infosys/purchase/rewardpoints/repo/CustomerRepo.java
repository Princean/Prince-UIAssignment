package com.infosys.purchase.rewardpoints.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.purchase.rewardpoints.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}

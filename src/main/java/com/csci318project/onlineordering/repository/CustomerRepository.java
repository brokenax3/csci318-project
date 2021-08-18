package com.csci318project.onlineordering.repository;

import com.csci318project.onlineordering.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

package com.csci318project.onlineordering.dao;

import com.csci318project.onlineordering.model.Contact;
import com.csci318project.onlineordering.model.Customer;

import java.util.Optional;

public interface CustomerDao {

    boolean insertCustomer(Customer customer);
    
    boolean updateCustomerByName(String name, Customer customer);
    
    boolean insertCustomerContact(Customer customer, Contact contact);

    boolean updateCustomer(Customer customer, Customer newCustomer);

    Optional<Customer> findCustomerByCompanyName(String name);
}

package com.csci318project.onlineordering.service;

import com.csci318project.onlineordering.dao.CustomerDao;
import com.csci318project.onlineordering.model.Contact;
import com.csci318project.onlineordering.model.Customer;

import java.util.Optional;

public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public boolean addCustomer(Customer customer) {
        // Add Customer into database
        return customerDao.insertCustomer(customer);
    }

    public boolean updateCustomer(Customer customer, Customer newCustomer) {
        // Update Customer information
        return customerDao.updateCustomer(customer, newCustomer);
    }

    public boolean addCustomerContact(Customer customer, Contact contact) {
        // Add Contact
        return customerDao.insertCustomerContact(customer, contact);
    }

    public boolean updateCustomerContact(String name, Customer customer) {
        // Update Customer Contact information
        return customerDao.updateCustomerByName(name, customer);
    }

    public Optional<Customer> lookUpCustomer(String name) {
        // Look up Customer basic info and contact
        return customerDao.findCustomerByCompanyName(name);
    }

}

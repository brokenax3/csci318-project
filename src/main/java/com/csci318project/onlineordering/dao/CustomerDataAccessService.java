package com.csci318project.onlineordering.dao;

import com.csci318project.onlineordering.model.Contact;
import com.csci318project.onlineordering.model.Customer;

import java.util.Optional;

public class CustomerDataAccessService implements CustomerDao{
    @Override
    public boolean insertCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean updateCustomerByName(String name, Customer customer) {
        return false;
    }

    @Override
    public boolean insertCustomerContact(Customer customer, Contact contact) {
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer, Customer newCustomer) {
        return false;
    }

    @Override
    public Optional<Customer> findCustomerByCompanyName(String name) {
        return Optional.empty();
    }
}

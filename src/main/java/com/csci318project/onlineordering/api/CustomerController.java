package com.csci318project.onlineordering.api;

import com.csci318project.onlineordering.model.Contact;
import com.csci318project.onlineordering.model.Customer;
import com.csci318project.onlineordering.repository.ContactRepository;
import com.csci318project.onlineordering.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private final CustomerRepository customerRepository;
    private final ContactRepository contactRepository;

    public CustomerController(CustomerRepository customerRepository, ContactRepository contactRepository) {
        this.customerRepository = customerRepository;
        this.contactRepository = contactRepository;
    }

    @GetMapping()
    List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @PostMapping
    Customer addCustomer(@NonNull @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("{id}")
    Customer getCustomerById(@PathVariable long id) {
        return customerRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping(value = "company")
    List<Customer> getCutomerByCompanyName(@RequestParam("name") String companyName) {
        return customerRepository.findByCompanyName(companyName);
    }

    @PutMapping("{id}")
    Customer updateCustomer(@PathVariable long id, @NonNull @RequestBody Customer newCustomer) {
        Customer customer = customerRepository.findById(id).map(old -> new Customer(id, newCustomer.getCompanyName(), newCustomer.getAddress(), newCustomer.getCountry(), old.getContact()))
                .orElseThrow(RuntimeException::new);
        return customerRepository.save(customer);
    }

    @PutMapping("{id}/contact/{contactId}")
    Customer addCustomerContact(@PathVariable long id, @PathVariable long contactId) {
        Customer customer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        Contact contact = contactRepository.findById(contactId).orElseThrow(RuntimeException::new);
        customer.setContact(contact);
        contact.setCustomer(customer);
        return customerRepository.saveAndFlush(customer);
    }

}

package onlineordering.customerservice.service;

import onlineordering.customerservice.model.Contact;
import onlineordering.customerservice.model.ContactNotFoundException;
import onlineordering.customerservice.model.Customer;
import onlineordering.customerservice.model.CustomerNotFoundException;
import onlineordering.customerservice.repository.ContactRepository;
import onlineordering.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ContactRepository contactRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ContactRepository contactRepository) {
        this.customerRepository = customerRepository;
        this.contactRepository = contactRepository;
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    public List<Customer> getCustomerByCompanyName(String companyName) {
        return customerRepository.findByCompanyName(companyName);
    }

    public Customer updateCustomer(long id, Customer newCustomer) {
        Customer customer = customerRepository.findById(id)
                .map(old -> new Customer(id, newCustomer.getCompanyName(), newCustomer.getAddress(), newCustomer.getCountry(), old.getContact()))
                .orElseThrow(CustomerNotFoundException::new);
        return customerRepository.save(customer);
    }

    public Customer addCustomerContact(long id, long contactId) {
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        Contact contact = contactRepository.findById(contactId).orElseThrow(ContactNotFoundException::new);

        customer.setContact(contact);
        contact.setCustomer(customer);
        return customerRepository.save(customer);
    }
}

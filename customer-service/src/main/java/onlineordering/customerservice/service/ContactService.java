package onlineordering.customerservice.service;

import onlineordering.customerservice.model.Contact;
import onlineordering.customerservice.model.ContactNotFoundException;
import onlineordering.customerservice.model.Customer;
import onlineordering.customerservice.model.CustomerNotFoundException;
import onlineordering.customerservice.repository.ContactRepository;
import onlineordering.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private final ContactRepository contactRepository;
    private final CustomerRepository customerRepository;

    ContactService(ContactRepository contactRepository, CustomerRepository customerRepository) {
        this.contactRepository = contactRepository;
        this.customerRepository = customerRepository;
    }

    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(long id) {
        return contactRepository.findById(id);
    }

    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public ResponseEntity<Object> deleteContactById(long id) {
        contactRepository.findById(id).orElseThrow(ContactNotFoundException::new);
        contactRepository.deleteById(id);

        return new ResponseEntity<>("Contact is deleted", HttpStatus.OK);
    }

    public Contact updateContactById(long id, Contact contact){

        Contact updatedContact = contactRepository.findById(id).map(old -> {
            Contact newContact = new Contact(id, contact.getName(), contact.getPhone(), contact.getEmail(), contact.getPosition());
            Customer oldCustomer = customerRepository.findById(old.getCustomer().getId()).orElseThrow(CustomerNotFoundException::new);

            newContact.setCustomer(old.getCustomer());
            oldCustomer.setContact(newContact);

            return newContact;
        }).orElseThrow(ContactNotFoundException::new);

        return contactRepository.save(updatedContact);
    }
}

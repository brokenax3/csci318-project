package com.csci318project.onlineordering.api;

import com.csci318project.onlineordering.model.Contact;
import com.csci318project.onlineordering.model.Customer;
import com.csci318project.onlineordering.repository.ContactRepository;
import com.csci318project.onlineordering.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/contact")
@RestController
public class ContactController {

    @Autowired
    private final ContactRepository contactRepository;
    private final CustomerRepository customerRepository;

    ContactController(ContactRepository contactRepository, CustomerRepository customerRepository) {
        this.contactRepository = contactRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    List<Contact> getAllContact() {
        return contactRepository.findAll();
    }

    @GetMapping("{id}")
    Contact getContactById(@PathVariable long id) {
        return contactRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping
    Contact addContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @PutMapping("/delete/{id}")
    void deleteContactById(@PathVariable long id) {
        contactRepository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    Contact updateContactById(@PathVariable long id, @RequestBody Contact contact){

        Contact updatedContact = contactRepository.findById(id).map(old -> {
            Contact newContact = new Contact(id, contact.getName(), contact.getPhone(), contact.getEmail(), contact.getPosition());
            Customer oldCustomer = customerRepository.findById(old.getCustomer().getId()).orElseThrow(RuntimeException::new);

            newContact.setCustomer(old.getCustomer());
            oldCustomer.setContact(newContact);

            return newContact;
        }).orElseThrow(RuntimeException::new);

        return contactRepository.save(updatedContact);
    }

}

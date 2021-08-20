package com.csci318project.onlineordering.api;

import com.csci318project.onlineordering.model.Contact;
import com.csci318project.onlineordering.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/contact")
@RestController
public class ContactController {

    @Autowired
    private final ContactRepository contactRepository;

    ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
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
        Contact updatedContact = contactRepository.findById(id).map(old -> new Contact(id, contact.getName(), contact.getPhone(), contact.getEmail(), contact.getPosition()))
                .orElseThrow(RuntimeException::new);

        return contactRepository.save(updatedContact);
    }

}

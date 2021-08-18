package com.csci318project.onlineordering.api;

import com.csci318project.onlineordering.model.Contact;
import com.csci318project.onlineordering.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/contact")
@RestController
public class ContactController {

    @Autowired
    private final ContactRepository contactRepository;

    ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
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
        Contact updatedContact = contactRepository.findById(id).map(old -> new Contact(old.getId(), contact.getName(), contact.getPhone(), contact.getEmail(), contact.getPosition()))
                .orElseThrow(RuntimeException::new);

        return contactRepository.save(updatedContact);
    }

}

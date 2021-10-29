package onlineordering.customerservice.api;

import onlineordering.customerservice.model.Contact;
import onlineordering.customerservice.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/contact")
@RestController
public class ContactController {

    private final ContactService contactService;

    @Autowired
    ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getAllContact() {
        return contactService.getAllContact();
    }

    @GetMapping("{id}")
    public Optional<Contact> getContactById(@PathVariable long id) {
        return contactService.getContactById(id);
    }

    @PostMapping
    public Contact addContact(@RequestBody Contact contact) {
        return contactService.addContact(contact);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteContactById(@PathVariable long id) {
        return contactService.deleteContactById(id);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllContact() {
        return contactService.deleteAllContact();
    }

    @PutMapping("{id}")
    public Contact updateContactById(@PathVariable long id, @RequestBody Contact contact) {
        return contactService.updateContactById(id, contact);
    }

}

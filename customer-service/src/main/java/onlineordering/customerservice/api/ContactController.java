package onlineordering.customerservice.api;

import onlineordering.customerservice.model.Contact;
import onlineordering.customerservice.model.ContactNotFoundException;
import onlineordering.customerservice.model.Customer;
import onlineordering.customerservice.service.ContactService;
import onlineordering.customerservice.service.CustomerService;
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

    @PutMapping("{id}")
    public Contact updateContactById(@PathVariable long id, @RequestBody Contact contact) {
        return contactService.updateContactById(id, contact);
    }

}

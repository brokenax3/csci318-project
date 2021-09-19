package onlineordering.customerservice.api;

import onlineordering.customerservice.model.Contact;
import onlineordering.customerservice.model.Customer;
import onlineordering.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @PostMapping
    public Customer addCustomer(@NonNull @RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping("{id}")
    public Customer getCustomerById(@PathVariable long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping(value = "company")
    public List<Customer> getCustomerByCompanyName(@RequestParam("name") String companyName) {
        return customerService.getCustomerByCompanyName(companyName);
    }

    @PutMapping("{id}")
    public Customer updateCustomer(@PathVariable long id, @NonNull @RequestBody Customer newCustomer) {
        return customerService.updateCustomer(id, newCustomer);
    }

    @PutMapping("{id}/contact/{contactId}")
    public Customer addCustomerContact(@PathVariable long id, @PathVariable long contactId) {
        return customerService.addCustomerContact(id, contactId);
    }

}

package onlineordering.biservice.api;

import onlineordering.biservice.service.OrderInteractiveQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerOrderQueryController {

    @Autowired
    OrderInteractiveQuery orderInteractiveQuery;

    @GetMapping("{id}/product-list")
    public List<String> findCustomerProductList(@PathVariable long id) {
        return orderInteractiveQuery.findCustomerOrderProduct(id);
    }

    @GetMapping("{id}/total-order-value")
    public long getCustomerTotalOrderValue(@PathVariable long id) {
        return orderInteractiveQuery.getCustomerTotalOrderValue(id);
    }

}

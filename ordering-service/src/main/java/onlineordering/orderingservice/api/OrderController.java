package onlineordering.orderingservice.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import onlineordering.orderingservice.model.Order;
import onlineordering.orderingservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "customer")
    public ResponseEntity<Object> addOrder(@RequestParam("id") long customerId, @RequestBody Order order) throws JsonProcessingException {
        return orderService.addOrder(customerId, order);
    }

    @GetMapping("{orderId}/customer")
    public ResponseEntity<String> findCustomerByOrder(@PathVariable long orderId) {
        return orderService.findCustomerByOrder(orderId);
    }

    @GetMapping("{orderId}/product")
    public ResponseEntity<String> findProductByOrder(@PathVariable long orderId) {
        return orderService.findProductByOrder(orderId);
    }

    @GetMapping
    public List<Order> findAllOrder() {
        return orderService.findAllOrder();
    }
}

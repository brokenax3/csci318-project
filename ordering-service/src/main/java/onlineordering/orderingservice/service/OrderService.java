package onlineordering.orderingservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import onlineordering.orderingservice.model.Order;
import onlineordering.orderingservice.model.OrderEvent;
import onlineordering.orderingservice.repository.OrderEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class OrderService {

    private final ApplicationEventPublisher publisher;

    private final RestTemplate restTemplate;

    private final OrderEventRepository orderEventRepository;

    @Autowired
    public OrderService(ApplicationEventPublisher publisher, RestTemplate restTemplate, OrderEventRepository orderEventRepository) {
        this.publisher = publisher;
        this.restTemplate = restTemplate;
        this.orderEventRepository = orderEventRepository;
    }

    public ResponseEntity<Object> addOrder(Long customerId, Order order) throws JsonProcessingException {

        String productName = order.getProductName();

        String URI_CUSTOMER_ID = "http://localhost:8082/customer/" + customerId.toString();
        ResponseEntity<String> response = restTemplate.getForEntity(URI_CUSTOMER_ID, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            return new ResponseEntity<>(String.format("Customer with id:%d Not Found", customerId),  HttpStatus.NOT_FOUND);
        }

        ObjectMapper mapCustomer = new ObjectMapper();
        JsonNode rootCustomer = mapCustomer.readTree(response.getBody());
        String customerAddress = rootCustomer.path("address").toString();
        String customerContact = rootCustomer.path("contact").toString();
        // Get Phone number
        JsonNode rootContact = mapCustomer.readTree(customerContact);
        String customerPhone = rootContact.path("phone").toString();

        String output = "Customer Address : " + customerAddress + "\n"
                + "Customer Contact : " + customerPhone + "\n";

        String URI_PRODUCT_ID = "http://localhost:8080/product?name=" + productName;
        ResponseEntity<String> checkInventory = restTemplate.getForEntity(URI_PRODUCT_ID, String.class);

        if (checkInventory.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(output + "Out of Stock", HttpStatus.OK);
        }

        ObjectMapper mapProduct = new ObjectMapper();
        JsonNode rootProduct = mapProduct.readTree(Objects.requireNonNull(checkInventory.getBody()).replace("[","".replace("]","")));
        System.out.println(rootProduct.toString().replace("[","").replace("]", ""));
        System.out.println(rootProduct.get("stockQuantity"));
        Long stockQuantity = Long.valueOf(rootProduct.path("stockQuantity").toString());
        Long productId = Long.valueOf(rootProduct.path("id").toString());

        OrderEvent orderEvent = new OrderEvent(order.getProductName(), order.getQuantity(), stockQuantity);

        publisher.publishEvent(orderEvent);

        orderEventRepository.save(orderEvent);


        return new ResponseEntity<>(output + "Order Success", HttpStatus.OK);
    }
}

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
import org.springframework.web.client.HttpClientErrorException;
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

        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity(URI_CUSTOMER_ID, String.class);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(String.format("Customer with id : %d is Not Found", customerId), HttpStatus.NOT_FOUND);
        }

        // Parse JSON Input
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

        ResponseEntity<String> checkInventory;
        long stockQuantity;
        long productPrice;

        try {
            checkInventory = restTemplate.getForEntity(URI_PRODUCT_ID, String.class);
            // Parse JSON input
            ObjectMapper mapProduct = new ObjectMapper();
            JsonNode rootProduct = mapProduct.readTree(Objects.requireNonNull(checkInventory.getBody()).replace("[", "".replace("]", "")));
            productPrice = Long.parseLong(rootProduct.path("price").toString());
            stockQuantity = Long.parseLong(rootProduct.path("stockQuantity").toString());

            if (stockQuantity - order.getQuantity() <= 0) return new ResponseEntity<>(output + "Not Enough Stock", HttpStatus.OK);

        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(output + "Out of Stock", HttpStatus.OK);
        }

        OrderEvent orderEvent = new OrderEvent(order.getProductName(), order.getQuantity(), stockQuantity, productPrice);

        publisher.publishEvent(orderEvent);

        orderEventRepository.save(orderEvent);

        output = output + "Unit Price : " + productPrice + "\n" + "Unit(s) Ordered : " + order.getQuantity() + "\n";

        return new ResponseEntity<>(output + "Order Success", HttpStatus.OK);
    }
}

package onlineordering.orderingservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import onlineordering.orderingservice.model.Order;
import onlineordering.orderingservice.model.OrderEvent;
import onlineordering.orderingservice.model.OrderNotFoundException;
import onlineordering.orderingservice.repository.OrderEventRepository;
import onlineordering.orderingservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {

    private final ApplicationEventPublisher publisher;

    private final RestTemplate restTemplate;

    private final OrderRepository orderRepository;

    private final OrderEventRepository orderEventRepository;

    @Autowired
    public OrderService(ApplicationEventPublisher publisher, RestTemplate restTemplate, OrderRepository orderRepository, OrderEventRepository orderEventRepository) {
        this.publisher = publisher;
        this.restTemplate = restTemplate;
        this.orderRepository = orderRepository;
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

        String URI_PRODUCT_ID = "http://localhost:8080/product/find?name=" + productName;

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

            if (stockQuantity - order.getQuantity() <= 0) return new ResponseEntity<>(output + "Not Enough Stock" + "\n", HttpStatus.OK);

        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(output + "Product Not Found" + "\n" , HttpStatus.NOT_FOUND);
        }

        OrderEvent orderEvent = new OrderEvent(order.getProductName(), order.getQuantity(), stockQuantity, productPrice);
        order.setCustomerId(customerId);

        publisher.publishEvent(orderEvent);

        orderEventRepository.save(orderEvent);
        orderRepository.save(order);

        output = output + "Unit Price : " + productPrice + "\n" + "Unit(s) Ordered : " + order.getQuantity() + "\n";

        return new ResponseEntity<>(output + "Order Success" + "\n", HttpStatus.OK);
    }

    public ResponseEntity<String> findCustomerByOrder(long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        String URI_CUSTOMER_ID = "http://localhost:8082/customer/" + order.getCustomerId().toString();

        ResponseEntity<String> customerDetails;
        try {
            customerDetails = restTemplate.getForEntity(URI_CUSTOMER_ID, String.class);

        } catch (HttpClientErrorException e) {

            return new ResponseEntity<>("Customer not found. \n", HttpStatus.NOT_FOUND);
        }

        return customerDetails;
    }

    public ResponseEntity<String> findProductByOrder(long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        String productName = order.getProductName();

        String URI_PRODUCT_ID = "http://localhost:8080/product/find?name=" + productName;

        ResponseEntity<String> productDetails;
        try {
            productDetails = restTemplate.getForEntity(URI_PRODUCT_ID, String.class);

        } catch (HttpClientErrorException e) {

            return new ResponseEntity<>("Product Not Found. \n" , HttpStatus.NOT_FOUND);
        }

        return productDetails;
    }

    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }
}

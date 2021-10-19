package onlineordering.orderingservice.service;

import onlineordering.orderingservice.model.OrderEvent;
import onlineordering.orderingservice.repository.OrderEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderEventHandler {

    @Autowired
    RestTemplate restTemplate;

    private final OrderEventRepository orderEventRepository;

    public OrderEventHandler(OrderEventRepository orderEventRepository) {
        this.orderEventRepository = orderEventRepository;
    }

    @EventListener
    public void handle(OrderEvent orderEvent) {
        String productName = orderEvent.getProductName();
        String URI_PRODUCT_ID = "http://localhost:8080/product/update?name=" + productName;

        long newStock = orderEvent.getStockQuantity() - orderEvent.getOrderQuantity();

        Map<String, String> params = new HashMap<>();
        params.put("stockQuantity", Long.toString(newStock));

        restTemplate.put(URI_PRODUCT_ID,  params, String.class);

        orderEventRepository.save(orderEvent);
    }
}

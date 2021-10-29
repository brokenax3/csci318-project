package onlineordering.orderingservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.http.HTTPException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Component
public class OrderRunner implements CommandLineRunner {

    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(OrderRunner.class);
    private static final String ORDER_URL = "http://localhost:8081/order/customer?id=";
    private static final String[] flavour = {"Chocolate", "Vanilla", "Caramel", "White Chocolate", "Plain"};

    @Autowired
    public OrderRunner(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        while (!Thread.currentThread().isInterrupted()) {
            // Select a random CustomerId
            int customerSel = new Random().nextInt(4) + 6;
            // Select a random Product and Quantity
            int productSel = new Random().nextInt(4);
            int quantity = new Random().nextInt(10) + 1;

            // Make Request Body
            Map<String, String> paramsOrder = new HashMap<>();
            paramsOrder.put("supplier", "Coles");
            paramsOrder.put("productName", flavour[productSel] + " Cookie");
            paramsOrder.put("quantity", Integer.toString(quantity));

            try {
                ResponseEntity<String> response = restTemplate.postForEntity(ORDER_URL + customerSel, paramsOrder, String.class);
                logger.info(String.valueOf(response));
            } catch (HTTPException e) {
                logger.info(String.valueOf(e));
            }
            Thread.sleep(5000);
        }
    }
}

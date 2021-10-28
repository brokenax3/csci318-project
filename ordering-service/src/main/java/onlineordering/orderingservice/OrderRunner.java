package onlineordering.orderingservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.http.HTTPException;
import java.net.URI;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Component
public class OrderRunner implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(OrderRunner.class);

    @Autowired
    public OrderRunner(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {

        String[] names = {"Bill Gates", "Steve Jobs", "Tim Cook", "Steve Steve", "John Doe"};
        String phone, email, position;
        String URI_CONTACT_ID = "http://localhost:8082/contact/";

        // Clear contacts if Customer Service has already been running
        restTemplate.delete(URI_CONTACT_ID);

        // POST some Contacts
        logger.info("POST some Contacts");

        for (int i = 0; i < names.length; i++) {
            phone = String.valueOf(Integer.parseInt("0450000000") + i);
            email= names[i].replace(" ", "").toLowerCase(Locale.ROOT) + "@gmail.com";

            Map<String, String> paramsContact = new HashMap<String, String>();
            paramsContact.put("name", names[i]);
            paramsContact.put("email", email);
            paramsContact.put("phone", phone);
            paramsContact.put("position", "CEO");

            try {
                ResponseEntity<String> response = restTemplate.postForEntity(URI_CONTACT_ID, paramsContact, String.class);
                logger.info(String.valueOf(response));
            }catch (HTTPException e) {
                logger.info(String.valueOf(e));
            }
        }

        // Post some Customers
        String URI_CUSTOMER_ID = "http://localhost:8082/customer/";
        String[] companyNames = {"Microsoft", "Apple", "Apple", "Mojang", "Nickelodeon"};
        String[] addresses = {"Redmond", "Palo Alto", "Apple Park", "Stockholm", "New York"};
        String country;

        for (int i = 0; i < names.length; i++) {
            if (companyNames[i].equals("Mojang")) country = "Sweden"; else country = "USA";

             Map<String, String> params = new HashMap<String, String>();
             params.put("companyName", companyNames[i]);
             params.put("address", addresses[i]);
             params.put("country", country);

             try {
                 ResponseEntity<String> response = restTemplate.postForEntity(URI_CUSTOMER_ID, params, String.class);
                 logger.info(String.valueOf(response));
             } catch (HTTPException e) {
                 logger.info(String.valueOf(e));
             }
        }

        // Link Customers and Contacts
    }
}

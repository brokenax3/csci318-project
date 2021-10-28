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
    }
}

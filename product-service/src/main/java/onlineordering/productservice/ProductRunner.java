package onlineordering.productservice;

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
public class ProductRunner implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ProductRunner.class);

    @Autowired
    public ProductRunner(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        // POST some Products
        String URI_PRODUCT = "http://localhost:8080/product";
        String[] flavour = {"Chocolate", "Vanilla", "Caramel", "White Chocolate", "Plain"};
        String description;

        for (int i = 0; i < flavour.length; i++) {

            Map<String, String> paramsProduct = new HashMap<String, String >();
            paramsProduct.put("productCategory", "Confectionery");
            paramsProduct.put("productName", flavour[i] + " Cookie");
            paramsProduct.put("price", "2");
            paramsProduct.put("stockQuantity", "10000");

            try {
                ResponseEntity<String> response = restTemplate.postForEntity(URI_PRODUCT, paramsProduct, String.class);
                logger.info(String.valueOf(response));
            } catch (HTTPException e) {
                logger.info(String.valueOf(e));
            }
        }

        // POST some Product Details
        String URI_PRODUCT_DETAIL = "http://localhost:8080/product/detail";
        String[] comment = {"Gluten Free", "Not Gluten Free"};
        for (int i = 0; i < flavour.length; i++) {
            description = "Quite a " + flavour[i] + "-ish " + flavour[i] + " cookie.";
            int comment_sel = new Random().nextInt(comment.length);

            Map<String, String> paramsProductDetail = new HashMap<String, String >();
            paramsProductDetail.put("description", description);
            paramsProductDetail.put("comment", comment[comment_sel]);

            try {
                ResponseEntity<String> response = restTemplate.postForEntity(URI_PRODUCT_DETAIL, paramsProductDetail, String.class);
                logger.info(String.valueOf(response));
            } catch (HTTPException e) {
                logger.info(String.valueOf(e));
            }

        }

        // Link Products and Product Details
        for (int i = 1; i <= flavour.length; i++) {
            int productId = i;
            int detailId = i + 5;
            String URI_LINK = "http://localhost:8080/product/" + productId + "/detail/" + detailId;

            try {
                restTemplate.put(URI_LINK, null);
            } catch (HTTPException e) {
                logger.info(String.valueOf(e));
            }
        }
    }
}

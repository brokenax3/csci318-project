package onlineordering.productservice.api;

import onlineordering.productservice.model.Product;
import onlineordering.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping(path = "{id}")
    public Product getProductById(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProductById(@PathVariable("id") long id) {
        productService.deleteProductById(id);
    }

    @PutMapping(value = "product")
    public Product updateStock(@RequestParam("productName") String productName, @RequestBody long quantity) {
        return productService.updateStock(productName, quantity);
    }

    @PutMapping(path = "{id}")
    public Product updateProductById(@PathVariable("id") long id, @RequestBody Product product) {
        return productService.updateProductById(id, product);
    }
}

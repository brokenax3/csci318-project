package onlineordering.productservice.api;

import onlineordering.productservice.model.Product;
import onlineordering.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/product")
@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping
    public List<Product> findAllProduct() {
        return productService.findAllProduct();
    }

    @GetMapping(path = "{id}")
    public Optional<Product> findProductById(@PathVariable("id") long id) {
        return productService.findProductById(id);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable("id") long id) {
        return productService.deleteProductById(id);
    }

    @PutMapping(value = "product")
    public Product updateStock(@RequestParam("productName") String productName, @RequestBody long quantity) {
        return productService.updateStock(productName, quantity);
    }

    @PutMapping(path = "{id}")
    public Product updateProductById(@PathVariable("id") long id, @RequestBody Product product) {
        return productService.updateProductById(id, product);
    }

    @PutMapping(path = "{id}/detail/{productId}")
    public Product addProductDetail(@PathVariable long id, @PathVariable long productId) {
        return productService.addProductDetail(id, productId);
    }
}

package onlineordering.productservice.service;

import onlineordering.productservice.model.Product;
import onlineordering.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(long id) {
        return productRepository.getById(id);
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product updateStock(String productName, long quantity) {
        Product product = productRepository.findByProductName(productName);
        product.setStockQuantity(quantity);

        return productRepository.save(product);

    }

    public Product updateProduct(long id, Product product) {

        Product updatedProduct = productRepository.findById(id).map(old -> new Product(id, product.getProductCategory(), product.getName(),
                product.getPrice(), product.getStockQuantity(), product.getProductDetail())).orElseThrow(RuntimeException::new);

        return productRepository.save(updatedProduct);
    }
}

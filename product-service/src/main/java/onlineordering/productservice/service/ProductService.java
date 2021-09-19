package onlineordering.productservice.service;

import onlineordering.productservice.model.Product;
import onlineordering.productservice.model.ProductDetail;
import onlineordering.productservice.model.ProductDetailNotFoundException;
import onlineordering.productservice.model.ProductNotFoundException;
import onlineordering.productservice.repository.ProductDetailRepository;
import onlineordering.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductDetailRepository productDetailRepository) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findProductById(long id) {
        return productRepository.findById(id);
    }

    public ResponseEntity<Object> deleteProductById(long id) {
        productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.deleteById(id);

        return new ResponseEntity<>("Product is deleted", HttpStatus.OK);
    }

    public List<Product> findAllProduct(){
        return productRepository.findAll();
    }

    public ResponseEntity<Object> updateStock(String productName, long quantity) {
        Product product = productRepository.findByProductName(productName)
                .orElseThrow(ProductNotFoundException::new);
        product.setStockQuantity(quantity);
        productRepository.save(product);

        return new ResponseEntity<>("Product quantity is updated successfully", HttpStatus.OK);
    }

    public ResponseEntity<Object> updateProductById(long id, Product product) {

        Product updatedProduct = productRepository.findById(id).map(old -> new Product(id, product.getProductCategory(), product.getName(),
                product.getPrice(), product.getStockQuantity(), product.getProductDetail())).orElseThrow(ProductNotFoundException::new);

        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }

    public ResponseEntity<Object> addProductDetail(long id, long productId) {

        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        ProductDetail productDetail = productDetailRepository.findById(productId).orElseThrow(ProductDetailNotFoundException::new);

        product.setProductDetail(productDetail);
        productDetail.setProduct(product);
        productRepository.save(product);
        productDetailRepository.save(productDetail);

        return new ResponseEntity<>("Product detail linked to Product successfully", HttpStatus.OK);
    }
}

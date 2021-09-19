package onlineordering.productservice.service;

import onlineordering.productservice.model.Product;
import onlineordering.productservice.model.ProductDetail;
import onlineordering.productservice.repository.ProductDetailRepository;
import onlineordering.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Product findProductById(long id) {
        return productRepository.getById(id);
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllProduct(){
        return productRepository.findAll();
    }

    public Product updateStock(String productName, long quantity) {
        Product product = productRepository.findByProductName(productName);
        product.setStockQuantity(quantity);

        return productRepository.save(product);
    }

    public Product updateProductById(long id, Product product) {

        Product updatedProduct = productRepository.findById(id).map(old -> new Product(id, product.getProductCategory(), product.getName(),
                product.getPrice(), product.getStockQuantity(), product.getProductDetail())).orElseThrow(RuntimeException::new);

        return productRepository.save(updatedProduct);
    }

    public Product addProductDetail(long id, long productId) {

        Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        ProductDetail productDetail = productDetailRepository.findById(productId).orElseThrow(RuntimeException::new);

        product.setProductDetail(productDetail);
        productDetail.setProduct(product);
        productDetailRepository.save(productDetail);

        return productRepository.save(product);
    }
}

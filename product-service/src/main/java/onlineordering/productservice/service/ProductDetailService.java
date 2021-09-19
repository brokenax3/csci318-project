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

import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailService {
    private final ProductDetailRepository productDetailRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductDetailService(ProductDetailRepository productDetailRepository, ProductRepository productRepository) {
        this.productDetailRepository = productDetailRepository;
        this.productRepository = productRepository;
    }

    public ProductDetail addProductDetail(ProductDetail productDetail){
        return productDetailRepository.save(productDetail);
    }

    public List<ProductDetail> findAllProductDetail() {
        return productDetailRepository.findAll();
    }

    public Optional<ProductDetail> findProductDetailById(long id) {
        return productDetailRepository.findById(id);
    }

    public ResponseEntity<Object> deleteProductDetailById(long id) {
        productDetailRepository.findById(id).orElseThrow(ProductDetailNotFoundException::new);
        productDetailRepository.deleteById(id);

        return new ResponseEntity<>("Product detail is deleted", HttpStatus.OK);
    }

    public ProductDetail updateProductDetailById(long id, ProductDetail productDetail) {
        ProductDetail updatedProductDetail = productDetailRepository.findById(id).map(old -> {
            ProductDetail newProductDetail = new ProductDetail(id, productDetail.getDescription(), productDetail.getComment());
            Product oldProduct = productRepository.findById(old.getProduct().getId()).orElseThrow(ProductNotFoundException::new);

            newProductDetail.setProduct(oldProduct);
            oldProduct.setProductDetail(newProductDetail);

            return newProductDetail;
        }).orElseThrow(ProductNotFoundException::new);

        return productDetailRepository.save(updatedProductDetail);
    }
}

package onlineordering.productservice.service;

import onlineordering.productservice.model.ProductDetail;
import onlineordering.productservice.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailService {
    private final ProductDetailRepository productDetailRepository;

    @Autowired
    public ProductDetailService(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
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

    public void deleteProductDetailById(long id) {
        productDetailRepository.deleteById(id);
    }

    public ProductDetail updateProductDetailById(long id, ProductDetail productDetail) {
        return productDetailRepository.findById(id).map(old -> new ProductDetail(id, productDetail.getDescription(), productDetail.getComment()))
                .orElseThrow(RuntimeException::new);
    }
}

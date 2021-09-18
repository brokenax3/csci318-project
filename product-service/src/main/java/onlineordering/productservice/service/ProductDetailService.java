package onlineordering.productservice.service;

import onlineordering.productservice.model.ProductDetail;
import onlineordering.productservice.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService {
    private final ProductDetailRepository productDetailRepository;

    public ProductDetailService(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }

    @Autowired
    public ProductDetail addProductDetail(ProductDetail productDetail){
        return productDetailRepository.save(productDetail);
    }

    public ProductDetail getProductDetailById(long id) {
        return productDetailRepository.getById(id);
    }

    public void deleteProductDetailById(long id) {
        productDetailRepository.deleteById(id);
    }

    public ProductDetail updateProductDetailById(long id, ProductDetail productDetail) {
        return productDetailRepository.findById(id).map(old -> new ProductDetail(id, productDetail.getDescription(), productDetail.getComment()))
                .orElseThrow(RuntimeException::new);
    }
}

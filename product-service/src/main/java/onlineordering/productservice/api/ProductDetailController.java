package onlineordering.productservice.api;

import onlineordering.productservice.model.ProductDetail;
import onlineordering.productservice.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/product/detail")
@RestController
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    @Autowired
    public ProductDetailController(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }

    @PostMapping
    public ProductDetail addProductDetail(@RequestBody ProductDetail productDetail) {
        return productDetailService.addProductDetail(productDetail);
    }

    @GetMapping()
    public List<ProductDetail> findAllProductDetail() {
        return productDetailService.findAllProductDetail();
    }

    @GetMapping("{id}")
    public Optional<ProductDetail> findProductDetailById(@PathVariable long id) {
        return productDetailService.findProductDetailById(id);
    }

    @PutMapping("{id}")
    public ProductDetail updateProductDetailById(@PathVariable long id, @RequestBody ProductDetail productDetail) {
        return productDetailService.updateProductDetailById(id, productDetail);
    }

    @DeleteMapping("{id}")
    public void deleteProductDetailById(@PathVariable long id) {
        productDetailService.deleteProductDetailById(id);
    }

}

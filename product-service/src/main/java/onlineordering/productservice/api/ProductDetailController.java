package onlineordering.productservice.api;

import onlineordering.productservice.model.ProductDetail;
import onlineordering.productservice.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/product/detail")
@RestController
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    @Autowired
    public ProductDetailController(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }

    @PostMapping
    public ProductDetail addProductDetail(ProductDetail productDetail) {
        return productDetailService.addProductDetail(productDetail);
    }

    @GetMapping("{id}")
    public ProductDetail getProductDetailById(@PathVariable long id) {
        return productDetailService.getProductDetailById(id);
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

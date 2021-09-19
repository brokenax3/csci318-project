package onlineordering.productservice.api;

import onlineordering.productservice.model.ProductDetailNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductDetailExceptionController {

    @ExceptionHandler(value = ProductDetailNotFoundException.class)
    public ResponseEntity<Object> exception(ProductDetailNotFoundException exception) {
        return new ResponseEntity<>("Product Detail Not Found", HttpStatus.NOT_FOUND);
    }
}

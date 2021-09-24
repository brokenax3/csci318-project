package onlineordering.orderingservice.api;

import onlineordering.orderingservice.model.OnlineOrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OnlineOrderingExceptionController {

    @ExceptionHandler(value = OnlineOrderNotFoundException.class)
    public ResponseEntity<Object> exception(OnlineOrderNotFoundException exception) {
        return new ResponseEntity<>("Order Not Found", HttpStatus.NOT_FOUND);
    }
}
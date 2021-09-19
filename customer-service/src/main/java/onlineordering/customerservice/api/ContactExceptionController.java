package onlineordering.customerservice.api;

import onlineordering.customerservice.model.ContactNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ContactExceptionController {

    @ExceptionHandler(value = ContactNotFoundException.class)
    public ResponseEntity<Object> exception(ContactNotFoundException exception) {
        return new ResponseEntity<>("Contact Not Found", HttpStatus.NOT_FOUND);
    }
}

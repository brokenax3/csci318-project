package onlineordering.biservice.service;

import onlineordering.biservice.model.OrderEvent;
import org.apache.kafka.streams.state.QueryableStoreType;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderInteractiveQuery {

    private final InteractiveQueryService interactiveQueryService;

    public OrderInteractiveQuery(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }

    public ResponseEntity<Object> findProductOrderQuantity (String productName) {
        return new ResponseEntity<>("Product is deleted", HttpStatus.OK);
    }

    public ResponseEntity<Object> findCustomerOrders (String customerName) {
       return new ResponseEntity<>("Product is deleted", HttpStatus.OK);
    }

    public ReadOnlyKeyValueStore<String, OrderEvent>  orderEventStore() {
        return this.interactiveQueryService.getQueryableStore(OrderStreamProcessing.ORDER_EVENT_STORE,
                QueryableStoreTypes.keyValueStore());
    }
}

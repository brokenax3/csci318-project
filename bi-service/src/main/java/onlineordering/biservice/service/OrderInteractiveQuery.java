package onlineordering.biservice.service;

import onlineordering.biservice.model.OrderEvent;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderInteractiveQuery {

    private final InteractiveQueryService interactiveQueryService;

    @Autowired
    public OrderInteractiveQuery(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }

    public List<String> findCustomerOrderProduct (long customerId) {
        List<String> productList = new ArrayList<>();
        KeyValueIterator<String, OrderEvent> all = customerOrderEventStore().all();

        while (all.hasNext()) {
            OrderEvent orderEvent = all.next().value;
            Long table_customerId = orderEvent.getCustomerId();
            String productName = orderEvent.getProductName();
            if (table_customerId.equals(customerId)) {
                productList.add(productName);
            }
        }
        return productList;
    }

    public long getCustomerTotalOrderValue (long customerId) {
        long totalOrderValue = 0;

        KeyValueIterator<String, OrderEvent> all = customerOrderEventStore().all();
        while (all.hasNext()) {
            OrderEvent orderEvent = all.next().value;
            long table_customerId = orderEvent.getCustomerId();
            long quantity = orderEvent.getOrderQuantity();
            long price = orderEvent.getProductPrice();

            if (table_customerId == (customerId)) {
                totalOrderValue = totalOrderValue + quantity*price;
            }
        }

        return totalOrderValue;
    }

    public ReadOnlyKeyValueStore<String, OrderEvent> customerOrderEventStore() {
        return this.interactiveQueryService.getQueryableStore(OrderStreamProcessing.CUSTOMER_STATE_STORE,
                QueryableStoreTypes.keyValueStore());
    }
}

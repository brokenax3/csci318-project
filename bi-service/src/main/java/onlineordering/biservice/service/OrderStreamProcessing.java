package onlineordering.biservice.service;

import onlineordering.biservice.model.OrderEvent;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration
public class OrderStreamProcessing {

    public final static String CUSTOMER_STATE_STORE = "customer-state-store";

    @Bean
    public Function<KStream<?, OrderEvent>, KStream<String, Double>> process() {
        return inputStream -> {

            // Store products
            inputStream.map((key, value) -> {
                long customerId = value.getCustomerId();
                long orderQuantity = value.getOrderQuantity();
                long productPrice = value.getProductPrice();
                String productName = value.getProductName();

                OrderEvent orderEvent = new OrderEvent();
                orderEvent.setCustomerId(customerId);
                orderEvent.setOrderQuantity(orderQuantity);
                orderEvent.setProductPrice(productPrice);
                orderEvent.setProductName(productName);

                String new_key = productName + customerId;
                return KeyValue.pair(new_key, orderEvent);
            }).toTable(
                    Materialized.<String, OrderEvent, KeyValueStore<Bytes, byte[]>>as(CUSTOMER_STATE_STORE)
                            .withKeySerde(Serdes.String())
                            .withValueSerde(orderEventSerde())
            );
//            testKTable.toStream().print(Printed.<String, OrderEvent>toSysOut().withLabel("Print"));

            // Logging Order Value
            KStream<String, Long> testStream = inputStream.map((key, value) -> {
                String productName = value.getProductName();
                long orderQuantity = value.getOrderQuantity();
                return KeyValue.pair(productName, orderQuantity);
            });

//            testStream.print(Printed.<String, Long>toSysOut().withLabel("Test"));

            // Map product and quantity into a KTable
            KTable<String, Double> orderQuantityKTable = inputStream.map((key, value) -> {
                String productName = value.getProductName();
                long orderQuantity = value.getOrderQuantity();

                return KeyValue.pair(productName, orderQuantity);
                }).groupByKey().aggregate(() -> 0.0,
                    (key, value, total) -> total + value,
                    Materialized.with(Serdes.String(), Serdes.Double()));

            // Log Quantity Summary
            KStream<String, Double> orderQuantityStream = orderQuantityKTable.toStream();
            orderQuantityStream.print(Printed.<String, Double>toSysOut().withLabel("Log"));

            return orderQuantityStream;
        };
    }

    public Serde<OrderEvent> orderEventSerde() {
        final JsonSerde<OrderEvent> orderEventJsonSerde = new JsonSerde<>();

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "onlineordering.biservice.model.OrderEvent");
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        orderEventJsonSerde.configure(configProps, false);

        return orderEventJsonSerde;
    }
}

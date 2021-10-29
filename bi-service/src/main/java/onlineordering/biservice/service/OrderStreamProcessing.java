package onlineordering.biservice.service;

import onlineordering.biservice.model.OrderEvent;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class OrderStreamProcessing {

    public final static String ORDER_EVENT_STORE = "order-event-store";

//    @Bean
//    public Function<KStream<?, OrderEvent>, KStream<Long, OrderEvent>> process() {
//
//
//    }
}

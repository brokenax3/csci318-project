package onlineordering.biservice;

import onlineordering.biservice.model.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class BiServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(BiServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BiServiceApplication.class, args);
	}

//	@Bean
//	public Consumer<OrderEvent> consume() {
//		return input -> logger.info(input.toString());
//	}
}

package onlineordering.orderingservice.repository;

import onlineordering.orderingservice.model.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEventRepository extends JpaRepository<OrderEvent, Long> {
}

package onlineordering.orderingservice.repository;

import onlineordering.orderingservice.model.OnlineOrdering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnlineOrderingRepository extends JpaRepository <OnlineOrdering, Long>{

}

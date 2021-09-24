package onlineordering.orderingservice.service;

import onlineordering.orderingservice.model.OnlineOrdering;
import onlineordering.orderingservice.model.OnlineOrderNotFoundException;
import onlineordering.orderingservice.repository.OnlineOrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderingService {
	private final OnlineOrderingRepository onlineOrderRepo;
	
	@Autowired
    public OrderingService(OnlineOrderingRepository onlineOrderRepo) {
        this.onlineOrderRepo = onlineOrderRepo;
    }
	
	public OnlineOrdering addOrder (OnlineOrdering order) {
		return onlineOrderRepo.save(order);
	}
	
	public Optional<OnlineOrdering> findOrderById(long id) {
        return onlineOrderRepo.findById(id);
    }
	
	public ResponseEntity<Object> deleteOrderById(long id) {
		onlineOrderRepo.findById(id).orElseThrow(OnlineOrderNotFoundException::new);
		onlineOrderRepo.deleteById(id);

        return new ResponseEntity<>("Order is deleted", HttpStatus.OK);
    }
	
	public List<OnlineOrdering> findAllOrders(){
        return onlineOrderRepo.findAll();
    }
	
	public OnlineOrdering updateProduct(long orderID, String product) {
		OnlineOrdering order = onlineOrderRepo.findById(orderID)
                .orElseThrow(OnlineOrderNotFoundException::new);
		order.setProductName(product);

        return onlineOrderRepo.save(order);
    }
	
	public OnlineOrdering updateOrderById(long id, OnlineOrdering order) {

		OnlineOrdering updatedOrder = onlineOrderRepo.findById(id).map(old -> new OnlineOrdering(id, order.getCustomerId(), order.getProductName(),
				order.getQuantity(), order.getCustAddress(), order.getCustPhone())).orElseThrow(OnlineOrderNotFoundException::new);

		
        return onlineOrderRepo.save(updatedOrder);
    }
	
}

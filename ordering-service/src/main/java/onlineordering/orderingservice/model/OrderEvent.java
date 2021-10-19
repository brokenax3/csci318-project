package onlineordering.orderingservice.model;

import javax.persistence.*;

@Entity
public class OrderEvent {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="productName")
    private String productName;

    @Column(name="orderQuantity")
    private long orderQuantity;

    @Column(name="stockQuantity")
    private long stockQuantity;

    @Column(name="productPrice")
    private long productPrice;

    public OrderEvent() {}

    public OrderEvent(String productName, long orderQuantity, long stockQuantity, long productPrice){
        this.productName = productName;
        this.orderQuantity = orderQuantity;
        this.stockQuantity = stockQuantity;
        this.productPrice = productPrice;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(long orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public long getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}

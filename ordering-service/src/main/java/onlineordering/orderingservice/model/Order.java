package onlineordering.orderingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "productName")
    private String productName;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "customerId")
    private Long customerId;

    public Order(){}

    public Order(long id, String supplier, String productName, long quantity) {
        this.id = id;
        this.supplier = supplier;
        this.productName = productName;
        this.quantity = quantity;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getProductName() {
        return productName;
    }

    public long getQuantity() {
        return quantity;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public long getId() {
        return id;
    }

}








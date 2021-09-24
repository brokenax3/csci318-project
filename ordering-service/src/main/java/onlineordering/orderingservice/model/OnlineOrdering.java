package onlineordering.orderingservice.model;

import javax.persistence.*;

@Entity
@Table(name = "OnlineOrder")

public class OnlineOrdering {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "customerID")
    private long customerID;

    @Column(name = "productName")
    private String productName;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "custAddress")
    private String custAddress;
    
    @Column(name = "custPhone")
    private String custPhone;

    @OneToOne(mappedBy = "custID")
    private long custID;

    public OnlineOrdering() {}

    public OnlineOrdering(long id, long customerID, String productName, long quantity, String custAddress, String custPhone) {
        this.id = id;
        this.customerID = customerID;
        this.productName = productName;
        this.quantity = quantity;
        this.custAddress = custAddress;
        this.custPhone = custPhone;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getId() {
        return id;
    }

    public long getCustomerId() {
        return customerID;
    }
    

    public String getProductName() {
        return productName;
    }
    
    public long getQuantity() {
        return quantity;
    }

    public String getCustPhone() {
    	return custPhone;
    }
    
    public String getCustAddress() {
    	return custAddress;
    }

}

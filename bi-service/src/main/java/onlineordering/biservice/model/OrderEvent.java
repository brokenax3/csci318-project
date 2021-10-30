package onlineordering.biservice.model;

public class OrderEvent {

    private String productName;
    private long customerId;
    private long orderQuantity;
    private long productPrice;

    public OrderEvent() {}

    public OrderEvent(String productName, long customerId, long orderQuantity, long productPrice) {
        this.productName = productName;
        this.customerId = customerId;
        this.orderQuantity = orderQuantity;
        this.productPrice = productPrice;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
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
}
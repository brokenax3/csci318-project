package onlineordering.biservice.model;

public class OrderEvent {

    private String productName;
    private String customerId;
    private String quantity;
    private String price;

    public OrderEvent() {
    }

    public OrderEvent(String productName, String customerId, String quantity, String price) {
        this.productName = productName;
        this.customerId = customerId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
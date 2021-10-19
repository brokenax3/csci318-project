package onlineordering.orderingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    private String supplier;

    private String productName;

    private long quantity;

    public Order(){}

    public Order(String supplier, String productName, long quantity) {
        this.supplier = supplier;
        this.productName = productName;
        this.quantity = quantity;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setProduct(String product) {
        this.productName = product;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
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


}








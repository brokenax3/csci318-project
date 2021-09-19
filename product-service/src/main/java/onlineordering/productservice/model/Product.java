package onlineordering.productservice.model;

import javax.persistence.*;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "productCategory")
    private String productCategory;

    @Column(name = "productName")
    private String productName;

    @Column(name = "price")
    private long price;

    @Column(name = "stockQuantity")
    private long stockQuantity;

    @OneToOne(mappedBy = "product")
    private ProductDetail productDetail;

    public Product() {}

    public Product(long id, String productCategory, String productName, long price, long stockQuantity, ProductDetail productDetail) {
        this.id = id;
        this.productCategory = productCategory;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productDetail = productDetail;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setStockQuantity(long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public long getId() {
        return id;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getName() {
        return productName;
    }

    public long getPrice() {
        return price;
    }

    public long getStockQuantity() {
        return stockQuantity;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }
}

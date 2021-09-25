package onlineordering.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "ProductDetail")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "comment")
    private String comment;

    @OneToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    public ProductDetail() {}

    public ProductDetail(long id, String description, String comment) {
        this.id = id;
        this.description = description;
        this.comment = comment;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getId() {
        return id;
    }
    public Product getProduct() {
        return product;
    }

    public String getDescription() {
        return description;
    }

    public String getComment() {
        return comment;
    }
}

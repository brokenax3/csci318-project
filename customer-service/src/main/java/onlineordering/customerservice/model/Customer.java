package onlineordering.customerservice.model;

import javax.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "address")
    private String address;

    @Column(name = "country")
    private String country;

    @OneToOne(mappedBy = "customer")
    private Contact contact;

    public Customer() {};


    public Customer(long id, String companyName, String address, String country, Contact contact) {
        this.id = id;
        this.companyName = companyName;
        this.address = address;
        this.country = country;
        this.contact = contact;
    }

    public Customer(long id, String companyName, String address, String country) {
        this.id = id;
        this.companyName = companyName;
        this.address = address;
        this.country = country;
    }

    public void setContact(Contact newContact) {
        this.contact = newContact;
    }

    public long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public Contact getContact() {
        return contact;
    }

}

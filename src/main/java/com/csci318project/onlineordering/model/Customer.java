package com.csci318project.onlineordering.model;

public class Customer {

    private final String companyName;
    private final String address;
    private final String country;
    private Contact contact;


    public Customer(String companyName, String address, String country, Contact contact) {
        this.companyName = companyName;
        this.address = address;
        this.country = country;
        this.contact = contact;
    }

    public Customer(String companyName, String address, String country) {
        this.companyName = companyName;
        this.address = address;
        this.country = country;
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

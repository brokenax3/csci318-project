package com.csci318project.onlineordering.model;

public class Contact {

    private final String name;
    private final String phone;
    private final String email;
    private final String position;

    public Contact(String name, String phone, String email, String position) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }
}

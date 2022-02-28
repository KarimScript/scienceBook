package com.example.sciencebook;

public class Order {
    private String id;
    private String amount;
    private String phone;
    private String username;
    private String address;
    private String zipcode;
    private String bookname;
    private String email;

    public Order(String id, String amount, String phone, String username, String address, String zipcode, String bookname, String email) {
        this.id = id;
        this.amount = amount;
        this.phone = phone;
        this.username = username;
        this.address = address;
        this.zipcode = zipcode;
        this.bookname = bookname;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

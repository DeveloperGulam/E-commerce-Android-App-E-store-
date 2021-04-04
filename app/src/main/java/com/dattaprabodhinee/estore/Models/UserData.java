package com.dattaprabodhinee.estore.Models;

public class UserData {
    int id;
    String name, customer_img, email, number, city, zipcode, state, landmark, address, verification;

    public UserData(int id, String name, String customer_img, String email, String number, String city, String zipcode, String state, String landmark, String address, String verification) {
        this.id = id;
        this.name = name;
        this.customer_img = customer_img;
        this.email = email;
        this.number = number;
        this.city = city;
        this.zipcode = zipcode;
        this.state = state;
        this.landmark = landmark;
        this.address = address;
        this.verification = verification;
    }

    public UserData(int id, String name, String customer_img, String email, String number, String city, String zipcode, String state, String landmark, String address) {
        this.id = id;
        this.name = name;
        this.customer_img = customer_img;
        this.email = email;
        this.number = number;
        this.city = city;
        this.zipcode = zipcode;
        this.state = state;
        this.landmark = landmark;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomer_img() {
        return customer_img;
    }

    public void setCustomer_img(String customer_img) {
        this.customer_img = customer_img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }
}

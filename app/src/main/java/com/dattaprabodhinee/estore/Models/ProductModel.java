package com.dattaprabodhinee.estore.Models;

public class ProductModel {
    int id;
    String user_id, product_no, name;
    int price;
    String product_img;
    int orignal_price, packing_size;
    String packing_type, description;
    int stock;

    public ProductModel(int id, String user_id, String product_no, String name, int price, String product_img, int orignal_price, int packing_size, String packing_type, String description, int stock) {
        this.id = id;
        this.user_id = user_id;
        this.product_no = product_no;
        this.name = name;
        this.price = price;
        this.product_img = product_img;
        this.orignal_price = orignal_price;
        this.packing_size = packing_size;
        this.packing_type = packing_type;
        this.description = description;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_no() {
        return product_no;
    }

    public void setProduct_no(String product_no) {
        this.product_no = product_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }

    public int getOrignal_price() {
        return orignal_price;
    }

    public void setOrignal_price(int orignal_price) {
        this.orignal_price = orignal_price;
    }

    public int getPacking_size() {
        return packing_size;
    }

    public void setPacking_size(int packing_size) {
        this.packing_size = packing_size;
    }

    public String getPacking_type() {
        return packing_type;
    }

    public void setPacking_type(String packing_type) {
        this.packing_type = packing_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

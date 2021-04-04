package com.dattaprabodhinee.estore.Models;

public class CategoriesModel {
    int id;
    String name, category_img;

    public CategoriesModel(int id, String name, String category_img) {
        this.id = id;
        this.name = name;
        this.category_img = category_img;
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

    public String getCategory_img() {
        return category_img;
    }

    public void setCategory_img(String category_img) {
        this.category_img = category_img;
    }
}

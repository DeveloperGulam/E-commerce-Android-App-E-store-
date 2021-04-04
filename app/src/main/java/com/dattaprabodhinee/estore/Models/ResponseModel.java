package com.dattaprabodhinee.estore.Models;

public class ResponseModel {
    ProductModel productInfo;
    String response;

    public ResponseModel(String response) {
        this.response = response;
    }

    public ResponseModel(ProductModel productInfo, String response) {
        this.productInfo = productInfo;
        this.response = response;
    }

    public ProductModel getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductModel productInfo) {
        this.productInfo = productInfo;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

package com.dattaprabodhinee.estore.Models;

public class BannerModel {
    int bannerPic;
    String productTitle, productOffer;

    public BannerModel(int bannerPic, String productTitle, String productOffer) {
        this.bannerPic = bannerPic;
        this.productTitle = productTitle;
        this.productOffer = productOffer;
    }

    public int getBannerPic() {
        return bannerPic;
    }

    public void setBannerPic(int bannerPic) {
        this.bannerPic = bannerPic;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductOffer() {
        return productOffer;
    }

    public void setProductOffer(String productOffer) {
        this.productOffer = productOffer;
    }
}

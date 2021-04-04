package com.dattaprabodhinee.estore.Models;

public class IntroModel {
    int introImage;
    String title, description;

    public IntroModel(int introImage, String title, String description) {
        this.introImage = introImage;
        this.title = title;
        this.description = description;
    }

    public int getIntroImage() {
        return introImage;
    }

    public void setIntroImage(int introImage) {
        this.introImage = introImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

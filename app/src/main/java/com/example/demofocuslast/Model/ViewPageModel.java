package com.example.demofocuslast.Model;

public class ViewPageModel {
    private String homeName, desc;
    private int imageHome;
    private int homePrice;
    private int buttonGet;

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImageHome() {
        return imageHome;
    }

    public void setImageHome(int imageHome) {
        this.imageHome = imageHome;
    }

    public int getHomePrice() {
        return homePrice;
    }

    public void setHomePrice(int homePrice) {
        this.homePrice = homePrice;
    }

    public int getButtonGet() {
        return buttonGet;
    }

    public void setButtonGet(int buttonGet) {
        this.buttonGet = buttonGet;
    }

    public ViewPageModel() {
    }

    public ViewPageModel(String homeName, String desc, int imageHome, int homePrice, int buttonGet) {
        this.homeName = homeName;
        this.desc = desc;
        this.imageHome = imageHome;
        this.homePrice = homePrice;
        this.buttonGet = buttonGet;
    }
}

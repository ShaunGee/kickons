package com.example.kickons.home;

public class HomeItem {
    private Integer id;
    private String title;
    private String caption;
    private String price;
    private String img;


    public HomeItem(Integer id, String title, String caption, String price, String img) {
        this.id = id;
        this.title= title;
        this.caption = caption;
        this.price = price;
        this.img = img;

    }


    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCaption() {
        return caption;
    }

    public String getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }
}
package com.example.kickons;

public class SaleItem {

    private String name;
    private String location;
    private String price;


    public SaleItem(String name, String location, String price) {
        this.name = name;
        this.location = location;
        this.price = price;

    }


    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "SaleItem{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", price=" + String.valueOf(price) +
                '}';
    }


}

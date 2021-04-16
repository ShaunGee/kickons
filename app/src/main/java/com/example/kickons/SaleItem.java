package com.example.kickons;

public class SaleItem {

    private String name;
    private String location;
    private float price;

    public static final SaleItem[] drink = {
            new SaleItem("vodka", "Mooroobool", 15.50f),
            new SaleItem("Carton of Northern", "Palmcove", 50f),
            new SaleItem("Rum", "White Rock", 60f),
            new SaleItem("Carton of double black", "Bayview", 80f),
            new SaleItem("Goon", "Gordonvale", 10.50f),
    };

    public SaleItem(String name, String location, float price) {
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

    public float getPrice() {
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

    public SaleItem[] getList(){
        return drink;
    }
}

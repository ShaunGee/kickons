package com.example.kickons.delivery;

import android.location.Address;
import android.location.Geocoder;

public class DeliveryDetails {
    Integer deliveryId, item_id, user_id;
    Double delivery_longtitude, delivery_latitude;
    Boolean delivered, on_route;
    String item_img, item_title;
    Address address;


    public DeliveryDetails(Integer deliveryId, Boolean on_route, Boolean delivered, Double delivery_longtitude,
                           Double delivery_latitude, Integer item_id, Integer user_id, String item_img, String item_title, Address address) {

        this.deliveryId = deliveryId;
        this.on_route = on_route;
        this.delivered = delivered;
        this.delivery_latitude = delivery_latitude;
        this.delivery_longtitude = delivery_longtitude;
        this.user_id = user_id;
        this.item_id = item_id;
        this.item_img = item_img;
        this.item_title = item_title;
        this.address = address;


    }


    public Address getAddress() {

        return address;
    }

    public Integer getDeliveryId(){
        return deliveryId;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public Double getDelivery_longtitude() {
        return delivery_longtitude;
    }

    public Double getDelivery_latitude() {
        return delivery_latitude;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public Boolean getOn_route() {
        return on_route;
    }

    public String getItem_img() {
        return item_img;
    }

    public String getItem_title() {
        return item_title;
    }

}

package com.example.kickons;


public class Validate {

    public boolean isString(String word){
        boolean string = false;
        if (word != null){
            string = true;
        }
        return string;
    }


    public boolean isPrice(Float price){
        boolean inputPrice = false;
        if (price != null){
            inputPrice = true;
        }
        return inputPrice;
    }


}

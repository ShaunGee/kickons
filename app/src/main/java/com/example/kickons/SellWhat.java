package com.example.kickons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SellWhat extends AppCompatActivity {

    EditText item, location, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_what);

        //TODO: create database
        //TODO: add data to database
        //TODO: go to veiw items page after added to database

        item = findViewById(R.id.item_input);
        location = findViewById(R.id.location_input);
        price = findViewById(R.id.price__input);
    }
}
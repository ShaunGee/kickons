package com.example.kickons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SellWhat extends AppCompatActivity {

    EditText input_item, input_location, input_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_what);

        //TODO: create database x
        //TODO: add data to database x ive coded but havnt tested yet
        //TODO: access database and display entered information somehow. Use cursor
        //TODO: go to veiw items page after added to database

        input_item = findViewById(R.id.item_input);
        input_location = findViewById(R.id.location_input);
        input_price = findViewById(R.id.price__input);
    }

    public void sell_buttion(View view){





    }
}
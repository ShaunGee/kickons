package com.example.kickons;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SellWhat extends AppCompatActivity {

    EditText input_item, input_location, input_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_what);

        //TODO: create database x
        //TODO: add data to database x ive coded but havnt tested yet x
        //TODO: access database and display entered information somehow. Use cursor
        //TODO: go to veiw items page after added to database

        input_item = findViewById(R.id.item_input);
        input_location = findViewById(R.id.location_input);
        input_price = findViewById(R.id.price__input);
    }

    public void add_sell_item_button(View view){


        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db  = databaseHelper.getWritableDatabase();



        databaseHelper.addItemToDb(db,input_item.getText().toString(),input_location.getText().toString(), input_price.getText().toString());

        db.close();



    }
}
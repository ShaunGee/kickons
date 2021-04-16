package com.example.kickons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void sell_button(View view){
        Intent sell = new Intent(MainActivity.this, SellWhat.class);
        startActivity(sell);
    }



    public void buy_button(View view){
        Intent buy = new Intent(MainActivity.this, BuyPage.class);
        startActivity(buy);
    }

}
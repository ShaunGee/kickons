package com.example.kickons.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.kickons.R;

public class HomeActivity extends AppCompatActivity {

    RecyclerView homepageRecyclerView;
    HomeSaleItemsAdapter homeSaleItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homepageRecyclerView = (RecyclerView) findViewById(R.id.home_page_recyclerView);
        homeSaleItemsAdapter = new HomeSaleItemsAdapter(this);
        homepageRecyclerView.setAdapter(homeSaleItemsAdapter);




    }
}
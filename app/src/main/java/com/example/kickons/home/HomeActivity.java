package com.example.kickons.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
        FragmentManager fragmentManager = getSupportFragmentManager();
        ItemDisplayFragment itemDisplayFragment = new ItemDisplayFragment();
        fragmentManager.beginTransaction().add( R.id.home_page_frameLayout,ItemDisplayFragment.newInstance(null,null)).commit();

    }
}
package com.example.kickons.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

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
        Bundle bundle = new Bundle();
        bundle.putInt("user_id", getIntent().getIntExtra("user_id", 0));
        itemDisplayFragment.setArguments(bundle);
        fragmentManager.beginTransaction().add( R.id.home_page_frameLayout,itemDisplayFragment).commit();

    }

}
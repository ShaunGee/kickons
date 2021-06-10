package com.example.kickons.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.kickons.R;

public class DeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        DeliveryListFragment deliveryListFragment = new DeliveryListFragment();


        getSupportFragmentManager().beginTransaction().add(R.id.delivery_activity_frame_layout, deliveryListFragment).commit();



    }
}
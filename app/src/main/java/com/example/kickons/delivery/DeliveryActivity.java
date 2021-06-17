package com.example.kickons.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.kickons.R;

public class DeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        DeliveryListFragment deliveryListFragment = new DeliveryListFragment();

        Intent intent = getIntent();

        Bundle bundle = new Bundle();
        bundle.putInt("deliverer_id",(Integer) intent.getIntExtra("user_id", 0));

        deliveryListFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.delivery_activity_frame_layout, deliveryListFragment).commit();



    }
}
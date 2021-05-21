package com.example.kickons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.FrameLayout;


public class LogInActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        FragmentManager fragmentManager  = getSupportFragmentManager();
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.login_page_frame);
        fragmentManager.beginTransaction().add(R.id.login_page_frame, LogInFragment.newInstance(), null).addToBackStack(null).commit();






    }


}
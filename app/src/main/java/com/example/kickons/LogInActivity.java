package com.example.kickons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;


public class LogInActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        FragmentManager fragmentManager  = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(LogInFragment.newInstance(), null).commit();



    }


}
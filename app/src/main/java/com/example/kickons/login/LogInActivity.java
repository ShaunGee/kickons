package com.example.kickons.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.kickons.R;
import com.example.kickons.login.LogInFragment;


public class LogInActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //Start up the login Fragment
        FragmentManager fragmentManager  = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.login_page_frame, LogInFragment.newInstance(), null).addToBackStack(null).commit();


    }


}
package com.example.kickons;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogInFragment extends Fragment implements View.OnClickListener{

    Button loginBtn;
    TextView register, forgotPwd;
    Account userGoogleAccount;
    UserAccountManager userAccountManager;


    EditText userName;

    public LogInFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LogInFragment newInstance() {
        LogInFragment fragment = new LogInFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userAccountManager = new UserAccountManager(getActivity());


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_log_in, container, false);

        // Inflate the layout for this fragment
        return v;
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        loginBtn = (Button) getActivity().findViewById(R.id.login_login_button);
        forgotPwd  = (TextView) getActivity().findViewById(R.id.login_forgot_password);
        register = (TextView) getActivity().findViewById(R.id.login_register);

        userName = (EditText) getActivity().findViewById(R.id.login_username);

        userName.setOnClickListener(this);

        forgotPwd.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){


            case (R.id.login_register):
                getFragmentManager().beginTransaction().replace(R.id.login_page_frame, RegistrationFragment.newInstance()).addToBackStack(null).commit();
                //userAccountManager.addUserAccount();
                break;

            case (R.id.login_forgot_password):
                System.out.println("forgot password");
                break;
            case (R.id.login_username):
                System.out.println("ontouch being called");
                //Intent intent = userAccountManager.getUserAccounts(new String[] {"com.google"});
                //startActivity(intent);
                break;
        }
    }



}


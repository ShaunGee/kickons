package com.example.kickons.login;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kickons.R;
import com.example.kickons.UserAccountManager;
import com.example.kickons.delivery.DeliveryActivity;
import com.example.kickons.home.HomeActivity;
import com.example.kickons.registration.RegistrationEssential;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogInFragment extends Fragment implements View.OnClickListener{

    Button loginBtn;
    TextView register, forgotPwd;
    EditText userName,password;
    Account userGoogleAccount;
    UserAccountManager userAccountManager;



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
        password = (EditText) getActivity().findViewById(R.id.login_password);

        //set onclick listeners
        loginBtn.setOnClickListener(this);
        userName.setOnClickListener(this);
        forgotPwd.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){


            case (R.id.login_register):

                getFragmentManager().beginTransaction().replace(R.id.login_page_frame, RegistrationEssential.newInstance()).addToBackStack(null).commit();
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

            case (R.id.login_login_button):
                LoginJsonPost loginJsonPost = new LoginJsonPost(getContext(), userName, password);
                loginJsonPost.login();

                //Check to see if credentials are verified
                //Check to see if user is a driver or buyer
                //point them to correct activity


                break;
        }
    }



}


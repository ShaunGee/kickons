package com.example.kickons;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment implements View.OnClickListener {

    TextView firstName, lastName, email, mobile, age, gender;
    Button registerBtn;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RegistrationFragment newInstance() {
        RegistrationFragment fragment = new RegistrationFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firstName = (TextView) getActivity().findViewById(R.id.registration_first_name);
        lastName = (TextView) getActivity().findViewById(R.id.registration_last_name);
        email = (TextView) getActivity().findViewById(R.id.registration_email);
        mobile = (TextView) getActivity().findViewById(R.id.registration_phone);
        gender = (TextView) getActivity().findViewById(R.id.registration_gender);
        age = (TextView) getActivity().findViewById(R.id.registration_age);

        registerBtn = (Button) getActivity().findViewById(R.id.registration_btn);
        registerBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (firstName.getText().toString().isEmpty()){
            firstName.setError("First Name is required");
            firstName.requestFocus();
            return;
        }
        if (lastName.getText().toString().isEmpty()){
            lastName.setError("Last Name is required");
            lastName.requestFocus();
            return;
        }
        if (email.getText().toString().isEmpty()){
            email.setError("email is required");
            email.requestFocus();
            return;


        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            email.setError("Valid email is required");
            email.requestFocus();
            return;
        }
        if (mobile.getText().toString().isEmpty()){
            mobile.setError("Mobile is required");
            mobile.requestFocus();
            return;
        }
        if (gender.getText().toString().isEmpty()){
            gender.setError("Gender is required");
            gender.requestFocus();
            return;
        }
        if (age.getText().toString().isEmpty()){
            age.setError("Age is required");
            age.requestFocus();
            return;
        }

        System.out.println("register");
    }
}
package com.example.kickons.registration;

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

import com.example.kickons.PasswordSecurity;
import com.example.kickons.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationEssential#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationEssential extends Fragment implements View.OnClickListener {

    Button nextBtn;
    TextView email, pwd1, pwd2;

    public RegistrationEssential() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RegistrationEssential newInstance() {
        RegistrationEssential fragment = new RegistrationEssential();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nextBtn = (Button) getActivity().findViewById(R.id.registration_next_btn1);
        email = (TextView) getActivity().findViewById(R.id.registration_email);
        pwd1 = (TextView) getActivity().findViewById(R.id.registration_pwd1);
        pwd2 = (TextView) getActivity().findViewById(R.id.registration_pwd2);

        nextBtn.setOnClickListener(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_registration_essential, container, false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.registration_next_btn1):
                if (checkEntries()) {
                    Bundle bundle = new Bundle();
                    PasswordSecurity ps = new PasswordSecurity();
                    String hashedPs = ps.generateHash(pwd1.getText().toString());
                    bundle.putString("email", email.getText().toString());
                    bundle.putString("password", hashedPs);

                    Registration_Essential2 registration_essential2 = new Registration_Essential2();
                    registration_essential2.setArguments(bundle);

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_page_frame, registration_essential2)
                            .addToBackStack(null).commit();
                }
        }
    }

    private boolean checkEntries() {
        boolean check = false;


        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Valid email is required");
            email.requestFocus();
            return check;

        }

        if (!pwd1.getText().toString().equals(pwd2.getText().toString())){
            pwd1.requestFocus();
            pwd2.requestFocus();
            pwd2.setError("Passwords must match");

            pwd1.clearComposingText();
            pwd2.clearComposingText();
            return check;
        }

        check = true;
        return check;
    }

}
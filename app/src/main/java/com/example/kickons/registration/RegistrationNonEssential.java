package com.example.kickons.registration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kickons.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationNonEssential#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationNonEssential extends Fragment implements View.OnClickListener {

    TextView age, gender;
    Button registrationBtn;
    Bundle bundle;
    String inputAge, inputGender;


    public RegistrationNonEssential() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RegistrationNonEssential newInstance() {
        RegistrationNonEssential fragment = new RegistrationNonEssential();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = this.getArguments();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        gender = (TextView) getActivity().findViewById(R.id.registration_gender);
        age = (TextView) getActivity().findViewById(R.id.registration_age);
        registrationBtn = (Button) getActivity().findViewById(R.id.register_btn);
        registrationBtn.setOnClickListener(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration_non_essential, container, false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.register_btn):

                entriesEmpty();

                    bundle.putString("age",inputAge );
                    bundle.putString("gender",inputGender );



                RegistrationJSONPost registrationJSONPost = new RegistrationJSONPost(getContext(),
                        bundle.getString("fname"),
                        bundle.getString("lname"),
                        bundle.getString("email"),
                        bundle.getString("mobile"),
                        bundle.getString("age"),
                        bundle.getString("gender"),
                        bundle.getString("password")


                );

                registrationJSONPost.post();


                   // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_page_frame, LogInFragment.newInstance())
                     //       .commit();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bundle.clear();
    }

    private void entriesEmpty() {
        inputAge = age.getText().toString();
        inputGender = gender.getText().toString();


        if (age.getText().toString().isEmpty()){
            inputAge = "unspecified";
        }

        if (gender.getText().toString().isEmpty()){
            inputGender = "unspecified";

        }

    }
}
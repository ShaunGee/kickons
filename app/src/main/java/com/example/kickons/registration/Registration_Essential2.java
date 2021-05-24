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
 * Use the {@link Registration_Essential2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registration_Essential2 extends Fragment implements View.OnClickListener {

    Button nextBtn;
    Bundle bundle;

    TextView fname, lname, mobile;

    public Registration_Essential2() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Registration_Essential2 newInstance() {
        Registration_Essential2 fragment = new Registration_Essential2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = this.getArguments();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration__essential2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nextBtn = (Button) getActivity().findViewById(R.id.registration_next_btn2);
        fname = (TextView) getActivity().findViewById(R.id.registration_first_name);
        lname = (TextView) getActivity().findViewById(R.id.registration_last_name);
        mobile = (TextView) getActivity().findViewById(R.id.registration_phone);

        nextBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.registration_next_btn2):
                if (checkEntries()) {
                    bundle.putString("fname",fname.getText().toString() );
                    bundle.putString("lname",lname.getText().toString() );
                    bundle.putString("mobile",mobile.getText().toString() );

                    RegistrationNonEssential registrationNonEssential = new RegistrationNonEssential();
                    registrationNonEssential.setArguments(bundle);

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_page_frame, registrationNonEssential)
                            .addToBackStack(null).commit();
                }
        }
    }

    private boolean checkEntries() {
        boolean check = false;
        if (fname.getText().toString().isEmpty()){
            fname.setError("First Name is empty");
            fname.requestFocus();
            return check;
        }
        if (lname.getText().toString().isEmpty()){
            lname.setError("Last Name is empty");
            lname.requestFocus();
            return check;
        }
        if (mobile.getText().toString().isEmpty()){
            mobile.setError("Mobile is empty");
            mobile.requestFocus();
            return check;
        }
        check = true;
        return check;
    }


}
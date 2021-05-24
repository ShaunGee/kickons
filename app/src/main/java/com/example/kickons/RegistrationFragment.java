package com.example.kickons;

import android.content.Context;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;


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
        if (checkRegistrationInput()){
            //code that executes if input is valid
            RegistrationJSONPost registrationJSONPost = new RegistrationJSONPost(getContext(),firstName.getText().toString(),
                    lastName.getText().toString(),email.getText().toString(),mobile.getText().toString(),
                    age.getText().toString(),gender.getText().toString());

            registrationJSONPost.post();
        }
        else{
            Toast.makeText(getContext(), "Couldn't Register", Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkRegistrationInput(){
        //returns true if all information is correct
        boolean check = false;
        if (firstName.getText().toString().isEmpty()){
            firstName.setError("First Name is required");
            firstName.requestFocus();
            return check;
        }
        if (lastName.getText().toString().isEmpty()){
            lastName.setError("Last Name is required");
            lastName.requestFocus();
            return check;
        }
        if (email.getText().toString().isEmpty()){
            email.setError("email is required");
            email.requestFocus();
            return check;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            email.setError("Valid email is required");
            email.requestFocus();
            return check;
        }
        if (mobile.getText().toString().isEmpty()){
            mobile.setError("Mobile is required");
            mobile.requestFocus();
            return check;
        }
        if (gender.getText().toString().isEmpty()){
            gender.setError("Gender is required");
            gender.requestFocus();
            return check;
        }
        if (age.getText().toString().isEmpty()){
            age.setError("Age is required");
            age.requestFocus();
            return check;
        }

        System.out.println("register");
        check =true;

        return check;
    }
}

class RegistrationJSONPost {
        private final String firstName,lastName,email,mobile,age,gender;

        Context context;

    public RegistrationJSONPost(Context context, String firstName, String lastName, String email, String mobile, String age, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.age = age;
        this.gender = gender;

        this.context = context;
    }

    public void post(){
        JSONObject postData = new JSONObject();
        try {
            postData.put("f_name", firstName);
            postData.put("l_name", lastName);
            postData.put("email", email);
            postData.put("mobile", mobile);
            postData.put("age", age);
            postData.put("gender", gender);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, NetworkConstants.SERVER_POST_URL,
                postData, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("could not POST at this time. Error: "+ error);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }
}

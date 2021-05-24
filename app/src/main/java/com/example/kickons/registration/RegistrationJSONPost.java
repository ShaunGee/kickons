package com.example.kickons.registration;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kickons.NetworkConstants;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationJSONPost {
        private final String firstName,lastName,email,mobile,age,gender, password;

        Context context;

    public RegistrationJSONPost(Context context, String firstName, String lastName, String email, String mobile, String age, String gender, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.age = age;
        this.gender = gender;
        this.password = password;

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
            postData.put("password", password);




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

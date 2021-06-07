package com.example.kickons.login;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kickons.LandingActivity;
import com.example.kickons.NetworkConnection;
import com.example.kickons.NetworkConstants;
import com.example.kickons.PasswordSecurity;
import com.example.kickons.home.HomeActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginJsonPost {


    Context context;
    EditText username, password;


    LoginJsonPost(Context context, EditText username, EditText password) {

        this.username = username;
        this.password = password;
        this.context = context;

    }

    public void login(){
        JSONObject postData = new JSONObject();

        PasswordSecurity ps = new PasswordSecurity();
        String hashedPwd = ps.generateHash(password.getText().toString());

        try {
            postData.put("email", username.getText().toString());
            postData.put("password", hashedPwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, NetworkConstants.SERVER_LOG_IN_VERIFICATION,
                postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(context, (String) response.get("status"), Toast.LENGTH_LONG).show();
                    String r = (String) response.get("status");
                    if (r.contains("logged in")){

                        Intent intent = new Intent(context, HomeActivity.class);
                        intent.putExtra("user_id", (Integer) response.get("user_id"));
                        intent.putExtra("user_fname", (String) response.get("f_name"));
                        intent.putExtra("user_lname", (String) response.get("l_name"));
                        intent.putExtra("user_email", (String) response.get("email"));

                        context.startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("could not POST at this time. Error: "+ error);

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);

    }


}

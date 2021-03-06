package com.example.kickons;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

import static androidx.core.content.ContextCompat.getSystemService;

public class NetworkConnection {

    String url;
    JSONObject jsonObject;

RequestQueue requestQueue;



    public NetworkConnection(Context context) {
       requestQueue = Volley.newRequestQueue(context);
       //url = "http://192.168.8.104:8000/kickons_inventory/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, NetworkConstants.SERVER_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    System.out.println("first name is:  "+ response.get("f_name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                jsonObject =response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("json error: "+ error);
            }
        });


        requestQueue.add(jsonObjectRequest);
        //TODO: Password needs hashing and salt
        //TODO: add password to registration form
        //TODO: use firebase to authenticate users

    }

}
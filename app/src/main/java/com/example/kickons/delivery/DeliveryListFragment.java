package com.example.kickons.delivery;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kickons.NetworkConstants;
import com.example.kickons.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class DeliveryListFragment extends Fragment {


    public DeliveryListFragment() {
        // Required empty public constructor
    }


    public static DeliveryListFragment newInstance() {
        DeliveryListFragment deliveryListFragment = new DeliveryListFragment();
        return deliveryListFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getItemsFromServer();


    }

    private void getItemsFromServer() {

        List<DeliveryDetails> dd = new LinkedList<>();
        JsonArrayRequest request = new JsonArrayRequest(JsonArrayRequest.Method.GET, NetworkConstants.SERVER_GET_DELIVERIES_ON_ROUTE, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i <= response.length(); i++) {
                    try {
                        Integer delivery_id = (Integer) response.getJSONObject(i).get("id");
                        Boolean on_route = (Boolean) response.getJSONObject(i).get("on_route");
                        Boolean delivered = (Boolean) response.getJSONObject(i).get("delivered");
                        Double delivery_long = (Double) response.getJSONObject(i).get("delivery_longtitude");
                        Double delivery_lat = (Double) response.getJSONObject(i).get("delivery_latitude");
                        Integer item_id = (Integer) response.getJSONObject(i).getJSONObject("item_id").get("id");
                        Integer user_id = (Integer) response.getJSONObject(i).get("user_id");
                        String item_img = (String) response.getJSONObject(i).getJSONObject("item_id").get("item_image");
                        String item_title = (String) response.getJSONObject(i).getJSONObject("item_id").get("item_title");
                        //Geocoder geocoder = new Geocoder(getContext());
                        //Address deliveryAddress = geocoder.getFromLocation(delivery_lat, delivery_long,1).get(0);


                        dd.add(new DeliveryDetails(
                                delivery_id,
                                on_route,
                                delivered,
                                delivery_long,
                                delivery_lat,
                                item_id,
                                user_id,
                                item_img,
                                item_title


                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
                listItems(dd);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("the error is: " + error);
            }
        });
        Volley.newRequestQueue(getContext()).add(request);
    }

    //Method that creates adapter list in recylerview
    private void listItems(List<DeliveryDetails> dd) {

        RecyclerView recyclerView = getActivity().findViewById(R.id.delivery_list_recycler_view);
        DeliveryListAdapter deliveryListAdapter = new DeliveryListAdapter(dd, getFragmentManager(), getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(deliveryListAdapter);

    }
}
package com.example.kickons.delivery;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.kickons.NetworkConstants;
import com.example.kickons.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DeliveryDetailDisplayFragment extends Fragment implements View.OnClickListener {

    CardView deliveryItemDetailCardView;
    DeliveryMapFragment deliveryMapFragment;
    DeliveryDetails deliveryDetails;
    Bundle bundle;
    Button accept;
    Double currentLat, currentLong;


    public DeliveryDetailDisplayFragment() {
        // Required empty public constructor
    }

    public static DeliveryDetailDisplayFragment newInstance() {
        DeliveryDetailDisplayFragment fragment = new DeliveryDetailDisplayFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deliveryMapFragment = new DeliveryMapFragment();


        //Get Selected delivery details from previous fragment
        bundle = getArguments();
        deliveryDetails = (DeliveryDetails) bundle.getSerializable("selected_delevery_details");



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        deliveryItemDetailCardView = getActivity().findViewById(R.id.delivery_detail_display_item_details_cardview);

        //Add bundle data so map can recieve coordinates of delivery
        deliveryMapFragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction().add(R.id.delivery_detail_display_frame_layout_top, deliveryMapFragment).commit();
        populateCardView();

        accept = (Button) getActivity().findViewById(R.id.delivery_detail_display_item_details_accept_btn);
        accept.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery_detail_display, container, false);
    }

    private void populateCardView(){

            Address deliveryAddress = generateAddress();

            TextView itemName = deliveryItemDetailCardView.findViewById(R.id.delivery_card_view_on_item_name);
            TextView address = deliveryItemDetailCardView.findViewById(R.id.delivery_card_view_on_delivery_address);
            TextView onRoute = deliveryItemDetailCardView.findViewById(R.id.delivery_card_view_on_route);
            ImageView itemImg = deliveryItemDetailCardView.findViewById(R.id.delivery_card_view_on_item_image);

            itemName.setText(deliveryDetails.getItem_title());
            address.setText(deliveryAddress.getAddressLine(0));
            onRoute.setText(deliveryDetails.getItem_title());
            Picasso.get().load(deliveryDetails.getItem_img()).into(itemImg);


    }

    private Address generateAddress(){
        try {

        Geocoder geocoder = new Geocoder(getContext());
        Double deliveryLat = deliveryDetails.getDelivery_latitude();
        Double deliveryLong = deliveryDetails.getDelivery_longtitude();
        return geocoder.getFromLocation(deliveryLat, deliveryLong,1).get(0);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.delivery_detail_display_item_details_accept_btn){

            //check db to see if on_route still false which indicates that it is still available.
            //If True then notify user that delivery not available anymore via TOAST notification
            compareDbDeliveryIsOnRoute(deliveryDetails.getDeliveryId(),deliveryDetails.getOn_route());


        }
    }

    private void compareDbDeliveryIsOnRoute(Integer deliveryId,Boolean localOnRoute){


        //add delivery_id to the end of SERVER_GET_VERIFY_DELIVERIES_ON_ROUTE so that it response will be for that specific Delivery
        String url =  String.format("%s%s/", NetworkConstants.SERVER_GET_DELIVERIES,String.valueOf(deliveryDetails.getDeliveryId()));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url ,null, checkDeliveryJsonListener, errorListener);
        Volley.newRequestQueue(getContext()).add(request);



    }

    private void updateOnRoute(Boolean onRoute){
        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("id", deliveryDetails.getDeliveryId());
            jsonObject.put("on_route", true);


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, NetworkConstants.SERVER_UPDATE_ROUTE_STATUS, jsonObject, null, errorListener);
            Volley.newRequestQueue(getContext()).add(request);
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("error in connecting");
        }
    }




    Response.Listener<JSONObject> checkDeliveryJsonListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                if ((Boolean) response.get("on_route") == deliveryDetails.getOn_route()){
                    //update db on_route to true so other delivery users wont be able to take it
                    updateOnRoute(true);

                    //enter Deliverer model with instance of this delivery
                    createDeliveryForCurrentUser();


                }
                else{
                    Toast.makeText(getContext(), "Sorry this delivery isn't available anymore", Toast.LENGTH_LONG).show();
                }
            }
            catch (JSONException e){
                e.printStackTrace();
            }

        }
    };

    private void createDeliveryForCurrentUser() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user",deliveryDetails.getDeliverer_id());
            jsonObject.put("delivery_details_id", deliveryDetails.getDeliveryId());

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, NetworkConstants.SERVER_GET_DELIVERIES_OF_A_USER,
                    jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    DeliveryCurrentJobs dm = new DeliveryCurrentJobs();
                    dm.setArguments(bundle);
                    getParentFragmentManager().beginTransaction().replace(R.id.delivery_activity_frame_layout, dm).commit();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            Volley.newRequestQueue(getContext()).add(request);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    Response.ErrorListener errorListener =  new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    };

}
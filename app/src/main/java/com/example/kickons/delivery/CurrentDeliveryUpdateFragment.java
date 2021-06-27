package com.example.kickons.delivery;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kickons.NetworkConstants;
import com.example.kickons.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;

public class CurrentDeliveryUpdateFragment extends Fragment implements View.OnClickListener {
    Bundle bundle;
    DeliveryDetails selectedDeliveryDetail;

    public CurrentDeliveryUpdateFragment() {
        // Required empty public constructor
    }


    public static CurrentDeliveryUpdateFragment newInstance() {
        CurrentDeliveryUpdateFragment fragment = new CurrentDeliveryUpdateFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get data from bundle and store in local
        this.bundle = getArguments();
        assert getArguments() != null;
        this.selectedDeliveryDetail = (DeliveryDetails) getArguments().getSerializable("delivery_detail_edit");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_delivery_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button cancelbtn = getActivity().findViewById(R.id.cancel_job_btn);
        Button completebtn = getActivity().findViewById(R.id.job_completed_btn);

        populateCardview();

        cancelbtn.setOnClickListener(this);
        completebtn.setOnClickListener(this);

    }

    private void populateCardview() {
        try {
        CardView c = getActivity().findViewById(R.id.current_delivery_update_cardview);
        Address address = null;

        TextView deliveryonRoute = c.findViewById(R.id.delivery_card_view_on_route);
        TextView deliveryItemName = c.findViewById(R.id.delivery_card_view_on_item_name);
        TextView deliveryAddress = c.findViewById(R.id.delivery_card_view_on_delivery_address);
        ImageView deliveryItemImg = c.findViewById(R.id.delivery_card_view_on_item_image);

        deliveryonRoute.setText(String.valueOf(selectedDeliveryDetail.getOn_route()));
        deliveryItemName.setText(String.valueOf(selectedDeliveryDetail.getItem_title()));
        Picasso.get().load(selectedDeliveryDetail.getItem_img()).into(deliveryItemImg);

        //set Textview address result using the lat and long values from DeliveryDetails
        Geocoder geocoder = new Geocoder(getContext());
        Double deliveryLat = selectedDeliveryDetail.getDelivery_latitude();
        Double deliveryLong = selectedDeliveryDetail.getDelivery_latitude();

        address = geocoder.getFromLocation(deliveryLat,deliveryLong, 1).get(0);
        deliveryAddress.setText(address.getAddressLine(0));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cancel_job_btn){
            /*
            cancelJob() will delete the Deliverer.deleveryDetail from Deliverer model
                -   this will be done by a simple delete() based on the deliverydetail.id
             */

            cancelJob();

        }else if (view.getId()==R.id.job_completed_btn){
                        /*
            completejob() will set the Delivery.onroute value to false in db and Delivery.delivered to true
             */
            completejob();
        }
    }

    private void completejob() {
        try {
            String url = String.format(Locale.getDefault(), "%s%d/",  NetworkConstants.SERVER_GET_DELIVERIES, selectedDeliveryDetail.getDeliveryId());
            JSONObject jsonObject = new JSONObject();
            //set the Delivery.onroute value to false in db and Delivery.delivered to true
            //jsonObject.put("deliverer_id", selectedDeliveryDetail.getDeliveryId());
            jsonObject.put("delivered",true);
            jsonObject.put("on_route", false);

            //send request to server
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PATCH, url,
                    jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getContext(), String.format(Locale.getDefault(),
                            "item id: %d has been delivered", selectedDeliveryDetail.getDeliveryId()), Toast.LENGTH_LONG).show();
                    returnToMain();


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("this is the error: " + error);
                }
            });
            Volley.newRequestQueue(getContext()).add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void cancelJob() {
        try {
            String url = String.format(Locale.getDefault(),"%s%d/",  NetworkConstants.SERVER_SET_DELIVERIES_OF_A_USER, selectedDeliveryDetail.getDeliverer_id());

            JSONObject jsonObject = new JSONObject();
            //put deliveryDetail.id to json request and deliverer.id
            jsonObject.put("Deliverer_id", selectedDeliveryDetail.getDeliverer_id());
            jsonObject.put("DeliveryDetails_id", selectedDeliveryDetail.getDeliveryId());
            jsonObject.put("on_route", false);
            //jsonObject.put("deliverer_id", selectedDeliveryDetail.getDeliveryId());

            //send request to server
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, NetworkConstants.SERVER_CANCEL_DELIVERY,
                    jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getContext(), String.format(Locale.getDefault(), " %d removed", selectedDeliveryDetail.getDeliverer_id()), Toast.LENGTH_SHORT).show();
                    returnToMain();
                }
            }, null);

            Volley.newRequestQueue(getContext()).add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void returnToMain(){
        try {
            Thread.sleep(3000);

            Intent intent = new Intent(getContext(),DeliveryActivity.class);
            intent.putExtra("user_id", selectedDeliveryDetail.getDeliverer_id());
            getActivity().startActivity(intent);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
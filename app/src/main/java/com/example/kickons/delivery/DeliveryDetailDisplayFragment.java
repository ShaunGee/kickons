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
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.kickons.R;

import java.io.IOException;

public class DeliveryDetailDisplayFragment extends Fragment {

    CardView deliveryItemDetailCardView;
    DeliveryMapFragment deliveryMapFragment;
    DeliveryDetails deliveryDetails;
    Bundle bundle;

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
        getFragmentManager().beginTransaction().add(R.id.delivery_detail_display_frame_layout_top, deliveryMapFragment).commit();
        populateCardView();

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

            itemName.setText(deliveryDetails.getItem_title());
            address.setText(deliveryAddress.getAddressLine(0));
            onRoute.setText(deliveryDetails.getItem_title());


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
}
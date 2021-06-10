package com.example.kickons.delivery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.kickons.R;

public class DeliveryDetailDisplayFragment extends Fragment {

    FrameLayout frameLayoutTop, frameLayoutBottom;

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

        //DeliveryDetailDisplayFragment deliveryDetailDisplayFragment = new DeliveryDetailDisplayFragment();

        DeliveryMapFragment deliveryMapFragment = new DeliveryMapFragment();

        //getFragmentManager().beginTransaction().add(R.id.delivery_detail_display_frame_layout_top, deliveryDetailDisplayFragment).commit();
        getFragmentManager().beginTransaction().add(R.id.delivery_detail_display_frame_layout_bottom, deliveryMapFragment).commit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery_detail_display, container, false);
    }
}
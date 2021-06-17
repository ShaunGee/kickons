package com.example.kickons.delivery;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.kickons.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;


public class DeliveryCurrentJobs extends Fragment {

    FrameLayout listOfDeliveryMapFl, listOfDeliveryFl;
    Bundle bundle;


    public DeliveryCurrentJobs() {
        // Required empty public constructor
    }



    //deliverer o2o relationship with deliveries
    public static DeliveryCurrentJobs newInstance() {
        DeliveryCurrentJobs fragment = new DeliveryCurrentJobs();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bundle = getArguments();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery_current_jobs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DeliverylistOfDeliveriesOnMap map = new DeliverylistOfDeliveriesOnMap();
        DeliveryListOfAvailableJobs deliveryListOfAvailableJobs = new DeliveryListOfAvailableJobs();
        deliveryListOfAvailableJobs.setArguments(bundle);
        map.setArguments(bundle);

        getParentFragmentManager().beginTransaction().add(R.id.delivery_current_jobs_current_jobs_frame, deliveryListOfAvailableJobs).commit();
        getParentFragmentManager().beginTransaction().add(R.id.delivery_current_jobs_map_frame, map).commit();
    }


}
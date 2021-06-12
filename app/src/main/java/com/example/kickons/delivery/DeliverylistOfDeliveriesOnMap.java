package com.example.kickons.delivery;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kickons.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;


public class DeliverylistOfDeliveriesOnMap extends Fragment {

    LatLng userCurrentLocation;
    SupportMapFragment supportMapFragment;
    GoogleMap gmap;

    OnMapReadyCallback mapCallback = googleMap -> {
        gmap = googleMap;
        System.out.println("map being called");
       // googleMap.addMarker(new MarkerOptions().position(userCurrentLocation).title("Me"));
       // googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userCurrentLocation, 10f));
    };

    ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if (result){
                setUserCurrentLocationPermission();
            }
            else {
                //if user doesnt
            }
        }
    });

    private void updateMap(){

    }

    public DeliverylistOfDeliveriesOnMap() {
        // Required empty public constructor
    }


    public static DeliverylistOfDeliveriesOnMap newInstance() {
        DeliverylistOfDeliveriesOnMap fragment = new DeliverylistOfDeliveriesOnMap();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUserLocation();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync(mapCallback);


        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deliverylist_of_deliveries_on_map, container, false);
    }


    private void setUserLocation() {
        //permissions
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_DENIED){

            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)){
                System.out.println("tell user why");
            }
            else{
                activityResultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            }


        }
        else if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
            setUserCurrentLocationPermission();
        }
    }


    @SuppressLint("MissingPermission")
    private void setUserCurrentLocationPermission(){
        System.out.println("tdsfsdfs");
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                userCurrentLocation = new LatLng(location.getLatitude(),location.getLongitude());
                System.out.println("user location"+userCurrentLocation);


                //TODO: how to update map with location
            }
        });
    }


}
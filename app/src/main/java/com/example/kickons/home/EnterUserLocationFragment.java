package com.example.kickons.home;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kickons.R;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EnterUserLocationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnterUserLocationFragment extends Fragment implements View.OnClickListener {
    TextView results;
    Address locationAddress;

    EditText location;
    public EnterUserLocationFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static EnterUserLocationFragment newInstance(String param1, String param2) {
        EnterUserLocationFragment fragment = new EnterUserLocationFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enter_user_location, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        location = (EditText) view.findViewById(R.id.enter_user_location_address_input);
        Button nextBtn = (Button) view.findViewById(R.id.enter_user_location_address_input_next_btn);
        results = view.findViewById(R.id.enter_user_location_address_return);
        nextBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.enter_user_location_address_input_next_btn ){
            findAddress(location.getText().toString());
        }
    }

    private void findAddress(String address) {
        Geocoder geocoder = new Geocoder(getContext(),Locale.getDefault());
        //TODO: run this on a seperate thread because its doing too much work on the main thread
        try {
            locationAddress = geocoder.getFromLocationName(address, 5).get(0);

                 //System.out.println(geocoder.getFromLocationName(address, 5).size());
                 results.setText(locationAddress.getAddressLine(0));


            ItemPurchaseDetailFragment itemPurchaseDetailFragment = new ItemPurchaseDetailFragment();
            Bundle b = getArguments();
            b.putString("location",location.getText().toString());
            b.putDouble("location_long", locationAddress.getLongitude());
            b.putDouble("location_lat", locationAddress.getLatitude());
            itemPurchaseDetailFragment.setArguments(getArguments());
            getFragmentManager().beginTransaction().replace(R.id.home_page_frameLayout, itemPurchaseDetailFragment).
                    addToBackStack(null).commit();



        } catch (IOException e) {
            e.printStackTrace();

        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException){
            Toast.makeText(getContext(),"Enter a valid address", Toast.LENGTH_LONG).show();

        }
    }
}
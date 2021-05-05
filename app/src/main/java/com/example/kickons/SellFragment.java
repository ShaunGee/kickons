package com.example.kickons;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.service.autofill.OnClickAction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kickons.ui.main.SectionsPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellFragment extends Fragment {

    TextView input_item,input_location, input_price;


    public SellFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SellFragment newInstance() {
        SellFragment fragment = new SellFragment();
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
        View v = inflater.inflate(R.layout.fragment_sell, container, false);

        input_item = v.findViewById(R.id.item_input);
        input_location = v.findViewById(R.id.location_input);
        input_price = v.findViewById(R.id.price__input);


        v.findViewById(R.id.sell_button).setOnClickListener(view -> {

            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            SQLiteDatabase db = databaseHelper.getWritableDatabase();


            databaseHelper.addItemToDb(db, input_item.getText().toString(), input_location.getText().toString(), input_price.getText().toString());

            db.close();
        });

        //TODO: find out how to refresh buy page after adding something to db



        return v;
    }

/*

    public void add_sell_item_button(View view, ViewGroup container) {


        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = databaseHelper.getWritableDatabase();


        databaseHelper.addItemToDb(db, input_item.getText().toString(), input_location.getText().toString(), input_price.getText().toString());

        db.close();

        getLayoutInflater().inflate(R.layout.fragment_buy,container, false);

        //Intent intent = new Intent(getActivity(), MainActivity.class);
        //startActivity(intent);


    }

 */

}
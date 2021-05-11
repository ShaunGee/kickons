package com.example.kickons;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyFragment extends Fragment {

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    View li;

    ItemDisplayAdapter adapter;
    RecyclerView drinksRecycler;
    LinearLayoutManager linearLayoutManager;


    public BuyFragment() {

    }


    public static BuyFragment newInstance() {
        // Required empty public constructor
        BuyFragment fragment = new BuyFragment();
        //Bundle args = new Bundle();
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    /*
    I want to create a button that creates a new fragment containing edit info for the selected item that is called from the buy list
    this means the items ontouch will be called from the buyfragment class. use an implementation of the cardview ontouch event
     */





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        List<SaleItem> saleItemList = loadItemsFromDb();

        li = inflater.inflate(R.layout.fragment_buy, container, false);

        adapter = new ItemDisplayAdapter(saleItemList);
        drinksRecycler = li.findViewById(R.id.sale_recyclerView);

        //adapter.onTouchEvent(drinksRecycler, );
        //drinksRecycler.addOnItemTouchListener(new ItemDisplayAdapter.RecyclerOnTouch(container.getContext(),container, drinksRecycler));
        drinksRecycler.setAdapter(adapter);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        drinksRecycler.setLayoutManager(linearLayoutManager);

        return li;

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public List<SaleItem> loadItemsFromDb(){

        List<SaleItem> sI = new LinkedList<>();

        databaseHelper = new DatabaseHelper(getActivity());
        db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseContract.FeedEntry.TABLE_NAME, new String[]{DatabaseContract.FeedEntry._ID,
                DatabaseContract.FeedEntry.COLUMN_NAME_ITEM, DatabaseContract.FeedEntry.COLUMN_NAME_LOCATION,
                DatabaseContract.FeedEntry.COLUMN_NAME_PRICE}, null, null, null, null, null);

        /*
        use cursor to go through all units in db and add them to a list containing the 3 values. send those to the recylerview
         */

        while (cursor.moveToNext()) {
            sI.add(new SaleItem(cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        cursor.close();
        db.close();

        //System.out.println("this is the id of buy: "+getId());

        return sI;
    }




}
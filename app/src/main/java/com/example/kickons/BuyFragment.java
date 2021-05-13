package com.example.kickons;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    List<SaleItem> saleItemList;
    ViewPager viewPager;


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
        loadItemsFromDb();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        viewPager = container.findViewById(R.id.view_pager);

        li = inflater.inflate(R.layout.fragment_buy, container, false);

        adapter = new ItemDisplayAdapter(saleItemList);
        drinksRecycler = li.findViewById(R.id.sale_recyclerView);


        drinksRecycler.setAdapter(adapter);

        linearLayoutManager = new LinearLayoutManager(getActivity());

        //removeEntry();
        drinksRecycler.setLayoutManager(linearLayoutManager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                loadItemsFromDb();
                adapter = new ItemDisplayAdapter(saleItemList);
                drinksRecycler.setAdapter(adapter);

                linearLayoutManager = new LinearLayoutManager(getActivity());

                //removeEntry();
                drinksRecycler.setLayoutManager(linearLayoutManager);
                System.out.println("sdsdsd");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        return li;

    }



    public void loadItemsFromDb(){

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
        saleItemList = sI;
        //return sI;
    }




}
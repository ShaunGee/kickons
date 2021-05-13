package com.example.kickons;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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

/*
    public void removeEntry(){



        drinksRecycler.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View item = rv.findChildViewUnder(e.getX(),e.getY());
                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                SQLiteDatabase db = databaseHelper.getWritableDatabase();

                TextView name,price,location;
                name = (TextView) rv.findViewById(R.id.individual_item_name);
                price = (TextView) rv.findViewById(R.id.individual_item_price);
                location = (TextView) rv.findViewById(R.id.individual_item_location);



               // System.out.println("fefe: "+ name.toString().equals(adapter.saleItems.get(0).getName()));


                for (int i = 0;i < adapter.saleItems.size();i++){
                    if (name.getText().toString().equals(adapter.saleItems.get(i).getName())
                            && location.getText().toString().equals(adapter.saleItems.get(i).getLocation()) &&
                            price.getText().toString().equals(adapter.saleItems.get(i).getPrice())){

                        System.out.println("called");
                        drinksRecycler.removeViewAt(i);
                        adapter.notifyItemRemoved(i);
                        adapter.saleItems.remove(i);
                    }
                }

                System.out.println("touced");

               // databaseHelper.removeItemFromDb( db, name.getText().toString(), location.getText().toString(), price.getText().toString());

                //TODO: refresh adapter after deleting an entry


                db.close();



                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

 */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        List<SaleItem> saleItemList = loadItemsFromDb();

        li = inflater.inflate(R.layout.fragment_buy, container, false);

        adapter = new ItemDisplayAdapter(saleItemList);
        drinksRecycler = li.findViewById(R.id.sale_recyclerView);


        drinksRecycler.setAdapter(adapter);

        /*
        int x = adapter.saleItems.size();
        //saleItemList.remove(x);
        drinksRecycler.removeViewAt(x);
        adapter.notifyItemRemoved(x);
        adapter.notifyItemRangeChanged(x, adapter.saleItems.size());

         */

        linearLayoutManager = new LinearLayoutManager(getActivity());

        //removeEntry();
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
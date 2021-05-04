package com.example.kickons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.List;


public class BuyPage extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    List<SaleItem> saleItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_page);

        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseContract.FeedEntry.TABLE_NAME, new String[] {DatabaseContract.FeedEntry._ID,
                DatabaseContract.FeedEntry.COLUMN_NAME_ITEM, DatabaseContract.FeedEntry.COLUMN_NAME_LOCATION,
                DatabaseContract.FeedEntry.COLUMN_NAME_PRICE}, null, null, null, null, null);


        /*
        use cursor to go through all units in db and add them to a list containing the 3 values. send those to the recylerview
         */

        while (cursor.moveToNext()){
            saleItemList.add(new SaleItem(cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            //System.out.println("not empty");
            //System.out.println(cursor.getString(1));
        }
        //System.out.println(item);


        cursor.close();
        db.close();

        //TODO: get a list of the items from database to populate the recyclerview


        ItemDisplayAdapter adapter = new ItemDisplayAdapter(items, location, price);
        RecyclerView drinksRecycler = findViewById(R.id.sale_recyclerView);

        drinksRecycler.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        drinksRecycler.setLayoutManager(linearLayoutManager);


    }


}

//TODO: find out how to use cursor to get data from db x
//TODO: find out how to display data in gui

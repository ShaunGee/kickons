package com.example.kickons;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


public class BuyPage extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_page);

        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseContract.FeedEntry.TABLE_NAME, new String[] {DatabaseContract.FeedEntry._ID,
                DatabaseContract.FeedEntry.COLUMN_NAME_ITEM, DatabaseContract.FeedEntry.COLUMN_NAME_LOCATION,
                DatabaseContract.FeedEntry.COLUMN_NAME_PRICE}, null, null, null, null, null);


        //String item = cursor.getString(1);

        while (cursor.moveToNext()){
            System.out.println("not empty");
            System.out.println(cursor.getString(1));
        }
        //System.out.println(item);


        cursor.close();
        db.close();
    }


}

//TODO: find out how to use cursor to get data from db x
//TODO: find out how to display data in gui
//TODO: display data
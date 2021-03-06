package com.example.kickons;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DatabaseContract.FeedEntry.DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DatabaseContract.FeedEntry.TABLE_NAME + " (" +
                DatabaseContract.FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseContract.FeedEntry.COLUMN_NAME_ITEM + " TEXT," +
                DatabaseContract.FeedEntry.COLUMN_NAME_LOCATION + " TEXT," +
                DatabaseContract.FeedEntry.COLUMN_NAME_PRICE + " TEXT );");

        //System.out.println("on create called");


    }



    public void addItemToDb(SQLiteDatabase db, String item, String location, String price){

        //System.out.println("on add item called");

        ContentValues itemValues = new ContentValues();

        itemValues.put(DatabaseContract.FeedEntry.COLUMN_NAME_ITEM, item);
        itemValues.put(DatabaseContract.FeedEntry.COLUMN_NAME_LOCATION, location);
        itemValues.put(DatabaseContract.FeedEntry.COLUMN_NAME_PRICE, price);

        db.insert(DatabaseContract.FeedEntry.TABLE_NAME, null, itemValues);

    }

    public void removeItemFromDb(SQLiteDatabase db, String itemName,String itemLocation, String itemPrice){

        Cursor idCursor = db.rawQuery(String.format("SELECT %s  FROM %s WHERE %s = ? AND %s = ? AND %s = ?",DatabaseContract.FeedEntry._ID,
                DatabaseContract.FeedEntry.TABLE_NAME, DatabaseContract.FeedEntry.COLUMN_NAME_ITEM, DatabaseContract.FeedEntry.COLUMN_NAME_LOCATION, DatabaseContract.FeedEntry.COLUMN_NAME_PRICE),
                new String[]{itemName,itemLocation, itemPrice.split("\\$")[1]}, null);

        while (idCursor.moveToNext()) {
            db.delete(DatabaseContract.FeedEntry.TABLE_NAME, DatabaseContract.FeedEntry._ID + " = ?", new String[]{idCursor.getString(0)});
        }


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        //System.out.println("on upgrade called");



    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);

        //System.out.println("on downgrade called");


    }



    //This class will contain sqlite database commands to create and manipulate the database

    //private static final String SQL_CREATE_ENTRIES = "CREATE DATABASE"

}

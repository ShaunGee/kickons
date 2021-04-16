package com.example.kickons;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;


    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DatabaseContract.FeedEntry.DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DatabaseContract.FeedEntry.DATABASE_NAME + " (" +
                DatabaseContract.FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseContract.FeedEntry.COLUMN_NAME_ITEM + " TEXT," +
                DatabaseContract.FeedEntry.COLUMN_NAME_LOCATION + " TEXT," +
                DatabaseContract.FeedEntry.COLUMN_NAME_PRICE + " REAL );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    //This class will contain sqlite database commands to create and manipulate the database

    //private static final String SQL_CREATE_ENTRIES = "CREATE DATABASE"

}

package com.example.kickons;

import android.provider.BaseColumns;

public class DatabaseContract {


    private DatabaseContract(){}

    public static class FeedEntry implements BaseColumns{
        //BaseColumns gives entries an private ID
        public static final String DATABASE_NAME =  "kickons_database";
        public static final String TABLE_NAME = "sale_item";
        public static final String COLUMN_NAME_ITEM = "item";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_PRICE = "price";

    }
}

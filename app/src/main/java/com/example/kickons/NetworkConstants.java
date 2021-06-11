package com.example.kickons;

public class NetworkConstants {

    //hostAddrTestServer points to the server
    static String hostAddrTestServer = "192.168.8.104";


    public static String SERVER_URL = "http://"+hostAddrTestServer +":8000/kickons_inventory/users/1/?format=json"; //TEST CHANGE TO A DYNAMIC
    public static String SERVER_POST_URL = "http://"+hostAddrTestServer +":8000/kickons_inventory/users/";
    public static String SERVER_LOG_IN = "http://"+hostAddrTestServer +":8000/kickons_inventory/login/";
    public static String SERVER_LOG_IN_VERIFICATION = "http://"+hostAddrTestServer +":8000/kickons_inventory/login/verification/";
   public static String SERVER_GET_ITEMS =  "http://"+hostAddrTestServer +":8000/kickons_inventory/items/";
   public static String SERVER_GET_DELIVERIES = "http://"+hostAddrTestServer +":8000/kickons_inventory/delivery/";
    public static String SERVER_GET_DELIVERIES_ON_ROUTE = "http://"+hostAddrTestServer +":8000/kickons_inventory/get_deliveries/";
    public static String SERVER_GET_VERIFY_DELIVERIES_ON_ROUTE = "http://"+hostAddrTestServer +":8000/kickons_inventory/delivery/verify_delivery_on_route/";


    //public static String SERVER_GET_ALL_DELIVERIES = "http://"+hostAddrTestServer +":8000/kickons_inventory/delivery/";

}
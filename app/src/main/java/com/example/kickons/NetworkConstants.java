package com.example.kickons;

public class NetworkConstants {

    static String hostAddrTestServer = "192.168.8.104";

    public static String SERVER_URL = "http://"+hostAddrTestServer +":8000/kickons_inventory/users/1/?format=json"; //TEST CHANGE TO A DYNAMIC
    public static String SERVER_POST_URL = "http://"+hostAddrTestServer +":8000/kickons_inventory/users/";
    public static String SERVER_LOG_IN = "http://"+hostAddrTestServer +":8000/kickons_inventory/login/";
    public static String SERVER_LOG_IN_VERIFICATION = "http://"+hostAddrTestServer +":8000/kickons_inventory/login/verification/";
   public static String SERVER_GET_ITEMS =  "http://"+hostAddrTestServer +":8000/kickons_inventory/items/";


    public NetworkConstants() {
    }


}

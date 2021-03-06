package com.example.kickons;

public class NetworkConstants {

    //hostAddrTestServer points to the backend server (home external ip)
    static String hostAddrTestServer = "193.116.86.92";
    static String port = ""; //usually :8000 in testing and empty in production

    public static String SERVER_URL = "http://"+hostAddrTestServer +port+"/kickons_inventory/users/1/?format=json"; //TEST CHANGE TO A DYNAMIC
    public static String SERVER_POST_URL = "http://"+hostAddrTestServer +port+"/kickons_inventory/users/";
    public static String SERVER_LOG_IN = "http://"+hostAddrTestServer +port+"/kickons_inventory/login/";
    public static String SERVER_LOG_IN_VERIFICATION = "http://"+hostAddrTestServer +port+"/kickons_inventory/login/verification/";
    public static String SERVER_GET_ITEMS =  "http://"+hostAddrTestServer +port+"/kickons_inventory/items/";
    public static String SERVER_GET_DELIVERIES = "http://"+hostAddrTestServer +port+"/kickons_inventory/delivery/";
    public static String SERVER_GET_DELIVERIES_ON_ROUTE = "http://"+hostAddrTestServer +port+"/kickons_inventory/get_deliveries/";
    public static String SERVER_UPDATE_ROUTE_STATUS = "http://"+hostAddrTestServer +port+"/kickons_inventory/delivery/update_on_route_status/";

    public static String SERVER_SET_DELIVERIES_OF_A_USER = "http://"+hostAddrTestServer +port+"/kickons_inventory/deliverer/";
    public static String SERVER_CANCEL_DELIVERY = "http://"+hostAddrTestServer +port+"/kickons_inventory/deliverer/cancel_delivery/";
    public static String SERVER_GET_DELIVERIES_OF_A_USER = "http://"+hostAddrTestServer +port+"/kickons_inventory/get_deliveries/get_all_deliveries_of_user/";
    public static String SERVER_UPDATE_DELIVERER_ACTIVE_STATE = "http://"+hostAddrTestServer +port+"/kickons_inventory/deliverer/update_deliverer_active_status/";

    //public static String SERVER_GET_ALL_DELIVERIES = "http://"+hostAddrTestServer +":8000/kickons_inventory/delivery/";

}
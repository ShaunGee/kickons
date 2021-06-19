package com.example.kickons.delivery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kickons.NetworkConstants;
import com.example.kickons.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;


public class DeliveryListOfAvailableJobs extends Fragment {
    Bundle bundle;
    DeliveryDetails deliveryDetails;
    //List<DeliveryDetails> currentUserDeliveries;


    public DeliveryListOfAvailableJobs() {
        // Required empty public constructor
    }


    public static DeliveryListOfAvailableJobs newInstance() {
        DeliveryListOfAvailableJobs fragment = new DeliveryListOfAvailableJobs();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        assert bundle !=null;
        deliveryDetails = (DeliveryDetails) bundle.getSerializable("selected_delevery_details");

        //Send a request to the server to recieve all current deliveries by current delivery guy

        retrieveDelievryDetails(deliveryDetails.getDeliverer_id());
        //server returns


    }

    private void retrieveDelievryDetails(Integer deliverer_id) {

            List<DeliveryDetails> currentUserDeliveries = new LinkedList<>();
            //send a json request containing user idf to server

       JSONArray jsonArray = new JSONArray();
       JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("deliverer_id", deliverer_id);
            jsonArray.put(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = String.format("%s%s/%s",NetworkConstants.SERVER_GET_DELIVERIES_ON_ROUTE, String.valueOf(deliverer_id),"get_all_deliveries_of_user/");
       // String url = "http://192.168.8.104:8000/kickons_inventory/get_deliveries/get_current_user_deliveries/";

        //TODO: why is chosen deliveries not showing - bug is that server is giving an empty response becaise maybe the server hasnt had enough time to create the new entry in deliverer
        //TODO: update: test if it usually happens on first creation of deliverer table entry
        /*
        attempt 1 for creation of new entry - nothing apeared
        log out and log back in - item that was chosen is not being showed in list of available deliveries which is expected
        attempt 2 for creation of new deliverer entry - nothing is being shown when it is expected that there should be two current deliveries
        attempt 3 the same

        test:
        [X] check if correct response is getting getting sent back from server - response should be a a json array of all deliveries by the current
            deliverer user (jen)
                -found that on response wasnt getting stored because of incorect pulling of information |
                 - but still not being displayed
        [X] check if response is being stored correctly into the DeliveryDetail object array | solved this and works now
        [X] check if adapter is working | it works

        After fixing the response extraction. Im getting 4 Deliveries being returned for the user jen. It should be only one (the one chosen)
        on the first go.

        [X] check what the responses are
            [X]DB
            - set all deliveries on_route to False
            - delete all deliverer deliveries
        [X] do they apear all the time or only sometimes
            - run app in NORMAL mode 3x to make sure its not a one off thing

        I may know the problem. NetworkConstants.SERVER_GET_DELIVERIES_ON_ROUTE returns a list of ALL the deliveries whoes on_route is False
        which means they are not on route to destination.

        On response in DeliveryListOfAvailableJobs should be returning deliveries filtered (on_route = True, deliverer_user_id = {current_user(jen)}

        having a look the response, it doesn't have any link to deliverer. THe reposnse is deliveries model not deliverer model.
        I need to find some way to link the two models.

        I think deliverer has a forien key to delivery. It has a delivery_id shown in its table.
        Attempt to link models
        [X] try to create a forien key attribute in delivery model

        I've creted a forein key from delivery details to deliverer that stays null until someone is assigned to the delivery

        What i was trying to do earlier was establish a link between the deliverer and deliverydetails.

        Finaly fixed the bug..took me two days lol

         */
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, NetworkConstants.SERVER_GET_DELIVERIES_OF_A_USER, jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);
                for (int i = 0; i < response.length(); i++) {
                    System.out.println(response);
                    try {
                        Integer delivery_id = (Integer) response.getJSONObject(i).getJSONObject("DeliveryDetails").get("id");
                        Boolean on_route = (Boolean) response.getJSONObject(i).getJSONObject("DeliveryDetails").get("on_route");
                        Boolean delivered = (Boolean) response.getJSONObject(i).getJSONObject("DeliveryDetails").get("delivered");
                        Double delivery_long = (Double) response.getJSONObject(i).getJSONObject("DeliveryDetails").get("delivery_longtitude");
                        Double delivery_lat = (Double) response.getJSONObject(i).getJSONObject("DeliveryDetails").get("delivery_latitude");
                        Integer item_id = (Integer) response.getJSONObject(i).getJSONObject("DeliveryDetails").getJSONObject("item_id").get("id");
                        Integer user_id = (Integer) response.getJSONObject(i).getJSONObject("DeliveryDetails").get("user_id");
                        String item_img = (String) response.getJSONObject(i).getJSONObject("DeliveryDetails").getJSONObject("item_id").get("item_image");
                        String item_title = (String) response.getJSONObject(i).getJSONObject("DeliveryDetails").getJSONObject("item_id").get("item_title");
                        //Geocoder geocoder = new Geocoder(getContext());
                        //Address deliveryAddress = geocoder.getFromLocation(delivery_lat, delivery_long,1).get(0);


                        currentUserDeliveries.add(new DeliveryDetails(
                                delivery_id,
                                on_route,
                                delivered,
                                delivery_long,
                                delivery_lat,
                                item_id,
                                user_id,
                                item_img,
                                item_title,
                                deliveryDetails.getDeliverer_id()

                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("the exception that prevented 111: " + e.toString());

                    }
                }
                System.out.println(currentUserDeliveries);
                //create a listview of items to deliver
                initiateListView(currentUserDeliveries);

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getContext()).add(request);


    }

    private void initiateListView(List<DeliveryDetails> currentUserDeliveries) {
        RecyclerView recyclerView = getActivity().findViewById(R.id.list_of_avail_jobs_recy_view);
        DeliveryListAdapter deliveryListAdapter = new DeliveryListAdapter(currentUserDeliveries, getParentFragmentManager(),getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(deliveryListAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery_list_of_available_jobs, container, false);
    }


}

/*
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, NetworkConstants.SERVER_GET_DELIVERIES_ON_ROUTE, jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);
                for (int i = 0; i < response.length(); i++) {
                    System.out.println(response);
                    try {
                        Integer delivery_id = (Integer) response.getJSONObject(i).getJSONObject("delivery_details_id").get("id");
                        Boolean on_route = (Boolean) response.getJSONObject(i).getJSONObject("delivery_details_id").get("on_route");
                        Boolean delivered = (Boolean) response.getJSONObject(i).getJSONObject("delivery_details_id").get("delivered");
                        Double delivery_long = (Double) response.getJSONObject(i).getJSONObject("delivery_details_id").get("delivery_longtitude");
                        Double delivery_lat = (Double) response.getJSONObject(i).getJSONObject("delivery_details_id").get("delivery_latitude");
                        Integer item_id = (Integer) response.getJSONObject(i).getJSONObject("delivery_details_id").getJSONObject("item_id").get("id");
                        Integer user_id = (Integer) response.getJSONObject(i).get("user");
                        String item_img = (String) response.getJSONObject(i).getJSONObject("delivery_details_id").getJSONObject("item_id").get("item_image");
                        String item_title = (String) response.getJSONObject(i).getJSONObject("delivery_details_id").getJSONObject("item_id").get("item_title");
                        //Geocoder geocoder = new Geocoder(getContext());
                        //Address deliveryAddress = geocoder.getFromLocation(delivery_lat, delivery_long,1).get(0);


                        currentUserDeliveries.add(new DeliveryDetails(
                                delivery_id,
                                on_route,
                                delivered,
                                delivery_long,
                                delivery_lat,
                                item_id,
                                user_id,
                                item_img,
                                item_title,
                                deliveryDetails.getDeliverer_id()

                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("the exception that prevented 111: " + e.toString());

                    }
                }
                System.out.println(currentUserDeliveries);
                //create a listview of items to deliver
                initiateListView(currentUserDeliveries);

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getContext()).add(request);


    }
 */


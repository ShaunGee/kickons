package com.example.kickons.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemDisplayFragment extends Fragment {

    RecyclerView homepageRecyclerView;
    HomeSaleItemsAdapter homeSaleItemsAdapter;
    LinearLayoutManager linearLayoutManager;
    List<HomeItem> homeItems;


    public ItemDisplayFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ItemDisplayFragment newInstance(String param1, String param2) {
        ItemDisplayFragment fragment = new ItemDisplayFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getItems();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeItems = new LinkedList<>();
        return inflater.inflate(R.layout.fragment_item_display, container, false);
    }


    private void getItems(){

        JsonArrayRequest request = new JsonArrayRequest(JsonObjectRequest.Method.GET, NetworkConstants.SERVER_GET_ITEMS, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i<response.length();i++){
                    try {

                        homeItems.add(new HomeItem(
                                new JSONObject(response.get(i).toString()).get("id").toString(),
                                new JSONObject(response.get(i).toString()).get("item_title").toString(),
                                new JSONObject(response.get(i).toString()).get("item_caption").toString(),
                                new JSONObject(response.get(i).toString()).get("item_price").toString(),
                                "no images yet"
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                loadRecyclerView();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("the error: " + error);
            }
        });



        Volley.newRequestQueue(getContext()).add(request);
    }

    private void loadRecyclerView(){
        homepageRecyclerView = (RecyclerView) getActivity().findViewById(R.id.home_page_recyclerView);
        homeSaleItemsAdapter = new HomeSaleItemsAdapter(getFragmentManager(), homeItems);
        linearLayoutManager = new LinearLayoutManager(getContext());
        homepageRecyclerView.setLayoutManager(linearLayoutManager);
        homepageRecyclerView.setAdapter(homeSaleItemsAdapter);
    }

}
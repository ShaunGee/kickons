package com.example.kickons.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.kickons.NetworkConstants;
import com.example.kickons.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemPurchaseDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemPurchaseDetailFragment extends Fragment implements View.OnClickListener {
    Bundle b;

    public ItemPurchaseDetailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ItemPurchaseDetailFragment newInstance(String param1, String param2) {
        ItemPurchaseDetailFragment fragment = new ItemPurchaseDetailFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        b = getArguments();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button confirmBtn = (Button) view.findViewById(R.id.selected_item_confirm_btn);
        confirmBtn.setOnClickListener(this);

        TextView test = view.findViewById(R.id.selected_item_title);
        TextView caption = view.findViewById(R.id.selected_item_caption);
        TextView price = view.findViewById(R.id.selected_item_price);
        TextView location = view.findViewById(R.id.selected_item_location);
        ImageView img = view.findViewById(R.id.selected_item_Img);

        test.setText(b.get("item_title").toString());
        caption.setText(b.get("item_caption").toString());
        price.setText(b.get("item_price").toString());
        location.setText((b.get("location").toString()));
        Picasso.get().load(b.get("item_image_url").toString()).into(img);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_purchase_detail, container, false);
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.selected_item_confirm_btn){

                sendLocationToServer(b.getDouble("location_long"), b.getDouble("location_lat"),
                        b.getInt("user_id"), b.getInt("item_id")); //TODO: add test user id and item id once youve set up foriengn key in backend and login

                ItemOnItsWay itemOnItsWay = new ItemOnItsWay();
                itemOnItsWay.setArguments(b);
                getFragmentManager().beginTransaction().replace(R.id.home_page_frameLayout, itemOnItsWay).addToBackStack(null).commit();
        }
    }

    private void sendLocationToServer(Double longtitude, Double latitude, Integer user_id, Integer item_id){
        String url = NetworkConstants.SERVER_POST_DELIVERY;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("delivery_longtitude",longtitude);
            jsonObject.put("delivery_latitude",latitude);
            jsonObject.put("user_id",user_id);
            jsonObject.put("item_id",item_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("successs");
            }
        }, null);

        Volley.newRequestQueue(getContext()).add(request);
    }
}
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

import com.example.kickons.R;
import com.squareup.picasso.Picasso;

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

        test.setText(b.get("title").toString());
        caption.setText(b.get("caption").toString());
        price.setText(b.get("price").toString());
        location.setText((b.get("location").toString()));
        Picasso.get().load(b.get("image_url").toString()).into(img);
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
                DeliveryTrackerFragment deliveryTrackerFragment = new DeliveryTrackerFragment();
                getFragmentManager().beginTransaction().replace(R.id.home_page_frameLayout, deliveryTrackerFragment).addToBackStack(null).commit();
        }


    }
}
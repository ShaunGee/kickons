package com.example.kickons.delivery;

import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kickons.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class DeliveryListAdapter extends RecyclerView.Adapter<DeliveryListAdapter.ItemView>{

    List<DeliveryDetails> listOfDeliveries;
    FragmentManager fragmentManager;
    DeliveryDetails selectedDelivery;


    public DeliveryListAdapter(List<DeliveryDetails> listOfDeliveries, FragmentManager fragmentManager) {
        this.listOfDeliveries = listOfDeliveries;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public DeliveryListAdapter.ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_details_cardview, parent, false);

        return new DeliveryListAdapter.ItemView(cardView, fragmentManager);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemView holder, int position) {

        TextView deliveryonRoute = holder.itemView.findViewById(R.id.delivery_card_view_on_route);
        TextView deliveryItemName = holder.itemView.findViewById(R.id.delivery_card_view_on_item_name);
        TextView deliveryAddress = holder.itemView.findViewById(R.id.delivery_card_view_on_delivery_address);
        ImageView deliveryItemImg = holder.itemView.findViewById(R.id.delivery_card_view_on_item_image);

        deliveryonRoute.setText(String.valueOf(listOfDeliveries.get(position).getOn_route()));
        deliveryItemName.setText(String.valueOf(listOfDeliveries.get(position).getItem_title()));
        Picasso.get().load(listOfDeliveries.get(position).getItem_img()).into(deliveryItemImg);

        //returns and sets first address result using the lat and long values
        deliveryAddress.setText(String.valueOf(listOfDeliveries.get(position).getAddress().getAddressLine(0)));

    }


    @Override
    public int getItemCount() {
        return listOfDeliveries.size();
    }




     class ItemView extends  RecyclerView.ViewHolder implements View.OnClickListener, Serializable {
         FragmentManager fragmentManager;

        public ItemView(@NonNull View itemView, FragmentManager fragmentManager) {
            super(itemView);
            this.fragmentManager = fragmentManager;
            itemView.setOnClickListener(this);


        }

         @Override
         public void onClick(View view) {

            DeliveryDetailDisplayFragment deliveryDetailDisplayFragment = new DeliveryDetailDisplayFragment();
            //create a new bundle to be sent to deliveryDetailDisplay Fragment and put the delivery_id to it so further fragments know what
             Bundle bundle = deliveryDetailDisplayFragment.getArguments();


            deliveryDetailDisplayFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.delivery_activity_frame_layout, deliveryDetailDisplayFragment).commit();

         }
     }
}

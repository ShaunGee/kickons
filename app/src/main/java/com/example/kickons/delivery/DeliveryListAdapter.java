package com.example.kickons.delivery;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kickons.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class DeliveryListAdapter extends RecyclerView.Adapter<DeliveryListAdapter.ItemView> {

    List<DeliveryDetails> listOfDeliveries;
    FragmentManager fragmentManager;
    DeliveryDetails selectedDelivery;
    Context context;
    CustomOnclickListenerInterface click;


    public DeliveryListAdapter(List<DeliveryDetails> listOfDeliveries, FragmentManager fragmentManager,
                               Context context, CustomOnclickListenerInterface customOnclickListenerInterface) {
        this.listOfDeliveries = listOfDeliveries;
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.click = customOnclickListenerInterface;
    }

    @NonNull
    @Override
    public DeliveryListAdapter.ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_details_cardview, parent, false);

        return new DeliveryListAdapter.ItemView(cardView, fragmentManager);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemView holder, int position) {

        try {

        TextView deliveryonRoute = holder.itemView.findViewById(R.id.delivery_card_view_on_route);
        TextView deliveryItemName = holder.itemView.findViewById(R.id.delivery_card_view_on_item_name);
        TextView deliveryAddress = holder.itemView.findViewById(R.id.delivery_card_view_on_delivery_address);
        ImageView deliveryItemImg = holder.itemView.findViewById(R.id.delivery_card_view_on_item_image);

        deliveryonRoute.setText(String.valueOf(listOfDeliveries.get(position).getOn_route()));
        deliveryItemName.setText(String.valueOf(listOfDeliveries.get(position).getItem_title()));
        Picasso.get().load(listOfDeliveries.get(position).getItem_img()).into(deliveryItemImg);

        //set Textview address result using the lat and long values from DeliveryDetails
        Geocoder geocoder = new Geocoder(context);
        Double deliveryLat = listOfDeliveries.get(position).getDelivery_latitude();
        Double deliveryLong = listOfDeliveries.get(position).getDelivery_latitude();
        Address address = geocoder.getFromLocation(deliveryLat,deliveryLong, 1).get(0);
        deliveryAddress.setText(address.getAddressLine(0));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.recyclerviewOnItemClick(position, listOfDeliveries, fragmentManager);
            }
        });

        /*
        //onclick listener to handle itemclick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeliveryDetailDisplayFragment deliveryDetailDisplayFragment = new DeliveryDetailDisplayFragment();

                //create a new bundle to be sent to deliveryDetailDisplay Fragment and put the DeliveryDetail object
                Bundle bundle = new Bundle();
                bundle.putSerializable("selected_delevery_details", listOfDeliveries.get(position));
                deliveryDetailDisplayFragment.setArguments(bundle);

                fragmentManager.beginTransaction().replace(R.id.delivery_activity_frame_layout, deliveryDetailDisplayFragment).commit();
            }
        });

         */
        } catch (IOException  | IndexOutOfBoundsException e) {
            e.printStackTrace(); //incase address is not findable
            //Toast.makeText(context,"Couldn't find address", Toast.LENGTH_SHORT).show();
            }



    }


    @Override
    public int getItemCount() {
        return listOfDeliveries.size();
    }


    class ItemView extends  RecyclerView.ViewHolder {
        FragmentManager fragmentManager;

        public ItemView(@NonNull View itemView, FragmentManager fragmentManager) {
            super(itemView);
            this.fragmentManager = fragmentManager;


        }

    }

     }


package com.example.kickons;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemDisplayAdapter extends RecyclerView.Adapter<ItemDisplayAdapter.ViewHolder> {

    //private String[] items, location, price;
    List<SaleItem> saleItems;


    public ItemDisplayAdapter(List<SaleItem> i) {
        this.saleItems = i;
        //this.items = i.get(0);
        //this.location = location;
        //this.price = price;

    }

    @NonNull
    @Override
    public ItemDisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_item, parent, false);

        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDisplayAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView item = (TextView) cardView.findViewById(R.id.individual_item_name);
        TextView location = (TextView) cardView.findViewById(R.id.individual_item_location);
        TextView price = (TextView) cardView.findViewById(R.id.individual_item_price);

        item.setText(saleItems.get(position).getName());
        location.setText(saleItems.get(position).getLocation());
        price.setText(saleItems.get(position).getPrice());

    }

    @Override
    public int getItemCount() {

        return saleItems.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;

        }
    }
}


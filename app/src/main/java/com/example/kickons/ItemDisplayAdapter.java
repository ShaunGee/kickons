package com.example.kickons;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemDisplayAdapter extends RecyclerView.Adapter<ItemDisplayAdapter.ViewHolder> {

    private String[] items, location, price;



    public ItemDisplayAdapter(String[] items, String[] location, String[] price) {
        this.items = items;
        this.location = location;
        this.price = price;

    }

    @NonNull
    @Override
    public ItemDisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_item, parent,false);

        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDisplayAdapter.ViewHolder holder, int position) {
        CardView cardView =  holder.cardView;
        TextView i = (TextView) cardView.findViewById(R.id.individual_item_name);
        TextView l = (TextView) cardView.findViewById(R.id.individual_item_location);
        TextView p = (TextView) cardView.findViewById(R.id.individual_item_price);

        i.setText(items[position]);
        l.setText(location[position]);
        p.setText(price[position]);
    }

    @Override
    public int getItemCount() {

        return items.length;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;

        }
    }
}

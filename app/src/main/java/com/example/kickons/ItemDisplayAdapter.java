package com.example.kickons;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemDisplayAdapter extends RecyclerView.Adapter<ItemDisplayAdapter.ViewHolder> {

    //private String[] items, location, price;
    List<SaleItem> saleItems;
    ItemDisplayAdapter.ViewHolder h;


    public ItemDisplayAdapter(List<SaleItem> i) {
        this.saleItems = i;


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
        price.setText("$"+ saleItems.get(position).getPrice());



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("view clicked");
                //view.setBackgroundColor(000000);
                //TODO: create fragment to be opened when item is clicked

            }
        });



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


    public static class RecyclerOnTouch implements RecyclerView.OnItemTouchListener{
        RecyclerView recyclerView;
        GestureDetector gestureDetector;

        public RecyclerOnTouch(Context context, ViewGroup container, RecyclerView recyclerView) {

            this.recyclerView = recyclerView;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(),e.getY());
                    if (child != null) {
                        System.out.println("clicked from ItemDisplayAdapter");
                        Intent intent = new Intent(container.getContext(),SaleItemDetail.class);
                        System.out.println();
                        //intent.putExtra()

                        context.startActivity(intent);

                    }
                    return true;


                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            gestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {


        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }


    }

}




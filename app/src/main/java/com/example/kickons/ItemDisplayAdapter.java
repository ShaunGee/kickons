package com.example.kickons;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private List<SaleItem> saleItems;
    ViewGroup parent;


    public ItemDisplayAdapter(List<SaleItem> i) {
        this.saleItems = i;



    }

    @NonNull
    @Override
    public ItemDisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_item, parent, false);
        this.parent = parent;

        registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                Intent i = new Intent(parent.getContext(),LandingActivity.class);
                parent.getContext().startActivity(i);
                //parent.removeViewAt(0);
            }
        });


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
                System.out.println("clicked");

                    DatabaseHelper databaseHelper = new DatabaseHelper(parent.getContext());
                    SQLiteDatabase db = databaseHelper.getWritableDatabase();

                TextView name,price,location;
                name = (TextView) holder.cardView.findViewById(R.id.individual_item_name);
                price = (TextView) holder.cardView.findViewById(R.id.individual_item_price);
                location = (TextView) holder.cardView.findViewById(R.id.individual_item_location);


                for (int i = 0;i < saleItems.size();i++){
                    if (name.getText().toString().equals(saleItems.get(i).getName())
                            && location.getText().toString().equals(saleItems.get(i).getLocation()) &&
                            price.getText().toString().split("\\$")[1].equals(saleItems.get(i).getPrice())){

                        notifyItemRemoved(i);
                        saleItems.remove(i);
                        databaseHelper.removeItemFromDb( db, name.getText().toString(), location.getText().toString(), price.getText().toString());

                    }
                }

                //databaseHelper.removeItemFromDb( db, name.getText().toString(), location.getText().toString(), price.getText().toString());
                //RecyclerView.AdapterDataObserver observer = new Ober
                //registerAdapterDataObserver();

                //notifyDataSetChanged();
                db.close();

                //Cursor cursor = db.query()



            }
        });

        //TODO: Database Delete working but now need a way to refresh the page to show updated table. We can use intents or we can use adapterDataObserver



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

    /*


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

     */

}




package com.example.kickons;

import android.app.Activity;
import android.content.ContentValues;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class ItemDisplayAdapter extends RecyclerView.Adapter<ItemDisplayAdapter.ViewHolder> {

    //private String[] items, location, price;
    private List<SaleItem> saleItems;
    ViewGroup parent;
    Fragment fragment;


    public ItemDisplayAdapter(List<SaleItem> i, Fragment fragment) {
        this.saleItems = i;
        this.fragment = fragment;

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

                //Activity activity = (Activity) parent.getContext();
                //loadItemsFromDb(activity);


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
        price.setText("$" + saleItems.get(position).getPrice());


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                System.out.println("clicked");

                DatabaseHelper databaseHelper = new DatabaseHelper(parent.getContext());
                SQLiteDatabase db = databaseHelper.getWritableDatabase();

                TextView name, price, location;
                name = (TextView) cardView.findViewById(R.id.individual_item_name);
                price = (TextView) cardView.findViewById(R.id.individual_item_price);
                location = (TextView) cardView.findViewById(R.id.individual_item_location);


                for (int i = 0; i < saleItems.size(); i++) {
                    if (name.getText().toString().equals(saleItems.get(i).getName())
                            && location.getText().toString().equals(saleItems.get(i).getLocation()) &&
                            price.getText().toString().split("\\$")[1].equals(saleItems.get(i).getPrice())) {

                        notifyItemRemoved(i);
                        saleItems.remove(i);
                        databaseHelper.removeItemFromDb(db, name.getText().toString(), location.getText().toString(), price.getText().toString());

                    }
                }

                db.close();

            }


        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                //holder.cardView.invalidate();
                System.out.println("sout");
                return false;
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

    public void loadItemsFromDb(Activity activity) {


        List<SaleItem> sI = new LinkedList<>();

        DatabaseHelper databaseHelper = new DatabaseHelper(activity);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseContract.FeedEntry.TABLE_NAME, new String[]{DatabaseContract.FeedEntry._ID,
                DatabaseContract.FeedEntry.COLUMN_NAME_ITEM, DatabaseContract.FeedEntry.COLUMN_NAME_LOCATION,
                DatabaseContract.FeedEntry.COLUMN_NAME_PRICE}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            sI.add(new SaleItem(cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        cursor.close();
        db.close();

        saleItems = sI;
    }

}
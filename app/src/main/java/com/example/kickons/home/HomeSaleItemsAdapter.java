package com.example.kickons.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kickons.R;

import java.util.LinkedList;
import java.util.List;

public class HomeSaleItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<TestItemAdapter> testList;
    Context context;
    public HomeSaleItemsAdapter(Context context) {
        this.context = context;
        testList = test();

    }

    @NonNull
    @Override
    public ItemForSaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView itemCard = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.home_page_sale_item_card, parent);
        return new ItemForSaleViewHolder(itemCard);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CardView saleItem = (CardView) holder.itemView;

        //ImageView displayImg = (ImageView) saleItem.findViewById(R.id.home_page_sale_item_card_display_img);
        TextView title = (TextView) saleItem.findViewById(R.id.home_page_sale_item_card_title);
        TextView caption = (TextView) saleItem.findViewById(R.id.home_page_sale_item_card_caption);
        TextView price = (TextView) saleItem.findViewById(R.id.home_page_sale_item_card_item_price);

        title.setText(testList.get(position).getTittle());
        caption.setText(testList.get(position).getCaption());
        price.setText(testList.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    private List<TestItemAdapter> test(){
        List<TestItemAdapter> l = new LinkedList<>();
        l.add(new TestItemAdapter("vodka", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("vodka", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("vodka", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("vodka", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("vodka", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("vodka", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("vodka", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("vodka", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("vodka", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("vodka", "sdfgdsfsfsf", "sdf"));


        return l;
    }

     private class ItemForSaleViewHolder extends RecyclerView.ViewHolder {
        private CardView saleCard;
        public ItemForSaleViewHolder(@NonNull CardView saleCard) {
            super(saleCard);
            this.saleCard = saleCard;

        }
    }

    private class TestItemAdapter{
        String tittle, caption, price;

        public TestItemAdapter(String tittle, String caption, String price) {
            this.tittle = tittle;
            this.caption = caption;
            this.price = price;


        }

        public String getCaption() {
            return caption;
        }

        public String getPrice() {
            return price;
        }

        public String getTittle() {
            return tittle;
        }
    }
}

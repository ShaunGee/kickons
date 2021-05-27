package com.example.kickons.home;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kickons.R;
import java.util.LinkedList;
import java.util.List;

public class HomeSaleItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<TestItemAdapter> testList;
    FragmentManager fragmentManager;

    public HomeSaleItemsAdapter(FragmentManager fragmentManager) {
        //List to be tested
        testList = test();
        this.fragmentManager = fragmentManager;

    }

    @NonNull
    @Override
    public ItemForSaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_page_sale_item_card, parent,false);
        return new ItemForSaleViewHolder(itemCard, fragmentManager);
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

     private class ItemForSaleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView saleCard;
        FragmentManager fragmentManager;
        public ItemForSaleViewHolder(@NonNull View saleCard, FragmentManager fragmentManager) {
            super(saleCard);
            saleCard.setOnClickListener(this);
            this.fragmentManager = fragmentManager;

        }


         @Override
         public void onClick(View view) {
            TextView title = view.findViewById(R.id.home_page_sale_item_card_title);
             TextView caption = view.findViewById(R.id.home_page_sale_item_card_caption);
             TextView price = view.findViewById(R.id.home_page_sale_item_card_item_price);

             EnterUserLocationFragment enterUserLocationFragment = new EnterUserLocationFragment();
             Bundle b = new Bundle();
             b.putString("title", title.getText().toString());
             b.putString("caption", caption.getText().toString());
             b.putString("price", price.getText().toString());
             enterUserLocationFragment.setArguments(b);

             fragmentManager.beginTransaction().replace(R.id.home_page_frameLayout, enterUserLocationFragment).addToBackStack(null).commit();
         }
     }
    //dummy class for adapter test
    public class TestItemAdapter  {
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

    //Dummy code for adapter test
    private List<TestItemAdapter> test(){
        List<TestItemAdapter> l = new LinkedList<>();
        l.add(new TestItemAdapter("vodka", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("rum", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("beer", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("goon", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("coke", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("sfd", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("vsdf", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("ka", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("hooka", "sdfgdsfsfsf", "sdf"));
        l.add(new TestItemAdapter("weed", "sdfgdsfsfsf", "sdf"));


        return l;
    }
}

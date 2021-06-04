package com.example.kickons.home;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kickons.NetworkConstants;
import com.example.kickons.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class HomeSaleItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<TestItemAdapter> testList;
    FragmentManager fragmentManager;
    List<HomeItem> homeItemlist;
    Bundle bundle;

    Context context;

    public HomeSaleItemsAdapter(FragmentManager fragmentManager, List<HomeItem> homeItemlist, Bundle bundle) {
        this.bundle = bundle;
        //List to be tested
        testList = test();
        this.fragmentManager = fragmentManager;
        this.homeItemlist = homeItemlist;
        this.context = context;
    }

    public void addItemToList(int pos){}
    public void removeItemFromList(int pos){}


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
        TextView itemId = (TextView) saleItem.findViewById(R.id.home_page_sale_item_card_item_id);
        TextView title = (TextView) saleItem.findViewById(R.id.home_page_sale_item_card_title);
        TextView caption = (TextView) saleItem.findViewById(R.id.home_page_sale_item_card_caption);
        TextView price = (TextView) saleItem.findViewById(R.id.home_page_sale_item_card_item_price);
        TextView hiddenImgUrl = (TextView) saleItem.findViewById(R.id.home_page_sale_item_card_display_img_url);
        ImageView itemImg = saleItem.findViewById(R.id.home_page_sale_item_card_display_img);

        itemId.setText(String.valueOf(homeItemlist.get(position).getId()));
        title.setText(homeItemlist.get(position).getTitle());
        caption.setText(homeItemlist.get(position).getCaption());
        price.setText(homeItemlist.get(position).getPrice());
        hiddenImgUrl.setText(homeItemlist.get(position).getImg());
        Picasso.get().load(homeItemlist.get(position).getImg()).into(itemImg);





    }


    @Override
    public int getItemCount() {
        return homeItemlist.size();
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
            //get specific view information of click
             TextView itemId = (TextView) view.findViewById(R.id.home_page_sale_item_card_item_id);
            TextView title = view.findViewById(R.id.home_page_sale_item_card_title);
             TextView caption = view.findViewById(R.id.home_page_sale_item_card_caption);
             TextView price = view.findViewById(R.id.home_page_sale_item_card_item_price);
             TextView hiddenImgUrl = (TextView) view.findViewById(R.id.home_page_sale_item_card_display_img_url);

             EnterUserLocationFragment enterUserLocationFragment = new EnterUserLocationFragment();
             bundle.putInt("item_id", Integer.valueOf(itemId.getText().toString()));
             bundle.putString("item_title", title.getText().toString());
             bundle.putString("item_caption", caption.getText().toString()); //TODO: FIXXXXX
             bundle.putString("item_price", price.getText().toString());
             bundle.putString("item_image_url", hiddenImgUrl.getText().toString());
             enterUserLocationFragment.setArguments(bundle);



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

        public String getTitle() {
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

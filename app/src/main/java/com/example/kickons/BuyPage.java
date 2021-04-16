package com.example.kickons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import java.util.List;

public class BuyPage extends AppCompatActivity {

    LinearLayout buyPage;
    SaleItem saleItems;
    String[] test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_page);


        test = new String[]{
                "vodka",
                "rum",
                "jack daniels",
                "XXXX gold",
                "Tooheys Extra dryh",
                "vodka",
                "rum",
                "jack daniels",
                "XXXX gold",
                "Tooheys Extra dryh",
                "vodka",
                "rum",
                "jack daniels",
                "XXXX gold",
                "Tooheys Extra dryh",

        };
        buyPage = findViewById(R.id.buy_page);
        saleItems = new SaleItem("sdfsd", "fdsfsd", 34.3f);

        ArrayAdapter<String> saleItems = new ArrayAdapter<String>(BuyPage.this, R.layout.sale_item, test);

    }
}
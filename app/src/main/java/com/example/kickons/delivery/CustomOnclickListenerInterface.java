package com.example.kickons.delivery;

import androidx.fragment.app.FragmentManager;

import java.util.List;

public interface CustomOnclickListenerInterface {
    public void recyclerviewOnItemClick(int position, List<DeliveryDetails> listOfDeliveries, FragmentManager fragmentManager);
}

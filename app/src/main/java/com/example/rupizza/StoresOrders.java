package com.example.rupizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.example.rupizza.R;

import com.example.rupizza.RuPizza.Pizza;
import com.example.rupizza.RuPizza.Size;
import com.example.rupizza.RuPizza.SpecialityPizza;
import com.example.rupizza.RuPizza.StoreOrders;
import com.example.rupizza.RuPizza.Order;

public class StoresOrders extends AppCompatActivity {

    private ListView listViewStoreOrders;
    private StoreOrdersAdapter storeOrdersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        // Assuming you have a reference to your StoreOrders instance
        StoreOrders storeOrders = StoreOrders.getInstance();

        listViewStoreOrders = findViewById(R.id.listViewStoreOrders);

        // Create and set the adapter
        storeOrdersAdapter = new StoreOrdersAdapter(this, storeOrders.getOrders(), storeOrders.getMapping());
        listViewStoreOrders.setAdapter(storeOrdersAdapter);
    }
}
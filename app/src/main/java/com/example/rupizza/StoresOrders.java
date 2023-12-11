package com.example.rupizza;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rupizza.RuPizza.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoresOrders extends AppCompatActivity {

    private ListView listViewStoreOrders;
    private StoreOrdersAdapter storeOrdersAdapter;
    private Spinner spinnerOrderIDs;
    private TextView textViewTotal;
    private Button btnRemoveOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        // Assuming you have a reference to your StoreOrders instance
        StoreOrders storeOrders = StoreOrders.getInstance();

        listViewStoreOrders = findViewById(R.id.listViewStoreOrders);
        spinnerOrderIDs = findViewById(R.id.spinOrdIDsStore);
        textViewTotal = findViewById(R.id.totalStore);
        btnRemoveOrder = findViewById(R.id.btnStoreRemoveOrder);

        // Create and set the adapter
        storeOrdersAdapter = new StoreOrdersAdapter(this, storeOrders.getOrders(), storeOrders.getMapping());
        listViewStoreOrders.setAdapter(storeOrdersAdapter);

        // Populate the spinner with order IDs
        List<Integer> orderIDs = storeOrders.getOrders();
        ArrayAdapter<Integer> orderIDAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderIDs);
        orderIDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrderIDs.setAdapter(orderIDAdapter);

        // Add an OnItemSelectedListener to the spinner
        spinnerOrderIDs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected Order ID
                int selectedOrderID = (int) spinnerOrderIDs.getSelectedItem();

                // Update the adapter with the selected order
                storeOrdersAdapter.updateSelectedOrder(storeOrders.getMapping().get(selectedOrderID));

                // Recalculate and display total based on the selected order
                double total = storeOrders.calculateTotal(storeOrders.getMapping().get(selectedOrderID));
                textViewTotal.setText("Total: $" + String.format("%.2f", total));
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if nothing is selected
            }
        });

        btnRemoveOrder.setEnabled(!storeOrders.isEmpty());
        // Add a click listener to the Remove Order button
        btnRemoveOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the selected store order
                removeSelectedStoreOrder();
                textViewTotal.setText("Total: $" + String.format("%.2f", 0.0));
                // Refresh the ListView after removing the order
                storeOrdersAdapter.notifyDataSetChanged();

                btnRemoveOrder.setEnabled(!storeOrders.isEmpty());
            }
        });
    }

    private void removeSelectedStoreOrder() {
        // Get the selected store order
        int selectedOrderID = (int) spinnerOrderIDs.getSelectedItem();
        StoreOrders.getInstance().remove(selectedOrderID);

        // Refresh the spinner data after removing the order
        List<Integer> updatedOrderIDs = StoreOrders.getInstance().getOrders();
        ArrayAdapter<Integer> updatedOrderIDAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, updatedOrderIDs);
        updatedOrderIDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrderIDs.setAdapter(updatedOrderIDAdapter);

        // Set the default selection to the first item if available
        if (!updatedOrderIDs.isEmpty()) {
            spinnerOrderIDs.setSelection(0);
        }
    }

}

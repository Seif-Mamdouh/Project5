package com.example.rupizza;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rupizza.RuPizza.Order;
import com.example.rupizza.RuPizza.Pizza;
import com.example.rupizza.RuPizza.SpecialityPizza;
import com.example.rupizza.RuPizza.StoreOrders;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CurrentOrder extends AppCompatActivity {
    private ListView listView;
    private Spinner spinnerOrderIDs; // Add spinner for Order IDs
    private CurrentOrderAdapter currentOrderAdapter;
    private Button btnRemoveOrder;
    private TextView textViewTotalPrice;
    private TextView textViewTax;
    private TextView textViewTotal;
    private Button btnPlaceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        // Initialize ListView, Spinner, and Button
        listView = findViewById(R.id.listViewOrder);
        spinnerOrderIDs = findViewById(R.id.spinnerOrderIDs);
        btnRemoveOrder = findViewById(R.id.btnRemoveOrder);
        textViewTotalPrice = findViewById(R.id.totalPrice);
        textViewTax = findViewById(R.id.taxPrice);
        textViewTotal = findViewById(R.id.total);

        // Retrieve the order details
        Order order = Order.getPizzaOrder();
        List<Pizza> pizzas = order.getPizzas();

        // Create and set the adapter
        currentOrderAdapter = new CurrentOrderAdapter(pizzas);
        listView.setAdapter(currentOrderAdapter);

        // Populate the spinner with order IDs
        List<Integer> orderIDs = order.getOrderIDs();
        // Add "All Orders" to the order IDs
        orderIDs.add(0, -1);
        ArrayAdapter<Integer> orderIDAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderIDs);
        orderIDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrderIDs.setAdapter(orderIDAdapter);

        // Add an OnItemSelectedListener to the spinner
        spinnerOrderIDs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected Order ID
                int selectedOrderID = (int) spinnerOrderIDs.getSelectedItem();

                // Filter pizzas based on the selected Order ID
                List<Pizza> filteredPizzas = filterPizzasByOrderID(selectedOrderID);

                // Update the ListView with the filtered list
                currentOrderAdapter.setPizzas(filteredPizzas);
                currentOrderAdapter.notifyDataSetChanged();

                // Recalculate and display total price, tax, and total based on filtered pizzas
                double totalPrice = calculateTotalPrice(filteredPizzas);
                double tax = calculateTax(filteredPizzas);
                double total = totalPrice + tax;

                textViewTotalPrice.setText("Total Price: $" + String.format("%.2f", totalPrice));
                textViewTax.setText("Tax: $" + String.format("%.2f", tax));
                textViewTotal.setText("Total: $" + String.format("%.2f", total));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if nothing is selected
            }
        });

        // Add a click listener to the Remove Order button
        btnRemoveOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the order based on the selected Order ID
                int selectedOrderID = (int) spinnerOrderIDs.getSelectedItem();
                removeOrderByOrderID(selectedOrderID);

                // Refresh the ListView after removing the order
                currentOrderAdapter.setPizzas(Order.getPizzas());
                currentOrderAdapter.notifyDataSetChanged();
            }
        });


        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Log the start of the onClick method
                Log.d("CurrentOrder", "Place Order button clicked");

                // Place the current order in the store order list
                placeOrderInStore();

                // Log the current order after placing it in the store
                Log.d("CurrentOrder", "Current Order after placing in store: " + Order.getPizzaOrder().getPizzas());

                // Clear the current order
                clearCurrentOrder();

                // Log the current order after clearing
                Log.d("CurrentOrder", "Current Order after clearing: " + Order.getPizzaOrder().getPizzas());

                // Notify the adapter that the data has changed
                currentOrderAdapter.notifyDataSetChanged();

                // Log the end of the onClick method
                Log.d("CurrentOrder", "Place Order button click finished");
            }
        });
    }


    // Method to place the current order in the store order list
    private void placeOrderInStore() {
        Order currentOrder = Order.getPizzaOrder();
        StoreOrders storeOrders = StoreOrders.getInstance();
        storeOrders.add(currentOrder);
    }

    // Method to clear the current order
    private void clearCurrentOrder() {
        // Clear the current order
        Order.getPizzaOrder().resetOrder();

        // Clear the ListView
        currentOrderAdapter.setPizzas(new ArrayList<>());
        currentOrderAdapter.notifyDataSetChanged();

        // Clear the Spinner selection
        spinnerOrderIDs.setSelection(0); // Assuming the first item is "All Orders"

        // Clear the TextViews for total price, tax, and total
        textViewTotalPrice.setText("Total Price: $0.00");
        textViewTax.setText("Tax: $0.00");
        textViewTotal.setText("Total: $0.00");
    }

    // Add a method to filter pizzas by Order ID
    private List<Pizza> filterPizzasByOrderID(int orderID) {
        if (orderID == -1) {
            // Return all pizzas when "All Orders" is selected
            return Order.getPizzas();
        }

        List<Pizza> filteredPizzas = new ArrayList<>();
        for (Pizza pizza : Order.getPizzas()) {
            if (pizza instanceof SpecialityPizza && ((SpecialityPizza) pizza).getPizzaID() == orderID) {
                filteredPizzas.add(pizza);
            }
        }
        return filteredPizzas;
    }

    // Add a method to remove an order by Order ID
    private void removeOrderByOrderID(int orderID) {
        List<Pizza> pizzasToRemove = new ArrayList<>();
        for (Pizza pizza : Order.getPizzas()) {
            if (pizza instanceof SpecialityPizza && ((SpecialityPizza) pizza).getPizzaID() == orderID) {
                pizzasToRemove.add(pizza);
            }
        }
        Order.getPizzas().removeAll(pizzasToRemove);

        // Remove the Order ID from the Spinner adapter
        ArrayAdapter<Integer> orderIDAdapter = (ArrayAdapter<Integer>) spinnerOrderIDs.getAdapter();
        if (orderIDAdapter != null) {
            orderIDAdapter.remove(orderID);
            orderIDAdapter.notifyDataSetChanged();
        }
    }

    // Method to calculate the total price based on a list of pizzas
    private double calculateTotalPrice(List<Pizza> pizzas) {
        double totalPrice = 0.0;
        for (Pizza pizza : pizzas) {
            if (pizza instanceof SpecialityPizza) {
                totalPrice += ((SpecialityPizza) pizza).calculatePrice();
            }
        }
        return totalPrice;
    }

    // Method to calculate the tax based on a list of pizzas
    private double calculateTax(List<Pizza> pizzas) {
        double tax = 0.0;
        for (Pizza pizza : pizzas) {
            if (pizza instanceof SpecialityPizza) {
                tax += ((SpecialityPizza) pizza).calculateTax();
            }
        }
        return tax;
    }

}




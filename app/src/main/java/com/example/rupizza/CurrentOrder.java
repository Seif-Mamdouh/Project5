package com.example.rupizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.rupizza.RuPizza.Order;
import com.example.rupizza.RuPizza.Pizza;

import java.util.List;

public class CurrentOrder extends AppCompatActivity {
    private ListView listView;
    private CurrentOrderAdapter currentOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        // Initialize ListView
        listView = findViewById(R.id.listViewOrder);

        // Retrieve the order details
        Order order = Order.getPizzaOrder();
        List<Pizza> pizzas = order.getPizzas();

        // Create and set the adapter
        currentOrderAdapter = new CurrentOrderAdapter(pizzas);
        listView.setAdapter(currentOrderAdapter);
    }
}

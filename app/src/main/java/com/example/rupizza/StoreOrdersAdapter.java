package com.example.rupizza;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rupizza.RuPizza.Order;
import com.example.rupizza.RuPizza.Pizza;
import com.example.rupizza.RuPizza.SpecialityPizza;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// StoreOrdersAdapter.java
public class StoreOrdersAdapter extends BaseAdapter {

    private List<Integer> orderIDs;
    private Map<Integer, Order> orderMapping;
    private Context context;

    public StoreOrdersAdapter(Context context, List<Integer> orderIDs, Map<Integer, Order> orderMapping) {
        this.context = context;
        this.orderIDs = orderIDs;
        this.orderMapping = orderMapping;
    }

    @Override
    public int getCount() {
        return orderIDs.size();
    }

    @Override
    public Object getItem(int position) {
        return orderMapping.get(orderIDs.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_order, parent, false);
        }

        TextView textOrderDetails = convertView.findViewById(R.id.textOrderDetails);

        Order order = (Order) getItem(position);

        if (order != null) {
            // Customize this part based on how you want to display the order details
            String orderDetails = "Order ID: " + order.getOrderIDs() + "\n";

            // Iterate over the pizzas in the order and append details
            for (Pizza pizza : order.getPizzas()) {
                if (pizza instanceof SpecialityPizza) {
                    SpecialityPizza specialityPizza = (SpecialityPizza) pizza;
                    orderDetails += "Order ID: " + specialityPizza.getPizzaID() + "\n" +  // Include Order ID here
                            "Pizza Type: " + specialityPizza.getPizzaType() + "\n" +
                            "Quantity: " + specialityPizza.getQuantity() + "\n" +
                            "Size: " + specialityPizza.getSize() + "\n" +
                            "Extra Cheese: " + (specialityPizza.isExtraCheese() ? "yes" : "no") + "\n" +
                            "Extra Sauce: " + (specialityPizza.isExtraSauce() ? "yes" : "no") + "\n" +
                            "Toppings: " + specialityPizza.getToppings() + "\n" +
                            "Total Price: $" + specialityPizza.total() + "\n" +
                            "Tax: $" + specialityPizza.calculateTax() + "\n" +
                            "Total: $" + (specialityPizza.total() + specialityPizza.calculateTax()) + "\n\n";
                }
            }


            textOrderDetails.setText(orderDetails);
        }

        return convertView;
    }
}


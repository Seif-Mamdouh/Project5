package com.example.rupizza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rupizza.RuPizza.BuildYourOwnPizza;
import com.example.rupizza.RuPizza.Order;
import com.example.rupizza.RuPizza.Pizza;
import com.example.rupizza.RuPizza.SpecialityPizza;

import java.util.List;
import java.util.Map;

public class StoreOrdersAdapter extends BaseAdapter {

    private List<Integer> orderIDs;
    private Map<Integer, Order> orderMapping;
    private Context context;

    private Order selectedOrder;

    public void updateSelectedOrder(Order order) {
        this.selectedOrder = order;
        notifyDataSetChanged();
    }


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

    // Method to calculate the total price based on a list of pizzas
    private double calculateTotalPrice(List<Pizza> pizzas) {
        double totalPrice = 0.0;
        for (Pizza pizza : pizzas) {
            if (pizza instanceof SpecialityPizza) {
                totalPrice += ((SpecialityPizza) pizza).calculatePrice();
            }
            else if (pizza instanceof BuildYourOwnPizza) {
                totalPrice += ((BuildYourOwnPizza) pizza).calculatePrice();
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
            else if (pizza instanceof BuildYourOwnPizza) {
                tax += ((BuildYourOwnPizza) pizza).calculateTax();
            }
        }
        return tax;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_order, parent, false);
        }

        TextView textOrderDetails = convertView.findViewById(R.id.textOrderDetails);

        Order order = (Order) getItem(position);

        if (order != null && order == selectedOrder) {
            String orderDetails = "Order ID: " + orderMapping.keySet() + "\n";

            // Iterate over the pizzas in the order and append details
            for (Pizza pizza : order.getPizzas()) {
                if (pizza instanceof SpecialityPizza) {
                    SpecialityPizza specialityPizza = (SpecialityPizza) pizza;
                    orderDetails += "Order ID: " + specialityPizza.getPizzaID() + "\n" +
                            "Pizza Type: " + specialityPizza.getPizzaType() + "\n" +
                            "Quantity: " + specialityPizza.getQuantity() + "\n" +
                            "Size: " + specialityPizza.getSize() + "\n" +
                            "Extra Cheese: " + (specialityPizza.isExtraCheese() ? "yes" : "no") + "\n" +
                            "Extra Sauce: " + (specialityPizza.isExtraSauce() ? "yes" : "no") + "\n" +
                            "Toppings: " + specialityPizza.getToppings() + "\n" +
                            "Total Price: $" + specialityPizza.calculatePrice() + "\n" +
                            "Tax: $" + specialityPizza.calculateTax() + "\n" +
                            "Total: $" + (specialityPizza.total()) + "\n\n";
                }
                else if (pizza instanceof BuildYourOwnPizza) {
                    BuildYourOwnPizza specialityPizza = (BuildYourOwnPizza) pizza;
                    orderDetails += "Order ID: " + specialityPizza.getPizzaID() + "\n" +
                            "Pizza Type: " + specialityPizza.getPizzaType() + "\n" +
                            "Quantity: " + specialityPizza.getQuantity() + "\n" +
                            "Size: " + specialityPizza.getSize() + "\n" +
                            "Extra Cheese: " + (specialityPizza.isExtraCheese() ? "yes" : "no") + "\n" +
                            "Extra Sauce: " + (specialityPizza.isExtraSauce() ? "yes" : "no") + "\n" +
                            "Toppings: " + specialityPizza.getToppings() + "\n" +
                            "Total Price: $" + specialityPizza.calculatePrice() + "\n" +
                            "Tax: $" + specialityPizza.calculateTax() + "\n" +
                            "Total: $" + (specialityPizza.total()) + "\n\n";
                }
            }

            textOrderDetails.setText(orderDetails);
        } else {
            // If the order is not selected, set an empty string to clear the view
            textOrderDetails.setText("");
        }

        return convertView;
    }



}
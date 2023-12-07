package com.example.rupizza;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rupizza.RuPizza.Order;
import com.example.rupizza.RuPizza.Pizza;
import com.example.rupizza.RuPizza.SpecialityPizza;
import com.example.rupizza.RuPizza.StoreOrders;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CurrentOrder extends AppCompatActivity {
    private ListView listView;
    private Spinner spinnerOrderIDs; // Add spinner for Order IDs
    private CurrentOrderAdapter currentOrderAdapter;
    private Button btnRemoveOrder;
    private TextView textViewTotalPrice;
    private TextView textViewTax;
    private TextView textViewTotal;
    private Button btnPlaceOrder;


    private StoreOrdersAdapter storeOrdersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        listView = findViewById(R.id.listViewOrder);
        spinnerOrderIDs = findViewById(R.id.spinnerOrderIDs);
        btnRemoveOrder = findViewById(R.id.btnRemoveOrder);
        textViewTotalPrice = findViewById(R.id.totalPrice);
        textViewTax = findViewById(R.id.taxPrice);
        textViewTotal = findViewById(R.id.total);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

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

        // Add an OnItemClickListener to the ListView to display order details on item click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected pizza
                Pizza selectedPizza = (Pizza) currentOrderAdapter.getItem(position);

                // Display order details in a Toast
                displayOrderDetails(selectedPizza);
            }
        });

        // Add a click listener to the Remove Order button
        btnRemoveOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the order based on the selected Order ID
                int selectedOrderID = (int) spinnerOrderIDs.getSelectedItem();

                // Create an instance of the Order class
                Order currentOrder = Order.getPizzaOrder();

                // Remove the order by ID
                removeOrderByOrderID(selectedOrderID);

                // Refresh the ListView after removing the order
                currentOrderAdapter.setPizzas(currentOrder.getPizzas());
                currentOrderAdapter.notifyDataSetChanged();
            }
        });



        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Check if the order list is empty
                if (Order.getPizzaOrder().getPizzas().isEmpty()) {
                    showErrorDialog("Cannot place an empty order.");
                } else {
                    placeOrderInStore();
//                    // Clear the current order
                    clearCurrentOrder();

                    // Notify the adapter that the data has changed
                    currentOrderAdapter.notifyDataSetChanged();

                    // Log the end of the onClick method
                    Log.d("CurrentOrder", "Place Order button click finished");
                    showSuccessDialog("Order Placed in Store Order!");
                }
            }
        });

    }

//    // Modify the placeOrderInStore method to accept the order to place
//    private void placeOrderInStore(Order order) {
//        // Add the current order to the store orders
//        StoreOrders.getInstance().add(order);
//        // After adding the order in placeOrderInStore method
//        Log.d("StoreOrders", "Store Orders after placing order: " + StoreOrders.getInstance().getOrders());
//
//    }
//    // Create a copy of the current order
//    Order currentOrderCopy = new Order();
//                    currentOrderCopy.getPizzas().addAll(Order.getPizzaOrder().getPizzas());
    private void placeOrderInStore() {
        Order currentOrder = Order.getPizzaOrder();

        // Add the current order to the store orders
        StoreOrders.getInstance().add(currentOrder);
    }


    // Method to clear the current order
    private void clearCurrentOrder() {
        // Clear the ListView
        currentOrderAdapter.setPizzas(new ArrayList<>());
        currentOrderAdapter.notifyDataSetChanged();

        // Clear the Spinner selection
        spinnerOrderIDs.setSelection(0); // Assuming the first item is "All Orders"

        // Clear the TextViews for total price, tax, and total
        textViewTotalPrice.setText("Total Price: $0.00");
        textViewTax.setText("Tax: $0.00");
        textViewTotal.setText("Total: $0.00");

        // Clear the current order after updating the UI
        Order.getPizzaOrder().resetOrder();
    }


    // Method to display order details in a Toast
    private void displayOrderDetails(Pizza pizza) {
        String orderDetails = "";

        if (pizza instanceof SpecialityPizza) {
            SpecialityPizza specialityPizza = (SpecialityPizza) pizza;
            orderDetails +=
                    "Pizza Type: " + specialityPizza.getPizzaType();
        }

        Toast.makeText(this, orderDetails, Toast.LENGTH_LONG).show();
    }



    private List<Pizza> filterPizzasByOrderID(int orderID) {
        // Create an instance of the Order class
        Order currentOrder = Order.getPizzaOrder();

        if (orderID == -1) {
            // Return all pizzas when "All Orders" is selected
            return currentOrder.getPizzas();
        }

        List<Pizza> filteredPizzas = new ArrayList<>();
        for (Pizza pizza : currentOrder.getPizzas()) {
            if (pizza instanceof SpecialityPizza && ((SpecialityPizza) pizza).getPizzaID() == orderID) {
                filteredPizzas.add(pizza);
            }
        }
        return filteredPizzas;
    }

    private void removeOrderByOrderID(int orderID) {
        // Create an instance of the Order class
        Order currentOrder = Order.getPizzaOrder();

        List<Pizza> pizzasToRemove = new ArrayList<>();
        for (Pizza pizza : currentOrder.getPizzas()) {
            if (pizza instanceof SpecialityPizza && ((SpecialityPizza) pizza).getPizzaID() == orderID) {
                pizzasToRemove.add(pizza);
            }
        }

        if (!pizzasToRemove.isEmpty()) {
            currentOrder.getPizzas().removeAll(pizzasToRemove);

            // Remove the Order ID from the Spinner adapter
            ArrayAdapter<Integer> orderIDAdapter = (ArrayAdapter<Integer>) spinnerOrderIDs.getAdapter();
            if (orderIDAdapter != null) {
                orderIDAdapter.remove(orderID);
                orderIDAdapter.notifyDataSetChanged();

                showSuccessDialog("Order removed successfully!");
            }
        } else {
            // If the list is empty, show an error dialog
            showErrorDialog("No order found for the selected Order ID.");
        }
    }

    // Method to show a success dialog
    private void showSuccessDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Success")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    // You can add any additional action on OK button click
                    dialog.dismiss();
                })
                .show();
    }

    // Method to show an error dialog
    private void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    // You can add any additional action on OK button click
                    dialog.dismiss();
                })
                .show();
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




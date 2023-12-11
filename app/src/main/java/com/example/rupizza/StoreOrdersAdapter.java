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

/**
 * Represents the StoreOrders android studio Adapter class
 * @author Satya Pandya
 */
public class StoreOrdersAdapter extends BaseAdapter {

    private List<Integer> orderIDs;
    private Map<Integer, Order> orderMapping;
    private Context context;

    private Order selectedOrder;

    /**
     * Functino to update selected order
     * Notifies data set changed
     * @param order
     */
    public void updateSelectedOrder(Order order) {
        this.selectedOrder = order;
        notifyDataSetChanged();
    }

    /**
     * Constructor for the adapter class
     * @param context
     * @param orderIDs
     * @param orderMapping
     */
    public StoreOrdersAdapter(Context context, List<Integer> orderIDs, Map<Integer, Order> orderMapping) {
        this.context = context;
        this.orderIDs = orderIDs;
        this.orderMapping = orderMapping;
    }

    /**
     * Gets the size of the orderIDs
     * @return
     */
    @Override
    public int getCount() {
        return orderIDs.size();
    }

    /**
     * Get order at index
     * @param position Position of the item whose data we want within the adapter's
     * data set.
     * @return
     */
    @Override
    public Object getItem(int position) {
        return orderMapping.get(orderIDs.get(position));
    }

    /**
     * Get the position
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Method to calculate the total price based on a list of pizzas
     * @param pizzas
     * @return total price
     */
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

    /**
     * Method to calculate the tax based on a list of pizzas
     * @param pizzas
     * @return tax
     */
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

    /**
     * Function to set up the view
     * @param position The position of the item within the adapter's data set of the item whose view
     *        we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *        is non-null and of an appropriate type before using. If it is not possible to convert
     *        this view to display the correct data, this method can create a new view.
     *        Heterogeneous lists can specify their number of view types, so that this View is
     *        always of the right type (see {@link #getViewTypeCount()} and
     *        {@link #getItemViewType(int)}).
     * @param parent The parent that this view will eventually be attached to
     * @return View
     */
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
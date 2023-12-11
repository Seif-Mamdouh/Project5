package com.example.rupizza;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rupizza.RuPizza.Pizza;

import java.util.List;

/**
 * CurrentOrderAdapter is a custom adapter for displaying a list of pizzas in a ListView.
 *
 * @author Seifeldeen Mohamed
 */



public class CurrentOrderAdapter extends BaseAdapter {

    private List<Pizza> pizzas;
    private List<Pizza> originalPizzas;

    private TextView textViewTotalPrice;
    private TextView textViewTax;
    private TextView textViewTotal;


    /**
     * Constructs a {@code CurrentOrderAdapter} with the provided list of pizzas.
     *
     * @param pizzas The list of pizzas to be displayed in the adapter.
     */
    public CurrentOrderAdapter(List<Pizza> pizzas) {
        this.originalPizzas = pizzas;
        this.pizzas = pizzas;
    }

    /**
     * Sets the list of pizzas for display in the adapter.
     *
     * @param pizzas The new list of pizzas to be displayed.
     */
    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }


    /**
     * Returns the number of pizzas in the current list.
     *
     * @return The number of pizzas in the list.
     */
    @Override
    public int getCount() {
        return pizzas.size();
    }

    /**
     * Returns the pizza at the specified position in the list.
     *
     * @param position
     * @return The pizza at specific position
     */
    @Override
    public Object getItem(int position) {
        return pizzas.get(position);
    }


    /**
     * Returns the item ID of the pizza at the specified position in the list.
     *
     * @param position
     * @return The item ID of the pizza.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * Returns a View that displays the pizza at the specified position in the list.
     *
     * @param position    The position of the pizza in the list.
     * @param convertView The recycled View to populate.
     * @param parent      The parent ViewGroup.
     * @return A View that displays the pizza at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(parent.getContext());
        textView.setText(pizzas.get(position).toString());
        return textView;
    }
}
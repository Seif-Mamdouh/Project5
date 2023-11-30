package com.example.rupizza;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rupizza.RuPizza.Pizza;

import java.util.List;

public class CurrentOrderAdapter extends BaseAdapter {

    private List<Pizza> pizzas;

    public CurrentOrderAdapter(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    @Override
    public int getCount() {
        return pizzas.size();
    }

    @Override
    public Object getItem(int position) {
        return pizzas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Implement the view for each item in the ListView
        // You might want to inflate a custom layout for each item
        // and populate it with the pizza details

        // Example:
        TextView textView = new TextView(parent.getContext());
        textView.setText(pizzas.get(position).toString());
        return textView;
    }
}
package com.example.rupizza;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rupizza.RuPizza.Pizza;

import java.util.List;

public class CurrentOrderAdapter extends BaseAdapter {

    private List<Pizza> pizzas;
    private List<Pizza> originalPizzas;  // Store the original list of pizzas


    public CurrentOrderAdapter(List<Pizza> pizzas) {
        this.originalPizzas = pizzas;
        this.pizzas = pizzas;
    }
    public void setPizzas(List<Pizza> pizzas) {
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

    public void setOriginalPizzas(List<Pizza> pizzas) {
        this.originalPizzas = pizzas;
        notifyDataSetChanged();  // Notify the adapter that the data set has changed
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(parent.getContext());
        textView.setText(pizzas.get(position).toString());
        return textView;
    }
}
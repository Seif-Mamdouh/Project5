package com.example.rupizza;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rupizza.RuPizza.Pizza;

import java.util.List;

public class CurrentOrderAdapter extends BaseAdapter {

    private List<Pizza> pizzas;
    private List<Pizza> originalPizzas;

    private TextView textViewTotalPrice;
    private TextView textViewTax;
    private TextView textViewTotal;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(parent.getContext());
        textView.setText(pizzas.get(position).toString());
        return textView;
    }
}
package com.example.rupizza;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rupizza.RuPizza.Order;

import java.util.List;

public class StoreOrdersAdapter extends BaseAdapter {
    private List<Order> storeOrders;

    public StoreOrdersAdapter(List<Order> storeOrders) {
        this.storeOrders = storeOrders;
    }

    @Override
    public int getCount() {
        return storeOrders.size();
    }

    @Override
    public Object getItem(int position) {
        return storeOrders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public void updateStoreOrders(List<Order> storeOrders) {
        this.storeOrders = storeOrders;
        notifyDataSetChanged();
        Log.d("StoreOrdersAdapter", "Data updated. New size: " + storeOrders.size());
    }
}


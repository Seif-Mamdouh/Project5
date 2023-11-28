package com.example.rupizza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rupizza.R;
import com.example.rupizza.RuPizza.Pizza;
import com.example.rupizza.RuPizza.SpecialityPizza;
import com.example.rupizza.SpecialityPizzaViewHolder;

import java.util.List;

public class SpecialityPizzaAdapter extends RecyclerView.Adapter<SpecialityPizzaViewHolder> {
    private List<Pizza> pizzaList;
    private Context context;

    public SpecialityPizzaAdapter(List<Pizza> pizzaList, Context context) {
        this.pizzaList = pizzaList;
        this.context = context;
    }

    @NonNull
    @Override
    public SpecialityPizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_speciality_pizza, parent, false);
        return new SpecialityPizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialityPizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.pizzaDetails.setText(pizza.toString());
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }
}

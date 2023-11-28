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
import com.bumptech.glide.Glide;


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

        // Load pizza image using Glide
        Glide.with(context)
                .load(getPizzaImageResource(pizza.getPizzaType())) // Define a method to map pizza types to image resources
                .placeholder(R.drawable.ic_launcher_background) // Placeholder image while loading
//                .error(R.drawable. // Error image if loading fails
                .into(holder.imagePizza);

        // Display only the pizza type
        holder.textPizzaDetails.setText(pizza.getPizzaType().toString());
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }


    private int getPizzaImageResource(Pizza.PizzaType pizzaType) {
        switch (pizzaType) {
            case DELUXE:
                return R.drawable.ic_launcher_background;
            case SUPREME:
                return R.drawable.ic_launcher_background;
            case SEAFOOD:
                return R.drawable.ic_launcher_background;
            case PEPPERONI:
                return R.drawable.ic_launcher_background;
            case MEATZZA:
                return R.drawable.ic_launcher_background;
            case HALAL:
                return R.drawable.ic_launcher_background;
            case CHEESE:
                return R.drawable.ic_launcher_background;
            case SALMON:
                return R.drawable.ic_launcher_background;
            case SHRIMP:
                return R.drawable.ic_launcher_background;
            case MIX_GRILL:
                return R.drawable.ic_launcher_background;
            case BUFFALO_CHICKEN:
                return R.drawable.ic_launcher_background;
            default:
                return R.drawable.ic_launcher_background;
        }
    }
}

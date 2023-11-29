package com.example.rupizza;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rupizza.R;
import com.example.rupizza.RuPizza.Pizza;
import com.example.rupizza.RuPizza.Size;
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
        SpecialityPizza pizza = (SpecialityPizza) pizzaList.get(position);

        // Load pizza image using Glide
        Glide.with(context)
                .load(getPizzaImageResource(pizza.getPizzaType()))
                .placeholder(R.drawable.pizza)
                .into(holder.imagePizza);

        // Display pizza type
        holder.textPizzaDetails.setText(pizza.getPizzaType().toString());

        // Display toppings
        List<String> toppings = pizza.getToppings();
        if (toppings == null || toppings.isEmpty()) {
            // If toppings list is not provided, use default toppings
            toppings = Pizza.getDefaultToppings(pizza.getPizzaType());
        }

        if (toppings != null && !toppings.isEmpty()) {
            holder.textToppings.setText("Toppings: " + TextUtils.join(", ", toppings));
        } else {
            holder.textToppings.setText("No toppings");
        }

        ArrayAdapter<Size> sizeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, Size.values());
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinnerSize.setAdapter(sizeAdapter);


        holder.checkBoxExtraCheese.setChecked(pizza.isExtraCheese());
        holder.checkBoxExtraSauce.setChecked(pizza.isExtraSauce());

        // Handle spinner item selection
        holder.spinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int selectedPosition, long id) {
                // Create a SpecialityPizza with the selected size
                Size selectedSize = Size.values()[selectedPosition];
                Pizza selectedPizza = Pizza.createPizza(
                        pizza.getPizzaType(),
                        selectedSize,
                        pizza.isExtraSauce(),
                        pizza.isExtraCheese(),
                        pizza.getToppings(),
                        pizza.getQuantity()
                );

                // Do something with the selected pizza, e.g., update the data set or perform an action
                // For now, let's just log the details
                Log.d("SpecialityPizzaAdapter", "Selected Pizza: " + selectedPizza);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if nothing is selected
            }
        });
    }


    @Override
    public int getItemCount() {
        return pizzaList.size();
    }


    private int getPizzaImageResource(Pizza.PizzaType pizzaType) {
        if (pizzaType == null) {
            // Handle null case, return a default image or something
            return R.drawable.pizza;
        }

        switch (pizzaType) {
            case DELUXE:
                return R.drawable.deluxe;
            case SUPREME:
                return R.drawable.supreme;
            case SEAFOOD:
                return R.drawable.seafood;
            case PEPPERONI:
                return R.drawable.pepperoni;
            case MEATZZA:
                return R.drawable.meattza;
            case HALAL:
                return R.drawable.halal;
            case CHEESE:
                return R.drawable.cheese;
            case SALMON:
                return R.drawable.salmon;
            case SHRIMP:
                return R.drawable.pizza;
            case MIX_GRILL:
                return R.drawable.mixedgrill;
            case BUFFALO_CHICKEN:
                return R.drawable.buffalo;
            default:
                return R.drawable.ic_launcher_background;
        }
    }
}

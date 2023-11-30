package com.example.rupizza;

import static com.example.rupizza.RuPizza.Pizza.getDefaultToppings;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rupizza.RuPizza.Pizza;
import com.example.rupizza.RuPizza.Size;
import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SpecialityPizzaAdapter extends RecyclerView.Adapter<SpecialityPizzaViewHolder> {
    private List<Pizza.PizzaType> pizzaList;
    private Context context;

    public SpecialityPizzaAdapter(List<Pizza.PizzaType> pizzaList, Context context) {
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
        Pizza.PizzaType pizzaType = pizzaList.get(position);

        // Load pizza image using Glide or any other image loading library
        Glide.with(context)
                .load(getPizzaImageResource(pizzaType))
                .placeholder(R.drawable.pizza) // Use a default image or placeholder
                .into(holder.imagePizza);

        holder.textPizzaDetails.setText(pizzaType.toString());

        AtomicReference<List<String>> toppings = new AtomicReference<>(getDefaultToppings(pizzaType));
        if (toppings.get() != null && !toppings.get().isEmpty()) {
            holder.textToppings.setText("Toppings: " + TextUtils.join(", ", toppings.get()));
        } else {
            holder.textToppings.setText("No toppings");
        }

        // Populate the spinner with available sizes
        ArrayAdapter<Size> sizeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, Size.values());
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinnerSize.setAdapter(sizeAdapter);

        // Set the selected size in the spinner
        int selectedSizePosition = sizeAdapter.getPosition(Size.SMALL); // Set a default size
        holder.spinnerSize.setSelection(selectedSizePosition);

        // Handle spinner item selection
        holder.spinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int selectedPosition, long id) {
                // Do something when a size is selected
                Size selectedSize = Size.values()[selectedPosition];
                // You can perform actions based on the selected size
                Log.d("SpecialityPizzaAdapter", "Selected size: " + selectedSize);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if nothing is selected
            }
        });

        // Handle checkbox states
        holder.checkBoxExtraSauce.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Handle extra sauce checkbox state change
            Log.d("SpecialityPizzaAdapter", "Extra Sauce: " + isChecked);
        });

        holder.checkBoxExtraCheese.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Handle extra cheese checkbox state change
            Log.d("SpecialityPizzaAdapter", "Extra Cheese: " + isChecked);
        });


        // Populate the quantity spinner
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getQuantityOptions());
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinnerQuantity.setAdapter(quantityAdapter);

        // Set the selected quantity in the spinner
        int selectedQuantityPosition = quantityAdapter.getPosition(1);
        holder.spinnerQuantity.setSelection(selectedQuantityPosition);


        holder.btnAddToCart.setOnClickListener(view -> {
            // Get the selected pizza details
            Size selectedSize = Size.values()[holder.spinnerSize.getSelectedItemPosition()];
            boolean extraCheese = holder.checkBoxExtraCheese.isChecked();
            boolean extraSauce = holder.checkBoxExtraSauce.isChecked();
            toppings.set(getDefaultToppings(pizzaType)); // Implement this method as needed
            int quantity = (int) holder.spinnerQuantity.getSelectedItem(); // Assuming spinner is populated with Integer values

            // Create a SpecialityPizza instance
            Pizza selectedPizza = Pizza.createPizza(pizzaType, selectedSize, extraSauce, extraCheese, toppings.get(), quantity);

            // Do something with the created pizza, add to OrderClass
            Log.d("SpecialityPizzaAdapter", "Added Pizza to Cart: " + selectedPizza);
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

    private List<Integer> getQuantityOptions() {
        List<Integer> options = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            options.add(i);
        }
        return options;
    }
}

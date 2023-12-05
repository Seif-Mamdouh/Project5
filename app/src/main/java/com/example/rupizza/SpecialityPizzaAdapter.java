package com.example.rupizza;

import static com.example.rupizza.RuPizza.Pizza.getDefaultToppings;
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
import com.example.rupizza.RuPizza.Order;
import com.example.rupizza.RuPizza.Pizza;
import com.example.rupizza.RuPizza.Size;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import androidx.appcompat.app.AlertDialog;


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
            private boolean userInteraction = true; // Flag to track user interaction
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int selectedPosition, long id) {
                // Check if the change is due to user interaction
                if (userInteraction) {
                    updateBasePrice(holder);
                }
                userInteraction = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if nothing is selected
            }
        });

        // Handle checkbox states
        holder.checkBoxExtraSauce.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateBasePrice(holder);
        });

        holder.checkBoxExtraCheese.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateBasePrice(holder);

        });

        // Populate the quantity spinner
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getQuantityOptions());
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinnerQuantity.setAdapter(quantityAdapter);

        // Set the selected quantity in the spinner
        int selectedQuantityPosition = quantityAdapter.getPosition(1);
        holder.spinnerQuantity.setSelection(selectedQuantityPosition);

        // Handle quantity spinner item selection
        holder.spinnerQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int selectedPosition, long id) {
                updateBasePrice(holder);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if nothing is selected
            }
        });


        holder.btnAddToCart.setOnClickListener(view -> {
            // Get the selected pizza details
            Size selectedSize = Size.values()[holder.spinnerSize.getSelectedItemPosition()];
            boolean extraCheese = holder.checkBoxExtraCheese.isChecked();
            boolean extraSauce = holder.checkBoxExtraSauce.isChecked();
            toppings.set(getDefaultToppings(pizzaType));
            int quantity = (int) holder.spinnerQuantity.getSelectedItem();

            Pizza selectedPizza = Pizza.createPizza(pizzaType, selectedSize, extraSauce, extraCheese, toppings.get(), quantity);

            boolean addedToOrder = Order.getPizzaOrder().addPizza(selectedPizza);

            if (addedToOrder) {
                Log.d("SpecialityPizzaAdapter", "Added Pizza to Order: " + selectedPizza);
                showSuccessDialog();
            } else {
                Log.e("SpecialityPizzaAdapter", "Failed to add Pizza to Order");
            }
        });
    }



    @Override
    public int getItemCount() {
        return pizzaList.size();
    }


    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Success")
                .setMessage("Pizza added to the order successfully!")
                .setPositiveButton("OK", (dialog, which) -> {
                    // Handle OK button click if needed
                    dialog.dismiss();
                })
                .show();
    }

    // Helper method to update the base price
    private void updateBasePrice(SpecialityPizzaViewHolder holder) {
        // Retrieve the selected size from the spinner
        Size selectedSize = Size.values()[holder.spinnerSize.getSelectedItemPosition()];

        // Retrieve the selected quantity from the spinner
        int selectedQuantity = (int) holder.spinnerQuantity.getSelectedItem();

        // Recalculate and update the total price
        double total = calculateTotal(
                pizzaList.get(holder.getAdapterPosition()),
                selectedSize,
                selectedQuantity,
                holder.checkBoxExtraCheese.isChecked(),
                holder.checkBoxExtraSauce.isChecked()
        );
        holder.textBasePrice.setText("Total Price: $" + String.format("%.2f", total));
    }

    private double calculateTotal(Pizza.PizzaType pizzaType, Size size, int quantity, boolean extraCheese, boolean extraSauce) {
        // Calculate the base price based on pizza type, size, and optional toppings
        double basePrice = calculateBasePrice(pizzaType, size, extraCheese, extraSauce);

        // Calculate the total price based on quantity
        return basePrice * quantity;
    }
    private double calculateBasePrice(Pizza.PizzaType pizzaType, Size size, boolean extraCheese, boolean extraSauce) {
        Log.d("SpecialityPizzaAdapter", "Calculating Base Price for Pizza Type: " + pizzaType + ", Size: " + size);

        double basePrice;

        switch (pizzaType) {
            case DELUXE:
                basePrice = switch (size) {
                    case SMALL -> 14.99;
                    case MEDIUM -> 14.99 + 2.0; // $2 extra for medium
                    case LARGE -> 14.99 + 4.0; // $4 extra for large
                };
                break;
            case SUPREME:
                basePrice = switch (size) {
                    case SMALL -> 15.99;
                    case MEDIUM -> 15.99 + 2.0;
                    case LARGE -> 15.99 + 4.0;
                };
            case MEATZZA:
                basePrice = switch (size) {
                    case SMALL -> 16.99;
                    case MEDIUM -> 16.99 + 2.0;
                    case LARGE -> 16.99 + 4.0;
                };
            case SEAFOOD:
                basePrice = switch (size) {
                    case SMALL -> 17.99;
                    case MEDIUM -> 17.99 + 2.0;
                    case LARGE -> 17.99 + 4.0;
                };
                break;
            case PEPPERONI, SHRIMP, HALAL, BUFFALO_CHICKEN, SALMON, CHEESE, MIX_GRILL:
                        basePrice = switch (size) {
                            case SMALL -> 10.99;
                            case MEDIUM -> 10.99 + 2.0;
                            case LARGE -> 10.99 + 4.0;
                        };
                break;
            default:
                basePrice = 0.0;
        }

        if (extraCheese) {
            basePrice += 1.0;
        }

        if (extraSauce) {
            basePrice += 1.0;
        }

        return basePrice;
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

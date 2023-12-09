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




/**
 * {RecyclerView.Adapter} implementation for managing the display of speciality pizzas in a {RecyclerView}.
 * This adapter is designed to work with the {SpecialityPizzaViewHolder} class for item views.
 *
 * @author Seifeldeen Mohamed
 */
public class SpecialityPizzaAdapter extends RecyclerView.Adapter<SpecialityPizzaViewHolder> {

    /**
     * The list of speciality pizza types to be displayed.
     */
    private List<Pizza.PizzaType> pizzaList;

    /**
     * The context associated with the adapter.
     */
    private Context context;


    /**
     * Constructs a new instance of {@code SpecialityPizzaAdapter}.
     *
     * @param pizzaList The list of speciality pizza types to be displayed.
     * @param context   The context associated with the adapter.
     */
    public SpecialityPizzaAdapter(List<Pizza.PizzaType> pizzaList, Context context) {
        this.pizzaList = pizzaList;
        this.context = context;
    }

    /**
     * Called when RecyclerView needs a new {SpecialityPizzaViewHolder} of the given type to represent an item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new {SpecialityPizzaViewHolder} that holds a View of the given view type.
     */
    @NonNull
    @Override
    public SpecialityPizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_speciality_pizza, parent, false);
        return new SpecialityPizzaViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder   The {SpecialityPizzaViewHolder} that should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull SpecialityPizzaViewHolder holder, int position) {
        Pizza.PizzaType pizzaType = pizzaList.get(position);

        Glide.with(context)
                .load(getPizzaImageResource(pizzaType))
                .placeholder(R.drawable.pizza)
                .into(holder.imagePizza);

        holder.textPizzaDetails.setText(pizzaType.toString());

        AtomicReference<List<String>> toppings = new AtomicReference<>(getDefaultToppings(pizzaType));
        if (toppings.get() != null && !toppings.get().isEmpty()) {
            holder.textToppings.setText("Toppings: " + TextUtils.join(", ", toppings.get()));
        } else {
            holder.textToppings.setText("No toppings");
        }


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
                //showSuccessDialog();
            } else {
                Log.e("SpecialityPizzaAdapter", "Failed to add Pizza to Order");
            }
        });
    }


    /**
     * Gets the number of items in the data set represented by this adapter.
     *
     * @return The total number of items in the data set held by the adapter.
     */
    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    /**
     * Shows a success dialog when a pizza is successfully added to the order.
     */
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

    /**
     * Helper method to update the base price based on the selected size, quantity, and checkbox states.
     *
     * @param holder The {SpecialityPizzaViewHolder} containing the UI elements.
     */
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

    /**
     * Calculates the total price of a pizza based on its type, size, quantity, and optional toppings.
     *
     * @param pizzaType   The type of the pizza.
     * @param size        The size of the pizza.
     * @param quantity    The quantity of the pizza.
     * @param extraCheese  Whether extra cheese is selected.
     * @param extraSauce   Whether extra sauce is selected.
     * @return The total price of the pizza.
     */
    private double calculateTotal(Pizza.PizzaType pizzaType, Size size, int quantity, boolean extraCheese, boolean extraSauce) {
        // Calculate the base price based on pizza type, size, and optional toppings
        double basePrice = calculateBasePrice(pizzaType, size, extraCheese, extraSauce);

        // Calculate the total price based on quantity
        return basePrice * quantity;
    }

    /**
     * Calculates the base price of a pizza based on its type, size, and optional toppings.
     *
     * @param pizzaType   The type of the pizza.
     * @param size        The size of the pizza.
     * @param extraCheese  Whether extra cheese is selected.
     * @param extraSauce   Whether extra sauce is selected.
     * @return The base price of the pizza.
     */
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

    /**
     * Gets the image resource ID for the specified pizza type.
     *
     * @param pizzaType The type of the pizza.
     * @return The image resource ID for the specified pizza type.
     */
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


    /**
     * Gets a list of quantity options from 1 to 10.
     *
     * @return A list of quantity options.
     */
    private List<Integer> getQuantityOptions() {
        List<Integer> options = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            options.add(i);
        }
        return options;
    }
}

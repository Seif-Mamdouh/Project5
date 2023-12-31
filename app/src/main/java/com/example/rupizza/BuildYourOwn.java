package com.example.rupizza;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rupizza.RuPizza.Order;
import com.example.rupizza.RuPizza.Pizza;
import com.example.rupizza.RuPizza.Size;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the BuildYourOwn android studio front-end to back-end connector class
 * @author Satya Pandya
 */
public class BuildYourOwn extends AppCompatActivity {

    private Spinner sizeTypeSpinner;
    private CheckBox extraSauceCheckBox;
    private CheckBox extraCheeseCheckBox;

    private CheckBox sauceSelection;
    private ListView availableToppingsListView;
    private ListView selectedToppingsListView;
    private TextView pizzaSubTotalTextView;
    private Button placeOrderButton;
    private ImageView pizzaImageView;

    private Context context;

    private static final double EXTRA_TOPPING_COST = 1.0;
    private static final int MAX_TOPPINGS = 7;
    private static final int MIN_TOPPINGS = 3;

    private static final double SMALL_PRICE = 8.99;
    private static final double MEDIUM_PRICE = 10.99;
    private static final double LARGE_PRICE = 12.99;

    private static final double TOPPING_PRICE = 1.49;

    private List<String> availableToppings;
    private List<String> selectedToppings;

    /**
     * onCreate function to create android studio instance
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_your_own);

        initializeViews();
        initializeUI();
        setupListeners();
    }

    /**
     * Method to initialize views
     */
    private void initializeViews() {
        sizeTypeSpinner = findViewById(R.id.sizeTypeSpinner);
        extraSauceCheckBox = findViewById(R.id.extraSauceCheckBox);
        extraCheeseCheckBox = findViewById(R.id.extraCheeseCheckBox);
        sauceSelection = findViewById(R.id.sauceSelection);
        availableToppingsListView = findViewById(R.id.availableToppingsListView);
        selectedToppingsListView = findViewById(R.id.selectedToppingsListView);
        pizzaSubTotalTextView = findViewById(R.id.pizzaSubTotalTextView);
        placeOrderButton = findViewById(R.id.placeOrderButton);
        pizzaImageView = findViewById(R.id.pizzaImageView);
    }

    /**
     * Method to initialize UI
     */
    private void initializeUI() {
        pizzaSubTotalTextView.setText("$0.00");

        availableToppings = new ArrayList<>(Arrays.asList(
                "Pepperoni", "Mushrooms", "Green peppers", "Onions", "Sausage",
                "Black olives", "Bacon", "Pineapple", "Fresh tomatoes", "Spinach",
                "Jalapenos", "Feta cheese", "Blue cheese"
        ));

        selectedToppings = new ArrayList<>();

        ArrayAdapter<String> availableToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, availableToppings);
        ArrayAdapter<String> selectedToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedToppings);

        availableToppingsListView.setAdapter(availableToppingsAdapter);
        selectedToppingsListView.setAdapter(selectedToppingsAdapter);

        List<String> sizeOptions = Arrays.asList("Small", "Medium", "Large");
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizeOptions);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeTypeSpinner.setAdapter(sizeAdapter);

        sizeTypeSpinner.setSelection(0);
        updateCost();
        changePicture("path/to/default/image");
    }

    /**
     * Method to setup the listeners
     */
    private void setupListeners() {
        extraSauceCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> updateCost());
        extraCheeseCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> updateCost());

        availableToppingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onAvailableToppingClicked(position);
            }
        });

        selectedToppingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onRemoveToppingClicked(position);
            }
        });

        placeOrderButton.setOnClickListener(v -> {
            // Get the selected pizza details
            String selectedSize = sizeTypeSpinner.getSelectedItem().toString().toUpperCase();
            boolean extraCheese = extraCheeseCheckBox.isChecked();
            boolean extraSauce = extraSauceCheckBox.isChecked();

            if(selectedToppings.size() < MIN_TOPPINGS) {
                Log.e("BuildYourOwn", "Select at least 3 toppings.");
                showErrorDialog("Select at least 3 toppings.");
                return;
            }

            String selectedSauce = sauceSelection.isChecked() ? "Alfredo sauce" : "Tomato sauce";
            selectedToppings.add(selectedSauce);

            Log.d("BuildYourOwn", "Selected Sauce: " + selectedSauce);

            List<String> selectedToppingsCopy = new ArrayList<>(selectedToppings);
            int quantity = 1;

            Pizza selectedPizza = Pizza.createPizza(Pizza.PizzaType.BUILD_YOUR_OWN,
                    Size.valueOf(selectedSize),
                    extraSauce,
                    extraCheese,
                    selectedToppingsCopy,
                    quantity
            );

            boolean addedToOrder = Order.getPizzaOrder().addPizza(selectedPizza);

            if (addedToOrder) {
                // Handle success, you may show a success dialog or perform other actions
                Log.d("BuildYourOwn", "Added Pizza to Order: " + selectedPizza);
                showSuccessDialog(this, "Pizza added to the order successfully!");
                clearOrder();

            } else {
                // Handle failure
                Log.e("BuildYourOwn", "Failed to add Pizza to Order");
                showErrorDialog("Failed to add Pizza to Order.");
            }
        });
    }

    /**
     * Method to clean the order
     */
    private void clearOrder() {
        // Reset spinner to the first item
        sizeTypeSpinner.setSelection(0);

        // Uncheck checkboxes
        extraCheeseCheckBox.setChecked(false);
        extraSauceCheckBox.setChecked(false);


        // Clear selected toppings on the UI thread
        runOnUiThread(() -> {
            updateToppingsLists();
        });

        // Update UI elements
        updateCost();

    }

    /**
     * Method to update the toppings list
     */
    private void updateToppingsLists() {
        runOnUiThread(() -> {
            ArrayAdapter<String> selectedToppingsAdapter = (ArrayAdapter<String>) selectedToppingsListView.getAdapter();
            selectedToppingsAdapter.clear();
            selectedToppingsAdapter.addAll(selectedToppings);
            selectedToppingsAdapter.notifyDataSetChanged();
        });
    }


    /**
     * Method to show the success dialog
     * @param context
     * @param message
     */
    private void showSuccessDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Success")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    // Handle OK button click if needed
                    dialog.dismiss();
                })
                .show();
    }

    /**
     * Method to show the error dialog
     * @param message
     */
    private void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    // You can add any additional action on OK button click
                    dialog.dismiss();
                })
                .show();
    }

    /**
     * Method to change the picture
     * @param imagePath
     */
    private void changePicture(String imagePath) {
        pizzaImageView.setImageResource(R.drawable.pizza);
    }

    /**
     * Method to update the cost
     */
    private void updateCost() {
        double totalCost = calculateCost();
        if (extraSauceCheckBox.isChecked()) {
            totalCost += EXTRA_TOPPING_COST;
        }
        if (extraCheeseCheckBox.isChecked()) {
            totalCost += EXTRA_TOPPING_COST;
        }
        pizzaSubTotalTextView.setText(String.format("$%.2f", totalCost));
    }

    /**
     * Helper method when available topping clicked
     * @param position
     */
    private void onAvailableToppingClicked(int position) {
        String selectedTopping = availableToppings.get(position);
        int selectedToppingsCount = selectedToppings.size();

        if (selectedToppingsCount < MAX_TOPPINGS) {
            availableToppings.remove(selectedTopping);
            selectedToppings.add(selectedTopping);
            updateCost();
        } else {
            // Handle failure
            Log.e("BuildYourOwn", "Select maximum of 7 toppings.");
            showErrorDialog("Select maximum of 7 toppings.");
        }

        // Notify the adapters that the data set has changed
        ((ArrayAdapter<String>) availableToppingsListView.getAdapter()).notifyDataSetChanged();
        ((ArrayAdapter<String>) selectedToppingsListView.getAdapter()).notifyDataSetChanged();
    }

    /**
     * Helper method when remove topping clicked
     * @param position
     */
    private void onRemoveToppingClicked(int position) {
        String selectedTopping = selectedToppings.get(position);
        availableToppings.add(selectedTopping);
        selectedToppings.remove(selectedTopping);
        updateCost();

        // Notify the adapters that the data set has changed
        ((ArrayAdapter<String>) availableToppingsListView.getAdapter()).notifyDataSetChanged();
        ((ArrayAdapter<String>) selectedToppingsListView.getAdapter()).notifyDataSetChanged();
    }

    /**
     * Method to calculate cost of order
     * @return
     */
    private double calculateCost() {
        String selectedSize = sizeTypeSpinner.getSelectedItem().toString();
        double basePrice = getBasePrice(selectedSize);
        double toppingsPrice = getToppingsPrice();

        return basePrice + toppingsPrice;
    }

    /**
     * Method to get the base price
     * @param size
     * @return base price
     */
    private double getBasePrice(String size) {
        switch (size) {
            case "Small":
                return SMALL_PRICE;
            case "Medium":
                return MEDIUM_PRICE;
            case "Large":
                return LARGE_PRICE;
            default:
                return 0.0;
        }
    }

    /**
     * Method to get the toppings price
     * @return price of toppings
     */
    private double getToppingsPrice() {
        int additionalToppings = Math.max(0, selectedToppings.size() - MIN_TOPPINGS);
        return additionalToppings * TOPPING_PRICE;
    }
}

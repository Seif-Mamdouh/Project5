package com.example.rupizza;


import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 * ViewHolder class for holding views of individual specialty pizza items in a RecyclerView.
 * Contains references to various UI elements such as image, text, spinner, checkboxes, and buttons.
 *
 * @author Seifeldeen Mohamed
 */
public class SpecialityPizzaViewHolder extends RecyclerView.ViewHolder {
    /** ImageView for displaying the pizza image. */
    public ImageView imagePizza;

    /** TextView for displaying pizza details. */
    public TextView textPizzaDetails;

    /** TextView for displaying pizza toppings. */
    public TextView textToppings;

    /** Spinner for selecting pizza size. */
    public Spinner spinnerSize;

    /** CheckBox for selecting extra cheese option. */
    public CheckBox checkBoxExtraCheese;

    /** CheckBox for selecting extra sauce option. */
    public CheckBox checkBoxExtraSauce;

    /** Spinner for selecting pizza quantity. */
    public Spinner spinnerQuantity;

    /** TextView for displaying the base price of the pizza. */
    public TextView textBasePrice;

    /** Button for adding the pizza to the cart. */
    public Button btnAddToCart;

    /**
     * Constructor for the SpecialityPizzaViewHolder.
     *
     * @param itemView The root view representing an item in the RecyclerView.
     */
    public SpecialityPizzaViewHolder(@NonNull View itemView) {
        super(itemView);
        imagePizza = itemView.findViewById(R.id.imagePizza);
        textPizzaDetails = itemView.findViewById(R.id.textPizzaDetails);
        textToppings = itemView.findViewById(R.id.textToppings);
        spinnerSize = itemView.findViewById(R.id.spinnerSize);
        checkBoxExtraCheese = itemView.findViewById(R.id.checkBoxExtraCheese);
        checkBoxExtraSauce = itemView.findViewById(R.id.checkBoxExtraSauce);
        spinnerQuantity = itemView.findViewById(R.id.spinnerQuantity);
        btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        textBasePrice = itemView.findViewById(R.id.textBasePrice);
    }
}


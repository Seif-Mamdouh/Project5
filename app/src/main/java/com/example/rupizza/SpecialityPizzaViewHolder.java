package com.example.rupizza;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.recyclerview.widget.RecyclerView;

public class SpecialityPizzaViewHolder extends RecyclerView.ViewHolder {
    public ImageView imagePizza;
    public TextView textPizzaDetails;
    public TextView textToppings;
    public Spinner spinnerSize;
    public CheckBox checkBoxExtraCheese;
    public CheckBox checkBoxExtraSauce;
    Spinner spinnerQuantity;
    public Button btnAddToCart;

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
    }
}


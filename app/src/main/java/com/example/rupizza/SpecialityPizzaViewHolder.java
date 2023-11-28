package com.example.rupizza;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpecialityPizzaViewHolder extends RecyclerView.ViewHolder {
    public ImageView imagePizza;
    public TextView textPizzaDetails;

    public SpecialityPizzaViewHolder(@NonNull View itemView) {
        super(itemView);
        imagePizza = itemView.findViewById(R.id.imagePizza);
        textPizzaDetails = itemView.findViewById(R.id.textPizzaDetails);
    }
}

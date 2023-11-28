package com.example.rupizza;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpecialityPizzaViewHolder extends RecyclerView.ViewHolder {
    TextView pizzaDetails;

    public SpecialityPizzaViewHolder(@NonNull View itemView) {
        super(itemView);
        pizzaDetails = itemView.findViewById(R.id.textPizzaDetails);
    }
}

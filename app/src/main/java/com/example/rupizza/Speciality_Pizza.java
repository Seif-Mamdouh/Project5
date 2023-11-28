package com.example.rupizza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rupizza.RuPizza.Pizza;
import com.example.rupizza.RuPizza.Size;
import com.example.rupizza.RuPizza.SpecialityPizza;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Speciality_Pizza extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speciality_pizza);

        // Create a list of Pizza objects with different types
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(new SpecialityPizza(Pizza.PizzaType.DELUXE, Size.MEDIUM, false, false));
        pizzaList.add(new SpecialityPizza(Pizza.PizzaType.SUPREME, Size.LARGE, true, false));
        pizzaList.add(new SpecialityPizza(Pizza.PizzaType.MEATZZA, Size.SMALL, true, true));

        // Add more pizzas or types as needed

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create and set the adapter
        SpecialityPizzaAdapter adapter = new SpecialityPizzaAdapter(pizzaList, this);
        recyclerView.setAdapter(adapter);
    }
}

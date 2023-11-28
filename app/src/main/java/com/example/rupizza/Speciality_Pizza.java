package com.example.rupizza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rupizza.RuPizza.Pizza;
import com.example.rupizza.RuPizza.SpecialityPizza;

import java.util.ArrayList;
import java.util.List;

public class Speciality_Pizza extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speciality_pizza);


        List<Pizza> pizzaList = new ArrayList<>();
        // Populate pizzaList with your data

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create and set the adapter
        SpecialityPizzaAdapter adapter = new SpecialityPizzaAdapter(pizzaList, this);
        recyclerView.setAdapter(adapter);
    }
}
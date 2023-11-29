package com.example.rupizza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rupizza.RuPizza.Pizza;
import com.example.rupizza.RuPizza.PizzaMaker;
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
        List<Pizza> pizzaList = createPizzaList();

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create and set the adapter
        SpecialityPizzaAdapter adapter = new SpecialityPizzaAdapter(pizzaList, this);

        recyclerView.setAdapter(adapter);
    }



    private List<Pizza> createPizzaList() {
        List<Pizza> list = new ArrayList<>();
        // Add pizzas using a loop or any other approach
        list.add(PizzaMaker.createPizza(Pizza.PizzaType.DELUXE, Size.SMALL, false, false, new ArrayList<>(), 1));
        list.add(PizzaMaker.createPizza(Pizza.PizzaType.SUPREME, Size.SMALL, false, false, new ArrayList<>(), 1));
        list.add(PizzaMaker.createPizza(Pizza.PizzaType.MEATZZA, Size.SMALL, true, false, new ArrayList<>(), 1));
        list.add(PizzaMaker.createPizza(Pizza.PizzaType.SEAFOOD, Size.SMALL, true, false, new ArrayList<>(), 1));
        list.add(PizzaMaker.createPizza(Pizza.PizzaType.PEPPERONI, Size.SMALL, true, false, new ArrayList<>(), 1));
        list.add(PizzaMaker.createPizza(Pizza.PizzaType.HALAL, Size.SMALL, true, false, new ArrayList<>(), 1));
        list.add(PizzaMaker.createPizza(Pizza.PizzaType.CHEESE, Size.SMALL, true, false, new ArrayList<>(), 1));
        list.add(PizzaMaker.createPizza(Pizza.PizzaType.MIX_GRILL, Size.SMALL, true, false, new ArrayList<>(), 1));
        list.add(PizzaMaker.createPizza(Pizza.PizzaType.SALMON, Size.SMALL, true, false, new ArrayList<>(), 1));
        list.add(PizzaMaker.createPizza(Pizza.PizzaType.SHRIMP, Size.SMALL, true, false, new ArrayList<>(), 1));
        list.add(PizzaMaker.createPizza(Pizza.PizzaType.BUFFALO_CHICKEN, Size.SMALL, true, false, new ArrayList<>(), 1));

        return list;
    }

}

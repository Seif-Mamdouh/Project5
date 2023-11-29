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
        list.add(createPizza(Pizza.PizzaType.DELUXE, Size.MEDIUM, false, false, new ArrayList<>()));
        list.add(createPizza(Pizza.PizzaType.SUPREME, Size.LARGE, true, false, new ArrayList<>()));
        list.add(createPizza(Pizza.PizzaType.MEATZZA, Size.LARGE, true, false, new ArrayList<>()));
        list.add(createPizza(Pizza.PizzaType.SEAFOOD, Size.LARGE, true, false, new ArrayList<>()));
        list.add(createPizza(Pizza.PizzaType.PEPPERONI, Size.LARGE, true, false, new ArrayList<>()));
        list.add(createPizza(Pizza.PizzaType.HALAL, Size.LARGE, true, false, new ArrayList<>()));
        list.add(createPizza(Pizza.PizzaType.CHEESE, Size.LARGE, true, false, new ArrayList<>()));
        list.add(createPizza(Pizza.PizzaType.MIX_GRILL, Size.LARGE, true, false, new ArrayList<>()));
        list.add(createPizza(Pizza.PizzaType.SALMON, Size.LARGE, true, false, new ArrayList<>()));
        list.add(createPizza(Pizza.PizzaType.SHRIMP, Size.LARGE, true, false, new ArrayList<>()));
        list.add(createPizza(Pizza.PizzaType.BUFFALO_CHICKEN, Size.LARGE, true, false, new ArrayList<>()));



        return list;
    }

    private SpecialityPizza createPizza(Pizza.PizzaType pizzaType, Size size, boolean extraSauce, boolean extraCheese, List<String> toppings) {
        List<String> defaultToppings = Pizza.getDefaultToppings(pizzaType);
        return new SpecialityPizza(pizzaType, size, extraSauce, extraCheese, defaultToppings);
    }
}

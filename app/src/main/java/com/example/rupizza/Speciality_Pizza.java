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
        list.add(createPizza(Pizza.PizzaType.DELUXE, Size.MEDIUM, false, false));
        list.add(createPizza(Pizza.PizzaType.SUPREME, Size.LARGE, true, false));
        list.add(createPizza(Pizza.PizzaType.MEATZZA, Size.SMALL, true, true));
        list.add(createPizza(Pizza.PizzaType.SEAFOOD, Size.MEDIUM, true, false));
        list.add(createPizza(Pizza.PizzaType.PEPPERONI, Size.LARGE, false, true));
        list.add(createPizza(Pizza.PizzaType.HALAL, Size.MEDIUM, true, true));
        list.add(createPizza(Pizza.PizzaType.BUFFALO_CHICKEN, Size.LARGE, false, true));
        list.add(createPizza(Pizza.PizzaType.CHEESE, Size.SMALL, false, false));
        list.add(createPizza(Pizza.PizzaType.MIX_GRILL, Size.MEDIUM, true, false));
        list.add(createPizza(Pizza.PizzaType.SALMON, Size.LARGE, true, true));
        list.add(createPizza(Pizza.PizzaType.SHRIMP, Size.SMALL, false, true));

        return list;
    }

    private SpecialityPizza createPizza(Pizza.PizzaType pizzaType, Size size, boolean extraSauce, boolean extraCheese) {
        return new SpecialityPizza(pizzaType, size, extraSauce, extraCheese);
    }
}

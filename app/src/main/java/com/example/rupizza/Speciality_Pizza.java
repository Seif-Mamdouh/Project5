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

        // Create a list of Pizza types
        List<Pizza.PizzaType> pizzaTypeList = createPizzaList();

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create and set the adapter
        SpecialityPizzaAdapter adapter = new SpecialityPizzaAdapter(pizzaTypeList, this);

        recyclerView.setAdapter(adapter);
    }


    private List<Pizza.PizzaType> createPizzaList (){
        List <Pizza.PizzaType> list = new ArrayList<>();

        list.add(Pizza.PizzaType.DELUXE);
        list.add(Pizza.PizzaType.SUPREME);
        list.add(Pizza.PizzaType.MEATZZA);
        list.add(Pizza.PizzaType.SEAFOOD);
        list.add(Pizza.PizzaType.PEPPERONI);
        list.add(Pizza.PizzaType.HALAL);
        list.add(Pizza.PizzaType.CHEESE);
        list.add(Pizza.PizzaType.MIX_GRILL);
        list.add(Pizza.PizzaType.SALMON);
        list.add(Pizza.PizzaType.SHRIMP);
        list.add(Pizza.PizzaType.BUFFALO_CHICKEN);


        return list;
    }

}

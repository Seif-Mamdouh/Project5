package com.example.rupizza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.rupizza.RuPizza.Pizza;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.Nullable;



/**
 * Activity class representing a screen displaying a list of specialty pizzas.
 * Allows users to view and customize pizzas for ordering.
 *
 *  @author Seifeldeen Mohamed
 */
public class Speciality_Pizza extends AppCompatActivity {

    /**
     * Called when the activity is first created. Responsible for initializing the activity,
     * creating the pizza list, setting up the RecyclerView, and configuring the adapter.
     *
     * @param savedInstanceState A Bundle containing the activity's previously saved state, if any.
     */
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

    /**
     * Creates a list of specialty pizza types for display.
     *
     * @return A list of PizzaType representing different specialty pizzas.
     */
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

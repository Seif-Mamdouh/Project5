package com.example.rupizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSpecialityPizza = findViewById(R.id.button);
        Button buttonBuildYourOwn = findViewById(R.id.button2);
        Button buttonCurrentOrder = findViewById(R.id.button3);
        Button buttonStoreOrder = findViewById(R.id.button4);

        buttonSpecialityPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inflate the Speciality Pizza layout dynamically
                View specialityPizzaView = LayoutInflater.from(MainActivity.this).inflate(R.layout.speciality_pizza, null);
                setContentView(specialityPizzaView);
            }
        });


        buttonBuildYourOwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inflate the Speciality Pizza layout dynamically
                View buildYourOwnPizza = LayoutInflater.from(MainActivity.this).inflate(R.layout.build_your_own_pizza, null);
                setContentView(buildYourOwnPizza);
            }
        });


        buttonCurrentOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inflate the Speciality Pizza layout dynamically
                View currentOrder = LayoutInflater.from(MainActivity.this).inflate(R.layout.current_order, null);
                setContentView(currentOrder);
            }
        });


        buttonStoreOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inflate the Speciality Pizza layout dynamically
                View storeOrder = LayoutInflater.from(MainActivity.this).inflate(R.layout.store_order, null);
                setContentView(storeOrder);
            }
        });


    }
}

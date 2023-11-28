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
        Button buildYourOwn = findViewById(R.id.button2);
        Button currentOrder = findViewById(R.id.button3);
        Button storeOrder = findViewById(R.id.button4);

        buttonSpecialityPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add your intent code here
                Intent intent = new Intent(MainActivity.this, Speciality_Pizza.class);
                startActivity(intent);
            }
        });

        buildYourOwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add your intent code here
                Intent intent = new Intent(MainActivity.this, BuildYourOwn.class);
                startActivity(intent);
            }
        });

        currentOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add your intent code here
                Intent intent = new Intent(MainActivity.this, CurrentOrder.class);
                startActivity(intent);
            }
        });

        storeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add your intent code here
                Intent intent = new Intent(MainActivity.this, StoreOrders.class);
                startActivity(intent);
            }
        });
    }
}

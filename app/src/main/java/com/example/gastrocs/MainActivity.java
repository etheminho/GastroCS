package com.example.gastrocs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button kundebtn, restaurantbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kundebtn= (Button) findViewById(R.id.kundebtn);
        restaurantbtn= (Button) findViewById(R.id.restaurantbtn);
        kundebtn.setBackgroundColor(Color.GREEN);
        restaurantbtn.setBackgroundColor(Color.GREEN);
        kundebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kundebtn.setBackgroundColor(Color.WHITE);
                kundebtn.setTextColor(Color.BLACK);
                restaurantbtn.setBackgroundColor(Color.GREEN);
                restaurantbtn.setTextColor(Color.BLACK);
                Intent intent=new Intent(getApplicationContext(),kundeAnmelden.class);
                startActivity(intent);

            }
        });
        restaurantbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurantbtn.setBackgroundColor(Color.WHITE);
                restaurantbtn.setTextColor(Color.BLACK);
                kundebtn.setBackgroundColor(Color.GREEN);
                kundebtn.setTextColor(Color.BLACK);
                Intent intent=new Intent(getApplicationContext(),restaurantAnmelden.class);
                startActivity(intent);

            }
        });

    }
}
package com.example.gastrocs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class restaurantHome extends AppCompatActivity {
    Button emailBtn, maVerwaltungBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_home);
        emailBtn= findViewById(R.id.emailschreiben);
        maVerwaltungBtn=findViewById(R.id.mitarbeiterverwalten);
        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), emailAnschreiben.class);
                startActivity(intent);
            }
        });
        maVerwaltungBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), mitarbeiterVerwaltung.class);
                startActivity(intent);
            }
        });
    }
}
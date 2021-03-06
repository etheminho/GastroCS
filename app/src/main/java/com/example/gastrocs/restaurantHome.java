package com.example.gastrocs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class restaurantHome extends AppCompatActivity {

    Button emailBtn, maVerwaltungBtn, tischAnlegenBtn, gerichtAnlegenBtn, speiseKarteBtn, besucherBtn, feedbackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_home);
        tischAnlegenBtn=findViewById(R.id.tischanlegen);
        emailBtn= findViewById(R.id.emailschreiben);
        maVerwaltungBtn=findViewById(R.id.mitarbeiterverwalten);
        gerichtAnlegenBtn=findViewById(R.id.gerichtHinzufuegen);
        besucherBtn=findViewById(R.id.besucherButton);
        speiseKarteBtn=findViewById(R.id.speisekarte);
       feedbackBtn=findViewById(R.id.FBButton);
       feedbackBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(getApplicationContext(), FeedbackLesen.class);
               startActivity(intent);
           }
       });

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
        tischAnlegenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), TischHinzufuegen.class);
                startActivity(intent);
            }
        });
        gerichtAnlegenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), GerichtAnlegen.class);
                startActivity(intent);
            }
        });
       speiseKarteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), GerichtListe.class);
                startActivity(intent);
            }
        });
       besucherBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(getApplicationContext(), Besucher.class);
               startActivity(intent);
           }
       });


    }
}
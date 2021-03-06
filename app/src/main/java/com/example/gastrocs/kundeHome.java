package com.example.gastrocs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class kundeHome extends AppCompatActivity {
        Button coronaButton, feedbackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunde_home);
        coronaButton=findViewById(R.id.coronaBtn);
        kundeAnmelden ob1=new kundeAnmelden();
        feedbackButton=findViewById(R.id.feedbackBtn);
        Toast.makeText(kundeHome.this, "Angemeldeter Nutzer: "+ob1.session,Toast.LENGTH_SHORT).show();
        coronaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), CoronaZettel.class);
                startActivity(intent);


            }
        });
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Feedback.class);
                startActivity(intent);


            }
        });

    }
}
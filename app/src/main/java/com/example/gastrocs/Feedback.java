package com.example.gastrocs;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Feedback extends AppCompatActivity {
        Button submitBtn;
        RatingBar ratingBar1;
        EditText txt;
        DBHelperFeedback DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        submitBtn=findViewById(R.id.feedbackSubmit);
        ratingBar1=findViewById(R.id.ratingBar);
        txt=findViewById(R.id.bemerkung);
        DB =new DBHelperFeedback(this);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kundeAnmelden ob2=new kundeAnmelden();
                String anzahlStern= String.valueOf(ratingBar1.getRating());
                Calendar kalender=Calendar.getInstance();
                SimpleDateFormat datumFormat=new SimpleDateFormat("dd:MM:yyyy");
                String heute= datumFormat.format(kalender.getTime());
                SimpleDateFormat zeitFormat=new SimpleDateFormat("HH:mm");
                String uhrzeit= zeitFormat.format(kalender.getTime());
                String benutzerName = ob2.session;
                String textBemerkung=txt.getText().toString();

                if (anzahlStern.equals("") || benutzerName.equals("") || textBemerkung.equals("")) {
                    Toast.makeText(Feedback.this, "Bitte alle Felder ausfuellen", Toast.LENGTH_SHORT).show();
                }
                else{

                        boolean checkerInsert = DB.insertFeedback(benutzerName,textBemerkung,anzahlStern,heute ,uhrzeit);
                        if (checkerInsert == true) {
                            Toast.makeText(Feedback.this, "Wir haben Ihr Feedback erhalten!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Feedback.this, "Fehler beim Hinzufuegen der Daten", Toast.LENGTH_SHORT).show();
                        }

                }
            }
        });
    }
}
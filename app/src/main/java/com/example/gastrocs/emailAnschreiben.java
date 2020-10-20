package com.example.gastrocs;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class emailAnschreiben extends AppCompatActivity {
    EditText gender,name,nachrichttext;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_schreiben);
        name =findViewById(R.id.name);
        gender =findViewById(R.id.genus);
        nachrichttext =findViewById(R.id.nachrichttext);


        img =findViewById(R.id.teilen);
        img.setOnClickListener(new View.OnClickListener() {
            //DATUM
            Calendar kalender=Calendar.getInstance();
            SimpleDateFormat datumFormat=new SimpleDateFormat("dd:MM:yyyy");
            String heute= datumFormat.format(kalender.getTime());
            //Zeit

            SimpleDateFormat zeitFormat=new SimpleDateFormat("HH:mm");
            String uhrzeit= zeitFormat.format(kalender.getTime());

            String anrede;
            @Override
            public void onClick(View v) {
                String kundenName = name.getText().toString();
                String genus = gender.getText().toString();
                String nachricht = nachrichttext.getText().toString();


                if(genus.equals("herr")||genus.equals("Herr")){
                    anrede="Sehr geehrter Herr "+kundenName+", ";
                }
                else if(genus.equals("frau")||genus.equals("Frau")){
                    anrede="Sehr geehrte Frau "+kundenName+", ";
                }
                else{
                    anrede="Sehr geehrte Damen und Herren, ";

                }



                Intent intent= new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_TEXT,anrede+"\n\n"+nachricht+"\n\nMit freundlichen Grüßen"+"\n\nIhr GastroCS Team"+"\n\n\n\nAm: "+heute+"\nUm: "+uhrzeit+" Uhr");
                startActivity(intent);
            }
        });
    }
}
package com.example.gastrocs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CoronaZettel extends AppCompatActivity {
    EditText nm,plz1,str,hsnr,stdt,sonst,handynr;
    Button submitBtn;
    DBHelperCoronaZettel DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_zettel);
        nm=findViewById(R.id.besucherName);
        plz1=findViewById(R.id.plz);
        str=findViewById(R.id.strasse);
        hsnr=findViewById(R.id.hausnr);
        stdt=findViewById(R.id.stadt);
        sonst=findViewById(R.id.sonstiges);
        handynr=findViewById(R.id.handynummer);
        submitBtn=findViewById(R.id.submitButton);
        DB =new DBHelperCoronaZettel(this);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar kalender=Calendar.getInstance();
                SimpleDateFormat datumFormat=new SimpleDateFormat("dd:MM:yyyy");
                String heute= datumFormat.format(kalender.getTime());
                SimpleDateFormat zeitFormat=new SimpleDateFormat("HH:mm");
                String uhrzeit= zeitFormat.format(kalender.getTime());
                String nameTxt = nm.getText().toString();
                String telefonTxt = handynr.getText().toString();
                String hsnrTxt = hsnr.getText().toString();
                String plzTxt = plz1.getText().toString();
                String stdtTxt = stdt.getText().toString();
                String strasseTxt = str.getText().toString();
                String sonstTxt = sonst.getText().toString();

                if (nameTxt.equals("") || telefonTxt.equals("") || hsnrTxt.equals("") || plzTxt.equals("")|| stdtTxt.equals("") || strasseTxt.equals("") || sonstTxt.equals("")) {
                    Toast.makeText(CoronaZettel.this, "Bitte alle Felder ausfuellen", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean checkerInsert = DB.insertBesucher(nameTxt,telefonTxt ,plzTxt ,stdtTxt ,strasseTxt ,hsnrTxt,sonstTxt ,heute ,uhrzeit);
                    if (checkerInsert == true) {
                        Toast.makeText(CoronaZettel.this, "Daten wurden erfolgreich hinzugefuegt", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CoronaZettel.this, "Fehler beim Hinzufuegen der Daten", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
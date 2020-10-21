package com.example.gastrocs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class mitarbeiterVerwaltung extends AppCompatActivity {
    Button insert, update, delete, show, suche;
    EditText name, abteilung, email, gbsdatum;
    DBHelperMitarbeiterVerwaltung DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitarbeiter_verwaltung);
        name=findViewById(R.id.name);
        abteilung=findViewById(R.id.abteilung);
        email=findViewById(R.id.emailAdresse);
        gbsdatum=findViewById(R.id.gbdatum);
        insert=findViewById(R.id.insertBtn);
        update=findViewById(R.id.bearbeitenBtn);
        delete=findViewById(R.id.deleteBtn);
        show=findViewById(R.id.showBtn);
        suche=findViewById(R.id.sucheBtn);
        insert.setBackgroundColor(Color.GREEN);
        delete.setBackgroundColor(Color.RED);
        update.setBackgroundColor(Color.YELLOW);
        show.setBackgroundColor(Color.MAGENTA);
        DB =new DBHelperMitarbeiterVerwaltung(this);
        //insert der Daten
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt = name.getText().toString();
                String abteilungTxt = abteilung.getText().toString();
                String emailTxt = email.getText().toString();
                String gdTxt = gbsdatum.getText().toString();
                if (nameTxt.equals("") || abteilungTxt.equals("") || emailTxt.equals("") || gdTxt.equals("")) {
                    Toast.makeText(mitarbeiterVerwaltung.this, "Bitte alle Felder ausfuellen", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean checkerInsert = DB.insertDaten(nameTxt, abteilungTxt, gdTxt, emailTxt);
                    if (checkerInsert == true) {
                        Toast.makeText(mitarbeiterVerwaltung.this, "Daten wurden erfolgreich hinzugefuegt", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mitarbeiterVerwaltung.this, "Fehler beim Hinzufuegen der Daten", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //update der Daten
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt=name.getText().toString();
                String abteilungTxt=abteilung.getText().toString();
                String emailTxt=email.getText().toString();
                String gdTxt=gbsdatum.getText().toString();
                boolean checkerUpdate=DB.updateDaten(nameTxt,abteilungTxt,gdTxt,emailTxt);
                if(checkerUpdate==true){
                    Toast.makeText(mitarbeiterVerwaltung.this,"Daten wurden erfolgreich aktuallisiert",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(mitarbeiterVerwaltung.this,"Fehler beim Aktuallisieren der Daten",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //delete der Daten
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gbsdate=gbsdatum.getText().toString();
                boolean checkerDelete=DB.deleteDaten(gbsdate);
                if(checkerDelete==true){
                    Toast.makeText(mitarbeiterVerwaltung.this,"Daten wurden erfolgreich geloescht",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(mitarbeiterVerwaltung.this,"Daten wurden erfolgreich geloescht",Toast.LENGTH_SHORT).show();
                }
            }
        });
        // get der Daten
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor erg=DB.getDaten();
                if(erg.getCount()==0){
                    Toast.makeText(mitarbeiterVerwaltung.this,"Keine Daten vorhanden",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    StringBuffer stBf=new StringBuffer();
                    int counter=0;
                    while(erg.moveToNext()){
                        counter++;
                        stBf.append("Name: "+erg.getString(1)+"\n");
                        stBf.append("Abteilung: "+erg.getString(2)+"\n");
                        stBf.append("Geburtsdatum: "+erg.getString(3)+"\n");
                        stBf.append("E-Mail Adresse: "+erg.getString(4)+"\n");
                        stBf.append("=============================="+"\n\n");
                    }
                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(mitarbeiterVerwaltung.this);
                    alertDialog.setCancelable(true);
                    //Counter der MA
                    alertDialog.setTitle("Anzahl aller Mitarbeiter: "+counter);
                    alertDialog.setMessage(stBf.toString());
                    alertDialog.show();

                }

            }
        });

        // Suche nach jmd
        suche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt=name.getText().toString();
                Cursor erg=DB.sucheDaten(nameTxt);
                if(erg.getCount()==0){
                    Toast.makeText(mitarbeiterVerwaltung.this,"Keine Daten vorhanden",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    StringBuffer stBf=new StringBuffer();
                    int counter=0;
                    while(erg.moveToNext()){
                        counter++;
                        stBf.append("Name: "+erg.getString(1)+"\n");
                        stBf.append("Abteilung: "+erg.getString(2)+"\n");
                        stBf.append("Geburtsdatum: "+erg.getString(3)+"\n");
                        stBf.append("E-Mail Adresse: "+erg.getString(4)+"\n");
                        stBf.append("=============================="+"\n\n");
                    }
                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(mitarbeiterVerwaltung.this);
                    alertDialog.setCancelable(true);
                    //Counter der MA
                    alertDialog.setTitle("Anzahl der betroffenen Mitarbeiter: "+counter);
                    alertDialog.setMessage(stBf.toString());
                    alertDialog.show();

                }

            }
        });
    }
}
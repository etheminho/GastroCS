package com.example.gastrocs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TischHinzufuegen extends AppCompatActivity {
    Button insert, show, delete, deleteAlles;
    EditText AnzahlPlatz, reiheXBtn, tischId;
    DBHelperTischHinzufuegen DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tisch_hinzufuegen);
        AnzahlPlatz=findViewById(R.id.anzpltz);
        reiheXBtn=findViewById(R.id.reiheX);
        tischId=findViewById(R.id.idTisch);
        insert=findViewById(R.id.insertBtn);
        show=findViewById(R.id.showBtn);
        delete=findViewById(R.id.deleteBtn);
        deleteAlles=findViewById(R.id.deleteallBtn);
        //update=findViewById(R.id.bearbeitenBtn);
        // delete=findViewById(R.id.deleteBtn);
        // show=findViewById(R.id.showBtn);
        // suche=findViewById(R.id.sucheBtn);
        insert.setBackgroundColor(Color.GREEN);
        deleteAlles.setBackgroundColor(Color.RED);
        delete.setBackgroundColor(Color.YELLOW);
        show.setBackgroundColor(Color.MAGENTA);
        DB =new DBHelperTischHinzufuegen(this);
        //insert der Daten
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  plaetzeTxt = AnzahlPlatz.getText().toString();
                String reiheXTxt = reiheXBtn.getText().toString();

                if (reiheXTxt.equals("") || plaetzeTxt.equals("")) {
                    Toast.makeText(TischHinzufuegen.this, "Bitte alle Felder ausfuellen", Toast.LENGTH_SHORT).show();
                }
                else{
                    int rx=Integer.parseInt(reiheXTxt);
                    int plaetzeTxt1 =Integer.parseInt(plaetzeTxt);
                    if(rx>5||plaetzeTxt1>12||plaetzeTxt1<2){
                        Toast.makeText(TischHinzufuegen.this, "Das Restaurant verfÃ¼gt Ã¼ber 5 Reihen, Tisch darf zw.(2-12) groÃŸ sein!", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        if(checkVerfuegbareFlaesche("1")==true||checkVerfuegbareFlaesche("2")==true ||checkVerfuegbareFlaesche("3")==true ||checkVerfuegbareFlaesche("4")==true ||checkVerfuegbareFlaesche("5")==true){
                            Toast.makeText(TischHinzufuegen.this, "Achtung des Mindesabstands von 1.5 M, CORONA!", Toast.LENGTH_SHORT).show();
                        }else{
                            int laenge=plaetzeTxt1/2;

                            boolean checkerInsert = DB.insertDaten(plaetzeTxt1, laenge, reiheXTxt);
                            if (checkerInsert == true) {
                                Toast.makeText(TischHinzufuegen.this, "Der Tisch wurde angelegt", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(TischHinzufuegen.this, "Der Tisch konnte nicht angelegt werden", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }}}
        });


        // Zeige aller Tische
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor erg=DB.getAllerTische();
                if(erg.getCount()==0){
                    Toast.makeText(TischHinzufuegen.this,"Keine Tische vorhanden",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    StringBuffer stBf=new StringBuffer();
                    int counter=0;
                    while(erg.moveToNext()){
                        counter++;
                        stBf.append("Tisch-ID: "+erg.getString(0)+"\n");
                        stBf.append("Anzahl der Plätze: "+erg.getString(1)+" Personen\n");
                        stBf.append("Länge: "+erg.getString(2)+" m.\n");
                        stBf.append("breite: 3 m.\n");
                        stBf.append("Reihe: "+erg.getString(3)+"\n");

                        stBf.append("=============================="+"\n\n");
                    }
                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(TischHinzufuegen.this);
                    alertDialog.setCancelable(true);
                    //Counter der MA
                    alertDialog.setTitle("Anzahl aller Tische: "+counter);
                    alertDialog.setMessage(stBf.toString());
                    alertDialog.show();

                }

            }
        });
        // LÃ¶sche einen Tisch nach Koordinaten (X,Y)
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idT=tischId.getText().toString();

                boolean checkerDelete=DB.deleteEinenTisch(idT);
                if(checkerDelete==true){
                    Toast.makeText(TischHinzufuegen.this,"Der Tisch Nummer: "+"("+idT+")" +" wurde gelöscht",Toast.LENGTH_SHORT).show();
                }
                else if (checkerDelete!=true){
                    Toast.makeText(TischHinzufuegen.this,"Der Tisch Nummer: "+"("+idT+")" +" wurde gelöscht, falls er vorhanden ist",Toast.LENGTH_SHORT).show();
                }
            }
        });
        // LÃ¶sche aller Tische
        deleteAlles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkerDelete=DB.deleteAlleTische();
                if(checkerDelete==true){
                    Toast.makeText(TischHinzufuegen.this,"Ihr Restaurant hat jetzt keine Tische",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(TischHinzufuegen.this,"Ihr Restaurant hat jetzt keine Tische",Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    //daten checken
    public boolean checkVerfuegbareFlaesche(String reiheX){
        String nameTxt=reiheXBtn.getText().toString();
        Cursor erg=DB.sucheDaten(nameTxt);
        int meter=0;
        while(erg.moveToNext()){

            meter=meter+2+(Integer.parseInt(erg.getString(1)));

        }
        if(meter>49){
            return true;
        }
        else{
            return false;
        }



    }//
}
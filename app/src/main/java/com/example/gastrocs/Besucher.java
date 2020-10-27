package com.example.gastrocs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Besucher extends AppCompatActivity {
    DBHelperCoronaZettel DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_besucher);
        DB =new DBHelperCoronaZettel(this);
                Cursor erg=DB.getAllerTische();
                if(erg.getCount()==0){
                    Toast.makeText(Besucher.this,"Keine Besucher vorhanden",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    StringBuffer stBf=new StringBuffer();
                    int counter=0;
                    while(erg.moveToNext()){
                        counter++;
                        stBf.append("Name: "+erg.getString(0)+"\n");
                        stBf.append("Telefonnr.: "+erg.getString(1)+"\n");
                        stBf.append("PLZ: "+erg.getString(2)+"\n");
                        stBf.append("Stadt: "+erg.getString(3)+"\n");
                        stBf.append("Straße: "+erg.getString(4)+"\n");
                        stBf.append("Hausnr.: "+erg.getString(5)+"\n");
                        stBf.append("Sonstiges: "+erg.getString(6)+"\n");
                        stBf.append("Am: "+erg.getString(7)+"\n");
                        stBf.append("Um: "+erg.getString(8)+"\n");



                        stBf.append("=============================="+"\n\n");
                    }
                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(Besucher.this);
                    alertDialog.setCancelable(true);
                    //Counter der MA
                    alertDialog.setTitle("Anzahl der Besucher: "+counter);
                    alertDialog.setMessage(stBf.toString());
                    alertDialog.show();

                }



    }
}
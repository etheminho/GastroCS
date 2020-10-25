package com.example.gastrocs;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GerichtListe extends AppCompatActivity {
    GridView gridView;
    ArrayList<Gericht> liste;
    GerichtListeAdapter adapter=null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gericht_add_layout);

        gridView = (GridView) findViewById(R.id.fotoShow);
        liste=new ArrayList<>();
        adapter=new GerichtListeAdapter(this,R.layout.food_elemente,liste);
        gridView.setAdapter(adapter);
        // alle Daten holen aus SQLITE
        Cursor cursor=GerichtAnlegen.dbhelper.getDaten("Select * from Gericht");
        liste.clear();
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String preis=cursor.getString(2);
            byte[] img=cursor.getBlob(3);
            liste.add(new Gericht(name,preis,id,img));
        }
        adapter.notifyDataSetChanged();

    }
}

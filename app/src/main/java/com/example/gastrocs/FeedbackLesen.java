package com.example.gastrocs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class FeedbackLesen extends AppCompatActivity {
    DBHelperFeedback DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_lesen);
        DB=new DBHelperFeedback(this);
        Cursor erg=DB.getAllerFeedback();
        if(erg.getCount()==0){
            Toast.makeText(FeedbackLesen.this,"Keine Feedbacks vorhanden",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            StringBuffer stBf=new StringBuffer();
            int counter=0;
            while(erg.moveToNext()){
                counter++;
                stBf.append("Name: "+erg.getString(0)+"\n");
                stBf.append("Bemerkung: "+erg.getString(1)+"\n");
                stBf.append("Anzahl der Sterne: "+erg.getString(2)+"\n");
                stBf.append("Datum: "+erg.getString(3)+"\n");
                stBf.append("Zeit: "+erg.getString(4)+"\n");




                stBf.append("=============================="+"\n\n");
            }
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(FeedbackLesen.this);
            alertDialog.setCancelable(true);
            //Counter der MA
            alertDialog.setTitle("Anzahl der Feedbacks: "+counter);
            alertDialog.setMessage(stBf.toString());
            alertDialog.show();

        }

    }
}
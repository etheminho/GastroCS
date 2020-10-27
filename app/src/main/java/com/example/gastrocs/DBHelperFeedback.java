package com.example.gastrocs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperFeedback  extends SQLiteOpenHelper {
    public DBHelperFeedback(@Nullable Context context) {
        super(context, "feedback.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE  TABLE feedback (name TEXT,beschreibung TEXT, sterne TEXT, datum TEXT,zeit TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS feedback");

    }
    public boolean insertFeedback(String bname,String besch,String star,String date,String uhr){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name", bname);
        cv.put("beschreibung", besch);
        cv.put("sterne", star);
        cv.put("datum", date);
        cv.put("zeit", uhr);

        long erg=DB.insert("feedback",null,cv);
        if(erg==-1){
            return  false;}
        else{
            return true;
        }
    }




    public Cursor getAllerFeedback(){
        SQLiteDatabase DB= this.getWritableDatabase();

        Cursor cs=DB.rawQuery("select * from feedback",null);
        return cs;
    }




}



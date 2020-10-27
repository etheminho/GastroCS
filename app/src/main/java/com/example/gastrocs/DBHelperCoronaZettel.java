package com.example.gastrocs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperCoronaZettel  extends SQLiteOpenHelper {
    public DBHelperCoronaZettel(@Nullable Context context) {
        super(context, "coronadat.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE  TABLE coronainfo (name TEXT, nr TEXT, plz TEXT,stadt TEXT,strasse TEXT,hausnummer TEXT, sonst TEXT, datum TEXT,zeit TEXT, status TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS coronainfo");

    }
    public boolean insertBesucher(String bName,String nr,String plz,String stdt,String str,String hsnr,String sons,String dat,String zei, String statusc){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name", bName);
        cv.put("nr", nr);
        cv.put("plz", plz);
        cv.put("stadt", stdt);
        cv.put("strasse", str);
        cv.put("hausnummer", hsnr);
        cv.put("sonst", sons);
        cv.put("datum", dat);
        cv.put("zeit", zei);
        cv.put("status", statusc);
        long erg=DB.insert("coronainfo",null,cv);
        if(erg==-1){
            return  false;}
        else{
            return true;
        }
    }



    //zeige alle Tische
    public Cursor getAllerTische(){
        SQLiteDatabase DB= this.getWritableDatabase();

        Cursor cs=DB.rawQuery("select * from coronainfo",null);
        return cs;
    }




}



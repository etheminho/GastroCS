package com.example.gastrocs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperTischHinzufuegen extends SQLiteOpenHelper {
    public DBHelperTischHinzufuegen(@Nullable Context context) {
        super(context, "tischAnlegenCS.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE  TABLE Tisch (id INTEGER PRIMARY KEY AUTOINCREMENT,platzAnz TEXT, laenge INTEGER, reiheX TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS Tisch");

    }
    public boolean insertDaten(int platzAnz, int laenge,  String reiheX){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("platzAnz", platzAnz);
        cv.put("laenge", laenge);
        cv.put("reiheX", reiheX);
        long erg=DB.insert("Tisch",null,cv);
        if(erg==-1){
            return  false;}
        else{
            return true;
        }
    }

    public boolean deleteEinenTisch(String id){
        SQLiteDatabase DB= this.getWritableDatabase();


        Cursor cs=DB.rawQuery("Delete from Tisch  where id=?",new String[]{id});
        if(cs.getCount()>0){


            long erg=DB.delete("Tisch", "id=?", new String[]{id});
            if(erg==-1){
                return  false;}
            else{
                return true;
            }
        }else{
            return false;
        }}
    // Delete aller Tische
    public boolean deleteAlleTische(){
        SQLiteDatabase DB= this.getWritableDatabase();


        Cursor cs=DB.rawQuery("Delete from Tisch ",null);
        if(cs.getCount()>0){


            long erg=DB.delete("Tisch", null,null);
            if(erg==-1){
                return  false;}
            else{
                return true;
            }
        }else{
            return false;
        }}


    //zeige alle Tische
    public Cursor getAllerTische(){
        SQLiteDatabase DB= this.getWritableDatabase();

        Cursor cs=DB.rawQuery("Select * from Tisch",null);
        return cs;
    }

    //Suche nach Tischen in einer bestimmten Reihe
    public Cursor sucheDaten(String reiheX) {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cs = DB.rawQuery("Select * from Tisch where reiheX=?", new String[]{reiheX});
        return cs;
    }



}



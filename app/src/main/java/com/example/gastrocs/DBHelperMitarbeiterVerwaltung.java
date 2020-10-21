package com.example.gastrocs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperMitarbeiterVerwaltung extends SQLiteOpenHelper {
    public DBHelperMitarbeiterVerwaltung(@Nullable Context context) {
        super(context, "mitarbeiterVerwaltung.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE  TABLE Mitarbeiter (id INTEGER primary key autoincrement,name TEXT, abteilung TEXT, gbsdatum TEXT, email TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS Mitarbeiter");

    }
    public boolean insertDaten(String name, String abteilung, String gbsdatum, String email){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name", name);
        cv.put("abteilung", abteilung);
        cv.put("gbsdatum", gbsdatum);
        cv.put("email", email);
        long erg=DB.insert("Mitarbeiter",null,cv);
        if(erg==-1){
            return  false;}
        else{
            return true;
        }
    }

    public boolean updateDaten(String name, String abteilung, String gbsdatum, String email){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("abteilung", abteilung);
        cv.put("gbsdatum", gbsdatum);
        cv.put("email", email);
        Cursor cs=DB.rawQuery("SELECT * from Mitarbeiter where name=?",new String[]{name});
        if(cs.getCount()>0){


            long erg=DB.update("Mitarbeiter", cv,"name=?", new String[]{name});
            if(erg==-1){
                return  false;}
            else{
                return true;
            }
        }else{
            return false;
        }}

    public boolean deleteDaten(String gbsdatum){
        SQLiteDatabase DB= this.getWritableDatabase();


        Cursor cs=DB.rawQuery("Delete from Mitarbeiter where gbsdatum=?",new String[]{gbsdatum});
        if(cs.getCount()>0){


            long erg=DB.delete("Mitarbeiter", "gbsdatum=?", new String[]{gbsdatum});
            if(erg==-1){
                return  false;}
            else{
                return true;
            }
        }else{
            return false;
        }}
    //zeige alle Daten
    public Cursor getDaten(){
        SQLiteDatabase DB= this.getWritableDatabase();

        Cursor cs=DB.rawQuery("Select * from Mitarbeiter",null);
        return cs;
    }

    //suche nach jmd bestimmtem
    public Cursor sucheDaten(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cs = DB.rawQuery("Select * from Mitarbeiter where name=?", new String[]{name});
        return cs;
    }


}

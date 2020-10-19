package com.example.gastrocs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperRestaurant extends SQLiteOpenHelper {
    public static final String DBNAME = "database1.db";
    public DBHelperRestaurant(@Nullable Context context) {

        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table if not exists restkonto(benutzername TEXT primary key, kennwort TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP table if exists restkonto");

    }
    public boolean insertData(String benutzername,String kennwort){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("benutzername", benutzername );
        contentValues.put("kennwort", kennwort );
        long erg =MyDB.insert("restkonto",null,contentValues);
        if(erg==-1) return false;
        else return true;
    }
    public boolean checkBenutzername(String benutzername){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery("select * from restkonto where benutzername=?", new String[]{benutzername});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public boolean checkBenutzernameKennwort(String benutzername, String kennwort){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery("select * from restkonto where benutzername=? and kennwort = ?", new String[]{benutzername, kennwort});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}


























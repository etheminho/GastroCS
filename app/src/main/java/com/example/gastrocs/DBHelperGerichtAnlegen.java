package com.example.gastrocs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DBHelperGerichtAnlegen extends SQLiteOpenHelper {
    public DBHelperGerichtAnlegen(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void queryDaten(String query){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(query);
    }


    public void insertData(String name, String preis, byte[] image){
        SQLiteDatabase database=getWritableDatabase();
        String sql="INSERT INTO Gericht VALUES(NULL,?,?,?)";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,name);
        statement.bindString(2,preis);
        statement.bindBlob(3,image);
        statement.executeInsert();
    }

    public Cursor getDaten(String sql){
        SQLiteDatabase database=getWritableDatabase();
        return database.rawQuery(sql,null);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


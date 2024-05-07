package com.example.trabalho_ademar_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class gerenciadorDB extends SQLiteOpenHelper {

    private static final String BD_NAME = "aula3004.db";
    private static final int BD_VERSION = 1;
    private android.content.ContentValues ContentValues;

    public gerenciadorDB( Context context) {
        super(context, BD_NAME, null, BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE participante (" +
                "nome TEXT," +
                "CPF TEXT PRIMARY KEY," +
                "telefone TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS participante");
    }
    public boolean insertdate(String nome, String CPF, String telefone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues.put("nome", nome);
        ContentValues.put("CPF", CPF);
        ContentValues.put("telefone", telefone);
        long result = db.insert("participante", null, ContentValues);
        return result != -1;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM participante",null);

    }

}
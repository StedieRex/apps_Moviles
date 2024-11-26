package com.example.a14proyecto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper{
    String usuario = "create table usuario ( id int primary key not null, nom text, usuario text, pass text )";
    String insertUsuario = "insert into usuario values(1, 'gil', 'glp', '123456')";
    String insertUsuario2 = "insert into usuario values(2, 'marco', 'msg','123456')";

    String articulo ="create table articulo ( id int primary key not null, nom text, costo decimal(16,6), fecha text)";
    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(usuario);
        db.execSQL(insertUsuario);
        db.execSQL(insertUsuario2);
        db.execSQL(articulo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists suario");
        db.execSQL("drop table if exists articulo");
        onCreate(db);
    }
}

package com.example.a14proyecto;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MostrarActivity extends AppCompatActivity {
    RecyclerView rc;
    ArrayList<articulos>lista;
    ConexionSQLiteHelper conn;

    SQLiteDatabase db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mostrar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lista = new ArrayList<articulos>();
        rc = (RecyclerView) findViewById(R.id.RecyclerView);
        rc.setLayoutManager(new GridLayoutManager(this,1));

        /*
        //prueba con una lista de 10 productos al azar
        for(int i=0; i<10; i++){
            articulos a = new articulos("","i ","12","11/12/2024");
            lista.add(a);
        }/**/

        try{
            conn = new ConexionSQLiteHelper(this, "articulos", null, 3);
            db = conn.getReadableDatabase();

            String sql = "select id, nom, costo, fecha from articulo ";

            System.out.println(sql);

            Cursor cursor = db.rawQuery(sql,null);

            String id = "";
            String nom = "";
            String costo = "";
            String fecha = "";

            while(cursor.moveToNext()){
                id = ""+cursor.getInt(0);
                nom = cursor.getString(1);
                costo = "" + cursor.getDouble(2);
                fecha = cursor.getString(3);
                System.out.println("------------------------");
                System.out.println("------------------------");
                System.out.println("------------------------");
                articulos a = new articulos(id,nom,costo,fecha);
                lista.add(a);
                System.out.println("id: "+id+ " nom: "+nom+" costo: "+costo);

            }

            cursor.close();
            db.close();
        }catch (Exception ex){
            System.out.println("------------------------");
            System.out.println(ex.getMessage().toString());
            System.out.println("------------------------");
        }

        adaptar ad = new adaptar(lista);
        rc.setAdapter(ad);
    }
}
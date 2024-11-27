package com.example.a14proyecto;

import android.annotation.SuppressLint;
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

        for(int i=0; i<10; i++){
            articulos a = new articulos("","i ","12","11/12/2024");
            lista.add(a);
        }

        adaptar ad = new adaptar(lista);
        rc.setAdapter(ad);
    }
}
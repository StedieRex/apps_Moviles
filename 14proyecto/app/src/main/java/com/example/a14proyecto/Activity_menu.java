package com.example.a14proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_menu extends AppCompatActivity {
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {formCrear();}
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {formModificar();}
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {formEliminar();}
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {formMostrar();}
        });

    }

    public void formCrear(){
        Intent i = new Intent(this, CrearActivity.class);
        startActivity(i);
    }
    public void formModificar(){
        Intent i = new Intent(this, ModificarActivity.class);
        startActivity(i);
    }
    public void formEliminar(){
        Intent i = new Intent(this, EliminarActivity.class);
        startActivity(i);
    }
    public void formMostrar(){
        Intent i = new Intent(this, MostrarActivity.class);
        startActivity(i);
    }
}
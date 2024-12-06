package com.example.a3proyecto_micambioventanas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class gustosActivity extends AppCompatActivity {
    ImageView img;
    Button btn1;
    Button btndisgustos;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gustos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        img= (ImageView) findViewById(R.id.imageView2);
        btn1= (Button) findViewById(R.id.button5);
        btndisgustos = (Button) findViewById(R.id.button2);

        img.setImageResource(R.drawable.gusto3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                switch (i){
                    case 1:
                        img.setImageResource(R.drawable.gusto1);
                        break;
                    case 2:
                        img.setImageResource(R.drawable.gusto2);
                        break;
                    case 3:
                        img.setImageResource(R.drawable.gusto3);
                        i=0;
                        break;
                }

            }
        });

        btndisgustos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambioVentana(view);
            }
        });
    }
    public void cambioVentana(View v){
        Intent i = new Intent(this, disgustosActivity.class);
        startActivity(i);
    }
}
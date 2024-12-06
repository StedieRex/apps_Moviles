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

public class disgustosActivity extends AppCompatActivity {
    ImageView img;
    Button btn1;
    Button btngustos;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_disgustos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        img= (ImageView) findViewById(R.id.imageView);
        btn1= (Button) findViewById(R.id.button4);
        btngustos= (Button) findViewById(R.id.button3);

        img.setImageResource(R.drawable.disgusto3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                switch (i){
                    case 1:
                        img.setImageResource(R.drawable.disgusto1);
                        break;
                    case 2:
                        img.setImageResource(R.drawable.disgusto2);
                        break;
                    case 3:
                        img.setImageResource(R.drawable.disgusto3);
                        i=0;
                        break;
                }

            }
        });

        btngustos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               cambioVentana(view);
            }
        });
    }

    public void cambioVentana(View v){
        Intent i = new Intent(this, gustosActivity.class);
        startActivity(i);
    }
}
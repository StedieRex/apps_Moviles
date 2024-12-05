package com.example.a3proyecto_micambioventanas;

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

        img.setImageResource(R.drawable.cubo1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0){
                    img.setImageResource(R.drawable.cubo2);
                    i=1;
                }else{
                    img.setImageResource(R.drawable.cubo1);
                    i=0;
                }
            }
        });
    }
}
package com.example.a10practica;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    CountDownTimer timer;

    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        img = (ImageView) findViewById(R.id.imageView);
        i=0;
        startTime();
    }

    private void startTime(){
        timer = new CountDownTimer(5000, 555) {
            @Override
            public void onTick(long l) {
                i++;
                if(i==1){
                    img.setImageResource(R.drawable.a1);
                }else if(i==2){
                    img.setImageResource(R.drawable.a2);
                }else if(i==3){
                    img.setImageResource(R.drawable.a3);
                }else if(i==4){
                    img.setImageResource(R.drawable.a4);
                }else if(i==5){
                    img.setImageResource(R.drawable.a5);
                }else if(i==6){
                    img.setImageResource(R.drawable.a6);
                }else if(i==7){
                    img.setImageResource(R.drawable.a7);
                }else if(i==8){
                    img.setImageResource(R.drawable.a8);
                }else if(i==9){
                    img.setImageResource(R.drawable.a9);
                }
                System.out.println(""+i);
            }

            @Override
            public void onFinish() {
                i=0;
                startTime();
            }
        }.start();
    }
}